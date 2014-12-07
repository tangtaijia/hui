package com.hui.common.dao.impl;

import java.util.List;

import com.hui.common.dao.IBugDao;
import com.hui.common.entity.Bug;

public class BugDaoImpl extends AbstractBaseDao<Bug, Bug> implements IBugDao
{
    @SuppressWarnings("unchecked")
	public List<Bug> selectAll(Bug bug) {
    	return getSqlMapClientTemplate().queryForList(namespace + ".selectAllE", bug);
    }
    
    public Integer getCount(Bug bug) {
    	return (Integer) getSqlMapClientTemplate().queryForObject(namespace + ".getCountE", bug);
    }
}
