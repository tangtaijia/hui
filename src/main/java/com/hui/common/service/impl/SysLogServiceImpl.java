package com.hui.common.service.impl;

import java.util.List;

import com.hui.common.dao.ISysLogDao;
import com.hui.common.entity.SysLog;
import com.hui.common.service.ISysLogService;

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
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, SysLog> implements ISysLogService
{
    
    public Integer deleteAll()
    {
        return ((ISysLogDao)baseDao).deleteAll();
    }
    
    public Integer delete(List<Integer> ids)
    {
        return ((ISysLogDao)baseDao).delete(ids);
    }
    
}