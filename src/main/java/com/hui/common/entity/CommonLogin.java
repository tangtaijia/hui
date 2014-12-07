package com.hui.common.entity;

/**
 * 第三方登录
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  LuLiLi
 * @version  [版本号, 2014-7-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommonLogin extends BaseEntity{

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1056471009289539528L;
	
	private Integer loginId;
	
	private String huiNo;
	
	private String commonKey;
	
	private Integer type;
	
	private Integer createTime;

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getHuiNo() {
		return huiNo;
	}

	public void setHuiNo(String huiNo) {
		this.huiNo = huiNo;
	}

	public String getCommonKey() {
		return commonKey;
	}

	public void setCommonKey(String commonKey) {
		this.commonKey = commonKey;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

}
