package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.Conference;
import model.Manuscript;
import model.User;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/31/2016
 */

public class ConferenceTest {
	
	/**
	 * A deadline
	 */
	public Date deadline;
	
	/**
	 * A test conference
	 */
	public Conference testConference;
	
	/**
	 * A manuscript
	 */
	public Manuscript manuscript;
	
	@Before
	public void setUp() {
		deadline = new Date(System.currentTimeMillis() + 3600000);
		testConference = new Conference(new User("Tester"), "TestConference", deadline, 0 , 5);
		File manFile = null;
		try {
			manFile = new File("./TestDataFiles/TestManuscriptFile");
		} catch (NullPointerException e) {
			System.err.println("PATHNAME ERROR");
		}
		try {
			manuscript = new Manuscript(manFile, "Tester", "test");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tests the adding of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscript() {
		testConference.addManuscript(manuscript);
		assertEquals(testConference.getMyManuscripts().get(manuscript.getMyTitle()), manuscript);
	}
	
	/**
	 * tests the removing of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testRemoveManuscript() {
		testConference.addManuscript(manuscript);
		testConference.removeManuscript(manuscript.getMyTitle());
		assertTrue(testConference.getMyManuscripts().isEmpty());
	}
	
	/**
	 * tests editing the title of a submitted manuscript
	 * 
	 * @version 5/31/2016
	 */
	@Test
	public void testEditManuscriptTitle() {
		testConference.addManuscript(manuscript);
		String newTitle = "New Title";
		testConference.editManuscriptTitle("test", newTitle);
		assertEquals(testConference.getMyManuscripts().get(newTitle).getMyTitle(), newTitle);
	}

}
