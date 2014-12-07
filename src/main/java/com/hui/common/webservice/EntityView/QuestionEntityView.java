package com.hui.common.webservice.EntityView;

public class QuestionEntityView {
	private int questionId;
	
	private String gradeText;
	
	private String courseText;
	
	private String questionDescription;
	
	private int reward;
	
	private String thumbnailUrl;
	
	private int hasImage;
	
	private String imageId;
	
	private long timeTick;
	
	private int hasBestAnswer;
	
	private int answers;
	
	private String huidaNo;
	
	private String nickname;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getGradeText() {
		return gradeText;
	}

	public void setGradeText(String gradeText) {
		this.gradeText = gradeText;
	}

	public String getCourseText() {
		return courseText;
	}

	public void setCourseText(String courseText) {
		this.courseText = courseText;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public long getTimeTick() {
		return timeTick;
	}

	public void setTimeTick(long timeTick) {
		this.timeTick = timeTick;
	}

	public int getHasBestAnswer() {
		return hasBestAnswer;
	}

	public void setHasBestAnswer(int hasBestAnswer) {
		this.hasBestAnswer = hasBestAnswer;
	}

	public int getHasImage() {
		return hasImage;
	}

	public void setHasImage(int hasImage) {
		this.hasImage = hasImage;
	}

	public int getAnswers() {
		return answers;
	}

	public void setAnswers(int answers) {
		this.answers = answers;
	}

	public String getHuidaNo() {
		return huidaNo;
	}

	public void setHuidaNo(String huidaNo) {
		this.huidaNo = huidaNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
