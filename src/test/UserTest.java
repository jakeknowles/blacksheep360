package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.ProgramChair;
import model.Reviewer;
import model.Roles;
import model.SubProgramChair;
import model.User;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/31/2016
 */
public class UserTest {

	/**
	 * A user with no roles.
	 */
	private User noRoles;
	
	/**
	 * A user with all roles.
	 */
	private User hasRoles;
	
	/**
	 * @version 5/31/2016
	 */
	@Before
	public void setUp() {
		noRoles = new User("Test");
		String testName = "Tester";
		hasRoles = new User(testName, new Roles(new Author(testName), new Reviewer(), new SubProgramChair(), new ProgramChair()));
	}
	
	/**
	 * Tests that a user with all roles shows as having all roles.
	 * @version 5/31/2016
	 */
	@Test
	public void testIsRoleOnHasRole() {
		assertTrue(hasRoles.isRole(User.AUTHOR));
		assertTrue(hasRoles.isRole(User.REVIEWER));
		assertTrue(hasRoles.isRole(User.SUBPROGRAM_CHAIR));
		assertTrue(hasRoles.isRole(User.PROGRAM_CHAIR));
	}
	
	/**
	 * Tests a user with no roles.
	 * @version 5/31/2016
	 */
	@Test
	public void testIsRoleOnDoesNotHaveRole(){
		assertFalse(noRoles.isRole(User.AUTHOR));
		assertFalse(noRoles.isRole(User.REVIEWER));
		assertFalse(noRoles.isRole(User.SUBPROGRAM_CHAIR));
		assertFalse(noRoles.isRole(User.PROGRAM_CHAIR));
	}

}
