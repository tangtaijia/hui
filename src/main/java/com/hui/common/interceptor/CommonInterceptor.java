package com.hui.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.hui.common.entity.SysData;
import com.hui.common.entity.User;
import com.hui.common.service.IMessageService;
import com.hui.common.service.ISysDataService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.CookieHandler;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.RegUtils;
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
public class CommonInterceptor extends AbstractInterceptor
{
    
    private static final long serialVersionUID = -3936772203980270629L;
    
    @Autowired
    private ISysDataService sysDataService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IMessageService messageService;
    
    public String intercept(ActionInvocation invocation)
        throws Exception
    {
        // 取得请求相关的ActionContext实例
        ActionContext ctx = invocation.getInvocationContext();
        /*二维码图片地址*/
        getCodePicLoc(ctx);
        /*检查是否保存登录状态*/
        checkSavedLogin(ctx);
        /*检查是否跨站点请求伪造*/
        if (checkReferer())
        {
            return "frontIndex";
        }
        // 否则struts.xml中<param name="scope">request</param>无法读取，只能写成session
        return invocation.invoke();
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
    
    /**
     * 二维码图片地址
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void getCodePicLoc(ActionContext ctx)
    {
        /*二维码图片地址*/
        Map session = ctx.getSession();
        String code2 = "";
        SysData sysDataParam = new SysData();
        sysDataParam.setDataCode("code_location");
        List<SysData> sysDatas = sysDataService.selectByKey(sysDataParam);
        if (CommonUtils.isNotEmptyOrNull(sysDatas))
        {
            JSONArray jasonArray = JSONArray.parseArray(sysDatas.get(0).getDataValue());
            Map<String, Object> map = (Map<String, Object>)jasonArray.get(0);
            code2 = map.get("url") + "";
            session.put("code2", code2);
        }
        /*安卓软件地址*/
        String andrLoc = "";
        sysDataParam = new SysData();
        sysDataParam.setDataCode("soft_download");
        sysDatas = sysDataService.selectByKey(sysDataParam);
        if (CommonUtils.isNotEmptyOrNull(sysDatas))
        {
            JSONArray jasonArray = JSONArray.parseArray(sysDatas.get(0).getDataValue());
            Map<String, Object> map = (Map<String, Object>)jasonArray.get(0);
            andrLoc = map.get("url") + "";
            session.put("andrLoc", andrLoc);
        }
    }
    
    /**
     * 检查是否保存了登录状态
     * <功能详细描述>
     * @param request
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void checkSavedLogin(ActionContext ctx)
    {
        HttpServletRequest request = (HttpServletRequest)ctx.get(StrutsStatics.HTTP_REQUEST);
        if (request.getRequestURI().indexOf("exitLogin") > -1)
        {
            return;
        }
        String loginKey = CookieHandler.getCookieValueByName(request, FrontUtils.LOGIN_KEY);
        String pwd = CookieHandler.getCookieValueByName(request, FrontUtils.LOGIN_PWD);
        Map session = ctx.getSession();
        User user = (User)session.get("user");
        if (CommonUtils.isEmptyOrNull(user))
        {
            User userParam = new User();
            userParam.setUserPwd(pwd);
            /*判断是否为手机号码*/
            if (RegUtils.isPhone(loginKey))
            {
                userParam.setMobile(loginKey);
                userParam.setNickName(null);
            }
            else
            {
                userParam.setMobile(null);
                userParam.setNickName(loginKey);
            }
            List<User> users = userService.selectByKey(userParam);
            User result = null;
            if (CommonUtils.isNotEmptyOrNull(users))
            {
                result = users.get(0);
                userService.presentBean4Login1Day(result);//一天登录一次赠送汇豆
                session.put("user", result);
                Integer unReadNum = messageService.unReadNum(result.getHuiNo());//未读消息数量
                session.put("unReadNum", unReadNum);
            }
            else
            {
                return;
            }
        }
        else
        {
            return;
        }
        
    }
    
}