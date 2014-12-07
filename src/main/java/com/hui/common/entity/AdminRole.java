package com.hui.common.entity;

/**
 * 
 * <管理员 权限 中间表>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AdminRole extends BaseEntity
{
    
    private static final long serialVersionUID = -3418246885172049253L;
    /**
     * 主键
     */
    private Integer arId;
    /**
     * 管理员id
     */
    private Integer adminId;
    /**
     * 权限id
     */
    private Integer roleId;
    /**
     * 管理员名
     */
    private String adminName;
    /**
     * 权限名
     */
    private String roleName;
    
    public Integer getArId()
    {
        return arId;
    }
    
    public void setArId(Integer arId)
    {
        this.arId = arId;
    }
    
    public Integer getAdminId()
    {
        return adminId;
    }
    
    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
    }
    
    public Integer getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
    
    public String getAdminName()
    {
        return adminName;
    }
    
    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }
    
    public String getRoleName()
    {
        return roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
}