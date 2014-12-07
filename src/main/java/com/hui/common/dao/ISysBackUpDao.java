package com.hui.common.dao;

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
public interface ISysBackUpDao extends IBaseDao<SysBackUp, SysBackUp>
{
    
    public List<Object> selectTab(SysBackUp bi);
    
    public Integer getTabItems(SysBackUp bi);
    
    public Integer getMaxSeqId();
    
    public Integer getIncreBackUpSeqId();
    
}