package com.hui.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IQuestionUserDao;
import com.hui.common.entity.QuestionUser;
import com.hui.common.service.IQuestionUserService;
import com.hui.common.utils.CommonUtils;

@Service
public class QuestionUserServiceImpl extends BaseServiceImpl<QuestionUser, QuestionUser> implements IQuestionUserService
{
    @Autowired
    private IQuestionUserDao questionUserDao;
    
   
    public Integer saveEntity(QuestionUser questionUser)
    {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("qId", questionUser.getQuestionId());
        List<QuestionUser> questionUsers = questionUserDao.selectAllByMap(paramMap);
        if(CommonUtils.isNotEmptyOrNull(questionUsers)) {
            for(QuestionUser questionUserTmp:questionUsers) {
                questionUserDao.delete(questionUserTmp.getQuId());
            }
        }
        return super.saveEntity(questionUser);
    }
}
