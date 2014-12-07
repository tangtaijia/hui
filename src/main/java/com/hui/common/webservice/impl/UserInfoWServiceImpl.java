package com.hui.common.webservice.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IUserDao;
import com.hui.common.dao.IUserFollowDao;
import com.hui.common.entity.Fans;
import com.hui.common.entity.User;
import com.hui.common.utils.MD5Encrypt;
import com.hui.common.webservice.IUserInfoWService;
import com.hui.common.webservice.EntityView.OtherUserInfoEntityView;

@Service("userInfoWService")
public class UserInfoWServiceImpl implements IUserInfoWService {
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserFollowDao userFollowDao;
	
	/**
	 * 用户登录
	 */
	
	public User login(String key, String pwd) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("nickName", key);
		paramMap.put("password", MD5Encrypt.encode(pwd));
		User user = userDao.queryUserInfo(paramMap);
		return user;
	}
	
	/**
	 * 检查昵称重复
	 */
	
	public int isNicknameExists(String nickname) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("nickName", nickname);
		int count = userDao.isNicknameExists(paramMap);
		return count;
	}
	
	/**
	 * 检查手机号码重复
	 */
	
	public int isMobileExists(String mobile) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("mobile", mobile);
		int count = userDao.isMobileExists(paramMap);
		return count;
	}

	/**
	 * 注册 
	 */
	
	public void register(String mobile, String pwd, String nickname) throws Exception {
		Calendar cal = Calendar.getInstance();
		long millis = cal.getTimeInMillis();
		String millisStr = String.valueOf(millis);
		millisStr = millisStr.substring(millisStr.length()-8);
		User user = new User();
		user.setMobile(mobile);
		user.setUserPwd(MD5Encrypt.encode(pwd));
		user.setNickName(nickname);
		user.setBeanNum(0);
		user.setStatus(0);
		user.setHuiNo(millisStr);
		userDao.register(user);
	}

	/**
	 * 重置密码
	 */
	
	public void resetPassword(String mobile, String newPassword) throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("mobile", mobile);
		paramMap.put("password", MD5Encrypt.encode(newPassword));
		userDao.resetUserPasword(paramMap);
	}

	
	public List<Object> getOtherInformation(String huidaNo,String other) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("huidaNo", other);
		//查询他人信息
		User user = userDao.queryUserInfoByHuiNo(paramMap);
		OtherUserInfoEntityView entityView = new OtherUserInfoEntityView();
		List<Object> returnList = new ArrayList<Object>();
		if(user != null){
			entityView.setNickname(user.getNickName());
			entityView.setHuidaNo(other);
			entityView.setAnswers(user.getAnswerNo());
			entityView.setAccepts(user.getAcceptNo());
			paramMap.clear();
			paramMap.put("myHuiNo", huidaNo);
			paramMap.put("otherHuiNo", other);
			//查询好友关系
			List<Fans> fansList = userFollowDao.searchFriends(paramMap);
			if(fansList.size() == 0){
				entityView.setStatus(0);
			}
			if(fansList.size() == 1){
				Fans fans = fansList.get(0);
				if(fans.getFromNo().equals(huidaNo)){
					entityView.setStatus(1);
				}else{
					entityView.setStatus(0);
				}
			}
			if(fansList.size() == 2){
				entityView.setStatus(2);
			}
		}
		returnList.add(entityView);
		return returnList;
	}

	/**
	 * 获取用户信息
	 */
	
	public User getUserInfoEntity(Map<String,String> paramMap) {
		User user = userDao.getUserInfoEntity(paramMap);
		return user;
	}

	
	public void modifyPassword(String huidaNo, String oldPassword,
			String newPassword) throws Exception {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("oldPassword", MD5Encrypt.encode(oldPassword));
		paramMap.put("newPassword", MD5Encrypt.encode(newPassword));
		User user = new User();
		user.setHuiNo(huidaNo);
		user.setUserPwd(MD5Encrypt.encode(oldPassword));
		List<User> userList = userDao.selectByKey(user);
		if(userList.size() == 0){
			throw new Exception("旧密码不正确");
		}
		userDao.modifyPassword(paramMap);
	}


}
