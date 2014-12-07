package com.hui.common.dao;

import java.util.List;
import java.util.Map;

import com.hui.common.entity.Msg;

public interface IMessageDao extends IBaseDao<Msg, Msg> {
	
	public int getMessageCountByType(Map<String,String> paramMap);
	
	/**
	 * 查询相应类型的消息（消息种类：1-回答我的 2-求助回答 3-回答被采纳 4-系统消息）
	 * <功能详细描述>
	 * @param paramMap
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<Msg> getMessagesByType(Map<String,Object> paramMap);
	/**
	 * 查询消息列表
	 * <功能详细描述>
	 * @param paramMap
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    List<Msg> getAll(Map<String, Object> paramMap);
    /**
     * 查询消息数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    Integer getCount(Map<String, Object> paramMap);
	
	public List getMessagesPagingByType(Map<String,String> paramMap);
	
	public void setMessageRead(Map<String,String> paramMap) throws Exception;
	
	Integer deleteByTypeAndInfoId(Map<String, Object> paramMap);
	
	public void setMessageReadByCondition(Map<String, String> paramMap);

}
