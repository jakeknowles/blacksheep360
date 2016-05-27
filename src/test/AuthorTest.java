package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Manuscript;
import model.User;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class AuthorTest {
	
	private Date futureDeadline;
	
	private Date pastDeadline;
	
	private User noManuscriptUser;
	
	private User oneManuscriptUser;
	
	private File manuscriptFile;
	
	private Manuscript testManuscript;
	
	private ArrayList<Manuscript> manList;
	
	@Before
	public void setUp() {
		long someTimeInterval = 3600000;
		futureDeadline = new Date(System.currentTimeMillis() + someTimeInterval);
		pastDeadline = new Date(System.currentTimeMillis() - someTimeInterval);
		noManuscriptUser = new User("Arthur");
		oneManuscriptUser = new User("Arthur");
		try {
			manuscriptFile = new File("./TestDataFiles/AntiSocialNetwork.doc");
		} catch (NullPointerException e) {
			System.err.println("PATHNAME ERROR");
		}
		testManuscript = new Manuscript(manuscriptFile, noManuscriptUser.getMyName(), "Anti Social Network");
		noManuscriptUser.getMyRoles().myAuthor = new Author(noManuscriptUser.getMyName());
		manList = new ArrayList<Manuscript>();
		manList.add(testManuscript);
		oneManuscriptUser.getMyRoles().myAuthor = new Author(oneManuscriptUser.getMyName(), manList);
		
	}

	/**
	 * tests the authors ability to submit a manuscript.
	 * 
	 * @version 5/26/2016
	 */
	@Test
	public void testSubmitManuscript() {
		noManuscriptUser.getMyRoles().myAuthor.submitManuscript(manuscriptFile, futureDeadline, "Anti Social Network");
		assertTrue(noManuscriptUser.getMyRoles().myAuthor.getMyManuscripts().get(0).getMyTitle().equals(testManuscript.getMyTitle()));
	}
	
	/**
	 * tests the authors ability to submit a manuscript when the deadline is passed.
	 * 
	 * @version 5/26/2016
	 */
	@Test
	public void testSubmitManuscriptAfterDeadline() {
		assertNull(noManuscriptUser.getMyRoles().myAuthor.submitManuscript(manuscriptFile, pastDeadline, "test"));
	}
	
	/**
	 * Tests that an author may remove or unsubmit a manuscript.
	 * 
	 * @version 5/26/2016
	 */
	@Test
	public void testDeleteManuscript() {
		Manuscript manuscriptObj = oneManuscriptUser.getMyRoles().myAuthor.getMyManuscripts().get(0);
		oneManuscriptUser.getMyRoles().myAuthor.deleteManuscript(manuscriptObj);
		assertEquals(oneManuscriptUser.getMyRoles().myAuthor.getMyManuscripts().size(), 0);
	}
	
	/**
	 * Tests that an author may edit a manuscript.
	 * 
	 * @version 5/26/2016
	 */
	@Test
	public void testEditManuscriptFile() {
		Manuscript man = oneManuscriptUser.getMyRoles().myAuthor.getMyManuscripts().get(0);
		File otherFile = null;
		try {
			otherFile = new File("./TestDataFiles/AlexTest.txt");
		} catch (NullPointerException e) {
			System.err.println("PATHNAME ERROR");
		}
		oneManuscriptUser.getMyRoles().myAuthor.editManuscriptFile(man, otherFile);
		assertEquals(oneManuscriptUser.getMyRoles().myAuthor.getMyManuscripts().get(0).getMyManuscript(), otherFile);
	}
	
	@Test
	public void testEditManuscriptTitle() {
		Manuscript man = oneManuscriptUser.getMyRoles().myAuthor.getMyManuscripts().get(0);
		String newTitle = "New Title";
		oneManuscriptUser.getMyRoles().myAuthor.editManuscriptTitle(man, newTitle);
		assertEquals(oneManuscriptUser.getMyRoles().myAuthor.getMyManuscripts().get(0).getMyTitle(), newTitle);
	}

}
