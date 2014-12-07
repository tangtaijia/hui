package com.hui.ajax;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.BeanFlow;
import com.hui.common.entity.User;
import com.hui.common.service.IBeanFlowService;
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
public class AjaxBeanFlowAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 3783454212920721040L;
    
    private IBeanFlowService beanFlowService;
    
    private IUserService userService;
    
    private Integer bfId;
    
    private String qryFlowTimeFrom;
    
    private String qryFlowTimeTo;
    
    private Integer qryInOut;
    
    private String qryHuiNo;
    
    private String rows;
    
    private String page;
    
    private AjaxResult result;
    
    public void setBeanFlowService(IBeanFlowService beanFlowService)
    {
        this.beanFlowService = beanFlowService;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    public Integer getBfId()
    {
        return bfId;
    }

    public void setBfId(Integer bfId)
    {
        this.bfId = bfId;
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
            BeanFlow beanFlow = beanFlowService.selectById(bfId);
            if(CommonUtils.isNotEmptyOrNull(beanFlow)){
                if(CommonUtils.isNotEmptyOrNullOr0OrFalse(beanFlow.getFlowTime())) {
                    beanFlow.setFlowTimeStr((CommonUtils.formatTimeStamp(beanFlow.getFlowTime(), CommonUtils.getLongDateFormat())));
                }
                if(0==beanFlow.getInOut()) {
                    beanFlow.setInOutStr("流入");
                }
                if(1==beanFlow.getInOut()) {
                    beanFlow.setInOutStr("流出");
                }
                User user= userService.getUserInfo(beanFlow.getHuiNo());
                beanFlow.setUser(user);
                result = new AjaxResult(true, beanFlow);
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
        
        BeanFlow beanFlowParam = new BeanFlow();
        beanFlowParam.setFlowTimeFrom(StringUtils.isBlank(qryFlowTimeFrom) ? null : CommonUtils.getTimeStamp(qryFlowTimeFrom));
        beanFlowParam.setFlowTimeTo(StringUtils.isBlank(qryFlowTimeTo) ? null : CommonUtils.getTimeStamp(qryFlowTimeTo));
        beanFlowParam.setInOut(qryInOut);
        beanFlowParam.setHuiNo(qryHuiNo);
        beanFlowParam.setStart(start);
        beanFlowParam.setSize(size);
        List<BeanFlow> beanFlows = beanFlowService.list(beanFlowParam);
        if(CommonUtils.isNotEmptyOrNull(beanFlows)){
            for(BeanFlow beanFlow:beanFlows) {
                if(CommonUtils.isNotEmptyOrNullOr0OrFalse(beanFlow.getFlowTime())) {
                    beanFlow.setFlowTimeStr((CommonUtils.formatTimeStamp(beanFlow.getFlowTime(), CommonUtils.getLongDateFormat())));
                }
                if(0==beanFlow.getInOut()) {
                    beanFlow.setInOutStr("流入");
                }
                if(1==beanFlow.getInOut()) {
                    beanFlow.setInOutStr("流出");
                }
            }
        }
        result = new AjaxResult(beanFlowService.getCount(beanFlowParam), beanFlows);
        
        return SUCCESS;
    }
    
}