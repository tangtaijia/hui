package com.hui.common.dao;

import java.util.List;

import com.hui.common.entity.InvKeywords;

public interface IInvKeywordsDao extends IBaseDao<InvKeywords, InvKeywords>
{
	public Integer delete(List<Integer> ids);

}
