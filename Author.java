import java.util.List;


public class Author extends User {

	public List myManuscripts;
	
	public Author(String theName, String theEmail) {
		super(theName, theEmail);
	}

	private void submitManuscript() {
		
	}
	
	
	public void deleteManuscript() {
			
	}

	public void editManuscript() {
		
	}

	public String toString() {
		return "Im a Author";
	}

}