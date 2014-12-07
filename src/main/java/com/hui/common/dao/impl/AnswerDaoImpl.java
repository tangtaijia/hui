package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.IAnswerDao;
import com.hui.common.entity.Answer;
import com.hui.common.webservice.EntityView.AnswerEntityView;

public class AnswerDaoImpl extends AbstractBaseDao<Answer, Answer> implements IAnswerDao {

    public Integer modifyStatus(Integer status, List<Integer> ids)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", status);
        param.put("ids", ids);
        return (Integer)getSqlMapClientTemplate().update(namespace + ".modifyStatus", param);
    }
    
	@SuppressWarnings("unchecked")
	public List<AnswerEntityView> getAnswerTrends(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".queryRecentAnswerStatusInfo", paramMap);
	}
	
	
	public List getAnswerListByQuestionId(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getAnswerListByQuestionIdPaging", paramMap);
	}

	
	public void setBestAnswer(Map<String, String> paramMap) throws Exception {
		getSqlMapClientTemplate().update(namespace + ".setBestAnswer", paramMap);
	}

	
	public List getMyAnswersPagingList(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getMyAnswersPagingList", paramMap);
	}
	
	
	public Integer plusPraise(Integer answerId)
	{
	    return getSqlMapClientTemplate().update(namespace + ".plusPraise", answerId);
	}
	
	
	public Integer minusPraise(Integer answerId)
	{
	    return getSqlMapClientTemplate().update(namespace + ".minusPraise", answerId);
	}
	
}
