package model; 

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * A subprogram chair's recommendation.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class Recommendation implements Serializable {
	

	private static final long serialVersionUID = -7409304984185013329L;
	/**
	 * The recommendation form.
	 */
	private File myRecommendationForm;
	
	/**
	 * A short statement of recommendation.
	 */
	private String myRecommendationStatement;
	
	/**
	 * A stored recommendation file.
	 */
	private File storedRecommendationForm;
	
	/**
	 * @version 5/8/2016
	 * @throws IOException 
	 */
	public Recommendation(File theRecommendationForm, String theRecommendationStatement) throws IOException {
		myRecommendationForm = theRecommendationForm;
		myRecommendationStatement = theRecommendationStatement;
		storedRecommendationForm = Store();
	}
	
	/**
	 * Gets the recommendation form of this recommendation.
	 * 
	 * @return the myRecommendationForm
	 */
	public File getMyRecommendationForm() {
		return myRecommendationForm;
	}


	/**
	 * @version 5/31/2016
	 */
	public String getStatement(){
		return myRecommendationStatement;
	}
	
	/**
	 * Stores a copy of a submitted recommendation
	 * @version 6/1/2016
	 */
	public File Store() throws IOException {
		String pathName = "./StoredFiles/" + myRecommendationForm.getName();
		File stored = new File(pathName);
		Files.copy(myRecommendationForm.toPath(), stored.toPath(), StandardCopyOption.REPLACE_EXISTING);
		return stored;
	}
}