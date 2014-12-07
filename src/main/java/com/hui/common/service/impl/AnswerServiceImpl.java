package com.hui.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IAnswerDao;
import com.hui.common.dao.IMessageDao;
import com.hui.common.dao.IPraiseDao;
import com.hui.common.dao.IQuestionDao;
import com.hui.common.dao.ISysFileDao;
import com.hui.common.dao.IUserDao;
import com.hui.common.dao.IUserFollowDao;
import com.hui.common.entity.Answer;
import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.Fans;
import com.hui.common.entity.ImageTwo;
import com.hui.common.entity.InvKeywords;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Page;
import com.hui.common.entity.Praise;
import com.hui.common.entity.Question;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.User;
import com.hui.common.service.IAnswerService;
import com.hui.common.service.IBeanFlowService;
import com.hui.common.service.ISysFileService;
import com.hui.common.service.ITeacherInfoService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.FileUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.FtpUtils;

@Service("answerService")
public class AnswerServiceImpl extends BaseServiceImpl<Answer, Answer> implements IAnswerService
{
    
    @Autowired
    private IAnswerDao answerDao;
    @Autowired
    private IQuestionDao questionDao;
    @Autowired
    private ISysFileDao sysFileDao;
    @Autowired
    private IPraiseDao praiseDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IMessageDao messageDao;
    @Autowired
    private IUserFollowDao userFollowDao;
    @Autowired
    private ISysFileService sysFileService;
    @Autowired
    private ITeacherInfoService teacherInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBeanFlowService beanFlowService;
    
    public Integer modifyStatus(Integer status, List<Integer> ids)
    {
    	if(CommonUtils.isNotEmptyOrNull(ids)){
    		for(Integer id:ids) {
    			/*点赞清零*/
    			Answer answer = selectById(id);
    			Question question = questionDao.selectById(answer.getQuestionId());
    			answer.setHasPraise(0);
    			answer.setPraiseNum(0);
    			if(1==answer.getIsFavorate()) {
    				question.setHasFavorate(0);
    				answer.setIsFavorate(0);
    			}
    			answerDao.update(answer);
    			Praise praiseParam = new Praise();
    			praiseParam.setAnswerId(id);
    			praiseDao.deletePraise(praiseParam);
    			/*问题的答案数减少*/
    			Integer number = question.getAnswerNum();
    			question.setAnswerNum(--number);
    			question.setHasAnswer(0);
    			questionDao.update(question);
    			
    		}
    	}
        return ((IAnswerDao)baseDao).modifyStatus(status, ids);
    }
   
