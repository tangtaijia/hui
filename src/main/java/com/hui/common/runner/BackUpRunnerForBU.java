package com.hui.common.runner;

import com.hui.common.entity.SysBackUp;
import com.hui.common.service.ISysBackUpService;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BackUpRunnerForBU implements Runnable
{
    
    private ISysBackUpService sysBackUpService;
    
    private SysBackUp bi;
    
    public BackUpRunnerForBU(ISysBackUpService sysBackUpService, SysBackUp bi)
    {
        this.sysBackUpService = sysBackUpService;
        this.bi = bi;
    }
    
   
    public void run()
    {
        sysBackUpService.processBackUpForBU(bi);
    }
    
}