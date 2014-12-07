package com.hui.webservicetest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Before;
import org.junit.Test;

public class UserFollowTest {
	
	private HttpClient client;
	
	@Before
	public void setUp(){
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost",8080,"http");
	}
	
	@Test
	public void getFollowListTest() throws HttpException, IOException {
		System.out.println("invoke getFollowListTest http...");
        PostMethod post = new PostMethod("/hui/interface/getFollowList.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "83505213");
		NameValuePair page = new NameValuePair("page", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,page});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}

	@Test
	public void addFollowTest() throws HttpException, IOException {
		System.out.println("invoke addFollowTestTest http...");
        PostMethod post = new PostMethod("/hui/interface/addFollow.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607543");
		NameValuePair other = new NameValuePair("other", "2222222");
		NameValuePair status = new NameValuePair("status", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,other,status});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void deleteFollowTest() throws HttpException, IOException {
		System.out.println("invoke deleteFollowTest http...");
        PostMethod post = new PostMethod("/hui/interface/addFollow.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607543");
		NameValuePair other = new NameValuePair("other", "2222222");
		NameValuePair status = new NameValuePair("status", "2");
		post.setRequestBody(new NameValuePair[]{huidaNo,other,status});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	
	
	@Test
	public void addFriendTest() throws HttpException, IOException {
		System.out.println("invoke addFollowTestTest http...");
        PostMethod post = new PostMethod("/hui/interface/addFriend.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607543");
		NameValuePair other = new NameValuePair("other", "333333");
		NameValuePair status = new NameValuePair("status", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,other,status});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void deleteFriendTest() throws HttpException, IOException {
		System.out.println("invoke deleteFriendTest http...");
        PostMethod post = new PostMethod("/hui/interface/addFollow.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607543");
		NameValuePair other = new NameValuePair("other", "333333");
		NameValuePair status = new NameValuePair("status", "2");
		post.setRequestBody(new NameValuePair[]{huidaNo,other,status});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getFriendsListTest() throws HttpException, IOException {
		System.out.println("invoke getFriendsListTest http...");
        PostMethod post = new PostMethod("/hui/interface/getFollowList.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "83505213");
		NameValuePair page = new NameValuePair("page", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,page});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void searchFriendsTest() throws HttpException, IOException {
		System.out.println("invoke searchFriendsTest http...");
        PostMethod post = new PostMethod("/hui/interface/searchFriends.hui");
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		NameValuePair value1 = new NameValuePair("huidaNo", "93607543");
		NameValuePair value2 = new NameValuePair("key","测试");
		NameValuePair value3 = new NameValuePair("page","1");
		post.setRequestBody(new NameValuePair[]{value1,value2,value3});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getFollowsTest() throws HttpException, IOException {
		System.out.println("invoke getFollowsTest http...");
        PostMethod post = new PostMethod("/hui/interface/getFollows.hui");
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607543");
		post.setRequestBody(new NameValuePair[]{huidaNo});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getFriendsTest() throws HttpException, IOException {
		System.out.println("invoke getFriendsTest http...");
        PostMethod post = new PostMethod("/hui/interface/getFriends.hui");
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607543");
		post.setRequestBody(new NameValuePair[]{huidaNo});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void isMyFriendTest() throws HttpException, IOException {
		System.out.println("invoke isMyFriendTest http...");
        PostMethod post = new PostMethod("/hui_ah/interface/isMyFriend.hui");
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		NameValuePair value1 = new NameValuePair("huidaNo", "3662147");
		NameValuePair value2 = new NameValuePair("other", "83505213");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void searchAllUsersTest() throws HttpException, IOException {
		System.out.println("invoke searchAllUsersTest http...");
        PostMethod post = new PostMethod("/hui/interface/searchAllUsers.hui");
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		NameValuePair value1 = new NameValuePair("huidaNo", "93607543");
		NameValuePair value2 = new NameValuePair("key","测试");
		NameValuePair value3 = new NameValuePair("page","1");
		post.setRequestBody(new NameValuePair[]{value1,value2,value3});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
}
