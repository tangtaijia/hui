package com.hui.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.hui.common.dao.ISysBackUpDao;
import com.hui.common.dao.ISysTabDao;
import com.hui.common.entity.SysBackUp;
import com.hui.common.entity.SysTab;
import com.hui.common.runner.BackUpRunnerForBU;
import com.hui.common.runner.BackUpRunnerForSEQID;
import com.hui.common.runner.BackUpThreadPool;
import com.hui.common.service.ISysBackUpService;
import com.hui.common.utils.CommonUtils;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysBackUpServiceImpl extends BaseServiceImpl<SysBackUp, SysBackUp> implements ISysBackUpService
{
    
    private ISysTabDao sysTabDao;
    
    public void setSysTabDao(ISysTabDao sysTabDao)
    {
        this.sysTabDao = sysTabDao;
    }
    
   
    public Integer createBackUp()
    {
        Integer seqId = null;
        
        // 数据备份类型：0-增量；1-全量；
        if (1 == CommonUtils.getBackupForm())
        {
            seqId = ((ISysBackUpDao)baseDao).getMaxSeqId() + 1;
            
            String filePath =
                CommonUtils.getFilePath() + File.separator + "backup" + File.separator + "auto" + File.separator
                    + "seq" + seqId;
            
            File fileSeq = new File(filePath);
            if (!fileSeq.exists())
            {
                fileSeq.mkdirs();
            }
            File file = null;
            SysBackUp bi = null;
            
            SysTab ti = new SysTab();
            ti.setStatus(0);
            
            List<SysTab> sysTabs = sysTabDao.selectAll(ti);
            
            for (SysTab sysTab : sysTabs)
            {
                bi = new SysBackUp();
                
                bi.setSeqId(seqId);
                bi.setTabId(sysTab.getTabId());
                // 数据备份方式：0-定期备份；1-手动备份；
                bi.setBackupType(0);
                bi.setBackupForm(1);
                bi.setFilePath(filePath + File.separator + sysTab.getTabName());
                bi.setFileItems(0);
                bi.setStatus(0);
                bi.setBackupTime(CommonUtils.getCurTime());
                bi.setNotes("数据待备份");
                
                baseDao.save(bi);
                
                file = new File(filePath + File.separator + sysTab.getTabName());
                if (!file.exists())
                {
                    file.mkdir();
                }
            }
        }
        else
        {
            Integer increBackUpSeqId = ((ISysBackUpDao)baseDao).getIncreBackUpSeqId();
            if (null == increBackUpSeqId)
            {
                seqId = ((ISysBackUpDao)baseDao).getMaxSeqId() + 1;
                
                String filePath =
                    CommonUtils.getFilePath() + File.separator + "backup" + File.separator + "auto" + File.separator
                        + "seq" + seqId;
                
                File fileSeq = new File(filePath);
                if (!fileSeq.exists())
                {
                    fileSeq.mkdirs();
                }
                File file = null;
                SysBackUp bi = null;
                
                SysTab ti = new SysTab();
                ti.setStatus(0);
                
                List<SysTab> sysTabs = sysTabDao.selectAll(ti);
                
                for (SysTab sysTab : sysTabs)
                {
                    bi = new SysBackUp();
                    
                    bi.setSeqId(seqId);
                    bi.setTabId(sysTab.getTabId());
                    // 数据备份方式：0-定期备份；1-手动备份；
                    bi.setBackupType(0);
                    bi.setBackupForm(0);
                    bi.setFilePath(filePath + File.separator + sysTab.getTabName());
                    bi.setFileItems(0);
                    bi.setStatus(0);
                    bi.setBackupTime(CommonUtils.getCurTime());
                    bi.setNotes("数据待备份");
                    
                    baseDao.save(bi);
                    
                    file = new File(filePath + File.separator + sysTab.getTabName());
                    if (!file.exists())
                    {
                        file.mkdir();
                    }
                }
            }
            else
            {
                seqId = increBackUpSeqId;
            }
        }
        
        BackUpThreadPool.getThreadPool().execute(new BackUpRunnerForSEQID(this, seqId));
        return seqId;
    }
    
   
    public void createBackUpHand(List<Integer> tabIds)
    {
        Integer seqId = ((ISysBackUpDao)baseDao).getMaxSeqId() + 1;
        String filePath =
            CommonUtils.getFilePath() + File.separator + "backup" + File.separator + "hand" + File.separator + "seq"
                + seqId;
        
        File fileSeq = new File(filePath);
        if (!fileSeq.exists())
        {
            fileSeq.mkdirs();
        }
        File file = null;
        SysBackUp bi = null;
        
        for (Integer tabId : tabIds)
        {
            bi = new SysBackUp();
            
            bi.setSeqId(seqId);
            bi.setTabId(tabId);
            bi.setBackupType(1);
            bi.setBackupForm(1);
            bi.setFilePath(filePath + File.separator + sysTabDao.selectById(tabId).getTabName());
            bi.setFileItems(0);
            bi.setStatus(0);
            bi.setBackupTime(CommonUtils.getCurTime());
            bi.setNotes("数据待备份");
            
            baseDao.save(bi);
            
            file = new File(filePath + File.separator + sysTabDao.selectById(tabId).getTabName());
            if (!file.exists())
            {
                file.mkdir();
            }
        }
        
        BackUpThreadPool.getThreadPool().execute(new BackUpRunnerForSEQID(this, seqId));
    }
    
   
    public void processBackUpForSEQID(Integer seqId)
    {
        SysBackUp bi = new SysBackUp();
        bi.setSeqId(seqId);
        
        List<SysBackUp> sysBackUps = baseDao.selectAll(bi);
        
        for (SysBackUp sysBackUp : sysBackUps)
        {
            BackUpThreadPool.getThreadPool().execute(new BackUpRunnerForBU(this, sysBackUp));
        }
    }
    
    @SuppressWarnings("unchecked")
   
    public void processBackUpForBU(SysBackUp bi)
    {
        OutputStream os = null;
        Writer out = null;
        
        try
        {
            bi.setStatus(1);
            bi.setNotes("数据备份中");
            
            baseDao.update(bi);
            
            int items = CommonUtils.getIncreBackupItems();
            long fileMaxSize = CommonUtils.getFileMaxSize() * 1024 * 1024;
            
            int fileItems = 0;
            if (0 == bi.getFileItems())
            {
                fileItems = 1;
            }
            else
            {
                fileItems = bi.getFileItems();
            }
            
            // 数据备份类型：0-增量；1-全量；
            if (1 == bi.getBackupForm())
            {
                Integer tabItems = ((ISysBackUpDao)baseDao).getTabItems(bi);
                for (int i = 0; i < tabItems; i = i + items)
                {
                    bi.setStart(i);
                    bi.setSize(items);
                    
                    List<Object> tabInfos = ((ISysBackUpDao)baseDao).selectTab(bi);
                    StringBuffer sb = new StringBuffer();
                    Map tab = null;
                    
                    for (Object tabInfo : tabInfos)
                    {
                        sb.append("INSERT INTO `" + bi.getTabName() + "` (");
                        tab = (HashMap)tabInfo;
                        sb.append(getFieldNames(tab.keySet().iterator())).append(") VALUES (");
                        sb.append(getFieldValues(tab.values().iterator())).append(");\n");
                    }
                    
                    File file = new File(bi.getFilePath() + File.separator + fileItems + ".sql");
                    if (!file.exists())
                    {
                        file.createNewFile();
                    }
                    else
                    {
                        if (file.length() >= fileMaxSize)
                        {
                            fileItems++;
                            file = new File(bi.getFilePath() + File.separator + fileItems + ".sql");
                            file.createNewFile();
                        }
                    }
                    
                    os = new FileOutputStream(file, true);
                    out = new OutputStreamWriter(os, "utf-8");
                    out.write(sb.toString());
                    
                    IOUtils.closeQuietly(out);
                    IOUtils.closeQuietly(os);
                }
                
                bi.setFileItems(fileItems);
            }
            else
            {
                bi.setStart(bi.getEndItems());
                bi.setSize(items);
                
                List<Object> tabInfos = ((ISysBackUpDao)baseDao).selectTab(bi);
                StringBuffer sb = new StringBuffer();
                Map tab = null;
                
                for (Object tabInfo : tabInfos)
                {
                    sb.append("INSERT INTO `" + bi.getTabName() + "` (");
                    tab = (HashMap)tabInfo;
                    sb.append(getFieldNames(tab.keySet().iterator())).append(") VALUES (");
                    sb.append(getFieldValues(tab.values().iterator())).append(");\n");
                }
                
                File file = new File(bi.getFilePath() + File.separator + fileItems + ".sql");
                if (!file.exists())
                {
                    file.createNewFile();
                }
                else
                {
                    if (file.length() >= fileMaxSize)
                    {
                        fileItems++;
                        file = new File(bi.getFilePath() + File.separator + fileItems + ".sql");
                        file.createNewFile();
                    }
                }
                
                os = new FileOutputStream(file, true);
                out = new OutputStreamWriter(os, "utf-8");
                out.write(sb.toString());
                
                bi.setStartItems(bi.getEndItems());
                bi.setEndItems(bi.getStartItems() + tabInfos.size());
                bi.setFileItems(fileItems);
                bi.setBackupTime(CommonUtils.getCurTime());
                
                IOUtils.closeQuietly(out);
                IOUtils.closeQuietly(os);
            }
            
            bi.setStatus(2);
            bi.setNotes("数据备份成功");
            
            baseDao.update(bi);
        }
        catch (Exception e)
        {
            bi.setStatus(3);
            bi.setNotes(e.getMessage().length() > 200 ? StringUtils.substring(e.getMessage(), 0, 200) : e.getMessage());
            
            baseDao.update(bi);
        }
        finally
        {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(os);
        }
    }
    
    private String getFieldNames(Iterator<String> keys)
    {
        StringBuffer result = new StringBuffer();
        
        while (keys.hasNext())
        {
            result.append("`" + keys.next() + "`,");
        }
        if (result.length() > 0)
        {
            result.deleteCharAt(result.length() - 1);
        }
        
        return result.toString();
    }
    
    private String getFieldValues(Iterator<Object> values)
    {
        StringBuffer result = new StringBuffer();
        Object value = null;
        
        while (values.hasNext())
        {
            value = values.next();
            if (null == value)
            {
                result.append("NULL,");
            }
            else
            {
                result.append("'" + value + "',");
            }
        }
        if (result.length() > 0)
        {
            result.deleteCharAt(result.length() - 1);
        }
        
        return result.toString();
    }
    
   
    public void reBackUp(Integer backupId)
    {
        SysBackUp bi = baseDao.selectById(backupId);
        
        File file = new File(bi.getFilePath());
        if (file.exists())
        {
            File[] files = file.listFiles();
            for (File temp : files)
            {
                temp.delete();
            }
        }
        
        bi.setStatus(0);
        bi.setFileItems(0);
        bi.setBackupTime(CommonUtils.getCurTime());
        bi.setNotes("数据待备份");
        
        baseDao.update(bi);
        
        BackUpThreadPool.getThreadPool().execute(new BackUpRunnerForBU(this, bi));
    }
    
}