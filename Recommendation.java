
import java.io.File;
import java.io.Serializable;
<<<<<<< HEAD

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

=======

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

>>>>>>> master
public class Recommendation implements Serializable {
	

	private static final long serialVersionUID = -7409304984185013329L;
	/**
	 * The recommendation form.
	 */
	public File myRecommendationForm;
	
	/**
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
	public Recommendation(File theRecommendationForm) {
		myRecommendationForm = theRecommendationForm;
	}
	
	public String toString() {
		return myRecommendationForm.getName();
	}

}