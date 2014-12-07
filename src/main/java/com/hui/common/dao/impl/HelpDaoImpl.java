package com.hui.common.dao.impl;

import java.util.List;
import java.util.Map;

import com.hui.common.dao.IHelpDao;
import com.hui.common.entity.Help;

public class HelpDaoImpl extends AbstractBaseDao<Help, Help> implements IHelpDao {

	
	public List getHelpPagingList(Map<String, Object> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getHelpPagingList", paramMap);
	}

	
	public List getHelpDetail(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getHelpDetailById", paramMap);
	}

}
