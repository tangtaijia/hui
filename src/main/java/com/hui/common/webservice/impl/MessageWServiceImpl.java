package com.hui.common.webservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IMessageDao;
import com.hui.common.utils.PaginationConfig;
import com.hui.common.webservice.IMessageWService;

@Service("messageWService")
public class MessageWServiceImpl implements IMessageWService {
	
	@Autowired
	private IMessageDao messageDao;

	
	public int getAnswerMeCountByUnread(String huidaNo) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgType", "1");
		paramMap.put("isRead", "0");
		return messageDao.getMessageCountByType(paramMap);
	}

	
	public int getAskMeCountByUnread(String huidaNo) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgType", "2");
		paramMap.put("isRead", "0");
		return messageDao.getMessageCountByType(paramMap);
	}

	
	public int getAcceptMeCountByUnread(String huidaNo) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgType", "3");
		paramMap.put("isRead", "0");
		return messageDao.getMessageCountByType(paramMap);
	}

	
	public int getSystemMessageByUnread(String huidaNo) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgType", "4");
		paramMap.put("isRead", "0");
		return messageDao.getMessageCountByType(paramMap);
	}

	
	public List getAnswerMeList(String huidaNo,String page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgType", "1");
		int offset = PaginationConfig.pageOffSet(Integer.valueOf(page));
		paramMap.put("offset", String.valueOf(offset));
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		return messageDao.getMessagesPagingByType(paramMap);
	}

	
	public List getAskMeList(String huidaNo,String page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgType", "2");
		int offset = PaginationConfig.pageOffSet(Integer.valueOf(page));
		paramMap.put("offset", String.valueOf(offset));
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		return messageDao.getMessagesPagingByType(paramMap);
	
	}

	
	public List getAcceptMeList(String huidaNo,String page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgType", "3");
		int offset = PaginationConfig.pageOffSet(Integer.valueOf(page));
		paramMap.put("offset", String.valueOf(offset));
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		return messageDao.getMessagesPagingByType(paramMap);
	}

	
	public List getSystemMessageList(String huidaNo,String page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgType", "4");
		int offset = PaginationConfig.pageOffSet(Integer.valueOf(page));
		paramMap.put("offset", String.valueOf(offset));
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		return messageDao.getMessagesPagingByType(paramMap);
	}

	
	public void setMessageRead(String huidaNo, String messageId) throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("messageId", messageId);
		messageDao.setMessageRead(paramMap);
		
	}
	
	public void setMessageReadByCondition(String huidaNo,String questionId,String msgType) throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("msgInfoId", questionId);
		paramMap.put("msgType", msgType);
		messageDao.setMessageReadByCondition(paramMap);
	}

}
