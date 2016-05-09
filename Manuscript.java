import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * The class representing a manuscript.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class Manuscript implements Serializable {

	private static final long serialVersionUID = 6767811284956410619L;
	/**
	 * Title of the manuscript.
	 */
	public String myTitle;
	/**
	 * The file containing the manuscript.
	 */
	public File myManuscript;
	/**
	 * A list of reviews of the manuscript.
	 */
	public List<Review> myReviews;
	/**
	 * The name of the submitting author.
	 */
	public String myAuthorName;
	/**
	 * The recommendation of the subprogram chair assigned to this manuscript.
	 */
	public Recommendation myRecommendation;
	/**
	 * The program chair's approval of this manuscript.
	 */
	public Boolean myApproval;
	
	/**
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
	public Manuscript(File theManuscript, String theAuthorName) {
		myManuscript = theManuscript;
		myReviews = new ArrayList<Review>();
		myAuthorName = theAuthorName;
		myApproval = false;
		myRecommendation = null;
		myTitle = theManuscript.getName();
	}
	
	/**
	 * Prints the string representation in the form:
	 * Title - Author.
	 * 
	 * @version 5/8/2016
	 */
	public String toString() {
		return myTitle + " - " + myAuthorName;
	}
	
}
