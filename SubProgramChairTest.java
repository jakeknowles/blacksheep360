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
		subChair.myRoles.mySubProgramChair = new SubProgramChair();
		reviewer.myRoles.myReviewer = new Reviewer(reviewer.myName);
		File manFile = new File("./AntiSocialNetWork.doc");
		Manuscript manu = new Manuscript(manFile, "Tester");
		assertTrue(subChair.myRoles.mySubProgramChair.assignReviewer(manu, reviewer));
		}
	
	/**
	 * tests submitting a recommendation of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitRecommendation() {
		User subChair = new User("Sub Chairman");
		subChair.myRoles.mySubProgramChair = new SubProgramChair();
		File manFile = new File("./AntiSocialNetWork.doc");
		File recFile = new File("./review.txt");
		Manuscript manu = new Manuscript(manFile, "Tester");
		subChair.myRoles.mySubProgramChair.submitRecommendation(manu, recFile);
		assertEquals(manu.myRecommendation.myRecommendationForm, recFile);
	}
	
	/**
	 * tests adding a manuscript to the list of assigned manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscript() {
		User subChair = new User("Sub Chairman");
		subChair.myRoles.mySubProgramChair = new SubProgramChair();
		File manFile = new File("./AntiSocialNetWork.doc");
		Manuscript manu = new Manuscript(manFile, "Tester");
		assertTrue(subChair.myRoles.mySubProgramChair.addManuscript(manu, subChair.myName));
		assertEquals(subChair.myRoles.mySubProgramChair.myManuscripts.size(), 1);
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
		subChair.myRoles.mySubProgramChair = new SubProgramChair();
		File manFile = new File("./AntiSocialNetWork.doc");
		Manuscript manu = new Manuscript(manFile, "Sub Chairman");
		assertFalse(subChair.myRoles.mySubProgramChair.addManuscript(manu, subChair.myName));
		assertEquals(subChair.myRoles.mySubProgramChair.myManuscripts.size(), 0);
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
		subChair.myRoles.mySubProgramChair = new SubProgramChair();
		File manFile = new File("./AntiSocialNetwork.doc");
		File manFile2 = new File("./AlexTest.txt");
		File manFile3 = new File("./checkin3.doc");
		File manFile4 = new File("./review.txt");
		File manFile5 = new File("./Class Diagram.pdf");
		Manuscript manu = new Manuscript(manFile, "Arthur");
		Manuscript manu2 = new Manuscript(manFile2, "Arthur");
		Manuscript manu3 = new Manuscript(manFile3, "Arthur");
		Manuscript manu4 = new Manuscript(manFile4, "Arthur");
		Manuscript manu5 = new Manuscript(manFile5, "Arthur");
		assertTrue(subChair.myRoles.mySubProgramChair.addManuscript(manu, subChair.myName));
		assertTrue(subChair.myRoles.mySubProgramChair.addManuscript(manu2, subChair.myName));
		assertTrue(subChair.myRoles.mySubProgramChair.addManuscript(manu3, subChair.myName));
		assertTrue(subChair.myRoles.mySubProgramChair.addManuscript(manu4, subChair.myName));
		assertFalse(subChair.myRoles.mySubProgramChair.addManuscript(manu5, subChair.myName));
		assertEquals(subChair.myRoles.mySubProgramChair.myManuscripts.size(), 4);
	}

}
