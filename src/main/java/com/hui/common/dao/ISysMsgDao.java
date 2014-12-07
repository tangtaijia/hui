package com.hui.common.dao;

import java.util.List;

import com.hui.common.entity.SysMsg;

/**
 * 系统消息Dao
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISysMsgDao extends IBaseDao<SysMsg, SysMsg>
{
    List<SysMsg> selectAllE(SysMsg sysMsg);
    
    Integer getCountE(SysMsg sysMsg);
}
