import java.util.Date;
import java.util.HashMap;
import java.io.Serializable;

public class Conference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5606112605169805993L;
	public User myProgramChair;
	public String myConfName;
	public HashMap<String, Manuscript> myManuscripts;
	public Date myManuscriptDeadline;
	
	public Conference(User thePC, String theConfName) {
		myProgramChair = thePC;
		thePC.myRoles.myProgramChair = new ProgramChair();
		myConfName = theConfName;
		myManuscripts = new HashMap<String, Manuscript>();
	}
	
	public void addManuscript(Manuscript theManuscript) {
		myManuscripts.put(theManuscript.myTitle, theManuscript);
	}
	
	
	public void removeManuscript(String theTitle) {
		myManuscripts.remove(theTitle);
	}
}
