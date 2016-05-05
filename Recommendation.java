import java.io.File;
import java.io.Serializable;

public class Recommendation implements java.io.Serializable {
	
	public File myRecommendationForm;
	
	public Recommendation(File theRecommendationForm) {
		myRecommendationForm = theRecommendationForm;
	}
	
	public String toString() {
		return "I'm a recommendation";
	}

}