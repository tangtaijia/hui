package com.hui.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Admin;
import com.hui.common.entity.AdminRole;
import com.hui.common.entity.AjaxResult;
import com.hui.common.service.IAdminService;
import com.hui.common.service.ISysRoleService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.MD5Encrypt;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxAdminAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = -7955124202461531997L;
    
    private IAdminService adminService;
    
    private ISysRoleService sysRoleService;
    
    private AjaxResult result;
    
    private String rows;
    
    private String page;
    
    private Integer adminId;
    
    private String adminName;
    
    private String adminPwd;
    
    private String nickName;
    
    private String mobile;
    
    private String qryAdminName;
    
    private String qryNickName;
    
    private Integer qryStatus;
    
    private String delIds;
    
    private String adminrole;
    
    public void setAdminService(IAdminService adminService)
    {
        this.adminService = adminService;
    }
    
    public void setSysRoleService(ISysRoleService sysRoleService)
    {
        this.sysRoleService = sysRoleService;
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
    
    public String getAdminPwd()
    {
        return adminPwd;
    }
    
    public void setAdminPwd(String adminPwd)
    {
        this.adminPwd = adminPwd;
    }
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getQryAdminName()
    {
        return qryAdminName;
    }
    
    public void setQryAdminName(String qryAdminName)
    {
        this.qryAdminName = qryAdminName;
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
    
    public String getAdminrole()
    {
        return adminrole;
    }
    
    public void setAdminrole(String adminrole)
    {
        this.adminrole = adminrole;
    }
    
    public String toget()
    {
        try
        {
            Admin admin = adminService.selectById(adminId);
            if (null != admin)
            {
                admin.setStatusStr((String)CommonUtils.getSysData("status")
                    .getValue()
                    .get(Integer.toString(admin.getStatus())));
                
                AdminRole ar = new AdminRole();
                ar.setAdminId(adminId);
                
                result = new AjaxResult(true, sysRoleService.selectAdminRole(ar), admin);
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
        // 每页的开始记录  第一页为1  第二页为number +1    
        int start = (intPage - 1) * size;
        
        Admin ai = new Admin();
        ai.setAdminName(StringUtils.isBlank(qryAdminName) ? null : qryAdminName);
        ai.setNickName(StringUtils.isBlank(qryNickName) ? null : qryNickName);
        ai.setStatus(qryStatus);
        ai.setStart(start);
        ai.setSize(size);
        
        List<Admin> admins = adminService.list(ai);
        if (null != admins && 0 != admins.size())
        {
            for (Admin admin : admins)
            {
                admin.setStatusStr((String)CommonUtils.getSysData("status")
                    .getValue()
                    .get(Integer.toString(admin.getStatus())));
            }
        }
        
        result = new AjaxResult(adminService.getCount(ai), admins);
        
        return SUCCESS;
    }
    
    public String toall()
    {
        result = new AjaxResult(true, adminService.list());
        return SUCCESS;
    }
    
    public String torole()
    {
        try
        {
            Admin admin = adminService.selectById(adminId);
            if (null != admin)
            {
                // 为了避免脏数据
                List<Integer> aIds = new ArrayList<Integer>();
                aIds.add(adminId);
                sysRoleService.deleteAdminRole(aIds);
                
                if (!StringUtils.isBlank(adminrole))
                {
                    AdminRole ar = null;
                    String[] idArray = StringUtils.split(adminrole, ',');
                    for (String id : idArray)
                    {
                        ar = new AdminRole();
                        ar.setAdminId(adminId);
                        ar.setRoleId(Integer.parseInt(StringUtils.trim(id)));
                        sysRoleService.saveAdminRole(ar);
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
            Admin ai = new Admin();
            ai.setAdminName(adminName);
            ai.setAdminPwd(MD5Encrypt.encode(adminPwd));
            ai.setNickName(nickName);
            if (!StringUtils.isBlank(mobile))
            {
                ai.setMobile(mobile);
            }
            
            adminService.saveEntity(ai);
            
            result = new AjaxResult(true, "新增管理员成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "新增管理员失败");
        }
        
        return SUCCESS;
    }
    
    public String tomodify()
    {
        try
        {
            Admin admin = adminService.selectById(adminId);
            if (null != admin)
            {
                if (!StringUtils.isBlank(adminPwd))
                {
                    admin.setAdminPwd(MD5Encrypt.encode(adminPwd));
                }
                admin.setNickName(nickName);
                if (!StringUtils.isBlank(mobile))
                {
                    admin.setMobile(mobile);
                }
                adminService.updateEntity(admin);
                
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
    
    public String tomaintain()
    {
        try
        {
            Admin admin = adminService.selectById(adminId);
            if (null != admin)
            {
                if (!StringUtils.isBlank(adminPwd))
                {
                    admin.setAdminPwd(MD5Encrypt.encode(adminPwd));
                }
                admin.setNickName(nickName);
                admin.setMobile(mobile);
                adminService.updateEntity(admin);
                
                Admin loginAdmin = (Admin)this.getRequest().getSession().getAttribute("loginAdmin");
                admin.setProfile(loginAdmin.getProfile());
                
                this.getRequest().getSession().setAttribute("loginAdmin", admin);
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
            adminService.modifyStatus(0, ids);
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
            adminService.modifyStatus(1, ids);
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
            adminService.modifyStatus(2, ids);
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
    
}