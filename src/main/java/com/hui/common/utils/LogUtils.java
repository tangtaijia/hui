package com.hui.common.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hui.common.dao.ISysLogDao;
import com.hui.common.entity.Admin;
import com.hui.common.entity.SysLog;
import com.hui.common.listener.WebContext;
import com.hui.common.utils.CommonUtils.LogLevel;

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
public class LogUtils
{
    
    private static LogUtils instance;
    
    private static Object lock = new Object();
    
    private ISysLogDao sysLogDao;
    
    private static Logger logger;
    
    private LogUtils()
    {
        // 获得日志类loger的实例  
        logger = Logger.getLogger(this.getClass());
        ServletContext context = WebContext.getInstance().getContext();
        // loger所需的配置文件路径  
        PropertyConfigurator.configure(context.getRealPath("/WEB-INF/classes/resources/log4j.properties"));
        
        ApplicationContext ac = new ClassPathXmlApplicationContext("/resources/applicationContext*.xml");
        sysLogDao = (ISysLogDao)ac.getBean("sysLogDao");
    }
    
    public static LogUtils getInstance()
    {
        if (null == instance)
        {
            synchronized (lock)
            {
                if (null == instance)
                {
                    instance = new LogUtils();
                }
            }
        }
        return instance;
    }
    
    /**
     * 记录方法异常日志
     */
    public void log(HttpServletRequest request, JoinPoint joinpoint, Throwable ex)
    {
        try
        {
            if (1 == CommonUtils.getLogable() && 1 == CommonUtils.getErrorLog())
            {
                String remoteIP = CommonUtils.getRemoteIP(request);
                String curTimeStr = CommonUtils.getFormatCurTime(CommonUtils.getLongDateFormat());
                Integer curTime = CommonUtils.getCurTime();
                
                String message =
                    "操作类型：" + CommonUtils.getSysData("operate_type").getValue().get(Integer.toString(0)) + "--操作时间："
                        + curTimeStr + "-IP地址：" + remoteIP + "-类名：" + joinpoint.getThis() + "-方法名："
                        + joinpoint.getSignature().getName() + "-日志信息：" + ex.toString();
                
                SysLog li = new SysLog();
                li.setRemoteIp(remoteIP);
                li.setLogTime(curTime);
                li.setOperateType(0);
                li.setLogLevel(1);
                li.setClassName(joinpoint.getThis().toString());
                li.setMethodName(joinpoint.getSignature().getName());
                li.setLogInfo(ex.getMessage());
                
                // 日志记录方式：0-文件；1-数据库；2-文件和数据库；
                switch (CommonUtils.getLoggerType())
                {
                    case 0:
                        logger.error(message);
                        break;
                    case 1:
                        sysLogDao.save(li);
                        break;
                    case 2:
                        logger.error(message);
                        sysLogDao.save(li);
                        break;
                    default:
                }
            }
        }
        catch (Exception e)
        {
            
        }
    }
    
    /**
     * 记录日志（管理员）
     */
    public void log(HttpServletRequest request, Admin admin, Integer operateType, LogLevel logLevel, String logInfo,
        String className, String methodName)
    {
        try
        {
            if (1 == CommonUtils.getLogable())
            {
                // 操作类型：0-方法异常；1-登录登出；2-系统配置；3-定时任务；
                switch (operateType)
                {
                    case 1:
                        if (0 == CommonUtils.getLoginLog())
                        {
                            return;
                        }
                        break;
                    case 2:
                        if (0 == CommonUtils.getSysCfgLog())
                        {
                            return;
                        }
                        break;
                    default:
                        return;
                }
                String remoteIP = CommonUtils.getRemoteIP(request);
                String curTimeStr = CommonUtils.getFormatCurTime(CommonUtils.getLongDateFormat());
                Integer curTime = CommonUtils.getCurTime();
                
                String message =
                    "操作类型：" + CommonUtils.getSysData("operate_type").getValue().get(Integer.toString(operateType))
                        + "--操作时间：" + curTimeStr + "-IP地址：" + remoteIP + "-操作用户：" + admin.getAdminName() + "-类名："
                        + className + "-方法名：" + methodName + "-日志信息：" + logInfo;
                
                SysLog li = new SysLog();
                li.setRemoteIp(remoteIP);
                li.setOperatorType(2);
                li.setOperatorName(admin.getAdminName());
                switch (logLevel)
                {
                    case INFO:
                        li.setLogLevel(0);
                        break;
                    case ERROR:
                        li.setLogLevel(1);
                        break;
                    default:
                }
                li.setLogTime(curTime);
                li.setOperateType(operateType);
                li.setClassName(className);
                li.setMethodName(methodName);
                li.setLogInfo(logInfo);
                
                // 日志记录方式：0-文件；1-数据库；2-文件和数据库；
                switch (CommonUtils.getLoggerType())
                {
                    case 0:
                        switch (logLevel)
                        {
                            case INFO:
                                logger.info(message);
                                break;
                            case ERROR:
                                logger.error(message);
                                break;
                            default:
                        }
                        break;
                    case 1:
                        sysLogDao.save(li);
                        break;
                    case 2:
                        switch (logLevel)
                        {
                            case INFO:
                                logger.info(message);
                                break;
                            case ERROR:
                                logger.error(message);
                                break;
                            default:
                        }
                        sysLogDao.save(li);
                        break;
                    default:
                }
            }
        }
        catch (Exception e)
        {
            
        }
    }
    
    /**
     * 记录日志（定时任务等）
     */
    public void log(Integer operateType, LogLevel logLevel, String operatorName, String logInfo, String className,
        String methodName)
    {
        try
        {
            if (1 == CommonUtils.getLogable())
            {
                // 操作类型：0-方法异常；1-登录登出；2-系统配置；3-定时任务；
                switch (operateType)
                {
                    case 3:
                        if (0 == CommonUtils.getJobLog())
                        {
                            return;
                        }
                        break;
                    default:
                        return;
                }
                String curTimeStr = CommonUtils.getFormatCurTime(CommonUtils.getLongDateFormat());
                Integer curTime = CommonUtils.getCurTime();
                
                String message =
                    "操作类型：" + CommonUtils.getSysData("operate_type").getValue().get(Integer.toString(operateType))
                        + "--操作时间：" + curTimeStr + "-类名：" + className + "-方法名：" + methodName + "-日志信息：" + logInfo;
                
                SysLog li = new SysLog();
                li.setLogTime(curTime);
                li.setOperateType(operateType);
                li.setOperatorName(operatorName);
                switch (logLevel)
                {
                    case INFO:
                        li.setLogLevel(0);
                        break;
                    case ERROR:
                        li.setLogLevel(1);
                        break;
                    default:
                }
                li.setClassName(className);
                li.setMethodName(methodName);
                li.setLogInfo(logInfo);
                
                // 日志记录方式：0-文件；1-数据库；2-文件和数据库；
                switch (CommonUtils.getLoggerType())
                {
                    case 0:
                        switch (logLevel)
                        {
                            case INFO:
                                logger.info(message);
                                break;
                            case ERROR:
                                logger.error(message);
                                break;
                            default:
                        }
                        break;
                    case 1:
                        sysLogDao.save(li);
                        break;
                    case 2:
                        switch (logLevel)
                        {
                            case INFO:
                                logger.info(message);
                                break;
                            case ERROR:
                                logger.error(message);
                                break;
                            default:
                        }
                        sysLogDao.save(li);
                        break;
                    default:
                }
            }
        }
        catch (Exception e)
        {
            
        }
    }
    
}