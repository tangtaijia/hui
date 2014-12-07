package com.hui.common.entity;

import java.io.Serializable;

/**
 * 地区
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-20]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Region implements Serializable
{
    /**
	 * 注释内容
	 */
	private static final long serialVersionUID = 3754783043337141638L;
	private Province province;
    private City city;
    private County county;
    
    public Region(){}
    
    public Region(Province province,City city,County county) {
        this.province = province;
        this.city = city;
        this.county = county;
    }
    
    public Province getProvince()
    {
        return province;
    }
    public void setProvince(Province province)
    {
        this.province = province;
    }
    public City getCity()
    {
        return city;
    }
    public void setCity(City city)
    {
        this.city = city;
    }
    public County getCounty()
    {
        return county;
    }
    public void setCounty(County county)
    {
        this.county = county;
    }
}
