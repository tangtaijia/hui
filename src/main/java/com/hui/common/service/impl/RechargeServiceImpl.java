package com.hui.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IRechargeDao;
import com.hui.common.entity.RechargePackage;
import com.hui.common.service.IRechargeService;

@Service
public class RechargeServiceImpl extends BaseServiceImpl<RechargePackage, RechargePackage> implements IRechargeService
{
    @Autowired
    private IRechargeDao rechargeDao;
}
