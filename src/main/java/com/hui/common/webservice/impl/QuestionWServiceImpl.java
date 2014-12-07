package com.hui.common.webservice.impl;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hui.common.dao.IMessageDao;
import com.hui.common.dao.IQuestionDao;
import com.hui.common.dao.IQuestionUserDao;
import com.hui.common.dao.ISysFileDao;
import com.hui.common.dao.IUserDao;
import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.Grade;
import com.hui.common.entity.InvKeywords;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Question;
import com.hui.common.entity.QuestionUser;
import com.hui.common.entity.Subject;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.User;
import com.hui.common.service.IBeanFlowService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.FtpUtils;
import com.hui.common.utils.ImageUtils;
import com.hui.common.utils.PaginationConfig;
import com.hui.common.webservice.IQuestionWService;
import com.hui.common.webservice.EntityView.QuestionEntityView;

@Service("questionWService")
public class QuestionWServiceImpl implements IQuestionWService {
	
	@Autowired
	private IQuestionDao questionDao;
	
	@Autowired
	private ISysFileDao sysFileDao;
	
	@Autowired
	private IQuestionUserDao questionUserDao;
	
	@Autowired
	private IMessageDao messageDao;
	
	@Autowired
	private IUserDao userDao; 
	@Autowired
	private IBeanFlowService beanFlowService;
	@Autowired
	private IUserService userService;
	
