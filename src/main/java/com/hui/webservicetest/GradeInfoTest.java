package com.hui.webservicetest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class GradeInfoTest {

	private HttpClient client;

	@Before
	public void setUp() {
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost", 8080, "http");
	}

	@Test
	public void getGradeListTest() throws HttpException, IOException {
		System.out.println("invoke getGradeListTest http...");
		PostMethod post = new PostMethod("/hui_ah/interface/getGradeList.hui");
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getCourseListByGradeIdTest() throws HttpException, IOException {
		System.out.println("invoke getCourseListByGradeIdTest http...");
		PostMethod post = new PostMethod("/hui_ah/interface/getCourseListByGradeId.hui");
		NameValuePair value = new NameValuePair("gradeId", "1");
		post.setRequestBody(new NameValuePair[]{value});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}

}
