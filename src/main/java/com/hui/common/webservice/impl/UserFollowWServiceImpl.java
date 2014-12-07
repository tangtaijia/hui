package com.hui.common.webservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IUserDao;
import com.hui.common.dao.IUserFollowDao;
import com.hui.common.entity.Fans;
import com.hui.common.entity.User;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.ConvertObjectField;
import com.hui.common.utils.PaginationConfig;
import com.hui.common.webservice.IUserFollowWService;
import com.hui.common.webservice.EntityView.FansEntityView;

@Service("userFollowWService")
public class UserFollowWServiceImpl implements IUserFollowWService {
	
	@Autowired
	private IUserFollowDao userFollowDao;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserService userService;
	

	
	public void addFollow(String huidaNo, String other, int status) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("myHuiId", huidaNo);
		map.put("otherHuiId", other);
		if(status == 1){
			userFollowDao.addUserFollow(map);
		}
		if(status == 2){
			userFollowDao.deleteUserFollow(map);
		}
	}
	
	
	public List<Object> getFollowList(String huidaNo, int page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		int offset = PaginationConfig.pageOffSet(page);
		paramMap.put("offset", String.valueOf(offset));
		paramMap.put("pagesize", String.valueOf(PaginationConfig.pageSize));
		//获取关注列表
		List<Fans> fansList = userFollowDao.getUserFollowList(paramMap);
		List<Object> resultList = new ArrayList<Object>();
		FansEntityView view = null;
		for(Fans fans:fansList){
			
			view = new FansEntityView();
			view.setHuidaNo(String.valueOf(fans.getFromNo()));
			String nickName = userService.getUser(fans.getFromNo()).getNickName();
			view.setNickname(nickName);
			
			 /*判断是否已关注*/
	        if(CommonUtils.isNotEmptyOrNull(fans.getFromNo())) {
	            Map<String,String> param = new HashMap<String, String>();
	            param.put("myHuiNo", fans.getFromNo());
	            param.put("otherHuiNo", huidaNo);
	            List<Fans> fanss = this.userFollowDao.searchFriends(param);
	            if(CommonUtils.isNotEmptyOrNull(fanss) && fanss.size()>1) {
	            	view.setStatus(2);//互相关注
	            } else {
	                List<Fans> bullyFriends = this.userFollowDao.searchBullyFriends(param);
	                if(CommonUtils.isNotEmptyOrNull(bullyFriends)) {
	                	view.setStatus(1);//已关注
	                }
	            }
	        } else {
	        	view.setStatus(0);
	        }
			
			ConvertObjectField.convertNullValue2Empty(view);
			resultList.add(view);
		}
		return resultList;
	}
	
	public List<Object> getFriendsList(String huidaNo, int page) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		int offset = PaginationConfig.pageOffSet(page);
		paramMap.put("offset", String.valueOf(offset));
		paramMap.put("pagesize", String.valueOf(PaginationConfig.pageSize));
		//获取好友列表
		List<Fans> fansList = userFollowDao.getFriendsList(paramMap);
		List<Object> resultList = new ArrayList<Object>();
		FansEntityView view = null;
		for(Fans fans:fansList){
			view = new FansEntityView();
			view.setHuidaNo(String.valueOf(fans.getToNo())); 
			String nickName = userService.getUser(fans.getToNo()).getNickName();
			view.setNickname(nickName);
			
			 /*判断是否已关注*/
	        if(CommonUtils.isNotEmptyOrNull(fans.getToNo())) {
	            Map<String,String> param = new HashMap<String, String>();
	            param.put("myHuiNo", huidaNo);
	            param.put("otherHuiNo", fans.getToNo());
	            List<Fans> fanss = this.userFollowDao.searchFriends(param);
	            if(CommonUtils.isNotEmptyOrNull(fanss) && fanss.size()>1) {
	            	view.setStatus(2);//互相关注
	            } else {
	                List<Fans> bullyFriends = this.userFollowDao.searchBullyFriends(param);
	                if(CommonUtils.isNotEmptyOrNull(bullyFriends)) {
	                	view.setStatus(1);//已关注
	                }
	            }
	        } else {
	        	view.setStatus(0);
	        }
			ConvertObjectField.convertNullValue2Empty(view);
			resultList.add(view);
		}
		return resultList;
	}

	
	public List<Object> searchFriends(String huidaNo, String key,String page) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("nickName", key);
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		paramMap.put("offset", String.valueOf(PaginationConfig.pageOffSet(Integer.valueOf(page))));
		List<User> userList = userDao.searchUserByNickName(paramMap);
		List<Object> resultList = new ArrayList<Object>();
		FansEntityView view = null;
		for(User user:userList){
			view = new FansEntityView();
			view.setHuidaNo(user.getHuiNo());
			view.setNickname(user.getNickName());
			 /*判断是否已关注*/
	        if(CommonUtils.isNotEmptyOrNull(user.getHuiNo())) {
	            Map<String,String> param = new HashMap<String, String>();
	            param.put("myHuiNo", huidaNo);
	            param.put("otherHuiNo", user.getHuiNo());
	            List<Fans> fanss = this.userFollowDao.searchFriends(param);
	            if(CommonUtils.isNotEmptyOrNull(fanss) && fanss.size()>1) {
	            	view.setStatus(2);//互相关注
	            } else {
	                List<Fans> bullyFriends = this.userFollowDao.searchBullyFriends(param);
	                if(CommonUtils.isNotEmptyOrNull(bullyFriends)) {
	                	view.setStatus(1);//已关注
	                }
	            }
	        } else {
	        	view.setStatus(0);
	        }
			resultList.add(view);
		}
		return resultList;
	}

	
	public int getFollowOrFriendCount(Map<String,String> paramMap) {
		return userFollowDao.getFollowOrFriendCount(paramMap);
	}

	
	public List<Object> searchAllUsers(String huidaNo, String key, String page) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("nickName", key);
		paramMap.put("huidaNo", huidaNo);
		paramMap.put("pageSize", String.valueOf(PaginationConfig.pageSize));
		paramMap.put("offset", String.valueOf(PaginationConfig.pageOffSet(Integer.valueOf(page))));
		List<User> userList = userDao.searchAllUserByNickName(paramMap);
		List<Object> resultList = new ArrayList<Object>();
		FansEntityView view = null;
		for(User user:userList){
			view = new FansEntityView();
			view.setHuidaNo(user.getHuiNo());
			view.setNickname(user.getNickName());
			 /*判断是否已关注*/
	        if(CommonUtils.isNotEmptyOrNull(user.getHuiNo())) {
	            Map<String,String> param = new HashMap<String, String>();
	            param.put("myHuiNo", huidaNo);
	            param.put("otherHuiNo", user.getHuiNo());
	            List<Fans> fanss = this.userFollowDao.searchFriends(param);
	            if(CommonUtils.isNotEmptyOrNull(fanss) && fanss.size()>1) {
	            	view.setStatus(2);//互相关注
	            } else {
	                List<Fans> bullyFriends = this.userFollowDao.searchBullyFriends(param);
	                if(CommonUtils.isNotEmptyOrNull(bullyFriends)) {
	                	view.setStatus(1);//已关注
	                }
	            }
	        } else {
	        	view.setStatus(0);
	        }
			resultList.add(view);
		}
		return resultList;
	}
	
}