	private final ReentrantLock lock = new ReentrantLock();

	
	public QuestionEntityView getQuestionDetailById(String questionId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("questionId", questionId);
		return questionDao.getQuestionDetailById(paramMap);
	}

	
	public List getMyQuestionsList(String huidaNo, String page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		paramMap.put("offset", String.valueOf(PaginationConfig.pageOffSet(Integer.valueOf(page))));
		return questionDao.getMyQuestionsList(paramMap);
	}

	
	public List getAllQuestionPaingList(String isReward, String isNoAnswer,String gradeId,String courseId,
			String page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		if(isReward != null && "1".equals(isReward)){
			paramMap.put("isReward", isReward);
		}
		if(isNoAnswer != null && "1".equals(isNoAnswer)){
			paramMap.put("isNoAnswer", "0");
		}
		
		if(gradeId != null && !"".equals(gradeId) && !"0".equals(gradeId)){
			paramMap.put("gradeId", gradeId);
		}
		if(courseId != null && !"".equals(courseId) && !"0".equals(courseId)){
			paramMap.put("courseId", courseId);
		}
		
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		paramMap.put("offset", String.valueOf(PaginationConfig.pageOffSet(Integer.valueOf(page))));
		
		return questionDao.getAllQuestionPaingList(paramMap);
	}

	
	public List getQuestionSearchByKey(String key, String page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("searchKey", key);
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		paramMap.put("offset", String.valueOf(PaginationConfig.pageOffSet(Integer.valueOf(page))));
		return questionDao.getAllQuestionPaingList(paramMap);
	}

	
	public List getLastSpeechUserList(String page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("pageSize", "3");
		paramMap.put("offset", String.valueOf(PaginationConfig.pageOffSet(Integer.valueOf(page))));
		return questionDao.getLastSpeechUserList(paramMap);
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void AskQuestion(Map<String, Object> paramMap) throws Exception {
		Question qt = new Question();
		Grade grade = new Grade();
		Subject subject = new Subject();
		String toTeacher = (String)paramMap.get("toTeacher");
		int rewardAmount = 0;
		if("1".equals(toTeacher)){
			rewardAmount = 5;
		}else{
			rewardAmount = Integer.valueOf((String)paramMap.get("reward"));
		}
		
		Map<String,String> userMap = new HashMap<String, String>();
		userMap.put("huidaNo", (String)paramMap.get("huidaNo"));
		User user =  userDao.getUserInfoEntity(userMap);
		if(user == null){
			throw new Exception("用户不存在");
		}
		int balance = user.getBeanNum();
		if(balance - rewardAmount < 0 ){
			throw new Exception("汇豆不足,请充值");
		}
		int isReward = 0;
		if(rewardAmount > 0){
			isReward = 1;
		}
		int hasImg = 0;
		String image = (String) paramMap.get("image");
		if(image != null && !"".equals(image)){
			hasImg = 1;
		}
		qt.setHuiNo((String)paramMap.get("huidaNo"));
		//过滤非法词
    	String questionDesc = (String)paramMap.get("questionDescription");
    	if(CommonUtils.isNotEmptyOrNull(questionDesc)) {
    		List<InvKeywords> invKeywordss = CommonUtils.getInvKeywordss();
    		for(InvKeywords invKeywords:invKeywordss) {
    			if(questionDesc.indexOf(invKeywords.getValue())>-1) {
    				questionDesc = questionDesc.replaceAll(invKeywords.getValue(), "***");
    			}
    		}
    		qt.setQuestionDesc(CommonUtils.filterDangerString(questionDesc));//过滤危险字符
    	} else {
    		qt.setQuestionDesc("");
    	}
		grade.setGradeId(Integer.valueOf((String)paramMap.get("gradeId")));
		qt.setGrade(grade);
		subject.setSubId(Integer.valueOf((String)paramMap.get("courseId")));
		qt.setSubject(subject);
		qt.setIsReward(isReward);
		qt.setRewardAmount(rewardAmount);
		qt.setCreateTime(CommonUtils.getCurTime());
		qt.setToTeacher(Integer.valueOf(toTeacher));
		qt.setAnswerNum(0);
		qt.setHasAnswer(0);
		qt.setHasImg(hasImg);
		qt.setHasFavorate(0);
		qt.setStatus(1);
		qt.setClientStyle((String)paramMap.get("mode") + "===" + (String)paramMap.get("os"));
		Integer questionId = questionDao.save(qt);
		//有图
		if(hasImg == 1){
			byte[] imageByte = ImageUtils.convertBase64toImageByte(image);
			createPicData((String)paramMap.get("huidaNo"),imageByte,String.valueOf(questionId),"question");
		}
		
		String[] friendArray = (String[]) paramMap.get("friendHuidaNo");
		//@学霸
		if(friendArray != null && friendArray.length > 0){
			QuestionUser qtuser = null;
			Msg msg = null;
			for(String huidaNo:friendArray){
				qtuser = new QuestionUser();
				qtuser.setHuiNo(huidaNo);
				qtuser.setQuestionId(questionId);
				questionUserDao.save(qtuser);
				//msg表添加消息提醒信息--求助我答
				msg = new Msg();
				msg.setCreateTime(CommonUtils.getCurTime());
				msg.setHuiNo(huidaNo);
				msg.setIsRead(0);
				msg.setMsgContent("");
				msg.setMsgType(2);
				msg.setMsgInfoId(questionId);
				messageDao.save(msg);
			}
		}
		//扣减汇豆
		if (CommonUtils.isNotEmptyOrNullOr0OrFalse(qt.getRewardAmount()))
        {
            /*剩余汇豆*/
            Integer leftBeanNum = beanFlowService.getLastLeftBeanNum(qt.getHuiNo());
            if(CommonUtils.isEmptyOrNullOr0OrFalse(leftBeanNum)) {
            	leftBeanNum = user.getBeanNum();
            }
            /*扣减汇豆*/
            userService.minusBean(qt.getHuiNo(), qt.getRewardAmount());
            
            /*汇豆流水*/
            BeanFlow beanFlow = new BeanFlow();
            beanFlow.setBeanNum(qt.getRewardAmount());
            beanFlow.setLeftBeanNum(leftBeanNum-qt.getRewardAmount());//设置剩余汇豆
            beanFlow.setBfSn(CommonUtils.randomByUUID(10));
            beanFlow.setFlowTime(CommonUtils.getCurTime());
            beanFlow.setHuiNo(qt.getHuiNo());
            beanFlow.setInOut(0);
            beanFlow.setType(0);
            beanFlow.setStatus(1);
            beanFlowService.saveEntity(beanFlow);
        }
		
	}
	
	/**
	 * 
	 * @param huidaNo	汇答号
	 * @param imageByte 图片字节数据
	 * @param dataId	对应关系id
	 * @param folder	文件夹
	 * @throws Exception
	 */
	public void createPicData(String huidaNo,byte[] imageByte,String dataId,String folder) throws Exception{
		String orginalFileName = huidaNo + "_original_pic_" + dataId + ".jpg";
		String orginalFilePath = "/" + folder + "/original/";
		
		String thumbnalFileName = huidaNo + "_thumbnail_pic_" + dataId + ".jpg";
		String thumbnalFilePath = "/" + folder + "/thumbnail/";
		try{
			lock.lock();
			//先创建数据,后生成图片
			//原图SysFile
			SysFile sysFile = new SysFile();
			sysFile.setDataId(Integer.valueOf(dataId));
			sysFile.setFileName(orginalFileName);
			sysFile.setFilePath(orginalFilePath);
			sysFile.setSeqId(2);
			sysFile.setFileType(4);
			List resultList = sysFileDao.selectByKey(sysFile);
			if(resultList.size() == 0){
				sysFileDao.save(sysFile);
			}
			//缩略图SysFile
			sysFile.setFileName(thumbnalFileName);
			sysFile.setFilePath(thumbnalFilePath);
			sysFile.setSeqId(1);
			sysFile.setFileType(4);
			resultList = sysFileDao.selectByKey(sysFile);
			if(resultList.size() == 0){
				sysFileDao.save(sysFile);
			}
			//生成jpg原图片
			String imgFilePath = DBUtils.getUploadPath()+ orginalFilePath + orginalFileName;
			if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
				InputStream ins = new ByteArrayInputStream(imageByte);
				FtpUtils.uploadFile(ins, orginalFilePath.substring(1, orginalFilePath.length()), orginalFileName);
			} else {
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(imageByte);
				out.flush();
				out.close();
			}
	        //生成缩略图
	        imgFilePath = DBUtils.getUploadPath()+ thumbnalFilePath + thumbnalFileName;
	        
//	        ImageUtils.createThumbnailImage(imageByte, 182, 182, imgFilePath, "jpg");
	        ImageUtils.createThumbnailImage(imageByte, 182, 182, thumbnalFilePath.substring(1, thumbnalFilePath.length()), thumbnalFileName, FilenameUtils.getExtension(thumbnalFileName));
		}catch(Exception e){
			throw e;
		}
		finally{
			lock.unlock();
		}
	}

}
