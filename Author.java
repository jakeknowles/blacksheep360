<<<<<<< HEAD

=======
>>>>>>> master
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

/**
 * This class represents the role of an author in the system.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class Author implements Serializable {

	
	private static final long serialVersionUID = -5568459789990717495L;
<<<<<<< HEAD
	
	/**
	 * A list of manuscripts written by the author.
	 */
	public List<Manuscript> myManuscripts;
	
	/**
	 * The name of the author.
	 */
	public String myName;
	
	/**
=======
	
	/**
	 * A list of manuscripts written by the author.
	 */
	public List<Manuscript> myManuscripts;
	
	/**
	 * The name of the author.
	 */
	public String myName;
	
	/**
>>>>>>> master
	 * Constructor
	 *  
	 * @version 5/8/2016
	 * 
	 * @param theName the name of the author
	 */
	public Author(String theName) {
		myName = theName;
		myManuscripts = new ArrayList<Manuscript>();
	}

	/**
	 * submits a manuscript by creating a manuscript from a file and adding it
	 * to the authors list of manuscripts.
	 * 
	 * @version 5/8/2016
	 */
<<<<<<< HEAD
	public Manuscript submitManuscript(File theManuscript, Date theDeadline, String theTitle) {
		Date now = new Date(System.currentTimeMillis());
		if (now.before(theDeadline)) {
			Manuscript submitted = new Manuscript(theManuscript, myName, theTitle);
=======
	public Manuscript submitManuscript(File theManuscript, Date theDeadline) {
		Date now = new Date(System.currentTimeMillis());
		if (now.before(theDeadline)) {
			Manuscript submitted = new Manuscript(theManuscript, myName);
>>>>>>> master
			myManuscripts.add(submitted);
			return submitted;
		} else { 
			return null;
		}
	}
	
	/**
	 * Removes or unsubmits a manuscript that was submitted.
	 * 
	 * @version 5/8/2016
	 */
	public void deleteManuscript(Manuscript theManuscript) {
		int index = myManuscripts.indexOf(theManuscript);
		if (index > -1) {
			myManuscripts.remove(index);
		}
	}

	/**
	 * Changes a submitted manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public void editManuscript(Manuscript theManuscript, File theNewFile) {
		theManuscript.myManuscript = theNewFile;
	}
}
