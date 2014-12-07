package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.Page;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.entity.User;
import com.hui.common.entity.teacherList;

/**
 * 教师Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ITeacherInfoService extends IBaseService<TeacherInfo, TeacherInfo>
{
    /**
     * 分页查询教师信息
     * <功能详细描述>
     * @param pageNo
     * @param huiNo
     * @param teacherName
     * @param onlineStatus
     * @param subjectId
     * @param gradeId
     * @return
     * @see [类、类#方法、类#成员]
     */
	Page<TeacherInfo> getTeacherInfos(Integer pageNo, Integer size,
			String huiNo, String teacherName, Integer onlineStatus,
			Integer subjectId,Integer gradeId);
	
	/**
	 * 查询教师信息
	 * <功能详细描述>
	 * @param huiNo
	 * @param teacherName
	 * @param onlineStatus
	 * @param subjectId
	 * @param gradeId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<TeacherInfo> getTeacherInfos(String huiNo, String teacherName,
			Integer onlineStatus, Integer subjectId, Integer gradeId);
	
	
	/**
	 * 
	 * 返回教师列表
	 * 
	 */
	List<teacherList> getTeachersList(String huidaNo, String gradeText, String courseText, int page);
	
	
    /**
     * 教师信息详情
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    TeacherInfo teacherInfoDetail(Integer teacherId);
    
    /**
     * 设置在线
     * <功能详细描述>
     * @param teacherId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer fitOnLine(Integer teacherId);
    
    /**
     * 设置离线
     * <功能详细描述>
     * @param teacherId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer fitOffLine(Integer teacherId);
    
    public List<TeacherInfo> getDutyTeacherList();
    
    public List getTeacherInformation(String huidaNo,String beginTime,String endTime);
    
    /**
     * 判断是否是老师
     * <功能详细描述>
     * @param huiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Boolean isTeacher(String huiNo);
    
    /**
     * 创建教师
     * <功能详细描述>
     * @param teacherInfo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer saveTeacher(TeacherInfo teacherInfo);
    
    /**
     * 修改教师
     * <功能详细描述>
     * @param teacherInfo
     * @param oriHuiNo 
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer modifyTeacher(TeacherInfo teacherInfo, String oriHuiNo);
    
    /**
     * 添加老师属性到用户
     * <功能详细描述>
     * @param user
     * @return
     * @see [类、类#方法、类#成员]
     */
    User pasteTeacher(User user);
    
}
