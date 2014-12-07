package com.hui.common.entity;

/**
 * 
 * <系统日志>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysLog extends BaseEntity
{
    
    private static final long serialVersionUID = -1240183669778838094L;
    /**
     * 主键
     */
    private Integer logId;
    
    private String remoteIp;
    /**
     * 操作用户类型
     */
    private Integer operatorType = 0;
    
    private String operatorTypeStr;
    
    private String operatorName;
    /**
     * 操作类型
     */
    private Integer operateType;
    
    private String operateTypeStr;
    /**
     * 日志级别
     */
    private Integer logLevel;
    
    private String logLevelStr;
    
    private Integer logTime;
    
    private Integer logTimeFrom;
    
    private Integer logTimeTo;
    
    private String logTimeStr;
    
    private String className;
    
    private String methodName;
    
    private String logInfo;
    
    public Integer getLogId()
    {
        return logId;
    }
    
    public void setLogId(Integer logId)
    {
        this.logId = logId;
    }
    
    public String getRemoteIp()
    {
        return remoteIp;
    }
    
    public void setRemoteIp(String remoteIp)
    {
        this.remoteIp = remoteIp;
    }
    
    public Integer getOperatorType()
    {
        return operatorType;
    }
    
    public void setOperatorType(Integer operatorType)
    {
        this.operatorType = operatorType;
    }
    
    public String getOperatorTypeStr()
    {
        return operatorTypeStr;
    }
    
    public void setOperatorTypeStr(String operatorTypeStr)
    {
        this.operatorTypeStr = operatorTypeStr;
    }
    
    public Integer getLogLevel()
    {
        return logLevel;
    }
    
    public void setLogLevel(Integer logLevel)
    {
        this.logLevel = logLevel;
    }
    
    public String getLogLevelStr()
    {
        return logLevelStr;
    }
    
    public void setLogLevelStr(String logLevelStr)
    {
        this.logLevelStr = logLevelStr;
    }
    
    public String getOperatorName()
    {
        return operatorName;
    }
    
    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }
    
    public Integer getOperateType()
    {
        return operateType;
    }
    
    public void setOperateType(Integer operateType)
    {
        this.operateType = operateType;
    }
    
    public String getOperateTypeStr()
    {
        return operateTypeStr;
    }
    
    public void setOperateTypeStr(String operateTypeStr)
    {
        this.operateTypeStr = operateTypeStr;
    }
    
    public Integer getLogTime()
    {
        return logTime;
    }
    
    public void setLogTime(Integer logTime)
    {
        this.logTime = logTime;
    }
    
    public Integer getLogTimeFrom()
    {
        return logTimeFrom;
    }
    
    public void setLogTimeFrom(Integer logTimeFrom)
    {
        this.logTimeFrom = logTimeFrom;
    }
    
    public Integer getLogTimeTo()
    {
        return logTimeTo;
    }
    
    public void setLogTimeTo(Integer logTimeTo)
    {
        this.logTimeTo = logTimeTo;
    }
    
    public String getLogTimeStr()
    {
        return logTimeStr;
    }
    
    public void setLogTimeStr(String logTimeStr)
    {
        this.logTimeStr = logTimeStr;
    }
    
    public String getClassName()
    {
        return className;
    }
    
    public void setClassName(String className)
    {
        this.className = className;
    }
    
    public String getMethodName()
    {
        return methodName;
    }
    
    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }
    
    public String getLogInfo()
    {
        return logInfo;
    }
    
    public void setLogInfo(String logInfo)
    {
        this.logInfo = logInfo;
    }
    
}