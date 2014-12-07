package com.hui.common.dao.impl;

import java.util.List;

import com.hui.common.dao.IRechargeDao;
import com.hui.common.entity.RechargePackage;

public class RechargeDaoImpl extends AbstractBaseDao<RechargePackage, RechargePackage> implements IRechargeDao {

	
	public List getRechargeList() {
		return getSqlMapClientTemplate().queryForList(namespace + ".getRechargeList");
	}

}
