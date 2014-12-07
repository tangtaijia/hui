package com.hui.common.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hui.common.BaseTest;
public class UserFollowServiceTest extends BaseTest
{
    @Autowired
    private IUserFollowService userFollowService;
    
    @Test
    public void testAddFollow() {
        userFollowService.addUserFollow("83505213", "03668287");
        userFollowService.addUserFollow("03668287", "83505213");
    }
    
    @Test
    public void testDeleteFollow() {
        userFollowService.deleteUserFollow("83505213", "03668287");
    }
    
}
