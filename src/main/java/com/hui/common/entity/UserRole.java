package com.hui.common.entity;

/**
 * 
 * <用户 角色 中间表>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UserRole extends BaseEntity
{
    
    private static final long serialVersionUID = -4048706794484982737L;
    /**
     * 主键
     */
    private Integer arId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 用户名
     */
    private String nickName;
    private String huiNo;
    /**
     * 角色名
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
    
    public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    public Integer getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
    
    public String getRoleName()
    {
        return roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHuiNo() {
		return huiNo;
	}

	public void setHuiNo(String huiNo) {
		this.huiNo = huiNo;
	}
    
}