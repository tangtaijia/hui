package com.hui.common.job;

import java.io.File;

import javax.servlet.ServletContext;

import com.hui.common.listener.WebContext;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.LogUtils;

/**
 * 
 * 清除临时文件定时任务
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClearTempFilesJob extends BaseJob
{
    
   
    protected void doCheck()
    {
        try
        {
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.INFO,
                "清除临时文件定时任务",
                "定时任务启动",
                this.getClass().getName(),
                "doCheck");
            
            ServletContext context = WebContext.getInstance().getContext();
            File temp = new File(context.getRealPath("/upload/temp"));
            if (null != temp && temp.exists() && temp.isDirectory())
            {
                File[] files = temp.listFiles();
                for (File file : files)
                {
                    file.delete();
                }
            }
            
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.INFO,
                "清除临时文件定时任务",
                "定时任务完成",
                this.getClass().getName(),
                "doCheck");
        }
        catch (Exception e)
        {
            LogUtils.getInstance().log(3,
                CommonUtils.LogLevel.ERROR,
                "清除临时文件定时任务",
                "定时任务异常：" + e.getMessage(),
                this.getClass().getName(),
                "doCheck");
        }
    }
    
}