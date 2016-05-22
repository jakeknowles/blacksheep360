
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.Serializable;

/**
 * A subprogram chair.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class SubProgramChair implements Serializable {

	private static final long serialVersionUID = 7066022273971728744L;
	/**
	 * The maximum number of manuscripts that can be assigned to a subprogram chair.
	 */
	public static final int  MANUSCRIPT_LIMIT = 4;
	/**
	 * The list of manuscripts assigned to the subprogram chair.
	 */
	private List<Manuscript> myManuscripts;
	
	/**
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
	public SubProgramChair() {
		setMyManuscripts(new ArrayList<Manuscript>(MANUSCRIPT_LIMIT));
	}

	/**
	 * Assigns the Reviewer to the Manuscript. If the Manuscript is not
	 * one that the Subprogram Chair has been assigned, or the Reviewer 
	 * has already been assigned four Manuscript Reviews, or the Reviewer
	 * is the author of the Manuscript then this method does nothing.
	 * 
	 * @param theManuscript the manuscript that is assigned to the reviewer
	 * @param theReviewer the Reviewer that is assigned a manuscript
	 * 
	 * @author Geoffrey Tanay
	 * @version 05/08/2016
	 */
	public boolean assignReviewer(final Manuscript theManuscript, final User theReviewer) {
		boolean result = false;
		if(!theReviewer.isRole(User.REVIEWER)) {
			theReviewer.getMyRoles().myReviewer = new Reviewer(theReviewer.getMyName());
		}
		result = theReviewer.getMyRoles().myReviewer.addManuscript(theManuscript, theReviewer.getMyName());
		return result;
	}
	
	/**
	 * Submits a manuscript recommendation.
	 * 
	 * @param theManuscript
	 * @param theRecommendation
	 * 
	 * @author Geoffrey Tanay
	 * @version 05/08/2016
	 */
	public void submitRecommendation(final Manuscript theManuscript, final File theRecommendation) {
		theManuscript.setMyRecommendation(new Recommendation(theRecommendation));
	}
	
	public List<Manuscript> getMyManuscripts() {
		return myManuscripts;
	}

	public void setMyManuscripts(List<Manuscript> myManuscripts) {
		this.myManuscripts = myManuscripts;
	}

	/**
	 * Adds a manuscript to the list of assigned manuscripts.
	 * 
	 * @param theManuscript
	 * 
	 * @author Geoffrey Tanay
	 * @version 05/08/2016
	 */
	public boolean addManuscript(final Manuscript theManuscript, final String theUserName) {
		boolean result = false;
		if(getMyManuscripts().size() < MANUSCRIPT_LIMIT && !theManuscript.getMyAuthorName().equals(theUserName)) {
			getMyManuscripts().add(theManuscript);
			result = true;
		}
		return result;
	}

	/**
	 * Returns a List of the manuscripts assigned to the subprogram chair.
	 * 
	 * @version 5/8/2016
	 */
	public List<Manuscript> getManuscripts() {
		return getMyManuscripts();
	}
	
	public String toString() {
		return "Im a SubPC";
	}

}
