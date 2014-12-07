package com.hui.common.service.impl;

import java.util.List;

import com.hui.common.dao.IAdminDao;
import com.hui.common.entity.Admin;
import com.hui.common.service.IAdminService;

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
public class AdminServiceImpl extends BaseServiceImpl<Admin, Admin> implements IAdminService
{
    
   
    public Integer modifyStatus(Integer status, List<Integer> ids)
    {
        return ((IAdminDao)baseDao).modifyStatus(status, ids);
    }
    
    public List<Admin> needUnlockList()
    {
        return ((IAdminDao)baseDao).needUnlockList();
    }
    
}