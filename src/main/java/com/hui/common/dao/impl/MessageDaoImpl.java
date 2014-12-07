package com.hui.common.dao.impl;

import java.util.List;
import java.util.Map;

import com.hui.common.dao.IMessageDao;
import com.hui.common.entity.Msg;

class MessageDaoImpl extends AbstractBaseDao<Msg, Msg> implements IMessageDao {

	
	public int getMessageCountByType(Map<String, String> paramMap) {
		return (Integer) getSqlMapClientTemplate().queryForObject(namespace + ".getMessageCountByType",paramMap);
	}
	
	@SuppressWarnings("unchecked")
   
	public List<Msg> getMessagesByType(Map<String, Object> paramMap)
	{
	    return getSqlMapClientTemplate().queryForList(namespace+".getMessagesByType", paramMap);
	}
	
	@SuppressWarnings("unchecked")
   
	public List<Msg> getAll(Map<String, Object> paramMap)
	{
	    return getSqlMapClientTemplate().queryForList(namespace+".getAll", paramMap);
	}
	
	
	public List getMessagesPagingByType(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getMessagesPagingByType", paramMap);
	}

	
	public void setMessageRead(Map<String, String> paramMap) throws Exception {
		getSqlMapClientTemplate().update(namespace + ".setMessageRead", paramMap);
	}
	
   
    public Integer deleteByTypeAndInfoId(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().delete(namespace + ".deleteByTypeAndInfoId", paramMap);
    }

	
	public void setMessageReadByCondition(Map<String, String> paramMap) {
		getSqlMapClientTemplate().update(namespace + ".setMessageReadByCondition", paramMap);
	}

}
