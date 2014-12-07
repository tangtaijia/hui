package com.hui.common.webservice;

import java.util.List;

public interface IAnswerInfoWService {
	public List<Object> getAnswerTrends(String huidaNo);
	
	public List getAnswerListByQuestionId(String questionId, String page);
	
	public void setBestAnswer(String questionId,String answerId) throws Exception;
	
	public List getMyAnswersPagingList(String huidaNo,String page);
	
	public void answerQuestion(String huidaNo,String questionId,String answerDescription,
					String image,String mode,String os) throws Exception;
}
