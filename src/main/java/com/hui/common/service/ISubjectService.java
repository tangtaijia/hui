package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.Subject;

/**
 * 学科Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISubjectService extends IBaseService<Subject, Subject>
{
    /**
     * 取得年级下的学科列表
     * <功能详细描述>
     * @param gradeId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Subject> selectByGradeId(Integer gradeId);
}
