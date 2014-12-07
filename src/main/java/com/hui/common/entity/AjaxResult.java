package com.hui.common.entity;

import java.util.List;

/**
 * 
 * <ajax 结果>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxResult
{
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
    /**
     * 总数
     */
    private int total;
    
    private List rows;
    
    private List array;
    
    public AjaxResult(boolean success)
    {
        this.success = success;
    }
    
    public AjaxResult(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }
    
    public AjaxResult(boolean success, Object data)
    {
        this.success = success;
        this.data = data;
    }
    
    public AjaxResult(boolean success, String message, Object data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    public AjaxResult(boolean success, List array, Object data)
    {
        this.success = success;
        this.array = array;
        this.data = data;
    }
    
    public AjaxResult(int total, List rows)
    {
        this.total = total;
        this.rows = rows;
    }
    
    public boolean isSuccess()
    {
        return success;
    }
    
    public void setSuccess(boolean success)
    {
        this.success = success;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
    
    public int getTotal()
    {
        return total;
    }
    
    public void setTotal(int total)
    {
        this.total = total;
    }
    
    public List getRows()
    {
        return rows;
    }
    
    public void setRows(List rows)
    {
        this.rows = rows;
    }
    
    public List getArray()
    {
        return array;
    }
    
    public void setArray(List array)
    {
        this.array = array;
    }
    
}