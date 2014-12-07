package com.hui.ajax;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.CashFlow;
import com.hui.common.entity.User;
import com.hui.common.service.ICashFlowService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxCashFlowAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 3783454212920721040L;
    
    private ICashFlowService cashFlowService;
    
    private IUserService userService;
    
    private Integer cfId;
    
    private String qryFlowTimeFrom;
    
    private String qryFlowTimeTo;
    
    private Integer qryInOut;
    
    private Integer qryFlowSource;
    
    private String qryHuiNo;
    
    private String rows;
    
    private String page;
    
    private AjaxResult result;
    
    public void setCashFlowService(ICashFlowService cashFlowService)
    {
        this.cashFlowService = cashFlowService;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    public Integer getCfId()
    {
        return cfId;
    }

    public void setCfId(Integer cfId)
    {
        this.cfId = cfId;
    }

    public void setQryFlowTimeFrom(String qryFlowTimeFrom)
    {
        this.qryFlowTimeFrom = qryFlowTimeFrom;
    }

    public void setQryFlowTimeTo(String qryFlowTimeTo)
    {
        this.qryFlowTimeTo = qryFlowTimeTo;
    }

    public void setQryInOut(Integer qryInOut)
    {
        this.qryInOut = qryInOut;
    }
    
    public void setQryFlowSource(Integer qryFlowSource)
    {
        this.qryFlowSource = qryFlowSource;
    }

    public String getQryHuiNo()
    {
        return qryHuiNo;
    }

    public void setQryHuiNo(String qryHuiNo)
    {
        this.qryHuiNo = qryHuiNo;
    }

    public String getRows()
    {
        return rows;
    }
    
    public void setRows(String rows)
    {
        this.rows = rows;
    }
    
    public String getPage()
    {
        return page;
    }
    
    public void setPage(String page)
    {
        this.page = page;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public String toget()
    {
        try
        {
            CashFlow cashFlow = cashFlowService.selectById(cfId);
            if(CommonUtils.isNotEmptyOrNull(cashFlow)){
                if(CommonUtils.isNotEmptyOrNullOr0OrFalse(cashFlow.getFlowTime())) {
                    cashFlow.setFlowTimeStr((CommonUtils.formatTimeStamp(cashFlow.getFlowTime(), CommonUtils.getLongDateFormat())));
                }
                if(0==cashFlow.getInOut()) {
                    cashFlow.setInOutStr("流入");
                }
                if(1==cashFlow.getInOut()) {
                    cashFlow.setInOutStr("流出");
                }
                if(1==cashFlow.getFlowSource()) {
                    cashFlow.setFlowSourceStr("支付宝");
                }
                User user= userService.getUserInfo(cashFlow.getHuiNo());
                cashFlow.setUser(user);
                result = new AjaxResult(true, cashFlow);
            } else {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "查询失败");
        }
        
        return SUCCESS;
    }
    
    public String tolist()
    {
        // 当前页   
        int intPage = Integer.parseInt((null == page || "0".equals(page)) ? "1" : page);
        // 每页显示条数   
        int size = Integer.parseInt((null == rows || "0".equals(rows)) ? "10" : rows);
        // 每页的开始记录  第一页为1  第二页为number +1    
        int start = (intPage - 1) * size;
        
        CashFlow cashFlowParam = new CashFlow();
        cashFlowParam.setFlowTimeFrom(StringUtils.isBlank(qryFlowTimeFrom) ? null : CommonUtils.getTimeStamp(qryFlowTimeFrom));
        cashFlowParam.setFlowTimeTo(StringUtils.isBlank(qryFlowTimeTo) ? null : CommonUtils.getTimeStamp(qryFlowTimeTo));
        cashFlowParam.setInOut(qryInOut);
        cashFlowParam.setFlowSource(qryFlowSource);
        cashFlowParam.setHuiNo(qryHuiNo);
        cashFlowParam.setStart(start);
        cashFlowParam.setSize(size);
        List<CashFlow> cashFlows = cashFlowService.list(cashFlowParam);
        if(CommonUtils.isNotEmptyOrNull(cashFlows)){
            for(CashFlow cashFlow:cashFlows) {
                if(CommonUtils.isNotEmptyOrNullOr0OrFalse(cashFlow.getFlowTime())) {
                    cashFlow.setFlowTimeStr((CommonUtils.formatTimeStamp(cashFlow.getFlowTime(), CommonUtils.getLongDateFormat())));
                }
                if(0==cashFlow.getInOut()) {
                    cashFlow.setInOutStr("流入");
                }
                if(1==cashFlow.getInOut()) {
                    cashFlow.setInOutStr("流出");
                }
                if(1==cashFlow.getFlowSource()) {
                    cashFlow.setFlowSourceStr("支付宝");
                }
            }
        }
        result = new AjaxResult(cashFlowService.getCount(cashFlowParam), cashFlows);
        
        return SUCCESS;
    }
    
}