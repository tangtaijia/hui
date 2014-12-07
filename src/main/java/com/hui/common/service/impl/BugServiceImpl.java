package com.hui.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IBugDao;
import com.hui.common.entity.Bug;
import com.hui.common.service.IBugService;

@Service
public class BugServiceImpl extends BaseServiceImpl<Bug, Bug> implements IBugService
{
    @Autowired
    private IBugDao bugDao;
    
   
    public Integer addBug(Bug bug) throws Exception {
        return bugDao.save(bug);
    }
    
}
