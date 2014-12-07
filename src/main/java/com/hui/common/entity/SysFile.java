package com.hui.common.entity;

/**
 * 
 * <系统文件>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysFile extends BaseEntity
{
    
    private static final long serialVersionUID = -8127713357474966312L;
    /**
     * 主键
     */
    private Integer fileId;
    /**
     * 上传用户模板，则为1；会员头像，则为会员ID；
     */
    private Integer dataId = 0;
    /**
     * 序列，例如文章图片有多个
     */
    private Integer seqId = 0;
    
    private String fileName;
    
    private String filePath;
    /**
     * 文件类型：0-下载模板；1-会员头像；2-管理员头像；3-文章图片；
     */
    private Integer fileType = 0;
    /**
     * 路径类型：0-CONTEXT；1-URL；
     */
    private Integer pathType = 0;
    
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
    
    public Integer getSeqId()
    {
        return seqId;
    }
    
    public void setSeqId(Integer seqId)
    {
        this.seqId = seqId;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    public String getFilePath()
    {
        return filePath;
    }
    
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    public Integer getFileType()
    {
        return fileType;
    }
    
    public void setFileType(Integer fileType)
    {
        this.fileType = fileType;
    }
    
    public Integer getPathType()
    {
        return pathType;
    }
    
    public void setPathType(Integer pathType)
    {
        this.pathType = pathType;
    }
    
}