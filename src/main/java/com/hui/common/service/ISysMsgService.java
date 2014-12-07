package com.hui.common.service;

import com.hui.common.entity.Page;
import com.hui.common.entity.SysMsg;

/**
 * 系统消息Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISysMsgService extends IBaseService<SysMsg, SysMsg>
{
    
    /**
     * 系统消息详情页
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    SysMsg getSysMsgDetail(Integer sysMsgId);
    
    /**
     * 系统消息分页
     * <功能详细描述>
     * @param pageNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<SysMsg> getSysMsgs(Integer pageNo);
    
    public void saveSysMsg2User(SysMsg sysMsg, Integer page, Integer size);
    
}