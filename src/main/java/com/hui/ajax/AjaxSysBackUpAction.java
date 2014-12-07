package com.hui.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysBackUp;
import com.hui.common.entity.SysTab;
import com.hui.common.service.ISysBackUpService;
import com.hui.common.service.ISysTabService;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxSysBackUpAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = -2065609540500762937L;
    
    private ISysBackUpService sysBackUpService;
    
    private ISysTabService sysTabService;
    
    private AjaxResult result;
    
    private String rows;
    
    private String page;
    
    private Integer qryBackupType;
    
    private Integer qryBackupForm;
    
    private Integer qryStatus;
    
    private String qrySeqId;
    
    private String qryTabName;
    
    private String qrybackupTimeFrom;
    
    private String qrybackupTimeTo;
    
    private String handTabs;
    
    private Integer backupId;
    
    public void setSysBackUpService(ISysBackUpService sysBackUpService)
    {
        this.sysBackUpService = sysBackUpService;
    }
    
    public void setSysTabService(ISysTabService sysTabService)
    {
        this.sysTabService = sysTabService;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public void setResult(AjaxResult result)
    {
        this.result = result;
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
    
    public Integer getQryBackupType()
    {
        return qryBackupType;
    }
    
    public void setQryBackupType(Integer qryBackupType)
    {
        this.qryBackupType = qryBackupType;
    }
    
    public Integer getQryBackupForm()
    {
        return qryBackupForm;
    }
    
    public void setQryBackupForm(Integer qryBackupForm)
    {
        this.qryBackupForm = qryBackupForm;
    }
    
    public Integer getQryStatus()
    {
        return qryStatus;
    }
    
    public void setQryStatus(Integer qryStatus)
    {
        this.qryStatus = qryStatus;
    }
    
    public String getQrySeqId()
    {
        return qrySeqId;
    }
    
    public void setQrySeqId(String qrySeqId)
    {
        this.qrySeqId = qrySeqId;
    }
    
    public String getQryTabName()
    {
        return qryTabName;
    }
    
    public void setQryTabName(String qryTabName)
    {
        this.qryTabName = qryTabName;
    }
    
    public String getQrybackupTimeFrom()
    {
        return qrybackupTimeFrom;
    }
    
    public void setQrybackupTimeFrom(String qrybackupTimeFrom)
    {
        this.qrybackupTimeFrom = qrybackupTimeFrom;
    }
    
    public String getQrybackupTimeTo()
    {
        return qrybackupTimeTo;
    }
    
    public void setQrybackupTimeTo(String qrybackupTimeTo)
    {
        this.qrybackupTimeTo = qrybackupTimeTo;
    }
    
    public String getHandTabs()
    {
        return handTabs;
    }
    
    public void setHandTabs(String handTabs)
    {
        this.handTabs = handTabs;
    }
    
    public Integer getBackupId()
    {
        return backupId;
    }
    
    public void setBackupId(Integer backupId)
    {
        this.backupId = backupId;
    }
    
    public String tolist()
    {
        // 当前页   
        int intPage = Integer.parseInt((null == page || "0".equals(page)) ? "1" : page);
        // 每页显示条数   
        int size = Integer.parseInt((null == rows || "0".equals(rows)) ? "10" : rows);
        // 每页的开始记录  第一页为1  第二页为number +1    
        int start = (intPage - 1) * size;
        
        Integer seqId = null;
        if (StringUtils.isNotBlank(qrySeqId))
        {
            try
            {
                seqId = Integer.parseInt(qrySeqId);
            }
            catch (Exception e)
            {
                
            }
        }
        
        SysBackUp bi = new SysBackUp();
        bi.setBackupForm(qryBackupForm);
        bi.setBackupType(qryBackupType);
        bi.setStatus(qryStatus);
        bi.setSeqId(seqId);
        bi.setTabName(StringUtils.isBlank(qryTabName) ? null : qryTabName.trim());
        bi.setBackupTimeFrom(StringUtils.isBlank(qrybackupTimeFrom) ? null
            : CommonUtils.getTimeStamp(qrybackupTimeFrom));
        bi.setBackupTimeTo(StringUtils.isBlank(qrybackupTimeTo) ? null : CommonUtils.getTimeStamp(qrybackupTimeTo));
        bi.setStart(start);
        bi.setSize(size);
        
        List<SysBackUp> sysBackUps = sysBackUpService.list(bi);
        if (null != sysBackUps && 0 != sysBackUps.size())
        {
            for (SysBackUp sysBackUp : sysBackUps)
            {
                sysBackUp.setBackupTypeStr((String)CommonUtils.getSysData("backup_type")
                    .getValue()
                    .get(Integer.toString(sysBackUp.getBackupType())));
                sysBackUp.setBackupFormStr((String)CommonUtils.getSysData("backup_form")
                    .getValue()
                    .get(Integer.toString(sysBackUp.getBackupForm())));
                sysBackUp.setStatusStr((String)CommonUtils.getSysData("backup_status")
                    .getValue()
                    .get(Integer.toString(sysBackUp.getStatus())));
                if (0 != sysBackUp.getBackupTime())
                {
                    sysBackUp.setBackupTimeStr((CommonUtils.formatTimeStamp(sysBackUp.getBackupTime(),
                        CommonUtils.getLongDateFormat())));
                }
            }
        }
        
        result = new AjaxResult(sysBackUpService.getCount(bi), sysBackUps);
        
        return SUCCESS;
    }
    
    public String totabs()
    {
        SysTab ti = new SysTab();
        ti.setStatus(0);
        
        result = new AjaxResult(true, sysTabService.list(ti));
        
        return SUCCESS;
    }
    
    public String tocreatehand()
    {
        try
        {
            if (1 == CommonUtils.getBackupHand())
            {
                List<Integer> tabIds = new ArrayList<Integer>();
                
                if (!StringUtils.isBlank(handTabs))
                {
                    String[] idArray = StringUtils.split(handTabs, ',');
                    for (String id : idArray)
                    {
                        tabIds.add(Integer.parseInt(StringUtils.trim(id)));
                    }
                    
                    sysBackUpService.createBackUpHand(tabIds);
                }
                
                result = new AjaxResult(true, "数据备份中");
            }
            else
            {
                result = new AjaxResult(false, "手动备份功能未开启");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "执行失败");
        }
        
        return SUCCESS;
    }
    
    public String torebackup()
    {
        sysBackUpService.reBackUp(backupId);
        
        result = new AjaxResult(true);
        
        return SUCCESS;
    }
    
}