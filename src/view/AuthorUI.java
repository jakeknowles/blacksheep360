package view;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.User;

public class AuthorUI {
	
	private HashMap<String, User> myUsers;
	private Conference myConf;
	private String myName;
	private String myRole;
	private String currDateString;
	private Scanner console;


	public AuthorUI(HashMap<String, User> theUsers, Conference theConf, 
			String theWhoAmI, String theRole, String theCurrDateString, Scanner theConsole) {
		myUsers = theUsers; 
		myConf = theConf;
		myName = theWhoAmI; 
		myRole = theRole;
		currDateString = theCurrDateString;
		console = theConsole;	
	}
	

	/**
	 * displays the author interface and gets input.
	 * 
	 * @version 5/8/2016
	 */
	public void authorInterfaceHasManuscripts() {
		int temp = 0;
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a manuscript");
		System.out.println("\t2. Edit a manuscript");
		System.out.println("\t3. Remove a manuscript");
		System.out.println("\t4. Exit");
		System.out.print("Enter a selection > ");
		temp = console.nextInt();
		console.nextLine();
		System.out.println();
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
	 * User interface for a basic user.
	 * 
	 * @version 5/8/2016
	 */
	public void authorInterfaceNoManuscripts() {
		int temp = 0;
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
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
	public void submitManuscript() {
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		viewMyManuscripts();
		System.out.println("Enter the file path for the manuscript");
		System.out.println("you wish to upload");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String file = console.nextLine();
		System.out.println();
		if (file.equals("1")) {
			if (myUsers.get(myName).isRole(myRole)) {
				authorInterfaceHasManuscripts(); //GO TO AUTHOR
			} else {
				authorInterfaceNoManuscripts();
			}
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			File toBeSaved = new File(file);
			User me = myUsers.get(myName);
			System.out.print("Now enter the title of your submission \n> ");
			String paperName = console.nextLine();
			System.out.println();
			if (!me.isRole(User.AUTHOR)) {
				me.getMyRoles().myAuthor = new Author(me.getMyName());
			}
			Manuscript newManuscript = me.getMyRoles().myAuthor.submitManuscript(toBeSaved,
					myConf.getMyManuscriptDeadline(), paperName); // Alexandria, 5/15/16 - I made changes to Author and Manuscript to allow the user to specify the title.
			myConf.addManuscript(newManuscript);
			System.out.println(toBeSaved.length());
			System.out.println(paperName + " has been submitted to " + myConf.getMyConfName() + ".");
			myRole = "Author";
			submitManuscript();
		}
	}
	
	/**
	 * User interface for an author to change a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public void editManuscript() {
//		System.out.println("MSEE Conference Management");
////		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
////				substring(6, myUsers.get(myWhoAmI).getClass().
////						toString().length()) +" " + myWhoAmI);
//		System.out.println(myRole + " - " + myWhoAmI);
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		viewMyManuscripts();
		System.out.println("Enter the name of the manuscript"); 
		System.out.println("you wish to edit");
		System.out.println("ex. C:\\Documents\\example.doc"); 
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String paperName = console.nextLine(); 
		System.out.println();
		if (paperName.equals("1")) {
			authorInterfaceHasManuscripts(); //GO TO AUTHOR
		} else if (paperName.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			System.out.print("Please select what you would like to do: "
					+ "\n1. Change manuscript title"
					+ "\n2. Update manuscript file\n> ");
			int selection = console.nextInt();
			console.nextLine();
			System.out.println();
			switch (selection){
			case 1:
				System.out.print("Please enter the new title\n> ");
				String newTitle = console.nextLine();
				System.out.println();
				Manuscript manuscript = myConf.getMyManuscripts().get(paperName);
				myConf.getMyManuscripts().remove(paperName);
				manuscript.setMyTitle(newTitle);
				myConf.getMyManuscripts().put(newTitle, manuscript);
				System.out.println(paperName + "'s title has been changed to " + newTitle); //Alexandria, 5/22/16 - I changed this a bit because I saw some errors in its functionality.
				break;																		//Need to double check for problems from this.
			case 2:
				System.out.println("Please enter the file path of the updated manuscript file ");
				System.out.print("ex. C:\\Documents\\example.doc\n> ");
				String file = console.nextLine();
				System.out.println();
				File toBeSaved = new File(file);
				Manuscript change = myConf.getMyManuscripts().get(paperName);
				myUsers.get(myName).getMyRoles().myAuthor.editManuscriptFile(change, toBeSaved);
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
	public void removeManuscript() {
//		System.out.println("MSEE Conference Management");
////		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
////				substring(6, myUsers.get(myWhoAmI).getClass().
////						toString().length()) +" " + myWhoAmI);
//		System.out.println(myRole + " - " + myWhoAmI);
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		viewMyManuscripts();
		System.out.println("Enter the title of the manuscript");
		System.out.println("you wish to remove");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String file = console.nextLine();
		System.out.println();
		if (file.equals("1")) {
			authorInterfaceHasManuscripts(); //GO TO AUTHOR
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
//			System.out.println(toBeSaved.length());
			Manuscript toBeDeleted = myConf.getMyManuscripts().get(file);
			myConf.removeManuscript(file);
			myUsers.get(myName).getMyRoles().myAuthor.deleteManuscript(toBeDeleted);
			System.out.println(toBeDeleted.getMyTitle() + " has been removed."); //Alexandria, 5/15/16 - don't know if we need this. next time the menu rolls around it should show an updated list of their manuscripts.
			editManuscript();
		}
	}
	
	
	/**
	 * Prints a list of this user's submitted manuscripts.
	 * 
	 * @version 5/15/16
	 */
	public void viewMyManuscripts(){
		System.out.println("Manuscripts submitted: ");
		Author auth = myUsers.get(myName).getMyRoles().myAuthor;
		for (int i = 0; i < auth.getMyManuscripts().size(); i++) {
			System.out.println(auth.getMyManuscripts().get(i).getMyTitle());
		}
		
//		for (String str : myConf.getMyManuscripts().keySet()){
//			if (myConf.getMyManuscripts().get(str).getMyAuthorName().equals(myWhoAmI)){
//				System.out.println(str);
//			}
//		}
	}

}
