package com.hui.common.webservice.impl;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hui.common.dao.IAnswerDao;
import com.hui.common.dao.IMessageDao;
import com.hui.common.dao.IQuestionDao;
import com.hui.common.dao.ISysFileDao;
import com.hui.common.dao.IUserDao;
import com.hui.common.entity.Answer;
import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Question;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.User;
import com.hui.common.service.IAnswerService;
import com.hui.common.service.IBeanFlowService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.FtpUtils;
import com.hui.common.utils.ImageUtils;
import com.hui.common.utils.PaginationConfig;
import com.hui.common.webservice.IAnswerInfoWService;
import com.opensymphony.xwork2.ActionContext;

@Service("answerInfoWService")
public class AnswerInfoWServiceImpl implements IAnswerInfoWService {
	
	@Autowired
	private IAnswerDao answerDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private ISysFileDao sysFileDao;
	
	@Autowired
	private IQuestionDao questionDao;
	
	@Autowired
	private IMessageDao messageDao;
	@Autowired
    private IBeanFlowService beanFlowService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAnswerService answerService;
	
	private final ReentrantLock lock = new ReentrantLock();

	
	public List getAnswerTrends(String huidaNo) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("huidaNo", huidaNo);
		List answerList= answerDao.getAnswerTrends(paramMap);
		return answerList;
	}

	
	public List getAnswerListByQuestionId(String questionId, String page) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("questionId", questionId);
		int offset = PaginationConfig.pageOffSet(Integer.valueOf(page));
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		paramMap.put("offset", String.valueOf(offset));
		List answerList= answerDao.getAnswerListByQuestionId(paramMap);
		return answerList;
	}

	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public void setBestAnswer(String questionId, String answerId) throws Exception {
		answerService.doFavorite(Integer.parseInt(answerId));
	}

	
	public List getMyAnswersPagingList(String huidaNo, String page) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("huidaNo", huidaNo);
		int offset = PaginationConfig.pageOffSet(Integer.valueOf(page));
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		paramMap.put("offset", String.valueOf(offset));
		List answerList= answerDao.getMyAnswersPagingList(paramMap);
		return answerList;
	}

	
	@Transactional(rollbackFor = Exception.class)
	public void answerQuestion(String huidaNo, String questionId,
			String answerDescription, String image,String mode,String os) throws Exception {
		int hasImg = 0;
		if(image != null && !"".equals(image)){
			hasImg = 1;
		}
		//生成回答记录
		Answer answer = new Answer();
		answer.setHuiNo(huidaNo);
		answer.setQuestionId(Integer.valueOf(questionId));
		answer.setAnswerDesc(answerDescription);
		answer.setPraiseNum(0);
		answer.setClientStyle(mode + "===" + os);
		answer.setCreateTime(CommonUtils.getCurTime());
		answer.setHasImg(hasImg);
		answer.setIsFavorate(0);
		answer.setStart(1);
		answer.setStatus(1);
		
		Integer answerId = answerService.addAnswer(answer);
		//创建回答图片
		if(hasImg == 1){
			byte[] imageByte = ImageUtils.convertBase64toImageByte(image);
			createPicData(huidaNo,imageByte,String.valueOf(answerId),"answer");
		}
	}
	
	/**
	 * 
	 * @param huidaNo
	 * @param imageByte
	 * @param dataId
	 * @param folder
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
			sysFile.setFileType(5);
			List resultList = sysFileDao.selectByKey(sysFile);
			if(resultList.size() == 0){
				sysFileDao.save(sysFile);
			}
			//缩略图SysFile
			sysFile.setFileName(thumbnalFileName);
			sysFile.setFilePath(thumbnalFilePath);
			sysFile.setSeqId(1);
			sysFile.setFileType(5);
			resultList = sysFileDao.selectByKey(sysFile);
			if(resultList.size() == 0){
				sysFileDao.save(sysFile);
			}
			//生成jpg原图片
	        String imgFilePath = DBUtils.getUploadPath() + orginalFilePath + orginalFileName;
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
	        imgFilePath = DBUtils.getUploadPath() + thumbnalFilePath + thumbnalFileName;
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
