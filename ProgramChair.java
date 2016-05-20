
import java.util.HashMap;
import java.io.Serializable;

/**
 * This class represents a ProgramChair.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class ProgramChair implements Serializable{

	private static final long serialVersionUID = 8511840844224397611L;

	/**
	 * Constructor.
	 * 
	 * @version 5/8/2016
	 */
	public ProgramChair() {
	}

	/**
	 * Views all submitted manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public String viewManuscripts(HashMap<String, Manuscript> theManuscripts) {
		StringBuilder str = new StringBuilder();
		for (String s : theManuscripts.keySet()) {
			str.append(theManuscripts.get(s) + "\n");
		}
		return str.toString();
	}
	
	/**
	 * Gives decision on a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public void submitDecision(Manuscript theManuscript, boolean isAccepted) {
		theManuscript.setMyApproval(isAccepted);
	}
	
	/**
	 * Assigns a manuscript to a subprogram chair.
	 * 
	 * @version 5/8/2016
	 */
	public boolean assignManuscripts(User theSubProgramChair, Manuscript theManuscript) {
		boolean result = false;
		if(!theSubProgramChair.isRole(User.SUBPROGRAM_CHAIR)) {
			theSubProgramChair.getMyRoles().mySubProgramChair = new SubProgramChair();
		}
		result = theSubProgramChair.getMyRoles().mySubProgramChair.addManuscript(theManuscript, theSubProgramChair.getMyName());
		return result;
	}
	
//	public String toString() {
//		return "I'm a ProgramChair";
//	}
	
}

