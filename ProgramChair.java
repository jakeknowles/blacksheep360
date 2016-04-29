
public class ProgramChair extends User {

	public ProgramChair(String theName, String theEmail) {
		super(theName, theEmail);
	}

	private Manuscript viewManuscripts() {
		
		return new Manuscript();  //temp
	}
	
	private void submitDecision() {
		
	}
	
	private void assignManuscripts() {
		
	}
	
	public String toString() {
		return "I'm a ProgramChair";
	}
	
}
