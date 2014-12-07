package com.hui.common.webservice.EntityView;

public class OtherUserInfoEntityView {
	
	private String huidaNo;
	
	private String nickname;
	
	private int answers;
	
	private int accepts;
	
	/**
	 * 返回好友状态(0:未关注;1:已关注;2:相互关注)
	 */
	private int status;

	public String getHuidaNo() {
		if(huidaNo == null){
			return "";
		}
		return huidaNo;
	}

	public void setHuidaNo(String huidaNo) {
		this.huidaNo = huidaNo;
	}

	public String getNickname() {
		if(nickname == null){
			return "";
		}
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAnswers() {
		return answers;
	}

	public void setAnswers(int answers) {
		this.answers = answers;
	}

	public int getAccepts() {
		return accepts;
	}

	public void setAccepts(int accepts) {
		this.accepts = accepts;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
