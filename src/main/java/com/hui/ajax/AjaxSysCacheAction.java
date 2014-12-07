package com.hui.ajax;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.cache.SysCacheManager;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysCache;
import com.hui.common.service.ISysCacheService;
import com.hui.common.utils.CommonUtils;

public class AjaxSysCacheAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 884233642430193399L;
    
    private ISysCacheService sysCacheService;
    
    private Integer cacheId;
    
    private Integer refreshPeriod;
    
    private AjaxResult result;
    
    private String rows;
    
    private String page;
    
    public void setSysCacheService(ISysCacheService sysCacheService)
    {
        this.sysCacheService = sysCacheService;
    }
    
    public Integer getCacheId()
    {
        return cacheId;
    }
    
    public void setCacheId(Integer cacheId)
    {
        this.cacheId = cacheId;
    }
    
    public Integer getRefreshPeriod()
    {
        return refreshPeriod;
    }
    
    public void setRefreshPeriod(Integer refreshPeriod)
    {
        this.refreshPeriod = refreshPeriod;
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
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public String toget()
    {
        try
        {
            SysCache ci = sysCacheService.selectById(cacheId);
            if (null != ci)
            {
                result = new AjaxResult(true, ci);
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
        
        SysCache ci = new SysCache();
        ci.setStart(start);
        ci.setSize(size);
        
        result = new AjaxResult(sysCacheService.getCount(ci), sysCacheService.list(ci));
        
        return SUCCESS;
    }
    
    public String tomodify()
    {
        try
        {
            SysCache ci = sysCacheService.selectById(cacheId);
            if (null != ci)
            {
                ci.setRefreshPeriod(refreshPeriod);
                
                sysCacheService.updateEntity(ci);
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
    
    public String toflush()
    {
        try
        {
            SysCache ci = sysCacheService.selectById(cacheId);
            if (null != ci)
            {
                if (CommonUtils.INDEX_CACHE_CODE.equals(ci.getCacheCode()))
                {
                    CommonUtils.setIndexRefresh(true);
                }
                SysCacheManager cm = SysCacheManager.getInstance();
                cm.refreshCache(ci.getCacheCode());
                result = new AjaxResult(true, "刷新缓存成功");
            }
            else
            {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "刷新缓存失败");
        }
        return SUCCESS;
    }
    
    public String toflushall()
    {
        try
        {
            CommonUtils.setIndexRefresh(true);
            SysCacheManager cm = SysCacheManager.getInstance();
            cm.refreshAllCache();
            result = new AjaxResult(true, "刷新全部缓存成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "刷新全部缓存失败");
        }
        return SUCCESS;
    }
    
}