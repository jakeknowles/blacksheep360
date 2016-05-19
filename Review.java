
import java.io.File;
import java.io.Serializable;
<<<<<<< HEAD

/**
 * A review.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

=======

/**
 * A review.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

>>>>>>> master
public class Review implements Serializable {
	
	
	private static final long serialVersionUID = -2086403833237855553L;
	/**
	 * The review form.
	 */
	public File myReviewForm;
	public int myRating;
	
	/**
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
<<<<<<< HEAD
	public Review(File theReviewForm, int theRating) {
=======
	public Review(File theReviewForm) {
>>>>>>> master
		myReviewForm = theReviewForm;
		myRating = theRating;
	}
	
	public String toString() {
		return myReviewForm.getName();
	}

}