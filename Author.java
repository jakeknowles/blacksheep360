import java.io.File;
import java.util.List;
import java.io.Serializable;


public class Author implements java.io.Serializable {

	public List myManuscripts;
	public Manuscript myManuscript;
	
	public Author(String theName) {
	}

	private void submitManuscript(File theManuscript) {
//		myManuscript = theManuscript;
//		mFixMe= new Manuscript(myManuscript);
		
	}
	
	
	public void deleteManuscript(File theManuscript) { //A very brief outline 
		boolean isFound = false;
//		myManuscript = theManuscript;
//		for (int index = 0; !found && (index < myManuscripts); index++) {
//			if (myManuscript.equals(myManuscripts[index]) {
//				found  = true;
//			}
//		}
//		if (found = true) { //index out of bounds
//			myManuscripts.remove(index);
//		}
	}

	public void editManuscript() {
		
	}

	public String toString() {
		return "Im a Author";
	}

}
