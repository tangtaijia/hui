package com.hui.common.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.SysMsg;

public class SysMsgDaoTest extends BaseTest
{
    @Autowired
    private ISysMsgDao sysMsgDao;
    
    @Test
    public void testSysMsg() {
        SysMsg sysMsg = this.sysMsgDao.selectById(1);
System.out.println("+++++++++++++++sysMsg "+JSON.toJSONStringWithDateFormat(sysMsg,
    "yyyy-MM-dd HH:mm:ss"));
    }
}
