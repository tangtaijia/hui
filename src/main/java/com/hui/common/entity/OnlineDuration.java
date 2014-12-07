package com.hui.common.entity;

/**
 * 在线时长表
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OnlineDuration extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -106032311478376050L;
    /**
     * 主键
     */
    private Integer odId;
    /**
     * 教师id
     */
    private Integer teacherId;
    /**
     * 上线时间
     */
    private Integer beginTime;
    /**
     * 下线时间
     */
    private Integer endTime;
    
    
    public Integer getOdId()
    {
        return odId;
    }
    public void setOdId(Integer odId)
    {
        this.odId = odId;
    }
    public Integer getTeacherId()
    {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId)
    {
        this.teacherId = teacherId;
    }
    public Integer getBeginTime()
    {
        return beginTime;
    }
    public void setBeginTime(Integer beginTime)
    {
        this.beginTime = beginTime;
    }
    public Integer getEndTime()
    {
        return endTime;
    }
    public void setEndTime(Integer endTime)
    {
        this.endTime = endTime;
    }
    
}
