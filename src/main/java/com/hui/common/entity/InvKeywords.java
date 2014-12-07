package com.hui.common.entity;


public class InvKeywords extends BaseEntity
{
	/**
     * 注释内容
     */
	private static final long serialVersionUID = -260605724164044875L;
	
	private Integer id ;
	
	private String value ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
