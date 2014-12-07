package com.hui.common.entity;

/**
 * 区县
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class County extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1240112300047649223L;

    private Integer countyId;
    
    private String countyName;
    
    private Integer cityId;
    
    public County(){};
    
    public County(Integer countyId) {
        this.countyId = countyId;
    }

    public Integer getCountyId()
    {
        return countyId;
    }

    public void setCountyId(Integer countyId)
    {
        this.countyId = countyId;
    }

    public String getCountyName()
    {
        return countyName;
    }

    public void setCountyName(String countyName)
    {
        this.countyName = countyName;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }
}
