import java.io.File;
import java.io.Serializable;

public class Recommendation implements Serializable {
	
	public File myRecommendationForm;
	
	public Recommendation(File theRecommendationForm) {
		myRecommendationForm = theRecommendationForm;
	}
	
	public String toString() {
		return myRecommendationForm.getName();
	}

}