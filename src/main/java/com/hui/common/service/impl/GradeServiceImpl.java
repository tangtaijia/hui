package com.hui.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IGradeDao;
import com.hui.common.dao.ISubjectDao;
import com.hui.common.entity.Grade;
import com.hui.common.entity.Subject;
import com.hui.common.service.IGradeService;

@Service("gradeServiceImpl")
public class GradeServiceImpl extends BaseServiceImpl<Grade, Grade> implements IGradeService
{
    @Autowired
    private IGradeDao gradeDao;
    @Autowired
    private ISubjectDao subjectDao;
    
   
    public List<Grade> selectAllWithSubs()
    {
        List<Grade> grades = list();
        for(Grade grade:grades) {
            List<Subject> subjects = subjectDao.selectByGradeId(grade.getGradeId());
            grade.setSubjects(subjects);
        }
        return grades;
    }
}
