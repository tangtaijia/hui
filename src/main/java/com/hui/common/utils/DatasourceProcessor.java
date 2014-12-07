package com.hui.common.utils;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-6-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DatasourceProcessor extends BasicDataSource
{
    
    public void setUsername(String username)
    {
        try
        {
            ProcessMD5 md5 = new ProcessMD5();
            super.setUsername(md5.getusername(username));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void setPassword(String password)
    {
        try
        {
            ProcessMD5 md5 = new ProcessMD5();
            super.setPassword(md5.getpwd(password));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}