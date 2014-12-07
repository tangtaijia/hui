package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.Grade;

/**
 * 年级Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IGradeService extends IBaseService<Grade, Grade>
{
    /**
     * 查询年级列表（包含响应的学科）
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Grade> selectAllWithSubs();
}
