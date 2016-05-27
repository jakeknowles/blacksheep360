package model; 

import java.io.File;
import java.io.Serializable;

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
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
	public Recommendation(File theRecommendationForm) {
		myRecommendationForm = theRecommendationForm;
	}
	
	public String toString() {
		return getMyRecommendationForm().getName();
	}

	/**
	 * Gets the recommendation form of this recommendation.
	 * 
	 * @return the myRecommendationForm
	 */
	public File getMyRecommendationForm() {
		return myRecommendationForm;
	}


}