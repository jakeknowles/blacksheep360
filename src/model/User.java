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
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
	public User(String theName) {
		setMyName(theName);
		setMyRoles(new Roles());
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Roles getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(Roles myRoles) {
		this.myRoles = myRoles;
	}

	/**
	 * Checks to see if the user has a given role.
	 * 
	 * @version 5/8/2016
	 */
	public boolean isRole(String theRole) {
		if (theRole.equals(AUTHOR)) {
			return getMyRoles().myAuthor != null;
		} else if (theRole.equals(REVIEWER)) {
			return getMyRoles().myReviewer != null;
		} else if (theRole.equals(SUBPROGRAM_CHAIR)) {
			return getMyRoles().mySubProgramChair != null;
		} else if (theRole.equals(PROGRAM_CHAIR)) {
			return getMyRoles().myProgramChair != null;
		}
		return false;
	}
	
	public String toString() {
		return "I'm a User";
	}
	
	/**
	 * A class that stores the user's roles.
	 * 
	 * @version 5/8/2016
	 */
	public class Roles implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -8401286720437180771L;

		/**
		 * The user's author role.
		 */
		public Author myAuthor;
		
		/**
		 * The user's reviewer role.
		 */
		public Reviewer myReviewer;
		/**
		 * The user's subprogram chair role.
		 */
		public SubProgramChair mySubProgramChair;
		/**
		 * The user's Program chair role.
		 */
		public ProgramChair myProgramChair;
		
		/**
		 * Constructor.
		 * 
		 * @version 5/8/2016
		 */
		public Roles() {
			myAuthor = null;
			myReviewer = null;
			mySubProgramChair = null;
			myProgramChair = null;
		}
	}
	
}