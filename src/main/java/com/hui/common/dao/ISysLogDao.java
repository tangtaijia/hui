package com.hui.common.dao;

import java.util.List;

import com.hui.common.entity.SysLog;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISysLogDao extends IBaseDao<SysLog, SysLog>
{
    
    public Integer deleteAll();
    
    public Integer delete(List<Integer> ids);
    
}