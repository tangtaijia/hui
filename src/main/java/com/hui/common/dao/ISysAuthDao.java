package com.hui.common.dao;

import java.util.List;

import com.hui.common.entity.SysAuth;
import com.hui.common.entity.SysMenu;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISysAuthDao extends IBaseDao<SysAuth, SysAuth>
{
    
    public Integer deleteRoleAuth(List<Integer> roleIds);
    
    public List<SysMenu> selectAllRolesAuth(List<Integer> roleIds);
    
}