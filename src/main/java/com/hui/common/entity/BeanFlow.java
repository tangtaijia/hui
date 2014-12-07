package com.hui.common.entity;

/**
 * 汇豆流水
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BeanFlow extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1498868677559695859L;

    /**
     * 主键
     */
    private Integer bfId;
    /**
     * 汇答号
     */
    private String huiNo;
    /**
     * 用户
     */
    private User user;
    /**
     * 汇豆流水编号
     */
    private String bfSn;
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
     * 汇豆数
     */
    private Integer beanNum;
    /**
     * 汇豆余额
     */
    private Integer leftBeanNum;
    /**
     * 流入、流出：0-流出，1-流入
     */
    private Integer inOut;
    /**
     * 流入、流出--前台展示
     */
    private String inOutStr;
    /**
     * 汇豆流水类型0提问题1回答最佳2系统赠送3校讯通登录
     */
    private Integer type;
    /**
     * 汇豆流水类型--前台显示
     */
    private String typeStr;
    /**
     * 状态：0-废弃 1-正常 2-废弃
     */
    private Integer status;
    
    public Integer getBfId()
    {
        return bfId;
    }
    public void setBfId(Integer bfId)
    {
        this.bfId = bfId;
    }
    public String getHuiNo()
    {
        return huiNo;
    }
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    public String getBfSn()
    {
        return bfSn;
    }
    public void setBfSn(String bfSn)
    {
        this.bfSn = bfSn;
    }
    public Integer getFlowTime()
    {
        return flowTime;
    }
    public void setFlowTime(Integer flowTime)
    {
        this.flowTime = flowTime;
    }
    public Integer getBeanNum()
    {
        return beanNum;
    }
    public void setBeanNum(Integer beanNum)
    {
        this.beanNum = beanNum;
    }
    public Integer getLeftBeanNum() {
		return leftBeanNum;
	}
	public void setLeftBeanNum(Integer leftBeanNum) {
		this.leftBeanNum = leftBeanNum;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
    
}
