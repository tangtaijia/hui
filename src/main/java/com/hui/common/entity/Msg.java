package com.hui.common.entity;

/**
 * 消息
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Msg extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -162411270081190250L;
    /**
     * 主键
     */
    private Integer msgId;
    /**
     * 被推送的消息id
     */
    private Integer msgInfoId;
    /**
     * 汇答号
     */
    private String huiNo;
    /**
     * 消息种类：1-回答我的 2-求助回答 3-回答被采纳 4-系统消息
     */
    private Integer msgType;
    /**
     * 是否已读 0-未读  1-已读
     */
    private Integer isRead;
    
    private String msgContent;
    /**
     * 创建时间
     */
    private Integer createTime;
    /**
     * 创建时间(前台显示)
     */
    private String createTimeStr;
    
    public Integer getMsgId()
    {
        return msgId;
    }
    public void setMsgId(Integer msgId)
    {
        this.msgId = msgId;
    }
    public Integer getMsgInfoId()
    {
        return msgInfoId;
    }
    public void setMsgInfoId(Integer msgInfoId)
    {
        this.msgInfoId = msgInfoId;
    }
    public String getHuiNo()
    {
        return huiNo;
    }
    public void setHuiNo(String huiNo)
    {
        this.huiNo = huiNo;
    }
    public Integer getMsgType()
    {
        return msgType;
    }
    public void setMsgType(Integer msgType)
    {
        this.msgType = msgType;
    }
    public Integer getIsRead()
    {
        return isRead;
    }
    public void setIsRead(Integer isRead)
    {
        this.isRead = isRead;
    }
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
    public Integer getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Integer createTime)
    {
        this.createTime = createTime;
    }
    public String getCreateTimeStr()
    {
        return createTimeStr;
    }
    public void setCreateTimeStr(String createTimeStr)
    {
        this.createTimeStr = createTimeStr;
    }
    
}
