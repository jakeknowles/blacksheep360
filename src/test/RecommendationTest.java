package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import model.Recommendation;

public class RecommendationTest {

	/**
	 * Tests that a file is stored.
	 * @version 6/1/2016
	 */
	@Test
	public void testStoreWithExistingFile() {
		File recommendationFile = new File("./TestDataFiles/TestManuscriptFile");
		try {
			Recommendation testRecommendation = new Recommendation(recommendationFile, "Test Recommendation");
		} catch (IOException e) {
			fail();
		}
	}
	
	/**
	 * Tests that a nonexisting file is not stored
	 * @version 6/1/2016
	 * @throws IOException
	 */
	@Test (expected = IOException.class)
	public void testStoreWithNonExistingFile() throws IOException {
		File recommendationFile = new File("./TestDataFiles/NotAFile");
		Recommendation testRecommendation = new Recommendation(recommendationFile, "Test Recommendation");
		fail();
	}

}
