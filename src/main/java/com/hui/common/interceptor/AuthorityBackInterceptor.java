package com.hui.common.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.hui.common.entity.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AuthorityBackInterceptor extends AbstractInterceptor
{
    
    private static final long serialVersionUID = -2233276907178879085L;
    
    // 拦截Action处理的拦截方法
    @SuppressWarnings("unchecked")
    public String intercept(ActionInvocation invocation)
        throws Exception
    {
        // 取得请求相关的ActionContext实例
        ActionContext ctx = invocation.getInvocationContext();
        
        if (checkReferer())
        {
            return "backlogin";
        }
        
        Map session = ctx.getSession();
        
        Admin loginAdmin = (Admin)session.get("loginAdmin");
        if (null != loginAdmin)
        {
            Map<String, String> loginAuths = (Map<String, String>)session.get("loginAuths");
            Map<String, Map<String, String>> loginAdminOpts =
                (Map<String, Map<String, String>>)session.get("loginAdminOpts");
            boolean hasAuth = false;
            String key = null;
            
            if (null != loginAuths && null != loginAdminOpts)
            {
                String actionName = invocation.getProxy().getActionName();
                
                if (loginAdminOpts.containsKey(actionName))
                {
                    Map<String, String> auths = loginAdminOpts.get(actionName);
                    Iterator<String> keys = auths.keySet().iterator();
                    while (keys.hasNext())
                    {
                        key = keys.next();
                        if (loginAuths.containsKey(key))
                        {
                            hasAuth = true;
                            break;
                        }
                    }
                    
                    if (!hasAuth)
                    {
                        ctx.put("tip", "您没有操作此功能的权限");
                        return "backlogin";
                    }
                }
            }
            
            return invocation.invoke();
        }
        // 没有登陆，将服务器提示设置成一个HttpServletRequest属性
        ctx.put("tip", "您还没有登录，请登录系统");
        return "backlogin";
    }
    
    /**
     * 检查是否跨站点请求伪造
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    private boolean checkReferer()
    {
        //HTTP 头设置 Referer过滤  
        HttpServletRequest request = ServletActionContext.getRequest();
        String referer = request.getHeader("Referer"); // REFRESH
        String path = request.getContextPath();
        String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        if (StringUtils.isNotBlank(referer) && referer.indexOf(basePath) < 0)
        {
            return true;
        }
        return false;
    }
    
}