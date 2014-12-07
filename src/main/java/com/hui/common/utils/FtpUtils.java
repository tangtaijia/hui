package com.hui.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtils {
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {  
	    boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);//连接FTP服务器  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
//	        ftp.changeWorkingDirectory(path);  
	        
	        ftpCreateDirectoryTree(ftp,path);
	        ftp.enterLocalPassiveMode();// 设置PassiveMode传输
	        ftp.setFileType(FTP.BINARY_FILE_TYPE); //设置以二进制流的方式传输,如果缺省该句 传输txt正常 但图片和其他格式的文件传输出现乱码  
	        ftp.storeFile(filename, input);           
	          
	        input.close();  
	        ftp.logout();  
	        success = true;  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;  
	}
	
	/**
	 * 以流的形式上传图片
	 * <功能详细描述>
	 * @param in
	 * @param path
	 * @param targetFilename
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean uploadFile(InputStream in,String path,String targetFilename) {
		String portStr = getFtpConf("ftp.port");
		int port = 21;
		if(CommonUtils.isInteger(portStr)) {
			port = Integer.parseInt(portStr);
		}
		String targetPath = getFtpConf("ftp.dir");
		if(CommonUtils.isNotEmptyOrNull(path)) {
			targetPath = targetPath+path;
		}
		return uploadFile(getFtpConf("ftp.url"), port, getFtpConf("ftp.username"), getFtpConf("ftp.passwd"), targetPath, targetFilename, in);
	}
	
	public static boolean uploadFile(String sourceFile,String path,String targetFilename) {
		InputStream in = null;
		try {
			in = new FileInputStream(sourceFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(CommonUtils.isNotEmptyOrNull(in)) {
			String portStr = getFtpConf("ftp.port");
			int port = 21;
			if(CommonUtils.isInteger(portStr)) {
				port = Integer.parseInt(portStr);
			}
			String targetPath = getFtpConf("ftp.dir");
			if(CommonUtils.isNotEmptyOrNull(path)) {
				targetPath = targetPath+path;
			}
			return uploadFile(getFtpConf("ftp.url"), port, getFtpConf("ftp.username"), getFtpConf("ftp.passwd"), targetPath, targetFilename, in);
		} else {
			return false;
		}
	}
	
	/**
	 * 剪切文件（从临时文件夹到ftp服务器）
	 * <功能详细描述>
	 * @param oldPath
	 * @param newPath
	 * @param fileName
	 * @see [类、类#方法、类#成员]
	 */
	public static void cutSingleFile(String oldPath,String newPath,String fileName) {
		boolean success = uploadFile(oldPath+"/"+fileName, newPath,fileName) ;
		if(success) {
			File tempFile =
					new File(oldPath,
							fileName);
			if (null != tempFile && tempFile.exists())
			{
				tempFile.delete();
			}
		}
    }
	
	/**
	 * 删除远程文件
	 * <功能详细描述>
	 * @param path
	 * @param targetFilename
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean deleteFtpFile(String path,String targetFilename) {
		String portStr = getFtpConf("ftp.port");
		int port = 21;
		if(CommonUtils.isInteger(portStr)) {
			port = Integer.parseInt(portStr);
		}
		String targetPath = getFtpConf("ftp.dir");
		if(CommonUtils.isNotEmptyOrNull(path)) {
			targetPath = targetPath+path;
		}
		return deleteFtpFile(getFtpConf("ftp.url"), port, getFtpConf("ftp.username"), getFtpConf("ftp.passwd"), targetPath,targetFilename);
	}
	
	/**
	 * 删除远程文件
	 * <功能详细描述>
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param path
	 * @param filename
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean deleteFtpFile(String url,int port,String username, String password, String path, String filename) {
		boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);//连接FTP服务器  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        } 
	        if(CommonUtils.isNotEmptyOrNull(path.trim())) {
	        	ftp.deleteFile(path.trim()+"/"+filename);         
	        } else {
	        	ftp.deleteFile(filename);           
	        }
	          
	        ftp.logout();  
	        success = true;  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;  
	}
	
	public static void testUpLoadFromDisk(){  
	    try {  
	        FileInputStream in=new FileInputStream(new File("D:/test.txt")); 
//	        boolean flag = uploadFile("127.0.0.1", 21, "tang", "taijia", "/TST/ttt/aaa", "test.txt", in);
//	        boolean flag = uploadFile("117.18.2.28", 21, "gbgw2t7xhd", "rrejx8b22h", "/domains/gbgw2t7xhd.hkip331.51php.com/public_html/hui_files/", "test.txt", in);
	        boolean flag = uploadFile("113.10.158.175", 21, "tang", "111111t", "/hui_files/aaa/", "testd.txt", in);
	        System.out.println(flag);  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	private static String getFtpConf(String key) {
		Properties prop = new Properties();   
		InputStream in = null;
		ClassLoader classLoader = FtpUtils.class.getClassLoader();
		if(CommonUtils.isNotEmptyOrNull(classLoader)) {
			in = classLoader.getResourceAsStream("/resources/ftp.properties");   
		} else {
			in = ClassLoader.getSystemResourceAsStream("/resources/ftp.properties");   
		}
//		in = Object.class.getResourceAsStream("/resources/ftp.properties");  
        try {   
            prop.load(in);   
            String value = prop.getProperty(key).trim();   
            value = ProcessMD5.decrypt(value, ProcessMD5.getPrivateKey());
            return value;
        } catch (IOException e) {   
            e.printStackTrace();   
        }  
        return "";
	}
	
	/**
	 * utility to create an arbitrary directory hierarchy on the remote ftp
	 * server
	 * 
	 * @param client
	 * @param dirTree
	 *            the directory tree only delimited with / chars. No file name!
	 * @throws Exception
	 */
	private static void ftpCreateDirectoryTree(FTPClient client, String dirTree)
			throws IOException {

		boolean dirExists = true;

		// tokenize the string and attempt to change into each directory level.
		// If you cannot, then start creating.
		String[] directories = dirTree.split("/");
		for (String dir : directories) {
			if (!dir.isEmpty()) {
				if (dirExists) {
					dirExists = client.changeWorkingDirectory(dir);
				}
				if (!dirExists) {
					if (!client.makeDirectory(dir)) {
						throw new IOException(
								"Unable to create remote directory '" + dir
										+ "'.  error='"
										+ client.getReplyString() + "'");
					}
					if (!client.changeWorkingDirectory(dir)) {
						throw new IOException(
								"Unable to change into newly created remote directory '"
										+ dir + "'.  error='"
										+ client.getReplyString() + "'");
					}
				}
			}
		}
	}
}
