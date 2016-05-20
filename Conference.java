
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
	
	/**
	 * Constructor
	 * 
	 * @version 5/8/2016
	 */
	public Conference(User thePC, String theConfName, Date theDeadline) {
		myProgramChair = thePC;
		thePC.getMyRoles().myProgramChair = new ProgramChair();
		setMyConfName(theConfName);
		setMyManuscripts(new HashMap<String, Manuscript>());
		setMyManuscriptDeadline(theDeadline);
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

	public void setMyConfName(String myConfName) {
		this.myConfName = myConfName;
	}

	public void setMyManuscripts(HashMap<String, Manuscript> myManuscripts) {
		this.myManuscripts = myManuscripts;
	}

	public void setMyManuscriptDeadline(Date myManuscriptDeadline) {
		this.myManuscriptDeadline = myManuscriptDeadline;
	}

	/**
	 * Removes a manuscript from the submitted manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public void removeManuscript(String theTitle) {
		getMyManuscripts().remove(theTitle);
	}
}
