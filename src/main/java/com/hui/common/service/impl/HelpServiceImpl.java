package com.hui.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IHelpDao;
import com.hui.common.entity.Help;
import com.hui.common.entity.Page;
import com.hui.common.service.IHelpService;

@Service("helpService")
public class HelpServiceImpl extends BaseServiceImpl<Help, Help> implements IHelpService
{
    @Autowired
    private IHelpDao helpDao;

   
    public Page<Help> getHelps(Integer pageNo)
    {
        Page<Help> page = new Page<Help>();
        page.setPage(pageNo);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<Help> helps = this.helpDao.selectAllByMap(paramMap);
        Integer count = this.helpDao.getCount(paramMap);
        page.setDatas(helps);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
   
    public List<Help> getHelps()
    {
        List<Help> helps = this.helpDao.selectAll();
        return helps;
    }
}
