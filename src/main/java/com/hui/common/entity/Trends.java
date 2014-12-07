package com.hui.common.entity;

import java.io.Serializable;

/**
 * 个人动态
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Trends implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2696563513511317628L;
    /**
     * 提问 或是 回答
     */
    private String trendsTitle;
    /**
     * 提问或回答 描述
     */
    private String trendsDesc;
    /**
     * 是否有图
     */
    private Integer hasImg=0;
    /**
     * 原图
     */
    private SysFile imgOri;
    /**
     * 缩略图
     */
    private SysFile imgLt;
    /**
     * 创建时间
     */
    private String createTimeStr;
    /**
     * 年级学科
     */
    private String gradeSubject;
    /**
     * 回答数量
     */
    private Integer answerNum;
    /**
     * 跳转链接
     */
    private String linkUrl;
    
    public String getTrendsDesc()
    {
        return trendsDesc;
    }
    public void setTrendsDesc(String trendsDesc)
    {
        this.trendsDesc = trendsDesc;
    }
    public Integer getHasImg()
    {
        return hasImg;
    }
    public void setHasImg(Integer hasImg)
    {
        this.hasImg = hasImg;
    }
    public SysFile getImgOri()
    {
        return imgOri;
    }
    public void setImgOri(SysFile imgOri)
    {
        this.imgOri = imgOri;
    }
    public SysFile getImgLt()
    {
        return imgLt;
    }
    public void setImgLt(SysFile imgLt)
    {
        this.imgLt = imgLt;
    }
    public String getGradeSubject()
    {
        return gradeSubject;
    }
    public void setGradeSubject(String gradeSubject)
    {
        this.gradeSubject = gradeSubject;
    }
    public String getLinkUrl()
    {
        return linkUrl;
    }
    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }
    public Integer getAnswerNum()
    {
        return answerNum;
    }
    public void setAnswerNum(Integer answerNum)
    {
        this.answerNum = answerNum;
    }
    public String getCreateTimeStr()
    {
        return createTimeStr;
    }
    public void setCreateTimeStr(String createTimeStr)
    {
        this.createTimeStr = createTimeStr;
    }
    public String getTrendsTitle()
    {
        return trendsTitle;
    }
    public void setTrendsTitle(String trendTitle)
    {
        this.trendsTitle = trendTitle;
    }
}
