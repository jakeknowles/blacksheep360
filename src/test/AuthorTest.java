package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Manuscript;
import model.PastDeadlineException;
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
		noManuscriptUser.assignAuthor(new Author(noManuscriptUser.getMyName()));
		manList = new ArrayList<Manuscript>();
		manList.add(testManuscript);
		oneManuscriptUser.assignAuthor(new Author(oneManuscriptUser.getMyName(), manList));
		
	}

	/**
	 * tests the authors ability to submit a manuscript.
	 * 
	 * @version 5/26/2016
	 */
	@Test
	public void testSubmitManuscript() {
		try {
			noManuscriptUser.getAuthor().submitManuscript(manuscriptFile, futureDeadline, "Anti Social Network");
		} catch (PastDeadlineException e) {
			fail();
		}
		assertTrue(noManuscriptUser.getAuthor().getMyManuscripts().get(0).getMyTitle().equals(testManuscript.getMyTitle()));
	}
	
	/**
	 * tests the authors ability to submit a manuscript when the deadline is passed.
	 * 
	 * @version 5/26/2016
	 * @throws PastDeadlineException 
	 */
	@Test (expected = PastDeadlineException.class)
	public void testSubmitManuscriptAfterDeadline() throws PastDeadlineException {
		noManuscriptUser.getAuthor().submitManuscript(manuscriptFile, pastDeadline, "Anti Social Network");
		fail();
	}
	
	/**
	 * Tests that an author may remove or unsubmit a manuscript.
	 * 
	 * @version 5/26/2016
	 */
	@Test
	public void testDeleteManuscript() {
		Manuscript manuscriptObj = oneManuscriptUser.getAuthor().getMyManuscripts().get(0);
		oneManuscriptUser.getAuthor().deleteManuscript(manuscriptObj);
		assertEquals(oneManuscriptUser.getAuthor().getMyManuscripts().size(), 0);
	}
	
	/**
	 * Tests that an author may edit a manuscript.
	 * 
	 * @version 5/26/2016
	 */
	@Test
	public void testEditManuscriptFile() {
		Manuscript man = oneManuscriptUser.getAuthor().getMyManuscripts().get(0);
		File otherFile = null;
		try {
			otherFile = new File("./TestDataFiles/AlexTest.txt");
		} catch (NullPointerException e) {
			System.err.println("PATHNAME ERROR");
		}
		oneManuscriptUser.getAuthor().editManuscriptFile(man, otherFile);
		assertEquals(oneManuscriptUser.getAuthor().getMyManuscripts().get(0).getMyManuscript(), otherFile);
	}
	
	@Test
	public void testEditManuscriptTitle() {
		Manuscript man = oneManuscriptUser.getAuthor().getMyManuscripts().get(0);
		String newTitle = "New Title";
		oneManuscriptUser.getAuthor().editManuscriptTitle(man, newTitle);
		assertEquals(oneManuscriptUser.getAuthor().getMyManuscripts().get(0).getMyTitle(), newTitle);
	}

}
