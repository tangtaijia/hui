package com.hui.common.dao;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.Fans;

public interface IUserFollowDao{
	
	public void addUserFollow(Map<String,String> paramMap) throws Exception;
	
	public void deleteUserFollow(Map<String,String> paramMap) throws Exception;
	
	public List<Fans> getUserFollowList(Map<String,String> paramMap);
	
	public List<Fans> getFriendsList(Map<String,String> paramMap);
	
	public List<Fans> searchFriends(Map<String,String> paramMap);
	
	public List<Fans> searchBullyFriends(Map<String,String> paramMap);
	
	public int getFollowOrFriendCount(Map<String,String> paramMap);
	
}
