package com.hui.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IMessageDao;
import com.hui.common.dao.IQuestionDao;
import com.hui.common.dao.IQuestionUserDao;
import com.hui.common.dao.ISysFileDao;
import com.hui.common.dao.ITeacherInfoDao;
import com.hui.common.dao.IUserDao;
import com.hui.common.entity.Answer;
import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.ImageTwo;
import com.hui.common.entity.InvKeywords;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Page;
import com.hui.common.entity.Question;
import com.hui.common.entity.QuestionUser;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.entity.User;
import com.hui.common.service.IAnswerService;
import com.hui.common.service.IBeanFlowService;
import com.hui.common.service.IQuestionService;
import com.hui.common.service.ISysFileService;
import com.hui.common.service.ITeacherInfoService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.FileUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.FtpUtils;

@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question, Question> implements IQuestionService
{
    
    @Autowired
    private IQuestionDao questionDao;
    @Autowired
    private IUserDao userDao;
    @Autowired 
    private IQuestionUserDao questionUserDao;
    @Autowired
    private IMessageDao messageDao;
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private ITeacherInfoDao teacherInfoDao;
    @Autowired
    private ISysFileDao sysFileDao;
    @Autowired
    private IBeanFlowService beanFlowService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISysFileService sysFileService;
    @Autowired
    private ITeacherInfoService teacherInfoService;
    
    public Integer modifyStatus(Integer status, List<Integer> ids)
    {
    	if(CommonUtils.isNotEmptyOrNull(ids)) {
    		for(Integer id:ids) {
    			/*删除答案*/
    			List<Answer> answers = answerService.getAnswers(null,id);
    			List<Integer> aIds = new ArrayList<Integer>();
    			for(Answer answer:answers) {
    				aIds.add(answer.getaId());
    			}
    			if(CommonUtils.isNotEmptyOrNull(aIds)) {
    				answerService.modifyStatus(0, aIds);
    			}
    			/*删除相关消息*/
    			Map<String,Object> paramMap = new HashMap<String, Object>();
    			paramMap.put("msgInfoId", id);
    			List<Msg> msgs = messageDao.getAll(paramMap);
    			if(CommonUtils.isNotEmptyOrNull(msgs)) {
    				for(Msg msg:msgs) {
    					messageDao.delete(msg.getMsgId());
    				}
    			}
    			/*删除相关用户问题数据*/
    			paramMap.put("qId", id);
    			List<QuestionUser> questionUsers  = questionUserDao.selectAllByMap(paramMap);
    			if(CommonUtils.isNotEmptyOrNull(questionUsers)) {
    				for(QuestionUser questionUser:questionUsers) {
    					questionUserDao.delete(questionUser.getQuId());
    				}
    			}
    		}
    	}
        return ((IQuestionDao)baseDao).modifyStatus(status, ids);
    }
   
    public Page<Question> getQuestions(Integer pageNo, String huiNo, String questionDesc, Integer isReward,
        Integer hasAnswer, Integer gradeId, Integer subId,Integer toTeacher)
    {
        Page<Question> page = new Page<Question>();
        page.setPage(pageNo);
        page.setSize(5);
        
        Map<String,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huidaNo", huiNo);
        paramMap.put("questionDesc", questionDesc);
        paramMap.put("isReward", isReward);
        paramMap.put("hasAnswer", hasAnswer);
        paramMap.put("gradeId", gradeId);
        paramMap.put("subId", subId);
        paramMap.put("toTeacher", toTeacher);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<Question> questions = this.questionDao.getAll(paramMap);
        if(CommonUtils.isNotEmptyOrNull(questions)) {
            for(Question question:questions) {
                /*处理特殊字符*/
                question = FrontUtils.handlerSpecWord(question);
                /*设定前台展示的创建时间*/
                question.setCreateTimeStr(CommonUtils.formatTimeStamp(question.getCreateTime(), CommonUtils.getLongDateFormat()));
                /*加图片*/
                if(1 == question.getHasImg()) {
                    List<SysFile> images = this.questionDao.selectImagesByQId(question.getqId());
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
                        question.setImageTwo(imageTwo);
                    }
                }
                /*设定提问者*/
                List<User> users =  userDao.selectByKey(new User(question.getHuiNo()));
                User user = null;
                if(CommonUtils.isNotEmptyOrNull(users)) {
                    user = users.get(0);
                    /*加图片*/
                    user = addIcon(user);
                }
                /*添加老师属性到用户*/
                user = teacherInfoService.pasteTeacher(user);
                question.setUser(user);
                /*最佳答案*/
//                if(1 == question.getHasFavorate()) {
//                    Answer favoranswer = this.answerService.getFavorAnswer(question.getqId());
//                    question.setFavorAnswer(favoranswer);
//                }
                /*加学霸*/
                List<User> superStudents = this.userDao.getUserInfoByQId(question.getqId());
                if(CommonUtils.isNotEmptyOrNull(superStudents)) {
                	for(User superStudent:superStudents) {
                		superStudent = teacherInfoService.pasteTeacher(superStudent);
                	}
                    question.setSuperStudents(superStudents);
                }
                /*针对手机端型号处理*/
                String clientStyle = question.getClientStyle();
                if(CommonUtils.isNotEmptyOrNull(clientStyle)) {
                    String[] clientStyleArr = clientStyle.split("===");
                    if(clientStyleArr.length>1) {
                        question.setClientStyle(clientStyleArr[0]);
                    }
                }
            }
        }
        Integer count = this.questionDao.getCount(paramMap);
        page.setDatas(questions);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
    public Question getQuestion(Integer questionId) {
    	return this.questionDao.selectById(questionId);
    }
   
    public Question selectById(Integer id)
    {
        Question question = this.questionDao.selectById(id);
        /*处理特殊字符*/
        question = FrontUtils.handlerSpecWord(question);
        /*加图片*/
        if(1 == question.getHasImg()) {
            List<SysFile> images = this.questionDao.selectImagesByQId(id);
            if(CommonUtils.isNotEmptyOrNull(images)) {
                ImageTwo imageTwo = new ImageTwo(DBUtils.getFilePath());
                for(SysFile image:images) {
                    if(1==image.getSeqId()) {
                        imageTwo.setImgLt(image);
                    }
                    if(2==image.getSeqId()) {
                        imageTwo.setImgOri(image);
                    }
                }
                question.setImageTwo(imageTwo);
            }
        }
        /*加学霸*/
        List<User> superStudents = this.userDao.getUserInfoByQId(id);
        if(CommonUtils.isNotEmptyOrNull(superStudents)) {
        	for(User superStudent:superStudents) {
        		superStudent = teacherInfoService.pasteTeacher(superStudent);
        	}
            question.setSuperStudents(superStudents);
        }
        /*设定前台展示的创建时间*/
        question.setCreateTimeStr(CommonUtils.formatTimeStamp(question.getCreateTime(), CommonUtils.getLongDateFormat()));
        /*设定提问者*/
        List<User> users = userDao.selectByKey(new User(question.getHuiNo()));
        if(CommonUtils.isNotEmptyOrNull(users)) {
            User user = users.get(0);
            /*加图片*/
            user = addIcon(user);
            user = teacherInfoService.pasteTeacher(user);
            question.setUser(user);
        }
        /*最佳答案*/
        if(1 == question.getHasFavorate()) {
            Answer favoranswer = this.answerService.getFavorAnswer(question.getqId());
            question.setFavorAnswer(favoranswer);
        }
        return question;
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
    
   
    public Question getQuestion4Back(Integer questionId)
    {
        Question question = super.selectById(questionId);
        /*设定前台展示的创建时间*/
        question.setCreateTimeStr(CommonUtils.formatTimeStamp(question.getCreateTime(), CommonUtils.getLongDateFormat()));
        return question;
    }
    
   
    public List<Question> getQuestions4Back(Question questionParam)
    {
        questionParam.setToTeacher(null);
        List<Question> questions = list(questionParam);
        if(CommonUtils.isNotEmptyOrNull(questions)) {
            for(Question question:questions) {
                /*处理特殊字符*/
                question = FrontUtils.handlerSpecWord(question);
                /*设置年级，学科*/
                question.setGradeStr(question.getGrade()==null?"":question.getGrade().getGradeName());
                question.setSubjectStr(question.getSubject()==null?"":question.getSubject().getSubName());
                
                /*设置教师*/
                TeacherInfo teacherInfo = null;
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("qId", question.getqId());
                List<QuestionUser> questionUsers = this.questionUserDao.selectAllByMap(paramMap);
                if(CommonUtils.isNotEmptyOrNull(questionUsers)) {
                    QuestionUser questionUser = questionUsers.get(0);
                    paramMap.put("huiNo", questionUser.getHuiNo());
                    List<TeacherInfo> teacherInfos = this.teacherInfoDao.selectAllByMap(paramMap);
                    if (CommonUtils.isNotEmptyOrNull(teacherInfos))
                    {
                        teacherInfo = teacherInfos.get(0);
                        question.setTeacherName(teacherInfo.getTeacherName());
                    }
                }
            }
        }
        return questions;
    }
    
   
    public List<Question> list(Question questionParam)
    {
    	List<Question> listQuestion = questionDao.getAll(questionParam);
    	if(CommonUtils.isNotEmptyOrNull(listQuestion)) {
            for(Question question:listQuestion) {
                /*处理特殊字符*/
                question = FrontUtils.handlerSpecWord(question);
                /*设置年级，学科*/
                question.setGradeStr(question.getGrade()==null?"":question.getGrade().getGradeName());
                question.setSubjectStr(question.getSubject()==null?"":question.getSubject().getSubName());
                
                /*设置教师*/
                TeacherInfo teacherInfo = null;
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("qId", question.getqId());
                List<QuestionUser> questionUsers = this.questionUserDao.selectAllByMap(paramMap);
                if(CommonUtils.isNotEmptyOrNull(questionUsers)) {
                    QuestionUser questionUser = questionUsers.get(0);
                    paramMap.put("huiNo", questionUser.getHuiNo());
                    List<TeacherInfo> teacherInfos = this.teacherInfoDao.selectAllByMap(paramMap);
                    if (CommonUtils.isNotEmptyOrNull(teacherInfos))
                    {
                        teacherInfo = teacherInfos.get(0);
                        question.setTeacherName(teacherInfo.getTeacherName());
                    }
                }
            }
        }
    	return listQuestion;
    }
    
   
    public Integer add(Question question,String[] studentHuiNos) {
    	//过滤非法词
    	String questionDesc = question.getQuestionDesc();
    	if(CommonUtils.isNotEmptyOrNull(questionDesc)) {
    		List<InvKeywords> invKeywordss = CommonUtils.getInvKeywordss();
    		for(InvKeywords invKeywords:invKeywordss) {
    			if(questionDesc.indexOf(invKeywords.getValue())>-1) {
    				questionDesc = questionDesc.replaceAll(invKeywords.getValue(), "***");
    			}
    		}
    		question.setQuestionDesc(CommonUtils.filterDangerString(questionDesc));//过滤危险字符
    	}
    	
        Integer questionId = saveEntity(question);
        
        User userParam = new User();
        userParam.setHuiNo(question.getHuiNo());
        List<User> users = userDao.selectByKey(userParam);
        User user = new User();
        if(CommonUtils.isNotEmptyOrNull(users)) {
            user = users.get(0);
        }
        if(CommonUtils.isNotEmptyOrNull(studentHuiNos)) {
            List<QuestionUser> questionUsers = new ArrayList<QuestionUser>();
            List<Msg> msgs = new ArrayList<Msg>();
            for(String huiNo:studentHuiNos) {
                /*设置问题求助*/
                QuestionUser questionUser = new QuestionUser();
                questionUser.setHuiNo(huiNo);
                questionUser.setQuestionId(questionId);
                questionUsers.add(questionUser);
                
                /*设置求助我答*/
                Msg msg = new Msg();
                msg.setCreateTime(CommonUtils.getNowInt());
                msg.setHuiNo(huiNo);
                msg.setIsRead(0);
                String msgContent = question.getQuestionDesc().trim();
            	if(question.getQuestionDesc().trim().length()>FrontUtils.MSG_STR_EX_LENGTH) {
            		msgContent = question.getQuestionDesc().trim().substring(0, FrontUtils.MSG_STR_EX_LENGTH)+"…";
        		}
                msg.setMsgContent(user.getNickName()+"求助了您“"+msgContent+"”的问题");
                msg.setMsgInfoId(questionId);
                msg.setMsgType(2);
                msgs.add(msg);
            }
            if(CommonUtils.isNotEmptyOrNull(questionUsers)) {
                questionUserDao.batchSave(questionUsers);
            }
            if(CommonUtils.isNotEmptyOrNull(msgs)) {
                messageDao.batchSave(msgs);
            }
        }
        
        return questionId;
    }
    
    public Integer add(Question questionParam, String[] studentHuiNos, String imgStr)
    {
        try
        {
            questionParam.setClientStyle("PC");
            questionParam.setCreateTime(CommonUtils.getNowInt());
            if (CommonUtils.isNotEmptyOrNull(imgStr))
            {
                questionParam.setHasImg(1);
            }
            else
            {
                questionParam.setHasImg(0);
            }
            if (CommonUtils.isNotEmptyOrNullOr0OrFalse(questionParam.getRewardAmount()))
            {
                questionParam.setIsReward(1);
                
                /*剩余汇豆*/
                Integer leftBeanNum = beanFlowService.getLastLeftBeanNum(questionParam.getHuiNo());
                User user = userService.getUser(questionParam.getHuiNo());
                if(CommonUtils.isEmptyOrNullOr0OrFalse(leftBeanNum)) {
                	leftBeanNum = user.getBeanNum();
                }
                
                /*扣减汇豆*/
                userService.minusBean(questionParam.getHuiNo(), questionParam.getRewardAmount());
                
                /*汇豆流水*/
                BeanFlow beanFlow = new BeanFlow();
                beanFlow.setBeanNum(questionParam.getRewardAmount());
                beanFlow.setLeftBeanNum(leftBeanNum-questionParam.getRewardAmount());//设置剩余汇豆
                beanFlow.setBfSn(CommonUtils.randomByUUID(10));
                beanFlow.setFlowTime(CommonUtils.getCurTime());
                beanFlow.setHuiNo(questionParam.getHuiNo());
                beanFlow.setInOut(0);
                beanFlow.setType(0);
                beanFlow.setStatus(1);
                beanFlowService.saveEntity(beanFlow);
            }
            questionParam.setHasAnswer(0);
            questionParam.setAnswerNum(0);
            questionParam.setHasFavorate(0);
            questionParam.setStatus(1);
            questionParam.setAllocated(0);
            Integer questionId = this.add(questionParam, studentHuiNos);
            if (CommonUtils.isNotEmptyOrNull(imgStr))
            {
                SysFile sysFile = new SysFile();
                sysFile.setFileType(4);
                sysFile.setDataId(questionId);
                sysFile.setFileName(imgStr);
                sysFile.setFilePath("/question/original");
                sysFile.setPathType(0);
                sysFile.setSeqId(2);
                sysFileService.saveEntity(sysFile);
                String[] imgStrArr = imgStr.split("[.]");//关于点的问题是用string.split("[.]") 解决。
                String imgLtStr = imgStrArr[0] + "-Lt" + "." + imgStrArr[1];
                sysFile.setFilePath("/question/thumbnail");
                sysFile.setSeqId(1);
                sysFile.setFileName(imgLtStr);
                sysFileService.saveEntity(sysFile);
                if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
					FtpUtils.cutSingleFile(DBUtils.getUploadPath() + "/temp",
							"question/original", imgStr);
					FtpUtils.cutSingleFile(DBUtils.getUploadPath() + "/temp",
							"question/thumbnail", imgLtStr);
                } else {
                	FileUtils.cutSingleFile(DBUtils.getUploadPath() + "/temp",
                			DBUtils.getUploadPath() + "/question/original",
                			imgStr);
                	FileUtils.cutSingleFile(DBUtils.getUploadPath() + "/temp",
                			DBUtils.getUploadPath() + "/question/thumbnail",
                			imgLtStr);
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
    
   
    public Boolean hasFavoriteAnswer(Integer questionId)
    {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("qId", questionId);
        paramMap.put("hasFavorate", 1);
        Integer count = questionDao.getCount(paramMap);
        if(count>0) {
            return true;
        }
        return false;
    }
}
