package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.SysBackUp;

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
public interface ISysBackUpService extends IBaseService<SysBackUp, SysBackUp>
{
    
    public Integer createBackUp();
    
    public void createBackUpHand(List<Integer> tabIds);
    
    public void reBackUp(Integer backupId);
    
    public void processBackUpForSEQID(Integer seqId);
    
    public void processBackUpForBU(SysBackUp bi);
    
}