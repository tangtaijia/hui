package com.hui.common.dao;

import java.util.List;

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
public interface IUserRoleDao extends IBaseDao<UserRole, UserRole>
{
    
    public Integer deleteUserRole(List<Integer> userIds);
    
    public Integer deleteRoleUser(List<Integer> roleIds);
    
    public List<UserRole> selectUserRole(UserRole ar);
    
}