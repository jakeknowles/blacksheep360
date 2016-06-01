package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.User;
	
/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/31/2016
 */
public class ReviewerUI {

	/**
	 * The conference that the user is logged into.
	 */
	private Conference myConf;
	/**
	 * The name of the user logged in to the current session.
	 */
	private String myName;
	/**
	 * The current date.
	 */
	private String currDateString;
	/**
	 * The input console.
	 */
	private Scanner console;
	/**
	 * The reviewer currently logged in.
	 */
	private Reviewer myReviewer;
	/**
	 * A list of manuscripts assigned to the logged in reviewer.
	 */
	private List<Manuscript> myAssignedManuscripts;

	/**
	 * @version 5/31/2016
	 */
	public ReviewerUI(HashMap<String, User> theUsers, Conference theConf, 
			String theWhoAmI, String theRole, String theCurrDateString, Scanner theConsole) {
		myConf = theConf;
		myName = theWhoAmI; 
		currDateString = theCurrDateString;
		console = theConsole;	
		myReviewer = theUsers.get(myName).getReviewer();
		myAssignedManuscripts = new ArrayList<Manuscript>(myReviewer.getManuscripts());
	}

	/**
	 * User interface for a reviewer to select actions. 
	 * 
	 * @version 5/8/2016
	 */
	public void reviewerInterface() {
		
		MSEEConfMgr.header(User.REVIEWER, myName, currDateString, myConf.getMyConfName());
		System.out.println("\nReviews Submitted:");
		viewMyReviews();
		
		System.out.println("\nSelect an action:");
		System.out.println("\t1. Submit a review");
		System.out.println("\t2. Exit");
		System.out.print("Enter a selection > ");
		int temp = console.nextInt();
		console.nextLine();
		System.out.println();
		switch (temp) {
		case 1:
			submitReview();
			break;
		case 2:
			System.out.println("Exiting - Goodbye!");
			break;

		}
	}
	
	/**
	 * User interface to submit a review.
	 * 
	 * @version 5/8/2016
	 */
	public void submitReview() {
		int back = myAssignedManuscripts.size() + 1;
		int exit = back + 1;
		
		MSEEConfMgr.header(User.REVIEWER, myName, currDateString, myConf.getMyConfName());
		System.out.println("\nReviews Submitted:");
		viewMyReviews();
		
		System.out.println("\nSelect the manuscript to review"); 
		//System.out.println("you wish to submit");
		//System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t" + back + ". Back");
		System.out.println("\t" + exit + ". Exit");
		System.out.print("> ");
		int selection = console.nextInt();
		console.nextLine();
		if (selection == back) {
			reviewerInterface(); 
		} else if (selection == exit) {
			System.out.println("Exiting - Goodbye!");
		} else {
			Manuscript selectedManuscript = myAssignedManuscripts.get(selection - 1);
			System.out.println("Enter the file path for the review form.");
			System.out.print("\n> ");
			
			String filePath = console.nextLine();
			File reviewForm = new File(filePath);
			
			boolean validScore = false;
			int rating = 0;
			while (!validScore) {
				System.out.println("\nPlease enter a score for your review, ");
				System.out.println("from " + myConf.getLowScore() + "(lowest) to " + myConf.getHighScore() + "(highest)");
				System.out.print("\n> ");
				rating = console.nextInt();
				console.nextLine();
				if (rating >= myConf.getLowScore() && rating <= myConf.getHighScore()) {
					validScore = true;
				}
			}
			
			try {
				myReviewer.submitReview(reviewForm, selectedManuscript, rating);
			} catch (IOException e) {
				System.out.println("File not found at location: " + filePath + "\n");
				submitReview();
			} 
			System.out.println("\nReview submitted for " + selectedManuscript.getMyTitle()); 
			submitReview();
		}
	}
	
	/**
	 * Displays the reviews assigned to the reviewer, and the score that the reviewer has given.
	 * @version 5/31/2016
	 */
	public void viewMyReviews(){ 
		if (myAssignedManuscripts.size() > 0) {
			for (int i = 0; i < myAssignedManuscripts.size(); i++) {
				System.out.print((i+1) + ". " + myAssignedManuscripts.get(i).getMyTitle() + ", ");
				if (myReviewer.hasReviewed(myAssignedManuscripts.get(i))) {
					System.out.println("My Score: " + myReviewer.getMyReviews().get(myAssignedManuscripts.get(i)).getMyRating());
				} else {
					System.out.println("not yet reviewed");
				}
			}
		} else {
			System.out.println("No manuscripts assigned.");
		}
	}

}