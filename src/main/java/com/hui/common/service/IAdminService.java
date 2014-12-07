package com.hui.common.service;

import java.util.List;

import com.hui.common.entity.Admin;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-2-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IAdminService extends IBaseService<Admin, Admin>
{
    
    public Integer modifyStatus(Integer status, List<Integer> ids);
    
    public List<Admin> needUnlockList();
    
}