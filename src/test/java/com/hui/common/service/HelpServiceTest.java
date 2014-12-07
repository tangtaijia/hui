package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Help;
import com.hui.common.entity.Page;
public class HelpServiceTest extends BaseTest
{
    @Autowired
    private IHelpService helpService;
    
    @Test
    public void testGetHelps() {
        Page<Help> page = helpService.getHelps(1);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
}
