package com.hui.common.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Subject;
public class SubjectServiceTest extends BaseTest
{
    @Autowired
    private ISubjectService subjectService;
    
    @Test
    public void testSelectAll() {
        List<Subject> subjects = subjectService.list();
System.out.println("+++++++++++++++subjects "+JSON.toJSONStringWithDateFormat(subjects,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testSelectByGradeId() {
        List<Subject> subjects = subjectService.selectByGradeId(1);
System.out.println("+++++++++++++++subjects "+JSON.toJSONStringWithDateFormat(subjects,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
}
