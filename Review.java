
import java.io.File;
import java.io.Serializable;

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

public class Review implements Serializable {
	
	
	private static final long serialVersionUID = -2086403833237855553L;
	/**
	 * The review form.
	 */
	private File myReviewForm;
	private int myRating;
	private String myReviewedManuscriptTitle;
	
	/**
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
	public Review(File theReviewForm, int theRating, String theReviewedManuscriptTitle) {
		myReviewForm = theReviewForm;
		setMyRating(theRating);
		setMyReviewedManuscriptTitle(theReviewedManuscriptTitle);
	}
	
	public String getMyReviewedManuscriptTitle() {
		return myReviewedManuscriptTitle;
	}

	public int getMyRating() {
		return myRating;
	}

	public void setMyRating(int myRating) {
		this.myRating = myRating;
	}

	public void setMyReviewedManuscriptTitle(String myReviewedManuscriptTitle) {
		this.myReviewedManuscriptTitle = myReviewedManuscriptTitle;
	}

	public String toString() {
		return myReviewForm.getName();
	}

}