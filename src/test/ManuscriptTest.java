package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import model.Manuscript;
import model.PastDeadlineException;

public class ManuscriptTest {

	/**
	 * Tests that a file is stored.
	 * @version 6/1/2016
	 */
	@Test
	public void testStoreWithExistingFile() {
		File manuscriptFile = new File("./TestDataFiles/TestManuscriptFile");
		try {
			Manuscript testManuscript = new Manuscript(manuscriptFile, "Test Author", "Test Title");
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
		File manuscriptFile = new File("./TestDataFiles/NotAFile");
		Manuscript testManuscript = new Manuscript(manuscriptFile, "Test Author", "Test Title");
		fail();
	}

}
