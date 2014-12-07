package com.hui.common.service.impl;

import java.util.List;

import com.hui.common.dao.IInvKeywordsDao;
import com.hui.common.entity.InvKeywords;
import com.hui.common.service.IInvKeywordsService;

public class InvKeywordsServiceImpl extends BaseServiceImpl<InvKeywords, InvKeywords> implements IInvKeywordsService
{

	public Integer delete(List<Integer> ids) {
		return ((IInvKeywordsDao) baseDao).delete(ids);
	}

}
