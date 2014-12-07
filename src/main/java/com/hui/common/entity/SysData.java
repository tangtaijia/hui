package com.hui.common.entity;

import java.util.Map;

/**
 * 
 * <系统数据>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysData extends BaseEntity
{
    
    private static final long serialVersionUID = 6391096269413606310L;
    /**
     * 主键
     */
    private Integer dataId;
    /**
     * 数据码
     */
    private String dataCode;
    /**
     * 数据值
     */
    private String dataValue;
    
    private Map<String, Object> value;
    /**
     * 明细
     */
    private String detail;
    
    public Integer getDataId()
    {
        return dataId;
    }
    
    public void setDataId(Integer dataId)
    {
        this.dataId = dataId;
    }
    
    public String getDataCode()
    {
        return dataCode;
    }
    
    public void setDataCode(String dataCode)
    {
        this.dataCode = dataCode;
    }
    
    public String getDataValue()
    {
        return dataValue;
    }
    
    public void setDataValue(String dataValue)
    {
        this.dataValue = dataValue;
    }
    
    public Map<String, Object> getValue()
    {
        return value;
    }
    
    public void setValue(Map<String, Object> value)
    {
        this.value = value;
    }
    
    public String getDetail()
    {
        return detail;
    }
    
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    
}