

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
	
public class ReviewerUI {

	static HashMap<String, User> myUsers;
	static Conference myConf;
	static String myWhoAmI;
	static String myRole;
	static String currDateString;
	static Scanner console;

	public ReviewerUI(HashMap<String, User> theUsers, Conference theConf, 
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
		case 2:		//Alexandria, 5/15/16 - we need this in order to do submitReview() EDIT: done!
			viewMyReviews();
			break;
		case 3:
			System.out.println("Exiting - Goodbye!");
			//serial();
			break;

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
		System.out.println("Reviews Submitted:");
		viewMyReviews();										  //Alexandria, 5/17/16 - not working currently
		System.out.println("Enter the file path for the review"); //Alexandria, 5/15/16 - need to ask for number rating as well as show submitted reviews EDIT: now asks for number rating
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
			System.out.println("Please enter the numeric score of your review: "); //TODO Alexandria, 5/17/16 - We need a max score value stored somewhere. Also, wording help please.
			int rating = console.nextInt();
			System.out.println("Please select the manuscript number you are reviewing: ");
			Reviewer me = myUsers.get(myWhoAmI).myRoles.myReviewer;
			for (int i = 0; i < me.getManuscripts().size(); i++){
				System.out.println((i+1) + me.myManuscripts.get(i).myTitle); //Alexandria, 5/19/16 - this should now print the manuscript titles along with numbers
			}
			//System.out.println(me.getManuscripts());
			System.out.println("> ");
			int selection = console.nextInt();
			console.nextLine();
			Manuscript man = me.myManuscripts.get(selection - 1);
			me.submitReview(new File(file), man, rating); //Alexandria, 5/17/16 - added a rating to the review class
			//me.addManuscript(man, myUsers.get(myWhoAmI).myName); //Alexandria, 5/17/16 - shouldn't this be calling me.submitReview(new File(file), man)?
			System.out.println("Review submitted for " + man.myTitle); //Alexandria, 5/15/16 - "review submitted for exampleManuscript"
			submitReview();
		}
	}
	
	public static void viewMyReviews(){ //Alexandria, 5/19/16 - I had to make small changes to Review and Reviewer to make this work
		for (Review r : myUsers.get(myWhoAmI).myRoles.myReviewer.myReview){
			System.out.println("Manuscript title: " + r.myReviewedManuscriptTitle + "\nRating: " + r.myRating + "\n");
		}
	}

}