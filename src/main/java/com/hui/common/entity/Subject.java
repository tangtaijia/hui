package com.hui.common.entity;

/**
 * 科目
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Subject extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8855821446581704007L;
    
    /**
     * 主键
     */
    private Integer subId;
    /**
     * 科目名
     */
    private String subName;
    
    public Subject(){}
    
    public Subject(Integer subId) {
        this.subId = subId;
    }
    
    public Integer getSubId()
    {
        return subId;
    }
    public void setSubId(Integer subId)
    {
        this.subId = subId;
    }
    public String getSubName()
    {
        return subName;
    }
    public void setSubName(String subName)
    {
        this.subName = subName;
    }
    
}
