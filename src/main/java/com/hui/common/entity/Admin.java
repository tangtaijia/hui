package com.hui.common.entity;

/**
 * 
 * <管理员>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Admin extends BaseEntity
{
    
    private static final long serialVersionUID = 8265741669795857664L;
    
    /**
     * 主键
     */
    private Integer adminId;
    
    /**
     * 管理员名
     */
    private String adminName;
    
    /**
     * 密码
     */
    private String adminPwd;
    
    /**
     * 昵称
     */
    private String nickName;
    
    /**
     * 手机
     */
    private String mobile;
    
    /**
     * 能否删除：0-能；1-不能；
     */
    private Integer delable = 0;
    
    /**
     * 状态：0-正常；1-锁定；2-删除；
     */
    private Integer status = 0;
    
    private String statusStr;
    
    private String valiCode;
    
    private String profile;
    
    private Integer autoUnlock = 0;
    
    public Integer getAdminId()
    {
        return adminId;
    }
    
    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
    }
    
    public String getAdminName()
    {
        return adminName;
    }
    
    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }
    
    public String getAdminPwd()
    {
        return adminPwd;
    }
    
    public void setAdminPwd(String adminPwd)
    {
        this.adminPwd = adminPwd;
    }
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public Integer getDelable()
    {
        return delable;
    }
    
    public void setDelable(Integer delable)
    {
        this.delable = delable;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getStatusStr()
    {
        return statusStr;
    }
    
    public void setStatusStr(String statusStr)
    {
        this.statusStr = statusStr;
    }
    
    public String getValiCode()
    {
        return valiCode;
    }
    
    public void setValiCode(String valiCode)
    {
        this.valiCode = valiCode;
    }
    
    public String getProfile()
    {
        return profile;
    }
    
    public void setProfile(String profile)
    {
        this.profile = profile;
    }
    
    public Integer getAutoUnlock()
    {
        return autoUnlock;
    }
    
    public void setAutoUnlock(Integer autoUnlock)
    {
        this.autoUnlock = autoUnlock;
    }
    
}