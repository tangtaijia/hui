package com.hui.common.dao;

import java.util.List;

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
public interface ISysMenuDao extends IBaseDao<SysMenu, SysMenu>
{
    
    public List<SysMenu> selectAllMenus(SysMenu mi);
    
    public List<SysMenu> selectAllOpt(SysMenu mi);
    
}