package com.hui.common.entity;

/**
 * 答案
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Answer extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1137255314011960983L;
    /**
     * 主键
     */
    private Integer aId;
    /**
     * 汇答号
     */
    private String huiNo;
    /**
     * 回答者
     */
    private User user;
    /**
     * 问题id
     */
    private Integer questionId;
    /**
     * 问题——前台显示
     */
    private Question question;
    /**
     * 回答描述
     */
    private String answerDesc;
    /**
     * 点赞数
     */
    private Integer praiseNum;
    /**
     * 我是否已赞（用于确定单赞、消赞的状态）
     */
    private Integer hasPraise=0;
    /**
     * 客户端型号
     */
    private String clientStyle;
    /**
     * 创建时间
     */
    private Integer createTime;
    /**
     * 创建时间--前台
     */
    private String createTimeStr;
    /**
     * 有无图片 0-无 1-有
     */
    private Integer hasImg;
    /**
     * 图片
     */
    private ImageTwo imageTwo;
    /**
     * 是否为最佳答案：0-否 1-是
     */
    private Integer isFavorate;
    /**
     * 是否可设置为最佳答案（用于设置最佳答案的状态）
     */
    private Integer canFitFavor=0;
    /**
     * 状态 0-废弃 1-正常
     */
    private Integer status;
    public Integer getaId()
    {
        return aId;
    }
    public void setaId(Integer aId)
    {
        this.aId = aId;
    }
    public String getHuiNo()
    {
        return huiNo;
    }
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    public Integer getQuestionId()
    {
        return questionId;
    }
    public void setQuestionId(Integer questionId)
    {
        this.questionId = questionId;
    }
    public String getAnswerDesc()
    {
        return answerDesc;
    }
    public void setAnswerDesc(String answerDesc)
    {
        this.answerDesc = answerDesc;
    }
    public Integer getPraiseNum()
    {
        return praiseNum;
    }
    public void setPraiseNum(Integer praiseNum)
    {
        this.praiseNum = praiseNum;
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
    public Integer getHasImg()
    {
        return hasImg;
    }
    public void setHasImg(Integer hasImg)
    {
        this.hasImg = hasImg;
    }
    public Integer getIsFavorate()
    {
        return isFavorate;
    }
    public void setIsFavorate(Integer isFavorate)
    {
        this.isFavorate = isFavorate;
    }
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    public String getCreateTimeStr()
    {
        return createTimeStr;
    }
    public void setCreateTimeStr(String createTimeStr)
    {
        this.createTimeStr = createTimeStr;
    }
    public ImageTwo getImageTwo()
    {
        return imageTwo;
    }
    public void setImageTwo(ImageTwo imageTwo)
    {
        this.imageTwo = imageTwo;
    }
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public Question getQuestion()
    {
        return question;
    }
    public void setQuestion(Question question)
    {
        this.question = question;
    }
    public Integer getHasPraise()
    {
        return hasPraise;
    }
    public void setHasPraise(Integer hasPraise)
    {
        this.hasPraise = hasPraise;
    }
    public Integer getCanFitFavor()
    {
        return canFitFavor;
    }
    public void setCanFitFavor(Integer canFitFavor)
    {
        this.canFitFavor = canFitFavor;
    }
}
