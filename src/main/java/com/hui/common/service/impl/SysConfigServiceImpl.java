package com.hui.common.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.entity.SysConfig;
import com.hui.common.service.ISysConfigService;
import com.hui.common.utils.CommonUtils;

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
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig, SysConfig> implements ISysConfigService
{
    
   
    public void updateNextBackupDate()
    {
        SysConfig ci = new SysConfig();
        ci.setConfigCode("next_backup_date");
        List<SysConfig> sysConfigs = baseDao.selectByKey(ci);
        if (CommonUtils.isNotEmptyOrNull(sysConfigs))
        {
            SysConfig sysConfig = sysConfigs.get(0);
            Calendar c = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat(CommonUtils.getShortDateFormat());
            
            try
            {
                if (StringUtils.isBlank(sysConfig.getConfigValue()))
                {
                    String curDate = CommonUtils.getFormatCurTime(CommonUtils.getShortDateFormat());
                    Date beginDate = format.parse(curDate);
                    c.setTime(beginDate);
                }
                else
                {
                    String nextBackupDate =
                        CommonUtils.formatTimeStamp(Integer.parseInt(sysConfig.getConfigValue()),
                            CommonUtils.getShortDateFormat());
                    Date beginDate = format.parse(nextBackupDate);
                    c.setTime(beginDate);
                }
                
                // 数据备份周期：0-每天；1-每周；2-每月；3-每季度；4-每半年；5-每年；
                switch (CommonUtils.getBackupPeriod())
                {
                    case 0:
                        c.add(Calendar.DATE, 1);
                        break;
                    case 1:
                        c.add(Calendar.DATE, 7);
                        break;
                    case 2:
                        c.add(Calendar.MONTH, 1);
                        break;
                    case 3:
                        c.add(Calendar.MONTH, 3);
                        break;
                    case 4:
                        c.add(Calendar.MONTH, 6);
                        break;
                    case 5:
                        c.add(Calendar.YEAR, 1);
                        break;
                    default:
                }
                
                sysConfig.setConfigValue(Integer.toString(CommonUtils.getTimeStamp(format.format(c.getTime()))));
                baseDao.update(sysConfig);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
}