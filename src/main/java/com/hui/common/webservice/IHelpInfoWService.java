package com.hui.common.webservice;

import java.util.List;

public interface IHelpInfoWService {
	public List getHelpPagingList(String page);
	
	public List getHelpDetail(String helpId);
}
