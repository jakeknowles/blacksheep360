import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Reviewer implements Serializable {

	public static final int MANUSCRIPT_LIMIT = 4;
	public List<Manuscript> myManuscripts;
	public List<Review> myReview;
	
	public Reviewer(String theName) {
		myManuscripts = new ArrayList<Manuscript>(MANUSCRIPT_LIMIT);

	}

	public void submitReview(File theReview, Manuscript theManuscript) {
		Review rev = new Review(theReview);
		myReview.add(rev);
		theManuscript.myReviews.add(rev);
	}
	
	public boolean addManuscript(Manuscript theManuscript, String theUserName) {
		boolean result = false;
		if(myManuscripts.size() < MANUSCRIPT_LIMIT && !theManuscript.myAuthorName.equals(theUserName)) {
			myManuscripts.add(theManuscript);
			result = true;
		}
		return result;
	}
	
	public String getManuscripts() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < myManuscripts.size(); i++) {
			str.append(i + 1);
			str.append(". ");
			str.append(myManuscripts.get(i).toString() + "\n");
		}
		return str.toString();
	}
}