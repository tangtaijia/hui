package com.hui.common.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.hui.common.entity.Admin;
import com.hui.common.job.BaseJob;
import com.hui.common.service.IAdminService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.LogUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-6-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UnlockAdminJob extends BaseJob
{
    
    private IAdminService adminService;
    
    public void setAdminService(IAdminService adminService)
    {
        this.adminService = adminService;
    }
    
    @Override
    protected void doCheck()
    {
        try
        {
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.INFO,
                "定时解锁定时任务",
                "定时任务启动",
                this.getClass().getName(),
                "doCheck");
            
            Calendar c = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat(CommonUtils.getLongDateFormat());
            Integer curTime = CommonUtils.getTimeStamp(format.format(c.getTime()));
            
            List<Admin> admins = adminService.needUnlockList();
            for (Admin admin : admins)
            {
                if (curTime >= admin.getAutoUnlock())
                {
                    admin.setStatus(0);
                    admin.setAutoUnlock(0);
                    
                    adminService.updateEntity(admin);
                }
            }
            
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.INFO,
                "定时解锁定时任务",
                "定时任务完成",
                this.getClass().getName(),
                "doCheck");
        }
        catch (Exception e)
        {
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.ERROR,
                "定时解锁定时任务",
                "定时任务异常：" + e.getMessage(),
                this.getClass().getName(),
                "doCheck");
        }
    }
    
}