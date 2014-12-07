package com.hui.ajax;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysArticle;
import com.hui.common.entity.SysFile;
import com.hui.common.service.ISysArticleService;
import com.hui.common.service.ISysFileService;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AjaxSysArticleAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = -6310081960821430780L;
    
    private ISysArticleService sysArticleService;
    
    private ISysFileService sysFileService;
    
    private String editorContent;
    
    private String delIds;
    
    private Integer articleId;
    
    private String articleTitle;
    
    private Integer articleType;
    
    private Integer contentType;
    
    private String contentValue;
    
    private String validateFromStr;
    
    private String validateToStr;
    
    private Integer status;
    
    private Integer sortOrder;
    
    private Integer id;
    
    private Integer value;
    
    private AjaxResult result;
    
    private String rows;
    
    private String page;
    
    public void setSysArticleService(ISysArticleService sysArticleService)
    {
        this.sysArticleService = sysArticleService;
    }
    
    public void setSysFileService(ISysFileService sysFileService)
    {
        this.sysFileService = sysFileService;
    }
    
    public String getEditorContent()
    {
        return editorContent;
    }
    
    public void setEditorContent(String editorContent)
    {
        this.editorContent = editorContent;
    }
    
    public String getDelIds()
    {
        return delIds;
    }
    
    public void setDelIds(String delIds)
    {
        this.delIds = delIds;
    }
    
    public Integer getArticleId()
    {
        return articleId;
    }
    
    public void setArticleId(Integer articleId)
    {
        this.articleId = articleId;
    }
    
    public String getArticleTitle()
    {
        return articleTitle;
    }
    
    public void setArticleTitle(String articleTitle)
    {
        this.articleTitle = articleTitle;
    }
    
    public Integer getArticleType()
    {
        return articleType;
    }
    
    public void setArticleType(Integer articleType)
    {
        this.articleType = articleType;
    }
    
    public Integer getContentType()
    {
        return contentType;
    }
    
    public void setContentType(Integer contentType)
    {
        this.contentType = contentType;
    }
    
    public String getContentValue()
    {
        return contentValue;
    }
    
    public void setContentValue(String contentValue)
    {
        this.contentValue = contentValue;
    }
    
    public String getValidateFromStr()
    {
        return validateFromStr;
    }
    
    public void setValidateFromStr(String validateFromStr)
    {
        this.validateFromStr = validateFromStr;
    }
    
    public String getValidateToStr()
    {
        return validateToStr;
    }
    
    public void setValidateToStr(String validateToStr)
    {
        this.validateToStr = validateToStr;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public Integer getSortOrder()
    {
        return sortOrder;
    }
    
    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public Integer getValue()
    {
        return value;
    }
    
    public void setValue(Integer value)
    {
        this.value = value;
    }
    
    public AjaxResult getResult()
    {
        return result;
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
    
    public String toget()
    {
        try
        {
            SysArticle ai = sysArticleService.selectById(articleId);
            if (null != ai)
            {
                ai.setArticleTypeStr((String)CommonUtils.getSysData("article_type")
                    .getValue()
                    .get(Integer.toString(ai.getArticleType())));
                ai.setContentTypeStr((String)CommonUtils.getSysData("content_type")
                    .getValue()
                    .get(Integer.toString(ai.getContentType())));
                if (0 != ai.getValidateFrom())
                {
                    ai.setValidateFromStr(CommonUtils.formatTimeStamp(ai.getValidateFrom(),
                        CommonUtils.getLongDateFormat()));
                }
                if (0 != ai.getValidateTo())
                {
                    ai.setValidateToStr(CommonUtils.formatTimeStamp(ai.getValidateTo(),
                        CommonUtils.getLongDateFormat()));
                }
                
                SysFile fi = new SysFile();
                fi.setDataId(articleId);
                fi.setFileType(3);
                
                result = new AjaxResult(true, sysFileService.list(fi), ai);
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
        
        SysArticle ai = new SysArticle();
        ai.setStart(start);
        ai.setSize(size);
        
        String validateStr = null;
        
        List<SysArticle> sysArticles = sysArticleService.list(ai);
        if (null != sysArticles && 0 != sysArticles.size())
        {
            for (SysArticle sysArticle : sysArticles)
            {
                validateStr = "";
                
                sysArticle.setArticleTypeStr((String)CommonUtils.getSysData("article_type")
                    .getValue()
                    .get(Integer.toString(sysArticle.getArticleType())));
                sysArticle.setContentTypeStr((String)CommonUtils.getSysData("content_type")
                    .getValue()
                    .get(Integer.toString(sysArticle.getContentType())));
                sysArticle.setStatusStr((String)CommonUtils.getSysData("show_status")
                    .getValue()
                    .get(Integer.toString(sysArticle.getStatus())));
                if (0 != sysArticle.getValidateFrom())
                {
                    validateStr =
                        "从&nbsp;"
                            + CommonUtils.formatTimeStamp(sysArticle.getValidateFrom(),
                                CommonUtils.getLongDateFormat());
                }
                if (0 != sysArticle.getValidateTo())
                {
                    validateStr +=
                        "&nbsp;到&nbsp;"
                            + CommonUtils.formatTimeStamp(sysArticle.getValidateTo(),
                                CommonUtils.getLongDateFormat());
                }
                sysArticle.setValidateStr(validateStr);
            }
        }
        
        result = new AjaxResult(sysArticleService.getCount(ai), sysArticles);
        
        return SUCCESS;
    }
    
    public String tocreate()
    {
        try
        {
            SysArticle ai = new SysArticle();
            ai.setArticleTitle(articleTitle);
            ai.setArticleType(articleType);
            ai.setContentType(contentType);
            ai.setContentValue(StringUtils.isBlank(contentValue) ? "" : contentValue.trim());
            ai.setValidateFrom(StringUtils.isBlank(validateFromStr) ? 0 : CommonUtils.getTimeStamp(validateFromStr));
            ai.setValidateTo(StringUtils.isBlank(validateToStr) ? 0 : CommonUtils.getTimeStamp(validateToStr));
            ai.setStatus(status);
            ai.setSortOrder(sortOrder);
            
            sysArticleService.saveEntity(ai);
            
            result = new AjaxResult(true, "新增成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = new AjaxResult(false, "新增失败");
        }
        
        return SUCCESS;
    }
    
    public String tomodify()
    {
        try
        {
            SysArticle ai = sysArticleService.selectById(articleId);
            if (null != ai)
            {
                ai.setArticleTitle(articleTitle);
                if (ai.getContentType() == 1)
                {
                    ai.setContentValue(StringUtils.isBlank(contentValue) ? "" : contentValue.trim());
                }
                ai.setValidateFrom(StringUtils.isBlank(validateFromStr) ? 0
                    : CommonUtils.getTimeStamp(validateFromStr));
                ai.setValidateTo(StringUtils.isBlank(validateToStr) ? 0 : CommonUtils.getTimeStamp(validateToStr));
                ai.setStatus(status);
                ai.setSortOrder(sortOrder);
                
                sysArticleService.updateEntity(ai);
                
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
    
    public String tomodifycontent()
    {
        try
        {
            SysArticle ai = sysArticleService.selectById(articleId);
            if (null != ai)
            {
                ai.setContentValue(editorContent);
                
                sysArticleService.updateEntity(ai);
                
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
    
    public String tomodifySortOrder()
    {
        try
        {
            SysArticle ai = sysArticleService.selectById(id);
            if (null != ai)
            {
                ai.setSortOrder(value);
                
                sysArticleService.updateEntity(ai);
                
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
    
    @SuppressWarnings("deprecation")
    public String todel()
    {
        try
        {
            List<Integer> ids = new ArrayList<Integer>();
            String[] idArray = StringUtils.split(delIds, ',');
            SysFile fi = null;
            List<SysFile> sysFiles = null;
            File tempFile = null;
            
            for (String id : idArray)
            {
                ids.add(Integer.valueOf(id));
                
                // 删除文章图片
                fi = new SysFile();
                fi.setDataId(Integer.valueOf(id));
                fi.setFileType(3);
                
                sysFiles = sysFileService.list(fi);
                
                for (SysFile sysFile : sysFiles)
                {
                    tempFile =
                        new File(ServletActionContext.getRequest().getRealPath(sysFile.getFilePath()),
                            sysFile.getFileName());
                    if (null != tempFile && tempFile.exists())
                    {
                        tempFile.delete();
                    }
                }
                
                sysFileService.delete(fi);
            }
            sysArticleService.delete(ids);
            
            result = new AjaxResult(true, "删除成功");
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "删除失败");
        }
        
        return SUCCESS;
    }
    
}