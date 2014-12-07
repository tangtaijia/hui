package com.hui.webservicetest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

public class UserInfoTest{
	
	
	private HttpClient client;
	
	@Before
	public void setUp(){
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost",8080,"http");
	}

	@Test
	public void loginTest() throws HttpException, IOException {
        System.out.println("invoke loginTest http...");
        PostMethod post = new PostMethod("/hui/interface/login.hui");
		NameValuePair key = new NameValuePair("key", "13913851051");
		NameValuePair pwd = new NameValuePair("pwd", "111111");
		post.setRequestBody(new NameValuePair[]{key,pwd});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	
	@Test
	public void isNicknameExistsTest() throws HttpException, IOException {
		System.out.println("invoke isNicknameExistsTest http...");
        PostMethod post = new PostMethod("/hui/interface/isNicknameExists.hui");
		NameValuePair key = new NameValuePair("nickname", "zhangxian");
		post.setRequestBody(new NameValuePair[]{key});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void isMobileExistsTest() throws HttpException, IOException {
		System.out.println("invoke isMobileExistsTest http...");
        PostMethod post = new PostMethod("/hui/interface/isMobileExists.hui");
		NameValuePair key = new NameValuePair("mobile", "13913851051");
		post.setRequestBody(new NameValuePair[]{key});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void registerTest() throws HttpException, IOException {
		System.out.println("invoke registerTest http...");
        PostMethod post = new PostMethod("/hui/interface/register.hui");
		NameValuePair value1 = new NameValuePair("mobile", "13913851052");
		NameValuePair value2 = new NameValuePair("pwd", "111111");
		NameValuePair value3 = new NameValuePair("nickname", "张贤");
		post.setRequestBody(new NameValuePair[]{value1,value2,value3});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}
	
	
	@Test
	public void resetPasswordTest() throws HttpException, IOException {
		System.out.println("invoke resetPasswordTest http...");
        PostMethod post = new PostMethod("/hui/interface/resetPassword.hui");
		NameValuePair value1 = new NameValuePair("mobile", "13913851051");
		NameValuePair value2 = new NameValuePair("newPassword", "222222");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
        
	}
	
	@Test
	public void getOtherInformationTest() throws HttpException, IOException {
		System.out.println("invoke getOtherInformationTest http...");
        PostMethod post = new PostMethod("/hui/interface/getOtherInformation.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607543");
		NameValuePair value2 = new NameValuePair("other", "93607544");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}
	
	@Test
	public void getNicknameTest() throws HttpException, IOException {
		System.out.println("invoke getNicknameTest http...");
        PostMethod post = new PostMethod("/hui/interface/getNickname.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		post.setRequestBody(new NameValuePair[]{value1});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}
	
	@Test
	public void getHuiDouCountTest() throws HttpException, IOException {
		System.out.println("invoke getHuiDouCountTest http...");
        PostMethod post = new PostMethod("/hui/interface/getHuiDouCount.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		post.setRequestBody(new NameValuePair[]{value1});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}
	
	@Test
	public void modifyNicknameTest() throws HttpException, IOException {
		System.out.println("invoke modifyNicknameTest http...");
        PostMethod post = new PostMethod("/hui/interface/modifyNickname.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		NameValuePair value2 = new NameValuePair("nickname", "uuuuuuuu");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}
	
	@Test
	public void bindingTest() throws HttpException, IOException {
		System.out.println("invoke bindingTest http...");
        PostMethod post = new PostMethod("/hui/interface/binding.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		NameValuePair value2 = new NameValuePair("mobile", "13913900000");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}
	
	@Test
	public void modifyPasswordTest() throws HttpException, IOException {
		System.out.println("invoke modifyPasswordTest http...");
        PostMethod post = new PostMethod("/hui/interface/modifyPassword.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		NameValuePair value2 = new NameValuePair("oldPassword", "111111");
		NameValuePair value3 = new NameValuePair("newPassword", "333333");
		post.setRequestBody(new NameValuePair[]{value1,value2,value3});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}
	
	
	@Test
	public void sendIdentifyingCodeTest() throws HttpException, IOException {
		System.out.println("invoke sendIdentifyingCode http...");
        PostMethod post = new PostMethod("/hui/interface/sendIdentifyingCode.hui");
		NameValuePair value1 = new NameValuePair("mobile", "13913851051");
		post.setRequestBody(new NameValuePair[]{value1});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}
	
	@Test
	public void getMobileTest() throws HttpException, IOException {
		System.out.println("invoke getMobileCode http...");
        PostMethod post = new PostMethod("/hui/interface/getMobile.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "32555571");
		post.setRequestBody(new NameValuePair[]{value1});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));  
	}

}
