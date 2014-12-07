package com.hui.common.job;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class BaseJob
{
    
    public BaseJob()
    {
        super();
    }
    
    public void check()
    {
        try
        {
            doCheck();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }
    
    protected abstract void doCheck();
    
}