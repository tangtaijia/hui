package com.hui.common.entity;

/**
 * 城市
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class City extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 3482238502698394226L;

    private Integer cityId;
    
    private String cityName;
    
    private Integer provinceId;
    
    public City(){};
    
    public City(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public Integer getProvinceId()
    {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId)
    {
        this.provinceId = provinceId;
    }
}
