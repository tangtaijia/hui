package com.hui.common.webservice;

public class WebServiceResult {
	
	public Integer code;
	
	public String msg;
	
	public WebServiceResult(){
		this.code = 0;
		this.msg = "";
	}
	
	public WebServiceResult(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public WebServiceResult(String msg) {
		this.code = 0;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
