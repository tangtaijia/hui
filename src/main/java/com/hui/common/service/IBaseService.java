package com.hui.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IBaseService<T, PK extends Serializable>
{
    
    public T selectById(Integer id);
    
    public List<T> selectByKey(PK pk);
    
    public List<T> list(PK pk);
    
    public List<T> listByMap(Map<String,Object> paramMap);
    
    public List<T> list();
    
    public Integer getCount(PK pk);
    
    public Integer getCount(Map<String,Object> paramMap);
    
    public int updateEntity(PK pk);
    
    public Integer saveEntity(PK pk);
    
    public Integer delete(Integer id);
    
}