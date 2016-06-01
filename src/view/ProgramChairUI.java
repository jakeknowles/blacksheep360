package view;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.ManuscriptAcceptanceStatus;
import model.ProgramChair;
import model.User;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 5/31/2016
 */
public class ProgramChairUI {

	/**
	 * A map of users.
	 */
	private HashMap<String, User> myUsers;
	/**
	 * The conference currently logged into.
	 */
	private Conference myConf;
	/**
	 * The name of the current user.
	 */
	private String myName;
	/**
	 * The role of the current user.
	 */
	private String myRole;
	/**
	 * The current date.
	 */
	private String currDateString;
	/**
	 * the input console.
	 */
	private Scanner console;
	/**
	 * the current program chair
	 */
	private ProgramChair myProgChair;


	/**
	 * @version 5/30/2016
	 */
	public ProgramChairUI(HashMap<String, User> theUsers, Conference theConf, 
			String theWhoAmI, String theRole, String theCurrDateString, Scanner theConsole) {
		myUsers = theUsers; 
		myConf = theConf;
		myName = theWhoAmI; 
		myRole = theRole;
		currDateString = theCurrDateString;
		console = theConsole;	
		myProgChair = myUsers.get(myName).getProgramChair();
	}
	
	/**
	 * displays the program chair interface and reads user input.
	 * 
	 * @version 5/8/2016
	 */
	public void pcInterface() {
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		System.out.println("Select an action:");
		System.out.println("\t1. View list of submitted manuscripts.");
		System.out.println("\t2. Make acceptance decision on manuscripts.");
		System.out.println("\t3. View manuscript assignments to Subprogram Chairs.");
		System.out.println("\t4. Assign a Subprogram Chair to a manuscript.");
		System.out.println("\t5. Exit");
		System.out.print("Enter a selection > ");
		int temp = console.nextInt();
		console.nextLine();
		System.out.println();
		switch (temp) {
		case 1:
			viewManuscripts();
			break;
		case 2:
			makeAcceptanceDecision();
			break;
		case 3:
			viewSubProgramChairManuscriptAssignments();
			break;
		case 4:
			assignSPC();
			break;
		case 5:
			System.out.println("Exiting - Goodbye!");
			break;
		}
	}

