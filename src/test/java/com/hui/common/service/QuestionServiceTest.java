package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Grade;
import com.hui.common.entity.Page;
import com.hui.common.entity.Question;
import com.hui.common.entity.Subject;
public class QuestionServiceTest extends BaseTest
{
    @Autowired
    private IQuestionService questionService;
    
    @Test
    public void testQuestionPage() {
        Page<Question> page = questionService.getQuestions(1, "83505213",null, null, null,null,null,0);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testQuestionDetail() {
        Question question = questionService.selectById(1);
System.out.println("+++++++++++++++question "+JSON.toJSONStringWithDateFormat(question,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testAddQuestion() {
        Question question = new Question();
        Grade grade = new Grade(2);
        Subject subject = new Subject(3);
        question.setQuestionDesc("这是个问题");
        question.setClientStyle("Galaxy 4");
        question.setAnswerNum(0);
        question.setCreateTime(1400005555);
        question.setGrade(grade);
        question.setHasAnswer(0);
        question.setHasFavorate(0);
        question.setHasImg(0);
        question.setHuiNo("03668287");
        question.setIsReward(1);
        question.setRewardAmount(5);
        question.setStatus(1);
        question.setSubject(subject);
        question.setToTeacher(1);
        questionService.saveEntity(question);
    }
    
}
