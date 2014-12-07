package com.hui.common.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hui.common.dao.IBaseDao;

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
public abstract class AbstractBaseDao<T, PK extends Serializable> extends SqlMapClientDaoSupport implements
    IBaseDao<T, PK>
{
    
    protected String namespace;
    
    public void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }
    
    @SuppressWarnings("unchecked")
    public List<T> selectByKey(PK pk)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".selectByKey", pk);
    }
    
    @SuppressWarnings("unchecked")
    public List<T> selectAll(PK pk)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".selectAll", pk);
    }
    @SuppressWarnings("unchecked")
    public List<T> selectAllByMap(Map<String,Object> paramMap) 
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".selectAll", paramMap);
    }
    public List<T> selectAll() 
    {
        return selectAll(null);
    }
    
    public int update(PK pk)
    {
        return getSqlMapClientTemplate().update(namespace + ".update", pk);
    }
    
    public Integer batchUpdate(List<T> pks)
    {
        try
        {
            this.getSqlMapClient().startBatch();
            
            for (T pk : pks)
            {
                getSqlMapClient().update(namespace + ".update", pk);
            }
            return getSqlMapClient().executeBatch();
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Integer save(PK pk)
    {
        return (Integer)getSqlMapClientTemplate().insert(namespace + ".save", pk);
    }
    
    public Integer batchSave(List<T> pks)
    {
        try
        {
            this.getSqlMapClient().startBatch();
            
            for (T pk : pks)
            {
                getSqlMapClient().insert(namespace + ".save", pk);
            }
            return getSqlMapClient().executeBatch();
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Integer delete(Integer id)
    {
        return getSqlMapClientTemplate().delete(namespace + ".delete", id);
    }
    
    public Integer delete(Map<String, Object> paramMap)
    {
        return getSqlMapClientTemplate().delete(namespace + ".delete", paramMap);
    }
    
    public Integer getCount(PK pk)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getCount", pk);
    }
    public Integer getCount(Map<String,Object> paramMap)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getCount", paramMap);
    }
    
    @SuppressWarnings("unchecked")
    public T selectById(Integer id)
    {
        return (T)getSqlMapClientTemplate().queryForObject(namespace + ".selectById", id);
    }
    
}