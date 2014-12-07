package com.hui.common.action;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.hui.common.entity.SysFile;
import com.hui.common.entity.SysMsg;
import com.hui.common.entity.User;
import com.hui.common.service.ISysFileService;
import com.hui.common.service.ISysMsgService;
import com.hui.common.service.IUserService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.DBUtils;
import com.hui.common.utils.FileUtils;
import com.hui.common.utils.FrontUtils;
import com.hui.common.utils.FtpUtils;
import com.hui.common.utils.MD5Encrypt;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class UploadAction extends BaseActionSupport
{
    
    private static final long serialVersionUID = 2599806174309666690L;
    
    private IUserService userService;
    
    private ISysFileService sysFileService;
    
    private ISysMsgService sysMsgService;
    
    private List<File> file;
    
    private List<String> fileContentType;
    
    private List<String> fileFileName;
    
    private Integer uploadType;
    
    private String testStr;
    
    public String getTestStr()
    {
        return testStr;
    }

    public void setTestStr(String testStr)
    {
        this.testStr = testStr;
    }

    private List<String> rsts = new ArrayList<String>();
    
    private Integer articleId;
    
    private Integer sysMsgId;
    
    private String errorMsg;
    
    private Integer fileId;
    
    private String imgName;
    
    private Integer imgWidth;
    
    private Integer imgHeight;
    
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }
    
    public void setSysFileService(ISysFileService sysFileService)
    {
        this.sysFileService = sysFileService;
    }
    
    public void setSysMsgService(ISysMsgService sysMsgService)
    {
        this.sysMsgService = sysMsgService;
    }

    public List<File> getFile()
    {
        return file;
    }
    
    public void setFile(List<File> file)
    {
        this.file = file;
    }
    
    public List<String> getFileContentType()
    {
        return fileContentType;
    }
    
    public void setFileContentType(List<String> fileContentType)
    {
        this.fileContentType = fileContentType;
    }
    
    public List<String> getFileFileName()
    {
        return fileFileName;
    }
    
    public void setFileFileName(List<String> fileFileName)
    {
        this.fileFileName = fileFileName;
    }
    
    public Integer getUploadType()
    {
        return uploadType;
    }
    
    public void setUploadType(Integer uploadType)
    {
        this.uploadType = uploadType;
    }
    
    public List<String> getRsts()
    {
        return rsts;
    }
    
    public void setRsts(List<String> rsts)
    {
        this.rsts = rsts;
    }
    
    public Integer getArticleId()
    {
        return articleId;
    }
    
    public void setArticleId(Integer articleId)
    {
        this.articleId = articleId;
    }
    
    public String getErrorMsg()
    {
        return errorMsg;
    }
    
    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }
    
    public Integer getFileId()
    {
        return fileId;
    }
    
    public void setFileId(Integer fileId)
    {
        this.fileId = fileId;
    }
    
    public String getImgName()
    {
        return imgName;
    }
    
    public void setImgName(String imgName)
    {
        this.imgName = imgName;
    }
    
    public Integer getImgWidth()
    {
        return imgWidth;
    }
    
    public void setImgWidth(Integer imgWidth)
    {
        this.imgWidth = imgWidth;
    }
    
    public Integer getImgHeight()
    {
        return imgHeight;
    }
    
    public void setImgHeight(Integer imgHeight)
    {
        this.imgHeight = imgHeight;
    }
    
    public Integer getSysMsgId()
    {
        return sysMsgId;
    }

    public void setSysMsgId(Integer sysMsgId)
    {
        this.sysMsgId = sysMsgId;
    }

    public String execute()
    {
    	if(!"post".equalsIgnoreCase(getRequest().getMethod())) {//强制post提交
    		return "404";
    	}
		if (!getRequest()
				.getSession()
				.getId()
				.toLowerCase()
				.equalsIgnoreCase(
						getRequest().getParameter(FrontUtils.FORM_AUTH_NAME))) {
			return "404";
		}
        InputStream is = null;
        OutputStream os = null;
        String fileName = null;
        String fileType = null;
        String path = null;
        File tempFile = null;
        
        if (null == file)
        {
            this.addFieldError("file", "文件不能为空，请选择");
            return "forinput";
        }
        if (null == uploadType)
        {
            this.addFieldError("file", "无效的请求来源");
            return "forinput";
        }
        
        try
        {
            for (int i = 0; i < file.size(); i++)
            {
                switch (uploadType)
                {
                    case -1:
                        // 上传文章图片
                        // 计算是否超过图片上传限制
                        SysFile fi = new SysFile();
                        fi.setDataId(articleId);
                        fi.setFileType(3);
                        
                        if (CommonUtils.getArticleImgCount() <= sysFileService.getCount(fi))
                        {
                            imgName = "error";
                            errorMsg = "上传图片超过限制";
                            
                            return "preview";
                        }
                        
                        is = new BufferedInputStream(new FileInputStream(this.getFile().get(i)));
                    	byte[] b = new byte[8];
                    	fileName = this.getFileFileName().get(i);
                    	tempFile = new File(path, UUID.randomUUID().toString() + "." + fileType);
                    	imgName = tempFile.getName();
                        if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
                        	FtpUtils.uploadFile(new FileInputStream(this.getFile().get(i)), "article", imgName);
                        } else {
                        	fileType = FilenameUtils.getExtension(fileName);
                        	path = DBUtils.getUploadPath()+"/article";
                        	FileUtils.mkDirs(path);//创建文件路径
                        	
                        	os = new FileOutputStream(tempFile);
                        	while (-1 != is.read(b))
                        	{
                        		os.write(b);
                        	}
                        }
                        
                        fi.setSeqId(sysFileService.getMaxSeqId(fi) + 1);
                        fi.setFileName(tempFile.getName());
                        fi.setFilePath("/article");
                        
                        fileId = sysFileService.saveEntity(fi);
                        
                        return "preview";
                    case 0:
                        // 上传图片预览
                        BufferedImage image = ImageIO.read(this.getFile().get(i));
                        int width = image.getWidth();
                        int height = image.getHeight();
                        int iwidth = 300;
                        int iheight = 300;
                        int dwidth = 0;
                        int dheight = 0;
                        // 图像缩放
                        if (width > 0 && height > 0)
                        {
                            if (width > height)
                            {
                                dwidth = iwidth;
                                dheight = (height * iwidth) / width;
                            }
                            else
                            {
                                dheight = iheight;
                                dwidth = (width * iheight) / height;
                            }
                        }
                        
                        Image dimage = image.getScaledInstance(dwidth, dheight, Image.SCALE_DEFAULT);
                        BufferedImage tag = new BufferedImage(dwidth, dheight, BufferedImage.TYPE_INT_RGB);
                        Graphics2D g = tag.createGraphics();
                        g.drawImage(dimage, 0, 0, null);
                        g.dispose();
                        
                        fileName = this.getFileFileName().get(i);
                        fileType = FilenameUtils.getExtension(fileName);
                        path = DBUtils.getUploadPath()+"/temp";
                        FileUtils.mkDirs(path);//创建文件路径
                        tempFile = new File(path, UUID.randomUUID().toString() + "." + fileType);
                        os = new FileOutputStream(tempFile);
                        ImageIO.write(tag, fileType, os);
                        
                        imgName = tempFile.getName();
                        imgHeight = dheight;
                        imgWidth = dwidth;
                        
                        return "preview";
                    case 1:
                        // 导入用户数据
                        impUser(this.getFileFileName().get(i), getData(file.get(i), 1));
                        break;
                    case 2:
                        // 问题、 回答 图片
                        image = ImageIO.read(this.getFile().get(i));
                        width = image.getWidth();
                        height = image.getHeight();
                        String imgNameStr = UUID.randomUUID().toString();
                        fileName = this.getFileFileName().get(i);
                        fileType = FilenameUtils.getExtension(fileName);
                        String imgOriName = imgNameStr+ "." + fileType;
                        String imgLtName = imgNameStr +"-Lt"+ "." + fileType;
                        // 原图
                        path = DBUtils.getUploadPath()+"/temp";
                        FileUtils.mkDirs(path);//创建文件路径
                        final int LIMIT_WIDTH = 750;//限制宽750
                    	final int LIMIT_HEIGHT = 500;//限制高500
                    	final int LIMIT_SIZE = 52428800;//限制大小5M
                    	
                    	byte[]   data   =   ((DataBufferByte)image.getData().getDataBuffer()).getData();
                        int imgSize = data.length;
                        if(width<=LIMIT_WIDTH&&height<=LIMIT_HEIGHT && imgSize <= LIMIT_SIZE) {//原图
                        	is = new BufferedInputStream(new FileInputStream(this.getFile().get(i)));
                        	tempFile = new File(path, imgOriName);
                        	os = new FileOutputStream(tempFile);
                        	
                        	b = new byte[8];
                        	while (-1 != is.read(b))
                        	{
                        		os.write(b);
                        	}
                        } else {
                        	double widthD = width;
                        	double heightD = height;
                        	//原图大小大于5M的话，等比压缩
                    		widthD = Math.sqrt(((double)LIMIT_SIZE/imgSize))*widthD;
                    		heightD = Math.sqrt(((double)LIMIT_SIZE/imgSize))*heightD;
                    		//原图尺寸大于750*500的话，压缩
                    		if(widthD>=LIMIT_WIDTH) {
                        		heightD = (LIMIT_WIDTH/widthD)*heightD;
                        		widthD=LIMIT_WIDTH;
                        	}
                        	if(heightD>=LIMIT_HEIGHT) {
                        		widthD = (LIMIT_HEIGHT/heightD)*widthD;
                        		heightD = LIMIT_HEIGHT;
                        	}
                        	dimage = image.getScaledInstance((int)Math.round(widthD), (int)Math.round(heightD), Image.SCALE_DEFAULT);
                            tag = new BufferedImage((int)Math.round(widthD), (int)Math.round(heightD), BufferedImage.TYPE_INT_RGB);
                            g = tag.createGraphics();
                            g.drawImage(dimage, 0, 0, null);
                            g.dispose();
                            tempFile = new File(path, imgOriName);
                            os = new FileOutputStream(tempFile);
                            ImageIO.write(tag, fileType, os);
                        }
                        
                        imgName = tempFile.getName();
                        
                        dwidth = 182;
                        dheight = 182;
                        /*缩略图*/
                        dimage = image.getScaledInstance(dwidth, dheight, Image.SCALE_DEFAULT);
                        tag = new BufferedImage(dwidth, dheight, BufferedImage.TYPE_INT_RGB);
                        g = tag.createGraphics();
                        g.drawImage(dimage, 0, 0, null);
                        g.dispose();
                        path = DBUtils.getUploadPath()+"/temp";
                        tempFile = new File(path, imgLtName);
                        os = new FileOutputStream(tempFile);
                        ImageIO.write(tag, fileType, os);
                        
                        imgHeight = dheight;
                        imgWidth = dwidth;
                        
                        return "preview";
                    case 3:
                        // 上传系统消息图片
                        // 计算是否超过图片上传限制
                        
                        
                    	/*删除原有的系统图片*/
                        SysFile fileParam = new SysFile();
                        fileParam.setDataId(sysMsgId);
                        fileParam.setFileType(7);
                        List<SysFile> sysFiles = sysFileService.list(fileParam);
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
                        
                        sysFileService.delete(fileParam);
                    	
                        fi = new SysFile();
                        fi.setDataId(sysMsgId);
                        fi.setFileType(7);
                        
                        SysMsg sysMsg = sysMsgService.selectById(sysMsgId);
                        sysMsg.setHasImg(1);
                        sysMsgService.updateEntity(sysMsg);
                        
                        if (CommonUtils.getArticleImgCount() <= sysFileService.getCount(fi))
                        {
                            imgName = "error";
                            errorMsg = "上传图片超过限制";
                            
                            return "preview";
                        }
                        
                        image = ImageIO.read(this.getFile().get(i));
                        width = image.getWidth();
                        height = image.getHeight();
                        imgNameStr = UUID.randomUUID().toString();
                        fileName = this.getFileFileName().get(i);
                        fileType = FilenameUtils.getExtension(fileName);
                        imgOriName = imgNameStr+ "." + fileType;
                        imgLtName = imgNameStr +"-Lt"+ "." + fileType;
                        // 原图
                        b = new byte[8];
                        imgName = imgOriName;
                        if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
                        	FtpUtils.uploadFile(new FileInputStream(this.getFile().get(i)), "sysmsg/original", imgName);
                        } else {
                        	is = new BufferedInputStream(new FileInputStream(this.getFile().get(i)));
                        	path = DBUtils.getUploadPath()+"/sysmsg/original";
                        	FileUtils.mkDirs(path);//创建文件路径
                        	tempFile = new File(path, imgOriName);
                        	os = new FileOutputStream(tempFile);
                        	
                        	while (-1 != is.read(b))
                        	{
                        		os.write(b);
                        	}
                        	
                        }
                        
                        dwidth = 182;
                        dheight = 182;
                        /*缩略图*/
                        dimage = image.getScaledInstance(dwidth, dheight, Image.SCALE_DEFAULT);
                        tag = new BufferedImage(dwidth, dheight, BufferedImage.TYPE_INT_RGB);
                        g = tag.createGraphics();
                        g.drawImage(dimage, 0, 0, null);
                        g.dispose();
                        
                        if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
                        	File srcFile = this.getFile().get(i);
                        	BufferedImage imageF = ImageIO.read(srcFile);
                        	BufferedImage bi = imageF.getSubimage(0,0, width, height);
                        	tag.flush();
        					ByteArrayOutputStream bs = new ByteArrayOutputStream();
        					ImageOutputStream imOut;
        					imOut = ImageIO.createImageOutputStream(bs);
        					ImageIO.write(bi, fileType, imOut);
        					FtpUtils.uploadFile(new ByteArrayInputStream(bs.toByteArray()), "sysmsg/thumbnail", imgLtName);
        					bs.close();
                    	} else {
                    		path = DBUtils.getUploadPath()+"/sysmsg/thumbnail";
                    		FileUtils.mkDirs(path);//创建文件路径
                    		tempFile = new File(path, imgLtName);
                    		os = new FileOutputStream(tempFile);
                    		ImageIO.write(tag, fileType, os);
                    	}
                        imgHeight = dheight;
                        imgWidth = dwidth;
                        
                        fi.setSeqId(1);
                        fi.setFileName(imgLtName);
                        fi.setFilePath("/sysmsg/thumbnail");
                        sysFileService.saveEntity(fi);
                        fi.setSeqId(2);
                        fi.setFileName(imgOriName);
                        fi.setFilePath("/sysmsg/original");
                        
                        fileId = sysFileService.saveEntity(fi);
                        
                        return "preview";
                    case 4:
                        // 头像 图片
                        image = ImageIO.read(this.getFile().get(i));
                        width = image.getWidth();
                        height = image.getHeight();
                        imgNameStr = UUID.randomUUID().toString();
                        fileName = this.getFileFileName().get(i);
                        fileType = FilenameUtils.getExtension(fileName);
                        imgOriName = imgNameStr+ "." + fileType;
                        imgLtName = imgNameStr +"-Lt"+ "." + fileType;
                        // 原图
                        is = new BufferedInputStream(new FileInputStream(this.getFile().get(i)));
                        path = DBUtils.getUploadPath()+"/temp";
                        FileUtils.mkDirs(path);//创建文件路径
                        tempFile = new File(path, imgOriName);
                        os = new FileOutputStream(tempFile);
                        
                        b = new byte[8];
                        while (-1 != is.read(b))
                        {
                            os.write(b);
                        }
                        imgName = tempFile.getName();
                        
                        dwidth = 152;
                        dheight = 152;
                        /*缩略图*/
                        dimage = image.getScaledInstance(dwidth, dheight, Image.SCALE_DEFAULT);
                        tag = new BufferedImage(dwidth, dheight, BufferedImage.TYPE_INT_RGB);
                        g = tag.createGraphics();
                        g.drawImage(dimage, 0, 0, null);
                        g.dispose();
                        path = DBUtils.getUploadPath()+"/temp";
                        FileUtils.mkDirs(path);//创建文件路径
                        tempFile = new File(path, imgLtName);
                        os = new FileOutputStream(tempFile);
                        ImageIO.write(tag, fileType, os);
                        
                        imgHeight = dheight;
                        imgWidth = dwidth;
                        
                        return "preview";
                    default:
                }
            }
        }
        catch (Exception e)
        {
            if (-1 == uploadType || 0 == uploadType)
            {
                imgName = "error";
                errorMsg = "发生错误";
                
                return "preview";
            }
            this.addFieldError("file", "发生错误");
            return "forinput";
        }
        finally
        {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
        
        return SUCCESS;
    }
    
    public void impUser(String fileName, String[][] result)
    {
        // excel格式：用户名-密码-昵称
        User ui = null;
        int rowLength = result.length;
        if (0 == rowLength)
        {
            rsts.add(fileName + "：没有数据");
        }
        for (int i = 0; i < rowLength; i++)
        {
            if (StringUtils.isBlank(result[i][0]))
            {
                rsts.add(fileName + "：第" + (i + 2) + "行用户名为空");
                continue;
            }
            if (StringUtils.isBlank(result[i][1]))
            {
                rsts.add(fileName + "：第" + (i + 2) + "行密码为空");
                continue;
            }
            ui = new User();
            ui.setHuiNo(result[i][0]);
            if (null != userService.selectByKey(ui))
            {
                rsts.add(fileName + "：第" + (i + 2) + "行汇答号" + result[i][0] + "已被注册");
                continue;
            }
            try
            {
                ui.setUserPwd(MD5Encrypt.encode(result[i][1]));
                ui.setNickName(StringUtils.isBlank(result[i][2]) ? null : result[i][2]);
                userService.saveEntity(ui);
            }
            catch (Exception e)
            {
                rsts.add(fileName + "：第" + (i + 2) + "行插入异常");
                continue;
            }
        }
    }
    
    @SuppressWarnings("deprecation")
    public String[][] getData(File file, int ignoreRows)
        throws FileNotFoundException, IOException
    {
        List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        // 打开HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++)
        {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++)
            {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null)
                {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize)
                {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++)
                {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null)
                    {
                        switch (cell.getCellType())
                        {
                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell))
                                {
                                    Date date = cell.getDateCellValue();
                                    if (date != null)
                                    {
                                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                    }
                                    else
                                    {
                                        value = "";
                                    }
                                }
                                else
                                {
                                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals(""))
                                {
                                    value = cell.getStringCellValue();
                                }
                                else
                                {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                                break;
                            default:
                                value = "";
                        }
                    }
                    
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }
                
                if (hasValue)
                {
                    result.add(values);
                }
            }
        }
        IOUtils.closeQuietly(in);
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++)
        {
            returnArray[i] = (String[])result.get(i);
        }
        return returnArray;
    }
    
    /**
    * 去掉字符串右边的空格
    * @param str 要处理的字符串
    * @return 处理后的字符串
    */
    public String rightTrim(String str)
    {
        if (str == null)
        {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--)
        {
            if (str.charAt(i) != 0x20)
            {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }
    
}