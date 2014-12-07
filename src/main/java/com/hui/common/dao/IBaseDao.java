package com.hui.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IBaseDao<T, PK extends Serializable>
{
    
    public T selectById(Integer id);
    
    public List<T> selectByKey(PK pk);
    
    public List<T> selectAll(PK pk);
    
    public List<T> selectAllByMap(Map<String,Object> paramMap);
    
    public List<T> selectAll();
    
    public Integer getCount(PK pk);
    
    public Integer getCount(Map<String,Object> paramMap);
    
    public int update(PK pk);
    
    public Integer batchUpdate(List<T> pks);
    
    public Integer save(PK pk);
    
    public Integer batchSave(List<T> pks);
    
    public Integer delete(Integer id);
    
    public Integer delete(Map<String,Object> paramMap);
    
}