package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import model.Manuscript;
import model.Reviewer;
import model.User;
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
		ron.getMyRoles().myReviewer = new Reviewer("Ron");
		File revFile = new File("./review.txt");
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Arthur", "test");
		int SCORE = 79;
		ron.getMyRoles().myReviewer.submitReview(revFile, manu, SCORE);
		assertEquals(ron.getMyRoles().myReviewer.getMyReview().size(), 1);
		assertEquals(ron.getMyRoles().myReviewer.getMyReview().get(0), manu.getMyReviews().get(0));
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
		ron.getMyRoles().myReviewer = new Reviewer("Ron");
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Arthur", "test");
		assertTrue(ron.getMyRoles().myReviewer.addManuscript(manu, ron.getMyName()));
		assertEquals(ron.getMyRoles().myReviewer.getMyManuscripts().get(0), manu);
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
		ron.getMyRoles().myReviewer = new Reviewer("Ron");
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Ron", "test");
		assertFalse(ron.getMyRoles().myReviewer.addManuscript(manu, ron.getMyName()));
		assertEquals(ron.getMyRoles().myReviewer.getMyReview().size(), 0);
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
		ron.getMyRoles().myReviewer = new Reviewer("Ron");
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
		assertTrue(ron.getMyRoles().myReviewer.addManuscript(manu, ron.getMyName()));
		assertTrue(ron.getMyRoles().myReviewer.addManuscript(manu2, ron.getMyName()));
		assertTrue(ron.getMyRoles().myReviewer.addManuscript(manu3, ron.getMyName()));
		assertTrue(ron.getMyRoles().myReviewer.addManuscript(manu4, ron.getMyName()));
		assertFalse(ron.getMyRoles().myReviewer.addManuscript(manu5, ron.getMyName()));
		assertEquals(ron.getMyRoles().myReviewer.getMyManuscripts().size(), 4);
	}
	
}
