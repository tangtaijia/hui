package com.hui.common.action;

import com.opensymphony.xwork2.interceptor.ScopedModelDriven;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ModelDrivenActionSupport<T> extends BaseActionSupport implements ScopedModelDriven<T>
{
    
    private static final long serialVersionUID = 8221961386253501354L;
    
    private T model;
    
    private String scope;
    
    public void setModel(T model)
    {
        this.model = model;
    }
    
    public T getModel()
    {
        return model;
    }
    
    public String getScopeKey()
    {
        return this.scope;
    }
    
    public void setScopeKey(String arg0)
    {
        this.scope = arg0;
    }
    
}