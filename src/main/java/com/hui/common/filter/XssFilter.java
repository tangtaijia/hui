package com.hui.common.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.util.IOUtils;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014年7月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class XssFilter implements Filter
{
    
    @Override
    public void destroy()
    {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException
    {
        // getParameterMap会乱码
        request.setCharacterEncoding("UTF-8");
        
        BodyReaderHttpServletRequestWrapper httpRequest =
            new BodyReaderHttpServletRequestWrapper((HttpServletRequest)request);
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String url = httpRequest.getServletPath();
        String contentType = httpRequest.getContentType();
        
        // File上传
        if (StringUtils.isNotBlank(contentType) && (-1 != contentType.indexOf("multipart/form-data")))
        {
            BufferedReader br = null;
            try
            {
                br = new BufferedReader(new InputStreamReader(httpRequest.getInputStream()));
                String buffer = null;
                StringBuffer buff = new StringBuffer();
                while ((buffer = br.readLine()) != null)
                {
                    buff.append(buffer);
                }
                String value = URLDecoder.decode(buff.toString());
                if (!stripXSS(value))
                {
                    httpResponse.sendError(404);
                    return;
                }
            }
            catch (Exception e)
            {
                
            }
            finally
            {
                IOUtils.close(br);
            }
        }
        if (StringUtils.isNotBlank(url))
        {
            // 非法URL
            if (!url.matches("^[/A-Za-z\\d\\.\\_\\-]+$"))
            {
                httpResponse.sendError(404);
                return;
            }
            // extends="json-default"
            if ((-1 != url.indexOf("ajax")) || (-1 != url.indexOf("interface")))
            {
                if ((-1 != url.indexOf("createAjaxSysMsg")) || (-1 != url.indexOf("modifyAjaxSysMsg"))
                    || (-1 != url.indexOf("tocreateAjaxAdminSysArticle"))
                    || (-1 != url.indexOf("tomodifyAjaxAdminSysArticle"))
                    || (-1 != url.indexOf("tomodifycontentAjaxAdminSysArticle"))
                    || "/interface/AskStudent.hui".equalsIgnoreCase(url)
                    || "/interface/AskTeacher.hui".equalsIgnoreCase(url)
                    || "/interface/uploadUserIcon.hui".equalsIgnoreCase(url))
                {
                    filterChain.doFilter(httpRequest, response);
                    return;
                }
                
                String value = httpRequest.getQueryString();
                if (StringUtils.isNotBlank(value))
                {
                    value = URLDecoder.decode(httpRequest.getQueryString());
                    if (!stripXSS(value))
                    {
                        httpResponse.sendError(404);
                        return;
                    }
                }
                
                Map<String, String[]> parameterMap = httpRequest.getParameterMap();
                Iterator<String> keys = parameterMap.keySet().iterator();
                String key = null;
                String[] values = null;
                while (keys.hasNext())
                {
                    key = keys.next();
                    values = parameterMap.get(key);
                    for (String val : values)
                    {
                        value = URLDecoder.decode(val);
                        if (!stripXSS(value))
                        {
                            httpResponse.sendError(404);
                            return;
                        }
                    }
                }
            }
        }
        
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(httpRequest);
        filterChain.doFilter(xssRequest, response);
    }
    
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {
        
    }
    
    private boolean stripXSS(String value)
    {
        if (StringUtils.isNotBlank(value))
        {
            // Avoid anything in a src='...' type of expression   
            Pattern scriptPattern =
                Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);
            Matcher matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
            
            scriptPattern =
                Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
            
            // Avoid eval(...) expressions   
            scriptPattern =
                Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
            
            // Avoid expression(...) expressions   
            scriptPattern =
                Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
            
            // Avoid javascript:... expressions   
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
            
            // Avoid vbscript:... expressions   
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
            
            // Avoid onXXX= expressions
            List<String> htmlEvents = CommonUtils.getHTMLEvents();
            for (String event : htmlEvents)
            {
                scriptPattern =
                    Pattern.compile("'(.*)" + event + "(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                matcher = scriptPattern.matcher(value);
                while (matcher.find())
                {
                    return false;
                }
                
                scriptPattern =
                    Pattern.compile("\"(.*)" + event + "(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                matcher = scriptPattern.matcher(value);
                while (matcher.find())
                {
                    return false;
                }
                
                scriptPattern =
                    Pattern.compile(event + "(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                matcher = scriptPattern.matcher(value);
                while (matcher.find())
                {
                    return false;
                }
            }
            
            // Avoid anything between '> tags   
            scriptPattern = Pattern.compile("'(.*)>", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
            
            // Avoid anything between "> tags   
            scriptPattern = Pattern.compile("\"(.*)>", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
            
            // Avoid anything between <> tags   
            scriptPattern = Pattern.compile("<(.*)>", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                return false;
            }
        }
        return true;
    }
    
}