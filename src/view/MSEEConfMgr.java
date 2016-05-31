package view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.io.Serializable;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.Scanner;

import model.Conference;
import model.User;


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
	static List<Conference> myConferences;
	
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
	        myConferences = (List) in.readObject();
	        in.close();
	        fileIn.close();
		} catch(FileNotFoundException f) {
			initializeNewData();
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
		myConf = selectConference();
		String whoAmI = login();
		myWhoAmI = whoAmI;
//		System.out.println(myUsers.get(whoAmI).getClass().toString());
		System.out.println();
		displayInterface(whoAmI, myUsers);
		console.close();
		
		try {
	    	FileOutputStream fileOut = new FileOutputStream("./MSEEdata.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(myUsers);
	        out.writeObject(myConferences);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized file saved in ./MSEEdata.ser");
	      } catch(IOException i) {
	    	  i.printStackTrace();
	      }
	}
	
	public static Conference selectConference() {
		int i = 1;
		System.out.println("Select a conference number:");
		for (i = 0; i < myConferences.size(); i++) {
			System.out.println((i + 1) + ". " + myConferences.get(i).getMyConfName());
		}
		System.out.print("\n> ");
		int selection = console.nextInt();
		console.nextLine();
		Conference selectedConference = myConferences.get(selection-1);
		
		return selectedConference;
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
		
		System.out.println(userName + " has the following roles for this conference:");
		int i = 0;
		String roles[] = new String[4];
		roles[i++] = User.AUTHOR;
		//System.out.println("Author");
		if (myUsers.get(userName).isRole(User.PROGRAM_CHAIR)) {
			roles[i++] = User.PROGRAM_CHAIR;
			//System.out.println("Program Chair");
		}
		if (myUsers.get(userName).isRole(User.REVIEWER)) {
			roles[i++] = User.REVIEWER;
			//System.out.println("Reviewer");
		} 
		if (myUsers.get(userName).isRole(User.SUBPROGRAM_CHAIR)) {
			roles[i++] = User.SUBPROGRAM_CHAIR;
			//System.out.println("Subprogram Chair");
		} 
		for (int j = 1; j <= i; j++) {
			System.out.println(j + ". " + roles[j -1]);
		}
		System.out.print("Select Your Role > ");
		i = console.nextInt();
		console.nextLine();
		myRole = roles[i -1];
		return userName;
	}
	
	/**
	 * displays the appropriate user interface for the role the user logged in as.
	 * 
	 * @version 5/8/2016
	 */
	public static void displayInterface(String theWhoIam, HashMap<String, User> theUsers) {
		if (myRole.equals(User.AUTHOR)) {
			AuthorUI aui = new AuthorUI(myUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				aui.authorInterfaceHasManuscripts();
			} else {
				aui.authorInterfaceNoManuscripts();
			}
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.PROGRAM_CHAIR)) {
			ProgramChairUI pui = new ProgramChairUI(myUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			pui.pcInterface();
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.REVIEWER)) {
			ReviewerUI rui = new ReviewerUI(myUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			rui.reviewerInterface();
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.SUBPROGRAM_CHAIR)) {
			SubProgramChairUI spcui = new SubProgramChairUI(myUsers, myConf, myWhoAmI, myRole, 
					currDateString, console); 
			spcui.subpcInterface();
		} 
	}
	
	public static void header(String role, String name, String date, String conference){
		System.out.println("MSEE Conference Management");
		System.out.println( role + " - " + name);
		System.out.println(conference);
		System.out.println("Date: " + date);
	}
	
	public static void initializeNewData() {
		System.out.println("No existing data initializing new conference data\n");
		myUsers = new HashMap<String, User>();
		myUsers.put("Josh", new User("Josh")); //Name/Role
		myUsers.put("Arthur", new User("Arthur")); //Name/Role
		myUsers.put("Ron", new User("Ron")); //Name/Role
		myUsers.put("Steve", new User("Steve")); //Name/Role
		myUsers.put("Peter", new User("Peter")); //Name/Role
		Date deadline = new Date(System.currentTimeMillis() + 1209600000);
		myConf = new Conference(myUsers.get("Peter"), "Innovative Trends in Science", deadline, 0, 5);
		myConferences = new ArrayList<Conference>();
		myConferences.add(myConf);
	}
	
}
