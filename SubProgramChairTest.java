

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * Test class for the subprogram chair.
 * 
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class SubProgramChairTest {

	/**
	 * tests assigning a reviewer to a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAssignReviewer() {
		User subChair = new User("Sub Chairman");
		User reviewer = new User("Rev Ewer");
		subChair.getMyRoles().mySubProgramChair = new SubProgramChair();
		reviewer.getMyRoles().myReviewer = new Reviewer(reviewer.getMyName());
		File manFile = new File("./AntiSocialNetWork.doc");
		Manuscript manu = new Manuscript(manFile, "Tester", "test");
		assertTrue(subChair.getMyRoles().mySubProgramChair.assignReviewer(manu, reviewer));
		}
	
	/**
	 * tests submitting a recommendation of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitRecommendation() {
		User subChair = new User("Sub Chairman");
		subChair.getMyRoles().mySubProgramChair = new SubProgramChair();
		File manFile = new File("./AntiSocialNetWork.doc");
		File recFile = new File("./review.txt");
		Manuscript manu = new Manuscript(manFile, "Tester", "test");
		subChair.getMyRoles().mySubProgramChair.submitRecommendation(manu, recFile);
		assertEquals(manu.getMyRecommendation().getMyRecommendationForm(), recFile); //Alexandria, 5/22/16 - this line was having issues so I added a getter.
	}
	
	/**
	 * tests adding a manuscript to the list of assigned manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscript() {
		User subChair = new User("Sub Chairman");
		subChair.getMyRoles().mySubProgramChair = new SubProgramChair();
		File manFile = new File("./AntiSocialNetWork.doc");
		Manuscript manu = new Manuscript(manFile, "Tester", "test");
		assertTrue(subChair.getMyRoles().mySubProgramChair.addManuscript(manu, subChair.getMyName()));
		assertEquals(subChair.getMyRoles().mySubProgramChair.getMyManuscripts().size(), 1);
	}
	
	/**
	 * tests assigning a manuscript authored by the subprogram chair to the list 
	 * of assigned manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscriptSameAuthor() {
		User subChair = new User("Sub Chairman");
		subChair.getMyRoles().mySubProgramChair = new SubProgramChair();
		File manFile = new File("./AntiSocialNetWork.doc");
		Manuscript manu = new Manuscript(manFile, "Sub Chairman", "test");
		assertFalse(subChair.getMyRoles().mySubProgramChair.addManuscript(manu, subChair.getMyName()));
		assertEquals(subChair.getMyRoles().mySubProgramChair.getMyManuscripts().size(), 0);
	}
	
	/**
	 * tests adding a manuscript to the list of assigned manuscripts when the subprogram
	 * chair has already been assigned the maximum number of manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscriptAtLimit() {
		User subChair = new User("Sub Chairman");
		subChair.getMyRoles().mySubProgramChair = new SubProgramChair();
		File manFile = new File("./AntiSocialNetwork.doc");
		File manFile2 = new File("./AlexTest.txt");
		File manFile3 = new File("./checkin3.doc");
		File manFile4 = new File("./review.txt");
		File manFile5 = new File("./Class Diagram.pdf");
		Manuscript manu = new Manuscript(manFile, "Arthur", "test");
		Manuscript manu2 = new Manuscript(manFile2, "Arthur", "test");
		Manuscript manu3 = new Manuscript(manFile3, "Arthur", "test");
		Manuscript manu4 = new Manuscript(manFile4, "Arthur", "test");
		Manuscript manu5 = new Manuscript(manFile5, "Arthur", "test");
		assertTrue(subChair.getMyRoles().mySubProgramChair.addManuscript(manu, subChair.getMyName()));
		assertTrue(subChair.getMyRoles().mySubProgramChair.addManuscript(manu2, subChair.getMyName()));
		assertTrue(subChair.getMyRoles().mySubProgramChair.addManuscript(manu3, subChair.getMyName()));
		assertTrue(subChair.getMyRoles().mySubProgramChair.addManuscript(manu4, subChair.getMyName()));
		assertFalse(subChair.getMyRoles().mySubProgramChair.addManuscript(manu5, subChair.getMyName()));
		assertEquals(subChair.getMyRoles().mySubProgramChair.getMyManuscripts().size(), 4);
	}

}
