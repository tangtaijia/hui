package com.hui.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.IBaseDao;
import com.hui.common.service.IBaseService;

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
public abstract class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK>
{
    
    protected IBaseDao<T, PK> baseDao;
    
    public void setBaseDao(IBaseDao<T, PK> baseDao)
    {
        this.baseDao = baseDao;
    }
    
   
    public T selectById(Integer id)
    {
        return baseDao.selectById(id);
    }
    
   
    public List<T> selectByKey(PK pk)
    {
        return baseDao.selectByKey(pk);
    }
    
   
    public Integer getCount(PK pk)
    {
        return baseDao.getCount(pk);
    }
    
   
    public Integer getCount(Map<String, Object> paramMap)
    {
        return baseDao.getCount(paramMap);
    }
    
   
    public List<T> list(PK pk)
    {
        return baseDao.selectAll(pk);
    }
    
   
    public List<T> listByMap(Map<String, Object> paramMap)
    {
        return baseDao.selectAllByMap(paramMap);
    }
   
    public List<T> list()
    {
        return baseDao.selectAll(null);
    }
    
   
    public Integer saveEntity(PK pk)
    {
        return baseDao.save(pk);
    }
    
   
    public int updateEntity(PK pk)
    {
        return baseDao.update(pk);
    }
    
   
    public Integer delete(Integer id)
    {
        return baseDao.delete(id);
    }
    
}