import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Manuscript implements Serializable {

	public String myTitle;
	public File myManuscript;
	public List<Review> myReviews;
	public String myAuthorName;
	public Recommendation myRecommendation;
	public Boolean myApproval;
	
	public Manuscript(File theManuscript, String theAuthorName) {
		myManuscript = theManuscript;
		myReviews = new ArrayList<Review>();
		myAuthorName = theAuthorName;
		myApproval = false;
		myRecommendation = null;
		myTitle = theManuscript.getName();
	}
	
	public String toString() {
		return myTitle + " - " + myAuthorName;
	}
	
}
