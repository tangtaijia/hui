package com.hui.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebListener;

import com.hui.common.utils.FrontUtils;

/**
 * 全局设置Session-Cookie相交互部分属性
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-7-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@WebListener
public class SessionCookieInitialization implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {

		ServletContext servletContext = sce.getServletContext();

		SessionCookieConfig sessionCookie = servletContext
				.getSessionCookieConfig();
		sessionCookie.setName(FrontUtils.SESSION_NAME);
		sessionCookie.setPath(servletContext.getContextPath());
		sessionCookie.setHttpOnly(true);
		sessionCookie.setSecure(false);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
