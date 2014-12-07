package com.hui.common.runner;

import com.hui.common.service.ISysBackUpService;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BackUpRunnerForSEQID implements Runnable
{
    
    private ISysBackUpService sysBackUpService;
    
    private Integer seqId;
    
    public BackUpRunnerForSEQID(ISysBackUpService sysBackUpService, Integer seqId)
    {
        this.sysBackUpService = sysBackUpService;
        this.seqId = seqId;
    }
    
   
    public void run()
    {
        sysBackUpService.processBackUpForSEQID(seqId);
    }
    
}