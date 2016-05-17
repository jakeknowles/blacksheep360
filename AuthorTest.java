

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;

import org.junit.Test;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class AuthorTest {

	/**
	 * tests the authors ability to submit a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitManuscript() {
		User testUser = new User("Arthur");
		testUser.myRoles.myAuthor = new Author(testUser.myName);
		File manuscriptFile = new File("./AlexTest.txt");
		Date deadline = new Date(System.currentTimeMillis() + 3600000);
		testUser.myRoles.myAuthor.submitManuscript(manuscriptFile, deadline, "AlexTest.txt");
		assertTrue(testUser.myRoles.myAuthor.myManuscripts.get(0).myTitle.equals(manuscriptFile.getName()));
	}
	
	/**
	 * tests the authors ability to submit a manuscript when the deadline is passed.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitManuscriptAfterDeadline() {
		User testUser = new User("Arthur");
		testUser.myRoles.myAuthor = new Author(testUser.myName);
		File manuscriptFile = new File("./AlexTest.txt");
		Date deadline = new Date(System.currentTimeMillis() - 3600000);
		assertNull(testUser.myRoles.myAuthor.submitManuscript(manuscriptFile, deadline, "test"));
		//assertTrue(testUser.myRoles.myAuthor.myManuscripts.get(0).myTitle.equals(manuscriptFile.getName()));
	}
	
	/**
	 * Tests that an author may remove or unsubmit a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testDeleteManuscript() {
		User testUser = new User("Arthur");
		testUser.myRoles.myAuthor = new Author(testUser.myName);
		Date deadline = new Date(System.currentTimeMillis() + 3600000);
		File manuscriptFile = new File("./AlexTest.txt");
		Manuscript manuscriptObj = testUser.myRoles.myAuthor.submitManuscript(manuscriptFile, deadline, "test");
		testUser.myRoles.myAuthor.deleteManuscript(manuscriptObj);
		assertEquals(testUser.myRoles.myAuthor.myManuscripts.size(), 0);
	}
	
	/**
	 * Tests that an author may edit a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testEditManuscript() {
		User testUser = new User("Arthur");
		testUser.myRoles.myAuthor = new Author(testUser.myName);
		File manuscriptFile = new File("./AlexTest.txt");
		Date deadline = new Date(System.currentTimeMillis() + 3600000);
		Manuscript manuscriptObj = testUser.myRoles.myAuthor.submitManuscript(manuscriptFile, deadline, "test");
		File otherFile = new File("./AntiSocialNetwork.doc");
		testUser.myRoles.myAuthor.editManuscript(manuscriptObj, otherFile);
		assertEquals(testUser.myRoles.myAuthor.myManuscripts.get(0).myManuscript, otherFile);
	}

}
