package com.hui.common.entity;

public class QuestionUser extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -969922568433983212L;
    
    /**
     * 主键
     */
    private Integer quId;
    /**
     * 问题id
     */
    private Integer questionId;
    /**
     * 汇答号
     */
    private String huiNo;
    public Integer getQuId()
    {
        return quId;
    }
    public void setQuId(Integer quId)
    {
        this.quId = quId;
    }
    public Integer getQuestionId()
    {
        return questionId;
    }
    public void setQuestionId(Integer questionId)
    {
        this.questionId = questionId;
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
