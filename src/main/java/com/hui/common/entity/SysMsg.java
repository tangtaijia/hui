package com.hui.common.entity;

import java.util.List;

public class SysMsg extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1590845020178483782L;
    
    /**
     * 主键
     */
    private Integer sysMsgId;
    /**
     * 系统消息标题
     */
    private String sysMsgTitle;
    /**
     * 系统消息描述
     */
    private String sysMsgDesc;
    /**
     * 是否有图 0-否 1-是
     */
    private Integer hasImg;
    /**
     * 图片列表
     */
    private List<ImageTwo> imageTwos;
    /**
     * 状态：0-废弃  1-正常 2-冻结
     */
    private Integer status;
    
    private String statusStr;
    /**
     * 创建时间
     */
    private Integer createTime;
    /**
     * 创建时间--前台
     */
    private String createTimeStr;
    
    private Integer createTimeFrom;
    
    private Integer createTimeTo;
    
    public Integer getSysMsgId()
    {
        return sysMsgId;
    }
    public void setSysMsgId(Integer sysMsgId)
    {
        this.sysMsgId = sysMsgId;
    }
    public String getSysMsgTitle()
    {
        return sysMsgTitle;
    }
    public void setSysMsgTitle(String sysMsgTitle)
    {
        this.sysMsgTitle = sysMsgTitle;
    }
    public String getSysMsgDesc()
    {
        return sysMsgDesc;
    }
    public void setSysMsgDesc(String sysMsgDesc)
    {
        this.sysMsgDesc = sysMsgDesc;
    }
    public Integer getHasImg()
    {
        return hasImg;
    }
    public void setHasImg(Integer hasImg)
    {
        this.hasImg = hasImg;
    }
    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    public Integer getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Integer createTime)
    {
        this.createTime = createTime;
    }
    public String getCreateTimeStr()
    {
        return createTimeStr;
    }
    public void setCreateTimeStr(String createTimeStr)
    {
        this.createTimeStr = createTimeStr;
    }
    public List<ImageTwo> getImageTwos()
    {
        return imageTwos;
    }
    public void setImageTwos(List<ImageTwo> imageTwos)
    {
        this.imageTwos = imageTwos;
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
    public String getStatusStr()
    {
        return statusStr;
    }
    public void setStatusStr(String statusStr)
    {
        this.statusStr = statusStr;
    }
    
}
