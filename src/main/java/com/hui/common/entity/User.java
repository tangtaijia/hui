package com.hui.common.entity;

/**
 * 
 * <用户>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class User extends BaseEntity
{
    
    private static final long serialVersionUID = 3084374401153985882L;
    
    private Integer userId;
    
    private String huiNo;
    
    private String userPwd;
    
    private String newPwd;
    
    private String confirmPwd;
    
    private String nickName;
    
    private String mobile;
    
    private Integer beanNum;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 性别——前台展示
     */
    private String sexStr;
    /**
     * 生日
     */
    private Integer birthDate;
    /**
     * 生日——前台显示
     */
    private String birthDateStr;
    /**
     * 生日——前台展示
     */
    private BirthDate birthDay;
    /**
     * 星座
     */
    private Integer constellation;
    /**
     * 星座——前台显示
     */
    private String constellationStr;
    /**
     * 家乡
     */
    private String homeStr;
    /**
     * 家乡——前台显示
     */
    private Region home;
    /**
     * 居住地
     */
    private String residenceStr;
    /**
     * 居住地——前台显示
     */
    private Region residence;
    /**
     * 年级id
     */
    private Integer gradeId;
    /**
     * 年级——前台显示
     */
    private Grade grade;
    /**
     * 学校
     */
    private String school;
    /**
     * 学校地区
     */
    private String schoolRegionStr;
    /**
     * 学校
     */
    private Region schoolRegion;
    
    /**
     * 回答数
     */
    private Integer answerNo;
    /**
     * 采纳数
     */
    private Integer acceptNo;
    
    /**
     * 注册时间
     */
    private Integer createTime;
    
    /**
     * 状态：0-正常；1-锁定；2-删除；
     */
    private Integer status = 0;
    
    private String statusStr;
    
    private String valiCode;
    /**
     * 与我是否互相关注（0否1是）
     */
    private Integer fansToo=0;
    /**
     * 好友数
     */
    private Integer friendsNum;
    /**
     * 粉丝数
     */
    private Integer fansNum;
    /**
     * 未读数量
     */
    private UnReadNum unReadNum;
    
    private ImageTwo imageTwo;
    /**
     * 是否是老师的标识
     */
    private Boolean teacherFlag=false;
    /**
     * 记住账号
     */
    private Integer remember;
    /**
     * 是否是老师 0 否 1 是
     */
    private Integer isTeacher;
    
    private TeacherInfo teacher;
    
    public User(){}
    
    public User(String huiNo) {
        this.huiNo = huiNo;
    }
    
    public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    public String getUserPwd()
    {
        return userPwd;
    }
    
    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }
    
    public String getNewPwd()
    {
        return newPwd;
    }
    
    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }
    
    public String getConfirmPwd()
    {
        return confirmPwd;
    }
    
    public void setConfirmPwd(String confirmPwd)
    {
        this.confirmPwd = confirmPwd;
    }
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getStatusStr()
    {
        return statusStr;
    }
    
    public void setStatusStr(String statusStr)
    {
        this.statusStr = statusStr;
    }
    
    public String getValiCode()
    {
        return valiCode;
    }
    
    public void setValiCode(String valiCode)
    {
        this.valiCode = valiCode;
    }

    public String getHuiNo()
    {
        return huiNo;
    }

    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }

	public Integer getBeanNum() {
		return beanNum;
	}

	public void setBeanNum(Integer beanNum) {
		this.beanNum = beanNum;
	}

    public Integer getAnswerNo()
    {
        return answerNo;
    }

    public void setAnswerNo(Integer answerNo)
    {
        this.answerNo = answerNo;
    }

    public Integer getAcceptNo()
    {
        return acceptNo;
    }

    public void setAcceptNo(Integer acceptNo)
    {
        this.acceptNo = acceptNo;
    }

    public Integer getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Integer createTime)
    {
        this.createTime = createTime;
    }

    public Integer getFansToo()
    {
        return fansToo;
    }

    public void setFansToo(Integer fansToo)
    {
        this.fansToo = fansToo;
    }

    public Integer getFriendsNum()
    {
        return friendsNum;
    }

    public void setFriendsNum(Integer friendsNum)
    {
        this.friendsNum = friendsNum;
    }

    public Integer getFansNum()
    {
        return fansNum;
    }

    public void setFansNum(Integer fansNum)
    {
        this.fansNum = fansNum;
    }

    public Integer getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Integer birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getBirthDateStr()
    {
        return birthDateStr;
    }

    public void setBirthDateStr(String birthDateStr)
    {
        this.birthDateStr = birthDateStr;
    }

    public Integer getConstellation()
    {
        return constellation;
    }

    public void setConstellation(Integer constellation)
    {
        this.constellation = constellation;
    }

    public String getConstellationStr()
    {
        return constellationStr;
    }

    public void setConstellationStr(String constellationStr)
    {
        this.constellationStr = constellationStr;
    }

    public String getHomeStr()
    {
        return homeStr;
    }

    public void setHomeStr(String homeStr)
    {
        this.homeStr = homeStr;
    }

    public Region getHome()
    {
        return home;
    }

    public void setHome(Region home)
    {
        this.home = home;
    }

    public String getResidenceStr()
    {
        return residenceStr;
    }

    public void setResidenceStr(String residenceStr)
    {
        this.residenceStr = residenceStr;
    }

    public Region getResidence()
    {
        return residence;
    }

    public void setResidence(Region residence)
    {
        this.residence = residence;
    }

    public Integer getGradeId()
    {
        return gradeId;
    }

    public void setGradeId(Integer gradeId)
    {
        this.gradeId = gradeId;
    }

    public Grade getGrade()
    {
        return grade;
    }

    public void setGrade(Grade grade)
    {
        this.grade = grade;
    }

    public String getSchool()
    {
        return school;
    }

    public void setSchool(String school)
    {
        this.school = school;
    }

    public Integer getSex()
    {
        return sex;
    }

    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public String getSexStr()
    {
        return sexStr;
    }

    public void setSexStr(String sexStr)
    {
        this.sexStr = sexStr;
    }

    public BirthDate getBirthDay()
    {
        return birthDay;
    }

    public void setBirthDay(BirthDate birthDay)
    {
        this.birthDay = birthDay;
    }

    public String getSchoolRegionStr()
    {
        return schoolRegionStr;
    }

    public void setSchoolRegionStr(String schoolRegionStr)
    {
        this.schoolRegionStr = schoolRegionStr;
    }

    public Region getSchoolRegion()
    {
        return schoolRegion;
    }

    public void setSchoolRegion(Region schoolRegion)
    {
        this.schoolRegion = schoolRegion;
    }

    public UnReadNum getUnReadNum()
    {
        return unReadNum;
    }

    public void setUnReadNum(UnReadNum unReadNum)
    {
        this.unReadNum = unReadNum;
    }

    public ImageTwo getImageTwo()
    {
        return imageTwo;
    }

    public void setImageTwo(ImageTwo imageTwo)
    {
        this.imageTwo = imageTwo;
    }

    public Boolean getTeacherFlag()
    {
        return teacherFlag;
    }

    public void setTeacherFlag(Boolean teacherFlag)
    {
        this.teacherFlag = teacherFlag;
    }

	public Integer getRemember() {
		return remember;
	}

	public void setRemember(Integer remember) {
		this.remember = remember;
	}

	public Integer getIsTeacher() {
		return isTeacher;
	}

	public void setIsTeacher(Integer isTeacher) {
		this.isTeacher = isTeacher;
	}

	public TeacherInfo getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherInfo teacher) {
		this.teacher = teacher;
	}
    
}