package model; 

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
	
	/**
	 * A list of manuscripts written by the author.
	 */
	private List<Manuscript> myManuscripts;
	
	/**
	 * The name of the author.
	 */
	private String myName;
	
	/**
	 * @version 5/8/2016
	 * 
	 * @param theName the name of the author
	 */
	public Author(String theName) {
		myName = theName;
		myManuscripts = new ArrayList<Manuscript>();
	}
	
	/**
	 * @version 5/25/2016
	 * 
	 * @param theName The name of the author
	 * @param theManuscripts The manuscripts that the author has submitted.
	 */
	public Author(String theName, List<Manuscript> theManuscripts) {
		myName = theName;
		myManuscripts = new ArrayList<Manuscript>();
		for(Manuscript m : theManuscripts) {
			myManuscripts.add(new Manuscript(m));
		}
	}

	/**
	 * submits a manuscript by creating a manuscript from a file and adding it
	 * to the authors list of manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public Manuscript submitManuscript(File theManuscript, Date theDeadline, String theTitle) {
		Date now = new Date(System.currentTimeMillis());
		if (now.before(theDeadline)) {
			Manuscript submitted = new Manuscript(theManuscript, myName, theTitle);
			getMyManuscripts().add(submitted);
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
		int index = getMyManuscripts().indexOf(theManuscript);
		if (index > -1) {
			getMyManuscripts().remove(index);
		}
	}

	/**
	 * Changes a file of a submitted manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public void editManuscriptFile(Manuscript theManuscript, File theNewFile) {
		theManuscript.setMyManuscript(theNewFile);
	}
	
	/**
	 * Changes the title of a submitted manuscript.
	 * 
	 * @version 5/26/2016
	 * 
	 * @param theIndex 
	 * @param theNewTitle
	 */
	public void editManuscriptTitle(Manuscript theManuscript, String theNewTitle) {
		theManuscript.setMyTitle(theNewTitle);
	}

	/**
	 * Gets a list of this author's manuscripts.
	 * 
	 * @return the list of this author's manuscripts
	 */
	public List<Manuscript> getMyManuscripts() {
		return myManuscripts;
	}
	
	
}
