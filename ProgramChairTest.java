<<<<<<< HEAD


=======
>>>>>>> master
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * Test cases for the Program Chair class.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class ProgramChairTest {

	/**
	 * tests submit decision.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitDecision() {
		File manFile = new File("./AlexTest.txt");
<<<<<<< HEAD
		Manuscript aManuscript = new Manuscript(manFile, "Tester", "test");
=======
		Manuscript aManuscript = new Manuscript(manFile, "Tester");
>>>>>>> master
		User progChair = new User("Program Chairman");
		progChair.myRoles.myProgramChair = new ProgramChair();
		progChair.myRoles.myProgramChair.submitDecision(aManuscript, true);
		assertTrue(aManuscript.myApproval);
	}
	
	/**
	 * tests the assign manuscript method.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAssignManuscript() {
		User progChair = new User("Program Chairman");
		progChair.myRoles.myProgramChair = new ProgramChair();
		User subChair = new User("Sub Chairman");
		File manFile = new File("./AlexTest.txt");
<<<<<<< HEAD
		Manuscript manu = new Manuscript(manFile, "Tester", "test");
=======
		Manuscript manu = new Manuscript(manFile, "Tester");
>>>>>>> master
		assertTrue(progChair.myRoles.myProgramChair.assignManuscripts(subChair, manu));
		assertEquals(subChair.myRoles.mySubProgramChair.myManuscripts.size(), 1);
	}
	
	/**
	 * tests assign manuscript when attempting to assign a subprogram chair
	 * to a manuscript that he/she wrote.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAssignManuscriptToAuthor() {
		User progChair = new User("Program Chairman");
		progChair.myRoles.myProgramChair = new ProgramChair();
		User subChair = new User("Sub Chairman");
		File manFile = new File("./AlexTest.txt");
<<<<<<< HEAD
		Manuscript manu = new Manuscript(manFile, "Sub Chairman", "test");
=======
		Manuscript manu = new Manuscript(manFile, "Sub Chairman");
>>>>>>> master
		assertFalse(progChair.myRoles.myProgramChair.assignManuscripts(subChair, manu));
		assertEquals(subChair.myRoles.mySubProgramChair.myManuscripts.size(), 0);	
	}

}
