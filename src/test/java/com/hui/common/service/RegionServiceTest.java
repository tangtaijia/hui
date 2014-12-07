package com.hui.common.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.City;
import com.hui.common.entity.County;
import com.hui.common.entity.Page;
import com.hui.common.entity.Province;
import com.hui.common.utils.CommonUtils;
public class RegionServiceTest extends BaseTest
{
    @Autowired
    private IRegionService regionService;
    
    private SAXReader reader = null;
    private Document doc;
    private DocumentFactory df = DocumentFactory.getInstance();
    
    @Test
    public void testProvincePage() {
        Page<Province> page = regionService.getProvinces(1);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testAddProvice() {
        Province province = new Province();
        province.setProvinceName("湖南省");
        regionService.addProvince(province);
    }
    
    @Test
    public void testCityPage() {
        Page<City> page = regionService.getCitys(1,1);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testAddCity() {
        City city = new City();
        city.setCityName("长沙市");
        city.setProvinceId(6);
        regionService.addCity(city);
    }
    
    @Test
    public void testCountyPage() {
        Page<County> page = regionService.getCountys(1,110100);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testAddCounty() {
        County county = new County();
        county.setCountyName("岳麓区");
        county.setCityId(14);
        regionService.addCounty(county);
    }
    
    @Test
    public void initRegions() {
        inport(new File("D:\\MyAppDesign\\汇答题\\doc\\p.xml"), new File("D:\\MyAppDesign\\汇答题\\doc\\c.xml"), new File("D:\\MyAppDesign\\汇答题\\doc\\a.xml"));
    }
    
    @SuppressWarnings("unchecked")
    public void inport(File p, File c, File a) {
        reader = new SAXReader(df);
        try {
            doc = reader.read(p);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Element root = doc.getRootElement();
        List<Element> pList = root.selectNodes("/data/province");

        List<Province> provinces = new ArrayList<Province>();
        for (Element pEle : pList) {
            System.out.println(pEle.attributeValue("id") + " \t"
                    + pEle.attributeValue("name"));
            Province province = new Province();
            province.setProvinceName(pEle.attributeValue("name"));
            province.setProvinceId(Integer.valueOf(pEle.attributeValue("id")));
            provinces.add(province);
        }
        if(CommonUtils.isNotEmptyOrNull(provinces)) {
            this.regionService.batchAddProvinces(provinces);
        }
        // ////////////////////////////////////////////////////////////////////////

        try {
            doc = reader.read(c);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        root = doc.getRootElement();
        List<Element> cList = root.selectNodes("/data/city");

        List<City> citys = new ArrayList<City>();
        for (Element cEle : cList) {
            System.out.println(cEle.attributeValue("id") + " \t"
                    + cEle.attributeValue("name") + "\t"
                    + cEle.attributeValue("province"));
            City city = new City();
            city.setCityId(Integer.valueOf(cEle.attributeValue("id")));
            city.setCityName(cEle.attributeValue("name"));
            city.setProvinceId(Integer.valueOf(cEle.attributeValue("province")));
            citys.add(city);
        }
        if(CommonUtils.isNotEmptyOrNull(citys)) {
            this.regionService.batchAddCitys(citys);
        }

        // ////////////////////////////////////////////////////////////////////////

        try {
            doc = reader.read(a);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        root = doc.getRootElement();
        List<Element> aList = root.selectNodes("/data/area");

        List<County> countys = new ArrayList<County>();
        for (Element aEle : aList) {
            System.out.println(aEle.attributeValue("id") + " \t"
                    + aEle.attributeValue("name") + "\t"
                    + aEle.attributeValue("city") + "\t"
                    + aEle.attributeValue("zipCode") + "\t"
                    + aEle.attributeValue("areaCode") + "\t");

            County county = new County();
            county.setCountyId(Integer.valueOf(aEle.attributeValue("id")));
            county.setCityId(Integer.valueOf(aEle.attributeValue("city")));
            county.setCountyName(aEle.attributeValue("name"));
            countys.add(county);
        }
        if(CommonUtils.isNotEmptyOrNull(countys)) {
            this.regionService.batchAddCountys(countys);
        }
    }
    
}
