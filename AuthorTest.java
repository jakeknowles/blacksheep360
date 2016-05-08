import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * 
 */

/**
 * 
 *
 */
public class AuthorTest {

	@Test
	public void testSubmitManuscript() {
		User testUser = new User("Arthur");
		testUser.myRoles.myAuthor = new Author(testUser.myName);
		File manuscriptFile = new File("./AlexTest.txt");
		testUser.myRoles.myAuthor.submitManuscript(manuscriptFile);
		assertTrue(testUser.myRoles.myAuthor.myManuscripts.get(0).myTitle.equals(manuscriptFile.getName()));
	}
	
	@Test
	public void testDeleteManuscript() {
		User testUser = new User("Arthur");
		testUser.myRoles.myAuthor = new Author(testUser.myName);
		File manuscriptFile = new File("./AlexTest.txt");
		Manuscript manuscriptObj = testUser.myRoles.myAuthor.submitManuscript(manuscriptFile);
		testUser.myRoles.myAuthor.deleteManuscript(manuscriptObj);
		assertEquals(testUser.myRoles.myAuthor.myManuscripts.size(), 0);
	}
	
	@Test
	public void testEditManuscript() {
		User testUser = new User("Arthur");
		testUser.myRoles.myAuthor = new Author(testUser.myName);
		File manuscriptFile = new File("./AlexTest.txt");
		Manuscript manuscriptObj = testUser.myRoles.myAuthor.submitManuscript(manuscriptFile);
		File otherFile = new File("./AntiSocialNetwork.doc");
		testUser.myRoles.myAuthor.editManuscript(manuscriptObj, otherFile);
		assertEquals(testUser.myRoles.myAuthor.myManuscripts.get(0).myManuscript, otherFile);
	}

}
