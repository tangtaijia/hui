package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.BaseTest;
import com.hui.common.entity.Bug;
public class BugServiceTest extends BaseTest
{
    @Autowired
    private IBugService bugService;
    
    @Test
    public void testAddBug() {
        Bug bug = new Bug();
        bug.setBugDesc("问题反馈描述");
        bug.setClientStyle("三星 S4");
        bug.setCreateTime(1400055555);
        bug.setHuiNo("83505213");
        bug.setStatus(1);
        try
        {
            bugService.addBug(bug);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
