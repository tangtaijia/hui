package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Page;
import com.hui.common.entity.TeacherInfo;
import com.hui.common.utils.CommonUtils;
public class TeacherInfoServiceTest extends BaseTest
{
    @Autowired
    private ITeacherInfoService teacherInfoService;
    
    
    @Test
    public void testGetTeacherInfos() {
        Page<TeacherInfo> page = teacherInfoService.getTeacherInfos(1,null, "03668287", "汤", 1,null,null);
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testGetTeacherInfo() {
        TeacherInfo teacherInfo = teacherInfoService.teacherInfoDetail(1);
        long now = System.currentTimeMillis();
        System.out.println(now);
        long now1 = CommonUtils.getNowInt();
        System.out.println(1401003139319l-1400000000000l);
        System.out.println(now1);
System.out.println("+++++++++++++++teacherInfo "+JSON.toJSONStringWithDateFormat(teacherInfo,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    public static void main(String[] args)
    {
        System.out.println((1401003139319l-1400000000000l)/1000/60/60/24);
        System.out.println(666665/360/24);
    }
    
    @Test
    public void fitOnline() {
        teacherInfoService.fitOnLine(1);
    }
    
    @Test
    public void fitOffline() {
        teacherInfoService.fitOffLine(1);
    }
    
}
