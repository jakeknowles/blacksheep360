package view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.SubProgramChair;
import model.User;

public class SubProgramChairUI {

	private HashMap<String, User> myUsers;
	private Conference myConf;
	private String myName;
	private String myRole;
	private String currDateString;
	private Scanner console;
	private SubProgramChair mySubChair;


	public SubProgramChairUI(HashMap<String, User> theUsers, Conference theConf, 
			String theWhoAmI, String theRole, String theCurrDateString, Scanner theConsole) {
		myUsers = theUsers; 
		myConf = theConf;
		myName = theWhoAmI; 
		myRole = theRole;
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
		System.out.println();
		MSEEConfMgr.header(User.SUBPROGRAM_CHAIR, myName, currDateString, myConf.getMyConfName());
		System.out.println("Select an action:");
		System.out.println("\t1. Assign a reviewer to a paper");
		System.out.println("\t2. Submit recommendation");
		System.out.println("\t3. Exit");
		System.out.println("Enter a selection");
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
	 * @version 5/8/2016
	 */
	public void assignReviewer() {
		List<String> users = new ArrayList<String>(myUsers.keySet());
		int back = users.size() + 1;
		int exit = back + 1;
		
		MSEEConfMgr.header(User.SUBPROGRAM_CHAIR, myName, currDateString, myConf.getMyConfName());
		displayUsers(users);
		System.out.println("\nSelect the reviewer you want to assign."); 
		System.out.println("\n\t- OR -");
		System.out.println("\t" + back + ". Back");
		System.out.println("\t" + exit + ". Exit");
		System.out.println("> ");
		int name = console.nextInt();
		console.nextLine();
		if (name == back) {
			if (myUsers.get(myName).isRole(myRole)) {
				subpcInterface();
			}
		} else if (name == exit) {
			System.out.println("Exiting - Goodbye!");
		} else {
			User selectedReviewer = myUsers.get(users.get(name - 1));
			System.out.println(users.get(name -1) + " reviews: ");
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
				Manuscript selectedManuscript = mySubChair.getMyManuscripts().get(selection - 1);
				if (selectedReviewer.getReviewer().hasReviewed(selectedManuscript)) {
					System.out.println("Reviewer is already assigned to this manuscript.");
					assignReviewer();
				}
				boolean result = mySubChair.assignReviewer(selectedManuscript, selectedReviewer);
				if (result) {
					System.out.println(selectedManuscript.getMyTitle() + "assigned to " + users.get(name -1));
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
	 * @version 5/8/2016
	 */
	public void submitRecommendation() {
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		
		int back = mySubChair.getManuscripts().size() + 1;
		int exit = back + 1;
		System.out.println("\nManuscripts assigned:");
		for (int i = 1; i <= mySubChair.getManuscripts().size(); i++) {
			System.out.print("\t" + i + ". " + mySubChair.getManuscripts().get(i - 1).getMyTitle());
			if (mySubChair.getManuscripts().get(i - 1).getMyRecommendation() != null) {
				System.out.println(" - Already made recommendation");
			} else {
				System.out.println(" - No recommendation yet");
			}
		}
		
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
			System.out.println("Enter the file path for the recommendation: ");
			System.out.print("> ");
			String filePath = console.nextLine();
			File recommendationFile = null;
			try {
				recommendationFile = new File(filePath);
			} catch (NullPointerException e) {
				System.out.println("File not found at location: " + filePath + "\n");
				submitRecommendation();
			}
			mySubChair.submitRecommendation(mySubChair.getMyManuscripts().get(choice - 1), recommendationFile);
			System.out.println("Recommendation made for " + mySubChair.getMyManuscripts().get(choice - 1).getMyTitle());
			submitRecommendation();
		}
	}
	
	public void displayUsers(List<String> theUsers) {
		for (int i = 0; i < theUsers.size(); i++) {
			System.out.print((i+1) + ". " + theUsers.get(i));
			if (myUsers.get(theUsers.get(i)).isRole(User.PROGRAM_CHAIR)) {
				System.out.println(" - " + User.PROGRAM_CHAIR);
			} else if (myUsers.get(theUsers.get(i)).isRole(User.SUBPROGRAM_CHAIR)) {
				System.out.println(" - " + User.SUBPROGRAM_CHAIR);
			} else if (myUsers.get(theUsers.get(i)).isRole(User.REVIEWER)) {
				System.out.println(" - " + User.REVIEWER);
			} else if (myUsers.get(theUsers.get(i)).isRole(User.AUTHOR)) {
				System.out.println(" - " + User.AUTHOR);
			} else {
				System.out.println();
			}
		}
	}
	
}
