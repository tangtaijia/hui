package com.hui.common.webservice.EntityView;

public class FansEntityView {
	
	private String nickname;
	
	private String huidaNo;
	
	private int status;

	public String getNickname() {
		if(nickname == null){
			return "";
		}
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHuidaNo() {
		if(huidaNo == null){
			return "";
		}
		return huidaNo;
	}

	public void setHuidaNo(String huidaNo) {
		this.huidaNo = huidaNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
