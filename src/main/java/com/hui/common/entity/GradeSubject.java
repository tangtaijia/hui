package com.hui.common.entity;

/**
 * 年级 学科 中间表
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class GradeSubject extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 6430086007833940612L;
    /**
     * 主键
     */
    private Integer gsId;
    /**
     * 年级id
     */
    private Integer gradeId;
    /**
     * 学科id
     */
    private Integer subjectId;
    
    public Integer getGsId()
    {
        return gsId;
    }
    public void setGsId(Integer gsId)
    {
        this.gsId = gsId;
    }
    public Integer getGradeId()
    {
        return gradeId;
    }
    public void setGradeId(Integer gradeId)
    {
        this.gradeId = gradeId;
    }
    public Integer getSubjectId()
    {
        return subjectId;
    }
    public void setSubjectId(Integer subjectId)
    {
        this.subjectId = subjectId;
    }
    
}
