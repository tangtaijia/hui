package com.hui.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysLog;
import com.hui.common.service.ISysLogService;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxSysLogAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 3783454212920721040L;
    
    private ISysLogService sysLogService;
    
    private Integer logId;
    
    private Integer qryOperatorType;
    
    private Integer qryOperateType;
    
    private Integer qryLogLevel;
    
    private String qryOperatorName;
    
    private String qryRemoteIp;
    
    private String qryLogTimeFrom;
    
    private String qryLogTimeTo;
    
    private String rows;
    
    private String page;
    
    private AjaxResult result;
    
    private String delIds;
    
    public void setSysLogService(ISysLogService sysLogService)
    {
        this.sysLogService = sysLogService;
    }
    
    public Integer getLogId()
    {
        return logId;
    }
    
    public void setLogId(Integer logId)
    {
        this.logId = logId;
    }
    
    public Integer getQryOperatorType()
    {
        return qryOperatorType;
    }
    
    public void setQryOperatorType(Integer qryOperatorType)
    {
        this.qryOperatorType = qryOperatorType;
    }
    
    public Integer getQryOperateType()
    {
        return qryOperateType;
    }
    
    public void setQryOperateType(Integer qryOperateType)
    {
        this.qryOperateType = qryOperateType;
    }
    
    public Integer getQryLogLevel()
    {
        return qryLogLevel;
    }
    
    public void setQryLogLevel(Integer qryLogLevel)
    {
        this.qryLogLevel = qryLogLevel;
    }
    
    public String getQryOperatorName()
    {
        return qryOperatorName;
    }
    
    public void setQryOperatorName(String qryOperatorName)
    {
        this.qryOperatorName = qryOperatorName;
    }
    
    public String getQryRemoteIp()
    {
        return qryRemoteIp;
    }
    
    public void setQryRemoteIp(String qryRemoteIp)
    {
        this.qryRemoteIp = qryRemoteIp;
    }
    
    public String getQryLogTimeFrom()
    {
        return qryLogTimeFrom;
    }
    
    public void setQryLogTimeFrom(String qryLogTimeFrom)
    {
        this.qryLogTimeFrom = qryLogTimeFrom;
    }
    
    public String getQryLogTimeTo()
    {
        return qryLogTimeTo;
    }
    
    public void setQryLogTimeTo(String qryLogTimeTo)
    {
        this.qryLogTimeTo = qryLogTimeTo;
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
    
    public String getDelIds()
    {
        return delIds;
    }
    
    public void setDelIds(String delIds)
    {
        this.delIds = delIds;
    }
    
    public String toget()
    {
        try
        {
            SysLog sysLog = sysLogService.selectById(logId);
            if (null != sysLog)
            {
                sysLog.setOperatorTypeStr((String)CommonUtils.getSysData("operator_type")
                    .getValue()
                    .get(Integer.toString(sysLog.getOperatorType())));
                sysLog.setOperateTypeStr((String)CommonUtils.getSysData("operate_type")
                    .getValue()
                    .get(Integer.toString(sysLog.getOperateType())));
                sysLog.setLogLevelStr((String)CommonUtils.getSysData("log_level")
                    .getValue()
                    .get(Integer.toString(sysLog.getLogLevel())));
                if (0 != sysLog.getLogTime())
                {
                    sysLog.setLogTimeStr((CommonUtils.formatTimeStamp(sysLog.getLogTime(),
                        CommonUtils.getLongDateFormat())));
                }
                
                result = new AjaxResult(true, sysLog);
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
        
        SysLog li = new SysLog();
        li.setOperatorType(qryOperatorType);
        li.setOperateType(qryOperateType);
        li.setLogLevel(qryLogLevel);
        li.setOperatorName(StringUtils.isBlank(qryOperatorName) ? null : qryOperatorName.trim());
        li.setRemoteIp(StringUtils.isBlank(qryRemoteIp) ? null : qryRemoteIp.trim());
        li.setLogTimeFrom(StringUtils.isBlank(qryLogTimeFrom) ? null : CommonUtils.getTimeStamp(qryLogTimeFrom));
        li.setLogTimeTo(StringUtils.isBlank(qryLogTimeTo) ? null : CommonUtils.getTimeStamp(qryLogTimeTo));
        li.setStart(start);
        li.setSize(size);
        
        List<SysLog> logs = sysLogService.list(li);
        if (null != logs && 0 != logs.size())
        {
            for (SysLog log : logs)
            {
                log.setOperatorTypeStr((String)CommonUtils.getSysData("operator_type")
                    .getValue()
                    .get(Integer.toString(log.getOperatorType())));
                log.setOperateTypeStr((String)CommonUtils.getSysData("operate_type")
                    .getValue()
                    .get(Integer.toString(log.getOperateType())));
                log.setLogLevelStr((String)CommonUtils.getSysData("log_level")
                    .getValue()
                    .get(Integer.toString(log.getLogLevel())));
                if (0 != log.getLogTime())
                {
                    log.setLogTimeStr((CommonUtils.formatTimeStamp(log.getLogTime(), CommonUtils.getLongDateFormat())));
                }
            }
        }
        
        result = new AjaxResult(sysLogService.getCount(li), logs);
        
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
            sysLogService.delete(ids);
            
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
    
    public String todelall()
    {
        try
        {
            sysLogService.deleteAll();
            
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
    
}