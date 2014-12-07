package com.hui.common.action.mobileaction;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.User;
import com.hui.common.service.IMessageService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.DESPlus72;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.HttpUtil;
import com.hui.common.utils.MD5Encrypt;
import com.hui.common.webservice.IUserInfoWService;
import com.hui.common.webservice.WebServiceResult;

public class UserInfoAction extends BaseActionSupport {
	
	private static final Logger logger = Logger.getLogger(UserInfoAction.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8970391148242327184L;
	
	@Autowired
	private IUserInfoWService userInfoWService;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IMessageService messageService;
	
	private WebServiceResult jsonResult;
	
	private List<User> result = new ArrayList<User>();

	public List<User> getResult() {
		return result;
	}

	public void setResult(List<User> result) {
		this.result = result;
	}

	public WebServiceResult getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(WebServiceResult jsonResult) {
		this.jsonResult = jsonResult;
	}

	/**
	 * 用户登录
	 */
	public String execute(){
		HttpServletRequest request = getRequest();
		String key = request.getParameter("key");
		String pwd = request.getParameter("pwd");
//		User user = userInfoWService.login(key, pwd);
		String url=DBUtils.getInstance().getLoginSite();
		String ztime = MessageFormat.format("{0,date,yyyyMMddHHmmss}" ,
				new Object[]       {
				new java.sql.Date(System.currentTimeMillis())
		});
		pwd = MD5Encrypt.encode(pwd);
		String zy = "{name:\""+key+"\",pwd:\""+pwd+"\"}|"+ztime;
		try {
			zy = new DESPlus72().encrypt(zy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("zy", zy);
		String httpResult = HttpUtil.http(url, paramMap);
		JSONObject jsonobject = JSONObject.parseObject(httpResult);
		String codeParam = jsonobject.getString("code");
//		String descriptionParam = jsonobject.getString("description");
		if("1".equals(codeParam)) {
			String nameParam = jsonobject.getJSONObject("userInfo").getString("name");
			String phoneParam = jsonobject.getJSONObject("userInfo").getString("phone");
			String userIconParam = jsonobject.getJSONObject("userInfo").getString("userIcon");
			String userIconLtParam = jsonobject.getJSONObject("userInfo").getString("userIconLt");
			String sexParam = jsonobject.getJSONObject("userInfo").getString("sex");
			String birthdateParam = jsonobject.getJSONObject("userInfo").getString("birthdate");
			String commonkeyParam = jsonobject.getJSONObject("userInfo").getString("commonkey");
			User user = userService.login3(0, nameParam, phoneParam, userIconParam,userIconLtParam,  sexParam, birthdateParam, commonkeyParam);
			if(CommonUtils.isNotEmptyOrNull(user)) {
				getRequest().getSession().setAttribute("user", user);
				userService.presentBean4Login1Day(user);//一天登录一次赠送汇豆
	            user.setUserPwd("");
	            Integer unReadNum = messageService.unReadNum(user.getHuiNo());//未读消息数量
	            Map<String,Object> userInfo = new HashMap<String, Object>();
	            userInfo.put("user", user);
	            userInfo.put("unReadNum", unReadNum);
	            getRequest().getSession().setAttribute("unReadNum", unReadNum);
			}
			if(user == null){
				jsonResult = new WebServiceResult("用户未注册或者密码不正确");
			}else{
				jsonResult = new WebServiceResult(1,user.getHuiNo());
				this.getRequest().getSession().setAttribute("user", user);
			}
		} else {
			jsonResult = new WebServiceResult("用户未注册或者密码不正确");
		}
		return SUCCESS;
	}
	
	/**
	 * 用户退出
	 */
	public String logout(){
		HttpServletRequest request = getRequest();
		if(CommonUtils.isNotEmptyOrNull(request.getSession())) {
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();
		}
		jsonResult = new WebServiceResult("注销成功！");
		return SUCCESS;
	}
	
	/**
	 * 判断是否已经登录
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String isLogin() {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		try {
			if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
				json.put(FrontUtils.IS_LOGIN, 0);
			} else {
				json.put(FrontUtils.IS_LOGIN, 1);
			}
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 检查昵称重复
	 * @return
	 */
	public String isNicknameExists(){
		HttpServletRequest request = getRequest();
		String nickName = request.getParameter("nickname");
		int count = userInfoWService.isNicknameExists(nickName);
		jsonResult = new WebServiceResult("昵称不存在");
		if(count > 0){
			jsonResult = new WebServiceResult(1,"");
		}
		return SUCCESS;
	}
	
	/**
	 * 检查手机号码重复
	 * @return
	 */
	public String isMobileExists(){
		HttpServletRequest request = getRequest();
		String mobile = request.getParameter("mobile");
		int count = userInfoWService.isMobileExists(mobile);
		jsonResult = new WebServiceResult("手机号码不存在");
		if(count > 0){
			jsonResult = new WebServiceResult(1,"");
		}
		return SUCCESS;
	}
	
	/**
	 * 注册用户
	 * @return
	 */
	public String register(){
		HttpServletRequest request = getRequest();
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		String nickname = request.getParameter("nickname");
		try {
			userInfoWService.register(mobile, pwd, nickname);
			jsonResult = new WebServiceResult(1,"注册成功");
			User user = userInfoWService.login(nickname, pwd);
			getRequest().getSession().setAttribute("user", user);
		} catch (Exception e) {
			jsonResult = new WebServiceResult("注册失败:" + e.getMessage());
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return SUCCESS;
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	public String resetPassword(){
		HttpServletRequest request = getRequest();
		String mobile = request.getParameter("mobile");
		String newPassword = request.getParameter("newPassword");
		try {
			userInfoWService.resetPassword(mobile, newPassword);
			jsonResult = new WebServiceResult(1,"重置密码成功");
		} catch (Exception e) {
			jsonResult = new WebServiceResult("重置密码失败:" + e.getMessage());
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return SUCCESS;
	}
	
	
	/**
	 * 获取用户昵称
	 * @param huidaNo
	 * @return
	 */
	public String getNickname(){
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		String huidaNo = request.getParameter("huidaNo");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		User user = userInfoWService.getUserInfoEntity(paramMap);
		try {
			response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			json.put("nickname", user.getNickName());
			json.put(FrontUtils.IS_LOGIN, 1);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得汇豆数
	 * @return
	 */
	public String getHuiDouCount(){
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		if(CommonUtils.isEmptyOrNull(request.getSession().getAttribute("user"))) {
			response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			json.put(FrontUtils.IS_LOGIN, 0);
			try {
				response.getWriter().write(json.toJSONString());
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		String huidaNo = request.getParameter("huidaNo");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		User user = userInfoWService.getUserInfoEntity(paramMap);
		try {
			response.setCharacterEncoding("UTF-8");
			JSONObject json = new JSONObject();
			json.put("huidouCount", Integer.valueOf(user.getBeanNum()));
			json.put(FrontUtils.IS_LOGIN, 1);
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	/**
	 * 修改用户昵称
	 * @return
	 */
	public String modifyNickname(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		String nickname = request.getParameter("nickname");
		jsonResult = new WebServiceResult(1, "修改昵称成功");
		try {
			userService.resetNickName(huidaNo, nickname);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new WebServiceResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 绑定手机号码
	 * @return
	 */
	public String binding(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		String mobile = request.getParameter("mobile");
		jsonResult = new WebServiceResult(1, "绑定号码成功");
		try {
			userService.bindMobile(huidaNo, mobile);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new WebServiceResult(e.getMessage());
		}
		return SUCCESS;
	
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String modifyPassword(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		jsonResult = new WebServiceResult(1, "修改密码成功");
		try {
			userInfoWService.modifyPassword(huidaNo, oldPassword, newPassword);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new WebServiceResult(e.getMessage());
		}
		return SUCCESS;
	
	}
	
	/**
	 * 发送验证码
	 * @return
	 */
	public String sendIdentifyingCode(){
		HttpServletResponse response = getResponse();
		JSONObject json = new JSONObject();
		json.put("identifyingCode","1234");
		try {
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得手机号
	 * @return
	 */
	public String getMobile(){
		HttpServletRequest request = getRequest();
		String huidaNo = request.getParameter("huidaNo");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("status", "0");
		User user = userInfoWService.getUserInfoEntity(paramMap);
		String mobile = "";
		if(user != null){
			mobile = user.getMobile();
		}
		HttpServletResponse response = getResponse();
		JSONObject json = new JSONObject();
		json.put("mobile",mobile);
		try {
			response.getWriter().write(json.toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
