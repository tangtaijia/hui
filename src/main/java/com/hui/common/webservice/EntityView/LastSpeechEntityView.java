package com.hui.common.webservice.EntityView;

public class LastSpeechEntityView {
	
	private String huidaNo;
	
	private String nickName;
	
	private String gradeText;
	
	private String subjectText;
	
	private String message;
	
	/*
	 * 0:提问;1:回答 
	 */
	private int speechFlag;

	public String getHuidaNo() {
		return huidaNo;
	}

	public void setHuidaNo(String huidaNo) {
		this.huidaNo = huidaNo;
	}

	public String getGradeText() {
		return gradeText;
	}

	public void setGradeText(String gradeText) {
		this.gradeText = gradeText;
	}

	public String getSubjectText() {
		return subjectText;
	}

	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}

	public int getSpeechFlag() {
		return speechFlag;
	}

	public void setSpeechFlag(int speechFlag) {
		this.speechFlag = speechFlag;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMessage() {
		StringBuilder builder = new StringBuilder();
		if(speechFlag == 0){
			builder.append(nickName);
			builder.append(" [");
			builder.append(gradeText);
			builder.append(subjectText);
			builder.append("] 刚刚提出了问题");
		}
		if(speechFlag == 1){
			builder.append(nickName);
			builder.append(" [");
			builder.append(gradeText);
			builder.append(subjectText);
			builder.append("] 刚刚回答了问题");
		}
		return builder.toString();
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
