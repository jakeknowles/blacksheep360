package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
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
	
	/**
	 * deadline in the future.
	 */
	private Date futureDeadline;
	
	/**
	 * Deadline in the past.
	 */
	private Date pastDeadline;
	
	/**
	 * A user that is an author with no manuscripts.
	 */
	private User noManuscriptUser;
	
	/**
	 * A user that is an author with one manuscript.
	 */
	private User oneManuscriptUser;
	
	/**
	 * A file that contains a manuscript.
	 */
	private File manuscriptFile;
	
	
	/**
	 * a manuscript.
	 */
	private Manuscript testManuscript;
	
	
	/**
	 * A list of manuscripts.
	 */
	private ArrayList<Manuscript> manuscriptList;
	
	/**
	 * A test title for a manuscript.
	 */
	private String testTitle;
	
	@Before
	public void setUp() {
		long someTimeInterval = 3600000;
		futureDeadline = new Date(System.currentTimeMillis() + someTimeInterval);
		pastDeadline = new Date(System.currentTimeMillis() - someTimeInterval);
		noManuscriptUser = new User("Arthur");
		oneManuscriptUser = new User("Arthur");
		manuscriptFile = new File("./TestDataFiles/TestManuscriptFile");
		testTitle = "Test Title";
		try {
			testManuscript = new Manuscript(manuscriptFile, noManuscriptUser.getMyName(), testTitle);
		} catch (IOException e) {
			e.printStackTrace();
		}
		noManuscriptUser.assignAuthor(new Author(noManuscriptUser.getMyName()));
		manuscriptList = new ArrayList<Manuscript>();
		manuscriptList.add(testManuscript);
		try {
			oneManuscriptUser.assignAuthor(new Author(oneManuscriptUser.getMyName(), manuscriptList));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * tests the authors ability to submit a manuscript.
	 * 
	 * @version 5/26/2016
	 */
	@Test
	public void testSubmitManuscript() {
		try {
			noManuscriptUser.getAuthor().submitManuscript(manuscriptFile, futureDeadline, testTitle);
		} catch (PastDeadlineException e) {
			fail();
		} catch (IOException e) {
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
		try {
			noManuscriptUser.getAuthor().submitManuscript(manuscriptFile, pastDeadline, testTitle);
		} catch (IOException e) {
			fail();
		}
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
			otherFile = new File("./TestDataFiles/SecondTestManuscriptFile");
		} catch (NullPointerException e) {
			System.err.println("PATHNAME ERROR");
		}
		try {
			oneManuscriptUser.getAuthor().editManuscriptFile(man, otherFile);
		} catch (IOException e) {
			fail();
		}
		assertEquals(oneManuscriptUser.getAuthor().getMyManuscripts().get(0).getMyManuscript(), otherFile);
	}
	
	/**
	 * Tests that an author can change the title of a manuscript
	 * he/she has submitted.
	 * 
	 * @version 5/31/2016
	 */
	@Test
	public void testEditManuscriptTitle() {
		Manuscript man = oneManuscriptUser.getAuthor().getMyManuscripts().get(0);
		String newTitle = "New Title";
		oneManuscriptUser.getAuthor().editManuscriptTitle(man, newTitle);
		assertEquals(oneManuscriptUser.getAuthor().getMyManuscripts().get(0).getMyTitle(), newTitle);
	}

}
