
import java.util.Collection;
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
	 * Returns all submitted manuscripts.
	 * 
	 * @version 5/22/2016
	 */
	public Collection<Manuscript> viewManuscripts(HashMap<String, Manuscript> theManuscripts) { //Alexandria, 5/22/16 - I changed this for the sake of keeping the UI separate. This was basically a toString.
		return theManuscripts.values();
	}
	
	/**
	 * Gives decision on a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public void submitDecision(Manuscript theManuscript, ManuscriptAcceptanceStatus myDecision) {
		theManuscript.setMyApproval(myDecision);
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

