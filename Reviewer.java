import java.util.List;


public class Reviewer extends User {

	public List myManuscripts;
	
	public Reviewer(String theName, String theEmail) {
		super(theName, theEmail);
	}

	public void submitReview() {
		
	}
	
	public void editReview() {
		
	}
	
	public String toString() {
		return "Im a Reviewer";
	}

}