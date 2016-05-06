
import java.io.Serializable;
 
/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 4/29/2016
 */
public class User implements java.io.Serializable {

	public String myName;
	public Roles myRoles;
	
	public User(String theName) {
		myName = theName;
		myRoles = new Roles();
	}

	public boolean isRole(String theRole) {
		if (theRole.equals("Author")) {
			return myRoles.myAuthor != null;
		} else if (theRole.equals("Reviewer")) {
			return myRoles.myReviewer != null;
		} else if (theRole.equals("SubProgramChair")) {
			return myRoles.mySubProgramChair != null;
		} else if (theRole.equals("ProgramChair")) {
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
