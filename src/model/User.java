package model; 

import java.io.Serializable;
 
/**
 * A User of the system.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class User implements Serializable {

	private static final long serialVersionUID = 5751299948810425611L;
	
	public static final String SUBPROGRAM_CHAIR = "SubProgram Chair";
	public static final String AUTHOR = "Author";
	public static final String REVIEWER = "Reviewer";
	public static final String PROGRAM_CHAIR = "Program Chair";

	/**
	 * The name of the user.
	 */
	private String myName;
	
	/**
	 * A collection of the user's roles.
	 */
	private Roles myRoles;
	
	/**
	 * @version 5/8/2016
	 */
	public User(String theName) {
		myName = theName;
		myRoles = new Roles();
	}
	
	/**
	 * Constructor for creating a user with already established roles.
	 * 
	 * @param theName
	 * @param theRoles
	 */
	public User(String theName, Roles theRoles) {
		myName = theName;
		myRoles = theRoles;
	}

	/**
	 * @version 5/30/2016
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * @version 5/30/2016
	 */
	public Author getAuthor() {
		return myRoles.getAuthor();
	}
	
	/**
	 * @version 5/30/2016
	 */
	public Reviewer getReviewer() {
		return myRoles.getReviewer();
	}
	
	/**
	 * @version 5/30/2016
	 */
	public SubProgramChair getSubProgramChair() {
		return myRoles.getSubProgramChair();
	}

	/**
	 * @version 5/30/2016
	 */
	public ProgramChair getProgramChair() {
		return myRoles.getProgramChair();
	}
	/**
	 * Checks to see if the user has a given role.
	 * 
	 * @version 5/8/2016
	 */
	public boolean isRole(String theRole) {
		if (theRole.equals(AUTHOR)) {
			return getAuthor() != null;
		} else if (theRole.equals(REVIEWER)) {
			return getReviewer() != null;
		} else if (theRole.equals(SUBPROGRAM_CHAIR)) {
			return getSubProgramChair() != null;
		} else if (theRole.equals(PROGRAM_CHAIR)) {
			return getProgramChair() != null;
		}
		return false;
	}
	
	/**
	 * Assigns the role of author to the user.
	 * If the user is already an Author then it does nothing.
	 * 
	 * @version 5/31/2016
	 */
	public void assignAuthor(Author theAuthor) {
		if (myRoles.getAuthor() == null) {
			myRoles.setAuthor(theAuthor);
		}
	} 
	
	/**
	 * Assigns the role of reviewer to the user.
	 * If the user is already a reviewer then it does nothing.
	 * 
	 * @version 5/31/2016
	 */
	public void assignReviewer(Reviewer theReviewer) {
		if (myRoles.getReviewer() == null) {
			myRoles.setReviewer(theReviewer);
		}
	}
	
	/**
	 * Assigns the role of Subprogram Chair to the user.
	 * If the user is already a subprogram chair then it does nothing.
	 * 
	 * @version 5/31/2016
	 */
	public void assignSubProgramChair(SubProgramChair theSubProgramChair) {
		if (myRoles.getSubProgramChair() == null) {
			myRoles.setSubProgramChair(theSubProgramChair);
		}
	}
	
	/**
	 * Assigns the role of program chair to the user.
	 * If the user is already a program chair then it does nothing.
	 * 
	 * @version 5/31/2016
	 */
	public void assignProgramChair(ProgramChair theProgramChair) {
		if (myRoles.getProgramChair() == null) {
			myRoles.setProgramChair(theProgramChair);
		}
	}
}
