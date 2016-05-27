package model; 

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
	private String myTitle;
	/**
	 * The file containing the manuscript.
	 */
	private File myManuscript;
	/**
	 * A list of reviews of the manuscript.
	 */
	private List<Review> myReviews;
	/**
	 * The name of the submitting author.
	 */
	private String myAuthorName;
	/**
	 * The recommendation of the subprogram chair assigned to this manuscript.
	 */
	private Recommendation myRecommendation;
	/**
	 * The program chair's approval of this manuscript.
	 */
	private ManuscriptAcceptanceStatus myStatus;
	
	/**
	 * @version 5/25/2016
	 */
	public Manuscript(File theManuscript, String theAuthorName, String theTitle) {
		myManuscript = theManuscript;
		myReviews = new ArrayList<Review>();
		myAuthorName = theAuthorName;
		myStatus = ManuscriptAcceptanceStatus.NO_DECISION;
		myRecommendation = null;
		myTitle = theTitle;
	}
	
	/**
	 * @version 5/26/2016
	 * @param theOther the Manuscript to be copied
	 */
	public Manuscript(Manuscript theOther) {
		myManuscript = theOther.myManuscript;
		myReviews = new ArrayList<Review>();
		for (Review r : theOther.myReviews) {
			myReviews.add(new Review(r));
		}
		myAuthorName = theOther.myAuthorName;
		myStatus = theOther.myStatus;
		myRecommendation = theOther.myRecommendation;
		myTitle = theOther.myTitle;
	}

	public String getMyTitle() {
		return myTitle;
	}

	public String getMyAuthorName() {
		return myAuthorName;
	}

	public ManuscriptAcceptanceStatus getMyApproval() {
		return myStatus;
	}

	public File getMyManuscript() {
		return myManuscript;
	}

	public void setMyManuscript(File myManuscript) {
		this.myManuscript = myManuscript;
	}

	public List<Review> getMyReviews() {
		return myReviews;
	}

	public void setMyReviews(List<Review> myReviews) {
		this.myReviews = myReviews;
	}

	public Recommendation getMyRecommendation() {
		return myRecommendation;
	}

	public void setMyRecommendation(Recommendation myRecommendation) {
		this.myRecommendation = myRecommendation;
	}

	public void setMyApproval(ManuscriptAcceptanceStatus myApproval) {
		this.myStatus = myApproval;
	}

	public void setMyAuthorName(String myAuthorName) {
		this.myAuthorName = myAuthorName;
	}

	public void setMyTitle(String myTitle) {
		this.myTitle = myTitle;
	}
	
}