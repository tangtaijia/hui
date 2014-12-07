package com.hui.common.action.mobileaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.ImageTwo;
import com.hui.common.entity.Msg;
import com.hui.common.entity.SysMsg;
import com.hui.common.entity.User;
import com.hui.common.service.IMessageService;
import com.hui.common.service.ISysMsgService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.webservice.IMessageWService;
import com.hui.common.webservice.WebServiceResult;
import com.hui.common.webservice.EntityView.MessageEntityView;
import com.hui.common.webservice.EntityView.SysMessageEntityView;

public class MessageAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8146837508867292606L;
	
	@Autowired
	private IMessageWService messageWService;
	
	@Autowired
	private ISysMsgService sysMsgServiceImpl;
	
	@Autowired
	private IMessageService messageService;
	
	private WebServiceResult jsonResult;
	
	private List resultList;
	
	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public WebServiceResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(WebServiceResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	/**
	 * 获得未读“回答我的”数
	 */
	public String execute(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			int resultCount = messageWService.getAnswerMeCountByUnread(huidaNo);
			HttpServletResponse response = getResponse();
			try {
				JSONObject json = new JSONObject();
				json.put("count",resultCount);
				response.getWriter().write(json.toJSONString());
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获得未读“求助我的”数
	 * @return
	 */
	public String getAskMeCountByUnread(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			int resultCount = messageWService.getAskMeCountByUnread(huidaNo);
			HttpServletResponse response = getResponse();
			try {
				JSONObject json = new JSONObject();
				json.put("count",resultCount);
				response.getWriter().write(json.toJSONString());
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获得未读“回答被采纳”数
	 * @return
	 */
	public String getAcceptMeCountByUnread(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			int resultCount = messageWService.getAcceptMeCountByUnread(huidaNo);
			HttpServletResponse response = getResponse();
			try {
				JSONObject json = new JSONObject();
				json.put("count",resultCount);
				response.getWriter().write(json.toJSONString());
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获得未读“系统消息”数
	 * @return
	 */
	public String getSystemMessageByUnread(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			int resultCount = messageWService.getSystemMessageByUnread(huidaNo);
			HttpServletResponse response = getResponse();
			try {
				JSONObject json = new JSONObject();
				json.put("count",resultCount);
				response.getWriter().write(json.toJSONString());
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获得“回答我的”列表
	 * @return
	 */
	public String getAnswerMeList(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String page = request.getParameter("page");
			List list = messageWService.getAnswerMeList(huidaNo, page);
			Date currDate = Calendar.getInstance().getTime();
			resultList = new ArrayList();
			if(list.size() > 0){
				MessageEntityView view = null;
				for(int i=0;i<list.size();i++){
					Msg msg = (Msg) list.get(i);
					view = new MessageEntityView();
					view.setQuestionNo(msg.getMsgInfoId());
					view.setTitle(msg.getMsgContent());
					view.setFlag(msg.getIsRead());
					view.setTimeTick(Long.valueOf(msg.getCreateTime()));
					resultList.add(view);
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 获得“求助我的”列表
	 * @return
	 */
	public String getAskMeList(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String page = request.getParameter("page");
			List list = messageWService.getAskMeList(huidaNo, page);
			resultList = new ArrayList();
			if(list.size() > 0){
				MessageEntityView view = null;
				for(int i=0;i<list.size();i++){
					Msg msg = (Msg) list.get(i);
					view = new MessageEntityView();
					view.setQuestionNo(msg.getMsgInfoId());
					view.setTitle(msg.getMsgContent());
					view.setFlag(msg.getIsRead());
					view.setTimeTick(Long.valueOf(msg.getCreateTime()));
					resultList.add(view);
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 获得“回答被采纳”列表
	 * @return
	 */
	public String getAcceptMeList(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String page = request.getParameter("page");
			List list = messageWService.getAcceptMeList(huidaNo, page);
			Date currDate = Calendar.getInstance().getTime();
			resultList = new ArrayList();
			if(list.size() > 0){
				MessageEntityView view = null;
				for(int i=0;i<list.size();i++){
					Msg msg = (Msg) list.get(i);
					view = new MessageEntityView();
					view.setQuestionNo(msg.getMsgInfoId());
					view.setTitle(msg.getMsgContent());
					view.setFlag(msg.getIsRead());
					view.setTimeTick(Long.valueOf(msg.getCreateTime()));
					resultList.add(view);
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 获得“系统消息”列表
	 * @return
	 */
	public String getSystemMessageList(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String page = request.getParameter("page");
			List list = messageWService.getSystemMessageList(huidaNo, page);
			resultList = new ArrayList();
			if(list.size() > 0){
				MessageEntityView view = null;
				for(int i=0;i<list.size();i++){
					Msg msg = (Msg) list.get(i);
					view = new MessageEntityView();
					view.setQuestionNo(msg.getMsgId());
					view.setTitle(msg.getMsgContent());
					view.setFlag(msg.getIsRead());
					view.setTimeTick(Long.valueOf(msg.getCreateTime()));
					resultList.add(view);
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 获取系统消息信息
	 * @return
	 */
	public String getSystemMessageDetail(){
		HttpServletRequest request = getRequest();
		String messageId = request.getParameter("messageId");
		SysMsg sysMsg =  sysMsgServiceImpl.getSysMsgDetail(Integer.valueOf(messageId));
		resultList = new ArrayList();
		
//		if(CommonUtils.isNotEmptyOrNull(request.getSession().getAttribute("user"))) {
//			String huiNo = ((User)request.getSession().getAttribute("user")).getHuiNo();
//			
//			messageService.readMsg(Integer.parseInt(messageId), huiNo);
//		}
		if(sysMsg != null){
			SysMessageEntityView view = new SysMessageEntityView();
			view.setDescription(sysMsg.getSysMsgDesc());
			view.setMessageId(sysMsg.getSysMsgId());
			String url = "";
			String originalImageId = "";
			List<ImageTwo> imageTwos = sysMsg.getImageTwos();
			if(CommonUtils.isNotEmptyOrNull(imageTwos)) {
			    for(ImageTwo imageTwo:imageTwos) {
			      //缩略图url
			        String filePath = imageTwo.getImgLt().getFilePath();
                    String fileName = imageTwo.getImgLt().getFileName();
                    url = CommonUtils.getFilePathPrefix() +filePath+"/" + fileName;
                  //原图id
                    originalImageId = String.valueOf(imageTwo.getImgOri().getFileId());
			    }
			}
			view.setThumbnailUrl(url);
			view.setImageId(originalImageId);
			view.setTimeTick(sysMsg.getCreateTime());
			resultList.add(view);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 设置系统消息已读
	 * @return
	 */
	public String setMessageRead(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String messageId = request.getParameter("messageId");
			jsonResult = new WebServiceResult(1, "设置成功");
			try {
				messageWService.setMessageRead(huidaNo, messageId);
			} catch (Exception e) {
				jsonResult = new WebServiceResult(e.getMessage());
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 设置回答我的问题已读
	 */
	public String setAnswerMeRead(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String questionId = request.getParameter("questionId");
			jsonResult = new WebServiceResult(1, "设置成功");
			try {
				messageWService.setMessageReadByCondition(huidaNo, questionId, "1");
			} catch (Exception e) {
				jsonResult = new WebServiceResult(e.getMessage());
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 设置求助我的问题已读
	 * @return
	 */
	public String setAskMeRead(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String questionId = request.getParameter("questionId");
			jsonResult = new WebServiceResult(1, "设置成功");
			try {
				messageWService.setMessageReadByCondition(huidaNo, questionId, "2");
			} catch (Exception e) {
				jsonResult = new WebServiceResult(e.getMessage());
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 设置回答被采纳的问题已读
	 * @return
	 */
	public String setAcceptMeRead(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String questionId = request.getParameter("questionId");
			jsonResult = new WebServiceResult(1, "设置成功");
			try {
				messageWService.setMessageReadByCondition(huidaNo, questionId, "3");
			} catch (Exception e) {
				jsonResult = new WebServiceResult(e.getMessage());
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	

}
