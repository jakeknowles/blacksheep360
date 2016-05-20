

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class SubProgramChairUI {

	static HashMap<String, User> myUsers;
	static Conference myConf;
	static String myWhoAmI;
	static String myRole;
	static String currDateString;
	static Scanner console;


	public SubProgramChairUI(HashMap<String, User> theUsers, Conference theConf, 
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
			System.out.println("Please select the manuscript number you wish to assign:");
			int i = 1;
			//Alexandria, 5/15/16 - I don't see where a number is being displayed. Need to fix that. Also, show manuscripts already assigned to this reviewer.
			for (Manuscript manuscript: myUsers.get(myWhoAmI).myRoles.mySubProgramChair.getManuscripts()){
				System.out.println(i + ". " + manuscript.myTitle);
				i++;
			}
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
