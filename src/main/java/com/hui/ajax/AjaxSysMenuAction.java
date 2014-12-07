package com.hui.ajax;

import java.util.List;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.cache.SysCacheManager;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysAuth;
import com.hui.common.entity.SysMenu;
import com.hui.common.entity.ZTreeNodes;
import com.hui.common.service.ISysMenuService;
import com.hui.common.service.ISysRoleService;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxSysMenuAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 4097428061273193523L;
    
    private ISysMenuService sysMenuService;
    
    private ISysRoleService sysRoleService;
    
    private AjaxResult result;
    
    private Integer id;
    
    private Integer value;
    
    private Integer roleId;
    
    private Integer roleType;
    
    public void setSysMenuService(ISysMenuService sysMenuService)
    {
        this.sysMenuService = sysMenuService;
    }
    
    public void setSysRoleService(ISysRoleService sysRoleService)
    {
        this.sysRoleService = sysRoleService;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public Integer getValue()
    {
        return value;
    }
    
    public void setValue(Integer value)
    {
        this.value = value;
    }
    
    public Integer getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
    
    public Integer getRoleType()
    {
        return roleType;
    }
    
    public void setRoleType(Integer roleType)
    {
        this.roleType = roleType;
    }
    
    public String tolist()
    {
        SysMenu mi = new SysMenu();
        mi.setRoleType(roleType);
        
        List<SysMenu> sysMenus = sysMenuService.selectAllMenus(mi);
        for (SysMenu sysMenu : sysMenus)
        {
            sysMenu.set_parentId(sysMenu.getParentId());
        }
        result = new AjaxResult(sysMenus.size(), sysMenus);
        
        return SUCCESS;
    }
    
    public String toall()
    {
        SysCacheManager cm = SysCacheManager.getInstance();
        List<ZTreeNodes> zTreeNodes = null;
        if (roleType == 0)
        {
            zTreeNodes = cm.getAuthsAdmin();
        }
        else
        {
            zTreeNodes = cm.getAuthsUser();
        }
        
        // 获取角色下权限
        SysAuth ai = new SysAuth();
        ai.setRoleId(roleId);
        
        List<SysAuth> auths = sysRoleService.selectAllAuth(ai);
        
        for (ZTreeNodes zTreeNode : zTreeNodes)
        {
            zTreeNode.setChecked(false);
            for (SysAuth auth : auths)
            {
                if (zTreeNode.getId().equals(auth.getMenuId()))
                {
                    zTreeNode.setChecked(true);
                    break;
                }
            }
        }
        
        result = new AjaxResult(true, zTreeNodes);
        return SUCCESS;
    }
    
    public String tomodifySortOrder()
    {
        try
        {
            SysMenu mi = sysMenuService.selectById(id);
            if (null != mi)
            {
                mi.setSortOrder(value);
                
                sysMenuService.updateEntity(mi);
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
    
}