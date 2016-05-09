import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
/**
 * Tests the Reviewer class.
 * \
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

public class ReviewerTest {

	/**
	 * tests the submit review method.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitReview() {
		User ron = new User("Ron");
		ron.myRoles.myReviewer = new Reviewer("Ron");
		File revFile = new File("./review.txt");
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Arthur");
		ron.myRoles.myReviewer.submitReview(revFile, manu);
		assertEquals(ron.myRoles.myReviewer.myReview.size(), 1);
		assertEquals(ron.myRoles.myReviewer.myReview.get(0), manu.myReviews.get(0));
	}
	
	/**
	 * tests adding a manuscript to thos assigned to the reviewer. With
	 * a manuscript that should be added.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscript() {
		User ron = new User("Ron");
		ron.myRoles.myReviewer = new Reviewer("Ron");
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Arthur");
		assertTrue(ron.myRoles.myReviewer.addManuscript(manu, ron.myName));
		assertEquals(ron.myRoles.myReviewer.myManuscripts.get(0), manu);
	}
	
	/**
	 * tests adding a manuscript to those assigned to the reviewer that was
	 * authored by the reviewer.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscriptSameAuthor() {
		User ron = new User("Ron");
		ron.myRoles.myReviewer = new Reviewer("Ron");
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Ron");
		assertFalse(ron.myRoles.myReviewer.addManuscript(manu, ron.myName));
		assertEquals(ron.myRoles.myReviewer.myReview.size(), 0);
	}
	
	/**
	 * tests adding a manuscript to a reviewer that had already been assigned
	 * the maximum number of manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscriptAtLimit() {
		User ron = new User("Ron");
		ron.myRoles.myReviewer = new Reviewer("Ron");
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
		assertTrue(ron.myRoles.myReviewer.addManuscript(manu, ron.myName));
		assertTrue(ron.myRoles.myReviewer.addManuscript(manu2, ron.myName));
		assertTrue(ron.myRoles.myReviewer.addManuscript(manu3, ron.myName));
		assertTrue(ron.myRoles.myReviewer.addManuscript(manu4, ron.myName));
		assertFalse(ron.myRoles.myReviewer.addManuscript(manu5, ron.myName));
		assertEquals(ron.myRoles.myReviewer.myManuscripts.size(), 4);
	}
	
}
