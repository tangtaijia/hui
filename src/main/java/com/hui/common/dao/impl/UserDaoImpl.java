package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.IUserDao;
import com.hui.common.entity.User;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UserDaoImpl extends AbstractBaseDao<User, User> implements IUserDao
{
    
    public Integer modifyStatus(Integer status, List<Integer> ids)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", status);
        param.put("ids", ids);
        return (Integer)getSqlMapClientTemplate().update(namespace + ".modifyStatus", param);
    }

	
	public User queryUserInfo(Map<String, String> paramMap) {
		return (User) getSqlMapClientTemplate().queryForObject(namespace + ".queryUserInfo",paramMap);
	}

	
	public Integer isNicknameExists(Map<String, String> paramMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject(namespace + ".queryUserExistsByNickName",paramMap);
	}

	
	public Integer isMobileExists(Map<String, String> paramMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject(namespace + ".queryUserByMobile",paramMap);
	}

	
	public void register(User user) throws Exception {
		getSqlMapClientTemplate().insert(namespace + ".save", user);
	}

	
	public void resetUserPasword(Map<String, String> paramMap) throws Exception{
		getSqlMapClientTemplate().update(namespace + ".resetUserPassword",paramMap);
	}

	@SuppressWarnings("unchecked")
   
	public List<User> searchUserByNickName(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".searchUserByNickName", paramMap);
	}

	
	public User queryUserInfoByHuiNo(Map<String, String> paramMap) {
		return (User) getSqlMapClientTemplate().queryForObject(namespace + ".queryUserInfoByHuiNo", paramMap);
	}

	
	public User getUserInfoEntity(Map<String, String> paramMap) {
		return (User)getSqlMapClientTemplate().queryForObject(namespace + ".getUserInfoEntity", paramMap);
	}
	
	
	public Integer getCount(Map<String, Object> paramMap)
	{
	    return super.getCount(paramMap);
	}
	
    @SuppressWarnings("unchecked")
   
    public List<User> getUserInfoByQId(Integer qId)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".getUserInfoByQId", qId);
    }
    
   
    public Integer resetUserMobile(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().update(namespace + ".resetUserMobile",paramMap);
    }
    
   
    public Integer resetUserNickName(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().update(namespace + ".resetUserNickName",paramMap);
    }
    
    @SuppressWarnings("unchecked")
   
    public List<User> getMyFans(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".getMyFans",paramMap);
    }
    
   
    public Integer getMyFansCount(Map<String, Object> paramMap)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getMyFansCount",paramMap);
    }
    
    @SuppressWarnings("unchecked")
   
    public List<User> getMyFriends(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".getMyFriends",paramMap);
    }
    
   
    public Integer getMyFriendsCount(Map<String, Object> paramMap)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getMyFriendsCount",paramMap);
    }
    
   
    public Integer minusBean(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().update(namespace + ".minusBean", paramMap);
    }
    
   
    public Integer plusBean(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().update(namespace + ".plusBean", paramMap);
    }

	
	public void modifyPassword(Map<String, String> paramMap) throws Exception {
		getSqlMapClientTemplate().update(namespace + ".modifyPassword", paramMap);
	}

	
	public void setUserAcceptNo(Map<String, String> paramMap) throws Exception {
		getSqlMapClientTemplate().update(namespace + ".setUserAcceptNo", paramMap);
	}

	
	public void setUserAnswerNo(Map<String, String> paramMap) throws Exception {
		getSqlMapClientTemplate().update(namespace + ".setUserAnswerNo", paramMap);
	}

	
	public List<User> searchAllUserByNickName(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".searchAllUserByNickName", paramMap);
	}
}