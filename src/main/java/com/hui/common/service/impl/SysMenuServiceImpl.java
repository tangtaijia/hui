package com.hui.common.service.impl;

import java.util.List;

import com.hui.common.dao.ISysMenuDao;
import com.hui.common.entity.SysMenu;
import com.hui.common.service.ISysMenuService;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenu> implements ISysMenuService
{
    
   
    public List<SysMenu> selectAllMenus(SysMenu mi)
    {
        return ((ISysMenuDao)baseDao).selectAllMenus(mi);
    }
    
}