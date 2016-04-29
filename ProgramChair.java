import java.util.List;
import java.util.Scanner;

public class ProgramChair extends User {

	public ProgramChair(String theName, String theEmail) {
		super(theName, theEmail);
	}

	private Manuscript viewManuscripts(List theManuscripts) {
		
		return theManuscripts.toString();  //temp
	}
	
	private void submitDecision(Manuscript theManuscript, boolean isAccepted) {
//		Scanner s = new Scanner(System.in);
//		System.out.println("Please indicate your decision for this manuscript.\nEnter A for accept or R for reject.");
//		String choice = s.next();
//		if (s.equals("A")){
//			theManuscript.acceptanceStatus = True;
//		} else {
//			theManuscript.acceptanceStatus = False;
//		}
		theManuscript.acceptanceStatus = isAccepted;
		
	}
	
	private void assignManuscripts() {
		
	}
	
	public String toString() {
		return "I'm a ProgramChair";
	}
	
}
