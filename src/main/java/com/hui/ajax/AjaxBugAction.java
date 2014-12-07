package com.hui.ajax;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.Bug;
import com.hui.common.entity.User;
import com.hui.common.service.IBugService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxBugAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = -5759860974298421640L;
    
    private IBugService bugServiceImpl;
    
    private AjaxResult result;
    
    /**
     * 回馈内容
     */
    private String bugDesc;
    
    /**
     * 用户名（选填）
     */
    private String userName;
    
    /**
     * QQ（选填）
     */
    private Integer qq;
    
    /**
     * 联系方式（选填）
     */
    private String phone;
    
    /**
     * 验证码
     */
    private String valiCode;
    
    private Integer bugId;
    
    private String qryCreateTimeFrom;
    
    private String qryCreateTimeTo;
    
    private String rows;
    
    private String page;
    
    /**
     * 检查昵称是否存在
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String save()
    {
        if (!getRequest().getSession()
            .getId()
            .toLowerCase()
            .equalsIgnoreCase(getRequest().getParameter(FrontUtils.FORM_AUTH_NAME)))
        {
            return "404";
        }
        
        String vali =
            (String)getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        
        if (StringUtils.isBlank(valiCode) || StringUtils.isBlank(vali) || !valiCode.equals(vali))
        {
            result = new AjaxResult(false, "验证码错误");
            return SUCCESS;
        }
        Bug bug = new Bug();
        bug.setClientStyle("PC");
        bug.setCreateTime(CommonUtils.getNowInt());
        bug.setStatus(1);
        User user = (User)getRequest().getSession().getAttribute("user");
        if (CommonUtils.isNotEmptyOrNull(user))
        {
            bug.setHuiNo(user.getHuiNo());
            bug.setUserName(user.getNickName());
            bug.setPhone(user.getMobile());
        }
        if (CommonUtils.isNotEmptyOrNull(bugDesc))
        {
            bug.setBugDesc(bugDesc);
        }
        if (CommonUtils.isNotEmptyOrNull(userName))
        {
            bug.setUserName(userName);
        }
        if (CommonUtils.isNotEmptyOrNull(qq))
        {
            bug.setQq(qq);
        }
        if (CommonUtils.isNotEmptyOrNull(phone))
        {
            bug.setPhone(phone);
        }
        try
        {
            if (bugServiceImpl.addBug(bug) > 0)
            {
                result = new AjaxResult(true, "提交成功，感谢您的反馈！");
            }
            else
            {
                result = new AjaxResult(true, "提交失败！");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "系统错误!");
        }
        return SUCCESS;
    }
    
    public String toget()
    {
        try
        {
            Bug bug = bugServiceImpl.selectById(bugId);
            if (CommonUtils.isNotEmptyOrNull(bug))
            {
                if (CommonUtils.isNotEmptyOrNullOr0OrFalse(bug.getCreateTime()))
                {
                    bug.setCreateTimeStr((CommonUtils.formatTimeStamp(bug.getCreateTime(),
                        CommonUtils.getLongDateFormat())));
                }
                result = new AjaxResult(true, bug);
            }
            else
            {
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
        
        Bug bugParam = new Bug();
        bugParam.setCreateTimeFrom(StringUtils.isBlank(qryCreateTimeFrom) ? null
            : CommonUtils.getTimeStamp(qryCreateTimeFrom));
        bugParam.setCreateTimeTo(StringUtils.isBlank(qryCreateTimeTo) ? null
            : CommonUtils.getTimeStamp(qryCreateTimeTo));
        bugParam.setStart(start);
        bugParam.setSize(size);
        List<Bug> bugs = bugServiceImpl.list(bugParam);
        if (CommonUtils.isNotEmptyOrNull(bugs))
        {
            for (Bug bug : bugs)
            {
                if (CommonUtils.isNotEmptyOrNullOr0OrFalse(bug.getCreateTime()))
                {
                    bug.setCreateTimeStr((CommonUtils.formatTimeStamp(bug.getCreateTime(),
                        CommonUtils.getLongDateFormat())));
                }
            }
        }
        result = new AjaxResult(bugServiceImpl.getCount(bugParam), bugs);
        
        return SUCCESS;
    }
    
    public void setBugServiceImpl(IBugService bugServiceImpl)
    {
        this.bugServiceImpl = bugServiceImpl;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public void setBugDesc(String bugDesc)
    {
        this.bugDesc = bugDesc;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public void setQq(Integer qq)
    {
        this.qq = qq;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public void setValiCode(String valiCode)
    {
        this.valiCode = valiCode;
    }
    
    public void setBugId(Integer bugId)
    {
        this.bugId = bugId;
    }
    
    public void setQryCreateTimeFrom(String qryCreateTimeFrom)
    {
        this.qryCreateTimeFrom = qryCreateTimeFrom;
    }
    
    public void setQryCreateTimeTo(String qryCreateTimeTo)
    {
        this.qryCreateTimeTo = qryCreateTimeTo;
    }
    
    public void setRows(String rows)
    {
        this.rows = rows;
    }
    
    public void setPage(String page)
    {
        this.page = page;
    }
    
}