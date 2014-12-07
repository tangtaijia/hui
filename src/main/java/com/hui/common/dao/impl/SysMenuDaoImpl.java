package com.hui.common.dao.impl;

import java.util.List;

import com.hui.common.dao.ISysMenuDao;
import com.hui.common.entity.SysMenu;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysMenuDaoImpl extends AbstractBaseDao<SysMenu, SysMenu> implements ISysMenuDao
{
    
    @SuppressWarnings("unchecked")
   
    public List<SysMenu> selectAllMenus(SysMenu mi)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".selectAllMenus", mi);
    }
    
    @SuppressWarnings("unchecked")
   
    public List<SysMenu> selectAllOpt(SysMenu mi)
    {
        return getSqlMapClientTemplate().queryForList(namespace + ".selectAllOpt", mi);
    }
    
}