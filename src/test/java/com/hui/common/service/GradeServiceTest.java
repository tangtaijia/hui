package com.hui.common.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Grade;
public class GradeServiceTest extends BaseTest
{
    @Autowired
    private IGradeService gradeService;
    
    @Test
    public void testSelectAll() {
        List<Grade> grades = gradeService.list();
System.out.println("+++++++++++++++grades "+JSON.toJSONStringWithDateFormat(grades,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testSelectWithSubs() {
        List<Grade> grades = gradeService.selectAllWithSubs();
        System.out.println("+++++++++++++++grades "+JSON.toJSONStringWithDateFormat(grades,
            "yyyy-MM-dd HH:mm:ss"));
        
    }
    
}
