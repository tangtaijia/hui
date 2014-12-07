package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.IUserRoleDao;
import com.hui.common.entity.UserRole;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UserRoleDaoImpl extends AbstractBaseDao<UserRole, UserRole> implements IUserRoleDao
{
    
   
    public Integer deleteRoleUser(List<Integer> roleIds)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleIds", roleIds);
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".deleteRoleUser", param);
    }
    
   
    public Integer deleteUserRole(List<Integer> userIds)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userIds", userIds);
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".deleteUserRole", param);
    }
    
    @SuppressWarnings("unchecked")
   
    public List<UserRole> selectUserRole(UserRole ar)
    {
        return (List<UserRole>)getSqlMapClientTemplate().queryForList(namespace + ".selectUserRole", ar);
    }
    
}