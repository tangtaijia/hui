package com.hui.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AdminRole;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysAuth;
import com.hui.common.entity.SysRole;
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
public class AjaxSysRoleAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = -8564145672258825425L;
    
    private ISysRoleService sysRoleService;
    
    private Integer roleId;
    
    private String roleCode;
    
    private String roleName;
    
    private Integer roleType;
    
    private String notes;
    
    private String delIds;
    
    private String adminrole;
    
    private String menuIds;
    
    private AjaxResult result;
    
    private String rows;
    
    private String page;
    
    public void setSysRoleService(ISysRoleService sysRoleService)
    {
        this.sysRoleService = sysRoleService;
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
    
    public String getRoleName()
    {
        return roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
    public Integer getRoleType()
    {
        return roleType;
    }
    
    public void setRoleType(Integer roleType)
    {
        this.roleType = roleType;
    }
    
    public String getNotes()
    {
        return notes;
    }
    
    public void setNotes(String notes)
    {
        this.notes = notes;
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
    
    public String getMenuIds()
    {
        return menuIds;
    }
    
    public void setMenuIds(String menuIds)
    {
        this.menuIds = menuIds;
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
    
    public String toget()
    {
        try
        {
            SysRole ri = sysRoleService.selectById(roleId);
            if (null != ri)
            {
                AdminRole ar = new AdminRole();
                ar.setRoleId(roleId);
                
                result = new AjaxResult(true, sysRoleService.selectAdminRole(ar), ri);
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
        
        SysRole ri = new SysRole();
        ri.setRoleType(roleType);
        ri.setStart(start);
        ri.setSize(size);
        
        result = new AjaxResult(sysRoleService.getCount(ri), sysRoleService.list(ri));
        
        return SUCCESS;
    }
    
    public String toall()
    {
        SysRole ri = new SysRole();
        ri.setRoleType(roleType);
        
        result = new AjaxResult(true, sysRoleService.list(ri));
        return SUCCESS;
    }
    
    public String toauth()
    {
        try
        {
            SysRole ri = sysRoleService.selectById(roleId);
            if (null != ri)
            {
                List<Integer> rIds = new ArrayList<Integer>();
                rIds.add(roleId);
                sysRoleService.deleteRoleAuth(rIds);
                
                if (!StringUtils.isBlank(menuIds))
                {
                    SysAuth ai = null;
                    String[] idArray = StringUtils.split(menuIds, ',');
                    for (String id : idArray)
                    {
                        ai = new SysAuth();
                        ai.setRoleId(roleId);
                        ai.setMenuId(Integer.parseInt(StringUtils.trim(id)));
                        sysRoleService.saveSysAuth(ai);
                    }
                }
                
                result = new AjaxResult(true, "权限设置成功");
            }
            else
            {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "权限设置失败");
        }
        
        return SUCCESS;
    }
    
    public String toadmin()
    {
        try
        {
            SysRole ri = sysRoleService.selectById(roleId);
            if (null != ri)
            {
                List<Integer> rIds = new ArrayList<Integer>();
                rIds.add(roleId);
                sysRoleService.deleteRoleAdmin(rIds);
                
                if (!StringUtils.isBlank(adminrole))
                {
                    AdminRole ar = null;
                    String[] idArray = StringUtils.split(adminrole, ',');
                    for (String id : idArray)
                    {
                        ar = new AdminRole();
                        ar.setRoleId(roleId);
                        ar.setAdminId(Integer.parseInt(StringUtils.trim(id)));
                        sysRoleService.saveAdminRole(ar);
                    }
                }
                
                result = new AjaxResult(true, "管理员分配成功");
            }
            else
            {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "管理员分配失败");
        }
        
        return SUCCESS;
    }
    
    public String tocreate()
    {
        try
        {
            SysRole ri = new SysRole();
            ri.setRoleCode(roleCode);
            ri.setRoleName(roleName);
            ri.setRoleType(roleType);
            ri.setNotes(notes);
            
            sysRoleService.saveEntity(ri);
            
            result = new AjaxResult(true, "新增角色成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "新增角色失败");
        }
        
        return SUCCESS;
    }
    
    public String tomodify()
    {
        try
        {
            SysRole ri = sysRoleService.selectById(roleId);
            if (null != ri)
            {
                ri.setRoleCode(roleCode);
                ri.setRoleName(roleName);
                ri.setNotes(notes);
                
                sysRoleService.updateEntity(ri);
                
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
            sysRoleService.delete(ids);
            sysRoleService.deleteRoleAdmin(ids);
            sysRoleService.deleteRoleAuth(ids);
            
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
    
}