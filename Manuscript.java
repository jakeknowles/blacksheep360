import java.io.*;
import java.util.ArrayList;
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
public class Manuscript implements java.io.Serializable {

	public File myManuscript;
	public List myReviews;
	public String myAuthorName;
	public Recommendation myRecommendation;
	public Boolean myApproval;
	
	public Manuscript(File theManuscript, String theAuthorName) {
		myManuscript = theManuscript;
		myReviews = new ArrayList<Review>();
		myAuthorName = theAuthorName;
		myApproval = false;
		myRecommendation = null;
	}
	
	public String toString() {
		return myManuscript.getName();
	}
	
}
