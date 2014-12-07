package com.hui.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.ISubjectDao;
import com.hui.common.entity.Subject;
import com.hui.common.service.ISubjectService;

@Service
public class SubjectServiceImpl extends BaseServiceImpl<Subject,Subject> implements ISubjectService
{
    @Autowired
    private ISubjectDao subjectDao;

   
    public List<Subject> selectByGradeId(Integer gradeId)
    {
        return this.subjectDao.selectByGradeId(gradeId);
    }
    
}
