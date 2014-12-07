package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.IAdminRoleDao;
import com.hui.common.entity.AdminRole;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AdminRoleDaoImpl extends AbstractBaseDao<AdminRole, AdminRole> implements IAdminRoleDao
{
    
   
    public Integer deleteAdminRole(List<Integer> adminIds)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("adminIds", adminIds);
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".deleteAdminRole", param);
    }
    
    @SuppressWarnings("unchecked")
   
    public List<AdminRole> selectAdminRole(AdminRole ar)
    {
        return (List<AdminRole>)getSqlMapClientTemplate().queryForList(namespace + ".selectAdminRole", ar);
    }
    
   
    public Integer deleteRoleAdmin(List<Integer> roleIds)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleIds", roleIds);
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".deleteRoleAdmin", param);
    }
    
}