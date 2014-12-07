package com.hui.webservicetest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

public class SubmitBugTest {
	
	private HttpClient client;

	@Before
	public void setUp() {
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost", 8080, "http");
	}

	@Test
	public void submitSoftBugTest() throws HttpException, IOException {
		System.out.println("invoke submitSoftBugTest http...");
		PostMethod post = new PostMethod("/hui/interface/submitSoftBug.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "11111");
		NameValuePair value2 = new NameValuePair("content", "1111");
		post.setRequestBody(new NameValuePair[]{value1,value2});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}

}
