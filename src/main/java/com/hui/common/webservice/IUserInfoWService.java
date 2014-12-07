package com.hui.common.webservice;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.User;

public interface IUserInfoWService {
	
	/**
	 * 用户登录
	 */
	public User login(String key,String pwd);
	
	public int isNicknameExists(String nickname);
	
	public int isMobileExists(String mobile);
	
	public void register(String mobile, String pwd, String nickname) throws Exception;
	
	public void resetPassword(String mobile, String newPassword) throws Exception;
	
	public List<Object> getOtherInformation(String huidaNo,String other);
	
	public User getUserInfoEntity(Map<String,String> map);
	
	public void modifyPassword(String huidaNo, String oldPassword, String newPassword) throws Exception;
	
}
