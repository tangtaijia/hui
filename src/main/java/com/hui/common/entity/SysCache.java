package com.hui.common.entity;

import com.hui.common.utils.CommonUtils;

/**
 * 
 * <系统缓存>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysCache extends BaseEntity
{
    
    private static final long serialVersionUID = 780291178965032944L;
    /**
     * 主键
     */
    private Integer cacheId;
    
    private String cacheCode;
    /**
     * 缓存更新周期：单位为秒，值为0则取默认值更新缓存
     */
    private Integer refreshPeriod = CommonUtils.DEFAULT_REFRESHPERIOD;
    
    private String range = "0~86400";
    
    private String notes;
    
    public Integer getCacheId()
    {
        return cacheId;
    }
    
    public void setCacheId(Integer cacheId)
    {
        this.cacheId = cacheId;
    }
    
    public String getCacheCode()
    {
        return cacheCode;
    }
    
    public void setCacheCode(String cacheCode)
    {
        this.cacheCode = cacheCode;
    }
    
    public Integer getRefreshPeriod()
    {
        return refreshPeriod;
    }
    
    public void setRefreshPeriod(Integer refreshPeriod)
    {
        this.refreshPeriod = refreshPeriod;
    }
    
    public String getRange()
    {
        return range;
    }
    
    public void setRange(String range)
    {
        this.range = range;
    }
    
    public String getNotes()
    {
        return notes;
    }
    
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    
}