package com.hui.common.action.mobileaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.webservice.IRechargeWService;

@SuppressWarnings("rawtypes")
public class RechargeAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -157119894858059048L;
	
	@Autowired
	private IRechargeWService rechargeWSerivce;
	
	private List resultList;
	
	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String execute(){
		resultList = rechargeWSerivce.getRechargeList();
		return SUCCESS;
	}

}
