package com.hui.common.dao;

import java.util.List;

import com.hui.common.entity.AdminRole;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IAdminRoleDao extends IBaseDao<AdminRole, AdminRole>
{
    
    public Integer deleteAdminRole(List<Integer> adminIds);
    
    public Integer deleteRoleAdmin(List<Integer> roleIds);
    
    public List<AdminRole> selectAdminRole(AdminRole ar);
    
}