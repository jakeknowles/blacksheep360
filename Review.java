import java.io.File;
import java.io.Serializable;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 4/29/2016
 */
public class Review implements java.io.Serializable {
	
	public File myReviewForm;
	
	public Review(File theReviewForm) {
		myReviewForm = theReviewForm;
	}
	
	public String toString() {
		return "I'm a review";
	}

}