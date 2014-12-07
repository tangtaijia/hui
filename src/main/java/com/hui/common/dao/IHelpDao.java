package com.hui.common.dao;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.Help;

public interface IHelpDao extends IBaseDao<Help, Help> {
	
	public List getHelpPagingList(Map<String,Object> paramMap);
	
	public List getHelpDetail(Map<String,String> paramMap);
	
}
