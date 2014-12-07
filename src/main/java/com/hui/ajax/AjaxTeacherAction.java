package com.hui.ajax;

import java.util.List;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.Grade;
import com.hui.common.entity.Subject;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.entity.User;
import com.hui.common.service.ITeacherInfoService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxTeacherAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 3783454212920721040L;
    
    private ITeacherInfoService teacherInfoServiceImpl;
    
    private IUserService userService;
    
    private Integer teacherId;
    
    private String qryHuiNo;
    
    private String qryTeacherName;
    
    private Integer qryGradeId;
    
    private Integer qrySubjectId;
    
    private Integer qryOnlineStatus;
    
    private String teacherName;
    
    private String huiNo;
    
    private String teacherDesc;
    
    private Integer gradeId;
    
    private Integer subjectId;
    
    private Integer teacherTitle;
    
    private String rows;
    
    private String page;
    
    private AjaxResult result;
    
    public void setTeacherInfoServiceImpl(ITeacherInfoService teacherInfoServiceImpl)
    {
        this.teacherInfoServiceImpl = teacherInfoServiceImpl;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    public void setTeacherId(Integer teacherId)
    {
        this.teacherId = teacherId;
    }

    public String getQryHuiNo()
    {
        return qryHuiNo;
    }

    public void setQryHuiNo(String qryHuiNo)
    {
        this.qryHuiNo = qryHuiNo;
    }

    public String getQryTeacherName()
    {
        return qryTeacherName;
    }

    public void setQryTeacherName(String qryTeacherName)
    {
        this.qryTeacherName = qryTeacherName;
    }

    public Integer getQryGradeId()
    {
        return qryGradeId;
    }

    public void setQryGradeId(Integer qryGradeId)
    {
        this.qryGradeId = qryGradeId;
    }

    public Integer getQrySubjectId()
    {
        return qrySubjectId;
    }

    public void setQrySubjectId(Integer qrySubjectId)
    {
        this.qrySubjectId = qrySubjectId;
    }

    public Integer getQryOnlineStatus()
    {
        return qryOnlineStatus;
    }

    public void setQryOnlineStatus(Integer qryOnlineStatus)
    {
        this.qryOnlineStatus = qryOnlineStatus;
    }

    public void setTeacherName(String teacherName)
    {
        this.teacherName = teacherName;
    }

    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }

    public void setTeacherDesc(String teacherDesc)
    {
        this.teacherDesc = teacherDesc;
    }

    public void setGradeId(Integer gradeId)
    {
        this.gradeId = gradeId;
    }

    public void setSubjectId(Integer subjectId)
    {
        this.subjectId = subjectId;
    }

    public void setTeacherTitle(Integer teacherTitle)
    {
        this.teacherTitle = teacherTitle;
    }

    public Integer getTeacherId()
    {
        return teacherId;
    }

    public String getTeacherName()
    {
        return teacherName;
    }

    public String getHuiNo()
    {
        return huiNo;
    }

    public String getTeacherDesc()
    {
        return teacherDesc;
    }

    public Integer getGradeId()
    {
        return gradeId;
    }

    public Integer getSubjectId()
    {
        return subjectId;
    }

    public Integer getTeacherTitle()
    {
        return teacherTitle;
    }

    public String getRows()
    {
        return rows;
    }
    
    public void setRows(String rows)
    {
        this.rows = rows;
    }
    
    public String getPage()
    {
        return page;
    }
    
    public void setPage(String page)
    {
        this.page = page;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public String toget()
    {
        try
        {
            TeacherInfo teacherInfo = teacherInfoServiceImpl.teacherInfoDetail(teacherId);
            if(CommonUtils.isNotEmptyOrNull(teacherInfo)){
                User user= userService.getUserInfo(teacherInfo.getHuiNo());
                teacherInfo.setUser(user);
                result = new AjaxResult(true, teacherInfo);
            } else {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "查询失败");
        }
        
        return SUCCESS;
    }
    
    public String tolist()
    {
        // 当前页   
        int intPage = Integer.parseInt((null == page || "0".equals(page)) ? "1" : page);
        // 每页显示条数   
        int size = Integer.parseInt((null == rows || "0".equals(rows)) ? "10" : rows);
        // 每页的开始记录  第一页为1  第二页为number +1    
        int start = (intPage - 1) * size;
        
        TeacherInfo teacherInfoParam = new TeacherInfo();
        teacherInfoParam.setHuiNo(qryHuiNo);
        teacherInfoParam.setTeacherName(qryTeacherName);
        teacherInfoParam.setGrade(new Grade(qryGradeId));
        teacherInfoParam.setSubject(new Subject(qrySubjectId));
        teacherInfoParam.setOnlineStatus(qryOnlineStatus);
        teacherInfoParam.setStart(start);
        teacherInfoParam.setSize(size);
        List<TeacherInfo> teacherInfos = teacherInfoServiceImpl.list(teacherInfoParam);
        result = new AjaxResult(teacherInfoServiceImpl.getCount(teacherInfoParam), teacherInfos);
        
        return SUCCESS;
    }
    
    public String modifyOnlineStatus() {
        try
        {
            TeacherInfo teacherInfo = teacherInfoServiceImpl.selectById(teacherId);
            if(CommonUtils.isNotEmptyOrNull(teacherInfo)) {
                if(1==teacherInfo.getOnlineStatus()) {
                    teacherInfoServiceImpl.fitOffLine(teacherId);
                } else {
                    teacherInfoServiceImpl.fitOnLine(teacherId);
                }
                result = new AjaxResult(true, "修改成功");
            } else {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "修改失败");
        }
        
        return SUCCESS;
    }
    
    public String create() {
        try
        {
            TeacherInfo teacher = new TeacherInfo();
            teacher.setTeacherName(teacherName);
            teacher.setHuiNo(huiNo);
            teacher.setTeacherDesc(teacherDesc);
            teacher.setTeacherTitle(teacherTitle);
            teacher.setGrade(new Grade(gradeId));
            teacher.setSubject(new Subject(subjectId));
            teacher.setOnlineStatus(0);
            teacherInfoServiceImpl.saveTeacher(teacher);
            result = new AjaxResult(true, "新增成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "新增失败");
        }
        return SUCCESS;
    }
    public String modify() {
        try
        {
            TeacherInfo teacher = teacherInfoServiceImpl.selectById(teacherId);
            String oriHuiNo = teacher.getHuiNo();
            teacher.setTeacherName(teacherName);
            teacher.setHuiNo(huiNo);
            teacher.setTeacherDesc(teacherDesc);
            teacher.setTeacherTitle(teacherTitle);
            teacher.setGrade(new Grade(gradeId));
            teacher.setSubject(new Subject(subjectId));
            teacher.setOnlineStatus(0);
            teacherInfoServiceImpl.modifyTeacher(teacher,oriHuiNo);
            result = new AjaxResult(true, "修改成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "修改失败");
        }
        return SUCCESS;
    }
    
}