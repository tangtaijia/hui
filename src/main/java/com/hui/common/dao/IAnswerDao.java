package com.hui.common.dao;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.Answer;
import com.hui.common.webservice.EntityView.AnswerEntityView;

public interface IAnswerDao extends IBaseDao<Answer, Answer>{
    
    public Integer modifyStatus(Integer status, List<Integer> ids);
	
	public List<AnswerEntityView> getAnswerTrends(Map<String,String> paramMap);
	
	public List getAnswerListByQuestionId(Map<String,String> paramMap);
	
	public void setBestAnswer(Map<String,String> paramMap) throws Exception;
	
	public List getMyAnswersPagingList(Map<String,String> paramMap);
	
	/**
	 * 增加点赞数
	 * <功能详细描述>
	 * @param answerId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	Integer plusPraise(Integer answerId);
	/**
	 * 减少点赞数
	 * <功能详细描述>
	 * @param answerId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	Integer minusPraise(Integer answerId);
	
}
