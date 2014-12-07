package com.hui.common.entity;

/**
 * 现金流水
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CashFlow extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2442973938507424350L;
    
    /**
     * 主键
     */
    private Integer cfId;
    /**
     * 汇答号
     */
    private String huiNo;
    /**
     * 用户
     */
    private User user;
    /**
     * 现金流水编号
     */
    private String cfSn;
    /**
     * 流水时间
     */
    private Integer flowTime;
    /**
     * 流水时间--前台展示
     */
    private String flowTimeStr;
    
    private Integer flowTimeFrom;
    private Integer flowTimeTo;
    
    /**
     * 金额
     */
    private Integer flowAmount;
    /**
     * 发生来源 1-支付宝
     */
    private Integer flowSource;
    /**
     * 发生来源--前台展示
     */
    private String flowSourceStr;
    /**
     * 0-流出，1-流入
     */
    private Integer inOut;
    /**
     * 流入、流出--前台展示
     */
    private String inOutStr;
    /**
     * 状态：0-废弃 1-正常 2-废弃
     */
    private Integer status;
    
    
    public Integer getCfId()
    {
        return cfId;
    }
    public void setCfId(Integer cfId)
    {
        this.cfId = cfId;
    }
    public String getHuiNo()
    {
        return huiNo;
    }
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    public String getCfSn()
    {
        return cfSn;
    }
    public void setCfSn(String cfSn)
    {
        this.cfSn = cfSn;
    }
    public Integer getFlowTime()
    {
        return flowTime;
    }
    public void setFlowTime(Integer flowTime)
    {
        this.flowTime = flowTime;
    }
    public Integer getFlowAmount()
    {
        return flowAmount;
    }
    public void setFlowAmount(Integer flowAmount)
    {
        this.flowAmount = flowAmount;
    }
    public Integer getFlowSource()
    {
        return flowSource;
    }
    public void setFlowSource(Integer flowSource)
    {
        this.flowSource = flowSource;
    }
    public Integer getInOut()
    {
        return inOut;
    }
    public void setInOut(Integer inOut)
    {
        this.inOut = inOut;
    }
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    public String getFlowTimeStr()
    {
        return flowTimeStr;
    }
    public void setFlowTimeStr(String flowTimeStr)
    {
        this.flowTimeStr = flowTimeStr;
    }
    public String getFlowSourceStr()
    {
        return flowSourceStr;
    }
    public void setFlowSourceStr(String flowSourceStr)
    {
        this.flowSourceStr = flowSourceStr;
    }
    public String getInOutStr()
    {
        return inOutStr;
    }
    public void setInOutStr(String inOutStr)
    {
        this.inOutStr = inOutStr;
    }
    public void setFlowTimeFrom(Integer flowTimeFrom)
    {
        this.flowTimeFrom = flowTimeFrom;
    }
    public void setFlowTimeTo(Integer flowTimeTo)
    {
        this.flowTimeTo = flowTimeTo;
    }
    public Integer getFlowTimeFrom()
    {
        return flowTimeFrom;
    }
    public Integer getFlowTimeTo()
    {
        return flowTimeTo;
    }
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    
}
