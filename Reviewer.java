import java.io.File;
import java.util.List;


public class Reviewer extends User {

	public List myManuscripts;
	public Review[] myReview;
	
	public Reviewer(String theName, String theEmail) {
		super(theName, theEmail);
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