import java.io.File;
import java.util.List;
import java.io.Serializable;

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