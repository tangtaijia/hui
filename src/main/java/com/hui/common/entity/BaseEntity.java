package com.hui.common.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <基础Entity>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseEntity implements Serializable
{
    
    private static final long serialVersionUID = -468276408246550868L;
    
    private Integer start;
    
    private Integer size;
    
    public Integer getStart()
    {
        return start;
    }
    
    public void setStart(Integer start)
    {
        this.start = start;
    }
    
    public Integer getSize()
    {
        return size;
    }
    
    public void setSize(Integer size)
    {
        this.size = size;
    }
    
    public String toString()
    {
        Field[] fields = getClass().getDeclaredFields();
        StringBuffer outStr = new StringBuffer();
        outStr.append(getClass().getName());
        outStr.append("\n{\n");
        
        for (Field field : fields)
        {
            if (field.getType().isPrimitive() || field.getGenericType() == String.class
                || field.getGenericType() == Boolean.class || field.getGenericType() == Character.class
                || field.getGenericType() == Short.class || field.getGenericType() == Integer.class
                || field.getGenericType() == Long.class || field.getGenericType() == Float.class
                || field.getGenericType() == Double.class)
            {
                String methodName = "";
                
                if (field.getGenericType() == boolean.class || field.getGenericType() == Boolean.class)
                {
                    methodName = "is" + firstCharUpper(field.getName());
                }
                else
                {
                    methodName = "get" + firstCharUpper(field.getName());
                }
                
                Method method = null;
                try
                {
                    method = getClass().getMethod(methodName, null);
                    String fieldName = field.getName();
                    outStr.append("  [");
                    outStr.append(fieldName);
                    outStr.append(" = ");
                    outStr.append(method.invoke(this, null));
                    outStr.append("]\n");
                }
                catch (Exception e)
                {
                    
                }
            }
            else
            {
                outStr.append("  [");
                outStr.append(field.getName());
                outStr.append(" = ");
                outStr.append(field.toGenericString());
                outStr.append("]\n");
            }
        }
        
        outStr.append("}");
        return outStr.toString();
    }
    
    private String firstCharUpper(String str)
    {
        char firstChar = CharUtils.toChar(str);
        String upFirstChar = StringUtils.upperCase(String.valueOf(firstChar));
        return StringUtils.replaceOnce(str, String.valueOf(firstChar), upFirstChar);
    }
    
}