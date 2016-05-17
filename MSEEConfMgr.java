

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
//import java.io.Serializable;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.Scanner;


/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/8/2016
 */

// Alexandria, 5/15/16 - I think we should separate the headers of each interface into their own methods,
// since we use the same 3 lines in every menu.
public class MSEEConfMgr {

	static HashMap<String, User> myUsers;
	static Conference myConf;
	static String myWhoAmI;
	static String myRole;
	static String currDateString;
	static Scanner console;
	
	public MSEEConfMgr() {
		
	}
	
	/**
	 * Main method, serializes and deserializes stored data. If there is no stored data
	 * then a new list of users is initialized.
	 * 
	 * @version 5/8/2016
	 */
	public static void main(String[] theArgs) {
		  
		try {
			FileInputStream fileIn = new FileInputStream("./MSEEdata.ser");	
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        myUsers = (HashMap<String, User>) in.readObject();
	        myConf = (Conference) in.readObject();
	        in.close();
	        fileIn.close();
		} catch(FileNotFoundException f) {
			System.out.println("No existing data initializing new conference data\n");
			myUsers = new HashMap<String, User>();
			myUsers.put("Josh", new User("Josh")); //Name/Role
			myUsers.put("Arthur", new User("Arthur")); //Name/Role
			myUsers.get("Arthur").myRoles.myAuthor = new Author("Arthur");
			myUsers.put("Ron", new User("Ron")); //Name/Role
			myUsers.put("Steve", new User("Steve")); //Name/Role
			myUsers.put("Peter", new User("Peter")); //Name/Role
			Date deadline = new Date(System.currentTimeMillis() + 1209600000);
			myConf = new Conference(myUsers.get("Peter"), "Innovative Trends in Science", deadline);
		} catch(IOException i) {
	        i.printStackTrace();
	        return;
		} catch(ClassNotFoundException c) {
	        System.out.println("MSEE class not found");
	        c.printStackTrace();
	        return;
		} 
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy"); 	//Alexandria, 5/15/16 - to format the current date for display
		Date currDate = new Date();							//the current date
		currDateString = dateFormat.format(currDate);		//stored as a string
		
		console = new Scanner(System.in);
		String whoAmI = login();
		myWhoAmI = whoAmI;
//		System.out.println(myUsers.get(whoAmI).getClass().toString());
		displayInterface(whoAmI, myUsers);
		console.close();
		
		try {
	    	FileOutputStream fileOut = new FileOutputStream("./MSEEdata.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(myUsers);
	        out.writeObject(myConf);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized file saved in ./MSEEdata.ser");
	      } catch(IOException i) {
	    	  i.printStackTrace();
	      }
	}
	
	/**
	 * Login as a user and role.
	 * 
	 * @version 5/8/2016
	 */
	public static String login() {
		System.out.println("Welcome to the MSEE Conference Manager.");
		System.out.print("Please enter your User Name > ");
		String userName = console.nextLine();
		
		//Alexandria, 5/15/16 - the intent of the following code was to print out all
		//of the roles available to a user. It appears to only be working for Peter, though.
		//Anyone else only shows "User". Anyone know why that is?
		//5/17/16 - EDIT it looks like Peter was the only one who had a role other than user to start with.
		System.out.println(userName + " has the following roles for this conference:");
		if (myUsers.get(userName).isRole(User.AUTHOR)) {
			System.out.println("Author");
		}
		if (myUsers.get(userName).isRole(User.PROGRAM_CHAIR)) {
			System.out.println("Program Chair");
		}
		if (myUsers.get(userName).isRole(User.REVIEWER)) {
			System.out.println("Reviewer");
		} 
		if (myUsers.get(userName).isRole(User.SUBPROGRAM_CHAIR)) {
			System.out.println("Subprogram Chair");
		} 
		System.out.println("User");
		
		System.out.print("Enter Your Role > ");
		myRole = console.nextLine();
		return userName;
	}
	
	/**
	 * displays the appropriate user interface for the role the user logged in as.
	 * 
	 * @version 5/8/2016
	 */
	public static void displayInterface(String theWhoIam, HashMap<String, User> theUsers) {
		if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.AUTHOR)) {
			AuthorUI aui = new AuthorUI(myUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			aui.authorInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.PROGRAM_CHAIR)) {
			ProgramChairUI pui = new ProgramChairUI(myUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			pui.pcInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.REVIEWER)) {
			ReviewerUI rui = new ReviewerUI(myUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			rui.reviewerInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.SUBPROGRAM_CHAIR)) {
			SubProgramChairUI spcui = new SubProgramChairUI(myUsers, myConf, myWhoAmI, myRole, 
					currDateString, console); 
			spcui.subpcInterface(myWhoAmI);
		} else {
			myRole = "User";
			UserUI uui = new UserUI(myUsers, myConf, myWhoAmI, myRole, currDateString, console);
			uui.userInterface(myWhoAmI);
		}
	}
	
}
