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
		ron.assignReviewer(new Reviewer());
		File revFile = new File("./review.txt");
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Arthur", "test");
		int SCORE = 79;
		ron.getReviewer().submitReview(revFile, manu, SCORE);
		assertEquals(ron.getReviewer().getMyReviews().size(), 1);
		assertEquals(ron.getReviewer().getMyReviews().get(manu), manu.getMyReviews().get(0));
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
		ron.assignReviewer(new Reviewer());
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Arthur", "test");
		assertTrue(ron.getReviewer().addManuscript(manu, ron.getMyName()));
		assertTrue(ron.getReviewer().getManuscripts().contains(manu));
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
		ron.assignReviewer(new Reviewer());
		File manFile = new File("./AntiSocialNetwork.doc");
		Manuscript manu = new Manuscript(manFile, "Ron", "test");
		assertFalse(ron.getReviewer().addManuscript(manu, ron.getMyName()));
		assertEquals(ron.getReviewer().getMyReviews().size(), 0);
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
		ron.assignReviewer(new Reviewer());
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
		assertTrue(ron.getReviewer().addManuscript(manu, ron.getMyName()));
		assertTrue(ron.getReviewer().addManuscript(manu2, ron.getMyName()));
		assertTrue(ron.getReviewer().addManuscript(manu3, ron.getMyName()));
		assertTrue(ron.getReviewer().addManuscript(manu4, ron.getMyName()));
		assertFalse(ron.getReviewer().addManuscript(manu5, ron.getMyName()));
		assertEquals(ron.getReviewer().getManuscripts().size(), 4);
	}
	
}