	/**
	 * User interface for assigning a subprogram chair to a manuscript.
	 * 
	 * @version 5/8/2016
	 */
	public void assignSPC() {
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		List<String> users = new ArrayList<String>(myUsers.keySet());
		System.out.println("Subprogram Chairs: ");
		List<String> subProgramChairs = getSubProgramChairs(users);
		displaySubProgramChairs(subProgramChairs);
		int back = subProgramChairs.size() + 1;
		int exit = back + 1;
		System.out.println("Select the subprogram chair you want to assign to a manuscript.");
		System.out.println("\n\t- OR -");
		System.out.println("\t" + back + ". Back");
		System.out.println("\t" + exit + ". Exit");
		System.out.println("> ");
		int  selection = console.nextInt();
		console.nextLine();
		if (selection == back) {
			if (myUsers.get(myName).isRole(myRole)) {
				pcInterface();
			}
		} else if (selection == exit) {
			System.out.println("Exiting - Goodbye!");
		} else {
			User selectedSPC = myUsers.get(subProgramChairs.get(selection - 1));
			
			System.out.println("Manuscripts already assigned to " + selectedSPC.getMyName() + ":");
			for (Manuscript manuscript : selectedSPC.getSubProgramChair().getManuscripts()){
				System.out.println(manuscript.getMyTitle());
			}
			
			System.out.println("\nManuscripts available to assign:");
			List<Manuscript> availableManuscripts = new ArrayList<Manuscript>();
			int i = 0;
			for (String title : myConf.getMyManuscripts().keySet()) {
				Manuscript man = myConf.getMyManuscripts().get(title);
				if (!man.isAssignedtoSubProgramChair()) {
					System.out.println("\t" + (i+1) + ". " + man.getMyTitle());
					availableManuscripts.add(man);
					i++;
				}
			}
			
			System.out.println("\nSelect the manuscript you wish to assign:"); 
			System.out.println("> ");
			selection = console.nextInt();
			console.nextLine();
			boolean result = myProgChair.assignManuscripts(selectedSPC, availableManuscripts.get(selection - 1));
			if (result) {
				System.out.println(selectedSPC.getMyName() + " has been assigned " + availableManuscripts.get(selection - 1).getMyTitle());
				availableManuscripts.get(selection - 1).setAssignedtoSubProgramChair(true);
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
	public void makeAcceptanceDecision() {
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		List<Manuscript> submittedManuscripts = new ArrayList<Manuscript>(myConf.getMyManuscripts().values());
		int back = submittedManuscripts.size() + 1;
		int exit = back + 1;
		printManuscripts(submittedManuscripts);
		System.out.println("\nSelect the "
				+ "manuscript you want to make a decision for."); 
		System.out.println("\n\t- OR -");
		System.out.println("\t" + back + ". Back");
		System.out.println("\t" + exit + ". Exit");
		System.out.print("> ");
		int input = console.nextInt();
		console.nextLine();
		if (input == back) {
			pcInterface(); 
		} else if (input == exit) {
			System.out.println("Exiting - Goodbye!");
		} else {
			System.out.println("\nSelect your acceptance decision for this manuscript:");
			System.out.println("1. Accept");
			System.out.println("2. Reject");
			System.out.println("3. Back");
			System.out.print("> ");
			String decision = console.nextLine();
			if (decision.equals("1")) {
				myProgChair.submitDecision(submittedManuscripts.get(input - 1), ManuscriptAcceptanceStatus.ACCEPTED);
			} else if (decision.equals("2")) {
				myProgChair.submitDecision(submittedManuscripts.get(input - 1), ManuscriptAcceptanceStatus.REJECTED);
			} else {
				makeAcceptanceDecision();
			}
			System.out.println("Your decision has been submitted.");
			makeAcceptanceDecision();
		}
	}
	
	/**
	 * A screen to view manuscripts submitted to this conference.
	 * 
	 * @version 5/19/16
	 */
	public void viewManuscripts(){
		List<Manuscript> submittedManuscripts = new ArrayList<Manuscript>(myConf.getMyManuscripts().values());
		printManuscripts(submittedManuscripts);
		System.out.println("\n\n\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String input = console.nextLine();
		if (input.equals("1")) {
			pcInterface(); 	
		} else if (input.equals("2")) {
			System.out.println("Exiting - Goodbye!");
		}
	}
	
	/**
	 * Prints out all of the current conference's submitted manuscripts.
	 * 
	 * @version 5/22/16
	 */
	public void printManuscripts(List<Manuscript> theManuscripts){
		System.out.println("------Submitted Manuscripts------");
		System.out.println("Title, Author, Recommendation, Acceptance Status\n");
		for (int i = 0; i < theManuscripts.size(); i ++){
			System.out.print((i+1) + ". " + theManuscripts.get(i).getMyTitle() + ", " + theManuscripts.get(i).getMyAuthorName() + ", ");
			if (theManuscripts.get(i).getMyRecommendation() != null) {
				System.out.print(theManuscripts.get(i).getMyRecommendation().getStatement() + ", ");
			} else {
				System.out.print("No Recommendation, ");
			}
			switch (theManuscripts.get(i).getMyApproval()){
			case NO_DECISION:
				System.out.println("Undecided");
				break;
			case REJECTED:
				System.out.println("Rejected");
				break;
			case ACCEPTED:
				System.out.println("Accepted");
				break;
			}
		}
	}
	
	/**
	 * Displays a list of subprogram chairs.
	 * @version 5/31/2016
	 */
	public void displaySubProgramChairs(List<String> theUsers) {
		int j = 1;
		for (int i = 0; i < theUsers.size(); i++) {
			 if (myUsers.get(theUsers.get(i)).isRole(User.SUBPROGRAM_CHAIR)) {
				 System.out.println((j++) + ". " + theUsers.get(i));
			 }
		}
	}
	
	/**
	 * Gets a list of all assigned subprogram chairs.
	 * @version 5/31/2016
	 */
	public List<String> getSubProgramChairs(List<String> theUsers) {
		List<String> spc = new ArrayList<String>();
		for (int i = 0; i < theUsers.size(); i++) {
			 if (myUsers.get(theUsers.get(i)).isRole(User.SUBPROGRAM_CHAIR)) {
				 spc.add(theUsers.get(i));
			 }
		}
		return spc;
	}
	
	/**
	 * Displays all subprogram chairs and the manuscripts that have been assigned to them.
	 * Also displays manuscripts not assigned to a subprogram chair.
	 * 
	 * @version 5/31/2016
	 */
	public void viewSubProgramChairManuscriptAssignments() {
		for (String subPC : myUsers.keySet()) {
			if (myUsers.get(subPC).isRole(User.SUBPROGRAM_CHAIR)) {
				System.out.println("\nManuscripts assigned to " + subPC);
				for (Manuscript manuscript : myUsers.get(subPC).getSubProgramChair().getManuscripts()){
					System.out.println("\t" + manuscript.getMyTitle());
				}
			}
		}
		System.out.println("\nManuscripts not assigned to a Subprogram Chair:");
		for (String title : myConf.getMyManuscripts().keySet()) {
			Manuscript man = myConf.getMyManuscripts().get(title);
			if (!man.isAssignedtoSubProgramChair()) {
				System.out.println("\t" + man.getMyTitle());
			}
		}
		pcInterface();
	}
}
