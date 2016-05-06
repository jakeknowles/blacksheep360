import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 4/29/2016
 */
public class Conference implements java.io.Serializable {

	public ProgramChair myProgramChair;
	public String myConfName;
	public HashMap<String, Manuscript> myManuscripts;
	
	public Conference(ProgramChair thePC, String theConfName) {
		myProgramChair = thePC;
		myConfName = theConfName;
		myManuscripts = new HashMap<String, Manuscript>();
	}
	
	public void addManuscript(String theUser, File theManuscript) {
		Manuscript m = new Manuscript(theManuscript, theUser);
		myManuscripts.put(theUser, m);
	}
	
	//NEED TO FIX THIS CAUSE IT REMOVES ALL reviews
	public void removeManuscript(String theUser) {
		myManuscripts.remove(theUser);
	}

	
	public String toString() {
		return "I'm a Conference";
	}
	
}
