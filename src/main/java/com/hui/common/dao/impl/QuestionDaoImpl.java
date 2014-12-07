package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.IQuestionDao;
import com.hui.common.entity.Question;
import com.hui.common.webservice.EntityView.QuestionEntityView;

public class QuestionDaoImpl extends AbstractBaseDao<Question, Question> implements IQuestionDao
{
    
    @SuppressWarnings("unchecked")
   
    public List<Question> getAll(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().queryForList(namespace+".getAll", paramMap);
    }
    
    @SuppressWarnings("unchecked")
   
    public List<Question> getAll(Question questionParam)
    {
        return getSqlMapClientTemplate().queryForList(namespace+".getAllE", questionParam);
    }
    
   
    public Integer getCount(Question questionParam)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace+".getCountE", questionParam);
    }
    
    @SuppressWarnings("unchecked")
    public List<com.hui.common.entity.SysFile> selectImagesByQId(Integer qId) 
    {
        return getSqlMapClientTemplate().queryForList(namespace+".selectImagesByQId", qId);
    }
    
   
	public QuestionEntityView getQuestionDetailById(Map<String, String> paramMap) {
		return (QuestionEntityView) getSqlMapClientTemplate().queryForObject(namespace + ".getQuestionDetailById", paramMap);
	}

	
	public List getMyQuestionsList(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getQuestionsPagingList", paramMap);
	}

	
	public List getAllQuestionPaingList(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getAllQuestionPaingList", paramMap);
	}

	
	public List getLastSpeechUserList(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getLastSpeechUserList", paramMap);
	}

	
	public void updateQuestionAnswerNum(Map<String, String> paramMap) {
		getSqlMapClientTemplate().update(namespace + ".updateQuestionAnswerNum", paramMap);
	}

	
	public void updateQuestionFavorate(Map<String, String> paramMap)
			throws Exception {
		getSqlMapClientTemplate().update(namespace + ".updateQuestionFavorate",paramMap); 
	}

    public Integer modifyStatus(Integer status, List<Integer> ids)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", status);
        param.put("ids", ids);
        return (Integer)getSqlMapClientTemplate().update(namespace + ".modifyStatus", param);
    }
	
}