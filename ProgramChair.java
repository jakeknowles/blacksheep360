import java.util.Collection;
import java.util.HashMap;
import java.io.Serializable;

public class ProgramChair implements Serializable{

	public ProgramChair() {
	}

	public String viewManuscripts(HashMap<String, Manuscript> theManuscripts) {
		StringBuilder str = new StringBuilder();
		for (String s : theManuscripts.keySet()) {
			str.append(theManuscripts.get(s) + "\n");
		}
		return str.toString();
	}
	
	public void submitDecision(Manuscript theManuscript, boolean isAccepted) {
		theManuscript.myApproval = isAccepted;
	}
	
	public boolean assignManuscripts(User theSubProgramChair, Manuscript theManuscript) {
		boolean result = false;
		if(!theSubProgramChair.isRole(User.SUBPROGRAM_CHAIR)) {
			theSubProgramChair.myRoles.mySubProgramChair = new SubProgramChair();
		}
		result = theSubProgramChair.myRoles.mySubProgramChair.addManuscript(theManuscript, theSubProgramChair.myName);
		return result;
	}
	
	public String toString() {
		return "I'm a ProgramChair";
	}
	
}

