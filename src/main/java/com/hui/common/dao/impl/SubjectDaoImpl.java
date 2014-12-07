package com.hui.common.dao.impl;

import java.util.List;

import com.hui.common.dao.ISubjectDao;
import com.hui.common.entity.Subject;

public class SubjectDaoImpl extends AbstractBaseDao<Subject, Subject> implements ISubjectDao
{
    @SuppressWarnings("unchecked")
   
    public List<Subject> selectByGradeId(Integer gradeId)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".selectByGradeId", gradeId);
    }
}
