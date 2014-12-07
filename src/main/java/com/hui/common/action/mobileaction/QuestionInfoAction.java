package com.hui.common.action.mobileaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.SysFile;
import com.hui.common.service.ISysFileService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.ConvertObjectField;
import com.hui.common.utils.FrontUtils;
import com.hui.common.webservice.IQuestionWService;
import com.hui.common.webservice.WebServiceResult;
import com.hui.common.webservice.EntityView.QuestionEntityView;

public class QuestionInfoAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5222862478418111320L;
	
	@Autowired
	private IQuestionWService questionWService;
	
	@Autowired
	private ISysFileService sysFileService;
	
	private List resultList;
	
	public List getResultList() {
		return resultList;
	}
	
	private WebServiceResult jsonResult;

	public WebServiceResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(WebServiceResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String execute(){
		HttpServletRequest request = getRequest();
		String questionId = request.getParameter("questionId");
		QuestionEntityView view = questionWService.getQuestionDetailById(questionId);
		resultList = new ArrayList();
		if(view != null){
			int hasImg = view.getHasImage();
			if(hasImg == 1){
				SysFile sysFile = new SysFile();
				//获取缩略图url
				sysFile.setDataId(view.getQuestionId());
				sysFile.setFileType(4);
				sysFile.setSeqId(1);
				List ll = sysFileService.selectByKey(sysFile);
				if(ll.size() > 0){
					sysFile = (SysFile) ll.get(0);
					if(sysFile != null){
						String filePath = sysFile.getFilePath();
						String fileName = sysFile.getFileName();
						String path = CommonUtils.getFilePathPrefix() +filePath+"/" + fileName;
						view.setThumbnailUrl(path);
					}
				}
				//获取原图
				sysFile.setSeqId(2);
				ll = sysFileService.selectByKey(sysFile);
				if(ll.size() > 0){
					sysFile = (SysFile) ll.get(0);
					if(sysFile != null){
						view.setImageId(String.valueOf(sysFile.getFileId()));
					}
				}
			}
			ConvertObjectField.convertNullValue2Empty(view);
			resultList.add(view);
		}
		return SUCCESS;
	}
	
	/**
	 * 获取图片路径
	 * @return
	 */
	public String getImageUrl(){
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		String imageId = request.getParameter("imageId");
		String url = "";
		SysFile sysFile = sysFileService.selectById(Integer.valueOf(imageId));
		if(sysFile != null){
			String filePath = sysFile.getFilePath();
			String fileName = sysFile.getFileName();
			url = CommonUtils.getFilePathPrefix() +filePath+"/" + fileName;
		}
		try {
			JSONObject json = new JSONObject();
			json.put("url", url);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得我提出的所有问题列表
	 * @return
	 */
	public String getMyQuestionsList(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String page = request.getParameter("page");
			resultList = questionWService.getMyQuestionsList(huidaNo, page);
		}
		return SUCCESS;
	}
	
	/**
	 * 获得全部问题列表
	 * @return
	 */
	public String getAllQuestionPaingList(){
		HttpServletRequest request = getRequest();
		String isReward = request.getParameter("isReward");
		String isNoAnswer = request.getParameter("isNoAnswer");
		String page = request.getParameter("page");
		String gradeId = request.getParameter("gradeId");
		String courseId = request.getParameter("courseId");
		resultList = questionWService.getAllQuestionPaingList(isReward, isNoAnswer,gradeId,courseId,page);
		if(resultList.size() > 0){
			QuestionEntityView view = null;
			for(int i=0;i<resultList.size();i++){
				view = (QuestionEntityView) resultList.get(i);
				String questionDesc = view.getQuestionDescription();
				if(CommonUtils.isNotEmptyOrNull(questionDesc)) {
					if(questionDesc.length()>FrontUtils.QUESTION_DESC_EX_LENGTH) {
						view.setQuestionDescription(questionDesc.substring(0, FrontUtils.QUESTION_DESC_EX_LENGTH)+"…");
					}
				}
				int hasImg = view.getHasImage();
				if(hasImg == 1){
					SysFile sysFile = new SysFile();
					//获取缩略图url
					sysFile.setDataId(view.getQuestionId());
					sysFile.setFileType(4);
					sysFile.setSeqId(1);
					List ll = sysFileService.selectByKey(sysFile);
					if(ll.size() > 0){
						sysFile = (SysFile) ll.get(0);
						if(sysFile != null){
							String filePath = sysFile.getFilePath();
							String fileName = sysFile.getFileName();
							String path = CommonUtils.getFilePathPrefix() +filePath+"/" + fileName;
							view.setThumbnailUrl(path);
						}
					}
					//获取原图
					sysFile.setSeqId(2);
					ll = sysFileService.selectByKey(sysFile);
					if(ll.size() > 0){
						sysFile = (SysFile) ll.get(0);
						if(sysFile != null){
							view.setImageId(String.valueOf(sysFile.getFileId()));
						}
					}
				}
				ConvertObjectField.convertNullValue2Empty(view);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 获得搜索问题列表
	 * @return
	 */
	public String getQuestionSearchByKey(){
		HttpServletRequest request = getRequest();
		String key = request.getParameter("key");
		String page = request.getParameter("page");
		resultList = questionWService.getQuestionSearchByKey(key, page);
		return SUCCESS;
	}
	
	/**
	 * 获得最新发言用户列表
	 * @return
	 */
	public String getLastSpeechUserList(){
		HttpServletRequest request = getRequest();
		String page = request.getParameter("page");
		resultList = questionWService.getLastSpeechUserList(page);
		return SUCCESS;
	}
	
	/**
	 * 向同学提问
	 * @return
	 */
	public String AskStudent(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String questionDescription = request.getParameter("questionDescription");
			String image = request.getParameter("image");
			String gradeId = request.getParameter("gradeId");
			String courseId = request.getParameter("courseId");
			String reward = request.getParameter("reward");
			String mode = request.getParameter("mode");
			String os = request.getParameter("os");
			String friendHuidaNoString = request.getParameter("friendHuidaNo");
			String[] friendHuidaNo = friendHuidaNoString.split(",");
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("huidaNo", huidaNo);
			paramMap.put("questionDescription", questionDescription);
			paramMap.put("image", image);
			paramMap.put("gradeId", gradeId);
			paramMap.put("courseId", courseId);
			paramMap.put("reward", reward);
			paramMap.put("mode", mode);
			paramMap.put("os", os);
			paramMap.put("friendHuidaNo", friendHuidaNo);
			paramMap.put("toTeacher", "0");
			jsonResult = new WebServiceResult(1,"提问成功");
			try{
				questionWService.AskQuestion(paramMap);
			}catch(Exception e){
				e.printStackTrace();
				jsonResult = new WebServiceResult(e.getMessage());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 向老师提问
	 * @return
	 */
	public String AskTeacher(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String questionDescription = request.getParameter("questionDescription");
			String image = request.getParameter("image");
			String gradeId = request.getParameter("gradeId");
			String courseId = request.getParameter("courseId");
			String reward = request.getParameter("reward");
			String mode = request.getParameter("mode");
			String os = request.getParameter("os");
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("huidaNo", huidaNo);
			paramMap.put("questionDescription", questionDescription);
			paramMap.put("image", image);
			paramMap.put("gradeId", gradeId);
			paramMap.put("courseId", courseId);
			paramMap.put("reward", reward);
			paramMap.put("mode", mode);
			paramMap.put("os", os);
			paramMap.put("toTeacher", "1");
			jsonResult = new WebServiceResult(1,"提问成功");
			try{
				questionWService.AskQuestion(paramMap);
			}catch(Exception e){
				e.printStackTrace();
				jsonResult = new WebServiceResult(e.getMessage());
			}
		}
		return SUCCESS;
	}

}
