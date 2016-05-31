package view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.User;
	
public class ReviewerUI {

	private Conference myConf;
	private String myName;
	private String currDateString;
	private Scanner console;
	private Reviewer myReviewer;
	private List<Manuscript> myAssignedManuscripts;

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
		
		System.out.println();
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
			File reviewForm = null;
			System.out.println("Enter the file path for the review form.");
			System.out.print("\n> ");
			
			String filePath = console.nextLine();
			try {
				reviewForm = new File(filePath);
			} catch (NullPointerException e) {
				System.out.println("File not found at location: " + filePath + "\n");
				submitReview();
			}
			
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
			
			myReviewer.submitReview(reviewForm, selectedManuscript, rating); 
			System.out.println("\nReview submitted for " + selectedManuscript.getMyTitle()); 
			submitReview();
		}
	}
	
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