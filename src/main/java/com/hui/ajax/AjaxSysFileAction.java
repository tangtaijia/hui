package com.hui.ajax;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FilenameUtils;

import com.hui.common.action.BaseActionSupport;
import com.hui.common.entity.Admin;
import com.hui.common.entity.AjaxResult;
import com.hui.common.entity.SysFile;
import com.hui.common.service.ISysFileService;
import com.hui.common.utils.DBUtils;
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
public class AjaxSysFileAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = -2571043491913417489L;
    
    private ISysFileService sysFileService;
    
    private Integer fileId;
    
    private Integer dataId;
    
    private Integer fileType;
    
    private String fileName;
    
    private Integer x;
    
    private Integer y;
    
    private Integer width;
    
    private Integer height;
    
    private AjaxResult result;
    
    public void setSysFileService(ISysFileService sysFileService)
    {
        this.sysFileService = sysFileService;
    }
    
    public Integer getFileId()
    {
        return fileId;
    }
    
    public void setFileId(Integer fileId)
    {
        this.fileId = fileId;
    }
    
    public Integer getDataId()
    {
        return dataId;
    }
    
    public void setDataId(Integer dataId)
    {
        this.dataId = dataId;
    }
    
    public Integer getFileType()
    {
        return fileType;
    }
    
    public void setFileType(Integer fileType)
    {
        this.fileType = fileType;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    public Integer getX()
    {
        return x;
    }
    
    public void setX(Integer x)
    {
        this.x = x;
    }
    
    public Integer getY()
    {
        return y;
    }
    
    public void setY(Integer y)
    {
        this.y = y;
    }
    
    public Integer getWidth()
    {
        return width;
    }
    
    public void setWidth(Integer width)
    {
        this.width = width;
    }
    
    public Integer getHeight()
    {
        return height;
    }
    
    public void setHeight(Integer height)
    {
        this.height = height;
    }
    
    public AjaxResult getResult()
    {
        return result;
    }
    
    public String totemplatelist()
    {
        SysFile fi = new SysFile();
        fi.setFileType(0);
        fi.setDataId(dataId);
        result = new AjaxResult(true, sysFileService.list(fi));
        
        return SUCCESS;
    }
    
    public String tocreate()
    {
        try
        {
            Admin loginAdmin = (Admin)this.getRequest().getSession().getAttribute("loginAdmin");
            
            if (fileType == 2)
            {
                // 管理员头像
            	File srcFile = new File(DBUtils.getUploadPath()+"/temp", fileName);
        		BufferedImage image = ImageIO.read(srcFile);
        		BufferedImage bi = image.getSubimage(x, y, width, height);
        		String type = FilenameUtils.getExtension(fileName);
            	if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
            		//TODO 上传管理员头像到FTP服务器
					bi.flush();
					ByteArrayOutputStream bs = new ByteArrayOutputStream();
					ImageOutputStream imOut;
					imOut = ImageIO.createImageOutputStream(bs);
					ImageIO.write(bi, type, imOut);
					FtpUtils.uploadFile(new ByteArrayInputStream(bs.toByteArray()), "profile/admin", fileName);
            	} else {
            		File destFile = new File(DBUtils.getUploadPath()+"/profile/admin", fileName);
            		ImageIO.write(bi, type, destFile);
            	}
                
                SysFile fi = new SysFile();
                fi.setDataId(loginAdmin.getAdminId());
                fi.setFileName(fileName);
                fi.setFilePath("/profile/admin");
                fi.setFileType(fileType);
                // 删除之前的管理员头像
                List<SysFile> sysFiles = sysFileService.list(fi);
                File tempFile = null;
                for (SysFile sysFile : sysFiles)
                {
                	if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
                		FtpUtils.deleteFtpFile(sysFile.getFilePath(), sysFile.getFileName());
                	} else {
                		tempFile =
                				new File(DBUtils.getUploadPath()+sysFile.getFilePath(),
                						sysFile.getFileName());
                		if (null != tempFile && tempFile.exists())
                		{
                			tempFile.delete();
                		}
                	}
                }
                
                sysFileService.delete(fi);
                
                sysFileService.saveEntity(fi);
                
                loginAdmin.setProfile("/profile/admin/" + fileName);
                this.getRequest().getSession().setAttribute("loginAdmin", loginAdmin);
            }
            
            result = new AjaxResult(true, loginAdmin.getProfile());
        }
        catch (Exception e)
        {
            result = new AjaxResult(false, "发生错误");
        }
        return SUCCESS;
    }
    
    public String todelartile()
    {
        // 删除文章图片
        SysFile sysFile = sysFileService.selectById(fileId);
        if (null != sysFile)
        {
            File tempFile =
                new File(sysFile.getFilePath(), sysFile.getFileName());
            if (null != tempFile && tempFile.exists())
            {
                tempFile.delete();
            }
            
            SysFile fi = new SysFile();
            fi.setFileId(fileId);
            // dataId设了默认值
            fi.setDataId(null);
            fi.setFileType(null);
            
            sysFileService.delete(fi);
            
            result = new AjaxResult(true, "删除成功");
        }
        else
        {
            result = new AjaxResult(false, "数据不存在");
        }
        
        return SUCCESS;
    }
    
}