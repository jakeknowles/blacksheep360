

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for User.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class UserTest {

	/**
	 * Tests the isRole method of user.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testIsRole() {
		User test = new User("Tester");
		assertFalse(test.isRole("Author"));
		assertFalse(test.isRole("Reviewer"));
		assertFalse(test.isRole("SubProgram Chair"));
		assertFalse(test.isRole("Program Chair"));
		
		test.myRoles.myAuthor = new Author(test.myName);
		test.myRoles.myReviewer = new Reviewer(test.myName);
		test.myRoles.mySubProgramChair = new SubProgramChair();
		test.myRoles.myProgramChair = new ProgramChair();
		
		assertTrue(test.isRole("Author"));
		assertTrue(test.isRole("Reviewer"));
		assertTrue(test.isRole("SubProgram Chair"));
		assertTrue(test.isRole("Program Chair"));
		
	}

}
