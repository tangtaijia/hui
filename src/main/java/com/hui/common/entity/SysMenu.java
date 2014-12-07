package com.hui.common.entity;

/**
 * 
 * <系统菜单>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysMenu extends BaseEntity
{
    
    private static final long serialVersionUID = -4264494968783651476L;
    
    private Integer menuId;
    
    private String menuCode;
    
    private String menuName;
    /**
     * 类型：0-菜单；1-按钮；
     */
    private Integer menuType = 0;
    
    private String menuUrl;
    
    private Integer parentId = 0;
    
    private String detail;
    /**
     * 角色类型：0-管理员；1-会员；
     */
    private Integer roleType;
    /**
     * 角色类型：0-管理员；1-会员；
     */
    private Integer sortOrder = 999;
    
    private Integer _parentId = 0;
    
    public Integer getMenuId()
    {
        return menuId;
    }
    
    public void setMenuId(Integer menuId)
    {
        this.menuId = menuId;
    }
    
    public String getMenuCode()
    {
        return menuCode;
    }
    
    public void setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
    }
    
    public String getMenuName()
    {
        return menuName;
    }
    
    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }
    
    public Integer getMenuType()
    {
        return menuType;
    }
    
    public void setMenuType(Integer menuType)
    {
        this.menuType = menuType;
    }
    
    public String getMenuUrl()
    {
        return menuUrl;
    }
    
    public void setMenuUrl(String menuUrl)
    {
        this.menuUrl = menuUrl;
    }
    
    public Integer getParentId()
    {
        return parentId;
    }
    
    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }
    
    public String getDetail()
    {
        return detail;
    }
    
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    
    public Integer getRoleType()
    {
        return roleType;
    }
    
    public void setRoleType(Integer roleType)
    {
        this.roleType = roleType;
    }
    
    public Integer getSortOrder()
    {
        return sortOrder;
    }
    
    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }
    
    public Integer get_parentId()
    {
        return _parentId;
    }
    
    public void set_parentId(Integer parentId)
    {
        _parentId = parentId;
    }
    
}