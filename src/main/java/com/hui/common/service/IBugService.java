package com.hui.common.service;

import com.hui.common.entity.Bug;

/**
 * 问题反馈Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IBugService extends IBaseService<Bug, Bug>
{
    /**
     * 问题反馈
     * <功能详细描述>
     * @param bug
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer addBug(Bug bug) throws Exception;
}
