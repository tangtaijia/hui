package com.hui.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IUserFollowDao;
import com.hui.common.entity.Fans;
import com.hui.common.service.IUserFollowService;
import com.hui.common.utils.CommonUtils;

@Service("userFollowService")
public class UserFollowServiceImpl extends BaseServiceImpl<Fans, Fans> implements IUserFollowService
{
    @Autowired
    private IUserFollowDao userFollowDao;
    
   
    public void addUserFollow(String sourceHuiNo, String targetHuiNo)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("myHuiId", sourceHuiNo);
        paramMap.put("otherHuiId", targetHuiNo);
        paramMap.put("fromHuiNo", sourceHuiNo);
        paramMap.put("toHuiNo", targetHuiNo);
        Integer count = userFollowDao.getFollowOrFriendCount(paramMap);
        if(CommonUtils.isNotEmptyOrNullOr0OrFalse(count)) {
            return;
        }
        try
        {
            userFollowDao.addUserFollow(paramMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
   
    public void deleteUserFollow(String sourceHuiNo, String targetHuiNo)
    {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("myHuiId", sourceHuiNo);
        paramMap.put("otherHuiId", targetHuiNo);
        Integer count = userFollowDao.getFollowOrFriendCount(paramMap);
        if(CommonUtils.isEmptyOrNullOr0OrFalse(count)) {
            return;
        }
        try
        {
            userFollowDao.deleteUserFollow(paramMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
}
