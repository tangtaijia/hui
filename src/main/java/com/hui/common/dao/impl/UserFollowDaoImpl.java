package com.hui.common.dao.impl;


import java.util.List;
import java.util.Map;

import com.hui.common.dao.IUserFollowDao;
import com.hui.common.entity.Fans;

public class UserFollowDaoImpl extends AbstractBaseDao implements IUserFollowDao {

	
	public void addUserFollow(Map<String, String> paramMap) throws Exception{
		getSqlMapClientTemplate().insert(namespace + ".addUserFollow", paramMap);
		
	}

	
	public List<Fans> getUserFollowList(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getFollowList", paramMap);
	}
	
	public List<Fans> getFriendsList(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getFriendsList", paramMap);
	}
	
	public void deleteUserFollow(Map<String, String> paramMap) throws Exception {
		getSqlMapClientTemplate().delete(namespace + ".deleteUserFollow", paramMap);
	}

	
	public List<Fans> searchFriends(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".searchFriends", paramMap);
	}
	
	@SuppressWarnings("unchecked")
   
	public List<Fans> searchBullyFriends(Map<String, String> paramMap)
	{
	    return getSqlMapClientTemplate().queryForList(namespace + ".searchBullyFriends", paramMap);
	}

	
	public int getFollowOrFriendCount(Map<String, String> paramMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject(namespace + ".getFollowOrFriendCount", paramMap);
	}


}
