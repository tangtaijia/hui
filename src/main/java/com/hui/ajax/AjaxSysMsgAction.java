package com.hui.ajax;

import java.util.List;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.SysMsg;
import com.hui.common.service.ISysFileService;
import com.hui.common.service.ISysMsgService;
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
public class AjaxSysMsgAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 3783454212920721040L;
    
    private ISysMsgService sysMsgService;
    
    private ISysFileService sysFileService;
    
    private Integer sysMsgId;
    
    private String qrySysMsgTitle;
    
    private Integer qryCreateTimeFrom;
    
    private Integer qryCreateTimeTo;
    
    private Integer qryStatus;
    
    private String sysMsgTitle;
    
    private String sysMsgDesc;
    
    private String rows;
    
    private String page;
    
    private AjaxResult result;
    
    public void setSysMsgService(ISysMsgService sysMsgService)
    {
        this.sysMsgService = sysMsgService;
    }

    public void setSysFileService(ISysFileService sysFileService)
    {
        this.sysFileService = sysFileService;
    }

    public Integer getSysMsgId()
    {
        return sysMsgId;
    }

    public void setSysMsgId(Integer sysMsgId)
    {
        this.sysMsgId = sysMsgId;
    }

    public void setQrySysMsgTitle(String qrySysMsgTitle)
    {
        this.qrySysMsgTitle = qrySysMsgTitle;
    }

    public void setQryCreateTimeFrom(Integer qryCreateTimeFrom)
    {
        this.qryCreateTimeFrom = qryCreateTimeFrom;
    }

    public void setQryCreateTimeTo(Integer qryCreateTimeTo)
    {
        this.qryCreateTimeTo = qryCreateTimeTo;
    }

    public void setQryStatus(Integer qryStatus)
    {
        this.qryStatus = qryStatus;
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
    
    public String getSysMsgTitle()
    {
        return sysMsgTitle;
    }

    public String getSysMsgDesc()
    {
        return sysMsgDesc;
    }

    public void setSysMsgTitle(String sysMsgTitle)
    {
        this.sysMsgTitle = sysMsgTitle;
    }

    public void setSysMsgDesc(String sysMsgDesc)
    {
        this.sysMsgDesc = sysMsgDesc;
    }

    public String toget()
    {
        try
        {
            SysMsg sysMsg = sysMsgService.getSysMsgDetail(sysMsgId);
            if(CommonUtils.isNotEmptyOrNull(sysMsg)){
                sysMsg.setCreateTimeStr(CommonUtils.formatTimeStamp(sysMsg.getCreateTime(), CommonUtils.getLongDateFormat()));
                SysFile fi = new SysFile();
                fi.setDataId(sysMsgId);
                fi.setFileType(7);
                List<SysFile> sysFiles = sysFileService.list(fi);
                fi = new SysFile();
                if(CommonUtils.isNotEmptyOrNull(sysFiles)) {
                	for(SysFile sysFile : sysFiles) {
                		if(sysFile.getSeqId()==2) {//原图
                			fi = sysFile;
                		}
                	}
                }
                sysFiles.clear();
                sysFiles.add(fi);
                result = new AjaxResult(true, sysFiles,sysMsg);
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
        
        SysMsg sysMsgParam = new SysMsg();
        sysMsgParam.setCreateTimeFrom(qryCreateTimeFrom);
        sysMsgParam.setCreateTimeTo(qryCreateTimeTo);
        sysMsgParam.setSysMsgTitle(qrySysMsgTitle);
        sysMsgParam.setStatus(qryStatus);
        sysMsgParam.setStart(start);
        sysMsgParam.setSize(size);
        List<SysMsg> sysMsgs = sysMsgService.list(sysMsgParam);
        result = new AjaxResult(sysMsgService.getCount(sysMsgParam), sysMsgs);
        
        return SUCCESS;
    }
    
    public String create() {
        try
        {
            SysMsg sysMsg = new SysMsg();
            sysMsg.setCreateTime(CommonUtils.getCurTime());
            sysMsg.setHasImg(0);
            sysMsg.setStatus(1);
            sysMsg.setSysMsgDesc(sysMsgDesc);
            sysMsg.setSysMsgTitle(sysMsgTitle);
            sysMsgService.saveEntity(sysMsg);
            result = new AjaxResult(true, "新增成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "新增失败");
        }
        return SUCCESS;
    }
    
    public String modify() {
        try
        {
            SysMsg sysMsg = sysMsgService.selectById(sysMsgId);
            sysMsg.setSysMsgTitle(sysMsgTitle);
            sysMsg.setSysMsgDesc(sysMsgDesc);
            sysMsgService.updateEntity(sysMsg);
            result = new AjaxResult(true, "修改成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "修改失败");
        }
        return SUCCESS;
    }
    
}