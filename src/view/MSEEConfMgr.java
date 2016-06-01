package view;


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
 * @version 5/31/2016
 */
public class MSEEConfMgr {

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
	        myConferences = (List) in.readObject();
	        in.close();
	        fileIn.close();
		} catch(FileNotFoundException f) {
			System.err.println("MSEEdata.ser not found.");
			return;
		} catch(IOException i) {
	        i.printStackTrace();
	        return;
		} catch(ClassNotFoundException c) {
	        System.out.println("MSEE class not found");
	        c.printStackTrace();
	        return;
		} 
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy"); 	
		Date currDate = new Date();							
		currDateString = dateFormat.format(currDate);		
		
		console = new Scanner(System.in);
		myConf = selectConference();
		String whoAmI = login(myConf.getUserMap());
		myWhoAmI = whoAmI;
		System.out.println();
		displayInterface(whoAmI, myConf.getUserMap());
		console.close();
		
		try {
	    	FileOutputStream fileOut = new FileOutputStream("./MSEEdata.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(myConferences);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized file saved in ./MSEEdata.ser");
	      } catch(IOException i) {
	    	  i.printStackTrace();
	      }
	}
	
	/**
	 * Selects a conference from a list.
	 * 
	 * @version 5/20/2016
	 */
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
	 * @version 5/31/2016
	 */
	public static String login(HashMap<String, User> theUsers) {
		System.out.print("Please enter your User Name > ");
		String userName = console.nextLine();
		
		System.out.println(userName + " has the following roles for this conference:");
		int i = 0;
		String roles[] = new String[4];
		roles[i++] = User.AUTHOR;
		if (theUsers.get(userName).isRole(User.PROGRAM_CHAIR)) {
			roles[i++] = User.PROGRAM_CHAIR;
		}
		if (theUsers.get(userName).isRole(User.REVIEWER)) {
			roles[i++] = User.REVIEWER;
		} 
		if (theUsers.get(userName).isRole(User.SUBPROGRAM_CHAIR)) {
			roles[i++] = User.SUBPROGRAM_CHAIR;
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
			AuthorUI aui = new AuthorUI(theUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			if (theUsers.get(myWhoAmI).isRole(myRole)) {
				aui.authorInterfaceHasManuscripts();
			} else {
				aui.authorInterfaceNoManuscripts();
			}
		} else if (theUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.PROGRAM_CHAIR)) {
			ProgramChairUI pui = new ProgramChairUI(theUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			pui.pcInterface();
		} else if (theUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.REVIEWER)) {
			ReviewerUI rui = new ReviewerUI(theUsers, myConf, myWhoAmI, myRole, 
					currDateString, console);
			rui.reviewerInterface();
		} else if (theUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.SUBPROGRAM_CHAIR)) {
			SubProgramChairUI spcui = new SubProgramChairUI(theUsers, myConf, myWhoAmI, myRole, 
					currDateString, console); 
			spcui.subpcInterface();
		} 
	}
	
	/**
	 * Displays the program header
	 * @version 5/25/2016
	 */
	public static void header(String role, String name, String date, String conference){
		System.out.println("\n\nMSEE Conference Management");
		System.out.println( role + " - " + name);
		System.out.println(conference);
		System.out.println("Date: " + date);
	}	
}
