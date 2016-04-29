import java.util.List;


public class SubProgramChair extends User {

	public List myManuscripts;
	
	public SubProgramChair(String theName, String theEmail) {
		super(theName, theEmail);
	}

	private void assignReviewer() {
		
	}
	
	private void submitRecommendation() {
		
	}
	
	private Recommendation viewRecommendation() {
		return new Recommendation(null);
	}
	
	public void editReview() {
		
	}
	
	public String toString() {
		return "Im a SubPC";
	}

}