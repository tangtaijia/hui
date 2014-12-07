package com.hui.common.webservice;

import java.util.List;

public interface IMessageWService {
	
	public int getAnswerMeCountByUnread(String huidaNo);
	
	public int getAskMeCountByUnread(String huidaNo);
	
	public int getAcceptMeCountByUnread(String huidaNo);
	
	public int getSystemMessageByUnread(String huidaNo);
	
	public List getAnswerMeList(String huidaNo,String page);
	
	public List getAskMeList(String huidaNo,String page);
	
	public List getAcceptMeList(String huidaNo,String page);
	
	public List getSystemMessageList(String huidaNo,String page);
	
	public void setMessageRead(String huidaNo, String messageId) throws Exception;
	
	public void setMessageReadByCondition(String huidaNo,String questionId,String msgType) throws Exception;
	
}
