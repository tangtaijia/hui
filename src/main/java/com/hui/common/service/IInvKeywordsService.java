package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.InvKeywords;

public interface IInvKeywordsService extends IBaseService<InvKeywords, InvKeywords>
{
	public Integer delete(List<Integer> ids);

}
