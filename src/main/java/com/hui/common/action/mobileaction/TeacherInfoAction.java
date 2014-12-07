package com.hui.common.action.mobileaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.service.ITeacherInfoService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.webservice.EntityView.TeacherInfoEntityView;

public class TeacherInfoAction extends BaseActionSupport
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 8075473716298901403L;
    
    @Autowired
    private ITeacherInfoService teacherInfoService;
    
    private List resultList;
    
    private AjaxResult result;
    
    private String rows;
    
    private String page;
    
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
    
    public List getResultList()
    {
        return resultList;
    }
    
    public void setResultList(List resultList)
    {
        this.resultList = resultList;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public String execute()
    {
        List<TeacherInfo> list = teacherInfoService.getDutyTeacherList();
        resultList = new ArrayList();
        TeacherInfoEntityView view = null;
        for (TeacherInfo info : list)
        {
            view = new TeacherInfoEntityView();
            view.setHuidaNo(info.getHuiNo());
            view.setName(info.getTeacherName());
            view.setTeacherTitle(info.getTeacherTitle());
            view.setIntroduction(info.getTeacherDesc());
            resultList.add(view);
        }
        return SUCCESS;
    }
    
    /**
     * 获得教师信息
     * 
     * @return
     */
    public String getTeacherInformation()
    {
        HttpServletRequest request = getRequest();
        String huidaNo = request.getParameter("huidaNo");
        Map<String, String> map = CommonUtils.getLastWeekTime();
        resultList = teacherInfoService.getTeacherInformation(huidaNo, map.get("lastMonday"), map.get("lastSunday"));
        return SUCCESS;
    }
    
    /**
     * 返回教师列表
     * 
     * @return
     */
    public String getTeachersList()
    {
        HttpServletRequest request = getRequest();
        int page = Integer.valueOf(request.getParameter("page"));
        String huidaNo = request.getParameter("huidaNo");
        // String gradeName =
        // CommonUtils.toUTF_8(request.getParameter("gradeText")) ;
        String subjectName = CommonUtils.toUTF_8(request.getParameter("courseText"));
        resultList = teacherInfoService.getTeachersList(huidaNo, null, subjectName, page);
        return SUCCESS;
    }
    
}