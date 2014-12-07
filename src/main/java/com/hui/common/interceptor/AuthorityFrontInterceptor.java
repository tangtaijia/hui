package com.hui.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.entity.User;
import com.hui.common.service.IMessageService;
import com.hui.common.utils.CommonUtils;
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
public class AuthorityFrontInterceptor extends AbstractInterceptor
{
    
    private static final long serialVersionUID = -2233276907178879085L;
    
    @Autowired
    private IMessageService messageService;
    
    // 拦截Action处理的拦截方法
    @SuppressWarnings({"unchecked", "rawtypes"})
    public String intercept(ActionInvocation invocation)
        throws Exception
    {
        // 取得请求相关的ActionContext实例
        ActionContext ctx = invocation.getInvocationContext();
        
        if (checkReferer())
        {
            return "frontIndex";
        }
        
        Map session = ctx.getSession();
        
        User user = (User)session.get("user");
        if (CommonUtils.isNotEmptyOrNull(user))
        {
            Integer unReadNum = messageService.unReadNum(user.getHuiNo());//未读消息数量
            session.put("unReadNum", unReadNum);
            return invocation.invoke();
        }
        // 没有登陆，将服务器提示设置成一个HttpServletRequest属性
        ctx.put("tip", "您还没有登录，请登录系统");
        session.put("isLogin", "false");
        return "frontIndex";
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