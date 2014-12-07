package com.hui.common.entity;

/**
 * 
 * <系统备份>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysBackUp extends BaseEntity
{
    
    private static final long serialVersionUID = 3249933807900640499L;
    /**
     * 主键
     */
    private Integer backupId;
    /**
     * 批次号
     */
    private Integer seqId;
    /**
     * 表id
     */
    private Integer tabId;
    /**
     * 表名
     */
    private String tabName;
    /**
     * 数据备份方式：0-定期备份；1-手动备份；
     */
    private Integer backupType;
    
    private String backupTypeStr;
    /**
     * 数据备份类型：0-增量；1-全量；
     */
    private Integer backupForm;
    
    private String backupFormStr;
    
    private Integer startItems = 0;
    
    private Integer endItems = 0;
    
    private String filePath;
    
    private Integer fileItems = 0;
    /**
     * 状态：0-待备份；1-备份中；2-备份成功；3-备份失败；
     */
    private Integer status;
    
    private String statusStr;
    /**
     * 备份时间
     */
    private Integer backupTime;
    
    private Integer backupTimeFrom;
    
    private Integer backupTimeTo;
    
    private String backupTimeStr;
    
    private String notes;
    
    public Integer getBackupId()
    {
        return backupId;
    }
    
    public void setBackupId(Integer backupId)
    {
        this.backupId = backupId;
    }
    
    public Integer getSeqId()
    {
        return seqId;
    }
    
    public void setSeqId(Integer seqId)
    {
        this.seqId = seqId;
    }
    
    public Integer getTabId()
    {
        return tabId;
    }
    
    public void setTabId(Integer tabId)
    {
        this.tabId = tabId;
    }
    
    public String getTabName()
    {
        return tabName;
    }
    
    public void setTabName(String tabName)
    {
        this.tabName = tabName;
    }
    
    public Integer getBackupType()
    {
        return backupType;
    }
    
    public void setBackupType(Integer backupType)
    {
        this.backupType = backupType;
    }
    
    public String getBackupTypeStr()
    {
        return backupTypeStr;
    }
    
    public void setBackupTypeStr(String backupTypeStr)
    {
        this.backupTypeStr = backupTypeStr;
    }
    
    public Integer getBackupForm()
    {
        return backupForm;
    }
    
    public void setBackupForm(Integer backupForm)
    {
        this.backupForm = backupForm;
    }
    
    public String getBackupFormStr()
    {
        return backupFormStr;
    }
    
    public void setBackupFormStr(String backupFormStr)
    {
        this.backupFormStr = backupFormStr;
    }
    
    public Integer getStartItems()
    {
        return startItems;
    }
    
    public void setStartItems(Integer startItems)
    {
        this.startItems = startItems;
    }
    
    public Integer getEndItems()
    {
        return endItems;
    }
    
    public void setEndItems(Integer endItems)
    {
        this.endItems = endItems;
    }
    
    public String getFilePath()
    {
        return filePath;
    }
    
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    public Integer getFileItems()
    {
        return fileItems;
    }
    
    public void setFileItems(Integer fileItems)
    {
        this.fileItems = fileItems;
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
    
    public Integer getBackupTime()
    {
        return backupTime;
    }
    
    public void setBackupTime(Integer backupTime)
    {
        this.backupTime = backupTime;
    }
    
    public Integer getBackupTimeFrom()
    {
        return backupTimeFrom;
    }
    
    public void setBackupTimeFrom(Integer backupTimeFrom)
    {
        this.backupTimeFrom = backupTimeFrom;
    }
    
    public Integer getBackupTimeTo()
    {
        return backupTimeTo;
    }
    
    public void setBackupTimeTo(Integer backupTimeTo)
    {
        this.backupTimeTo = backupTimeTo;
    }
    
    public String getBackupTimeStr()
    {
        return backupTimeStr;
    }
    
    public void setBackupTimeStr(String backupTimeStr)
    {
        this.backupTimeStr = backupTimeStr;
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