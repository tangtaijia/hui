package com.hui.common.job;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.service.ISysBackUpService;
import com.hui.common.service.ISysConfigService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.LogUtils;

/**
 * 
 * 备份数据定时任务
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BackUpJob extends BaseJob
{
    
    private ISysBackUpService sysBackUpService;
    
    private ISysConfigService sysConfigService;
    
    public void setSysBackUpService(ISysBackUpService sysBackUpService)
    {
        this.sysBackUpService = sysBackUpService;
    }
    
    public void setSysConfigService(ISysConfigService sysConfigService)
    {
        this.sysConfigService = sysConfigService;
    }
    
   
    protected void doCheck()
    {
        try
        {
            // 定期备份数据：0-关闭；1-开启；
            if (1 == CommonUtils.getBackup())
            {
                LogUtils.getInstance().log(3,
                    CommonUtils.LogLevel.INFO,
                    "备份数据定时任务",
                    "定时任务启动",
                    this.getClass().getName(),
                    "doCheck");
                
                String nextBackupDate =
                    CommonUtils.formatTimeStamp(CommonUtils.getNextBackupDate(), CommonUtils.getShortDateFormat());
                String curDate = CommonUtils.getFormatCurTime(CommonUtils.getShortDateFormat());
                
                if (StringUtils.isNotBlank(nextBackupDate) && !nextBackupDate.equals(curDate))
                {
                    LogUtils.getInstance().log(3,
                        CommonUtils.LogLevel.INFO,
                        "备份数据定时任务",
                        "定时任务完成：未到定期备份时间",
                        this.getClass().getName(),
                        "doCheck");
                }
                else
                {
                    Integer seqId = sysBackUpService.createBackUp();
                    sysConfigService.updateNextBackupDate();
                    
                    // 数据备份类型：0-增量；1-全量；
                    if (1 == CommonUtils.getBackupForm())
                    {
                        LogUtils.getInstance().log(3,
                            CommonUtils.LogLevel.INFO,
                            "备份数据定时任务",
                            "定时任务完成：备份类型~全量；SeqId~" + seqId,
                            this.getClass().getName(),
                            "doCheck");
                    }
                    else
                    {
                        LogUtils.getInstance().log(3,
                            CommonUtils.LogLevel.INFO,
                            "备份数据定时任务",
                            "定时任务完成：备份类型~增量；SeqId~" + seqId,
                            this.getClass().getName(),
                            "doCheck");
                    }
                }
            }
            else
            {
                LogUtils.getInstance().log(3,
                    CommonUtils.LogLevel.INFO,
                    "备份数据定时任务",
                    "定期备份数据功能未开启",
                    this.getClass().getName(),
                    "doCheck");
            }
        }
        catch (Exception e)
        {
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.ERROR,
                "备份数据定时任务",
                "定时任务异常：" + e.getMessage(),
                this.getClass().getName(),
                "doCheck");
        }
    }
    
}