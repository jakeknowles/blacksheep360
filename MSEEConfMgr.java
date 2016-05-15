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
			authorInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.PROGRAM_CHAIR)) {
			pcInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.REVIEWER)) {
			reviewerInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals(User.SUBPROGRAM_CHAIR)) {
			subpcInterface(myWhoAmI);
		} else {
			myRole = "User";
			userInterface(myWhoAmI);
		}
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
	 * displays the program chair interface and reads user input.
	 * 
	 * @version 5/8/2016
	 */
	public static void pcInterface(String theWhoIam) {
		int temp = 0;
		System.out.println();
//		System.out.println("MSEE Conference Management");
//		System.out.println("Program Chair - " + theWhoIam);
//		System.out.println("Date: " + currDateString);
		header(theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. View list of submitted manuscripts.");
		System.out.println("\t2. Make acceptance decision on manuscripts.");
		System.out.println("\t3. View manuscript assignments to Subprogram Chairs.");
		System.out.println("\t4. Assign a Subprogram Chair to a manuscript.");
		System.out.println("\t5. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
		console.nextLine();
		switch (temp) {
		case 1:
			System.out.println(myUsers.get(theWhoIam).myRoles.myProgramChair.viewManuscripts(myConf.myManuscripts)); //Alexandria, 5/15/16 - can we put view manuscripts in a method so it can be called from acceptance()?
			pcInterface(myWhoAmI);
			break;
		case 2:
			acceptance();
			break;
		case 3:
			for (String s : myUsers.keySet()) {
				if (myUsers.get(s).isRole(User.SUBPROGRAM_CHAIR)) { 	//Alexandria, 5/15/16 - same goes for this code inside the for each loop. Could use it for assignSPC()
					System.out.println(s + ":");
					System.out.println(myUsers.get(s).myRoles.mySubProgramChair.getManuscripts());
				}
			}
			pcInterface(myWhoAmI);
			break;
		case 4:
			assignSPC();
			break;
		case 5:
			System.out.println("Exiting - Goodbye!");
			//serial();
			break;
		}
	}

	/**
	 * User interface for assigning a subprogram chair to a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public static void assignSPC() {
//		System.out.println("MSEE Conference Management");
//		System.out.println("Program Chair - " + myWhoAmI);
		header(myWhoAmI);
		System.out.println("Enter the name of the subprogram chair you want to assign to a manuscript."); //Alexandria, 5/15/16 - display list of subprogram chairs here
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String name = console.nextLine();
		if (name.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				pcInterface(myWhoAmI); //GO TO AUTHOR
			}
		} else if (name.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			System.out.println("Please enter the name of the manuscript you wish to assign:"); //Alexandria, 5/15/16 - prior to this, print out the manuscripts already assigned to this SPC
			for (String str : myConf.myManuscripts.keySet()) {
				if (!myConf.myManuscripts.get(str).myAuthorName.equals(name)) {
					System.out.println(myConf.myManuscripts.get(str));
				}
			}
			System.out.println("> ");
			String selection = console.nextLine();
			boolean result = myUsers.get(myWhoAmI).myRoles.myProgramChair.assignManuscripts(myUsers.get(name), myConf.myManuscripts.get(selection));
			if (result) {
				System.out.println("SUCCESS!"); //Alexandria, 5/15/16 - this should either display "ExampleSPC has been assigned SamplePaperName" or a list of SPCs and their assigned papers.
			} else {
				System.out.println("FAILED!");
			}
			assignSPC();
		}
	}
	
	/**
	 * User interface for a program chair to accept a paper.
	 * 
	 * @version 5/8/2016
	 */
	public static void acceptance() {
//		System.out.println("MSEE Conference Management");
//		System.out.println("Program Chair - " + myWhoAmI);
		header(myWhoAmI);
		for (String str : myConf.myManuscripts.keySet()) { //Alexandria, 5/15/16 - if we put view manuscripts into a method we could call that here, unless we wanted to not show already accepted manuscripts.
			System.out.print(str + " ");
			System.out.println(myConf.myManuscripts.get(str));
		}
		System.out.println("Enter the title of the "
				+ "manuscript you want to accept/reject."); //Alexandria, 5/15/16 - problem here: we're telling them they can accept and reject, but when they input a name the system "accepts" it. Need group input on this one.
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String input = console.nextLine();
		if (input.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				pcInterface(myWhoAmI); //GO TO AUTHOR
			}
		} else if (input.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			myUsers.get(myWhoAmI).myRoles.myProgramChair.submitDecision(myConf.myManuscripts.get(input), true);
			System.out.println("SUCCESS!"); //Alexandria, 5/15/16 - "nameOfPaper has been accepted" or something like that
			acceptance();
		}
	}
	
	/**
	 * User interface for a subprogram chair to select actions.
	 * 
	 * @version 5/8/2016
	 */
	public static void subpcInterface(String theWhoIam) {
		int temp = 0;
		System.out.println();
//		System.out.println("MSEE Conference Management");
//		System.out.println("Subprogram Chair - " + theWhoIam);
//		System.out.println("Date: " + currDateString);
		header(theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. Assign a reviewer to a paper");
		System.out.println("\t2. Submit recommendation");
		System.out.println("\t3. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
		console.nextLine();
		switch (temp) {
		case 1:
			assignReviewer();
			break;
		case 2:
			submitRecommendation();
			break;
		case 3:
			System.out.println("Exiting - Goodbye!");
			//serial();
			break;
		}
	}
	

	/**
	 * user interface for a subprogram chair to assign a reviewer to a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public static void assignReviewer() {
//		System.out.println("MSEE Conference Management");
//		System.out.println("Subprogram Chair - " + myWhoAmI);
		header(myWhoAmI);
		System.out.println("Enter the name of the reviewer you want to assign."); //Alexandria, 5/15/16 - print list of reviewers here
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String name = console.nextLine();
		if (name.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				subpcInterface(myWhoAmI); //GO TO AUTHOR
			}
		} else if (name.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			//Show a list (with numbers) of papers with exception of those authored by that reviewer
			//Select a paper number
			//Attach selected paper to selected reviewer			
			System.out.println("Please select the manuscript number you wish to assign:"); //Alexandria, 5/15/16 - I don't see where a number is being displayed. Need to fix that. Also, show manuscripts already assigned to this reviewer.
			System.out.println(myUsers.get(myWhoAmI).myRoles.mySubProgramChair.getManuscripts());
			System.out.println("> ");
			int selection = console.nextInt(); //Alexandria, 5/15/16 - should have a 0 as an option to return to reviewer selection instead of proceeding
			console.nextLine();
			SubProgramChair me = myUsers.get(myWhoAmI).myRoles.mySubProgramChair;
			boolean result = me.assignReviewer(me.myManuscripts.get(selection - 1), myUsers.get(name));
			if (result) {
				System.out.println("SUCCESS!");//Alexandria, 5/15/16 - "paperName assigned to reviewerName"
			} else {
				System.out.println("FAILED!");
			}
			assignReviewer();
		}
	}
	
	/**
	 * User interface for a reviewer to select actions. 
	 * 
	 * @version 5/8/2016
	 */
	public static void reviewerInterface(String theWhoIam) {
		int temp = 0;
		System.out.println();
//		System.out.println("MSEE Conference Management");
//		System.out.println("Reviewer - " + theWhoIam);
//		System.out.println("Date: " + currDateString);
		header(theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a review");
		System.out.println("\t2. View Submitted Reviews");
		System.out.println("\t3. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
		console.nextLine();
		switch (temp) {
		case 1:
			submitReview();
			break;
		case 2:		//Alexandria, 5/15/16 - we need this in order to do submitReview()
			break;
		case 3:
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
		System.out.println("Enter a selection");
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
	
	public static void viewMyManuscripts(){
		System.out.println("Manuscripts submitted: ");
		for (String str : myConf.myManuscripts.keySet()){
			if (myConf.myManuscripts.get(str).myAuthorName.equals(myWhoAmI)){
				System.out.println(str);
			}
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
		System.out.println("Enter the file path for the manuscript"); //Alexandria, 5/15/16 - should display a list of manuscripts here
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
				me.myRoles.myAuthor = new Author(me.myName);
			}
			Manuscript newManuscript = me.myRoles.myAuthor.submitManuscript(toBeSaved, myConf.myManuscriptDeadline, paperName); // Alexandria, 5/15/16 - I made changes to Author and Manuscript to allow the user to specify the title.
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
		System.out.println("Enter the file path and name for the manuscript"); //A
		System.out.println("you wish to edit");
		System.out.println("ex. C:\\Documents\\example.doc"); //Alexandria, 5/15/16 - we need a submenu to ask them if they want to change the title, or upload an updated manuscript
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
			File toBeSaved = new File(file);
			Manuscript change = myConf.myManuscripts.get(toBeSaved.getName());
			myUsers.get(myWhoAmI).myRoles.myAuthor.editManuscript(change, toBeSaved);
			System.out.println("SUCCESS!");
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
	 * User interface to submit a review.
	 * 
	 * @version 5/8/2016
	 */
	public static void submitReview() {
//		System.out.println("MSEE Conference Management");
////		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
////				substring(6, myUsers.get(myWhoAmI).getClass().
////						toString().length()) +" " + myWhoAmI);
//		System.out.println(myRole + " - " + myWhoAmI);
		header(myWhoAmI);
		System.out.println("Enter the file path for the review"); //Alexandria, 5/15/16 - need to ask for number rating as well as show submitted reviews
		System.out.println("you wish to submit");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = console.nextLine();
		if (file.equals("1")) {
			reviewerInterface(myWhoAmI); //GO TO AUTHOR
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			System.out.println("Please select the manuscript number you are reviewing:");
			Reviewer me = myUsers.get(myWhoAmI).myRoles.myReviewer;
			System.out.println(me.getManuscripts());
			System.out.println("> ");
			int selection = console.nextInt();
			console.nextLine();
			Manuscript man = me.myManuscripts.get(selection - 1);
			me.addManuscript(man, myUsers.get(myWhoAmI).myName);
			System.out.println("SUCCESS!"); //Alexandria, 5/15/16 - "review submitted for exampleManuscript"
			submitReview();
		}
	}
	
	/**
	 * User interface to submit a recommendation.
	 * 
	 * @version 5/8/2016
	 */
	public static void submitRecommendation() {
//		System.out.println("MSEE Conference Management");
////		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
////				substring(6, myUsers.get(myWhoAmI).getClass().
////						toString().length()) +" " + myWhoAmI);
//		System.out.println(myRole + " - " + myWhoAmI);
		header(myWhoAmI);
		System.out.println("Enter the file path and name for the recommendation");//Alexandria, 5/15/16 - show recommendations already made
		System.out.println("you wish to submit");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = console.nextLine();
		if (file.equals("1")) {
			subpcInterface(myWhoAmI); //GO TO AUTHOR
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
//			System.out.println(toBeSaved.length());
			SubProgramChair me = myUsers.get(myWhoAmI).myRoles.mySubProgramChair;
			System.out.println("Please select the manuscript number ");
			System.out.println("you are making a recommendation for:");
			System.out.println(me.getManuscripts());
			System.out.println("> ");
			int selection = console.nextInt();
			console.nextLine();
			me.submitRecommendation(me.myManuscripts.get(selection - 1), new File(file));
			System.out.println("SUCCESS!");
			submitRecommendation();
		}
	}
	
}
