package com.hui.common.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IBeanFlowDao;
import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.Page;
import com.hui.common.service.IBeanFlowService;
import com.hui.common.utils.CommonUtils;

@Service("beanFlowService")
public class BeanFlowServiceImpl extends BaseServiceImpl<BeanFlow, BeanFlow> implements IBeanFlowService
{
	@Autowired 
	private IBeanFlowDao beanFlowDao;
	
	public Page<BeanFlow> getBeanFlows(Integer pageNo, String huiNo)
    {
        Page<BeanFlow> page = new Page<BeanFlow>();
        page.setPage(pageNo);
        page.setSize(10);
        
        BeanFlow beanFlowParam = new BeanFlow();
        beanFlowParam.setHuiNo(huiNo);
        beanFlowParam.setStart(page.getStart());
        beanFlowParam.setSize(page.getSize());
        
        List<BeanFlow> beanFlows = beanFlowDao.selectAll(beanFlowParam);
        if(CommonUtils.isNotEmptyOrNull(beanFlows)) {
        	for(BeanFlow beanFlow:beanFlows){
        		beanFlow.setFlowTimeStr(CommonUtils.formatDate2Str(CommonUtils.formatTimeStamp2Date(beanFlow.getFlowTime())));
        	}
        }
        
        Integer count = this.beanFlowDao.getCount(beanFlowParam);
        page.setDatas(beanFlows);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
	
	public Integer getLastLeftBeanNum(String huiNo) {
		return beanFlowDao.getLastLeftBeanNum(huiNo);
	}

	public Boolean isRecivedToday(String huiNo, Integer type) {
		
		BeanFlow beanFlowParam = new BeanFlow();
		beanFlowParam.setHuiNo(huiNo);
		beanFlowParam.setType(type);
		Calendar cal = Calendar.getInstance();
		beanFlowParam.setFlowTimeFrom(CommonUtils.getTimeStamp(CommonUtils.formatDate2Str(cal.getTime())));//今天
		cal.add(Calendar.DATE, 1);
		beanFlowParam.setFlowTimeTo(CommonUtils.getTimeStamp(CommonUtils.formatDate2Str(cal.getTime())));//明天
		Integer count = beanFlowDao.getCount(beanFlowParam);
		if(CommonUtils.isNotEmptyOrNullOr0OrFalse(count)) {
			return true;
		} else {
			return false;
		}
	}
}
