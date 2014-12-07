package com.hui.common.service;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.Msg;
import com.hui.common.entity.Page;

public interface IMessageService
{
    /**
     * 查询相应类型的消息（消息种类：1-回答我的 2-求助回答 3-回答被采纳 4-系统消息）
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Msg> getMessagesByType(String huiNo,Integer msgType);
    
    /**
     * 消息分页
     * <功能详细描述>
     * @param pageNo
     * @param huiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Page<Msg> getMsgs(Integer pageNo,String huiNo,Integer msgType);
    
    /**
     * 读取消息
     * <功能详细描述>
     * @param msgId
     * @param huiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer readMsg(Integer msgId,String huiNo);
    
    /**
     * 是否已读
     * <功能详细描述>
     * @param msgId
     * @param huiNo
     * @param msgType
     * @return
     * @see [类、类#方法、类#成员]
     */
    Boolean hasRead(Integer msgId, String huiNo,String msgType);
    
    /**
     * 未读消息数量
     * <功能详细描述>
     * @param huiNo
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer unReadNum(String huiNo);
    
    /**
     * 未读消息数量
     * <功能详细描述>
     * @param huiNo
     * @param msgType
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer unReadNum(String huiNo,Integer msgType);
    
    Integer deleteByTypeAndInfoId(Map<String, Object> paramMap);
}
