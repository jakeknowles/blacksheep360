import java.io.File;
import java.util.List;
import java.io.Serializable;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 4/29/2016
 */
public class Reviewer implements java.io.Serializable {

	public Manuscript[] myManuscripts;
	public Review[] myReview;
	
	public Reviewer(String theName) {
		myManuscripts = new Manuscript[4];

	}

	public void submitReview(File theReview, int theReviewNum) {
		myReview[theReviewNum] = new Review(theReview);
	}
	
// Note: Commenting this out b/c I don't believe it's required since there is no user story.
//	public void editReview() {
//		
//	}
	
	public String toString() {
		return  "Im a Reviewer";
	}

}