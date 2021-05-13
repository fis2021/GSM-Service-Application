package managers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FeedbackData {

    private final StringProperty username;
    private final StringProperty review;
    private final IntegerProperty rating;

    public FeedbackData(String username, String review, Integer rating){
        this.username = new SimpleStringProperty(username);
        this.review = new SimpleStringProperty(review);
        this.rating = new SimpleIntegerProperty(rating);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getReview() {
        return review.get();
    }

    public StringProperty reviewProperty() {
        return review;
    }

    public void setReview(String review) {
        this.review.set(review);
    }

    public int getRating() {
        return rating.get();
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }
}
