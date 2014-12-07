package com.hui.common.dao.impl;

import com.hui.common.dao.IBeanFlowDao;
import com.hui.common.entity.BeanFlow;

public class BeanFlowDaoImpl extends AbstractBaseDao<BeanFlow, BeanFlow> implements IBeanFlowDao
{
    public Integer getLastLeftBeanNum(String huiNo) {
    	return (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getLastLeftBeanNum", huiNo);
    }
}
