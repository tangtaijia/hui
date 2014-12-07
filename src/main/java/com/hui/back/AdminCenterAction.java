package com.hui.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.cache.SysCacheManager;
import com.hui.common.entity.Admin;
import com.hui.common.entity.AdminRole;
import com.hui.common.entity.SysMenu;
import com.hui.common.entity.ZTreeNodes;
import com.hui.common.service.ISysMenuService;
import com.hui.common.service.ISysRoleService;
import com.hui.common.utils.JacksonUtils;

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
public class AdminCenterAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = -7916045454038666445L;
    
    private ISysRoleService sysRoleService;
    
    private ISysMenuService sysMenuService;
    
    private Admin adminInfo;
    
    public void setSysRoleService(ISysRoleService sysRoleService)
    {
        this.sysRoleService = sysRoleService;
    }
    
    public void setSysMenuService(ISysMenuService sysMenuService)
    {
        this.sysMenuService = sysMenuService;
    }
    
    public Admin getAdminInfo()
    {
        return adminInfo;
    }
    
    public void setAdminInfo(Admin adminInfo)
    {
        this.adminInfo = adminInfo;
    }
    
    public String execute()
    {
        Admin loginAdmin = (Admin)this.getRequest().getSession().getAttribute("loginAdmin");
        Map<String, String> loginAuths = new HashMap<String, String>();
        SysCacheManager cm = SysCacheManager.getInstance();
        String menus = "[]";
        
        if (null != loginAdmin)
        {
            adminInfo = loginAdmin;
            
            AdminRole ar = new AdminRole();
            ar.setAdminId(loginAdmin.getAdminId());
            List<AdminRole> adminRoles = sysRoleService.selectAdminRole(ar);
            
            if (null != adminRoles && adminRoles.size() > 0)
            {
                List<Integer> ids = new ArrayList<Integer>();
                for (AdminRole adminRole : adminRoles)
                {
                    ids.add(adminRole.getRoleId());
                }
                List<SysMenu> auths = sysRoleService.selectAllRolesAuth(ids);
                if (null != auths && auths.size() > 0)
                {
                    for (SysMenu auth : auths)
                    {
                        loginAuths.put(auth.getMenuCode(), auth.getMenuCode());
                    }
                    menus = getAdminMenus(auths);
                }
            }
            
            this.getRequest().getSession().setAttribute("loginAuths", loginAuths);
            this.getRequest().getSession().setAttribute("loginMenus", menus);
            this.getRequest().getSession().setAttribute("loginAdminOpts", cm.getAuthsAdminOpt());
            return "backindex";
        }
        
        return "backlogin";
    }
    
    public String getAdminMenus(List<SysMenu> auths)
    {
        List<ZTreeNodes> listZTreeNodes = new ArrayList<ZTreeNodes>();
        JacksonUtils ju = JacksonUtils.getInstance();
        ZTreeNodes zTreeNodes = null;
        
        SysMenu mi = new SysMenu();
        mi.setRoleType(0);
        
        List<SysMenu> listSysMenu = sysMenuService.selectAllMenus(mi);
        
        for (SysMenu sysMenu : listSysMenu)
        {
            for (SysMenu auth : auths)
            {
                if (sysMenu.getMenuId().equals(auth.getMenuId()))
                {
                    zTreeNodes = new ZTreeNodes();
                    zTreeNodes.setId(sysMenu.getMenuId());
                    zTreeNodes.setpId(sysMenu.getParentId());
                    zTreeNodes.setName(sysMenu.getMenuName());
                    zTreeNodes.setIframeUrl(sysMenu.getMenuUrl());
                    listZTreeNodes.add(zTreeNodes);
                    break;
                }
            }
        }
        
        return ju.writeEntityJSON(listZTreeNodes);
    }
    
}