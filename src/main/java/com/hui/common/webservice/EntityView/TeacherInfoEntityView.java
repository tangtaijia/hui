package com.hui.common.webservice.EntityView;

public class TeacherInfoEntityView {
	
	private String huidaNo;
	
	private String name;
	
	private String rank;
	
	private String introduction;
	
	private int answers;
	
	private String accepts;
	
	private int onLineTimes;
	
	private int teacherTitle;

	public String getHuidaNo() {
		return huidaNo;
	}

	public void setHuidaNo(String huidaNo) {
		this.huidaNo = huidaNo;
	}

	public String getName() {
		return name + "老师";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		if(teacherTitle == 0){
			rank = "家教";
		}
		if(teacherTitle == 1){
			rank = "教师";
		}
		if(teacherTitle == 2){
			rank = "高级教师";
		}
		if(teacherTitle == 3){
			rank = "特级教师";
		}
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getAnswers() {
		return answers;
	}

	public void setAnswers(int answers) {
		this.answers = answers;
	}

	public String getAccepts() {
		return accepts;
	}

	public void setAccepts(String accepts) {
		this.accepts = accepts;
	}

	public int getOnLineTimes() {
		return onLineTimes;
	}

	public void setOnLineTimes(int onLineTimes) {
		this.onLineTimes = onLineTimes;
	}

	public int getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(int teacherTitle) {
		this.teacherTitle = teacherTitle;
	}
	
}
