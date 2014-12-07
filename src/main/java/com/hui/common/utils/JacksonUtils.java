package com.hui.common.utils;

import java.util.LinkedHashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class JacksonUtils
{
    
    private static JacksonUtils instance;
    
    private static Object lock = new Object();
    
    private ObjectMapper objectMapper;
    
    private JacksonUtils()
    {
        objectMapper = new ObjectMapper();
        //过滤null的属性
        objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
    }
    
    public static JacksonUtils getInstance()
    {
        if (null == instance)
        {
            synchronized (lock)
            {
                if (null == instance)
                {
                    instance = new JacksonUtils();
                }
            }
        }
        return instance;
    }
    
    /** 
      * JSON转换为List对象 
      */
    @SuppressWarnings("unchecked")
    public List<LinkedHashMap<String, Object>> readJson2List(String str)
    {
        try
        {
            return objectMapper.readValue(str, List.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public String writeEntityJSON(Object obj)
    {
        try
        {
            return objectMapper.writeValueAsString(obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
    
}