package com.hui.common.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hui.common.BaseTest;
import com.hui.common.entity.Page;
import com.hui.common.entity.Trends;
import com.hui.common.entity.User;
public class UserServiceTest extends BaseTest
{
    @Autowired
    private IUserService userService;
    
    @Test
    public void testBindMobile() {
        userService.bindMobile("83505213", "15062294657");
    }
    
    @Test
    public void testResetNickName() {
        userService.resetNickName("83505213", "taijia");
    }
    
    @Test
    public void testFriendsPage() {
        Page<User> page = userService.getMyFriends(1,null, "83505213","c");
System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testFansPage() {
        Page<User> page = userService.getMyFans(1,null, "03668287","c");
        System.out.println("+++++++++++++++page "+JSON.toJSONStringWithDateFormat(page,
            "yyyy-MM-dd HH:mm:ss"));
    }
    
    @Test
    public void testGetTrends() {
        List<Trends> trendsList = userService.getTrendses("83505213", 3);
System.out.println("+++++++++++++++trendsList "+JSON.toJSONStringWithDateFormat(trendsList,
    "yyyy-MM-dd HH:mm:ss"));
    }
    
}
