package com.hui.common.webservice.impl;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hui.common.dao.ISysFileDao;
import com.hui.common.dao.IUserDao;
import com.hui.common.entity.SysFile;
import com.hui.common.entity.User;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.FtpUtils;
import com.hui.common.utils.ImageUtils;
import com.hui.common.webservice.IUserPhotoService;

@Service("userPhotoService")
public class UserPhotoServiceImpl implements IUserPhotoService {
	
	@Autowired
	private ISysFileDao sysFileDao;
	
	@Autowired
	private IUserDao userDao;
	
	private final ReentrantLock lock = new ReentrantLock();

	
	@Transactional(rollbackFor=Exception.class)
	public void createUserPhotoData(String huidaNo,byte[] imageByte) throws Exception{
		String orginalFileName = huidaNo + "_original_icon.jpg";
		String orginalFilePath = "/userPhoto/original/";
		
		String thumbnalFileName = huidaNo + "_thumbnail_icon.jpg";
		String thumbnalFilePath = "/userPhoto/thumbnail/";
		try{
			lock.lock();
			//先创建数据,后生成图片
			//原图SysFile
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("huidaNo", huidaNo);
			User user = userDao.queryUserInfoByHuiNo(paramMap);
			SysFile sysFile = new SysFile();
			sysFile.setDataId(user.getUserId());
			sysFile.setFileName(orginalFileName);
			sysFile.setFilePath(orginalFilePath);
			sysFile.setSeqId(2);
			sysFile.setFileType(1);
			List resultList = sysFileDao.selectByKey(sysFile);
			if(resultList.size() > 0){
				//删除的时候会一并删除原图和缩略图2条记录
				sysFileDao.delete(sysFile);
			}
			sysFileDao.save(sysFile);
			
			//缩略图SysFile
			sysFile.setFileName(thumbnalFileName);
			sysFile.setFilePath(thumbnalFilePath);
			sysFile.setSeqId(1);
			sysFile.setFileType(1);
			sysFileDao.save(sysFile);
			//生成jpg原图片
	        String imgFilePath = DBUtils.getUploadPath()+ orginalFilePath + orginalFileName;
	        if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
				InputStream ins = new ByteArrayInputStream(imageByte);
				FtpUtils.uploadFile(ins, orginalFilePath.substring(1, orginalFilePath.length()), orginalFileName);
			} else {
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(imageByte);
				out.flush();
				out.close();
			}
	        //生成缩略图
	        imgFilePath = DBUtils.getUploadPath() + thumbnalFilePath + thumbnalFileName;
//	        ImageUtils.createThumbnailImage(imageByte, 152, 152, imgFilePath, "jpg");
	        ImageUtils.createThumbnailImage(imageByte, 152, 152, thumbnalFilePath.substring(1, thumbnalFilePath.length()), thumbnalFileName, FilenameUtils.getExtension(thumbnalFileName));
		}
		finally{
			lock.unlock();
		}
	}

	
	public List<SysFile> queryUserPhotoInfo(String huidaNo) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("huidaNo", huidaNo);
		User user = userDao.queryUserInfoByHuiNo(paramMap);
		SysFile sysFile = new SysFile();
		sysFile.setDataId(user.getUserId());
		sysFile.setSeqId(1);
		sysFile.setFileType(1);
		List<SysFile> sysFileList = sysFileDao.selectByKey(sysFile);
		return sysFileList;
	}

}
