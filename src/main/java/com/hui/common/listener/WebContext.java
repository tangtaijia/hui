package com.hui.common.listener;

import javax.servlet.ServletContext;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WebContext
{
    
    private final static WebContext instance = new WebContext();
    
    private static ServletContext context;
    
    private WebContext()
    {
        
    }
    
    public static WebContext getInstance()
    {
        return instance;
    }
    
    protected void setContext(ServletContext context)
    {
        WebContext.context = context;
    }
    
    public ServletContext getContext()
    {
        return context;
    }
    
}