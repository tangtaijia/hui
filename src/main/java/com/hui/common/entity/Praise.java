package com.hui.common.entity;

/**
 * 点赞
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Praise extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4714704360270203356L;
    
    /**
     * 主键
     */
    private Integer prId;
    /**
     * 回答id
     */ 
    private Integer answerId;
    /**
     * 汇答号
     */
    private String huiNo;
    
    public Integer getPrId()
    {
        return prId;
    }
    public void setPrId(Integer prId)
    {
        this.prId = prId;
    }
    public Integer getAnswerId()
    {
        return answerId;
    }
    public void setAnswerId(Integer answerId)
    {
        this.answerId = answerId;
    }
    public String getHuiNo()
    {
        return huiNo;
    }
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    
}
