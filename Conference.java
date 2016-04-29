import java.util.List;

public class Conference {

	public ProgramChair myProgramChair;
	public String myConfName;
	public List myManuscripts;
	
	public Conference(ProgramChair thePC, String theConfName) {
		myProgramChair = thePC;
		myConfName = theConfName;
	}
	
	public String toString() {
		return "I'm a Conference";
	}
	
}
