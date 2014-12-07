package com.hui.ajax;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.Grade;
import com.hui.common.entity.Question;
import com.hui.common.entity.QuestionUser;
import com.hui.common.entity.Subject;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.service.IQuestionService;
import com.hui.common.service.IQuestionUserService;
import com.hui.common.service.ITeacherInfoService;
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
public class AjaxTeacherQuestionAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 3783454212920721040L;
    
    private ITeacherInfoService teacherInfoServiceImpl;
    
    private IQuestionService questionServiceImpl;
    
    private IQuestionUserService questionUserService;
    
    private Integer qId;
    
    private String huiNo;
    
    private String qryHuiNo;
    
    private Integer qryGradeId;
    
    private Integer qrySubjectId;
    
    private String qryCreateTimeFrom;
    
    private String qryCreateTimeTo;
    
    private Integer qryAllocated;
    
    private String rows;
    
    private String page;
    
    private AjaxResult result;
    
    private List<TeacherInfo> teachers;
    

    public String getQryHuiNo()
    {
        return qryHuiNo;
    }

    public void setQryHuiNo(String qryHuiNo)
    {
        this.qryHuiNo = qryHuiNo;
    }

    public Integer getqId()
    {
        return qId;
    }

    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }

    public void setqId(Integer qId)
    {
        this.qId = qId;
    }

    public void setTeacherInfoServiceImpl(ITeacherInfoService teacherInfoServiceImpl)
    {
        this.teacherInfoServiceImpl = teacherInfoServiceImpl;
    }

    public void setQuestionServiceImpl(IQuestionService questionServiceImpl)
    {
        this.questionServiceImpl = questionServiceImpl;
    }

    public void setQuestionUserService(IQuestionUserService questionUserService)
    {
        this.questionUserService = questionUserService;
    }

    public void setQryGradeId(Integer qryGradeId)
    {
        this.qryGradeId = qryGradeId;
    }

    public void setQrySubjectId(Integer qrySubjectId)
    {
        this.qrySubjectId = qrySubjectId;
    }

    public void setQryCreateTimeFrom(String qryCreateTimeFrom)
    {
        this.qryCreateTimeFrom = qryCreateTimeFrom;
    }

    public void setQryCreateTimeTo(String qryCreateTimeTo)
    {
        this.qryCreateTimeTo = qryCreateTimeTo;
    }

    public void setQryAllocated(Integer qryAllocated)
    {
        this.qryAllocated = qryAllocated;
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
    
    public List<TeacherInfo> getTeachers()
    {
        return teachers;
    }

    public String toget()
    {
        try
        {
            Question question = questionServiceImpl.getQuestion4Back(qId);
            if(CommonUtils.isNotEmptyOrNull(question)){
                result = new AjaxResult(true, question);
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
    
    public String tolistTeachers() {
        List<TeacherInfo> teacherInfos = teacherInfoServiceImpl.listByMap(new HashMap<String, Object>());
        teachers = teacherInfos;
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
        
        Question questionParam = new Question();
        questionParam.setHuiNo(qryHuiNo);
        questionParam.setGrade(new Grade(qryGradeId));
        questionParam.setSubject(new Subject(qrySubjectId));
        questionParam.setCreateTimeFrom(StringUtils.isBlank(qryCreateTimeFrom) ? null : CommonUtils.getTimeStamp(qryCreateTimeFrom));
        questionParam.setCreateTimeTo(StringUtils.isBlank(qryCreateTimeTo) ? null : CommonUtils.getTimeStamp(qryCreateTimeTo));
        questionParam.setAllocated(qryAllocated);
        questionParam.setStart(start);
        questionParam.setSize(size);
        List<Question> questions = questionServiceImpl.getQuestions4Back(questionParam);
        if(CommonUtils.isNotEmptyOrNull(questions)){
            for(Question question:questions) {
                if(CommonUtils.isNotEmptyOrNullOr0OrFalse(question.getCreateTime())) {
                    question.setCreateTimeStr((CommonUtils.formatTimeStamp(question.getCreateTime(), CommonUtils.getLongDateFormat())));
                }
                if(CommonUtils.isNotEmptyOrNull(question.getAllocated())) {
                    if(0==question.getAllocated()) {
                        question.setAllocatedStr("no");
                    }
                    if(1==question.getAllocated()) {
                        question.setAllocatedStr("ok");
                    }
                } else {
                    question.setAllocatedStr("no");
                }
            }
        }
        result = new AjaxResult(questionServiceImpl.getCount(questionParam), questions);
        return SUCCESS;
    }
    
    public String allocate() {
        QuestionUser questionUser  = new QuestionUser();
        questionUser.setHuiNo(huiNo);
        questionUser.setQuestionId(qId);
        try
        {
            questionUserService.saveEntity(questionUser);
            Question question = questionServiceImpl.selectById(qId);
            question.setAllocated(1);
            questionServiceImpl.updateEntity(question);
            result = new AjaxResult(true, "操作成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "操作失败");
        }
        return SUCCESS;
    }
    
}