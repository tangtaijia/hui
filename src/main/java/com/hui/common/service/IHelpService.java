package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.Help;
import com.hui.common.entity.Page;

/**
 * 帮助Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IHelpService extends IBaseService<Help, Help>
{
    /**
     * 分页查询帮助
     * <功能详细描述>
     * @param pageNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<Help> getHelps(Integer pageNo);
    
    List<Help> getHelps();
    
}
