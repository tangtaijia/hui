package com.hui.webservicetest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

public class HelpInfoTest {
	
	private HttpClient client;

	@Before
	public void setUp() {
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost", 8080, "http");
	}

	@Test
	public void getHelpListTest() throws HttpException, IOException {
		System.out.println("invoke getHelpListTest http...");
		PostMethod post = new PostMethod("/hui/interface/getHelpList.hui");
		NameValuePair value = new NameValuePair("page", "1");
		post.setRequestBody(new NameValuePair[]{value});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getHelpDetailTest() throws HttpException, IOException {
		System.out.println("invoke getHelpDetailTest http...");
		PostMethod post = new PostMethod("/hui/interface/getHelpDetail.hui");
		NameValuePair value = new NameValuePair("helpId", "2");
		post.setRequestBody(new NameValuePair[]{value});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}

}
