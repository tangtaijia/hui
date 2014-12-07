package com.hui.common.entity;

import java.util.Map;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ShowMsg
{
    
    private boolean result;
    
    private String message;
    
    private String detail;
    
    private Map<String, String> links;
    
    public boolean isResult()
    {
        return result;
    }
    
    public void setResult(boolean result)
    {
        this.result = result;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public String getDetail()
    {
        return detail;
    }
    
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    
    public Map<String, String> getLinks()
    {
        return links;
    }
    
    public void setLinks(Map<String, String> links)
    {
        this.links = links;
    }
    
}