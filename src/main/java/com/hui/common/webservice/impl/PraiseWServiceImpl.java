package com.hui.common.webservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IPraiseDao;
import com.hui.common.webservice.IPraiseWService;

@Service("praiseWService")
public class PraiseWServiceImpl implements IPraiseWService {
	
	@Autowired
	private IPraiseDao praiseDao;

	
	public void praise(String huidaNo, String answerId) throws Exception{
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("answerId", answerId);
		praiseDao.praise(paramMap);
	}

	
	public int queryPraiseCount(Map<String,String> paramMap) {
		return praiseDao.queryPraiseCount(paramMap);
	}

}