    public Page<Answer> getAnswers(Integer pageNo, String huiNo, Integer questionId)
    {
        Page<Answer> page = new Page<Answer>();
        page.setPage(pageNo);
        
        Map<String,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huiNo", huiNo);
        paramMap.put("questionId", questionId);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<Answer> answers = this.answerDao.selectAllByMap(paramMap);
        if(CommonUtils.isNotEmptyOrNull(answers)) {
            for(Answer answer:answers) {
                this.assembleAnswer(answer);
                /*加图片*/
                if(1 == answer.getQuestion().getHasImg()) {
                    List<SysFile> images = this.questionDao.selectImagesByQId(answer.getQuestionId());
                    if(CommonUtils.isNotEmptyOrNull(images)) {
                        ImageTwo imageTwo = new ImageTwo();
                        for(SysFile image:images) {
                            if(1==image.getSeqId()) {
                                imageTwo.setImgLt(image);
                            }
                            if(2==image.getSeqId()) {
                                imageTwo.setImgOri(image);
                            }
                        }
                        answer.getQuestion().setImageTwo(imageTwo);
                    }
                }
                /*设定前台展示的创建时间*/
                answer.getQuestion().setCreateTimeStr(CommonUtils.formatTimeStamp(answer.getQuestion().getCreateTime(), CommonUtils.getLongDateFormat()));
                /*针对手机端型号处理*/
                String clientStyle = answer.getClientStyle();
                if(CommonUtils.isNotEmptyOrNull(clientStyle)) {
                    String[] clientStyleArr = clientStyle.split("===");
                    if(clientStyleArr.length>1) {
                    	answer.setClientStyle(clientStyleArr[0]);
                    }
                }
            }
        }
        Integer count = this.answerDao.getCount(paramMap);
        page.setDatas(answers);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
   
    public Page<Answer> getAnswers(Integer pageNo, User userInfo, String huiNo, Integer questionId)
    {
        Page<Answer> page = getAnswers(pageNo, huiNo, questionId);
        Question question = questionDao.selectById(questionId);
        if(CommonUtils.isNotEmptyOrNull(userInfo)) {
            List<Answer> answers = page.getDatas();
            if(CommonUtils.isNotEmptyOrNull(answers)) {
                for(Answer answer:answers) {
                    Map<String,String> param = new HashMap<String, String>();
                    param.put("huidaNo", userInfo.getHuiNo());
                    param.put("answerId", answer.getaId().toString());
                    Integer praiseNum = praiseDao.queryPraiseCount(param);
                    this.assembleAnswer(answer,userInfo);
                    /*设置是否点赞*/
                    if(praiseNum > 0) {
                        answer.setHasPraise(1);
                    }
                    /*设置可否设为最佳答案*/ 
                    if(CommonUtils.isNotEmptyOrNull(question) && userInfo.getHuiNo().equals(question.getHuiNo())) {
                        answer.setCanFitFavor(1);
                    }
                    /*针对手机端型号处理*/
                    String clientStyle = answer.getClientStyle();
                    if(CommonUtils.isNotEmptyOrNull(clientStyle)) {
                        String[] clientStyleArr = clientStyle.split("===");
                        if(clientStyleArr.length>1) {
                        	answer.setClientStyle(clientStyleArr[0]);
                        }
                    }
                }
            }
        }
        return page;
    }
    
    public List<Answer> getAnswers( String huiNo,Integer questionId) {
    	 Map<String,Object> paramMap  = new HashMap<String, Object>();
         paramMap.put("huiNo", huiNo);
         paramMap.put("questionId", questionId);
         List<Answer> answers = this.answerDao.selectAllByMap(paramMap);
         return answers;
    }
    
   
    public Answer getAnswer(Integer aId)
    {
        Answer answer = selectById(aId);
        this.assembleAnswer(answer);
        return answer;
    }
    
   
    public Answer getFavorAnswer(Integer questionId)
    {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("questionId", questionId);
        paramMap.put("isFavorate", 1);
        List<Answer> answers = this.answerDao.selectAllByMap(paramMap);
        Answer answer = null;
        if(CommonUtils.isNotEmptyOrNull(answers)) {
            answer = answers.get(0);
            this.assembleAnswer(answer);
        }
        return answer;
    }
    
    /**
     * 组装回答
     * <功能详细描述>
     * @param answer
     * @param userInfo
     * @param huiNo
     * @param answerId
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    private Answer assembleAnswer(Answer answer,User userInfo) {
        /*设定前台展示的创建时间*/
        answer.setCreateTimeStr(CommonUtils.formatTimeStamp(answer.getCreateTime(), CommonUtils.getLongDateFormat()));
        answer = FrontUtils.handlerSpecWord(answer);
        /*加图片*/
        if(1 == answer.getHasImg()) {
            SysFile sysFileParam = new SysFile();
            sysFileParam.setFileType(5);
            sysFileParam.setDataId(answer.getaId());
            List<SysFile> images = sysFileDao.selectAll(sysFileParam);
            if(CommonUtils.isNotEmptyOrNull(images)) {
                ImageTwo imageTwo = new ImageTwo(DBUtils.getInstance().getRemoteFilePath());
                for(SysFile image:images) {
                    if(1==image.getSeqId()) {
                        imageTwo.setImgLt(image);
                    }
                    if(2==image.getSeqId()) {
                        imageTwo.setImgOri(image);
                    }
                }
                answer.setImageTwo(imageTwo);
            }
        }
        Map<String,String> param = new HashMap<String, String>();
        param.put("answerId", answer.getaId().toString());
        Integer praiseNum = praiseDao.queryPraiseCount(param);
        answer.setPraiseNum(praiseNum);//设置点赞数
        param.put("huidaNo", answer.getHuiNo());
        User user = userDao.queryUserInfoByHuiNo(param);
        /*加老师标示*/
        Boolean teacherFlag = teacherInfoService.isTeacher(user.getHuiNo());
        if(CommonUtils.isNotEmptyOrNullOr0OrFalse(teacherFlag)) {
            user.setTeacherFlag(true);
        }
        /*加图片*/
        user = addIcon(user);
        /*判断是否已关注*/
        if (CommonUtils.isNotEmptyOrNull(userInfo) && !userInfo.getHuiNo().equals(user.getHuiNo())
            && CommonUtils.isNotEmptyOrNull(userInfo.getHuiNo())&&CommonUtils.isNotEmptyOrNull(user.getHuiNo()))
        {
            param.clear();
            param.put("myHuiNo", userInfo.getHuiNo());
            param.put("otherHuiNo", user.getHuiNo());
            List<Fans> fans = this.userFollowDao.searchFriends(param);
            if(CommonUtils.isNotEmptyOrNull(fans) && fans.size()>1) {
                user.setFansToo(1);//互相关注
            } else {
                List<Fans> bullyFriends = this.userFollowDao.searchBullyFriends(param);
                if(CommonUtils.isNotEmptyOrNull(bullyFriends)) {
                    user.setFansToo(2);//已关注
                }
            }
        } else {
            user.setFansToo(0);
        }
        /*添加老师属性到用户*/
        user = teacherInfoService.pasteTeacher(user);
        answer.setUser(user);//设置回答者
        Question question = questionDao.selectById(answer.getQuestionId());
        answer.setQuestion(question);//设置问题
        return answer;
    }
    
