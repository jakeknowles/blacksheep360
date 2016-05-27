package model; 

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
	 * A list of manuscripts that have been assigned to the reviewer.
	 */
	private List<Manuscript> myManuscripts;
	/**
	 * A list of reviews submitted by this reviewer.
	 */
	private List<Review> myReview;
	
	/**
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
	public Reviewer(String theName) {
		setMyManuscripts(new ArrayList<Manuscript>(MANUSCRIPT_LIMIT));
		setMyReview(new ArrayList<Review>());

	}

	/**
	 * Submits a review of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public void submitReview(File theReview, Manuscript theManuscript, int theRating) {
		Review rev = new Review(theReview, theRating, theManuscript.getMyTitle());
		getMyReview().add(rev);
		theManuscript.getMyReviews().add(rev);
	}
	
	public List<Manuscript> getMyManuscripts() {
		return myManuscripts;
	}

	public void setMyManuscripts(List<Manuscript> myManuscripts) {
		this.myManuscripts = myManuscripts;
	}

	public List<Review> getMyReview() {
		return myReview;
	}

	public void setMyReview(List<Review> myReview) {
		this.myReview = myReview;
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
		if(getMyManuscripts().size() < MANUSCRIPT_LIMIT && !theManuscript.getMyAuthorName().equals(theUserName)) {
			getMyManuscripts().add(theManuscript);
			result = true;
		}
		return result;
	}
	
	/**
	 * Returns a List of all manuscripts assigned to the reviewer.
	 * 
	 * @version 5/8/2016
	 */
	public List<Manuscript> getManuscripts() {
		return getMyManuscripts();
	}
}