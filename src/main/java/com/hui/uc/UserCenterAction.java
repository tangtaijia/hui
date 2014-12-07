package com.hui.uc;

import java.util.List;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Answer;
import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.Collection;
import com.hui.common.entity.Grade;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Page;
import com.hui.common.entity.Question;
import com.hui.common.entity.User;
import com.hui.common.service.IAnswerService;
import com.hui.common.service.IBeanFlowService;
import com.hui.common.service.ICollectionService;
import com.hui.common.service.IGradeService;
import com.hui.common.service.IMessageService;
import com.hui.common.service.IQuestionService;
import com.hui.common.service.ISysFileService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.MD5Encrypt;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UserCenterAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 3520293200396311844L;
    
    private IUserService userService;
    
    private IGradeService gradeServiceImpl;
    
    private IQuestionService questionServiceImpl;
    
    private IAnswerService answerService;
    
    private IMessageService messageService;
    
    private ISysFileService sysFileService;
    
    private ICollectionService collectionService;
    
    private IBeanFlowService beanFlowService;
    
    private User userInfo;
    
    private List<Grade> grades;
    
    /**
     * 是否搜索粉丝（0学霸好友1粉丝）
     */
    private Integer searchFans = 0;
    
    /**
     * 好友Page
     */
    private Page<User> friendsPage = new Page<User>();
    
    /**
     * 昵称
     */
    private String nickNameParam;
    
    /**
     * 是否切换着搜索朋友列表
     */
    private Integer isSwitch = 0;
    
    private String imgStr;
    
    private Integer userId;
    
    /**
     * 手机验证码
     */
    private String phoneVlCode;
    
    /**
     * 图形验证码
     */
    private String mobile;
    
    /*修改密码--开始*/
    private String userPwd;//原始密码
    
    private String newPwd;
    
    private String confirmPwd;
    
    /*修改密码--结束*/
    
    /**
     * 问题分页
     */
    private Page<Question> questionPage = new Page<Question>();
    
    /**
     * 回答分页
     */
    private Page<Answer> answerPage = new Page<Answer>();
    
    /**
     * 消息分页
     */
    private Page<Msg> msgPage = new Page<Msg>();
    
    /**
     * 关注
     */
    private Page<Collection> collectionPage = new Page<Collection>();
    
    /**
     * 汇豆流水
     */
    private Page<BeanFlow> beanFlowPage = new Page<BeanFlow>();
    
    /**
     * 个人中心主页
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String home()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(user))
        {
            userInfo = userService.getUserInfo(user.getHuiNo());
            getRequest().getSession().setAttribute("user", userInfo);
            grades = gradeServiceImpl.list();
            return "home";
        }
        return "frontIndex";
    }
    
    /**
     * 好友列表
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String friends()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        if (1 == isSwitch)
        {
            friendsPage.setPage(1);
        }
        if (0 == searchFans)
        {
            friendsPage = userService.getMyFriends(friendsPage.getPage(), 12, user.getHuiNo(), nickNameParam);
        }
        else
        {
            friendsPage = userService.getMyFans(friendsPage.getPage(), 12, user.getHuiNo(), nickNameParam);
        }
        return "friends";
    }
    
    /**
     * 搜索好友
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String searchUsers()
    {
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        
        User user = (User)this.getRequest().getSession().getAttribute("user");
        nickNameParam = CommonUtils.toUTF_8(nickNameParam);
        friendsPage = userService.getUsers(friendsPage.getPage(), 12, user.getHuiNo(), nickNameParam);
        return "searchUsers";
    }
    
    /**
     * 我的提问
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String myQuestions()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        questionPage =
            questionServiceImpl.getQuestions(questionPage.getPage(),
                user.getHuiNo(),
                null,
                null,
                null,
                null,
                null,
                null);
        return "myQuestions";
    }
    
    /**
     * 我的回答
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String myAnswers()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        answerPage = answerService.getAnswers(answerPage.getPage(), user.getHuiNo(), null);
        return "myAnswers";
    }
    
    /**
     * 消息中心
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String messageCenter()
    {
        return answerMe();
    }
    
    public String myCollections()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        collectionPage = collectionService.getCollections(collectionPage.getPage(), user.getHuiNo());
        return "myCollections";
    }
    
    /**
     * 回答我的
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String answerMe()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        msgPage = messageService.getMsgs(msgPage.getPage(), userInfo.getHuiNo(), 1);
        return "answerMe";
    }
    
    /**
     * 求助我答
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String askMe()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        msgPage = messageService.getMsgs(msgPage.getPage(), userInfo.getHuiNo(), 2);
        return "askMe";
    }
    
    /**
     * 回答被采纳
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String acceptedAnswers()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        msgPage = messageService.getMsgs(msgPage.getPage(), userInfo.getHuiNo(), 3);
        return "acceptedAnswers";
    }
    
    /**
     * 系统消息
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String sysMsgs()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        msgPage = messageService.getMsgs(msgPage.getPage(), userInfo.getHuiNo(), 4);
        return "sysMsgs";
    }
    
    /**
     * 去充值
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toRecharge()
    {
        return "toRecharge";
    }
    
    /**
     * 修改头像
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String modifyIcon()
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
        sysFileService.modifyIcon(userId, imgStr);
        return "modifyIcon";
    }
    
    /**
     * 汇豆流水
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String myBeans()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        beanFlowPage = beanFlowService.getBeanFlows(beanFlowPage.getPage(), userInfo.getHuiNo());
        return "myBeans";
    }
    
    /**
     * 汇豆介绍
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String beanIntroduce()
    {
        User user = (User)this.getRequest().getSession().getAttribute("user");
        userInfo = userService.getUserInfo(user.getHuiNo());
        return "beanIntroduce";
    }
    
    ////////////修改密码 绑定/修改手机号 start////////////
    /**
     * 修改密码-跳到修改密码页 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String toResetPwd()
    {
        return "resetpwdPhone";// 验证身份
    }
    
    /**
     * 修改密码-验证手机号 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String valideCode4Reset()
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
            return "resetpwdPhone";// 验证身份
        }
        /*短信验证码非空验证*/
        if (CommonUtils.isEmptyOrNull(phoneVlCode))
        {
            getRequest().setAttribute("tipInRequest", "对不起，请输入手机手机验证码！");
            return "resetpwdPhone";// 验证身份
        }
        /* 检查该手机号是否正确 */
        User userParam = new User();
        userParam.setMobile(this.mobile);
        Integer count = userService.getCount(userParam);
        if (CommonUtils.isEmptyOrNullOr0OrFalse(count))
        {
            getRequest().setAttribute("tipInRequest", "对不起，您输入的手机号不正确！");
            return "resetpwdPhone";// 验证身份
        }
        // TODO 短信验证码
        if (!phoneVlCode.equals(super.getPhoneCode()))
        {
            getRequest().setAttribute("tipInRequest", "对不起，验证码不正确！");
            return "resetpwdPhone";// 验证身份
        }
        return "resetpwdReset";// 修改密码页
    }
    
    /**
     * 修改密码-重置密码(原密码，新密码，确认新密码) <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String resetPwd()
    {
        if (CommonUtils.isEmptyOrNull(newPwd))
        {
            getRequest().setAttribute("tipInRequest", "对不起，密码不能为空！");
            return "resetpwdReset";// 修改密码页
        }
        User user = (User)this.getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(user))
        {
            /*手机号非空验证*/
            if (CommonUtils.isEmptyOrNull(mobile))
            {
                getRequest().setAttribute("tipInRequest", "对不起，请输入手机号！");
                return "resetpwdPhone";// 验证身份
            }
            /* 检查该手机号是否正确 */
            User userParam = new User();
            userParam.setMobile(this.mobile);
            Integer count = userService.getCount(userParam);
            if (CommonUtils.isEmptyOrNullOr0OrFalse(count))
            {
                getRequest().setAttribute("tipInRequest", "对不起，您输入的手机号不正确！");
                return "resetpwdPhone";// 验证身份
            }
            if (!this.newPwd.equals(this.confirmPwd))
            {
                getRequest().setAttribute("tipInRequest", "对不起，两次输入的密码不一致！");
                return "resetpwdReset";// 修改密码页
            }
            user.setUserPwd(MD5Encrypt.encode(CommonUtils.filterDangerString(newPwd)));
            this.userService.updateEntity(user);
        }
        return "resetpwdSuccess";// 修改成功页
        
    }
    
    /**
     * 绑定手机号-跳转到绑定手机页面 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String bindPhone()
    {
        return "bindPhone";// 输入手机号页
    }
    
    /**
     * 绑定手机号-验证手机号 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String validePhone()
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
        User userInfo = (User)getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(userInfo))
        {
            /*手机号非空验证*/
            if (CommonUtils.isEmptyOrNull(mobile))
            {
                getRequest().setAttribute("tipInRequest", "对不起，请输入手机号！");
                return "bindPhone";// 输入手机号页
            }
            /* 检查该手机号是否存在 */
            User userParam = new User();
            userParam.setMobile(this.mobile);
            Integer count = userService.getCount(userParam);
            if (count > 0)
            {
                getRequest().setAttribute("tipInRequest", "对不起，您输入的手机号已被绑定！");
                return "bindPhone";// 输入手机号页
            }
        }
        return "bindPhoneCode";// 验证码填写页
    }
    
    /**
    * 绑定手机号-验证手机号验证码
    * <功能详细描述>
    * @return
    * @see [类、类#方法、类#成员]
    */
    public String valideCode4Phone()
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
        User userInfo = (User)getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(userInfo))
        {
            /*手机号非空验证*/
            if (CommonUtils.isEmptyOrNull(mobile))
            {
                getRequest().setAttribute("tipInRequest", "对不起，请输入手机号！");
                return "bindPhone";// 输入手机号页
            }
            /* 检查该手机号是否存在 */
            User user = userService.selectById(userInfo.getUserId());
            User userParam = new User();
            userParam.setMobile(this.mobile);
            Integer count = userService.getCount(userParam);
            if (count > 0)
            {
                getRequest().setAttribute("tipInRequest", "对不起，您输入的手机号已被绑定！");
                return "bindPhone";// 验证码填写页
            }
            /*短信验证码非空验证*/
            if (CommonUtils.isEmptyOrNull(phoneVlCode))
            {
                getRequest().setAttribute("tipInRequest", "对不起，请输入手机手机验证码！");
                return "bindPhoneCode";// 验证码填写页
            }
            // TODO 短信验证码
            if (!phoneVlCode.equals(super.getPhoneCode()))
            {
                getRequest().setAttribute("tipInRequest", "对不起，验证码不正确！");
                return "bindPhoneCode";// 验证码填写页
            }
            user.setMobile(CommonUtils.filterDangerString(mobile));
            userService.updateEntity(user);
        }
        return "bindSuccess";// 修改成功页
    }
    
    ////////////修改密码 绑定/修改手机号 end////////////
    
    public User getUserInfo()
    {
        return userInfo;
    }
    
    public void setUserInfo(User userInfo)
    {
        this.userInfo = userInfo;
    }
    
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }
    
    public Integer getSearchFans()
    {
        return searchFans;
    }
    
    public void setSearchFans(Integer searchFans)
    {
        this.searchFans = searchFans;
    }
    
    public Page<User> getFriendsPage()
    {
        return friendsPage;
    }
    
    public void setNickNameParam(String nickNameParam)
    {
        this.nickNameParam = nickNameParam;
    }
    
    public String getNickNameParam()
    {
        return nickNameParam;
    }
    
    public void setIsSwitch(Integer isSwitch)
    {
        this.isSwitch = isSwitch;
    }
    
    public void setGradeServiceImpl(IGradeService gradeServiceImpl)
    {
        this.gradeServiceImpl = gradeServiceImpl;
    }
    
    public void setSysFileService(ISysFileService sysFileService)
    {
        this.sysFileService = sysFileService;
    }
    
    public void setBeanFlowService(IBeanFlowService beanFlowService)
    {
        this.beanFlowService = beanFlowService;
    }
    
    public List<Grade> getGrades()
    {
        return grades;
    }
    
    public Page<Question> getQuestionPage()
    {
        return questionPage;
    }
    
    public void setQuestionServiceImpl(IQuestionService questionServiceImpl)
    {
        this.questionServiceImpl = questionServiceImpl;
    }
    
    public Page<Answer> getAnswerPage()
    {
        return answerPage;
    }
    
    public void setAnswerService(IAnswerService answerService)
    {
        this.answerService = answerService;
    }
    
    public Page<Msg> getMsgPage()
    {
        return msgPage;
    }
    
    public Page<Collection> getCollectionPage()
    {
        return collectionPage;
    }
    
    public Page<BeanFlow> getBeanFlowPage()
    {
        return beanFlowPage;
    }
    
    public void setMessageService(IMessageService messageService)
    {
        this.messageService = messageService;
    }
    
    public void setCollectionService(ICollectionService collectionService)
    {
        this.collectionService = collectionService;
    }
    
    public String getImgStr()
    {
        return imgStr;
    }
    
    public void setImgStr(String imgStr)
    {
        this.imgStr = imgStr;
    }
    
    public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    public void setPhoneVlCode(String phoneVlCode)
    {
        this.phoneVlCode = phoneVlCode;
    }
    
    public void setUserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }
    
    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }
    
    public void setConfirmPwd(String confirmPwd)
    {
        this.confirmPwd = confirmPwd;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
}