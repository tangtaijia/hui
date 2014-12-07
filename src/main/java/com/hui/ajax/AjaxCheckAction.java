package com.hui.ajax;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Admin;
import com.hui.common.entity.SysRole;
import com.hui.common.entity.User;
import com.hui.common.service.IAdminService;
import com.hui.common.service.ISysRoleService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxCheckAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 5225907143928906350L;
    
    private ISysRoleService sysRoleService;
    
    private IUserService userService;
    
    private IAdminService adminService;
    
    private Integer userId;
    
    private String huiNo;
    
    private String nickName;
    
    private Integer adminId;
    
    private String adminName;
    
    private String mobile;
    
    private Integer roleId;
    
    private String roleCode;
    
    private boolean result = false;
    
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
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    public Integer getAdminId()
    {
        return adminId;
    }
    
    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
    }
    
    public String getAdminName()
    {
        return adminName;
    }
    
    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public Integer getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
    
    public String getRoleCode()
    {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }
    
    public boolean isResult()
    {
        return result;
    }
    
    public void setSysRoleService(ISysRoleService sysRoleService)
    {
        this.sysRoleService = sysRoleService;
    }
    
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }
    
    public void setAdminService(IAdminService adminService)
    {
        this.adminService = adminService;
    }
    
    public String huino()
    {
        User user = new User();
        user.setUserId(userId);
        user.setHuiNo(huiNo);
        
        if (StringUtils.isNotBlank(huiNo) && CommonUtils.isEmptyOrNull(userService.selectByKey(user)))
        {
            result = true;
        }
        
        return SUCCESS;
    }
    
    public String nickname()
    {
        User user = new User();
        user.setUserId(userId);
        user.setNickName(nickName);
        if (CommonUtils.isNotEmptyOrNull(nickName) && CommonUtils.isEmptyOrNull(this.userService.selectByKey(user)))
        {
            result = true;
        }
        return SUCCESS;
    }
    
    public String usermobile()
    {
        User user = new User();
        user.setUserId(userId);
        user.setMobile(mobile);
        List<User> users = userService.selectByKey(user);
        if (StringUtils.isNotBlank(mobile) && CommonUtils.isEmptyOrNull(users))
        {
            result = true;
        }
        return SUCCESS;
    }
    
    public String adminname()
    {
        Admin ai = new Admin();
        ai.setAdminId(adminId);
        ai.setAdminName(adminName);
        
        if (StringUtils.isNotBlank(adminName) && CommonUtils.isEmptyOrNull(adminService.selectByKey(ai)))
        {
            result = true;
        }
        
        return SUCCESS;
    }
    
    public String adminmobile()
    {
        Admin ai = new Admin();
        ai.setAdminId(adminId);
        ai.setMobile(mobile);
        
        if (StringUtils.isNotBlank(mobile) && CommonUtils.isEmptyOrNull(adminService.selectByKey(ai)))
        {
            result = true;
        }
        
        return SUCCESS;
    }
    
    public String rolecode()
    {
        SysRole ri = new SysRole();
        ri.setRoleId(roleId);
        ri.setRoleCode(roleCode);
        
        if (StringUtils.isNotBlank(roleCode) && CommonUtils.isEmptyOrNull(sysRoleService.selectByKey(ri)))
        {
            result = true;
        }
        
        return SUCCESS;
    }
    
}