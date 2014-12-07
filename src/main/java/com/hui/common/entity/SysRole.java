package com.hui.common.entity;

/**
 * 
 * <角色>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysRole extends BaseEntity
{
    
    private static final long serialVersionUID = -4072217482363872753L;
    
    private Integer roleId;
    
    private String roleCode;
    
    private String roleName;
    /**
     * 角色类型：0-管理员；1-会员；
     */
    private Integer roleType = 0;
    
    private String notes;
    
    public Integer getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
    
    public String getRoleCode()
    {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }
    
    public String getRoleName()
    {
        return roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
    public Integer getRoleType()
    {
        return roleType;
    }
    
    public void setRoleType(Integer roleType)
    {
        this.roleType = roleType;
    }
    
    public String getNotes()
    {
        return notes;
    }
    
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    
}