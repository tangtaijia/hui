package com.hui.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hui.common.dao.IInvKeywordsDao;
import com.hui.common.entity.InvKeywords;

public class InvKeywordsDaoImpl extends AbstractBaseDao<InvKeywords, InvKeywords> implements IInvKeywordsDao
{

	public Integer delete(List<Integer> ids)
	{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ids", ids);
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".delete", param);
    }

}
