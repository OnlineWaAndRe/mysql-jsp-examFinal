package entity;

public class Review {
	private int reviewId;
	private int fk_review_user;
	private int selfId;
	private String reviewContent;
	
	public int getSelfId() {
		return selfId;
	}
	public void setSelfId(int selfId) {
		this.selfId = selfId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getFk_review_user() {
		return fk_review_user;
	}
	public void setFk_review_user(int fk_review_user) {
		this.fk_review_user = fk_review_user;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", fk_review_user=" + fk_review_user + ", selfId=" + selfId
				+ ", reviewContent=" + reviewContent + "]";
	}

	
}
