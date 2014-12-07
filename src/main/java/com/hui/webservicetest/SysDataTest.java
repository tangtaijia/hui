package com.hui.webservicetest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

public class SysDataTest {
	
	private HttpClient client;

	@Before
	public void setUp() {
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost", 8080, "http");
	}

	@Test
	public void getQRCodeTest() throws HttpException, IOException {
		System.out.println("invoke getQRCodeTest http...");
		PostMethod post = new PostMethod("/hui/interface/getQRCode.hui");
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getLastVersionTest() throws HttpException, IOException {
		System.out.println("invoke getLastVersionTest http...");
		PostMethod post = new PostMethod("/hui/interface/getLastVersion.hui");
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getSoftDownloadAddressTest() throws HttpException, IOException {
		System.out.println("invoke getSoftDownloadAddressTest http...");
		PostMethod post = new PostMethod("/hui/interface/getSoftDownloadAddress.hui");
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}

}
