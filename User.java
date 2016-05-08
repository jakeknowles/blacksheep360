
import java.io.Serializable;
 
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5751299948810425611L;
	public static final String SUBPROGRAM_CHAIR = "SubProgram Chair";
	public static final String AUTHOR = "Author";
	public static final String REVIEWER = "Reviewer";
	public static final String PROGRAM_CHAIR = "Program Chair";

	public String myName;
	public Roles myRoles;
	
	public User(String theName) {
		myName = theName;
		myRoles = new Roles();
	}

	public boolean isRole(String theRole) {
		if (theRole.equals(AUTHOR)) {
			return myRoles.myAuthor != null;
		} else if (theRole.equals(REVIEWER)) {
			return myRoles.myReviewer != null;
		} else if (theRole.equals(SUBPROGRAM_CHAIR)) {
			return myRoles.mySubProgramChair != null;
		} else if (theRole.equals(PROGRAM_CHAIR)) {
			return myRoles.myProgramChair != null;
		}
		return false;
	}
	
	public String toString() {
		return "I'm a User";
	}
	
	class Roles {
		public Author myAuthor;
		public Reviewer myReviewer;
		public SubProgramChair mySubProgramChair;
		public ProgramChair myProgramChair;
		
		public Roles() {
			myAuthor = null;
			myReviewer = null;
			mySubProgramChair = null;
			myProgramChair = null;
		}
	}
	
}
