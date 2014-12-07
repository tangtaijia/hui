package com.hui.common.webservice;

import java.util.Map;

public interface IPraiseWService {
	public void praise(String huidaNo, String answerId) throws Exception;
	
	public int queryPraiseCount(Map<String,String> paramMap);
	
}
