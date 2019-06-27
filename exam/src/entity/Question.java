package entity;

public class Question {
	private int questionId;
	private int fk_question_user;
	private String questionContent;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public int getFk_question_user() {
		return fk_question_user;
	}
	public void setFk_question_user(int fk_question_user) {
		this.fk_question_user = fk_question_user;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", fk_question_user=" + fk_question_user + ", questionContent="
				+ questionContent + "]";
	}
	
	
}
