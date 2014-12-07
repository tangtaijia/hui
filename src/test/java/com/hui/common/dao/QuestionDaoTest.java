package com.hui.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Question;
import com.hui.common.entity.SysFile;

public class QuestionDaoTest extends BaseTest
{
    @Autowired
    private IQuestionDao questionDao;
    
    @Test
    public void testGetQuestions() {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<Question> questions = questionDao.getAll(paramMap);
System.out.println("+++++++++++++++questions "+JSON.toJSONStringWithDateFormat(questions,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testQuestionImages() {
        List<SysFile> imgs = questionDao.selectImagesByQId(1);
System.out.println("+++++++++++++++imgs "+JSON.toJSONStringWithDateFormat(imgs,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
}
