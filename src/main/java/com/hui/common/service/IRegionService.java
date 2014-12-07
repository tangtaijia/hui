package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.City;
import com.hui.common.entity.County;
import com.hui.common.entity.Page;
import com.hui.common.entity.Province;

/**
 * 地区Service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IRegionService
{
    /**
     * 分页查询省份
     * <功能详细描述>
     * @param pageNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<Province> getProvinces(Integer pageNo);
    /**
     * 省份列表
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Province> getProvinces();
    
    /**
     * 添加省份
     * <功能详细描述>
     * @param province
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer addProvince(Province province);
    
    /**
     * 批量插入省
     * <功能详细描述>
     * @param provinces
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer batchAddProvinces(List<Province> provinces);
    
    /**
     * 根据省份id分页查询城市
     * <功能详细描述>
     * @param pageNo
     * @param provinceId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<City> getCitys(Integer pageNo,Integer provinceId);
    
    /**
     * 根据省份id查询城市列表
     * <功能详细描述>
     * @param provinceId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<City> getCitys(Integer provinceId);
    
    /**
     * 添加城市
     * <功能详细描述>
     * @param city
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer addCity(City city);
    
    /**
     * 批量插入城市
     * <功能详细描述>
     * @param citys
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer batchAddCitys(List<City> citys);
    
    /**
     * 根据城市id分页查询区县
     * <功能详细描述>
     * @param pageNo
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<County> getCountys(Integer pageNo,Integer cityId);
    
    /**
     * 根据城市id查询区县列表
     * <功能详细描述>
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<County> getCountys(Integer cityId);
    
    /**
     * 添加区县
     * <功能详细描述>
     * @param county
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer addCounty(County county);
    
    /**
     * 批量插入区县
     * <功能详细描述>
     * @param countys
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer batchAddCountys(List<County> countys);
}
