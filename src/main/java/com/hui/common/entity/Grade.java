package com.hui.common.entity;

import java.util.List;

/**
 * 年级
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Grade extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -260605724164044210L;
    
    /**
     * 主键
     */
    private Integer gradeId;
    /**
     * 年级名
     */
    private String gradeName;
    
    /**
     * 学科
     */
    private List<Subject> subjects;
    
    public Grade(){}
    
    public Grade(Integer gradeId) {
        this.gradeId = gradeId;
    }
    
    public Integer getGradeId()
    {
        return gradeId;
    }
    public void setGradeId(Integer gradeId)
    {
        this.gradeId = gradeId;
    }
    public String getGradeName()
    {
        return gradeName;
    }
    public void setGradeName(String gradeName)
    {
        this.gradeName = gradeName;
    }

    public List<Subject> getSubjects()
    {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects)
    {
        this.subjects = subjects;
    }
}
