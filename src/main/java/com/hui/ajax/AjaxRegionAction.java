package com.hui.ajax;

import java.util.List;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.City;
import com.hui.common.entity.County;
import com.hui.common.entity.Province;
import com.hui.common.service.IRegionService;
import com.hui.common.utils.CommonUtils;

public class AjaxRegionAction extends BaseActionSupport
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5993077924327222087L;
    
    private IRegionService regionService;
    
    private List<Province> provinces;
    
    private List<City> cities;
    
    private List<County> counties;
    
    private Integer provinceId;
    
    private Integer cityId;
    
    private AjaxResult result;
    
    /**
     * 查询省份
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String fetchProvinces() {
        try
        {
            provinces = regionService.getProvinces();
            if(CommonUtils.isNotEmptyOrNull(provinces)) {
                result = new AjaxResult(true, provinces);
            } else {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "查询失败");
        }
        return SUCCESS;
    }
    
    /**
     * 查询城市
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String fetchCities() {
        try
        {
        	if(CommonUtils.isNotEmptyOrNullOr0OrFalse(provinceId)) {
        		cities = regionService.getCitys(provinceId);
        	}
            if(CommonUtils.isNotEmptyOrNull(cities)) {
                result = new AjaxResult(true, cities);
            } else {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "查询失败");
        }
        return SUCCESS;
    }
    
    /**
     * 查询区县
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String fetchCounties() {
        try
        {
        	if(CommonUtils.isNotEmptyOrNullOr0OrFalse(cityId)) {
        		counties = regionService.getCountys(cityId);
        	}
            if(CommonUtils.isNotEmptyOrNull(counties)) {
                result = new AjaxResult(true, counties);
            } else {
                result = new AjaxResult(false, "数据不存在");
            }
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "查询失败");
        }
        return SUCCESS;
    }

    public List<Province> getProvinces()
    {
        return provinces;
    }

    public List<City> getCities()
    {
        return cities;
    }

    public List<County> getCounties()
    {
        return counties;
    }

    public void setRegionService(IRegionService regionService)
    {
        this.regionService = regionService;
    }
    
    public void setProvinceId(Integer provinceId)
    {
        this.provinceId = provinceId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public AjaxResult getResult()
    {
        return result;
    }

}
