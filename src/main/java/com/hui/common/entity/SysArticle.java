package com.hui.common.entity;

/**
 * 
 * <系统文章>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysArticle extends BaseEntity
{
    
    private static final long serialVersionUID = -1303425488678301196L;
    /**
     * 主键
     */
    private Integer articleId;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章分类：0-内置文章；1-网站帮助；2-网站公告；
     */
    private Integer articleType;
    
    private String articleTypeStr;
    /**
     * 内容类型：0-HTML；1-URL；
     */
    private Integer contentType;
    
    private String contentTypeStr;
    /**
     * 内容值
     */
    private String contentValue;
    
    private Integer validateFrom;
    
    private String validateFromStr;
    
    private Integer validateTo;
    
    private String validateToStr;
    
    private String validateStr;
    /**
     * 状态：0-显示；1-隐藏；
     */
    private Integer status;
    
    private String statusStr;
    /**
     * 排序
     */
    private Integer sortOrder;
    
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
    
    public String getArticleTypeStr()
    {
        return articleTypeStr;
    }
    
    public void setArticleTypeStr(String articleTypeStr)
    {
        this.articleTypeStr = articleTypeStr;
    }
    
    public Integer getContentType()
    {
        return contentType;
    }
    
    public void setContentType(Integer contentType)
    {
        this.contentType = contentType;
    }
    
    public String getContentTypeStr()
    {
        return contentTypeStr;
    }
    
    public void setContentTypeStr(String contentTypeStr)
    {
        this.contentTypeStr = contentTypeStr;
    }
    
    public String getContentValue()
    {
        return contentValue;
    }
    
    public void setContentValue(String contentValue)
    {
        this.contentValue = contentValue;
    }
    
    public Integer getValidateFrom()
    {
        return validateFrom;
    }
    
    public void setValidateFrom(Integer validateFrom)
    {
        this.validateFrom = validateFrom;
    }
    
    public String getValidateFromStr()
    {
        return validateFromStr;
    }
    
    public void setValidateFromStr(String validateFromStr)
    {
        this.validateFromStr = validateFromStr;
    }
    
    public Integer getValidateTo()
    {
        return validateTo;
    }
    
    public void setValidateTo(Integer validateTo)
    {
        this.validateTo = validateTo;
    }
    
    public String getValidateToStr()
    {
        return validateToStr;
    }
    
    public void setValidateToStr(String validateToStr)
    {
        this.validateToStr = validateToStr;
    }
    
    public String getValidateStr()
    {
        return validateStr;
    }
    
    public void setValidateStr(String validateStr)
    {
        this.validateStr = validateStr;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getStatusStr()
    {
        return statusStr;
    }
    
    public void setStatusStr(String statusStr)
    {
        this.statusStr = statusStr;
    }
    
    public Integer getSortOrder()
    {
        return sortOrder;
    }
    
    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }
    
}