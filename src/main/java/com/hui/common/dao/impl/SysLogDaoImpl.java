package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.ISysLogDao;
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
public class SysLogDaoImpl extends AbstractBaseDao<SysLog, SysLog> implements ISysLogDao
{
    
    public Integer deleteAll()
    {
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".deleteall");
    }
    
    public Integer delete(List<Integer> ids)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ids", ids);
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".delete", param);
    }
    
}