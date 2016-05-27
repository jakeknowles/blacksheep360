package test;

import static org.junit.Assert.*;

import java.io.File;
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
 * @version 5/8/2016
 */

public class ConferenceTest {
	
	public Date deadline;
	
	public Conference testConf;
	
	public Manuscript man;
	
	@Before
	public void setUp() {
		deadline = new Date(System.currentTimeMillis() + 3600000);
		testConf = new Conference(new User("Tester"), "TestConference", deadline);
		File manFile = null;
		try {
			manFile = new File("./TestDataFiles/AntiSocialNetwork.doc");
		} catch (NullPointerException e) {
			System.err.println("PATHNAME ERROR");
		}
		man = new Manuscript(manFile, "Tester", "test");
	}

	/**
	 * tests the adding of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscript() {
		testConf.addManuscript(man);
		assertEquals(testConf.getMyManuscripts().get(man.getMyTitle()), man);
	}
	
	/**
	 * tests the removing of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testRemoveManuscript() {
		testConf.addManuscript(man);
		testConf.removeManuscript(man.getMyTitle());
		assertTrue(testConf.getMyManuscripts().isEmpty());
	}

}
