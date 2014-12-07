package com.hui.back;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.ModelDrivenActionSupport;
import com.hui.common.entity.Admin;
import com.hui.common.entity.SysFile;
import com.hui.common.service.IAdminService;
import com.hui.common.service.ISysFileService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.LogUtils;
import com.hui.common.utils.MD5Encrypt;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LoginAdminAction extends ModelDrivenActionSupport<Admin>
{
    
    private static final long serialVersionUID = -8977007990964445593L;
    
    private IAdminService adminService;
    
    private ISysFileService sysFileService;
    
    public void setAdminService(IAdminService adminService)
    {
        this.adminService = adminService;
    }
    
    public void setSysFileService(ISysFileService sysFileService)
    {
        this.sysFileService = sysFileService;
    }
    
    public String execute()
    {
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        
        int times = 0;
        int login_fail_maxtimes = CommonUtils.getLoginFailMaxTimes();
        String login_fail_times = (String)this.getRequest().getSession().getAttribute("login_fail_times");
        if (StringUtils.isNotBlank(login_fail_times))
        {
            times = Integer.parseInt(login_fail_times);
        }
        // 锁用户
        if (times == login_fail_maxtimes)
        {
            Admin temp = new Admin();
            temp.setAdminName(this.getModel().getAdminName());
            Admin result = null;
            
            List<Admin> admins = adminService.selectByKey(temp);
            if (CommonUtils.isNotEmptyOrNull(admins))
            {
                result = admins.get(0);
            }
            
            if (null != result && (0 == result.getStatus()))
            {
                Calendar c = Calendar.getInstance();
                DateFormat format = new SimpleDateFormat(CommonUtils.getLongDateFormat());
                switch (CommonUtils.getLoginFailLockDates())
                {
                    case 0:
                        c.add(Calendar.DATE, 1);
                        break;
                    case 1:
                        c.add(Calendar.DATE, 7);
                        break;
                    case 2:
                        c.add(Calendar.MONTH, 1);
                        break;
                    case 3:
                        c.add(Calendar.MONTH, 3);
                        break;
                    case 4:
                        c.add(Calendar.MONTH, 6);
                        break;
                    case 5:
                        c.add(Calendar.YEAR, 1);
                        break;
                    default:
                }
                
                result.setStatus(1);
                result.setAutoUnlock(CommonUtils.getTimeStamp(format.format(c.getTime())));
                
                adminService.updateEntity(result);
            }
        }
        if (times >= login_fail_maxtimes)
        {
            addActionError("登录失败超过" + login_fail_maxtimes + "次");
            this.getRequest().getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            
            return "backlogin";
        }
        else
        {
            if (1 == CommonUtils.getBLValicode())
            {
                String vali =
                    (String)getRequest().getSession()
                        .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
                
                if (StringUtils.isBlank(this.getModel().getValiCode()) || StringUtils.isBlank(vali)
                    || !this.getModel().getValiCode().equals(vali))
                {
                    addActionError("验证码错误");
                    this.getRequest()
                        .getSession()
                        .removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
                    
                    return "backlogin";
                }
            }
            
            this.getModel().setAdminPwd(MD5Encrypt.encode(this.getModel().getAdminPwd()));
            List<Admin> admins = adminService.selectByKey(this.getModel());
            Admin result = null;
            if (CommonUtils.isNotEmptyOrNull(admins))
            {
                result = admins.get(0);
            }
            if (null != result)
            {
                if (0 == result.getStatus())
                {
                    String profile = "/template/profile/defaultlogo.jpg";
                    
                    SysFile fi = new SysFile();
                    fi.setDataId(result.getAdminId());
                    fi.setFileType(2);
                    
                    List<SysFile> sysFiles = sysFileService.list(fi);
                    for (SysFile sysFile : sysFiles)
                    {
                        profile = sysFile.getFilePath() + "/" + sysFile.getFileName();
                    }
                    
                    result.setProfile(profile);
                    this.getRequest().getSession().setAttribute("loginAdmin", result);
                    
                    LogUtils.getInstance().log(this.getRequest(),
                        (Admin)this.getRequest().getSession().getAttribute("loginAdmin"),
                        1,
                        CommonUtils.LogLevel.INFO,
                        "登录成功",
                        this.getClass().getName(),
                        "execute");
                    
                    this.getRequest().getSession().removeAttribute("login_fail_times");
                    
                    return SUCCESS;
                }
                
                this.getRequest().getSession().setAttribute("login_fail_times", Integer.toString(++times));
                addActionError("用户已被锁定");
                this.getRequest().getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
                
                return "backlogin";
            }
            
            this.getRequest().getSession().setAttribute("login_fail_times", Integer.toString(++times));
            addActionError(this.getText("login.error"));
            
            this.getRequest().getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            
            return "backlogin";
        }
    }
    
    public String exit()
    {
        LogUtils.getInstance().log(this.getRequest(),
            (Admin)this.getRequest().getSession().getAttribute("loginAdmin"),
            1,
            CommonUtils.LogLevel.INFO,
            "退出登录",
            this.getClass().getName(),
            "exit");
        
        this.getRequest().getSession().removeAttribute("loginAdmin");
        this.getRequest().getSession().removeAttribute("loginAuths");
        this.getRequest().getSession().removeAttribute("loginMenus");
        
        return "backlogin";
    }
    
}