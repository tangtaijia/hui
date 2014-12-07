package com.hui.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IAnswerDao;
import com.hui.common.dao.IPraiseDao;
import com.hui.common.entity.Praise;
import com.hui.common.service.IPraiseService;

@Service("praiseService")
public class PraiseServiceImpl extends BaseServiceImpl<Praise, Praise> implements IPraiseService
{
    @Autowired
    private IPraiseDao praiseDao;
    @Autowired
    private IAnswerDao answerDao;
    
   
    public Integer praise(Praise praise)
    {
        answerDao.plusPraise(praise.getAnswerId());//添加点赞数
        return praiseDao.save(praise);
    }
    
   
    public Integer unPraise(Integer prId)
    {
        Praise praise = selectById(prId);
        answerDao.minusPraise(praise.getAnswerId());//减少点赞数
        return praiseDao.delete(prId);
    }
    
   
    public Integer unPraise(Praise praise)
    {
        answerDao.minusPraise(praise.getAnswerId());//减少点赞数
        return praiseDao.deletePraise(praise);
    }
    
}
