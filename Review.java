import java.io.File;
import java.io.Serializable;

public class Review implements Serializable {
	
	public File myReviewForm;
	
	public Review(File theReviewForm) {
		myReviewForm = theReviewForm;
	}
	
	public String toString() {
		return myReviewForm.getName();
	}

}