package com.hui.common.dao;

import java.util.List;

import com.hui.common.entity.SysArticle;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISysArticleDao extends IBaseDao<SysArticle, SysArticle>
{
    
    public Integer delete(List<Integer> ids);
    
}