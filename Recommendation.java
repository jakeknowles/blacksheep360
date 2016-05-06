import java.io.File;
import java.io.Serializable;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 4/29/2016
 */
public class Recommendation implements java.io.Serializable {
	
	public File myRecommendationForm;
	
	public Recommendation(File theRecommendationForm) {
		myRecommendationForm = theRecommendationForm;
	}
	
	public String toString() {
		return "I'm a recommendation";
	}

}