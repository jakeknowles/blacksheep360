package model; 

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
	 * Whether the Manuscript had been assigned to a Program Chair
	 */
	private boolean myAssignedtoSubProgramChair;
	/**
	 * a manuscript file stored by the system.
	 */
	private File myStoredManuscript;
	
	/**
	 * @version 5/25/2016
	 * @throws IOException 
	 */
	public Manuscript(File theManuscript, String theAuthorName, String theTitle) throws IOException {
		myManuscript = theManuscript;
		myReviews = new ArrayList<Review>();
		myAuthorName = theAuthorName;
		myStatus = ManuscriptAcceptanceStatus.NO_DECISION;
		myRecommendation = null;
		myTitle = theTitle;
		myAssignedtoSubProgramChair = false;
		myStoredManuscript = Store();
	}
	
	/**
	 * Constructor for testing and setting a conference to a particular state.
	 * 
	 * @param theManuscript
	 * @param theAuthorName
	 * @param theTitle
	 * @param theReviews
	 * @param theAssigned
	 * 
	 * @version 5/30/2016
	 * @throws IOException 
	 */
	public Manuscript(File theManuscript, String theAuthorName, String theTitle, List<Review> theReviews, boolean theAssigned) throws IOException {
		myManuscript = theManuscript;
		myReviews = theReviews;
		myAuthorName = theAuthorName;
		myStatus = ManuscriptAcceptanceStatus.NO_DECISION;
		myRecommendation = null;
		myTitle = theTitle;
		myAssignedtoSubProgramChair = theAssigned;
		myStoredManuscript = Store();
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
		myAssignedtoSubProgramChair = theOther.myAssignedtoSubProgramChair;
		myStoredManuscript = theOther.myStoredManuscript;
	}

	//Setters and Getters
	/**
	 * @version 5/30/2016
	 */
	public String getMyTitle() {
		return myTitle;
	}

	/**
	 * @version 5/30/2016
	 */
	public String getMyAuthorName() {
		return myAuthorName;
	}

	/**
	 * @version 5/30/2016
	 */
	public ManuscriptAcceptanceStatus getMyApproval() {
		return myStatus;
	}

	/**
	 * @version 5/30/2016
	 */
	public File getMyManuscript() {
		return myManuscript;
	}

	public void setMyManuscript(File theManuscript) {
		this.myManuscript = theManuscript;
	}

	/**
	 * @version 5/30/2016
	 */
	public List<Review> getMyReviews() {
		return myReviews;
	}

	/**
	 * @version 5/30/2016
	 */
	public Recommendation getMyRecommendation() {
		return myRecommendation;
	}

	/**
	 * @version 5/30/2016
	 */
	public void setMyRecommendation(Recommendation theRecommendation) {
		this.myRecommendation = theRecommendation;
	}

	/**
	 * @version 5/30/2016
	 */
	public void setMyApproval(ManuscriptAcceptanceStatus theApproval) {
		this.myStatus = theApproval;
	}

	/**
	 * @version 5/30/2016
	 */
	public void setMyTitle(String theTitle) {
		this.myTitle = theTitle;
	}
	
	/**
	 * @version 5/30/2016
	 */
	public boolean isAssignedtoSubProgramChair() {
		return myAssignedtoSubProgramChair;
	}
	
	/**
	 * @version 5/30/2016
	 */
	public void setAssignedtoSubProgramChair(boolean theState) {
		myAssignedtoSubProgramChair = theState;
	}
	
	/**
	 * Changes the file of a manuscript
	 * @version 6/1/2016
	 */
	public void editFile(File theNewFile) throws IOException {
		myManuscript = theNewFile;
		String pathName = "./StoredFiles/" + myManuscript.getName();
		File stored = new File(pathName);
		Files.copy(myManuscript.toPath(), stored.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	/**
	 * Stores a copy of a submitted manuscript
	 * @version 6/1/2016
	 */
	public File Store() throws IOException {
		String pathName = "./StoredFiles/" + myManuscript.getName();
		File stored = new File(pathName);
		Files.copy(myManuscript.toPath(), stored.toPath(), StandardCopyOption.REPLACE_EXISTING);
		return stored;
	}
	
}
