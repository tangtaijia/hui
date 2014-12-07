package com.hui.front;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Grade;
import com.hui.common.entity.ImageTwo;
import com.hui.common.entity.InvKeywords;
import com.hui.common.entity.Page;
import com.hui.common.entity.Question;
import com.hui.common.entity.Subject;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.SysMsg;
import com.hui.common.entity.Trends;
import com.hui.common.entity.User;
import com.hui.common.service.IGradeService;
import com.hui.common.service.IMessageService;
import com.hui.common.service.IQuestionService;
import com.hui.common.service.ISubjectService;
import com.hui.common.service.ISysFileService;
import com.hui.common.service.ISysMsgService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.MD5Encrypt;

public class FrontAction extends BaseActionSupport
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 744601184439175402L;
    
    private IUserService userService;
    
    private IGradeService gradeServiceImpl;
    
    private ISubjectService subjectServiceImpl;
    
    private IQuestionService questionServiceImpl;
    
    private IMessageService messageService;
    
    private ISysMsgService sysMsgService;
    
    private ISysFileService sysFileService;
    
    /**
     * 年级列表
     */
    private List<Grade> grades;
    
    /**
     * 学科
     */
    private List<Subject> subjects;
    
    /**
     * 问题page
     */
    private Page<Question> questionPage = new Page<Question>();
    
    /**
     * 问题参数
     */
    private Question questionParam = new Question();
    
    /**
     * 汇答号参数
     */
    private String huiNoParam;
    
    /**
     * 他人信息
     */
    private User ta;
    
    /**
     * 动态
     */
    private List<Trends> trendses;
    
    /**
     * 系统消息
     */
    private SysMsg sysMsgInfo;
    
    /**
     * 系统消息id
     */
    private Integer sysMsgId;
    
    /*修改密码--开始*/
    private String nickName;
    
    private String userPwd;
    
    private String newPwd;
    
    private String confirmPwd;
    
    /*修改密码--结束*/
    private String resetKey;
    
    private String valiCode;
    
    /*绑定手机号--开始*/
    private String mobile;
    
    /*绑定手机号--结束*/
    private String phoneVlCode;
    
    private String info;
    
    /**
     * 首页
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String index()
    {
        //		/* 解决跨站点请求伪造 */
        //    	if(CommonUtils.isEmptyOrNull(getRequest().getHeader("Referer")) ) {
        //    		
        //    	}
        String qryStr = getRequest().getQueryString();
        if (StringUtils.isNotBlank(qryStr) && (-1 != qryStr.indexOf("questionParam.questionDesc")))
        {
            if (!getRequest().getSession()
                .getId()
                .toLowerCase()
                .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
            {
                return "404";
            }
        }
        
        User user = (User)getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(user))
        {
            user = userService.selectById(user.getUserId());
            /*加图片*/
            user = addIcon(user);
            getRequest().getSession().setAttribute("user", user);
        }
        grades = this.gradeServiceImpl.selectAllWithSubs();
        if (CommonUtils.isEmptyOrNullOr0OrFalse(questionParam.getGrade().getGradeId()))
        {
            subjects = this.subjectServiceImpl.list();
        }
        else
        {
            for (Grade grade : grades)
            {
                if (questionParam.getGrade().getGradeId().equals(grade.getGradeId()))
                {
                    subjects = grade.getSubjects();
                    break;
                }
            }
        }
        if (CommonUtils.isNotEmptyOrNull(questionParam.getQuestionDesc()))
        {
            questionParam.setQuestionDesc(CommonUtils.toUTF_8(questionParam.getQuestionDesc()));
        }
        questionPage =
            this.questionServiceImpl.getQuestions(questionPage.getPage(),
                null,
                questionParam.getQuestionDesc(),
                questionParam.getIsReward(),
                questionParam.getHasAnswer(),
                questionParam.getGrade().getGradeId(),
                questionParam.getSubject().getSubId(),
                0);
        return SUCCESS;
    }
    
    /**
     * 加图片
     * <功能详细描述>
     * @param user
     * @return
     * @see [类、类#方法、类#成员]
     */
    private User addIcon(User user)
    {
        SysFile sysFileParam = new SysFile();
        sysFileParam.setFileType(1);
        sysFileParam.setDataId(user.getUserId());
        List<SysFile> images = sysFileService.list(sysFileParam);
        if (CommonUtils.isNotEmptyOrNull(images))
        {
            ImageTwo imageTwo = new ImageTwo();
            for (SysFile image : images)
            {
                if (1 == image.getSeqId())
                {
                    imageTwo.setImgLt(image);
                }
                if (2 == image.getSeqId())
                {
                    imageTwo.setImgOri(image);
                }
            }
            user.setImageTwo(imageTwo);
        }
        return user;
    }
    
    /**
     * 登录页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toLogin()
    {
        return "login";
    }
    
    /**
     * 手机版页面
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String phone()
    {
        return "phone";
    }
    
    /**
     * TA的主页
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String buddyPage()
    {
        User user = (User)getRequest().getSession().getAttribute("user");
        String myHuiNo = null;
        if (CommonUtils.isNotEmptyOrNull(user))
        {
            myHuiNo = user.getHuiNo();
        }
        ta = userService.getTaInfo(huiNoParam, myHuiNo);//他人信息
        trendses = userService.getTrendses(huiNoParam, 3);//他人动态
        return "buddyPage";
    }
    
    /**
     * 系统消息详情页
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String sysMsgDetail()
    {
        sysMsgInfo = sysMsgService.getSysMsgDetail(sysMsgId);
        return "sysMsgDetail";
    }
    
    /**
     * 退出再登录
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String logOutToLogIn()
    {
        this.getRequest().getSession().removeAttribute("user");
        this.getRequest().getSession().setAttribute("isLogin", "false");
        return "frontIndex";
    }
    
    ////////////start////////////
    /**
     * 注册-跳到注册页
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toRegister()
    {
        return "registerInfo";//填写账号信息
    }
    
    /**
     * 注册-验证账号信息
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String doRegister()
    {
        if (!"post".equalsIgnoreCase(getRequest().getMethod()))
        {//强制post提交
            return "404";
        }
        /* 解决跨站点请求伪造 */
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        try
        {
            String vali =
                (String)getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            
            if (StringUtils.isBlank(this.valiCode) || StringUtils.isBlank(vali) || !this.valiCode.equals(vali))
            {
                getRequest().setAttribute("tipInRequest", "验证码错误");
                return "registerInfo";//填写账号信息
            }
            User user = new User();
            user.setUserPwd(MD5Encrypt.encode(userPwd.trim()));
            user.setHuiNo(FrontUtils.generateHuiNo());
            user.setAcceptNo(0);
            user.setAnswerNo(0);
            user.setBeanNum(0);
            user.setCreateTime(CommonUtils.getCurTime());
            user.setSchool("");
            user.setIsTeacher(0);
            user.setStatus(0);
            //过滤非法词
            if (CommonUtils.isNotEmptyOrNull(nickName))
            {
                List<InvKeywords> invKeywordss = CommonUtils.getInvKeywordss();
                for (InvKeywords invKeywords : invKeywordss)
                {
                    if (nickName.indexOf(invKeywords.getValue()) > -1)
                    {
                        nickName = nickName.replaceAll(invKeywords.getValue(), "***");
                    }
                }
                user.setNickName(CommonUtils.filterDangerString(nickName.trim()));
            }
            userService.saveEntity(user);
            this.getRequest().getSession().setAttribute("user", user);
            
            return "registerPhone";//验证账号信息
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        getRequest().setAttribute("tipInRequest", getText("createuser.error"));
        return "registerInfo";//填写账号信息
    }
    
    /**
     * 注册-验证手机信息
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String valideCode4Rg()
    {
        if (!"post".equalsIgnoreCase(getRequest().getMethod()))
        {//强制post提交
            return "404";
        }
        /* 解决跨站点请求伪造 */
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        try
        {
            if (!phoneVlCode.equals(super.getPhoneCode()))
            {
                getRequest().setAttribute("tipInRequest", "对不起，验证码不正确！");
                return "registerPhone";//验证账号信息
            }
            User userParam = new User();
            userParam.setMobile(mobile);
            List<User> users = userService.selectByKey(userParam);
            if (CommonUtils.isNotEmptyOrNull(users))
            {
                getRequest().setAttribute("tipInRequest", "对不起,该手机号已被绑定！");
                return "registerPhone";//验证账号信息
            }
            User user = (User)this.getRequest().getSession().getAttribute("user");
            user.setMobile(CommonUtils.filterDangerString(mobile));
            userService.updateEntity(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "registerSuccess";//成功页面
    }
    
    /**
     * 找回密码-跳到找回密码页
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toFindPwd()
    {
        return "findpwdInfo";//输入账号页
    }
    
    /**
     * 找回密码-验证账号信息
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String doValideInfo()
    {
        if (!"post".equalsIgnoreCase(getRequest().getMethod()))
        {//强制post提交
            return "404";
        }
        /* 解决跨站点请求伪造 */
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        String vali =
            (String)getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        
        if (StringUtils.isBlank(valiCode) || StringUtils.isBlank(vali) || !valiCode.equals(vali))
        {
            getRequest().setAttribute("tipInRequest", "验证码错误！");
            return "findpwdInfo";//输入账号页
        }
        /*检查该昵称是否存在*/
        User userParam = new User();
        userParam.setNickName(CommonUtils.filterDangerString(nickName));
        List<User> users = userService.selectByKey(userParam);
        User user = null;
        if (CommonUtils.isNotEmptyOrNull(users))
        {
            user = users.get(0);
        }
        if (CommonUtils.isEmptyOrNull(user))
        {
            getRequest().setAttribute("tipInRequest", "对不起，没有此用户！");
            return "findpwdInfo";//输入账号页
        }
        return "findpwdPhone";//验证身份页
    }
    
    /**
     * 找回密码-验证手机码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String valideCode4Fw()
    {
        if (!"post".equalsIgnoreCase(getRequest().getMethod()))
        {//强制post提交
            return "404";
        }
        /* 解决跨站点请求伪造 */
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        /*手机号非空验证*/
        if (CommonUtils.isEmptyOrNull(mobile))
        {
            getRequest().setAttribute("tipInRequest", "对不起，请输入手机号！");
            return "findpwdPhone";//验证身份页
        }
        /*短信验证码非空验证*/
        if (CommonUtils.isEmptyOrNull(phoneVlCode))
        {
            getRequest().setAttribute("tipInRequest", "对不起，请输入手机手机验证码！");
            return "findpwdPhone";//验证身份页
        }
        // TODO 短信验证码
        if (!phoneVlCode.equals(super.getPhoneCode()))
        {
            getRequest().setAttribute("tipInRequest", "对不起，验证码不正确！");
            return "findpwdPhone";//验证身份页
        }
        /*检查该昵称是否存在*/
        User userParam = new User();
        userParam.setNickName(CommonUtils.filterDangerString(nickName));
        List<User> users = userService.selectByKey(userParam);
        User user = null;
        if (CommonUtils.isNotEmptyOrNull(users))
        {
            user = users.get(0);
        }
        if (CommonUtils.isEmptyOrNull(user))
        {
            getRequest().setAttribute("tipInRequest", "对不起，没有此用户！");
            return "findpwdInfo";//输入账号页
        }
        if (!mobile.equals(user.getMobile()))
        {
            getRequest().setAttribute("tipInRequest", "对不起，您输入的手机号码有误！");
            return "findpwdPhone";//验证身份页
        }
        return "findpwdReset1";//密码重置页
    }
    
    /**
     * 找回密码-重置密码
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String resetPwd()
    {
        if (!"post".equalsIgnoreCase(getRequest().getMethod()))
        {//强制post提交
            return "404";
        }
        /* 解决跨站点请求伪造 */
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        /*手机号非空验证*/
        if (CommonUtils.isEmptyOrNull(mobile))
        {
            getRequest().setAttribute("tipInRequest", "对不起，请输入手机号！");
            return "findpwdPhone";//验证身份页
        }
        /*检查该昵称是否存在*/
        User userParam = new User();
        userParam.setNickName(nickName);
        List<User> users = userService.selectByKey(userParam);
        User user = null;
        if (CommonUtils.isNotEmptyOrNull(users))
        {
            user = users.get(0);
        }
        if (CommonUtils.isEmptyOrNull(user))
        {
            getRequest().setAttribute("tipInRequest", "对不起，没有此用户！");
            return "findpwdInfo";//输入账号页
        }
        if (!mobile.equals(user.getMobile()))
        {
            getRequest().setAttribute("tipInRequest", "对不起，您输入的手机号码有误！");
            return "findpwdPhone";//验证身份页
        }
        if (!newPwd.equals(confirmPwd))
        {
            getRequest().setAttribute("tipInRequest", "对不起，两次输入的密码不一致！");
            return "findpwdReset";//密码重置页
        }
        user.setUserPwd(MD5Encrypt.encode(CommonUtils.filterDangerString(newPwd)));
        this.userService.updateEntity(user);
        return "findpwdSuccess";//找回成功页
    }
    
    ////////////end////////////
    
    /**
     * 单点登录
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String login3()
    {
        //    	if(CommonUtils.isNotEmptyOrNull(info)) {
        //			DESUtils des = new DESUtils();
        //			String infoReal = des.getDesString(info);
        //			JSONObject jsonResult = new JSONObject();
        //			jsonResult = JSON.parseObject(infoReal);
        //			System.out.println(nameParam);
        //			System.out.println(phoneParam);
        //			System.out.println(userIconParam);
        //			System.out.println(sexParam);
        //			System.out.println(birthdateParam);
        //			System.out.println(commonkeyParam);
        //			System.out.println(codeParam);
        //			System.out.println(descriptionParam);
        //			nameParam = JSON.parseObject(jsonResult.get("userinfo").toString()).get("name");
        //			nameParam = JSON.parseObject(jsonResult.get("userinfo").toString()).get("phone");
        //			nameParam = JSON.parseObject(jsonResult.get("userinfo").toString()).get("userIcon");
        //			nameParam = JSON.parseObject(jsonResult.get("userinfo").toString()).get("birthdate");
        //			nameParam = JSON.parseObject(jsonResult.get("userinfo").toString()).get("name");
        //			User user = userService.login3(0, nameParam, phoneParam, userIconParam, sexParam, birthdateParam, commonkeyParam);
        //			getRequest().getSession().setAttribute("user", user);
        //		}
        return "index";
    }
    
    public List<Grade> getGrades()
    {
        return grades;
    }
    
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }
    
    public void setSysFileService(ISysFileService sysFileService)
    {
        this.sysFileService = sysFileService;
    }
    
    public void setGradeServiceImpl(IGradeService gradeServiceImpl)
    {
        this.gradeServiceImpl = gradeServiceImpl;
    }
    
    public void setSubjectServiceImpl(ISubjectService subjectServiceImpl)
    {
        this.subjectServiceImpl = subjectServiceImpl;
    }
    
    public void setSysMsgService(ISysMsgService sysMsgService)
    {
        this.sysMsgService = sysMsgService;
    }
    
    public List<Subject> getSubjects()
    {
        return subjects;
    }
    
    public void setQuestionServiceImpl(IQuestionService questionServiceImpl)
    {
        this.questionServiceImpl = questionServiceImpl;
    }
    
    public Page<Question> getQuestionPage()
    {
        return questionPage;
    }
    
    public void setQuestionPage(Page<Question> questionPage)
    {
        this.questionPage = questionPage;
    }
    
    public Question getQuestionParam()
    {
        return questionParam;
    }
    
    public void setQuestionParam(Question questionParam)
    {
        this.questionParam = questionParam;
    }
    
    public SysMsg getSysMsgInfo()
    {
        return sysMsgInfo;
    }
    
    public void setHuiNoParam(String huiNoParam)
    {
        this.huiNoParam = huiNoParam;
    }
    
    public User getTa()
    {
        return ta;
    }
    
    public void setSysMsgId(Integer sysMsgId)
    {
        this.sysMsgId = sysMsgId;
    }
    
    public List<Trends> getTrendses()
    {
        return trendses;
    }
    
    public void setMessageService(IMessageService messageService)
    {
        this.messageService = messageService;
    }
    
    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }
    
    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    public void setConfirmPwd(String confirmPwd)
    {
        this.confirmPwd = confirmPwd;
    }
    
    public void setValiCode(String valiCode)
    {
        this.valiCode = valiCode;
    }
    
    public String getResetKey()
    {
        return resetKey;
    }
    
    public void setResetKey(String resetKey)
    {
        this.resetKey = resetKey;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public String getPhoneVlCode()
    {
        return phoneVlCode;
    }
    
    public void setPhoneVlCode(String phoneVlCode)
    {
        this.phoneVlCode = phoneVlCode;
    }
    
    public String getUserPwd()
    {
        return userPwd;
    }
    
    public String getNewPwd()
    {
        return newPwd;
    }
    
    public String getConfirmPwd()
    {
        return confirmPwd;
    }
    
    public String getInfo()
    {
        return info;
    }
    
    public void setInfo(String info)
    {
        this.info = info;
    }
    
}