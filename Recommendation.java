import java.io.File;
import java.io.Serializable;

public class Recommendation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7409304984185013329L;
	public File myRecommendationForm;
	
	public Recommendation(File theRecommendationForm) {
		myRecommendationForm = theRecommendationForm;
	}
	
	public String toString() {
		return myRecommendationForm.getName();
	}

}