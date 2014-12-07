package com.hui.common.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;

import com.hui.common.entity.SysArticle;
import com.hui.common.entity.SysMsg;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MethodInterceptor
{
    
    public void before(JoinPoint joinpoint, Object param)
        throws Throwable
    {
        filterObject(param);
    }
    
    private Object filterObject(Object obj)
    {
        try
        {
            boolean isSkip = false;
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields)
            {
                isSkip = false;
                if ((obj.getClass().equals(SysArticle.class) && "contentValue".equalsIgnoreCase(field.getName()))
                    || (obj.getClass().equals(SysMsg.class) && "sysMsgDesc".equalsIgnoreCase(field.getName())))
                {
                    isSkip = true;
                }
                if (field.getGenericType() == String.class && !isSkip)
                {
                    String methodName = "get" + firstCharUpper(field.getName());
                    String setMethodName = "set" + firstCharUpper(field.getName());
                    
                    Method method = null;
                    method = obj.getClass().getMethod(methodName, null);
                    String value = (String)method.invoke(obj, null);
                    
                    if (StringUtils.isNotBlank(value))
                    {
                        value = stripXSS(value);
                        method = obj.getClass().getMethod(setMethodName, String.class);
                        method.invoke(obj, value);
                    }
                }
            }
        }
        catch (Exception e)
        {
            
        }
        return obj;
    }
    
    private String stripXSS(String value)
    {
        if (StringUtils.isNotBlank(value))
        {
            // Avoid null characters   
            value = value.replaceAll("", "");
            
            // Avoid anything in ' type of expression   
            Pattern scriptPattern = Pattern.compile("'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            Matcher matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                value = value.replace("'", "＇");
            }
            
            // Avoid anything in " type of expression   
            scriptPattern = Pattern.compile("\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                value = value.replace("\"", "＂");
            }
            
            // Avoid anything between < tags   
            scriptPattern = Pattern.compile("<", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                value = value.replace("<", "＜");
            }
            
            // Avoid anything between > tags   
            scriptPattern = Pattern.compile(">", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            while (matcher.find())
            {
                value = value.replace(">", "＞");
            }
            
            // Avoid eval(...) expressions   
            scriptPattern =
                Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid expression(...) expressions   
            scriptPattern =
                Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid javascript:... expressions   
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid vbscript:... expressions   
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid onXXX= expressions
            List<String> htmlEvents = CommonUtils.getHTMLEvents();
            for (String event : htmlEvents)
            {
                scriptPattern =
                    Pattern.compile(event + "(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                matcher = scriptPattern.matcher(value);
                while (matcher.find())
                {
                    value = value.replace("=", "＝");
                }
            }
        }
        return value;
    }
    
    private String firstCharUpper(String str)
    {
        char firstChar = CharUtils.toChar(str);
        String upFirstChar = StringUtils.upperCase(String.valueOf(firstChar));
        return StringUtils.replaceOnce(str, String.valueOf(firstChar), upFirstChar);
    }
    
}