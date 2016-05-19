

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class AuthorUI {
	
	static HashMap<String, User> myUsers;
	static Conference myConf;
	static String myWhoAmI;
	static String myRole;
	static String currDateString;
	static Scanner console;


	public AuthorUI(HashMap<String, User> theUsers, Conference theConf, 
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
		System.out.println("Date: " + currDateString); //Alexandria, 5/15/16 - displays the current date
	}

	/**
	 * displays the author interface and gets input.
	 * 
	 * @version 5/8/2016
	 */
	public static void authorInterface(String theWhoIam) {
		int temp = 0;
//		System.out.println("MSEE Conference Management");
//		System.out.println("Author - " + theWhoIam);
//		System.out.println("Date: " + currDateString); //Alexandria, 5/15/16 - displays the current date
		header(theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a manuscript");
		System.out.println("\t2. Edit a manuscript");
		System.out.println("\t3. Remove a manuscript");
		System.out.println("\t4. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
		console.nextLine();
		switch (temp) {
		case 1:
			submitManuscript();
			break;
		case 2:
			editManuscript();
			break;
		case 3:
			removeManuscript();
			break;
		case 4:
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
		System.out.println("Enter the file path for the manuscript"); //Alexandria, 5/15/16 - should display a list of manuscripts here EDIT: done!
		System.out.println("you wish to upload");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = console.nextLine();
		if (file.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				authorInterface(myWhoAmI); //GO TO AUTHOR
			} else {
				UserUI uui = new UserUI(myUsers, myConf, myWhoAmI, myRole, 
						currDateString, console);
				uui.userInterface(myWhoAmI);
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
				me.myRoles.myAuthor = new Author(me.myName);
			}
			Manuscript newManuscript = me.myRoles.myAuthor.submitManuscript(toBeSaved,
					myConf.myManuscriptDeadline, paperName); // Alexandria, 5/15/16 - I made changes to Author and Manuscript to allow the user to specify the title.
			myConf.addManuscript(newManuscript);
			System.out.println(toBeSaved.length());
			System.out.println("SUCCESS!");
			myRole = "Author";
			submitManuscript();
		}
	}
	
	/**
	 * User interface for an author to change a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public static void editManuscript() {
//		System.out.println("MSEE Conference Management");
////		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
////				substring(6, myUsers.get(myWhoAmI).getClass().
////						toString().length()) +" " + myWhoAmI);
//		System.out.println(myRole + " - " + myWhoAmI);
		header(myWhoAmI);
		viewMyManuscripts();//Alexandria, 5/15/16 - added this to print manuscript titles available for editing
		System.out.println("Enter the name of the manuscript"); //Alexandria, 5/17/16 - should be just name EDIT: done!
		System.out.println("you wish to edit");
		System.out.println("ex. C:\\Documents\\example.doc"); //Alexandria, 5/15/16 - we need a submenu to ask them if they want to change the title, or upload an updated manuscript EDIT: done!
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String paperName = console.nextLine(); 
		if (paperName.equals("1")) {
			authorInterface(myWhoAmI); //GO TO AUTHOR
		} else if (paperName.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			System.out.println("Please select what you would like to do: "
					+ "\n1. Change manuscript title"
					+ "\n2. Update manuscript file");
			int selection = console.nextInt();
			switch (selection){
			case 1:
				System.out.println("Please enter the new title: ");
				String newTitle = console.nextLine();
				myConf.myManuscripts.get(paperName).myTitle = newTitle;
				System.out.println(paperName + "'s title has been changed to " + newTitle);
				break;
			case 2:
				System.out.println("Please enter the file path of the updated manuscript file: ");
				System.out.println("ex. C:\\Documents\\example.doc");
				String file = console.nextLine();
				File toBeSaved = new File(file);
				Manuscript change = myConf.myManuscripts.get(toBeSaved.getName());
				myUsers.get(myWhoAmI).myRoles.myAuthor.editManuscript(change, toBeSaved);
				System.out.println("File submission successful.");
				break;
			}
			editManuscript();
		}
	}
	
	/**
	 * User interface to remove a submitted manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public static void removeManuscript() {
//		System.out.println("MSEE Conference Management");
////		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
////				substring(6, myUsers.get(myWhoAmI).getClass().
////						toString().length()) +" " + myWhoAmI);
//		System.out.println(myRole + " - " + myWhoAmI);
		header(myWhoAmI);
		viewMyManuscripts();
		System.out.println("Enter the title for the manuscript");
		System.out.println("you wish to remove");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = console.nextLine();
		if (file.equals("1")) {
			authorInterface(myWhoAmI); //GO TO AUTHOR
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
//			System.out.println(toBeSaved.length());
			Manuscript toBeDeleted = myConf.myManuscripts.get(file);
			myConf.removeManuscript(file);
			myUsers.get(myWhoAmI).myRoles.myAuthor.deleteManuscript(toBeDeleted);
			System.out.println("SUCCESS!"); //Alexandria, 5/15/16 - don't know if we need this. next time the menu rolls around it should show an updated list of their manuscripts.
			editManuscript();
		}
	}
	
	
	/**
	 * Prints a list of this user's submitted manuscripts.
	 * 
	 * @version 5/15/16
	 */
	public static void viewMyManuscripts(){
		System.out.println("Manuscripts submitted: ");
		for (String str : myConf.myManuscripts.keySet()){
			if (myConf.myManuscripts.get(str).myAuthorName.equals(myWhoAmI)){
				System.out.println(str);
			}
		}
	}

}
