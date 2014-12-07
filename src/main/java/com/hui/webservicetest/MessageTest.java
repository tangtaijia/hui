package com.hui.webservicetest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

public class MessageTest {

	private HttpClient client;

	@Before
	public void setUp() {
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost", 8080, "http");
	}

	@Test
	public void getAnswerMeCountByUnreadTest() throws HttpException, IOException {
		System.out.println("invoke getAnswerMeCountByUnreadTest http...");
		PostMethod post = new PostMethod("/hui/interface/getAnswerMeCountByUnread.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		post.setRequestBody(new NameValuePair[]{huidaNo});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getAskMeCountByUnreadTest() throws HttpException, IOException {
		System.out.println("invoke getAskMeCountByUnreadTest http...");
		PostMethod post = new PostMethod("/hui/interface/getAskMeCountByUnread.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		post.setRequestBody(new NameValuePair[]{huidaNo});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getAcceptMeCountByUnreadTest() throws HttpException, IOException {
		System.out.println("invoke getAcceptMeCountByUnreadTest http...");
		PostMethod post = new PostMethod("/hui/interface/getAcceptMeCountByUnread.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		post.setRequestBody(new NameValuePair[]{huidaNo});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getSystemMessageByUnreadTest() throws HttpException, IOException {
		System.out.println("invoke getSystemMessageByUnreadTest http...");
		PostMethod post = new PostMethod("/hui/interface/getSystemMessageByUnread.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		post.setRequestBody(new NameValuePair[]{huidaNo});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getAnswerMeListTest() throws HttpException, IOException {
		System.out.println("invoke getAnswerMeListTest http...");
		PostMethod post = new PostMethod("/hui/interface/getAnswerMeList.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		NameValuePair page = new NameValuePair("page", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,page});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getAskMeListTest() throws HttpException, IOException {
		System.out.println("invoke getAskMeListTest http...");
		PostMethod post = new PostMethod("/hui/interface/getAskMeList.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		NameValuePair page = new NameValuePair("page", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,page});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getAcceptMeListTest() throws HttpException, IOException {
		System.out.println("invoke getAcceptMeListTest http...");
		PostMethod post = new PostMethod("/hui/interface/getAcceptMeList.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		NameValuePair page = new NameValuePair("page", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,page});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getSystemMessageListTest() throws HttpException, IOException {
		System.out.println("invoke getSystemMessageListTest http...");
		PostMethod post = new PostMethod("/hui/interface/getSystemMessageList.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		NameValuePair page = new NameValuePair("page", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,page});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getSystemMessageDetailTest() throws HttpException, IOException {
		System.out.println("invoke getSystemMessageDetailTest http...");
		PostMethod post = new PostMethod("/hui/interface/getSystemMessageDetail.hui");
		NameValuePair huidaNo = new NameValuePair("messageId", "3");
		post.setRequestBody(new NameValuePair[]{huidaNo});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void setMessageReadTest() throws HttpException, IOException {
		System.out.println("invoke setMessageReadTest http...");
		PostMethod post = new PostMethod("/hui/interface/setMessageRead.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		NameValuePair value2 = new NameValuePair("messageId", "10");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void setAnswerMeReadTest() throws HttpException, IOException {
		System.out.println("invoke setAnswerMeReadTest http...");
		PostMethod post = new PostMethod("/hui/interface/setAnswerMeRead.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		NameValuePair value2 = new NameValuePair("questionId", "2");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void setAskMeReadTest() throws HttpException, IOException {
		System.out.println("invoke setAskMeReadTest http...");
		PostMethod post = new PostMethod("/hui/interface/setAskMeRead.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		NameValuePair value2 = new NameValuePair("questionId", "2");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void setAcceptMeReadTest() throws HttpException, IOException {
		System.out.println("invoke setAcceptMeReadTest http...");
		PostMethod post = new PostMethod("/hui/interface/setAcceptMeRead.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607544");
		NameValuePair value2 = new NameValuePair("questionId", "2");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	

}
