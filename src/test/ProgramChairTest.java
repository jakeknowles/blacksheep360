package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import model.Manuscript;
import model.ManuscriptAcceptanceStatus;
import model.ProgramChair;
import model.SubProgramChair;
import model.User;

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
	 * A manuscript file.
	 */
	public File manuscriptFile;
	
	/**
	 * a manuscript.
	 */
	public Manuscript aManuscript;
	
	/**
	 * A manuscript authored by a subprogram chair.
	 */
	public Manuscript subprogChairsManuscript;
	
	/**
	 * A program chair.
	 */
	public User progChair;
	
	/**
	 * A subprogram chair.
	 */
	public User subprogChair;
	
	
	@Before
	public void setUp() {
		manuscriptFile = new File("./TestDataFiles/TestManuscriptFile");
		try {
			aManuscript = new Manuscript(manuscriptFile, "Tester", "test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		progChair = new User("Program Chairman");
		progChair.assignProgramChair(new ProgramChair());
		subprogChair = new User("Sub Chairman");
		subprogChair.assignSubProgramChair(new SubProgramChair());
		try {
			subprogChairsManuscript = new Manuscript(manuscriptFile, "Sub Chairman", "test");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tests submit decision.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitDecision() {
		progChair.getProgramChair().submitDecision(aManuscript, ManuscriptAcceptanceStatus.ACCEPTED);
		assertTrue(aManuscript.getMyApproval().equals(ManuscriptAcceptanceStatus.ACCEPTED));
	}
	
	/**
	 * tests the assign manuscript method.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAssignManuscript() {
		assertTrue(progChair.getProgramChair().assignManuscripts(subprogChair, aManuscript));
		assertEquals(subprogChair.getSubProgramChair().getManuscripts().size(), 1);
	}
	
	/**
	 * tests assign manuscript when attempting to assign a subprogram chair
	 * to a manuscript that he/she wrote.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAssignManuscriptToAuthor() {
		assertFalse(progChair.getProgramChair().assignManuscripts(subprogChair, subprogChairsManuscript));
		assertEquals(subprogChair.getSubProgramChair().getManuscripts().size(), 0);	
	}

}
