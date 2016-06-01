package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import model.Review;

public class ReviewTest {

	/**
	 * Tests that a file is stored.
	 * @version 6/1/2016
	 */
	@Test
	public void testStoreWithExistingFile() {
		File reviewFile = new File("./TestDataFiles/TestManuscriptFile");
		try {
			Review testReview = new Review(reviewFile, 5, "Test Title");
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
		File reviewFile = new File("./TestDataFiles/NotAFile");
		Review testReview = new Review(reviewFile, 5, "Test Title");
		fail();
	}

}
