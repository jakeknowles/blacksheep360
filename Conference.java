<<<<<<< HEAD

import java.util.Date;
import java.util.HashMap;
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

=======
import java.util.Date;
import java.util.HashMap;
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

>>>>>>> master
public class Conference implements Serializable {

	private static final long serialVersionUID = 5606112605169805993L;
	/**
	 * the program chair of the conference.
	 */
	public User myProgramChair;
	
	/**
	 * The name of the conference.
	 */
	public String myConfName;
	
	/**
	 * A map of the submitted manuscript. With the title of the
	 * manuscripts as the key.
	 */
	public HashMap<String, Manuscript> myManuscripts;
	
	/**
	 * The deadline by which an author must submit a paper.
	 */
	public Date myManuscriptDeadline;
	
	/**
	 * Constructor
	 * 
	 * @version 5/8/2016
	 */
	public Conference(User thePC, String theConfName, Date theDeadline) {
		myProgramChair = thePC;
		thePC.myRoles.myProgramChair = new ProgramChair();
		myConfName = theConfName;
		myManuscripts = new HashMap<String, Manuscript>();
		myManuscriptDeadline = theDeadline;
	}
	
	/**
	 * Adds a Manuscript to the map of submitted manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public void addManuscript(Manuscript theManuscript) {
		myManuscripts.put(theManuscript.myTitle, theManuscript);
	}
	
	/**
	 * Removes a manuscript from the submitted manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public void removeManuscript(String theTitle) {
		myManuscripts.remove(theTitle);
	}
}
