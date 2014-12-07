package com.hui.common.webservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IHelpDao;
import com.hui.common.utils.PaginationConfig;
import com.hui.common.webservice.IHelpInfoWService;

@Service("helpInfoWService")
public class HelpInfoWServiceImpl implements IHelpInfoWService {
	
	@Autowired
	private IHelpDao helpDao;

	
	public List getHelpPagingList(String page) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("pageSize", PaginationConfig.pageSize);
		paramMap.put("offset", PaginationConfig.pageOffSet(Integer.valueOf(page)));
		return helpDao.getHelpPagingList(paramMap);
	}

	
	public List getHelpDetail(String helpId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("helpId", helpId);
		return helpDao.getHelpDetail(paramMap);
	}

}
