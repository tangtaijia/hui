package com.hui.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.InvKeywords;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.entity.User;
import com.hui.common.entity.UserRole;
import com.hui.common.message.SendSMSMessage;
import com.hui.common.service.IMessageService;
import com.hui.common.service.IQuestionService;
import com.hui.common.service.ISysRoleService;
import com.hui.common.service.ITeacherInfoService;
import com.hui.common.service.IUserFollowService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.CookieHandler;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.MD5Encrypt;
import com.hui.common.utils.RegUtils;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author LuLiLi
 * @version [版本号, 2014-1-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AjaxUserAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = -5759860974298421640L;
    
    private IUserService userService;
    
    private ISysRoleService sysRoleService;
    
    private IUserFollowService userFollowService;
    
    private IMessageService messageService;
    
    private IQuestionService questionService;
    
    private ITeacherInfoService teacherInfoServiceImpl;
    
    private List<User> users;
    
    private AjaxResult result;
    
    private String rows;
    
    private String page;
    
    private Integer userId;
    
    private String huiNo;
    
    private String userPwd;
    
    private String nickName;
    
    private String qryHuiNo;
    
    private String qryNickName;
    
    private Integer qryStatus;
    
    private String delIds;
    
    private String userrole;
    
    /* ========用户信息 开始======== */
    private Integer sex;
    
    private String birthdateYear;
    
    private String mobile;
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    private String birthdateMonth;
    
    private String birthdateDay;
    
    private Integer constellation;
    
    private Integer homeProvinceId;
    
    private Integer homeCityId;
    
    private Integer homeCountyId;
    
    private Integer residenceProvinceId;
    
    private Integer residenceCityId;
    
    private Integer residenceCountyId;
    
    private Integer gradeId;
    
    private Integer schoolRegionProvinceId;
    
    private Integer schoolRegionCityId;
    
    private Integer schoolRegionCountyId;
    
    private String school;
    
    /* ========用户信息 结束======== */
    
    /* ========添加 开始======== */
    
    private String targetHuiNo;
    
    /* ========添加 结束======== */
    
    private Integer msgIdParam;
    
    private Integer gradeIdParam;
    
    private Integer subIdParam;
    
    /**
     * 是否记住密码
     */
    private Integer remembers;
    
    /**
     * 验证码
     */
    private String valiCode;
    
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }
    
    public void setSysRoleService(ISysRoleService sysRoleService)
    {
        this.sysRoleService = sysRoleService;
    }
    
    public List<User> getUsers()
    {
        return users;
    }
    
    public void setUsers(List<User> users)
    {
        this.users = users;
    }
    
    public AjaxResult getResult()
    {
        return result;
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
    
    public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    public String getHuiNo()
    {
        return huiNo;
    }
    
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    
    public String getUserPwd()
    {
        return userPwd;
    }
    
    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }
    
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }
    
    public void setBirthdateYear(String birthdateYear)
    {
        this.birthdateYear = birthdateYear;
    }
    
    public void setBirthdateMonth(String birthdateMonth)
    {
        this.birthdateMonth = birthdateMonth;
    }
    
    public void setBirthdateDay(String birthdateDay)
    {
        this.birthdateDay = birthdateDay;
    }
    
    public void setConstellation(Integer constellation)
    {
        this.constellation = constellation;
    }
    
    public void setHomeProvinceId(Integer homeProvinceId)
    {
        this.homeProvinceId = homeProvinceId;
    }
    
    public void setHomeCityId(Integer homeCityId)
    {
        this.homeCityId = homeCityId;
    }
    
    public void setHomeCountyId(Integer homeCountyId)
    {
        this.homeCountyId = homeCountyId;
    }
    
    public void setResidenceProvinceId(Integer residenceProvinceId)
    {
        this.residenceProvinceId = residenceProvinceId;
    }
    
    public void setResidenceCityId(Integer residenceCityId)
    {
        this.residenceCityId = residenceCityId;
    }
    
    public void setResidenceCountyId(Integer residenceCountyId)
    {
        this.residenceCountyId = residenceCountyId;
    }
    
    public void setGradeId(Integer gradeId)
    {
        this.gradeId = gradeId;
    }
    
    public void setSchoolRegionProvinceId(Integer schoolRegionProvinceId)
    {
        this.schoolRegionProvinceId = schoolRegionProvinceId;
    }
    
    public void setSchoolRegionCityId(Integer schoolRegionCityId)
    {
        this.schoolRegionCityId = schoolRegionCityId;
    }
    
    public void setSchoolRegionCountyId(Integer schoolRegionCountyId)
    {
        this.schoolRegionCountyId = schoolRegionCountyId;
    }
    
    public void setSchool(String school)
    {
        this.school = school;
    }
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    public String getQryHuiNo()
    {
        return qryHuiNo;
    }
    
    public void setQryHuiNo(String qryHuiNo)
    {
        this.qryHuiNo = qryHuiNo;
    }
    
    public String getQryNickName()
    {
        return qryNickName;
    }
    
    public void setQryNickName(String qryNickName)
    {
        this.qryNickName = qryNickName;
    }
    
    public Integer getQryStatus()
    {
        return qryStatus;
    }
    
    public void setQryStatus(Integer qryStatus)
    {
        this.qryStatus = qryStatus;
    }
    
    public String getDelIds()
    {
        return delIds;
    }
    
    public void setDelIds(String delIds)
    {
        this.delIds = delIds;
    }
    
    public String getUserrole()
    {
        return userrole;
    }
    
    public void setUserrole(String userrole)
    {
        this.userrole = userrole;
    }
    
    public void setGradeIdParam(Integer gradeIdParam)
    {
        this.gradeIdParam = gradeIdParam;
    }
    
    public void setSubIdParam(Integer subIdParam)
    {
        this.subIdParam = subIdParam;
    }
    
    public void setTargetHuiNo(String targetHuiNo)
    {
        this.targetHuiNo = targetHuiNo;
    }
    
    public void setMsgIdParam(Integer msgIdParam)
    {
        this.msgIdParam = msgIdParam;
    }
    
    public void setRemembers(Integer remembers)
    {
        this.remembers = remembers;
    }
    
    public void setUserFollowService(IUserFollowService userFollowService)
    {
        this.userFollowService = userFollowService;
    }
    
    public void setMessageService(IMessageService messageService)
    {
        this.messageService = messageService;
    }
    
    public void setQuestionService(IQuestionService questionService)
    {
        this.questionService = questionService;
    }
    
    public void setTeacherInfoServiceImpl(ITeacherInfoService teacherInfoServiceImpl)
    {
        this.teacherInfoServiceImpl = teacherInfoServiceImpl;
    }
    
    public void setValiCode(String valiCode)
    {
        this.valiCode = valiCode;
    }
    
    public String toget()
    {
        try
        {
            User user = userService.selectById(userId);
            if (null != user)
            {
                UserRole ar = new UserRole();
                ar.setUserId(userId);
                
                user.setStatusStr((String)CommonUtils.getSysData("status")
                    .getValue()
                    .get(Integer.toString(user.getStatus())));
                if (CommonUtils.isNotEmptyOrNull(user.getBirthDate()))
                {
                    user.setBirthDateStr(CommonUtils.formatTimeStamp(user.getBirthDate(),
                        CommonUtils.getShortDateFormat()));
                }
                if (CommonUtils.isNotEmptyOrNull(user.getSex()))
                {
                    if (0 == user.getSex())
                    {
                        user.setSexStr("保密");
                    }
                    if (1 == user.getSex())
                    {
                        user.setSexStr("男");
                    }
                    if (2 == user.getSex())
                    {
                        user.setSexStr("女");
                    }
                }
                else
                {
                    user.setSexStr("保密");
                }
                result = new AjaxResult(true, sysRoleService.selectUserRole(ar), user);
            }
            else
            {
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
        // 每页的开始记录 第一页为1 第二页为number +1
        int start = (intPage - 1) * size;
        
        User ui = new User();
        ui.setHuiNo(StringUtils.isBlank(qryHuiNo) ? null : qryHuiNo);
        ui.setNickName(StringUtils.isBlank(qryNickName) ? null : qryNickName);
        ui.setStatus(qryStatus);
        ui.setStart(start);
        ui.setSize(size);
        
        List<User> users = userService.list(ui);
        if (null != users && 0 != users.size())
        {
            for (User user : users)
            {
                user.setStatusStr((String)CommonUtils.getSysData("status")
                    .getValue()
                    .get(Integer.toString(user.getStatus())));
                if (CommonUtils.isNotEmptyOrNull(user.getBirthDate()))
                {
                    user.setBirthDateStr(CommonUtils.formatTimeStamp(user.getBirthDate(),
                        CommonUtils.getShortDateFormat()));
                }
                if (CommonUtils.isNotEmptyOrNull(user.getSex()))
                {
                    if (0 == user.getSex())
                    {
                        user.setSexStr("保密");
                    }
                    if (1 == user.getSex())
                    {
                        user.setSexStr("男");
                    }
                    if (2 == user.getSex())
                    {
                        user.setSexStr("女");
                    }
                }
                else
                {
                    user.setSexStr("保密");
                }
            }
        }
        
        result = new AjaxResult(userService.getCount(ui), users);
        
        return SUCCESS;
    }
    
    public String torole()
    {
        try
        {
            User user = userService.selectById(userId);
            if (null != user)
            {
                // 为了避免脏数据
                List<Integer> aIds = new ArrayList<Integer>();
                aIds.add(userId);
                sysRoleService.deleteUserRole(aIds);
                
                if (!StringUtils.isBlank(userrole))
                {
                    UserRole ar = null;
                    String[] idArray = StringUtils.split(userrole, ',');
                    for (String id : idArray)
                    {
                        ar = new UserRole();
                        ar.setUserId(userId);
                        ar.setRoleId(Integer.parseInt(StringUtils.trim(id)));
                        sysRoleService.saveUserRole(ar);
                    }
                }
                
                result = new AjaxResult(true, "角色分配成功");
            }
            else
            {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "角色分配失败");
        }
        
        return SUCCESS;
    }
    
    public String tocreate()
    {
        try
        {
            User ui = new User();
            ui.setHuiNo(huiNo);
            ui.setUserPwd(MD5Encrypt.encode(userPwd));
            
            userService.saveEntity(ui);
            result = new AjaxResult(true, this.getText("createuser.success"));
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, this.getText("createuser.error"));
        }
        
        return SUCCESS;
    }
    
    public String tomodify()
    {
        try
        {
            User user = userService.selectById(userId);
            if (null != user)
            {
                if (!StringUtils.isBlank(userPwd))
                {
                    user.setUserPwd(MD5Encrypt.encode(userPwd));
                }
                user.setNickName(nickName);
                
                userService.updateEntity(user);
                result = new AjaxResult(true, "修改成功");
            }
            else
            {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "修改失败");
        }
        
        return SUCCESS;
    }
    
    public String tounlock()
    {
        try
        {
            List<Integer> ids = new ArrayList<Integer>();
            String[] idArray = StringUtils.split(delIds, ',');
            for (String id : idArray)
            {
                ids.add(Integer.valueOf(id));
            }
            userService.modifyStatus(0, ids);
            result = new AjaxResult(true, "解锁成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "解锁失败");
        }
        
        return SUCCESS;
    }
    
    public String tolock()
    {
        try
        {
            List<Integer> ids = new ArrayList<Integer>();
            String[] idArray = StringUtils.split(delIds, ',');
            for (String id : idArray)
            {
                ids.add(Integer.valueOf(id));
            }
            userService.modifyStatus(1, ids);
            result = new AjaxResult(true, "锁定成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "锁定失败");
        }
        
        return SUCCESS;
    }
    
    public String todel()
    {
        try
        {
            List<Integer> ids = new ArrayList<Integer>();
            String[] idArray = StringUtils.split(delIds, ',');
            for (String id : idArray)
            {
                ids.add(Integer.valueOf(id));
            }
            userService.modifyStatus(2, ids);
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
    
    public String toexport()
    {
        User ui = new User();
        ui.setHuiNo(StringUtils.isBlank(qryHuiNo) ? null : qryHuiNo);
        ui.setNickName(StringUtils.isBlank(qryNickName) ? null : qryNickName);
        ui.setStatus(qryStatus);
        
        users = userService.list(ui);
        
        return "export";
    }
    
    public String queryByNickName()
    {
        users = userService.getUsersByNickName(qryNickName, 0);
        User user = (User)this.getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(user) && CommonUtils.isNotEmptyOrNull(users))
        {
            List<User> usersResult = new ArrayList<User>();
            for (User userEl : users)
            {
                if (!userEl.getHuiNo().equals(user.getHuiNo()))
                {
                    usersResult.add(userEl);
                }
            }
            users = usersResult;
        }
        result = new AjaxResult(users.size(), users);
        return SUCCESS;
    }
    
    /**
     * 搜索好友 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryMyFriends()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(user))
        {
            users = userService.getMyFriends(user.getHuiNo(), qryNickName);
        }
        result = new AjaxResult(users.size(), users);
        return SUCCESS;
    }
    
    public String removeNoLoginInSession()
    {
        getRequest().getSession().removeAttribute("tipInRequest");
        getRequest().getSession().removeAttribute("isLogin");
        return SUCCESS;
    }
    
    public String removeTipInRequest()
    {
        getRequest().getSession().removeAttribute("tipInRequest");
        getRequest().removeAttribute("tipInRequest");
        return SUCCESS;
    }
    
    /**
     * 检查登录 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String checkLogin()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(user))
        {
            result = new AjaxResult(true, "已登录");
        }
        else
        {
            result = new AjaxResult(false, "未登录");
        }
        return SUCCESS;
    }
    
    /**
     * 更新用户信息 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String savePersonInfo()
    {
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        
        User user = userService.selectById(userId);
        user.setSex(sex);
        user.setNickName(CommonUtils.filterDangerString(nickName));
        if (CommonUtils.isNotEmptyOrNull(birthdateYear) && CommonUtils.isNotEmptyOrNull(birthdateMonth)
            && CommonUtils.isNotEmptyOrNull(birthdateDay))
        {
            String birthDateStr = birthdateYear + "-" + birthdateMonth + "-" + birthdateDay;
            Integer birthDate = CommonUtils.getTimeStamp(birthDateStr);
            user.setBirthDate(birthDate);
        }
        if (CommonUtils.isNotEmptyOrNull(constellation))
        {
            user.setConstellation(constellation);
        }
        if (CommonUtils.isNotEmptyOrNull(homeProvinceId) && CommonUtils.isNotEmptyOrNull(homeCityId)
            && CommonUtils.isNotEmptyOrNull(homeCountyId))
        {
            String homeStr = homeProvinceId + "," + homeCityId + "," + homeCountyId;
            user.setHomeStr(homeStr);
        }
        else
        {
            user.setHomeStr("");
        }
        if (CommonUtils.isNotEmptyOrNull(residenceProvinceId) && CommonUtils.isNotEmptyOrNull(residenceCityId)
            && CommonUtils.isNotEmptyOrNull(residenceCountyId))
        {
            String residenceStr = residenceProvinceId + "," + residenceCityId + "," + residenceCountyId;
            user.setResidenceStr(residenceStr);
        }
        else
        {
            user.setResidenceStr("");
        }
        try
        {
            if (userService.updateEntity(user) > 0)
            {
                user = userService.getUserInfo(user.getHuiNo());
                result = new AjaxResult(true, user);
            }
            else
            {
                result = new AjaxResult(true, "fail");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(true, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 更新用户学习信息 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String saveStudyInfo()
    {
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        
        User user = userService.selectById(userId);
        if (CommonUtils.isNotEmptyOrNull(gradeId))
        {
            user.setGradeId(gradeId);
        }
        if (CommonUtils.isNotEmptyOrNull(schoolRegionProvinceId) && CommonUtils.isNotEmptyOrNull(schoolRegionCityId)
            && CommonUtils.isNotEmptyOrNull(schoolRegionCountyId))
        {
            String schoolRegionStr = schoolRegionProvinceId + "," + schoolRegionCityId + "," + schoolRegionCountyId;
            user.setSchoolRegionStr(schoolRegionStr);
        }
        else
        {
            user.setSchoolRegionStr("");
        }
        //过滤非法词
        if (CommonUtils.isNotEmptyOrNull(school))
        {
            List<InvKeywords> invKeywordss = CommonUtils.getInvKeywordss();
            for (InvKeywords invKeywords : invKeywordss)
            {
                if (school.indexOf(invKeywords.getValue()) > -1)
                {
                    school = school.replaceAll(invKeywords.getValue(), "***");
                }
            }
        }
        user.setSchool(CommonUtils.filterDangerString(school));
        try
        {
            if (userService.updateEntity(user) > 0)
            {
                user = userService.getUserInfo(user.getHuiNo());
                result = new AjaxResult(true, user);
            }
            else
            {
                result = new AjaxResult(true, "fail");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(true, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 检查昵称是否存在 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String checkNameExists()
    {
        try
        {
            User user = userService.selectById(userId);
            if (userService.isNicknameExists(nickName) && !nickName.equals(user.getNickName()))
            {
                result = new AjaxResult(true, "exists");
            }
            else
            {
                result = new AjaxResult(false, "notExists");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 添加关注 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String follow()
    {
        User user = (User)getRequest().getSession().getAttribute("user");
        String huiNo = user.getHuiNo();
        try
        {
            if (!huiNo.endsWith(targetHuiNo))
            {
                userFollowService.addUserFollow(user.getHuiNo(), targetHuiNo);
                result = new AjaxResult(true, userService.assembleFansToo(user.getHuiNo(), targetHuiNo) + "");
            }
            else
            {
                result = new AjaxResult(false, "不能关注自己哦~");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 取消关注 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String unFollow()
    {
        User user = (User)getRequest().getSession().getAttribute("user");
        try
        {
            userFollowService.deleteUserFollow(user.getHuiNo(), targetHuiNo);
            result = new AjaxResult(true, "success");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 检查是否是自己
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String isMe()
    {
        User user = (User)getRequest().getSession().getAttribute("user");
        String huiNo = user.getHuiNo();
        try
        {
            if (!huiNo.endsWith(targetHuiNo))
            {
                result = new AjaxResult(true, "success");
            }
            else
            {
                result = new AjaxResult(false, "不能关注自己哦~");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "error");
        }
        return SUCCESS;
    }
    
    /**
     * 读取消息 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String readMsg()
    {
        User user = (User)getRequest().getSession().getAttribute("user");
        if (messageService.readMsg(msgIdParam, user.getHuiNo()) > 0)
        {
            result = new AjaxResult(true, "success");
        }
        else
        {
            result = new AjaxResult(false, "fail");
        }
        return SUCCESS;
    }
    
    /**
     * 发送短信码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String sendPhoneMsg()
    {
        try
        {
            //    		String phoneCode = CommonUtils.randomInt(6);
            String phoneCode = "1234";
            SendSMSMessage.send(mobile, phoneCode);
            getRequest().getSession().setAttribute("phoneCode", phoneCode);
            userService.removePhoneCode(this.getRequest().getSession());
            result = new AjaxResult(true, "success");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "fail");
        }
        return SUCCESS;
    }
    
    /**
     * 搜索教师
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryTeachers()
    {
        List<TeacherInfo> teacherInfos =
            teacherInfoServiceImpl.getTeacherInfos(null, null, null, subIdParam, gradeIdParam);
        if (CommonUtils.isNotEmptyOrNull(teacherInfos))
        {
            result = new AjaxResult(true, teacherInfos);
        }
        else
        {
            result = new AjaxResult(false, "没有记录！");
        }
        return SUCCESS;
    }
    
    /**
     * 登录
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String login()
    {
        String vali =
            (String)getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        
        if (StringUtils.isBlank(this.valiCode) || StringUtils.isBlank(vali) || !this.valiCode.equals(vali))
        {
            result = new AjaxResult(false, "提示：您输入的验证码有错误！");
            return SUCCESS;
        }
        User userParam = new User();
        /*非空判断*/
        if (CommonUtils.isEmptyOrNull(nickName))
        {
            result = new AjaxResult(false, "提示：请输入昵称！");
            return SUCCESS;
        }
        if (CommonUtils.isEmptyOrNull(userPwd))
        {
            result = new AjaxResult(false, "提示：请输入密码！");
            return SUCCESS;
        }
        userParam.setUserPwd(MD5Encrypt.encode(userPwd));
        /*判断是否为手机号码*/
        if (RegUtils.isPhone(nickName))
        {
            userParam.setMobile(nickName);
            userParam.setNickName(null);
        }
        else
        {
            userParam.setNickName(nickName);
            userParam.setMobile(null);
        }
        List<User> users = userService.selectByKey(userParam);
        User user = null;
        if (CommonUtils.isNotEmptyOrNull(users))
        {
            user = users.get(0);
        }
        
        if (null != user)
        {
            if (CommonUtils.isNotEmptyOrNullOr0OrFalse(remembers) && remembers == 1)
            {
                CookieHandler.deleteCookie(getRequest(), getResponse(), FrontUtils.LOGIN_KEY);
                CookieHandler.deleteCookie(getRequest(), getResponse(), FrontUtils.LOGIN_PWD);
                Hashtable<String, String> nameValues = new Hashtable<String, String>();
                nameValues.put(FrontUtils.LOGIN_KEY, user.getNickName());
                nameValues.put(FrontUtils.LOGIN_PWD, user.getUserPwd());
                CookieHandler.createCookie(getResponse(), nameValues, 15);
            }
            this.getRequest().getSession().setAttribute("user", user);
            userService.presentBean4Login1Day(user);//一天登录一次赠送汇豆
            user.setUserPwd("");
            Integer unReadNum = messageService.unReadNum(user.getHuiNo());//未读消息数量
            Map<String, Object> userInfo = new HashMap<String, Object>();
            userInfo.put("user", user);
            userInfo.put("unReadNum", unReadNum);
            getRequest().getSession().setAttribute("unReadNum", unReadNum);
            result = new AjaxResult(true, userInfo);
            return SUCCESS;
        }
        else
        {
            result = new AjaxResult(false, "提示：您输入的帐号或密码有错误！");
            return SUCCESS;
        }
    }
    
}