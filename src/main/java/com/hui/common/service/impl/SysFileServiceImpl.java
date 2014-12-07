package com.hui.common.service.impl;

import java.io.File;
import java.util.List;

import com.hui.common.dao.ISysFileDao;
import com.hui.common.entity.SysFile;
import com.hui.common.service.ISysFileService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.FileUtils;
import com.hui.common.utils.FtpUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysFileServiceImpl extends BaseServiceImpl<SysFile, SysFile> implements ISysFileService
{
    
   
    public Integer delete(SysFile fi)
    {
        return ((ISysFileDao)baseDao).delete(fi);
    }
    
   
    public Integer getMaxSeqId(SysFile fi)
    {
        return ((ISysFileDao)baseDao).getMaxSeqId(fi);
    }
    
    public Integer modifyIcon(Integer userId, String imgStr)
    {
        try
        {
            if(CommonUtils.isNotEmptyOrNull(imgStr)) {
                SysFile sysFileParam = new SysFile();
                sysFileParam.setFileType(1);
                sysFileParam.setDataId(userId);
                List<SysFile> images = this.list(sysFileParam); 
                if(CommonUtils.isNotEmptyOrNull(images)) {
                    for(SysFile image:images) {
                    	if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
                    		FtpUtils.deleteFtpFile(image.getFilePath(), image.getFileName());
                    	} else {
                    		File tempFile =
                    				new File(DBUtils.getUploadPath()+image.getFilePath(), image.getFileName());
                    		if (null != tempFile && tempFile.exists())
                    		{
                    			tempFile.delete();
                    		}
                    	}
                    }
                }
                this.delete(sysFileParam);
                SysFile sysFile = new SysFile();
                sysFile.setFileType(1);
                sysFile.setDataId(userId);
                sysFile.setFileName(imgStr);
                sysFile.setFilePath("/userPhoto/original/");
                sysFile.setPathType(0);
                sysFile.setSeqId(2);
                this.saveEntity(sysFile);
                String[] imgStrArr = imgStr.split("[.]");//关于点的问题是用string.split("[.]") 解决。
                String imgLtStr = imgStrArr[0]+"-Lt"+ "." + imgStrArr[1];
                sysFile.setFilePath("/userPhoto/thumbnail/");
                sysFile.setSeqId(1);
                sysFile.setFileName(imgLtStr);
                this.saveEntity(sysFile);
                if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
                	FtpUtils.cutSingleFile(DBUtils.getUploadPath()+"/temp", "userPhoto/original", imgStr);
                	FtpUtils.cutSingleFile(DBUtils.getUploadPath()+"/temp", "userPhoto/thumbnail", imgLtStr);
                } else {
                	FileUtils.cutSingleFile(DBUtils.getUploadPath()+"/temp", DBUtils.getUploadPath()+"/userPhoto/original", imgStr);
                	FileUtils.cutSingleFile(DBUtils.getUploadPath()+"/temp", DBUtils.getUploadPath()+"/userPhoto/thumbnail", imgLtStr);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    
}