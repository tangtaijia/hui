package com.hui.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IGradeSubjectDao;
import com.hui.common.service.IGradeSubjectService;

@Service("gradeSubjectService")
public class GradeSubjectServiceImpl implements IGradeSubjectService {
	
	@Autowired
	private IGradeSubjectDao gradeSubjectDao;

	public List getCourseListByGradeId(String gradeId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("gradeId", gradeId);
		return gradeSubjectDao.getCourseListByGradeId(paramMap);
	}

}
