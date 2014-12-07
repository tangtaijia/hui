package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.BaseTest;
import com.hui.common.entity.Praise;
public class PraiseServiceTest extends BaseTest
{
    @Autowired
    private IPraiseService praiseService;
    
    
    @Test
    public void testPraise() {
        Praise praise = new Praise();
        praise.setHuiNo("83505213");
        praise.setAnswerId(1);
        praiseService.praise(praise);
    }
    
    @Test
    public void testUnPraise() {
        praiseService.unPraise(1);
    }
    
}
