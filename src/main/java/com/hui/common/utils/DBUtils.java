package com.hui.common.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hui.common.dao.IGradeDao;
import com.hui.common.dao.IInvKeywordsDao;
import com.hui.common.dao.ISubjectDao;
import com.hui.common.dao.ISysConfigDao;
import com.hui.common.dao.ISysDataDao;
import com.hui.common.entity.Grade;
import com.hui.common.entity.InvKeywords;
import com.hui.common.entity.Subject;
import com.hui.common.entity.SysConfig;
import com.hui.common.entity.SysData;

public class DBUtils
{
    private static DBUtils instance;
    
    private static Object lock = new Object();
    
    private IGradeDao gradeDao;
    
    private ISubjectDao subjectDao;
    
    private ISysConfigDao sysConfigDao;
    
    private ISysDataDao sysDataDao;
    
    private IInvKeywordsDao invKeywordsDao;
    
    private static String fileUploadPath = null;
    
    private static final JacksonUtils ju = JacksonUtils.getInstance();
    
    private DBUtils()
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("/resources/applicationContext*.xml");
        gradeDao = (IGradeDao)ac.getBean("gradeDao");
        subjectDao = (ISubjectDao)ac.getBean("subjectDao");
        sysConfigDao = (ISysConfigDao)ac.getBean("sysConfigDao");
        sysDataDao = (ISysDataDao)ac.getBean("sysDataDao");
        invKeywordsDao = (IInvKeywordsDao)ac.getBean("invKeywordsDao");
    }
    
    public static DBUtils getInstance()
    {
        if (null == instance)
        {
            synchronized (lock)
            {
                if (null == instance)
                {
                    instance = new DBUtils();
                }
            }
        }
        return instance;
    }
    
    public static String getUploadPath() {
        if(null == fileUploadPath) {
            fileUploadPath = DBUtils.getInstance().getFileUploadPath();
        }
        if(!new File(fileUploadPath).exists()) {
            fileUploadPath = "D:/hui_files/images";
        }
        return fileUploadPath;
    }
    
    public static String getFilePath() {
            if(DBUtils.getInstance().getFileServer()==0) {//文件服务器是否是FTP
            	return DBUtils.getInstance().getRemoteFilePath();
            } else {
            	return DBUtils.getInstance().getLocalFilePath();
            }
    }
    
    public List<Grade> getGrades() {
        return gradeDao.selectAllByMap(new HashMap<String, Object>());
    }
    
    public List<Subject> getSubjects() {
        return subjectDao.selectAllByMap(new HashMap<String, Object>());
    }
    
    public String getFileUploadPath() {
        SysConfig sysConfigParam = new SysConfig();
        sysConfigParam.setConfigCode("file_upload_path");
        List<SysConfig> sysConfigs  = sysConfigDao.selectByKey(sysConfigParam);
        if(CommonUtils.isNotEmptyOrNull(sysConfigs)) {
            return sysConfigs.get(0).getConfigValue();
        }
        return "/upload";
    }
    
    /**
	 * 获取网站地址
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getSitePath() {
		SysData sysData = new SysData();
		sysData.setDataCode("site_path");
		List<SysData> list = sysDataDao.selectByKey(sysData);
		String url = "http://113.10.158.175:8082/hui_ah/";
		if(list != null && list.size() > 0){
			sysData = list.get(0);
			List ll = ju.readJson2List(sysData.getDataValue());
			if(ll.size() > 0){
				Map<String,Object> map = (Map<String, Object>) ll.get(0);
				url = (String) map.get("url");
			}
		}
		return url;
	}
	
	/**
	 * 文件服务器
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Integer getFileServer() {
		SysConfig sysConfigParam = new SysConfig();
        sysConfigParam.setConfigCode("file_server");
        List<SysConfig> sysConfigs  = sysConfigDao.selectByKey(sysConfigParam);
        if(CommonUtils.isNotEmptyOrNull(sysConfigs)) {
            return Integer.parseInt(sysConfigs.get(0).getConfigValue());
        }
        return 0;
	}
	
	/**
	 * 文件服务器
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getLoginSite() {
		SysConfig sysConfigParam = new SysConfig();
        sysConfigParam.setConfigCode("login_site");
        List<SysConfig> sysConfigs  = sysConfigDao.selectByKey(sysConfigParam);
        if(CommonUtils.isNotEmptyOrNull(sysConfigs)) {
            return sysConfigs.get(0).getConfigValue();
        }
        return "http://221.130.6.212:2388/justone/hdt";
	}
	
	/**
	 * 远程文件服务器路径
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getRemoteFilePath() {
		SysConfig sysConfigParam = new SysConfig();
        sysConfigParam.setConfigCode("file_path_prefix");
        List<SysConfig> sysConfigs  = sysConfigDao.selectByKey(sysConfigParam);
        if(CommonUtils.isNotEmptyOrNull(sysConfigs)) {
            return sysConfigs.get(0).getConfigValue();
        }
        return "http://113.10.158.175:8082/huiFileServer";
	}
	
	
	/**
	 * 本地文件服务器路径
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getLocalFilePath() {
		SysConfig sysConfigParam = new SysConfig();
        sysConfigParam.setConfigCode("file_read_path");
        List<SysConfig> sysConfigs  = sysConfigDao.selectByKey(sysConfigParam);
        if(CommonUtils.isNotEmptyOrNull(sysConfigs)) {
            return sysConfigs.get(0).getConfigValue();
        }
        return "/upload";
	}
	
	/**
	 * 查询所有非法关键字
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public boolean hasInvKeyWords(String value) {
		InvKeywords invKeywordsParam = new InvKeywords();
		invKeywordsParam.setValue(value);
		Integer count = invKeywordsDao.getCount(invKeywordsParam);
		if(CommonUtils.isNotEmptyOrNullOr0OrFalse(count)) {
			return true;
		}
		return false;
	}

    
    public static void main(String[] args)
    {
//        List<Grade> grades = DBUtils.getInstance().getGrades();
//        for (Grade grade:grades)
//        {
//            System.out.println(grade.getGradeName());
//        }
//        System.out.println(DBUtils.getInstance().getFileUploadPath());
//        
//        System.out.println(DBUtils.getInstance().getSitePath());
    	System.out.println(DBUtils.getInstance().getLoginSite());
    }
}
