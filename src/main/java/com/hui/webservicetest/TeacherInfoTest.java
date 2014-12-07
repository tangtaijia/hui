package com.hui.webservicetest;


import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Before;
import org.junit.Test;

public class TeacherInfoTest {

	private HttpClient client;

	@Before
	public void setUp() {
		client = new HttpClient();
		client.getHostConfiguration().setHost("localhost", 8080, "http");
	}

	@Test
	public void getDutyTeacherListTest() throws HttpException, IOException {
		System.out.println("invoke getDutyTeacherListTest http...");
		PostMethod post = new PostMethod("/hui_ah/interface/getDutyTeacherList.hui");
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getTeacherInformationTest() throws HttpException, IOException {
		System.out.println("invoke getTeacherInformationTest http...");
		PostMethod post = new PostMethod("/hui/interface/getTeacherInformation.hui");
		NameValuePair value1 = new NameValuePair("huidaNo", "93607545");
		post.setRequestBody(new NameValuePair[]{value1});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
	@Test
	public void getTeachersListTest() throws HttpException, IOException {
		System.out.println("invoke getTeacherInformationTest http...");
		PostMethod post = new PostMethod("/hui_ah/interface/getTeachersList.hui");
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		NameValuePair value1 = new NameValuePair("huidaNo", "");
		NameValuePair value2 = new NameValuePair("gradeText", "小学");
		NameValuePair value3 = new NameValuePair("courseText", "");
		NameValuePair page = new NameValuePair("page", "1");
		post.setRequestBody(new NameValuePair[]{value1,value2,value3,page});
        client.executeMethod(post);
        byte[] responseBody = post.getResponseBody();
        System.out.println("message context is:"+ new String(responseBody));
	}
	
}
