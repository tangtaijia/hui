package com.hui.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Help;
import com.hui.common.entity.Page;
import com.hui.common.entity.Subject;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.service.IHelpService;
import com.hui.common.service.ISubjectService;
import com.hui.common.service.ITeacherInfoService;

/**
 * 帮助中心Action
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HelpCenterAction extends BaseActionSupport
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5960766205248674859L;
    @Autowired
    private IHelpService helpService;
    @Autowired
    private ITeacherInfoService teacherInfoServiceImpl;
    @Autowired
    private ISubjectService subjectService;
    
    private List<Help> helps;
    
    private Page<TeacherInfo> teacherPage=new Page<TeacherInfo>();
    
    private List<Subject> subjects;
    
    /**
     * 学科
     */
    private Integer subjectIdParam;
    
    /**
     * 教师介绍
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String teachers() {
        subjects = subjectService.list();
        teacherPage = teacherInfoServiceImpl.getTeacherInfos(teacherPage.getPage(),10, null, null, null,subjectIdParam,null);
        return "teachers";
    }
    
    /**
     * 帮助中心
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String center() {
        helps = helpService.getHelps();
        return "center";
    }
    
    /**
     * 隐私保护
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String privacy() {
        return "privacy";
    }
    
    /**
     * 服务条款
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String service() {
        return "service";
    }
    
    /**
     * 联系我们
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String contact() {
        return "contact";
    }
    
    /**
     * 问题反馈
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String feed() {
        return "feed";
    }

    public IHelpService getHelpService()
    {
        return helpService;
    }

    public List<Help> getHelps()
    {
        return helps;
    }

    public Page<TeacherInfo> getTeacherPage()
    {
        return teacherPage;
    }

    public Integer getSubjectIdParam()
    {
        return subjectIdParam;
    }

    public void setSubjectIdParam(Integer subjectIdParam)
    {
        this.subjectIdParam = subjectIdParam;
    }

    public List<Subject> getSubjects()
    {
        return subjects;
    }

}
