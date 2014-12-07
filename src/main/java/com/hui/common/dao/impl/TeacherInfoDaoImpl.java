package com.hui.common.dao.impl;

import java.util.List;
import java.util.Map;

import com.hui.common.dao.ITeacherInfoDao;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.entity.teacherList;

public class TeacherInfoDaoImpl extends AbstractBaseDao<TeacherInfo, TeacherInfo> implements ITeacherInfoDao
{

	
	public List<TeacherInfo> getDutyTeacherList() {
		return getSqlMapClientTemplate().queryForList(namespace + ".queryTeacherInfoList");
	}

	
	public List getTeacherInformation(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getTeacherInformation",paramMap);
	}
	
	@SuppressWarnings("unchecked")
   
	public List<TeacherInfo> selectAllE(TeacherInfo teacherInfo)
	{
	    return getSqlMapClientTemplate().queryForList(namespace + ".selectAllE",teacherInfo);
	}
	
	
	public Integer getCountE(TeacherInfo teacherInfo)
	{
	    return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getCountE",teacherInfo); 
	}
	
	@SuppressWarnings("unchecked")
	public List<teacherList> getTeachersList(Map<String, String> paramMap) {
		return getSqlMapClientTemplate().queryForList(namespace + ".getTeachersList",paramMap);
	} 
    
}
