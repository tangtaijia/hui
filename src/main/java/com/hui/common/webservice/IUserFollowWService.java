package com.hui.common.webservice;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.Fans;

public interface IUserFollowWService {
	
	public void addFollow(String huidaNo, String other, int status) throws Exception;
	
	public List<Object> getFollowList(String huidaNo, int page);
	
	public List<Object> getFriendsList(String huidaNo, int page);
	
	public List<Object> searchFriends(String huidaNo, String key,String page);
	
	public int getFollowOrFriendCount(Map<String,String> paramMap);
	
	public List<Object> searchAllUsers(String huidaNo, String key,String page);
	
}
