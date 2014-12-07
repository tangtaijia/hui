package com.hui.webservicetest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

public class PraiseTest {
	
	private HttpClient client;

	@Before
	public void setUp() {
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost", 8080, "http");
	}

	@Test
	public void praiseTest() throws HttpException, IOException {
		System.out.println("invoke praiseTest http...");
		PostMethod post = new PostMethod("/hui/interface/praise.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		NameValuePair answerId = new NameValuePair("answerId", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,answerId});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void hasPraisedTest() throws HttpException, IOException {
		System.out.println("invoke hasPraisedTest http...");
		PostMethod post = new PostMethod("/hui/interface/hasPraised.hui");
		NameValuePair huidaNo = new NameValuePair("huidaNo", "93607544");
		NameValuePair answerId = new NameValuePair("answerId", "1");
		post.setRequestBody(new NameValuePair[]{huidaNo,answerId});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getPraisedCountTest() throws HttpException, IOException {
		System.out.println("invoke getPraisedCountTest http...");
		PostMethod post = new PostMethod("/hui/interface/getPraisedCount.hui");
		NameValuePair answerId = new NameValuePair("answerId", "2");
		post.setRequestBody(new NameValuePair[]{answerId});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}

}
