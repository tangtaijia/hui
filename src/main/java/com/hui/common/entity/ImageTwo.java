package com.hui.common.entity;

import java.io.Serializable;

import com.hui.common.utils.CommonUtils;

/**
 * 图片--原图、缩略图
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ImageTwo implements Serializable
{
    /**
	 * 注释内容
	 */
	private static final long serialVersionUID = 9016046114494824643L;
	
	public ImageTwo() {}
	public ImageTwo(String webImgPath) {
		this.webImgPath = webImgPath;
	}
	
	/**
     * 原图
     */
    private SysFile imgOri;
    /**
     * 缩略图
     */
    private SysFile imgLt;
    
    /**
     * 网站文件路径
     */
    private String webImgPath;
    
    public SysFile getImgOri()
    {
        return imgOri;
    }
    public void setImgOri(SysFile imgOri)
    {
        this.imgOri = imgOri;
    }
    public SysFile getImgLt()
    {
        return imgLt;
    }
    public void setImgLt(SysFile imgLt)
    {
        this.imgLt = imgLt;
    }
	public String getWebImgPath() {
		return webImgPath;
	}
	public void setWebImgPath(String webImgPath) {
		this.webImgPath = webImgPath;
	}
}
