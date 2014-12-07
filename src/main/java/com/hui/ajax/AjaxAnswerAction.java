package com.hui.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.Answer;
import com.hui.common.service.IAnswerService;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-6-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxAnswerAction extends BaseActionSupport
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -6604998208722565545L;
    
    private IAnswerService answerService;
    
    private AjaxResult result;
    
    private String rows;
    
    private String page;
    
    private Integer questionId;
    
    private Integer answerId;
    
    private String delIds;
    
    public void setAnswerService(IAnswerService answerService)
    {
        this.answerService = answerService;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public void setResult(AjaxResult result)
    {
        this.result = result;
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
    
    public Integer getQuestionId()
    {
        return questionId;
    }
    
    public void setQuestionId(Integer questionId)
    {
        this.questionId = questionId;
    }
    
    public Integer getAnswerId()
    {
        return answerId;
    }
    
    public void setAnswerId(Integer answerId)
    {
        this.answerId = answerId;
    }
    
    public String getDelIds()
    {
        return delIds;
    }
    
    public void setDelIds(String delIds)
    {
        this.delIds = delIds;
    }
    
    public String toget()
    {
        try
        {
            Answer answer = answerService.getAnswer(answerId);
            
            if (null != answer)
            {
                result = new AjaxResult(true, answer);
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
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        paramMap.put("questionId", questionId);
        paramMap.put("start", start);
        paramMap.put("size", size);
        
        List<Answer> answers = answerService.listByMap(paramMap);
        for (Answer answer : answers)
        {
            answer.setCreateTimeStr((CommonUtils.formatTimeStamp(answer.getCreateTime(),
                CommonUtils.getLongDateFormat())));
        }
        result = new AjaxResult(answerService.getCount(paramMap), answers);
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
            answerService.modifyStatus(0, ids);
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
    
}