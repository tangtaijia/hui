package com.hui.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import com.hui.common.BaseTest;
import com.hui.common.utils.FtpUtils;
import com.hui.common.utils.ProcessMD5;

public class FtpUtilsTest extends BaseTest{
	@Test
	public void testGetFtpConf() {
		Properties prop = new Properties();   
        InputStream in = Object.class.getResourceAsStream("/resources/ftp.properties");   
        try {   
            prop.load(in);   
            String param = prop.getProperty("ftp.url").trim();   
            System.out.println(ProcessMD5.encrypt(param, ProcessMD5.getPublicKey()));
            param = prop.getProperty("ftp.port").trim();   
            System.out.println(ProcessMD5.encrypt(param, ProcessMD5.getPublicKey()));
            param = prop.getProperty("ftp.username").trim();   
            System.out.println(ProcessMD5.encrypt(param, ProcessMD5.getPublicKey()));
            param = prop.getProperty("ftp.passwd").trim();   
            System.out.println(ProcessMD5.encrypt(param, ProcessMD5.getPublicKey()));
            param = prop.getProperty("ftp.dir").trim();   
            System.out.println(ProcessMD5.encrypt(param, ProcessMD5.getPublicKey()));
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
	}
	
	@Test
	public void testUploadFile() throws FileNotFoundException {
		try {
			long startTime=System.currentTimeMillis();
			FileInputStream fi = new FileInputStream("D:/question.png");
			FtpUtils.uploadFile(fi, "cc/dd", "question1.png");
			long endTime = System.currentTimeMillis();
			System.out.println(endTime-startTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteFile() {
		long startTime=System.currentTimeMillis();
		FtpUtils.deleteFtpFile("cc/dd", "test1.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test2.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test3.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test4.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test5.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test6.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test7.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test8.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test9.txt");
		FtpUtils.deleteFtpFile("cc/dd", "test0.txt");
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}
	
}
