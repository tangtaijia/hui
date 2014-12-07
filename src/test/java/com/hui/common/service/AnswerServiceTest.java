package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Answer;
import com.hui.common.entity.Page;
public class AnswerServiceTest extends BaseTest
{
    @Autowired
    private IAnswerService answerService;
    
    @Test
    public void testAnswerPage() {
        Page<Answer> page = answerService.getAnswers(1, "83505213", null);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testDoFavor() {
        this.answerService.doFavorite(1);
    }
    
    @Test
    public void testAddAnswer() {
        Answer answer = new Answer();
        answer.setAnswerDesc("这是个答案");
        answer.setClientStyle("大神F1");
        answer.setCreateTime(1400040000);
        answer.setHasImg(0);
        answer.setHuiNo("83505213");
        answer.setIsFavorate(1);
        answer.setPraiseNum(0);
        answer.setQuestionId(1);
        answer.setStatus(1);
        answerService.addAnswer(answer);
    }
}
