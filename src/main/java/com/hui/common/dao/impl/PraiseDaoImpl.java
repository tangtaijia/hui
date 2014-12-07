package com.hui.common.dao.impl;

import java.util.Map;

import com.hui.common.dao.IPraiseDao;
import com.hui.common.entity.Praise;

public class PraiseDaoImpl extends AbstractBaseDao<Praise, Praise> implements IPraiseDao {

	
	public void praise(Map<String, String> paramMap) throws Exception {
		getSqlMapClientTemplate().insert(namespace + ".addPraiseEntity", paramMap);
	}

	
	public int queryPraiseCount(Map<String, String> paramMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject(namespace + ".queryPraiseCount", paramMap);
	}
	
	
	public Integer deletePraise(Praise praise)
	{
	    return getSqlMapClientTemplate().delete(namespace + ".deletePraise", praise);
	}

}
