package com.hui.ajax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Admin;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysConfig;
import com.hui.common.service.ISysConfigService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.LogUtils;

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
public class AjaxSysCfgAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 2313997766032315176L;
    
    private ISysConfigService sysConfigService;
    
    private AjaxResult result;
    
    private Integer configId;
    
    private String configValue;
    
    private String rows;
    
    private String page;
    
    public void setSysConfigService(ISysConfigService sysConfigService)
    {
        this.sysConfigService = sysConfigService;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public Integer getConfigId()
    {
        return configId;
    }
    
    public void setConfigId(Integer configId)
    {
        this.configId = configId;
    }
    
    public String getConfigValue()
    {
        return configValue;
    }
    
    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
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
            SysConfig si = sysConfigService.selectById(configId);
            if (null != si)
            {
                if ("date".equals(si.getValueType()) && StringUtils.isNotBlank(si.getConfigValue()))
                {
                    si.setConfigValue(CommonUtils.formatTimeStamp(Integer.parseInt(si.getConfigValue()),
                        CommonUtils.getShortDateFormat()));
                }
                if ("datetime".equals(si.getValueType()) && StringUtils.isNotBlank(si.getConfigValue()))
                {
                    si.setConfigValue(CommonUtils.formatTimeStamp(Integer.parseInt(si.getConfigValue()),
                        CommonUtils.getLongDateFormat()));
                }
                if ("array".equals(si.getValueType()))
                {
                    si.setConfigValue((String)CommonUtils.getSysData(si.getConfigCode())
                        .getValue()
                        .get(si.getConfigValue()));
                }
                result = new AjaxResult(true, si);
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
    
    public String togetmodify()
    {
        try
        {
            SysConfig si = sysConfigService.selectById(configId);
            if (null != si)
            {
                if ("date".equals(si.getValueType()) && StringUtils.isNotBlank(si.getConfigValue()))
                {
                    si.setConfigValue(CommonUtils.formatTimeStamp(Integer.parseInt(si.getConfigValue()),
                        CommonUtils.getShortDateFormat()));
                }
                if ("datetime".equals(si.getValueType()) && StringUtils.isNotBlank(si.getConfigValue()))
                {
                    si.setConfigValue(CommonUtils.formatTimeStamp(Integer.parseInt(si.getConfigValue()),
                        CommonUtils.getLongDateFormat()));
                }
                result = new AjaxResult(true, CommonUtils.getSysDataArray(si.getConfigCode()), si);
            }
            else
            {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
        
        SysConfig si = new SysConfig();
        si.setStart(start);
        si.setSize(size);
        
        List<SysConfig> sysConfigs = sysConfigService.list(si);
        if (null != sysConfigs && 0 != sysConfigs.size())
        {
            for (SysConfig sysConfig : sysConfigs)
            {
                if ("date".equals(sysConfig.getValueType()) && StringUtils.isNotBlank(sysConfig.getConfigValue()))
                {
                    sysConfig.setConfigValue(CommonUtils.formatTimeStamp(Integer.parseInt(sysConfig.getConfigValue()),
                        CommonUtils.getShortDateFormat()));
                }
                if ("datetime".equals(sysConfig.getValueType()) && StringUtils.isNotBlank(sysConfig.getConfigValue()))
                {
                    sysConfig.setConfigValue(CommonUtils.formatTimeStamp(Integer.parseInt(sysConfig.getConfigValue()),
                        CommonUtils.getLongDateFormat()));
                }
                if ("array".equals(sysConfig.getValueType()))
                {
                    sysConfig.setConfigValue((String)CommonUtils.getSysData(sysConfig.getConfigCode())
                        .getValue()
                        .get(sysConfig.getConfigValue()));
                }
            }
        }
        
        result = new AjaxResult(sysConfigService.getCount(si), sysConfigs);
        
        return SUCCESS;
    }
    
    @SuppressWarnings("deprecation")
    public String tomodify()
    {
        try
        {
            SysConfig si = sysConfigService.selectById(configId);
            if (null != si)
            {
                if ("date".equals(si.getValueType()) || "datetime".equals(si.getValueType()))
                {
                    si.setConfigValue(StringUtils.isBlank(configValue) ? "" : CommonUtils.getTimeStamp(configValue)
                        .toString());
                }
                else
                {
                    si.setConfigValue(configValue);
                }
                
                sysConfigService.updateEntity(si);
                
                Properties properties = new Properties();
                String logFileName = this.getRequest().getRealPath("/WEB-INF/classes/resources/log4j.properties");
                InputStream is = null;
                OutputStream os = null;
                
                if ("file_path".equalsIgnoreCase(si.getConfigCode()))
                {
                    try
                    {
                        is = new FileInputStream(logFileName);
                        properties.load(is);
                        IOUtils.closeQuietly(is);
                        
                        properties.setProperty("log4j.appender.file.File", configValue + File.separator + "hui.log");
                        
                        os = new FileOutputStream(logFileName);
                        properties.store(os, "");
                        IOUtils.closeQuietly(os);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else if ("file_max_size".equalsIgnoreCase(si.getConfigCode()))
                {
                    try
                    {
                        is = new FileInputStream(logFileName);
                        properties.load(is);
                        IOUtils.closeQuietly(is);
                        
                        properties.setProperty("log4j.appender.file.MaxFileSize", configValue + "MB");
                        
                        os = new FileOutputStream(logFileName);
                        properties.store(os, "");
                        IOUtils.closeQuietly(os);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                
                LogUtils.getInstance().log(this.getRequest(),
                    (Admin)this.getRequest().getSession().getAttribute("loginAdmin"),
                    2,
                    CommonUtils.LogLevel.INFO,
                    si.toString(),
                    this.getClass().getName(),
                    "tomodify");
                
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