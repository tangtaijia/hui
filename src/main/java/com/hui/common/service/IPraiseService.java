package com.hui.common.service;

import java.util.Map;

import com.hui.common.entity.Praise;

/**
 * 点赞Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IPraiseService extends IBaseService<Praise, Praise>
{
    /**
     * 点赞
     * <功能详细描述>
     * @param praise
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer praise(Praise praise);
    
    /**
     * 消赞
     * <功能详细描述>
     * @param prId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer unPraise(Integer prId);
    
    /**
     * 消赞
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer unPraise(Praise praise);
}
