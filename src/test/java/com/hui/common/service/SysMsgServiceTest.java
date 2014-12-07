package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Page;
import com.hui.common.entity.SysMsg;
public class SysMsgServiceTest extends BaseTest
{
    @Autowired
    private ISysMsgService sysMsgService;
    
    @Test
    public void testSysMsg() {
        SysMsg sysMsg = sysMsgService.getSysMsgDetail(1);
System.out.println("+++++++++++++++sysMsg "+JSON.toJSONStringWithDateFormat(sysMsg,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testSysMsgPage() {
        Page<SysMsg> page = sysMsgService.getSysMsgs(1);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
}
