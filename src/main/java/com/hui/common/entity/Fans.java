package com.hui.common.entity;
/**
 * 关注
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  TangTaijia
 * @version  [版本号, 2014-5-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Fans extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8152517168793831483L;
    /**
     * 主键
     */
    private Integer fansId;
    /**
     * 关注者汇豆号
     */
    private String fromNo;
    /**
     * 被关注者汇豆号
     */
    private String toNo;
    /**
     * 关注时间
     */
    private Integer fansTime;
    
    private String nickName;

	public Integer getFansId() {
		return fansId;
	}

	public void setFansId(Integer fansId) {
		this.fansId = fansId;
	}

	public String getFromNo() {
		return fromNo;
	}

	public void setFromNo(String fromNo) {
		this.fromNo = fromNo;
	}

	public String getToNo() {
		return toNo;
	}

	public void setToNo(String toNo) {
		this.toNo = toNo;
	}

	public Integer getFansTime() {
		return fansTime;
	}

	public void setFansTime(Integer fansTime) {
		this.fansTime = fansTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    
}
