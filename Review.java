import java.io.File;
import java.io.Serializable;

public class Review implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2086403833237855553L;
	public File myReviewForm;
	
	public Review(File theReviewForm) {
		myReviewForm = theReviewForm;
	}
	
	public String toString() {
		return myReviewForm.getName();
	}

}