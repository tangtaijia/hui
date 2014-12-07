package com.hui.common.entity;

/**
 * 汇豆充值套餐表
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RechargePackage extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5650678562855205792L;
    
    /**
     * 主键
     */
    private Integer rcpId;
    
    /**
     * 金额
     */
    private Integer rcpAmount;
    /**
     * 汇豆数
     */
    private Integer rcpNum;
    /**
     * 0-废弃 1-正常
     */
    private Integer status;
    public Integer getRcpId()
    {
        return rcpId;
    }
    public void setRcpId(Integer rcpId)
    {
        this.rcpId = rcpId;
    }
    public Integer getRcpAmount()
    {
        return rcpAmount;
    }
    public void setRcpAmount(Integer rcpAmount)
    {
        this.rcpAmount = rcpAmount;
    }
    public Integer getRcpNum()
    {
        return rcpNum;
    }
    public void setRcpNum(Integer rcpNum)
    {
        this.rcpNum = rcpNum;
    }
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
}
