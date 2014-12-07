package com.hui.common.entity;

/**
 * 收藏
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-6-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Collection extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1840294458757937692L;
    
    private Integer id;
    
    private String huiNo;
    
    private Integer dataId;
    
    private Integer type;
    
    private Integer createTime;
    
    private Question question;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getDataId()
    {
        return dataId;
    }

    public void setDataId(Integer dataId)
    {
        this.dataId = dataId;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Integer createTime)
    {
        this.createTime = createTime;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
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
