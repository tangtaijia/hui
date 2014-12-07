package com.hui.common.dao;

import java.util.List;

import com.hui.common.entity.RechargePackage;

public interface IRechargeDao extends IBaseDao<RechargePackage, RechargePackage> {
	public List getRechargeList();
}
