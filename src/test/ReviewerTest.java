package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.Manuscript;
import model.Review;
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
 * @version 5/31/2016
 */

public class ReviewerTest {
	
	/**
	 * A reviewer with no assigned manuscripts.
	 */
	public User reviewerWithNoAssignedManuscripts;
	
	/**
	 * A reviewer with the maximum amount of assigned manuscripts.
	 */
	public User reviewerWithMaxAssignedManuscripts;
	
	/**
	 * A review file.
	 */
	public File reviewFile;
	
	/**
	 * A manuscript file.
	 */
	public File manuscriptFile;
	
	/**
	 * A manuscript.
	 */
	public Manuscript manuscript;
	
	/**
	 * A manuscript authored by a reviewer.
	 */
	public Manuscript reviewersManuscript;
	
	/**
	 * A score to assign to a manuscript.
	 */
	public int score;
	
	
	@Before
	public void setUp() {
		reviewerWithNoAssignedManuscripts = new User("Ron");
		reviewerWithNoAssignedManuscripts.assignReviewer(new Reviewer());
		reviewFile = new File("./TestDataFiles/TestReview");
		manuscriptFile = new File("./TestDataFiles/TestManuscriptFile");
		
		try {
			manuscript = new Manuscript(manuscriptFile, "Arthur", "test");
			reviewersManuscript = new Manuscript(manuscriptFile, reviewerWithNoAssignedManuscripts.getMyName(), "test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		score = 79;
		HashMap<Manuscript, Review> reviews = new HashMap<Manuscript, Review>();
		for (int i = 0; i < Reviewer.MANUSCRIPT_LIMIT; i++) {
			String testAuthor = "Test Author" + i;
			String testTitle = "Test Title" + i;
			Manuscript man;
			try {
				man = new Manuscript(manuscriptFile, testAuthor, testTitle);
				reviews.put(man, new Review(reviewFile, score + i, man.getMyTitle()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		reviewerWithMaxAssignedManuscripts = new User("Randy");
		reviewerWithMaxAssignedManuscripts.assignReviewer(new Reviewer(reviews));
	}

	/**
	 * tests the submit review method.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitReview() {
		try {
			reviewerWithNoAssignedManuscripts.getReviewer().submitReview(reviewFile, manuscript, score);
		} catch (IOException e) {
			fail();
		}
		assertEquals(reviewerWithNoAssignedManuscripts.getReviewer().getMyReviews().size(), 1);
		assertEquals(reviewerWithNoAssignedManuscripts.getReviewer().getMyReviews().get(manuscript), manuscript.getMyReviews().get(0));
	}
	
	/**
	 * tests adding a manuscript to thos assigned to the reviewer. With
	 * a manuscript that should be added.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscript() {
		assertTrue(reviewerWithNoAssignedManuscripts.getReviewer().addManuscript(manuscript, reviewerWithNoAssignedManuscripts.getMyName()));
		assertTrue(reviewerWithNoAssignedManuscripts.getReviewer().getManuscripts().contains(manuscript));
	}
	
	/**
	 * tests adding a manuscript to those assigned to the reviewer that was
	 * authored by the reviewer.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscriptSameAuthor() {
		assertFalse(reviewerWithNoAssignedManuscripts.getReviewer().addManuscript(reviewersManuscript, reviewerWithNoAssignedManuscripts.getMyName()));
		assertEquals(reviewerWithNoAssignedManuscripts.getReviewer().getMyReviews().size(), 0);
	}
	
	/**
	 * tests adding a manuscript to a reviewer that had already been assigned
	 * the maximum number of manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscriptAtLimit() {
		assertFalse(reviewerWithMaxAssignedManuscripts.getReviewer().addManuscript(manuscript, reviewerWithNoAssignedManuscripts.getMyName()));
		assertEquals(reviewerWithMaxAssignedManuscripts.getReviewer().getManuscripts().size(), 4);
	}
	
}
