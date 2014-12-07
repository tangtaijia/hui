package com.hui.common.action.mobileaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.BaseActionSupport;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.webservice.IUserFollowWService;
import com.hui.common.webservice.IUserInfoWService;
import com.hui.common.webservice.WebServiceResult;

public class UserFollowAction extends BaseActionSupport {
	
	private static final Logger logger = Logger.getLogger(UserInfoAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -1048600017115902919L;
	
	private WebServiceResult jsonResult;
	
	@Autowired
	private IUserFollowWService userFollowWService;
	
	@Autowired
	private IUserInfoWService userInfoWService;
	
	private List<Object> resultList;
	
	public WebServiceResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(WebServiceResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}

	/**
	 * 获取好友/粉丝列表
	 */
	public String execute(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String page = request.getParameter("page");
			resultList = userFollowWService.getFollowList(huidaNo, Integer.valueOf(page));
		}
		return SUCCESS;
	}
	
	public String getFriendsList() {
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String page = request.getParameter("page");
			resultList = userFollowWService.getFriendsList(huidaNo, Integer.valueOf(page));
		}
		return SUCCESS;
	}
	
	/**
	 * 好友/粉丝 加关注和取消关注
	 * @return
	 */
	public String addFollow(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String other = request.getParameter("other");
			if(huidaNo.equals(other)){
				jsonResult = new WebServiceResult("不能添加自己为好友");
				return SUCCESS;
			}
			String status = request.getParameter("status");
			jsonResult = new WebServiceResult(1,"操作成功");
			try {
				userFollowWService.addFollow(huidaNo, other, Integer.valueOf(status));
			} catch (Exception e) {
				logger.error("添加好友失败:" + e.getMessage());
				e.printStackTrace();
				jsonResult = new WebServiceResult("添加好友失败:" + e.getMessage());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 搜索我的好友
	 * @return
	 */
	public String searchFriends(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			jsonResult = new WebServiceResult(0,FrontUtils.NOT_LOGIN);
		} else {
			String huidaNo = request.getParameter("huidaNo");
			String key = request.getParameter("key");
			String page = request.getParameter("page");
			resultList = userFollowWService.searchFriends(huidaNo, key,page);
		}
		return SUCCESS;
	
	}
	
	/**
	 * 搜索所有用户
	 * @return
	 */
	public String searchAllUsers(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		String key = request.getParameter("key");
		String page = request.getParameter("page");
		resultList = userFollowWService.searchAllUsers(huidaNo, key,page);
		return SUCCESS;
	
	}
	
	
	/**
	 * 获取他人信息
	 * @return
	 */
	public String getOtherInformation(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		String other = request.getParameter("other");
		resultList = userInfoWService.getOtherInformation(huidaNo,other);
		return SUCCESS;
	}
	
	/**
	 * 获取关注数
	 * @return
	 */
	public String getFollows(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("toHuiNo", huidaNo);
		int resultCount = userFollowWService.getFollowOrFriendCount(paramMap);
		HttpServletResponse response = getResponse();
		try {
			JSONObject json = new JSONObject();
			json.put("follows", resultCount);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得好友数
	 * @return
	 */
	public String getFriends(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("fromHuiNo", huidaNo);
		int resultCount = userFollowWService.getFollowOrFriendCount(paramMap);
		HttpServletResponse response = getResponse();
		try {
			JSONObject json = new JSONObject();
			json.put("friends", resultCount);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询是否我的好友
	 * @return
	 */
	public String isMyFriend(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		String other = request.getParameter("other");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("fromHuiNo", huidaNo);
		paramMap.put("toHuiNo", other);
		//int resultCount = userFollowWService.getFollowOrFriendCount(paramMap);
		
		int resultCount = 0;
		int me = userFollowWService.getFollowOrFriendCount(paramMap);//我是否关注Ta
		paramMap.put("fromHuiNo", other);
		paramMap.put("toHuiNo", huidaNo);
		int ta = userFollowWService.getFollowOrFriendCount(paramMap);//Ta是否关注我
		if(me==0)
			resultCount = 0;
		else if(me==1&&ta==0)
			resultCount = 1;
		else if(me==1 && ta==1)
			resultCount = 2;
			
		HttpServletResponse response = getResponse();
		try {
			JSONObject json = new JSONObject();
			json.put("status", resultCount);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