    /**
     * 组装回答
     * <功能详细描述>
     * @param answer
     * @param huiNo
     * @param answerId
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    private Answer assembleAnswer(Answer answer) {
        return assembleAnswer(answer, null);
    }
    
    /**
     * 加图片
     * <功能详细描述>
     * @param user
     * @return
     * @see [类、类#方法、类#成员]
     */
    private User addIcon(User user) {
        SysFile sysFileParam = new SysFile();
        sysFileParam.setFileType(1);
        sysFileParam.setDataId(user.getUserId());
        List<SysFile> images = sysFileDao.selectAll(sysFileParam); 
        if(CommonUtils.isNotEmptyOrNull(images)) {
            ImageTwo imageTwo = new ImageTwo();
            for(SysFile image:images) {
                if(1==image.getSeqId()) {
                    imageTwo.setImgLt(image);
                }
                if(2==image.getSeqId()) {
                    imageTwo.setImgOri(image);
                }
            }
            user.setImageTwo(imageTwo);
        }
        return user;
    }
    
   
    public Integer doFavorite(Integer answerId)
    {
        Answer answer = this.answerDao.selectById(answerId);
        Map<String,String> param = new HashMap<String, String>();
        param.put("answerId", answer.getaId().toString());
        param.put("huidaNo", answer.getHuiNo());
        User user = userDao.queryUserInfoByHuiNo(param);
        answer.setUser(user);//设置回答者
        /*采纳数+1*/
        Integer acceptNo = user.getAcceptNo();
        if(CommonUtils.isEmptyOrNullOr0OrFalse(acceptNo)) {
        	acceptNo=0;
        }
    	user.setAcceptNo(++acceptNo);
    	userService.updateEntity(user);
    	
        Question question = questionDao.selectById(answer.getQuestionId());
        param.clear();
        param.put("huidaNo", question.getHuiNo());
        User questioner = userDao.queryUserInfoByHuiNo(param);
        question.setUser(questioner);//设置提问者
        answer.setQuestion(question);//设置问题
        if(1 == answer.getIsFavorate()) {
           return 0; 
        }
        final int BEAN_NUM = question.getRewardAmount();
        
        /*剩余汇豆*/
        Integer leftBeanNum = beanFlowService.getLastLeftBeanNum(answer.getHuiNo());
        if(CommonUtils.isEmptyOrNullOr0OrFalse(leftBeanNum)) {
        	leftBeanNum = userService.getUser(answer.getHuiNo()).getBeanNum();
        }
        
        /*添加汇豆*/
        userService.plusBean(answer.getHuiNo(), BEAN_NUM);
        
        /*汇豆流水*/
        BeanFlow beanFlow = new BeanFlow();
        beanFlow.setBeanNum(BEAN_NUM);
        beanFlow.setLeftBeanNum(leftBeanNum+BEAN_NUM);//设置剩余汇豆
        beanFlow.setBfSn(CommonUtils.randomByUUID(10));
        beanFlow.setFlowTime(CommonUtils.getCurTime());
        beanFlow.setHuiNo(answer.getHuiNo());
        beanFlow.setInOut(1);
        beanFlow.setType(1);
        beanFlow.setStatus(1);
        beanFlowService.saveEntity(beanFlow);
        
        /*设置回答被采纳*/
        Msg msg = new Msg();
        msg.setCreateTime(CommonUtils.getNowInt());
        msg.setHuiNo(answer.getUser().getHuiNo());
        msg.setIsRead(0);
        String msgContent = answer.getAnswerDesc().trim();
    	if(answer.getAnswerDesc().trim().length()>FrontUtils.MSG_STR_EX_LENGTH) {
    		msgContent = answer.getAnswerDesc().trim().substring(0, FrontUtils.MSG_STR_EX_LENGTH)+"…";
		}
        msg.setMsgContent(answer.getQuestion().getUser().getNickName()+"采纳了您“"+msgContent+"”的回答");
        msg.setMsgInfoId(answer.getQuestion().getqId());
        msg.setMsgType(3);
        messageDao.save(msg);
        answer.setIsFavorate(1);//设置本回答为最佳答案
        if(1 == question.getHasFavorate()) {
            return 0;
        }
        question.setHasFavorate(1);//设置相关问题为有最佳答案
        this.questionDao.update(question);
        return this.answerDao.update(answer);
    }
    
   
    public Integer addAnswer(Answer answer)
    {
    	//过滤非法词
    	String answerDesc = answer.getAnswerDesc();
    	if(CommonUtils.isNotEmptyOrNull(answerDesc)) {
    		List<InvKeywords> invKeywordss = CommonUtils.getInvKeywordss();
    		for(InvKeywords invKeywords:invKeywordss) {
    			if(answerDesc.indexOf(invKeywords.getValue())>-1) {
    				answerDesc = answerDesc.replaceAll(invKeywords.getValue(), "***");
    			}
    		}
    		answer.setAnswerDesc(CommonUtils.filterDangerString(answerDesc));//过滤危险字符
    	}
    	
        Map<String,String> param = new HashMap<String, String>();
        param.put("huidaNo", answer.getHuiNo());
        User user = userDao.queryUserInfoByHuiNo(param);
        answer.setUser(user);//设置回答者
        Question question = questionDao.selectById(answer.getQuestionId());
        param.clear();
        param.put("huidaNo", question.getHuiNo());
        User questioner = userDao.queryUserInfoByHuiNo(param);
        question.setUser(questioner);//设置提问者
        answer.setQuestion(question);//设置问题
        /*回答我的消息*/
        Msg msg = new Msg();
        msg.setCreateTime(CommonUtils.getNowInt());
        msg.setHuiNo(answer.getQuestion().getUser().getHuiNo());
        msg.setIsRead(0);
        if(CommonUtils.isEmptyOrNull(answer.getQuestion().getQuestionDesc())) {
            msg.setMsgContent(answer.getUser().getNickName()+"回答了您的提问");
        } else {
        	String msgContent = answer.getQuestion().getQuestionDesc().trim();
        	if(answer.getQuestion().getQuestionDesc().trim().length()>FrontUtils.MSG_STR_EX_LENGTH) {
        		msgContent = answer.getQuestion().getQuestionDesc().trim().substring(0, FrontUtils.MSG_STR_EX_LENGTH)+"…";
    		}
            msg.setMsgContent(answer.getUser().getNickName()+"回答了您“"+msgContent+"”的提问");
        }
        msg.setMsgInfoId(answer.getQuestion().getqId());
        msg.setMsgType(1);
        messageDao.save(msg);
        /*回答者回答数+1*/
        Integer answerNum = user.getAnswerNo();
        if(CommonUtils.isEmptyOrNullOr0OrFalse(answerNum)) {
        	answerNum=0;
        }
    	user.setAnswerNo(++answerNum);
    	userService.updateEntity(user);
        /*问题回答数+1*/
        if(CommonUtils.isEmptyOrNullOr0OrFalse(question.getHasAnswer())) {
            question.setHasAnswer(1);
        }
        /*添加汇豆*/
//        if (CommonUtils.isNotEmptyOrNullOr0OrFalse(question.getIsReward()))
//        {
//            
//            /*剩余汇豆*/
//            Integer leftBeanNum = beanFlowService.getLastLeftBeanNum(answer.getHuiNo());
//            if(CommonUtils.isEmptyOrNullOr0OrFalse(leftBeanNum)) {
//            	leftBeanNum = user.getBeanNum();
//            }
//            
//            /*添加汇豆*/
//            userService.plusBean(answer.getHuiNo(), question.getRewardAmount());
//            
//            /*汇豆流水*/
//            BeanFlow beanFlow = new BeanFlow();
//            beanFlow.setBeanNum(question.getRewardAmount());
//            beanFlow.setLeftBeanNum(leftBeanNum+question.getRewardAmount());//设置剩余汇豆
//            beanFlow.setBfSn(CommonUtils.randomByUUID(10));
//            beanFlow.setFlowTime(CommonUtils.getCurTime());
//            beanFlow.setHuiNo(answer.getHuiNo());
//            beanFlow.setInOut(1);
//            beanFlow.setType(0);
//            beanFlow.setStatus(1);
//            beanFlowService.saveEntity(beanFlow);
//        }
        question.setAnswerNum((question.getAnswerNum()==null?0:question.getAnswerNum())+1);
        questionDao.update(question);
        return answerDao.save(answer);
    }
    
   
    public Integer addAnswer(Answer answerParam, String imgStr)
    {
        try
        {
            answerParam.setClientStyle("PC");
            answerParam.setCreateTime(CommonUtils.getNowInt());
            if (CommonUtils.isNotEmptyOrNull(imgStr)) {
                answerParam.setHasImg(1);
            } else {
                answerParam.setHasImg(0);
            }
            answerParam.setHasPraise(0);
            answerParam.setIsFavorate(0);
            answerParam.setPraiseNum(0);
            answerParam.setStatus(1);
            Integer answerId = this.addAnswer(answerParam);
            /*回答图片*/
            if(CommonUtils.isNotEmptyOrNull(imgStr)) {
                SysFile sysFile = new SysFile();
                sysFile.setFileType(5);
                sysFile.setDataId(answerId);
                sysFile.setFileName(imgStr);
                sysFile.setFilePath("/answer/original");
                sysFile.setPathType(0);
                sysFile.setSeqId(2);
                sysFileService.saveEntity(sysFile);
                String[] imgStrArr = imgStr.split("[.]");//关于点的问题是用string.split("[.]") 解决。
                String imgLtStr = imgStrArr[0]+"-Lt"+ "." + imgStrArr[1];
                sysFile.setFilePath("/answer/thumbnail");
                sysFile.setSeqId(1);
                sysFile.setFileName(imgLtStr);
                sysFileService.saveEntity(sysFile);
                if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
                	FtpUtils.cutSingleFile(DBUtils.getUploadPath()+"/temp", "answer/original", imgStr);
                	FtpUtils.cutSingleFile(DBUtils.getUploadPath()+"/temp", "answer/thumbnail", imgLtStr);
                } else {
                	FileUtils.cutSingleFile(DBUtils.getUploadPath()+"/temp", DBUtils.getUploadPath()+"/answer/original", imgStr);
                	FileUtils.cutSingleFile(DBUtils.getUploadPath()+"/temp", DBUtils.getUploadPath()+"/answer/thumbnail", imgLtStr);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
