package com.hui.common.runner;

import com.hui.common.entity.SysMsg;
import com.hui.common.service.ISysMsgService;

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
public class SendSysMsgRunner implements Runnable
{
    
    private ISysMsgService sysMsgService;
    
    private SysMsg sysMsg;
    
    private Integer page;
    
    private Integer size;
    
    public SendSysMsgRunner(ISysMsgService sysMsgService, SysMsg sysMsg, Integer page, Integer size)
    {
        this.sysMsgService = sysMsgService;
        this.sysMsg = sysMsg;
        this.page = page;
        this.size = size;
    }
    
    public void run()
    {
        sysMsgService.saveSysMsg2User(sysMsg, page, size);
    }
    
}