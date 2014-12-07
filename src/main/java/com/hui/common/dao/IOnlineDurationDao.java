package com.hui.common.dao;

import com.hui.common.entity.OnlineDuration;

/**
 * 教师在线记录Dao
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IOnlineDurationDao extends IBaseDao<OnlineDuration, OnlineDuration>
{
    /**
     * 计算教师在线时长
     * <功能详细描述>
     * @param teacherId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer countOnlineTime(Integer teacherId);
    
    /**
     * 检查教师在线(0下线 1上线)
     * <功能详细描述>
     * @param teacherId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer checkOnline(Integer teacherId);
    
    /**
     * 获取教师最近一次的登录时间
     * <功能详细描述>
     * @param teacherId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer getRecentOnlineTime(Integer teacherId);
}
