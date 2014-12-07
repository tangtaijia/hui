package com.hui.common.entity;

/**
 * 软件问题
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Bug extends BaseEntity
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2606392097369564765L;
    
    private Integer bugId;
    
    /**
     * 汇答号
     */
    private String huiNo;
    
    /**
     * 软件问题描述
     */
    private String bugDesc;
    
    /**
     * 用户名（选填）
     */
    private String userName;
    
    /**
     * QQ（选填）
     */
    private Integer qq;
    
    /**
     * 联系方式（选填）
     */
    private String phone;
    
    /**
     * 客户端类型
     */
    private String clientStyle;
    
    /**
     * 创建时间
     */
    private Integer createTime;
    
    /**
     * 创建时间--前台展示
     */
    private String createTimeStr;
    
    private Integer createTimeFrom;
    
    private Integer createTimeTo;
    
    /**
     * 状态：0-废弃 1-正常
     */
    private Integer status;
    
    public Integer getBugId()
    {
        return bugId;
    }
    
    public void setBugId(Integer bugId)
    {
        this.bugId = bugId;
    }
    
    public String getHuiNo()
    {
        return huiNo;
    }
    
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    
    public String getBugDesc()
    {
        return bugDesc;
    }
    
    public void setBugDesc(String bugDesc)
    {
        this.bugDesc = bugDesc;
    }
    
    public String getClientStyle()
    {
        return clientStyle;
    }
    
    public void setClientStyle(String clientStyle)
    {
        this.clientStyle = clientStyle;
    }
    
    public Integer getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Integer createTime)
    {
        this.createTime = createTime;
    }
    
    public String getCreateTimeStr()
    {
        return createTimeStr;
    }
    
    public void setCreateTimeStr(String createTimeStr)
    {
        this.createTimeStr = createTimeStr;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public Integer getQq()
    {
        return qq;
    }
    
    public void setQq(Integer qq)
    {
        this.qq = qq;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public Integer getCreateTimeFrom()
    {
        return createTimeFrom;
    }
    
    public void setCreateTimeFrom(Integer createTimeFrom)
    {
        this.createTimeFrom = createTimeFrom;
    }
    
    public Integer getCreateTimeTo()
    {
        return createTimeTo;
    }
    
    public void setCreateTimeTo(Integer createTimeTo)
    {
        this.createTimeTo = createTimeTo;
    }
    
}