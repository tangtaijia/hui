package com.hui.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Msg;

public class MessageDaoTest extends BaseTest
{
    @Autowired
    private IMessageDao messageDao;
    
    @Test
    public void testGetMsgsByType() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<Msg> msgs = messageDao.getMessagesByType(paramMap);
System.out.println("+++++++++++++++msgs "+JSON.toJSONStringWithDateFormat(msgs,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testMsgPage() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<Msg> msgs = messageDao.getAll(paramMap);
System.out.println("+++++++++++++++msgs "+JSON.toJSONStringWithDateFormat(msgs,
    "yyyy-MM-dd HH:mm:ss"));
        Integer msgCount = messageDao.getCount(paramMap);
System.out.println("+++++++++++++++msgCount "+JSON.toJSONStringWithDateFormat(msgCount,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
}
