package com.hui.common.entity;

/**
 * 
 * <系统配置>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysConfig extends BaseEntity
{
    
    private static final long serialVersionUID = 5917790123349892029L;
    
    private Integer configId;
    
    private String configCode;
    
    private String configValue;
    /**
     * 值类型：string-字符串；date-日期；datetime-时间；array-数组；int-整数；
     */
    private String valueType;
    
    private String notes;
    
    private String detail;
    
    private String range;
    
    public Integer getConfigId()
    {
        return configId;
    }
    
    public void setConfigId(Integer configId)
    {
        this.configId = configId;
    }
    
    public String getConfigCode()
    {
        return configCode;
    }
    
    public void setConfigCode(String configCode)
    {
        this.configCode = configCode;
    }
    
    public String getConfigValue()
    {
        return configValue;
    }
    
    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
    }
    
    public String getValueType()
    {
        return valueType;
    }
    
    public void setValueType(String valueType)
    {
        this.valueType = valueType;
    }
    
    public String getNotes()
    {
        return notes;
    }
    
    public void setNotes(String notes)
    {
        this.notes = notes;
    }
    
    public String getDetail()
    {
        return detail;
    }
    
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    
    public String getRange()
    {
        return range;
    }
    
    public void setRange(String range)
    {
        this.range = range;
    }
    
}