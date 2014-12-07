package com.hui.common.entity;

/**
 * 帮助
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Help extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4726950918919478382L;
    
    /**
     * 主键
     */
    private Integer helpId;
    /**
     * 帮助标题
     */
    private String helpTitle;
    /**
     * 帮助描述
     */
    private String helpDesc;
    
    public Integer getHelpId()
    {
        return helpId;
    }
    public void setHelpId(Integer helpId)
    {
        this.helpId = helpId;
    }
    public String getHelpTitle()
    {
        return helpTitle;
    }
    public void setHelpTitle(String helpTitle)
    {
        this.helpTitle = helpTitle;
    }
    public String getHelpDesc()
    {
        return helpDesc;
    }
    public void setHelpDesc(String helpDesc)
    {
        this.helpDesc = helpDesc;
    }
    
}
