package com.hui.common.dao.impl;

import com.hui.common.dao.IOnlineDurationDao;
import com.hui.common.entity.OnlineDuration;

public class OnlineDurationDaoImpl extends AbstractBaseDao<OnlineDuration, OnlineDuration> implements IOnlineDurationDao
{
   
    public Integer countOnlineTime(Integer teacherId)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".countOnlineTime",teacherId);
    }
    
   
    public Integer checkOnline(Integer teacherId)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".checkOnline",teacherId);
    }
    
   
    public Integer getRecentOnlineTime(Integer teacherId)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getRecentOnlineTime",teacherId);
    }
}
