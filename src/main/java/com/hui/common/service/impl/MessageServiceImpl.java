package com.hui.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.common.dao.IMessageDao;
import com.hui.common.entity.Msg;
import com.hui.common.entity.Page;
import com.hui.common.service.IMessageService;
import com.hui.common.utils.CommonUtils;
import com.hui.common.utils.FrontUtils;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl  implements IMessageService
{
    
    @Autowired
    private IMessageDao messageDao;

   
    public List<Msg> getMessagesByType(String huiNo,Integer msgType)
    {
        Map<String,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huidaNo", huiNo);
        paramMap.put("msgType", msgType);
        return this.messageDao.getMessagesByType(paramMap);
    }

   
    public Page<Msg> getMsgs(Integer pageNo, String huiNo,Integer msgType)
    {
        Page<Msg> page = new Page<Msg>();
        page.setPage(pageNo);
        
        Map<String,Object> paramMap  = new HashMap<String, Object>();
        paramMap.put("huidaNo", huiNo);
        paramMap.put("msgType", msgType);
        paramMap.put("start", page.getStart());
        paramMap.put("size", page.getSize());
        
        List<Msg> msgs = this.messageDao.getAll(paramMap);
        /*设定前台展示的创建时间*/
        if(CommonUtils.isNotEmptyOrNull(msgs)) {
            for(Msg msg:msgs) {
                msg.setCreateTimeStr(CommonUtils.formatTimeStamp(msg.getCreateTime(), CommonUtils.getLongDateFormat()));
                msg = FrontUtils.handlerSpecWord(msg);
            }
        }
        Integer count = this.messageDao.getCount(paramMap);
        page.setDatas(msgs);
        page.setTotal(count);
        page.getTotalPages();
        return page;
    }
    
   
    public Integer readMsg(Integer msgId, String huiNo)
    {
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("messageId", String.valueOf(msgId));
        paramMap.put("huidaNo", huiNo);
        try
        {
            messageDao.setMessageRead(paramMap);
        }
        catch (Exception e)
        {
            return 0;
        }
        return 1;
    }
    
    public Boolean hasRead(Integer msgId, String huiNo,String msgType) {
//    	Map<String,Object> paramMap = new HashMap<String, Object>();
//    	paramMap.put("huidaNo", huiNo);
//        paramMap.put("msgType", msgType);
//        paramMap.put("start", page.getStart());
//        paramMap.put("size", page.getSize());
//        
//        Integer count = this.messageDao.getCount(paramMap);
//    	// TODO Auto-generated method stub
    	return null;
    }
   
    public Integer unReadNum(String huiNo)
    {
        return unReadNum(huiNo, null);
    }
    
   
    public Integer unReadNum(String huiNo, Integer msgType)
    {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("huidaNo", huiNo);
        paramMap.put("msgType", msgType);
        paramMap.put("isRead", 0);
        return messageDao.getCount(paramMap);
    }
    
   
    public Integer deleteByTypeAndInfoId(Map<String, Object> paramMap)
    {
        return messageDao.deleteByTypeAndInfoId(paramMap);
    }
}
