package entity;

public class Answer {
	private int answerId;
	private int fk_answer_user;
	public int getFk_answer_user() {
		return fk_answer_user;
	}
	public void setFk_answer_user(int fk_answer_user) {
		this.fk_answer_user = fk_answer_user;
	}
	private String answerContent;
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", fk_answer_user=" + fk_answer_user + ", answerContent="
				+ answerContent + "]";
	}

	
}
