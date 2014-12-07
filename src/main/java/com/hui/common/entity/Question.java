package com.hui.common.entity;

import java.util.List;

/**
 * 问题
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Question extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8696141470029608402L;
    /**
     * 主键
     */
    private Integer qId;
    /**
     * 汇答号
     */
    private String huiNo;
    /**
     * 提问题者
     */
    private User user;
    /**
     * 问题描述
     */
    private String questionDesc;
    /**
     * 年级id
     */
    private Grade grade=new Grade();
    private String gradeStr;
    /**
     * 科目id
     */
    private Subject subject=new Subject();
    private String subjectStr;
    /**
     * 客户端型号
     */
    private String clientStyle;
    /**
     * 是否悬赏：0-否 1-是
     */
    private Integer isReward;
    /**
     * 悬赏豆数
     */
    private Integer rewardAmount;
    /**
     * 创建时间
     */
    private Integer createTime;
    /**
     * 创建时间--用于前台
     */
    private String createTimeStr;
    
    private Integer createTimeFrom;
    
    private Integer createTimeTo;
    /**
     * 0未分配给老师1分配给老师
     */
    private Integer allocated;
    private String allocatedStr;
    private String teacherName;
    /**
     * 是否提问老师：0-否，1-是
     */
    private Integer toTeacher=0;
    /**
     * 相关学霸
     */
    private List<User> superStudents;
    /**
     * 回答数
     */
    private Integer answerNum;
    /**
     * 有无答案：0-无 1-有
     */
    private Integer hasAnswer;
    /**
     * 是否有图片  0-无 1-有
     */
    private Integer hasImg;
    /**
     * 图片
     */
    private ImageTwo imageTwo;
    /**
     * 是否有最佳答案：0-否 1-是
     */
    private Integer hasFavorate;
    /**
     * 最佳答案
     */
    private Answer favorAnswer;
    /**
     * 状态：0-废弃 1-正常
     */
    private Integer status;
    
    private Integer outTime;
    
    /**
     * 收藏标识
     */
    private Boolean collectFlag=false;
    
    public Integer getqId()
    {
        return qId;
    }
    public void setqId(Integer qId)
    {
        this.qId = qId;
    }
    public String getHuiNo()
    {
        return huiNo;
    }
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    public String getQuestionDesc()
    {
        return questionDesc;
    }
    public void setQuestionDesc(String questionDesc)
    {
        this.questionDesc = questionDesc;
    }
    public Grade getGrade()
    {
        return grade;
    }
    public void setGrade(Grade grade)
    {
        this.grade = grade;
    }
    public Subject getSubject()
    {
        return subject;
    }
    public void setSubject(Subject subject)
    {
        this.subject = subject;
    }
    public String getClientStyle()
    {
        return clientStyle;
    }
    public void setClientStyle(String clientStyle)
    {
        this.clientStyle = clientStyle;
    }
    public Integer getIsReward()
    {
        return isReward;
    }
    public void setIsReward(Integer isReward)
    {
        this.isReward = isReward;
    }
    public Integer getRewardAmount()
    {
        return rewardAmount;
    }
    public void setRewardAmount(Integer rewardAmount)
    {
        this.rewardAmount = rewardAmount;
    }
    public Integer getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Integer createTime)
    {
        this.createTime = createTime;
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
    public Integer getToTeacher()
    {
        return toTeacher;
    }
    public void setToTeacher(Integer toTeacher)
    {
        this.toTeacher = toTeacher;
    }
    public Integer getAnswerNum()
    {
        return answerNum;
    }
    public void setAnswerNum(Integer answerNum)
    {
        this.answerNum = answerNum;
    }
    public Integer getHasAnswer()
    {
        return hasAnswer;
    }
    public void setHasAnswer(Integer hasAnswer)
    {
        this.hasAnswer = hasAnswer;
    }
    public Integer getHasImg()
    {
        return hasImg;
    }
    public void setHasImg(Integer hasImg)
    {
        this.hasImg = hasImg;
    }
    public Integer getHasFavorate()
    {
        return hasFavorate;
    }
    public void setHasFavorate(Integer hasFavorate)
    {
        this.hasFavorate = hasFavorate;
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
    public List<User> getSuperStudents()
    {
        return superStudents;
    }
    public void setSuperStudents(List<User> superStudents)
    {
        this.superStudents = superStudents;
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
    public Answer getFavorAnswer()
    {
        return favorAnswer;
    }
    public void setFavorAnswer(Answer favorAnswer)
    {
        this.favorAnswer = favorAnswer;
    }
    public Integer getAllocated()
    {
        return allocated;
    }
    public void setAllocated(Integer allocated)
    {
        this.allocated = allocated;
    }
    public String getAllocatedStr()
    {
        return allocatedStr;
    }
    public void setAllocatedStr(String allocatedStr)
    {
        this.allocatedStr = allocatedStr;
    }
    public String getGradeStr()
    {
        return gradeStr;
    }
    public void setGradeStr(String gradeStr)
    {
        this.gradeStr = gradeStr;
    }
    public String getSubjectStr()
    {
        return subjectStr;
    }
    public void setSubjectStr(String subjectStr)
    {
        this.subjectStr = subjectStr;
    }
    public String getTeacherName()
    {
        return teacherName;
    }
    public void setTeacherName(String teacherName)
    {
        this.teacherName = teacherName;
    }
    public Integer getOutTime()
    {
        return outTime;
    }
    public void setOutTime(Integer outTime)
    {
        this.outTime = outTime;
    }
    public Boolean getCollectFlag()
    {
        return collectFlag;
    }
    public void setCollectFlag(Boolean collectFlag)
    {
        this.collectFlag = collectFlag;
    }
}
