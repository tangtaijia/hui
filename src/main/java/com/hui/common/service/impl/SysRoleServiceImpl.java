package com.hui.common.service.impl;

import java.util.List;

import com.hui.common.dao.IAdminRoleDao;
import com.hui.common.dao.ISysAuthDao;
import com.hui.common.dao.ISysRoleDao;
import com.hui.common.dao.IUserRoleDao;
import com.hui.common.entity.AdminRole;
import com.hui.common.entity.SysAuth;
import com.hui.common.entity.SysMenu;
import com.hui.common.entity.SysRole;
import com.hui.common.entity.UserRole;
import com.hui.common.service.ISysRoleService;

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
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRole> implements ISysRoleService
{
    
    private ISysAuthDao sysAuthDao;
    
    private IAdminRoleDao adminRoleDao;
    
    private IUserRoleDao userRoleDao;
    
    public void setSysAuthDao(ISysAuthDao sysAuthDao)
    {
        this.sysAuthDao = sysAuthDao;
    }
    
    public void setAdminRoleDao(IAdminRoleDao adminRoleDao)
    {
        this.adminRoleDao = adminRoleDao;
    }
    
    public void setUserRoleDao(IUserRoleDao userRoleDao)
    {
        this.userRoleDao = userRoleDao;
    }
    
   
    public Integer delete(List<Integer> ids)
    {
        return ((ISysRoleDao)baseDao).delete(ids);
    }
    
   
    public Integer deleteAdminRole(List<Integer> adminIds)
    {
        return adminRoleDao.deleteAdminRole(adminIds);
    }
    
   
    public Integer saveAdminRole(AdminRole ar)
    {
        return adminRoleDao.save(ar);
    }
    
   
    public List<AdminRole> selectAdminRole(AdminRole ar)
    {
        return adminRoleDao.selectAdminRole(ar);
    }
    
   
    public Integer deleteRoleAdmin(List<Integer> roleIds)
    {
        return adminRoleDao.deleteRoleAdmin(roleIds);
    }
    
   
    public List<SysAuth> selectAllAuth(SysAuth ai)
    {
        return sysAuthDao.selectAll(ai);
    }
    
   
    public Integer deleteRoleAuth(List<Integer> roleIds)
    {
        return sysAuthDao.deleteRoleAuth(roleIds);
    }
    
   
    public Integer saveSysAuth(SysAuth ai)
    {
        return sysAuthDao.save(ai);
    }
    
   
    public List<SysMenu> selectAllRolesAuth(List<Integer> roleIds)
    {
        return sysAuthDao.selectAllRolesAuth(roleIds);
    }
    
   
    public Integer deleteRoleUser(List<Integer> roleIds)
    {
        return userRoleDao.deleteRoleUser(roleIds);
    }
    
   
    public Integer deleteUserRole(List<Integer> userIds)
    {
        return userRoleDao.deleteUserRole(userIds);
    }
    
   
    public Integer saveUserRole(UserRole ar)
    {
        return userRoleDao.save(ar);
    }
    
   
    public List<UserRole> selectUserRole(UserRole ar)
    {
        return userRoleDao.selectUserRole(ar);
    }
    
}