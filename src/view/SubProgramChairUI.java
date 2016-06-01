package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.SubProgramChair;
import model.User;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/31/2016
 */
public class SubProgramChairUI {

	/**
	 * A map of names to user objects
	 */
	private HashMap<String, User> myUsers;
	/**
	 * a conference
	 */
	private Conference myConf;
	/**
	 * the name of the user logged in to this session.
	 */
	private String myName;
	/**
	 * The current date
	 */
	private String currDateString;
	/**
	 * The input consoles.
	 */
	private Scanner console;
	
	/**
	 * The logged in subprogram chair.
	 */
	private SubProgramChair mySubChair;


	public SubProgramChairUI(HashMap<String, User> theUsers, Conference theConf, 
			String theWhoAmI, String theRole, String theCurrDateString, Scanner theConsole) {
		myUsers = theUsers; 
		myConf = theConf;
		myName = theWhoAmI; 
		currDateString = theCurrDateString;
		console = theConsole;	
		mySubChair = myUsers.get(myName).getSubProgramChair();
	}
	
	
	/**
	 * User interface for a subprogram chair to select actions.
	 * 
	 * @version 5/8/2016
	 */
	public void subpcInterface() {
		MSEEConfMgr.header(User.SUBPROGRAM_CHAIR, myName, currDateString, myConf.getMyConfName());
		viewMyManuscripts();
		System.out.println("\nSelect an action:");
		System.out.println("\t1. Assign a reviewer to a paper");
		System.out.println("\t2. Submit recommendation");
		System.out.println("\t3. Exit");
		System.out.println("Enter a selection");
		System.out.print("> ");
		int temp = console.nextInt();
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
			break;
		}
	}
	
	/**
	 * user interface for a subprogram chair to assign a reviewer to a manuscript.
	 * 
	 * @version 5/31/2016
	 */
	public void assignReviewer() {
		List<String> users = new ArrayList<String>(myUsers.keySet());
		List<String> reviewers = getReviewers(users);
		int back = reviewers.size() + 1;
		int exit = back + 1;
		
		MSEEConfMgr.header(User.SUBPROGRAM_CHAIR, myName, currDateString, myConf.getMyConfName());
		System.out.println("Reviewers: ");
		displayReviewers(users);
		System.out.println("\nSelect the reviewer you want to assign."); 
		System.out.println("\n\t- OR -");
		System.out.println("\t" + back + ". Back");
		System.out.println("\t" + exit + ". Exit");
		System.out.println("> ");
		int name = console.nextInt();
		console.nextLine();
		if (name == back) {
			subpcInterface();
		} else if (name == exit) {
			System.out.println("Exiting - Goodbye!");
		} else {
			User selectedReviewer = myUsers.get(reviewers.get(name - 1));
			System.out.println(reviewers.get(name -1) + " reviews: ");
			if (selectedReviewer.isRole(User.REVIEWER)) {
				for (Manuscript m : selectedReviewer.getReviewer().getManuscripts()) {
					System.out.println("\t" + m.getMyTitle());
				}
			} else {
				System.out.println("\tnone");
			}
			//Show a list (with numbers) of papers with exception of those authored by that reviewer
			
			back = mySubChair.getManuscripts().size() + 1;
			exit = back + 1;
			System.out.println("\nManuscripts available to assign:");
			for (int i = 1; i <= mySubChair.getManuscripts().size(); i++) {
				System.out.println("\t" + i + ". " + mySubChair.getManuscripts().get(i - 1).getMyTitle());
			}
			
			//Select a paper number
			//Attach selected paper to selected reviewer			
			System.out.println("Select the manuscript you wish to assign:");
			System.out.println("\n\t- OR -");
			System.out.println("\t" + back + ". Back");
			System.out.println("\t" + exit + ". Exit");
			System.out.println("> ");
			int selection = console.nextInt(); 
			console.nextLine();
			if (selection == back) {
				assignReviewer();
			} else if (selection == exit) {
				System.out.println("Exiting - Goodbye!");
			} else {
				Manuscript selectedManuscript = mySubChair.getManuscripts().get(selection - 1);
				if (selectedReviewer.getReviewer().hasReviewed(selectedManuscript)) {
					System.out.println("Reviewer is already assigned to this manuscript.");
					assignReviewer();
				}
				boolean result = mySubChair.assignReviewer(selectedManuscript, selectedReviewer);
				if (result) {
					System.out.println(selectedManuscript.getMyTitle() + " assigned to " + reviewers.get(name -1));
				} else {
					System.out.println("FAILED!");
				}
				assignReviewer();
			}
		}
	}
		
	/**
	 * User interface to submit a recommendation.
	 * 
	 * @version 5/31/2016
	 */
	public void submitRecommendation() {
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		
		int back = mySubChair.getManuscripts().size() + 1;
		int exit = back + 1;
		viewMyManuscripts();
		
		System.out.println("Select the manuscript to submit a recommendation for:");
		System.out.println("\n\t- OR -");
		System.out.println("\t" + back + ". Back");
		System.out.println("\t" + exit + ". Exit");
		System.out.println("> ");
		int choice = console.nextInt();
		console.nextLine();
		if (choice == back) {
			subpcInterface();
		} else if (choice == exit) {
			System.out.println("Exiting - Goodbye!");
		} else {
			System.out.print("you are making a recommendation for: ");
			System.out.println(mySubChair.getManuscripts().get(choice -1).getMyTitle());
			System.out.println("Enter the file path for the recommendation form: ");
			System.out.print("> ");
			String filePath = console.nextLine();
			File recommendationFile = new File(filePath);
				
			System.out.println("Enter a recommendation statement:");
			System.out.print("> ");
			String statement = console.nextLine();
			try {
				mySubChair.submitRecommendation(mySubChair.getManuscripts().get(choice - 1), recommendationFile, statement);
			} catch (IOException e) {
				System.out.println("File not found at location: " + filePath + "\n");
				submitRecommendation();
			}
			System.out.println("Recommendation made for " + mySubChair.getManuscripts().get(choice - 1).getMyTitle());
			submitRecommendation();
		}
	}
	
	/**
	 * displays a list of reviewers.
	 * @version 5/31/2016
	 */
	public void displayReviewers(List<String> theUsers) {
		int j = 1;
		for (int i = 0; i < theUsers.size(); i++) {
			if (myUsers.get(theUsers.get(i)).isRole(User.REVIEWER)) {
				System.out.println((j++) + ". " + theUsers.get(i));
			}
		}
	}
	
	/**
	 * Gets a list of all assigned reviewers.
	 * @version 5/31/2016
	 */
	public List<String> getReviewers(List<String> theUsers) {
		List<String> reviewers = new ArrayList<String>();
		for (int i = 0; i < theUsers.size(); i++) {
			 if (myUsers.get(theUsers.get(i)).isRole(User.REVIEWER)) {
				 reviewers.add(theUsers.get(i));
			 }
		}
		return reviewers;
	}
	
	/**
	 * Displays a list of all manuscripts assigned to the subprogram chair.
	 * @version 5/30/2016
	 */
	public void viewMyManuscripts() {
		System.out.println("\nManuscripts assigned:");
		for (int i = 1; i <= mySubChair.getManuscripts().size(); i++) {
			Manuscript man = mySubChair.getManuscripts().get(i - 1);
			System.out.print("\t" + i + ". " + man.getMyTitle());
			if (man.getMyRecommendation() != null) {
				System.out.println(" - " + man.getMyRecommendation().getStatement());
			} else {
				System.out.println(" - No recommendation yet");
			}
			
			System.out.print("\tReview scores: ");
			if (!man.getMyReviews().isEmpty()) {
				System.out.print(man.getMyReviews().get(0).getMyRating());
				for (int j = 1; j < man.getMyReviews().size(); j++) {
					System.out.print(", " + man.getMyReviews().get(0).getMyRating());
				}
				System.out.println();
			} else {
				System.out.println("no reviews");
			}
		}
	}
	
}
