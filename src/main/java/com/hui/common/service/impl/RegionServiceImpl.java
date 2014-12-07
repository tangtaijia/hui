package com.hui.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.ICityDao;
import com.hui.common.dao.ICountyDao;
import com.hui.common.dao.IProvinceDao;
import com.hui.common.entity.City;
import com.hui.common.entity.County;
import com.hui.common.entity.Page;
import com.hui.common.entity.Province;
import com.hui.common.service.IRegionService;

@Service("regionService")
public class RegionServiceImpl implements IRegionService
{
    
    @Autowired
    private IProvinceDao provinceDao;
    @Autowired
    private ICityDao cityDao;
    @Autowired
    private ICountyDao countyDao;

   
    public Page<Province> getProvinces(Integer pageNo)
    {
        Page<Province> page = new Page<Province>();
        page.setPage(pageNo);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<Province> provinces = this.provinceDao.selectAllByMap(paramMap);
        Integer count = this.provinceDao.getCount(paramMap);
        page.setDatas(provinces);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }

   
    public List<Province> getProvinces()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return this.provinceDao.selectAllByMap(paramMap);
    }
    
   
    public Integer addProvince(Province province)
    {
        return this.provinceDao.save(province);
    }
    
   
    public Integer batchAddProvinces(List<Province> provinces)
    {
        return this.provinceDao.batchSave(provinces);
    }

   
    public Page<City> getCitys(Integer pageNo, Integer provinceId)
    {
        Page<City> page = new Page<City>();
        page.setPage(pageNo);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("provinceId", provinceId);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<City> cities = this.cityDao.selectAllByMap(paramMap);
        Integer count = this.cityDao.getCount(paramMap);
        page.setDatas(cities);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }

   
    public List<City> getCitys(Integer provinceId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("provinceId", provinceId);
        List<City> cities = this.cityDao.selectAllByMap(paramMap);
        return cities;
    }
    
   
    public Integer addCity(City city)
    {
        return this.cityDao.save(city);
    }
    
   
    public Integer batchAddCitys(List<City> citys)
    {
        return this.cityDao.batchSave(citys);
    }

   
    public Page<County> getCountys(Integer pageNo, Integer cityId)
    {
        Page<County> page = new Page<County>();
        page.setPage(pageNo);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cityId", cityId);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<County> counties = this.countyDao.selectAllByMap(paramMap);
        Integer count = this.countyDao.getCount(paramMap);
        page.setDatas(counties);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }

   
    public List<County> getCountys(Integer cityId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cityId", cityId);
        List<County> counties = this.countyDao.selectAllByMap(paramMap);
        return counties;
    }
    
   
    public Integer addCounty(County county)
    {
        return this.countyDao.save(county);
    }
    
   
    public Integer batchAddCountys(List<County> countys)
    {
        return this.countyDao.batchSave(countys);
    }
    
}
