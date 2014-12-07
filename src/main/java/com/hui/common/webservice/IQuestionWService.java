package com.hui.common.webservice;

import java.util.List;
import java.util.Map;

import com.hui.common.webservice.EntityView.QuestionEntityView;

public interface IQuestionWService {
	public QuestionEntityView getQuestionDetailById(String questionId);
	
	public List getMyQuestionsList(String huidaNo,String page);
	
	public List getAllQuestionPaingList(String isReward,String isNoAnswer,String gradeId,String courseId,String page);
	
	public List getQuestionSearchByKey(String key,String page);
	
	public List getLastSpeechUserList(String page);
	
	public void AskQuestion(Map<String,Object> paramMap) throws Exception;
}
