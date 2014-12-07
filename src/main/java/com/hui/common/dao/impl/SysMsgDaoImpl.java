package com.hui.common.dao.impl;

import java.util.List;

import com.hui.common.dao.ISysMsgDao;
import com.hui.common.entity.SysMsg;

public class SysMsgDaoImpl extends AbstractBaseDao<SysMsg, SysMsg>  implements ISysMsgDao
{
    @SuppressWarnings("unchecked")
   
    public List<SysMsg> selectAllE(SysMsg sysMsg)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".selectAllE",sysMsg);
    }
    
   
    public Integer getCountE(SysMsg sysMsg)
    {
        return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getCountE",sysMsg);
    }
}
