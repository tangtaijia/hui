package com.hui.common.entity;

import java.io.Serializable;

/**
 * 生日日期
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BirthDate implements Serializable
{
    /**
	 * 注释内容
	 */
	private static final long serialVersionUID = -6265429629159060017L;
	private Integer year;
    private Integer month;
    private Integer day;
    
    public BirthDate() {}
    
    public BirthDate(Integer year,Integer month,Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public Integer getYear()
    {
        return year;
    }
    public void setYear(Integer year)
    {
        this.year = year;
    }
    public Integer getMonth()
    {
        return month;
    }
    public void setMonth(Integer month)
    {
        this.month = month;
    }
    public Integer getDay()
    {
        return day;
    }
    public void setDay(Integer day)
    {
        this.day = day;
    }
}
