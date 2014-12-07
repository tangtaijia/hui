package com.hui.common.runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014年7月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SendSysMsgThreadPool
{
    
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    
    public static final ExecutorService getThreadPool()
    {
        return executorService;
    }
    
}