package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Collection;
import com.hui.common.entity.Page;
import com.hui.common.entity.Question;
import com.hui.common.entity.User;

public class CollectionServiceTest extends BaseTest
{
    @Autowired
    private ICollectionService collectionService;
    
    @Test
    public void testCollectQuestion() {
        Question question  = new Question();
        question.setqId(1);
        User user = new User("83505213");
        System.out.println(collectionService.collectQuestion(user, question));
    }
    
    @Test
    public void testGetCollections() {
        Page<Collection> collectionPage = collectionService.getCollections(1, "83505213");
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(collectionPage,
    "yyyy-MM-dd HH:mm:ss"));
    }
}
