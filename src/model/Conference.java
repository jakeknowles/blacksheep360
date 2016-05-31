package model; 

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * This class represents a conference. 
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class Conference implements Serializable {

	private static final long serialVersionUID = 5606112605169805993L;
	/**
	 * the program chair of the conference.
	 */
	private User myProgramChair;
	
	/**
	 * The name of the conference.
	 */
	private String myConfName;
	
	/**
	 * A map of the submitted manuscript. With the title of the
	 * manuscripts as the key.
	 */
	private HashMap<String, Manuscript> myManuscripts;
	
	/**
	 * The deadline by which an author must submit a paper.
	 */
	private Date myManuscriptDeadline;
	
	private int myLowestRating;
	private int myHighestRating;
	
	private HashMap<String, User> myUsers;
	
	/**
	 * Constructor
	 * 
	 * @version 5/8/2016
	 */
	public Conference(User thePC, String theConfName, Date theDeadline, int theLowRating, int theHighRating) {
		myProgramChair = thePC;
		thePC.assignProgramChair(new ProgramChair());
		myConfName = theConfName;
		myManuscripts = new HashMap<String, Manuscript>();
		myUsers = new HashMap<String, User>();
		myManuscriptDeadline = theDeadline;
		myLowestRating = theLowRating;
		myHighestRating = theHighRating;
	}
	
	/**
	 * Adds a Manuscript to the map of submitted manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public void addManuscript(Manuscript theManuscript) {
		getMyManuscripts().put(theManuscript.getMyTitle(), theManuscript);
	}
	
	public Date getMyManuscriptDeadline() {
		return myManuscriptDeadline;
	}

	public HashMap<String, Manuscript> getMyManuscripts() {
		return myManuscripts;
	}

	public String getMyConfName() {
		return myConfName;
	}
	
	public HashMap<String, User> getUserMap() {
		return myUsers;
	}
	
	public void editManuscriptTitle(String theOldTitle, String theNewTitle) {
		Manuscript man = myManuscripts.get(theOldTitle);
		myManuscripts.remove(theOldTitle);
		man.setMyTitle(theNewTitle);
		myManuscripts.put(theNewTitle, man);
	}

	/**
	 * Removes a manuscript from the submitted manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public void removeManuscript(String theTitle) {
		getMyManuscripts().remove(theTitle);
	}
	
	public int getLowScore() {
		return myLowestRating;
	}
	
	public int getHighScore() {
		return myHighestRating;
	}
}
