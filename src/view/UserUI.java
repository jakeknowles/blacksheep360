package view;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.User;

public class UserUI {

	static HashMap<String, User> myUsers;
	static Conference myConf;
	static String myWhoAmI;
	static String myRole;
	static String currDateString;
	static Scanner console;


	public UserUI(HashMap<String, User> theUsers, Conference theConf, 
			String theWhoAmI, String theRole, String theCurrDateString, Scanner theConsole) {
		myUsers = theUsers; 
		myConf = theConf;
		myWhoAmI = theWhoAmI; 
		myRole = theRole;
		currDateString = theCurrDateString;
		console = theConsole;	
	}
	
	public static void header(String theWhoIam){
		System.out.println("MSEE Conference Management");
		System.out.println( myRole + " - " + theWhoIam);
		System.out.println(myConf.getMyConfName());
		System.out.println("Date: " + currDateString); //Alexandria, 5/15/16 - displays the current date
	}

	/**
	 * User interface for a basic user.
	 * 
	 * @version 5/8/2016
	 */
	public static void userInterface(String theWhoIam) {
		int temp = 0;
		System.out.println();
//		System.out.println("MSEE Conference Management");
//		System.out.println("User - " + theWhoIam);
//		System.out.println("Date: " + currDateString);
		header(theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a manuscript");
		System.out.println("\t2. Exit");
		System.out.println("Enter a selection > ");
		temp = console.nextInt();
		console.nextLine();
		switch (temp) {
			case 1:
				submitManuscript();
				break;
			case 2:
				System.out.println("Exiting - Goodbye!");
				//serial();
				break;

		}
	}
	
	/**
	 * User interface for submitting manuscripts.
	 * 
	 * @version 5/8/2016
	 */
	public static void submitManuscript() {
//		System.out.println("MSEE Conference Management");
////		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
////				substring(6, myUsers.get(myWhoAmI).getClass().
////						toString().length()) +" " + myWhoAmI);
//		System.out.println(myRole + " - " + myWhoAmI);
		header(myWhoAmI);
		viewMyManuscripts();
		System.out.println("Enter the file path for the manuscript"); //Alexandria, 5/15/16 - should display a list of manuscripts here EDIT: done
		System.out.println("you wish to upload");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String file = console.nextLine();
		if (file.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {  //Alexandria, 5/17/16 - wouldn't this send them to the author menu as soon as they hit "back", even if they didn't submit anything? EDIT: nevermind I was reading it wrong
				AuthorUI aui = new AuthorUI(myUsers, myConf, myWhoAmI, myRole, 
						currDateString, console);
				aui.authorInterfaceHasManuscripts();
			} else {
				userInterface(myWhoAmI);
			}
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			File toBeSaved = new File(file);
			User me = myUsers.get(myWhoAmI);
			System.out.println("Now enter the title of your submission \n>");
			String paperName = console.nextLine();
			if (!me.isRole(User.AUTHOR)) {
				me.getMyRoles().myAuthor = new Author(me.getMyName());
			}
			Manuscript newManuscript = me.getMyRoles().myAuthor.submitManuscript(toBeSaved, 
					myConf.getMyManuscriptDeadline(), paperName); // Alexandria, 5/15/16 - I made changes to Author and Manuscript to allow the user to specify the title.
			myConf.addManuscript(newManuscript);
			System.out.println(toBeSaved.length());
			System.out.println("SUCCESS!");
			myRole = "Author";
			submitManuscript();
		}
	}
	
	/**
	 * Prints a list of this user's submitted manuscripts.
	 * 
	 * @version 5/15/16
	 */
	public static void viewMyManuscripts(){
		System.out.println("Manuscripts submitted: ");
		for (String str : myConf.getMyManuscripts().keySet()){
			if (myConf.getMyManuscripts().get(str).getMyAuthorName().equals(myWhoAmI)){
				System.out.println(str);
			}
		}
	}
	
}
