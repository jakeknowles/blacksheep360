import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class ProgramChair {

	public ProgramChair(String theName) {
	}

	private String viewManuscripts(List theManuscripts) {
		
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
		//FIX ME
//		theManuscript.acceptanceStatus = isAccepted;
		
	}
	
	private void assignManuscripts() {
		
	}
	
	public String toString() {
		return "I'm a ProgramChair";
	}
	
}

