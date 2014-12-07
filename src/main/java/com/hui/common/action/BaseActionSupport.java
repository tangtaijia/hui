package com.hui.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.hui.common.utils.DBUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

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
public class BaseActionSupport extends ActionSupport implements ServletRequestAware, ServletResponseAware
{
    
    private static final long serialVersionUID = -1232609418286925518L;
    
    ActionContext context = ActionContext.getContext();
    
    HttpServletRequest request;
    
    HttpServletResponse response;
    
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }
    
    public HttpServletRequest getRequest()
    {
        return request;
    }
    
    public HttpServletResponse getResponse()
    {
        return response;
    }
    
    /**
     * 获取手机验证码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected String getPhoneCode() {
    	String phoneCode = (String) request.getSession().getAttribute("phoneCode");
    	request.getSession().removeAttribute("phoneCode");
    	return phoneCode;
    }
    
    /**
     * 远程文件服务器地址(0 FTP 1 local)
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getFilePath()
    {
    	return DBUtils.getFilePath();
    }

    /**
     * 本地服务器地址
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getLocalFilePath()
    {
        return DBUtils.getInstance().getLocalFilePath();
    }

}