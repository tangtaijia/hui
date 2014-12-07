package com.hui.common.dao;

import java.util.Map;

import com.hui.common.entity.Praise;

public interface IPraiseDao extends IBaseDao<Praise, Praise> {
	
	public void praise(Map<String,String> paramMap) throws Exception;
	
	public int queryPraiseCount(Map<String,String> paramMap);
	
	Integer deletePraise(Praise praise);
	
}
