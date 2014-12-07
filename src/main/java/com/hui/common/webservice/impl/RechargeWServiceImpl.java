package com.hui.common.webservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IRechargeDao;
import com.hui.common.webservice.IRechargeWService;

@Service("rechargeWService")
public class RechargeWServiceImpl implements IRechargeWService {
	
	@Autowired
	private IRechargeDao rechargeDao;

	
	public List getRechargeList() {
		return rechargeDao.getRechargeList();
	}

}
