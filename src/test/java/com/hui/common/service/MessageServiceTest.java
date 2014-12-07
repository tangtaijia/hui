package com.hui.common.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Page;
public class MessageServiceTest extends BaseTest
{
    @Autowired
    private IMessageService messageService;
    
    @Test
    public void testGetMsgsByType() {
        List<Msg> msgs = messageService.getMessagesByType("83505213",4);
System.out.println("+++++++++++++++msgs "+JSON.toJSONStringWithDateFormat(msgs,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testMsgPage() {
        Page<Msg> page = messageService.getMsgs(1, "83505213",4);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
}
