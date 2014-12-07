package com.hui.common.entity;

/**
 * 
 * <系统表>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysTab extends BaseEntity
{
    
    private static final long serialVersionUID = 162266919494867520L;
    
    private Integer tabId;
    
    private String tabName;
    /**
     * 状态：0-显示；1-隐藏；
     */
    private Integer status;
    
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
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
}