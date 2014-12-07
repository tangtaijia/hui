package com.hui.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hui.common.dao.IInvKeywordsDao;
import com.hui.common.dao.ISysCacheDao;
import com.hui.common.dao.ISysConfigDao;
import com.hui.common.dao.ISysDataDao;
import com.hui.common.dao.ISysMenuDao;
import com.hui.common.entity.InvKeywords;
import com.hui.common.entity.SysCache;
import com.hui.common.entity.SysConfig;
import com.hui.common.entity.SysData;
import com.hui.common.entity.SysMenu;
import com.hui.common.entity.ZTreeNodes;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.JacksonUtils;
import com.opensymphony.oscache.base.NeedsRefreshException;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysCacheManager
{
    
    private static SysCacheManager instance;
    
    private static Object lock = new Object();
    
    private ISysConfigDao sysConfigDao;
    
    private ISysDataDao sysDataDao;
    
    private ISysMenuDao sysMenuDao;
    
    private ISysCacheDao sysCacheDao;
    
    private IInvKeywordsDao invKeywordsDao;
    
    private BaseCache newCache;
    
    ApplicationContext ac = new ClassPathXmlApplicationContext("/resources/applicationContext*.xml");
    
    private SysCacheManager()
    {
        sysConfigDao = (ISysConfigDao)ac.getBean("sysConfigDao");
        sysDataDao = (ISysDataDao)ac.getBean("sysDataDao");
        sysMenuDao = (ISysMenuDao)ac.getBean("sysMenuDao");
        sysCacheDao = (ISysCacheDao)ac.getBean("sysCacheDao");
        invKeywordsDao = (IInvKeywordsDao)ac.getBean("invKeywordsDao");
        newCache = new BaseCache(CommonUtils.KEYPREFIX);
    }
    
    public static SysCacheManager getInstance()
    {
        if (null == instance)
        {
            synchronized (lock)
            {
                if (null == instance)
                {
                    instance = new SysCacheManager();
                }
            }
        }
        return instance;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, SysCache> getSysCaches()
    {
        Map<String, SysCache> sysCaches = new HashMap<String, SysCache>();
        
        try
        {
            sysCaches =
                (Map<String, SysCache>)newCache.get(CommonUtils.SYSCACHE_CACHE_CODE, CommonUtils.DEFAULT_REFRESHPERIOD);
        }
        catch (NeedsRefreshException nre)
        {
            try
            {
                List<SysCache> listSysCache = sysCacheDao.selectAll();
                for (SysCache sysCache : listSysCache)
                {
                    sysCaches.put(sysCache.getCacheCode(), sysCache);
                }
                
                newCache.put(CommonUtils.SYSCACHE_CACHE_CODE, sysCaches);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // 尝试恢复缓存数据
                sysCaches = (Map<String, SysCache>)nre.getCacheContent();
                newCache.cancelUpdate(CommonUtils.SYSCACHE_CACHE_CODE);
            }
        }
        
        return sysCaches;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, SysConfig> getSysConfigs()
    {
        Map<String, SysConfig> sysConfigs = new HashMap<String, SysConfig>();
        
        try
        {
            sysConfigs =
                (Map<String, SysConfig>)newCache.get(CommonUtils.SYSCONFIG_CACHE_CODE,
                    CommonUtils.getRefreshPeriod(CommonUtils.SYSCONFIG_CACHE_CODE));
        }
        catch (NeedsRefreshException nre)
        {
            try
            {
                List<SysConfig> listSysConfig = sysConfigDao.selectAll();
                for (SysConfig sysConfig : listSysConfig)
                {
                    sysConfigs.put(sysConfig.getConfigCode(), sysConfig);
                }
                
                newCache.put(CommonUtils.SYSCONFIG_CACHE_CODE, sysConfigs);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // 尝试恢复缓存数据
                sysConfigs = (Map<String, SysConfig>)nre.getCacheContent();
                newCache.cancelUpdate(CommonUtils.SYSCONFIG_CACHE_CODE);
            }
        }
        
        return sysConfigs;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, SysData> getSysDatas()
    {
        Map<String, SysData> sysDatas = new HashMap<String, SysData>();
        JacksonUtils ju = JacksonUtils.getInstance();
        List<LinkedHashMap<String, Object>> list = null;
        
        try
        {
            sysDatas =
                (Map<String, SysData>)newCache.get(CommonUtils.SYSDATA_CACHE_CODE,
                    CommonUtils.getRefreshPeriod(CommonUtils.SYSDATA_CACHE_CODE));
        }
        catch (NeedsRefreshException nre)
        {
            try
            {
                List<SysData> listSysData = sysDataDao.selectAll();
                for (SysData sysData : listSysData)
                {
                    list = ju.readJson2List(sysData.getDataValue());
                    if (null != list && 0 != list.size())
                    {
                        sysData.setValue(list.get(0));
                    }
                    sysDatas.put(sysData.getDataCode(), sysData);
                }
                
                newCache.put(CommonUtils.SYSDATA_CACHE_CODE, sysDatas);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // 尝试恢复缓存数据
                sysDatas = (Map<String, SysData>)nre.getCacheContent();
                newCache.cancelUpdate(CommonUtils.SYSDATA_CACHE_CODE);
            }
        }
        
        return sysDatas;
    }
    
    @SuppressWarnings("unchecked")
    public List<ZTreeNodes> getAuthsAdmin()
    {
        List<ZTreeNodes> listZTreeNodes = new ArrayList<ZTreeNodes>();
        ZTreeNodes zTreeNodes = null;
        
        try
        {
            listZTreeNodes =
                (List<ZTreeNodes>)newCache.get(CommonUtils.ADMIN_AUTHS_CACHE_CODE,
                    CommonUtils.getRefreshPeriod(CommonUtils.ADMIN_AUTHS_CACHE_CODE));
        }
        catch (NeedsRefreshException nre)
        {
            try
            {
                SysMenu mi = new SysMenu();
                mi.setRoleType(0);
                
                List<SysMenu> listSysMenu = sysMenuDao.selectAll(mi);
                for (SysMenu sysMenu : listSysMenu)
                {
                    zTreeNodes = new ZTreeNodes();
                    zTreeNodes.setId(sysMenu.getMenuId());
                    zTreeNodes.setpId(sysMenu.getParentId());
                    zTreeNodes.setName(sysMenu.getMenuName());
                    listZTreeNodes.add(zTreeNodes);
                }
                
                newCache.put(CommonUtils.ADMIN_AUTHS_CACHE_CODE, listZTreeNodes);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // 尝试恢复缓存数据
                listZTreeNodes = (List<ZTreeNodes>)nre.getCacheContent();
                newCache.cancelUpdate(CommonUtils.ADMIN_AUTHS_CACHE_CODE);
            }
        }
        
        return listZTreeNodes;
    }
    
    @SuppressWarnings("unchecked")
    public List<ZTreeNodes> getAuthsUser()
    {
        List<ZTreeNodes> listZTreeNodes = new ArrayList<ZTreeNodes>();
        ZTreeNodes zTreeNodes = null;
        
        try
        {
            listZTreeNodes =
                (List<ZTreeNodes>)newCache.get(CommonUtils.USER_AUTHS_CACHE_CODE,
                    CommonUtils.getRefreshPeriod(CommonUtils.USER_AUTHS_CACHE_CODE));
        }
        catch (NeedsRefreshException nre)
        {
            try
            {
                SysMenu mi = new SysMenu();
                mi.setRoleType(1);
                
                List<SysMenu> listSysMenu = sysMenuDao.selectAll(mi);
                for (SysMenu sysMenu : listSysMenu)
                {
                    zTreeNodes = new ZTreeNodes();
                    zTreeNodes.setId(sysMenu.getMenuId());
                    zTreeNodes.setpId(sysMenu.getParentId());
                    zTreeNodes.setName(sysMenu.getMenuName());
                    listZTreeNodes.add(zTreeNodes);
                }
                
                newCache.put(CommonUtils.USER_AUTHS_CACHE_CODE, listZTreeNodes);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // 尝试恢复缓存数据
                listZTreeNodes = (List<ZTreeNodes>)nre.getCacheContent();
                newCache.cancelUpdate(CommonUtils.USER_AUTHS_CACHE_CODE);
            }
        }
        
        return listZTreeNodes;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String, Map<String, String>> getAuthsAdminOpt()
    {
        Map<String, Map<String, String>> adminOpts = new HashMap<String, Map<String, String>>();
        Map<String, String> menuCodes = null;
        
        try
        {
            adminOpts =
                (Map<String, Map<String, String>>)newCache.get(CommonUtils.ADMIN_OPT_AUTHS_CACHE_CODE,
                    CommonUtils.getRefreshPeriod(CommonUtils.ADMIN_OPT_AUTHS_CACHE_CODE));
        }
        catch (NeedsRefreshException nre)
        {
            try
            {
                SysMenu mi = new SysMenu();
                mi.setRoleType(0);
                
                List<SysMenu> listSysMenu = sysMenuDao.selectAllOpt(mi);
                for (SysMenu sysMenu : listSysMenu)
                {
                    if (!adminOpts.containsKey(sysMenu.getMenuUrl()))
                    {
                        menuCodes = new HashMap<String, String>();
                    }
                    else
                    {
                        menuCodes = adminOpts.get(sysMenu.getMenuUrl());
                    }
                    
                    menuCodes.put(sysMenu.getMenuCode(), sysMenu.getMenuCode());
                    adminOpts.put(sysMenu.getMenuUrl(), menuCodes);
                }
                
                newCache.put(CommonUtils.ADMIN_OPT_AUTHS_CACHE_CODE, adminOpts);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // 尝试恢复缓存数据
                adminOpts = (Map<String, Map<String, String>>)nre.getCacheContent();
                newCache.cancelUpdate(CommonUtils.ADMIN_OPT_AUTHS_CACHE_CODE);
            }
        }
        
        return adminOpts;
    }
    
    @SuppressWarnings("unchecked")
    public List<InvKeywords> getInvKeywords()
    {
    	List<InvKeywords> invKeywordss = new ArrayList<InvKeywords>();
        
        try
        {
        	invKeywordss =
                (List<InvKeywords>)newCache.get(CommonUtils.INV_KEYWORDS_CACHE_CODE,
                    CommonUtils.getRefreshPeriod(CommonUtils.INV_KEYWORDS_CACHE_CODE));
        }
        catch (NeedsRefreshException nre)
        {
            try
            {
                invKeywordss = invKeywordsDao.selectAll();
                newCache.put(CommonUtils.INV_KEYWORDS_CACHE_CODE, invKeywordss);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // 尝试恢复缓存数据
                invKeywordss = (List<InvKeywords>)nre.getCacheContent();
                newCache.cancelUpdate(CommonUtils.INV_KEYWORDS_CACHE_CODE);
            }
        }
        
        return invKeywordss;
    }
    
    public void refreshCache(String cacheCode)
    {
        newCache.remove(CommonUtils.SYSCACHE_CACHE_CODE);
        newCache.remove(cacheCode);
    }
    
    public void refreshAllCache()
    {
        newCache.removeAll();
    }
    
}