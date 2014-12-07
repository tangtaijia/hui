package com.hui.common.dao;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.TeacherInfo;
import com.hui.common.entity.teacherList;

public interface ITeacherInfoDao extends IBaseDao<TeacherInfo,TeacherInfo>
{
    public List<TeacherInfo> getDutyTeacherList();
    
    public List getTeacherInformation(Map<String,String> paramMap);
    
    List<TeacherInfo> selectAllE(TeacherInfo teacherInfo);
    
    Integer getCountE(TeacherInfo teacherInfo);

	public List<teacherList> getTeachersList(Map<String, String> paramMap);
}
