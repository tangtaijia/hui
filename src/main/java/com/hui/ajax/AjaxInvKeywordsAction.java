package com.hui.ajax;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.InvKeywords;
import com.hui.common.service.IInvKeywordsService;

public class AjaxInvKeywordsAction extends BaseActionSupport
{
	private static final long serialVersionUID = 5993077924327278428L;
	
	private IInvKeywordsService invKeywordsService;
	
	private AjaxResult result;
	
	private String rows;
    
    private String page;
    
    private Integer id;
    
    private String value;
    
    private String delIds;

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}

	public AjaxResult getResult() {
		return result;
	}

	public void setInvKeywordsService(IInvKeywordsService invKeywordsService) {
		this.invKeywordsService = invKeywordsService;
	}
	
	public String tolist(){
		// 当前页   
        int intPage = Integer.parseInt((null == page || "0".equals(page)) ? "1" : page);
        // 每页显示条数   
        int size = Integer.parseInt((null == rows || "0".equals(rows)) ? "10" : rows);
        // 每页的开始记录  第一页为1  第二页为number+1
        int start = (intPage - 1) * size;
        
        InvKeywords invkeywords = new InvKeywords();
        
        invkeywords.setStart(start);
        invkeywords.setSize(size);
        
        List<InvKeywords> list = invKeywordsService.list(invkeywords);
        
        result = new AjaxResult(invKeywordsService.getCount(invkeywords), list );
        
		return SUCCESS;
	}
	
	public String tocreate()
    {
        try
        {
        	InvKeywords invkeywords = new InvKeywords();
        	invkeywords.setId(id);
        	invkeywords.setValue(value);
            
        	invKeywordsService.saveEntity(invkeywords);
            
            result = new AjaxResult(true, "新增敏感词成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "新增敏感词失败");
        }
        
        return SUCCESS;
    }
	
	public String todel()
    {
        try
        {
            List<Integer> ids = new ArrayList<Integer>();
            String[] idArray = StringUtils.split(delIds, ',');
            for (String id : idArray)
            {
                ids.add(Integer.valueOf(id));
            }
            invKeywordsService.delete(ids);
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
	
	public String toget()
    {
        try
        {
        	InvKeywords invkeywords = invKeywordsService.selectById(id);
            if(invkeywords != null)
            {
            	result = new AjaxResult(true, invkeywords);
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
	
	public String tomodify()
    {
        try
        {
        	InvKeywords invkeywords = invKeywordsService.selectById(id);
            if (null != invkeywords)
            {
            	invkeywords.setValue(value);
                
            	invKeywordsService.updateEntity(invkeywords);
                
                result = new AjaxResult(true, "修改成功");
            }
            else
            {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "修改失败");
        }
        
        return SUCCESS;
    }
    
    

}
