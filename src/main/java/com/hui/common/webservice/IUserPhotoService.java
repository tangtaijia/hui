package com.hui.common.webservice;

import java.util.List;

import com.hui.common.entity.SysFile;

public interface IUserPhotoService {
	
	public void createUserPhotoData(String huidaNo,byte[] imageByte) throws Exception;
	
	public List<SysFile> queryUserPhotoInfo(String huidaNo);
}
