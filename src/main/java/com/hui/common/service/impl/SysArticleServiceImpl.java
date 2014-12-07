package com.hui.common.service.impl;

import java.util.List;

import com.hui.common.dao.ISysArticleDao;
import com.hui.common.entity.SysArticle;
import com.hui.common.service.ISysArticleService;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysArticleServiceImpl extends BaseServiceImpl<SysArticle, SysArticle> implements ISysArticleService
{
    
   
    public Integer delete(List<Integer> ids)
    {
        return ((ISysArticleDao)baseDao).delete(ids);
    }
    
}