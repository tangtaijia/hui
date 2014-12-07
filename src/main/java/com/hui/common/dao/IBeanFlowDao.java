package com.hui.common.dao;

import com.hui.common.entity.BeanFlow;

public interface IBeanFlowDao extends IBaseDao<BeanFlow, BeanFlow>
{
	/**
	 * 获取用户最近一条流水的余额
	 * <功能详细描述>
	 * @param huiNo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    Integer getLastLeftBeanNum(String huiNo);
}
