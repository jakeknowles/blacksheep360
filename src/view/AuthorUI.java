package view;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.PastDeadlineException;
import model.User;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/31/2016
 */
public class AuthorUI {
	
	/**
	 * A map of users.
	 */
	private HashMap<String, User> myUsers;
	/**
	 * The conference currently logged into.
	 */
	private Conference myConf;
	/**
	 * the name of the currently logged in user.
	 */
	private String myName;
	/**
	 * The role of the currently logged in user.
	 */
	private String myRole;
	/**
	 * The current date.
	 */
	private String currDateString;
	/**
	 * The input console.
	 */
	private Scanner console;

	/**
	 * @version 5/8/2016
	 */
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
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a manuscript");
		System.out.println("\t2. Edit a manuscript");
		System.out.println("\t3. Remove a manuscript");
		System.out.println("\t4. Exit");
		System.out.print("Enter a selection > ");
		int selection = console.nextInt();
		console.nextLine();
		System.out.println();
		switch (selection) {
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
			break;

		}
	}
	
	/**
	 * User interface for a basic user.
	 * 
	 * @version 5/8/2016
	 */
	public void authorInterfaceNoManuscripts() {
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a manuscript");
		System.out.println("\t2. Exit");
		System.out.println("Enter a selection > ");
		int selection = console.nextInt();
		console.nextLine();
		switch (selection) {
			case 1:
				submitManuscript();
				break;
			case 2:
				System.out.println("Exiting - Goodbye!");
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
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy"); 	
		System.out.println("\nThe deadline for manuscript submission is : " 
							+ dateFormat.format(myConf.getMyManuscriptDeadline()) + "\n");
		System.out.println("Enter the file path for the manuscript");
		System.out.println("you wish to upload");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String filePath = console.nextLine();
		System.out.println();
		if (filePath.equals("1")) {
			if (myUsers.get(myName).isRole(myRole)) {
				authorInterfaceHasManuscripts(); //GO TO AUTHOR
			} else {
				authorInterfaceNoManuscripts();
			}
		} else if (filePath.equals("2")) {
			System.out.println("Exiting - Goodbye!");
		} else {
			File toBeSaved = null;
			toBeSaved = new File(filePath);
			
			User me = myUsers.get(myName);
			System.out.print("Now enter the title of your submission \n> ");
			String paperName = console.nextLine();
			System.out.println();
			boolean newAuthor = false;
			if (!me.isRole(User.AUTHOR)) {
				me.assignAuthor(new Author(me.getMyName()));
				newAuthor = true;
			}
			Manuscript newManuscript = null;
			try {
				System.out.println(paperName);
				System.out.println(toBeSaved.getPath());
				System.out.println(myConf.getMyManuscriptDeadline().toString());
				newManuscript = me.getAuthor().submitManuscript(toBeSaved,
						myConf.getMyManuscriptDeadline(), paperName);
			} catch (PastDeadlineException e) {
				System.out.println("Submission Failed, deadline for manuscript submissions has passed.");
				if(newAuthor) {
					me.assignAuthor(null);
					authorInterfaceNoManuscripts();
				} else {
					authorInterfaceHasManuscripts();
				}
			} catch (IOException e) {
				System.out.println("File not found at location: " + filePath + "\n");
				submitManuscript();
			}
			myConf.addManuscript(newManuscript);
			System.out.println(paperName + " has been submitted to " + myConf.getMyConfName() + ".");
			myRole = User.AUTHOR;
			submitManuscript();
		}
	}
	
	/**
	 * User interface for an author to change a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public void editManuscript() {
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		Author auth = myUsers.get(myName).getAuthor();
		viewMyManuscripts();
		int back = auth.getMyManuscripts().size() + 1;
		int exit = back + 1;
		
		System.out.println("Select the manuscript you wish to edit"); 
		System.out.println("\n\t- OR -");
		System.out.println("\t" + back + ". Back");
		System.out.println("\t" + exit + ". Exit");
		System.out.print("> ");
		int selection = console.nextInt();
		console.nextLine(); 
		System.out.println();
		if (selection == back) {
			authorInterfaceHasManuscripts(); //GO TO AUTHOR
		} else if (selection == exit) {
			System.out.println("Exiting - Goodbye!");
		} else {
			Manuscript selectedManuscript = auth.getMyManuscripts().get(selection - 1);
			System.out.print("Please select what you would like to do: "
					+ "\n1. Change manuscript title"
					+ "\n2. Change manuscript file\n> ");
			selection = console.nextInt();
			console.nextLine();
			System.out.println();
			switch (selection){
			case 1:
				String oldTitle = selectedManuscript.getMyTitle();
				System.out.print("Please enter the new title\n> ");
				String newTitle = console.nextLine();
				System.out.println();
				myConf.editManuscriptTitle(oldTitle, newTitle);
				System.out.println(oldTitle + " has been changed to: " + newTitle); 
				break;																		
			case 2:
				System.out.println("Please enter the file path of the updated manuscript file ");
				System.out.print("ex. C:\\Documents\\example.doc\n> ");
				String filePath = console.nextLine();
				System.out.println();
				File toBeSaved = new File(filePath);
				try {
					myUsers.get(myName).getAuthor().editManuscriptFile(selectedManuscript, toBeSaved);
				} catch (IOException e) {
					System.out.println("File not found at location: " + filePath + "\n");
					editManuscript();
				}
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
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		Author auth = myUsers.get(myName).getAuthor();
		viewMyManuscripts();
		int back = auth.getMyManuscripts().size() + 1;
		int exit = back + 1;
		
		System.out.println("Select the manuscript you wish to remove");
		System.out.println("\n\t- OR -");
		System.out.println("\t" + back + ". Back");
		System.out.println("\t" + exit + ". Exit");
		System.out.print("> ");
		int selection = console.nextInt();
		console.nextLine(); 
		System.out.println();
		if (selection == back) {
			authorInterfaceHasManuscripts(); //GO TO AUTHOR
		} else if (selection == exit) {
			System.out.println("Exiting - Goodbye!");
		} else {
			Manuscript toBeDeleted = auth.getMyManuscripts().get(selection - 1);
			myConf.removeManuscript(toBeDeleted.getMyTitle());
			myUsers.get(myName).getAuthor().deleteManuscript(toBeDeleted);
			System.out.println(toBeDeleted.getMyTitle() + " has been removed."); 
			editManuscript();
		}
	}
	
	
	/**
	 * Prints a list of this user's submitted manuscripts.
	 * 
	 * @version 5/15/16
	 */
	public void viewMyManuscripts(){
		if(myUsers.get(myName).isRole(User.AUTHOR)) {
			System.out.println("Manuscripts submitted: ");
			Author auth = myUsers.get(myName).getAuthor();
			for (int i = 0; i < auth.getMyManuscripts().size(); i++) {
				Manuscript man = auth.getMyManuscripts().get(i);
				System.out.print((i + 1) + ". " + man.getMyTitle());
				
				
				if (myConf.getMyManuscriptDeadline().before(new Date())) {
					System.out.print(", Reviews: ");
					if (!man.getMyReviews().isEmpty()) {
						System.out.print(man.getMyReviews().get(0).getMyRating());
						for (int j = 1; j < man.getMyReviews().size(); j++) {
							System.out.print(", " + man.getMyReviews().get(0).getMyRating());
						}
					} else {
						System.out.print("no reviews");
					}
					System.out.print(", Approval Status: ");
					switch (man.getMyApproval()){
					case NO_DECISION:
						System.out.print("Undecided");
						break;
					case REJECTED:
						System.out.print("Rejected");
						break;
					case ACCEPTED:
						System.out.print("Accepted");
						break;
					}
				}
				System.out.println();
			}
		}
	}

}
