import java.util.List;


public class Author extends User {

	public List myManuscripts;
	public Manuscript myManuscript;
	
	public Author(String theName, String theEmail) {
		super(theName, theEmail);
	}

	private void submitManuscript(File theManuscript) {
		myManuscript = theManuscript;
		myManuscripts[] = new Manuscript(myManuscript);
		
	}
	
	
	public void deleteManuscript(File theManuscript) { //A very brief outline 
		boolean isFound = false;
		myManuscript = theManuscript;
		for (int index = 0; !found && (index < myManuscripts); index++) {
			if (myManuscript.equals(myManuscripts[index]) {
				found  = true;
			}
		}
		if (found = true) {
			myManuscripts.remove(index);
		}
	}

	public void editManuscript() {
		
	}

	public String toString() {
		return "Im a Author";
	}

}
