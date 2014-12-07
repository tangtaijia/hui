package com.hui.common.service;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.RechargePackage;
public class RechargeServiceTest extends BaseTest
{
    @Autowired
    private IRechargeService rechargeService;
    
    @Test
    public void testGetHelps() {
        List<RechargePackage> packages = rechargeService.listByMap(new HashMap<String, Object>());
System.out.println("+++++++++++++++packages "+JSON.toJSONStringWithDateFormat(packages,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
}
