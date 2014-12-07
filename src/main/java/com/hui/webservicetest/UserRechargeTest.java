package com.hui.webservicetest;


import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

public class UserRechargeTest {

private HttpClient client;
	
	@Before
	public void setUp(){
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost",8080,"http");
	}

	@Test
	public void getRechargeListTest() throws HttpException, IOException {
		System.out.println("invoke getRechargeListTest http...");
		PostMethod post = new PostMethod("/hui/interface/getRechargeList.hui");
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	
	}

}
