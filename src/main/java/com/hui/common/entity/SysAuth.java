package com.hui.common.entity;

/**
 * 
 * <权限 菜单 中间表>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysAuth extends BaseEntity
{
    
    private static final long serialVersionUID = 8034803055131465661L;
    /**
     * 主键
     */
    private Integer authId;
    /**
     * 权限id
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer menuId;
    
    public Integer getAuthId()
    {
        return authId;
    }
    
    public void setAuthId(Integer authId)
    {
        this.authId = authId;
    }
    
    public Integer getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
    
    public Integer getMenuId()
    {
        return menuId;
    }
    
    public void setMenuId(Integer menuId)
    {
        this.menuId = menuId;
    }
    
}