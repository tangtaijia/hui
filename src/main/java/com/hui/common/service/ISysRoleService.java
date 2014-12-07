package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.AdminRole;
import com.hui.common.entity.SysAuth;
import com.hui.common.entity.SysMenu;
import com.hui.common.entity.SysRole;
import com.hui.common.entity.UserRole;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISysRoleService extends IBaseService<SysRole, SysRole>
{
    
    public List<SysAuth> selectAllAuth(SysAuth ai);
    
    public Integer delete(List<Integer> ids);
    
    public Integer deleteUserRole(List<Integer> userIds);
    
    public Integer deleteRoleUser(List<Integer> roleIds);
    
    public Integer deleteAdminRole(List<Integer> adminIds);
    
    public Integer deleteRoleAdmin(List<Integer> roleIds);
    
    public Integer deleteRoleAuth(List<Integer> roleIds);
    
    public Integer saveUserRole(UserRole ar);
    
    public Integer saveAdminRole(AdminRole ar);
    
    public Integer saveSysAuth(SysAuth ai);
    
    public List<UserRole> selectUserRole(UserRole ar);
    
    public List<AdminRole> selectAdminRole(AdminRole ar);
    
    public List<SysMenu> selectAllRolesAuth(List<Integer> roleIds);
    
}