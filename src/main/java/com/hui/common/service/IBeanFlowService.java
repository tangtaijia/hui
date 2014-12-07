package com.hui.common.service;

import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.Page;

public interface IBeanFlowService extends IBaseService<BeanFlow,BeanFlow>
{
	/**
	 * 分页查询汇豆流水
	 * <功能详细描述>
	 * @param pageNo
	 * @param huiNo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	Page<BeanFlow> getBeanFlows(Integer pageNo, String huiNo);
	
	/**
	 * 获取用户最近一条流水的余额
	 * <功能详细描述>
	 * @param huiNo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    Integer getLastLeftBeanNum(String huiNo);
    
    /**
     * 是否今天领取过汇豆了
     * <功能详细描述>
     * @param huiNo
     * @param type 方式 - 汇豆流水类型0提问题1回答最佳2系统赠送3校讯通登录4每天一登录5充值
     * @return
     * @see [类、类#方法、类#成员]
     */
    Boolean isRecivedToday(String huiNo,Integer type);
}