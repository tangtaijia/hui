package com.hui.common.entity;

import java.math.BigDecimal;

public class TeacherInfo extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -3758349131417368189L;
    
    /**
     * 主键
     */
    private Integer teacherId;
    /**
     * 汇答号
     */
    private String huiNo;
    
    private User user;
    /**
     * 教师名
     */
    private String teacherName;
    /**
     * 教师描述
     */
    private String teacherDesc;
    /**
     * 年级
     */
    private Grade grade;
    /**
     * 年级名--easyui显示
     */
    private String gradeStr;
    /**
     * 科目
     */
    private Subject subject;
    /**
     * 学科名--easyui显示
     */
    private String subjectStr;
    /**
     * 教师职称:0-家教 1-教师 2-高级教师 3-特级教师
     */
    private Integer teacherTitle;
    /**
     * 教师职称——前台显示
     */
    private String teacherTitleStr;
    /**
     * 在线状态：0-离线；1-在线   
     */
    private Integer onlineStatus;
    /**
     * 在线状态--easyui展示
     */
    private String onlineStatusStr;
    /**
     * 在线时长
     */
    private Integer onlineTime;
    /**
     * 在线时长——前台显示
     */
    private String onlineTimeStr;
    /**
     * 采纳率
     */
    private BigDecimal acceptRate;
    
    public Integer getTeacherId()
    {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId)
    {
        this.teacherId = teacherId;
    }
    public String getHuiNo()
    {
        return huiNo;
    }
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    public String getTeacherName()
    {
        return teacherName;
    }
    public void setTeacherName(String teacherName)
    {
        this.teacherName = teacherName;
    }
    public String getTeacherDesc()
    {
        return teacherDesc;
    }
    public void setTeacherDesc(String teacherDesc)
    {
        this.teacherDesc = teacherDesc;
    }
    public Integer getTeacherTitle()
    {
        return teacherTitle;
    }
    public void setTeacherTitle(Integer teacherTitle)
    {
        this.teacherTitle = teacherTitle;
    }
    public Integer getOnlineStatus()
    {
        return onlineStatus;
    }
    public void setOnlineStatus(Integer onlineStatus)
    {
        this.onlineStatus = onlineStatus;
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
    public String getTeacherTitleStr()
    {
        return teacherTitleStr;
    }
    public void setTeacherTitleStr(String teacherTitleStr)
    {
        this.teacherTitleStr = teacherTitleStr;
    }
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public Integer getOnlineTime()
    {
        return onlineTime;
    }
    public void setOnlineTime(Integer onlineTime)
    {
        this.onlineTime = onlineTime;
    }
    public String getOnlineTimeStr()
    {
        return onlineTimeStr;
    }
    public void setOnlineTimeStr(String onlineTimeStr)
    {
        this.onlineTimeStr = onlineTimeStr;
    }
    public BigDecimal getAcceptRate()
    {
        return acceptRate;
    }
    public void setAcceptRate(BigDecimal acceptRate)
    {
        this.acceptRate = acceptRate;
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
    public String getOnlineStatusStr()
    {
        return onlineStatusStr;
    }
    public void setOnlineStatusStr(String onlineStatusStr)
    {
        this.onlineStatusStr = onlineStatusStr;
    }
    
}
