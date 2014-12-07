package com.hui.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;

/**
 * 监控quartz 是否关闭
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-7-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QuartzContextListener implements ServletContextListener {
	/* 
     * 关闭startQuertz
     *  
     * @seejavax.servlet.ServletContextListener#contextDestroyed(javax.servlet. 
     * ServletContextEvent) 
     */  
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		WebApplicationContext webApplicationContext = (WebApplicationContext) arg0
				.getServletContext()
				.getAttribute(
						WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		org.quartz.impl.StdScheduler startQuertz = (org.quartz.impl.StdScheduler) webApplicationContext
				.getBean("startQuertz");
		if (startQuertz != null) {
			startQuertz.shutdown();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}  
  
    /* 
     * (non-Javadoc) 
     *  
     * @see 
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet 
     * .ServletContextEvent) 
     */  
    @Override  
	public void contextInitialized(ServletContextEvent arg0) {
		// 不做任何事情
	} 
}
