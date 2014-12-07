package com.hui.common.cache;

import java.util.Date;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseCache extends GeneralCacheAdministrator
{
    
    private static final long serialVersionUID = -7006576911982297913L;
    
    /**
     * 关键字前缀字符
     */
    private String keyPrefix;
    
    public BaseCache(String keyPrefix)
    {
        super();
        this.keyPrefix = keyPrefix;
    }
    
    public void put(String key, Object value)
    {
        this.putInCache(this.keyPrefix + "_" + key, value);
    }
    
    public void put(String key, Object value, String[] groups)
    {
        this.putInCache(this.keyPrefix + "_" + key, value, groups);
    }
    
    public void remove(String key)
    {
        this.flushEntry(this.keyPrefix + "_" + key);
    }
    
    public void removeObjectByGroup(String group)
    {
        this.flushGroup(group);
    }
    
    public void removeAll(Date date)
    {
        this.flushAll(date);
    }
    
    public void removeAll()
    {
        this.flushAll();
    }
    
    public Object get(String key, int refreshPeriod)
        throws NeedsRefreshException
    {
        try
        {
            return this.getFromCache(this.keyPrefix + "_" + key, refreshPeriod);
        }
        catch (NeedsRefreshException e)
        {
            this.cancelUpdate(this.keyPrefix + "_" + key);
            throw e;
        }
    }
    
}