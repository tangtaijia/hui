package com.hui.common.service;

import com.hui.common.entity.SysFile;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISysFileService extends IBaseService<SysFile, SysFile>
{
    
    public Integer delete(SysFile fi);
    
    public Integer getMaxSeqId(SysFile fi);
    
    Integer modifyIcon(Integer userId,String imgStr);
    
}