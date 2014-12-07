package com.hui.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hui.common.dao.IGradeSubjectDao;

public class GradeSubjectDaoImpl extends AbstractBaseDao implements
		IGradeSubjectDao {

	
	public List getCourseListByGradeId(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getCourseListByGradeId", paramMap);
	}

}
