package com.hui.common.dao.impl;

import java.util.Map;

import com.hui.common.dao.ISysFileDao;
import com.hui.common.entity.SysFile;

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
public class SysFileDaoImpl extends AbstractBaseDao<SysFile, SysFile> implements ISysFileDao
{
    
   
    public Integer delete(SysFile fi)
    {
        return (Integer)getSqlMapClientTemplate().delete(namespace + ".delete", fi);
    }
    
   
    public Integer getMaxSeqId(SysFile fi)
    {
        Integer seqId = (Integer)getSqlMapClientTemplate().queryForObject(namespace + ".getMaxSeqId", fi);
        if (null == seqId)
        {
            return 0;
        }
        return seqId;
    }

	
	public SysFile querySysFileObject(Map<String, String> paramMap) {
		return (SysFile) getSqlMapClientTemplate().queryForObject(namespace + ".querySysFileObject", paramMap);
	}
    
    
}