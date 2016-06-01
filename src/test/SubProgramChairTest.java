package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Manuscript;
import model.Recommendation;
import model.Reviewer;
import model.SubProgramChair;
import model.User;

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
	 * A subprogram chair
	 */
	User subprogramChairWithNoAssignedManuscripts;
	
	/**
	 * A user that is a subprogram chair with the maximum amount of assigned manuscripts.
	 */
	User subprogramChairWithMaxAssignedManuscripts;
	
	/**
	 * A user that is a reviewer.
	 */
	User reviewer;
	
	/**
	 * A File for a manuscript.
	 */
	File manuscriptFile;
	
	/**
	 * A file for a recommendation.
	 */
	File recommendationFile;
	
	/**
	 * A manuscript.
	 */
	Manuscript manuscript;
	
	/**
	 * A manuscript authored by the subprogram chair.
	 */
	Manuscript subprogramChairsManuscript;
	
	/**
	 * A recommendation.
	 */
	Recommendation recommendation;
	
	@Before
	public void setUp() {
		subprogramChairWithNoAssignedManuscripts = new User("Sub Chairman");
		reviewer = new User("Rev Ewer");
		subprogramChairWithNoAssignedManuscripts.assignSubProgramChair(new SubProgramChair());
		reviewer.assignReviewer(new Reviewer());
		manuscriptFile = new File("./TestDataFiles/TestManuscriptFile");
		recommendationFile = new File("./TestDataFiles/TestRecommendation");
		try {
			manuscript = new Manuscript(manuscriptFile, "Tester", "test");
			subprogramChairsManuscript = new Manuscript(manuscriptFile, subprogramChairWithNoAssignedManuscripts.getMyName(), "test");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		ArrayList<Manuscript> manuscripts = new ArrayList<Manuscript>();
		for (int i = 0; i < Reviewer.MANUSCRIPT_LIMIT; i++) {
			String testAuthor = "Test Author" + i;
			String testTitle = "Test Title" + i;
			Manuscript man = null;
			try {
				man = new Manuscript(manuscriptFile, testAuthor, testTitle);
			} catch (IOException e) {
				e.printStackTrace();
			}
			manuscripts.add(man);
		}
		subprogramChairWithMaxAssignedManuscripts = new User("subprogram");
		subprogramChairWithMaxAssignedManuscripts.assignSubProgramChair(new SubProgramChair(manuscripts));
	}

	/**
	 * tests assigning a reviewer to a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAssignReviewer() {
		
		assertTrue(subprogramChairWithNoAssignedManuscripts.getSubProgramChair().assignReviewer(manuscript, reviewer));
		}
	
	/**
	 * tests submitting a recommendation of a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testSubmitRecommendation() {
		String recommendationStatement = "Test Recommendation";
		try {
			subprogramChairWithNoAssignedManuscripts.getSubProgramChair().submitRecommendation(manuscript, recommendationFile, recommendationStatement);
		} catch (IOException e) {
			fail();
		}
		assertEquals(manuscript.getMyRecommendation().getMyRecommendationForm(), recommendationFile);
	}
	
	/**
	 * tests adding a manuscript to the list of assigned manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscript() {
		assertTrue(subprogramChairWithNoAssignedManuscripts.getSubProgramChair().addManuscript(manuscript, subprogramChairWithNoAssignedManuscripts.getMyName()));
		assertEquals(subprogramChairWithNoAssignedManuscripts.getSubProgramChair().getManuscripts().size(), 1);
	}
	
	/**
	 * tests assigning a manuscript authored by the subprogram chair to the list 
	 * of assigned manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscriptSameAuthor() {
		assertFalse(subprogramChairWithNoAssignedManuscripts.getSubProgramChair().addManuscript(subprogramChairsManuscript, subprogramChairWithNoAssignedManuscripts.getMyName()));
		assertEquals(subprogramChairWithNoAssignedManuscripts.getSubProgramChair().getManuscripts().size(), 0);
	}
	
	/**
	 * tests adding a manuscript to the list of assigned manuscripts when the subprogram
	 * chair has already been assigned the maximum number of manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	@Test
	public void testAddManuscriptAtLimit() {
		assertFalse(subprogramChairWithMaxAssignedManuscripts.getSubProgramChair().addManuscript(manuscript, subprogramChairWithMaxAssignedManuscripts.getMyName()));
		assertEquals(subprogramChairWithMaxAssignedManuscripts.getSubProgramChair().getManuscripts().size(), 4);
	}

}
