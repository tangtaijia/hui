package com.hui.common.dao.impl;

import java.util.List;

import com.hui.common.dao.ISysBackUpDao;
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
public class SysBackUpDaoImpl extends AbstractBaseDao<SysBackUp, SysBackUp> implements ISysBackUpDao
{
    
    @SuppressWarnings("unchecked")
   
    public List<Object> selectTab(SysBackUp bi)
    {
        return (List<Object>)getSqlMapClientTemplate().queryForList(namespace + ".selectTab", bi);
    }
    
   
    public Integer getMaxSeqId()
    {
        Integer seqId = (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getMaxSeqId");
        if (null == seqId)
        {
            return 0;
        }
        return seqId;
    }
    
   
    public Integer getTabItems(SysBackUp bi)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getTabItems", bi);
    }
    
   
    public Integer getIncreBackUpSeqId()
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getIncreBackUpSeqId");
    }
    
}