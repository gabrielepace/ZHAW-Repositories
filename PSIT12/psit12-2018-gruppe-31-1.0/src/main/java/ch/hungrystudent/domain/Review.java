package ch.hungrystudent.domain;

public class Review {
    private long userId;
    private String username;
    private long markerId;
    private String reviewText;

    public Review (long userId, String username, long markerId, String reviewText) {
        this.userId = userId;
        this.username = username;
        this.markerId = markerId;
        this.reviewText = reviewText;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getMarkerId() {
        return markerId;
    }

    public void setMarkerId(long markerId) {
        this.markerId = markerId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
