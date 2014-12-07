package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.IAdminDao;
import com.hui.common.entity.Admin;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AdminDaoImpl extends AbstractBaseDao<Admin, Admin> implements IAdminDao
{
    
    public Integer modifyStatus(Integer status, List<Integer> ids)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("status", status);
        param.put("ids", ids);
        return (Integer)getSqlMapClientTemplate().update(namespace + ".modifyStatus", param);
    }

    @SuppressWarnings("unchecked")
    public List<Admin> needUnlockList()
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".needUnlockList");
    }
    
}