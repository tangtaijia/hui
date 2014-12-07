package com.hui.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
public class WebContextListener implements ServletContextListener
{
    
   
    public void contextDestroyed(ServletContextEvent sce)
    {
        
    }
    
   
    public void contextInitialized(ServletContextEvent sce)
    {
        WebContext.getInstance().setContext(sce.getServletContext());
    }
    
}