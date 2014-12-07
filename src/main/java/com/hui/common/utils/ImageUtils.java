package com.hui.common.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import sun.misc.BASE64Decoder;

import com.sun.image.codec.jpeg.ImageFormatException;

public class ImageUtils {
	
	private static final BASE64Decoder decoder = new BASE64Decoder();
	
	public static byte[] convertBase64toImageByte(String imageString) throws IOException{
		 //Base64解码
        byte[] b = decoder.decodeBuffer(imageString);
        for(int i=0;i<b.length;++i)
        {
            if(b[i]<0)
            {
                b[i]+=256;
            }
        }
		return b;
	}
	
	/**
	 * 生成缩略图
	 * 头像：白色底框:160*160px   不包含底框:152*152px
	 * 回答+提问：白色底框:196*196px   不包含底框:182*182px
	 * @param imageByte
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static void createThumbnailImage(byte[] imageByte,int width,int height,String imagePath,String fileName) throws Exception{
		BufferedInputStream input = new BufferedInputStream(new ByteArrayInputStream(imageByte));
		BufferedImage image = ImageIO.read(input);
//		int widthCheck = image.getWidth();
//		int heightCheck = image.getHeight();
//		if (widthCheck < 152 || heightCheck < 152) {
//			throw new Exception("请上传不小于 152 * 152 像素的图片");
//		}
		
		BufferedImage thumb = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);

		Graphics2D graph = (Graphics2D) thumb.getGraphics();
		graph.setColor(Color.WHITE);   
		graph.drawImage(image, 0, 0, width, height, Color.WHITE,null);

		graph.dispose();
		
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imagePath));
		ImageIO.write(thumb, fileName, out);
		out.close();
	}
	
	/**
	 * 生成缩略图
	 * 头像：白色底框:160*160px   不包含底框:152*152px
	 * 回答+提问：白色底框:196*196px   不包含底框:182*182px
	 * @param imageByte
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static void createThumbnailImage(byte[] imageByte,int width,int height,String imagePath,String fileName,String fileType) throws Exception{
		BufferedInputStream input = new BufferedInputStream(new ByteArrayInputStream(imageByte));
		BufferedImage image = ImageIO.read(input);
//		int widthCheck = image.getWidth();
//		int heightCheck = image.getHeight();
//		if (widthCheck < 152 || heightCheck < 152) {
//			throw new Exception("请上传不小于 152 * 152 像素的图片");
//		}
		
		BufferedImage thumb = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);

		Graphics2D graph = (Graphics2D) thumb.getGraphics();
		graph.setColor(Color.WHITE);   
		graph.drawImage(image, 0, 0, width, height, Color.WHITE,null);

		graph.dispose();
		if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
    		//TODO 上传系统消息缩略图到FTP服务器
        	thumb.flush();
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imOut;
			imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(thumb, fileType, imOut);
			FtpUtils.uploadFile(new ByteArrayInputStream(bs.toByteArray()), imagePath, fileName);
			bs.close();
    	} else {
    		String path = DBUtils.getUploadPath()+"/"+imagePath;
    		FileUtils.mkDirs(path);//创建文件路径
    		File tempFile = new File(path, fileName);
    		OutputStream os = new FileOutputStream(tempFile);
    		ImageIO.write(thumb, fileType, os);
    		os.close();
    	}
	}
}
