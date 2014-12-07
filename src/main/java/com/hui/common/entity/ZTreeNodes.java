package com.hui.common.entity;

/**
 * 
 * <树节点>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ZTreeNodes extends BaseEntity
{
    
    private static final long serialVersionUID = -4173163761242807496L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 父节点id
     */
    private Integer pId;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否打开
     */
    private boolean open = true;
    /**
     * 是否选中
     */
    private boolean checked = false;
    /**
     * 链接
     */
    private String iframeUrl;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public Integer getpId()
    {
        return pId;
    }
    
    public void setpId(Integer pId)
    {
        this.pId = pId;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public boolean isOpen()
    {
        return open;
    }
    
    public void setOpen(boolean open)
    {
        this.open = open;
    }
    
    public boolean isChecked()
    {
        return checked;
    }
    
    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
    
    public String getIframeUrl()
    {
        return iframeUrl;
    }
    
    public void setIframeUrl(String iframeUrl)
    {
        this.iframeUrl = iframeUrl;
    }
    
}