package model; 

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.Serializable;

/**
 * A reviewer.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class Reviewer implements Serializable {

	private static final long serialVersionUID = -497021263135386895L;
	
	/**
	 * The maximum number of manuscripts that can be assigned to a reviewer.
	 */
	public static final int MANUSCRIPT_LIMIT = 4;

	/**
	 * A list of reviews submitted by this reviewer.
	 */
	private Map<Manuscript, Review> myReviews;
	
	/**
	 * @version 5/8/2016
	 */
	public Reviewer() {
		myReviews = new HashMap<Manuscript, Review>();
	}
	
	public Reviewer(HashMap<Manuscript, Review> theReviews) {
		myReviews = theReviews;
	}

	/**
	 * Submits a review of a manuscript.
	 * 
	 * @version 5/8/2016
	 * @throws IOException 
	 */
	public void submitReview(File theReview, Manuscript theManuscript, int theRating) throws IOException {
		if(myReviews.containsKey(theManuscript)) {
			myReviews.remove(theManuscript);
		}
		
		Review rev = new Review(theReview, theRating, theManuscript.getMyTitle());
		myReviews.put(theManuscript, rev);
		theManuscript.getMyReviews().add(rev);
	}
	
	/**
	 * @version 5/31/2016
	 */
	public Map<Manuscript, Review> getMyReviews() {
		return myReviews;
	}

	/**
	 * Adds a manuscript to those assigned to the reviewer, but checks that the manuscript
	 * was not authored by the reviewer and that the reviewer is not already at the limit
	 * of manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public boolean addManuscript(Manuscript theManuscript, String theUserName) {
		boolean result = false;
		if(myReviews.keySet().size() < MANUSCRIPT_LIMIT && !theManuscript.getMyAuthorName().equals(theUserName)
				&& !hasReviewed(theManuscript)) {
			myReviews.put(theManuscript, null);
			result = true;
		}
		return result;
	}
	
	/**
	 * Returns a List of all manuscripts assigned to the reviewer.
	 * 
	 * @version 5/8/2016
	 */
	public Set<Manuscript> getManuscripts() {
		return myReviews.keySet();
	}
	
	/**
	 * Checks that this reviewer has submitted a review for the 
	 * given manuscript
	 * 
	 * @version 5/31/2016
	 */
	public boolean hasReviewed(Manuscript theManuscript) {
		if (myReviews.get(theManuscript) == null) {
			return false;
		} else {
			return true;
		}
	}
}
