package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.ISysAuthDao;
import com.hui.common.entity.SysAuth;
import com.hui.common.entity.SysMenu;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysAuthDaoImpl extends AbstractBaseDao<SysAuth, SysAuth> implements ISysAuthDao
{
    
   
    public Integer deleteRoleAuth(List<Integer> roleIds)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleIds", roleIds);
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".deleteRoleAuth", param);
    }
    
    @SuppressWarnings("unchecked")
   
    public List<SysMenu> selectAllRolesAuth(List<Integer> roleIds)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleIds", roleIds);
        return (List<SysMenu>)getSqlMapClientTemplate().queryForList(namespace + ".selectAllRolesAuth", param);
    }
    
}