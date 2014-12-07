package com.hui.common.service;


import com.hui.common.entity.Fans;
import com.hui.common.entity.Page;

/**
 * 用户关注Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IUserFollowService extends IBaseService<Fans, Fans>
{
    /**
     * 加关注
     * <功能详细描述>
     * @param sourceHuiNo
     * @param targetHuiNo
     * @see [类、类#方法、类#成员]
     */
    void addUserFollow(String sourceHuiNo,String targetHuiNo);
    
    /**
     * 去关注
     * <功能详细描述>
     * @param sourceHuiNo
     * @param targetHuiNo
     * @see [类、类#方法、类#成员]
     */
    void deleteUserFollow(String sourceHuiNo,String targetHuiNo);
    
}
