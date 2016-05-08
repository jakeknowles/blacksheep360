import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Author implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5568459789990717495L;
	
	public List<Manuscript> myManuscripts;
	public String myName;
	
	public Author(String theName) {
		myName = theName;
		myManuscripts = new ArrayList<Manuscript>();
	}

	public Manuscript submitManuscript(File theManuscript) {
		Manuscript submitted = new Manuscript(theManuscript, myName);
		myManuscripts.add(submitted);
		return submitted;
	}
	
	
	public void deleteManuscript(Manuscript theManuscript) {
		int index = myManuscripts.indexOf(theManuscript);
		if (index > -1) {
			myManuscripts.remove(index);
		}
	}

	public void editManuscript(Manuscript theManuscript, File theNewFile) {
		theManuscript.myManuscript = theNewFile;
	}
}
