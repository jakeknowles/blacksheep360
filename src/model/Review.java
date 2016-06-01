package model; 

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * A review.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class Review implements Serializable {
	
	
	private static final long serialVersionUID = -2086403833237855553L;
	/**
	 * The review form.
	 */
	private File myReviewForm;
	
	/**
	 * The score given
	 */
	private int myRating;
	
	/**
	 * The title of the reviewed manuscript
	 */
	private String myReviewedManuscriptTitle;
	
	/**
	 * A review file stored by the system.
	 */
	private File storedForm;
	
	/**
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 * @throws IOException 
	 */
	public Review(File theReviewForm, int theRating, String theReviewedManuscriptTitle) throws IOException {
		myReviewForm = theReviewForm;
		myRating = theRating;
		myReviewedManuscriptTitle = theReviewedManuscriptTitle;
		storedForm = Store();
	}
	
	/**
	 * @version 5/26/2016
	 * @param theOther the Review to be copied.
	 */
	public Review(Review theOther) {
		myReviewForm = theOther.myReviewForm;
		myRating = theOther.myRating;
		myReviewedManuscriptTitle = theOther.myReviewedManuscriptTitle;
		storedForm = theOther.storedForm;
	}
	
	/**
	 * @version 5/31/2016
	 */
	public String getMyReviewedManuscriptTitle() {
		return myReviewedManuscriptTitle;
	}

	/**
	 * @version 5/31/2016
	 */
	public int getMyRating() {
		return myRating;
	}
	
	/**
	 * Stores a copy of a submitted review
	 * @version 6/1/2016
	 */
	public File Store() throws IOException {
		String pathName = "./StoredFiles/" + myReviewForm.getName();
		File stored = new File(pathName);
		Files.copy(myReviewForm.toPath(), stored.toPath(), StandardCopyOption.REPLACE_EXISTING);
		return stored;
	}

}