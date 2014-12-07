package com.hui.common.entity;

/**
 * 省
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Province extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8521559468075290001L;

    private Integer provinceId;
    
    private String provinceName;
    
    public Province() {};
    
    public Province(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getProvinceId()
    {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId)
    {
        this.provinceId = provinceId;
    }

    public String getProvinceName()
    {
        return provinceName;
    }

    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }

}
