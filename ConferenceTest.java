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

public class ConferenceTest {

	/**
	 * tests the adding of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscript() {
		Date deadline = new Date(System.currentTimeMillis() + 3600000);
		Conference testConf = new Conference(new User("Tester"), "TestConference", deadline);
		File manFile = new File("AlexTest.txt");
		Manuscript man = new Manuscript(manFile, "Tester");
		testConf.addManuscript(man);
		assertEquals(testConf.myManuscripts.get(man.myTitle), man);
	}
	
	/**
	 * tests the removing of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testRemoveManuscript() {
		Date deadline = new Date(System.currentTimeMillis() + 3600000);
		Conference testConf = new Conference(new User("Tester"), "TestConference", deadline);
		File manFile = new File("AlexTest.txt");
		Manuscript man = new Manuscript(manFile, "Tester");
		testConf.addManuscript(man);
		testConf.removeManuscript(man.myTitle);
		assertTrue(testConf.myManuscripts.isEmpty());
	}

}
