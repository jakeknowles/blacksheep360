package view;
	
import java.util.HashMap;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.ManuscriptAcceptanceStatus;
import model.User;

public class ProgramChairUI {

	private HashMap<String, User> myUsers;
	private Conference myConf;
	private String myName;
	private String myRole;
	private String currDateString;
	private Scanner console;


	public ProgramChairUI(HashMap<String, User> theUsers, Conference theConf, 
			String theWhoAmI, String theRole, String theCurrDateString, Scanner theConsole) {
		myUsers = theUsers; 
		myConf = theConf;
		myName = theWhoAmI; 
		myRole = theRole;
		currDateString = theCurrDateString;
		console = theConsole;	
	}
	
	/**
	 * displays the program chair interface and reads user input.
	 * 
	 * @version 5/8/2016
	 */
	public void pcInterface(String theWhoIam) {
		int temp = 0;
		System.out.println();
//		System.out.println("MSEE Conference Management");
//		System.out.println("Program Chair - " + theWhoIam);
//		System.out.println("Date: " + currDateString);
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		System.out.println("Select an action:");
		System.out.println("\t1. View list of submitted manuscripts.");
		System.out.println("\t2. Make acceptance decision on manuscripts.");
		System.out.println("\t3. View manuscript assignments to Subprogram Chairs.");
		System.out.println("\t4. Assign a Subprogram Chair to a manuscript.");
		System.out.println("\t5. Exit");
		System.out.print("Enter a selection > ");
		temp = console.nextInt();
		console.nextLine();
		System.out.println();
		switch (temp) {
		case 1:
			//System.out.println(myUsers.get(theWhoIam).myRoles.myProgramChair.viewManuscripts(myConf.myManuscripts)); //Alexandria, 5/15/16 - can we put view manuscripts in a method so it can be called from acceptance()? EDIT: nevermind
			viewManuscripts();
			//pcInterface(myWhoAmI);
			break;
		case 2:
			acceptance();
			break;
		case 3:
			for (String s : myUsers.keySet()) {
				if (myUsers.get(s).isRole(User.SUBPROGRAM_CHAIR)) { 	//Alexandria, 5/15/16 - same goes for this code inside the for each loop. Could use it for assignSPC() EDIT: nevermind
					System.out.println(s + ":");
					System.out.println(myUsers.get(s).getMyRoles().mySubProgramChair.getManuscripts());
				}
			}
			pcInterface(myName);
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
	public void assignSPC() {
//		System.out.println("MSEE Conference Management");
//		System.out.println("Program Chair - " + myWhoAmI);
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		System.out.println("Subprogram Chairs: "); //Alexandria, 5/17/2016 - prints list of subprogram chairs
		for (String str : myUsers.keySet()){
			if (myUsers.get(str).isRole(User.SUBPROGRAM_CHAIR)){
			System.out.println(myUsers.get(str).getMyName());
			}
		}
		System.out.println("Enter the name of the subprogram chair you want to assign to a manuscript."); //Alexandria, 5/15/16 - display list of subprogram chairs here EDIT: done!
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String name = console.nextLine();
		if (name.equals("1")) {
			if (myUsers.get(myName).isRole(myRole)) {
				pcInterface(myName); //GO TO AUTHOR
			}
		} else if (name.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			System.out.println("Manuscripts already assigned to " + name + ":");
			for (Manuscript manuscript : myUsers.get(name).getMyRoles().mySubProgramChair.getManuscripts()){
				System.out.println(manuscript.getMyTitle());
			}
			System.out.println("\nPlease enter the name of the manuscript you wish to assign:"); //Alexandria, 5/15/16 - prior to this, print out the manuscripts already assigned to this SPC EDIT: done!
			for (String str : myConf.getMyManuscripts().keySet()) {
				if (!myConf.getMyManuscripts().get(str).getMyAuthorName().equals(name)) {
					System.out.println(myConf.getMyManuscripts().get(str).getMyTitle()); //Alexandria, 5/17/16 - now prints the manuscript titles
				}
			}
			System.out.println("> ");
			String selection = console.nextLine();
			boolean result = myUsers.get(myName).getMyRoles().myProgramChair.assignManuscripts(myUsers.get(name), myConf.getMyManuscripts().get(selection));
			if (result) {
				System.out.println(myUsers.get(name).getMyName() + " has been assigned " + myConf.getMyManuscripts().get(selection).getMyTitle()); //Alexandria, 5/15/16 - this should either display "ExampleSPC has been assigned SamplePaperName" or a list of SPCs and their assigned papers.
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
	public void acceptance() {
//		System.out.println("MSEE Conference Management");
//		System.out.println("Program Chair - " + myWhoAmI);
		MSEEConfMgr.header(User.AUTHOR, myName, currDateString, myConf.getMyConfName());
		printManuscripts();
		System.out.println("Enter the title of the "
				+ "manuscript you want to accept/reject."); //Alexandria, 5/15/16 - problem here: we're telling them they can accept and reject, but when they input a name the system "accepts" it. Need group input on this one.
		System.out.println("\n\t- OR -");					//5/22/16 fixed
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String input = console.nextLine();
		if (input.equals("1")) {
			if (myUsers.get(myName).isRole(myRole)) {
				pcInterface(myName); //GO TO AUTHOR
			}
		} else if (input.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		} else {
			//Alexandria, 5/19/16 - I think we somehow need to add the three states for manuscripts that Tennenberg talked about (undecided, accepted, rejected) EDIT: fixed.
			System.out.println("\nSelect your acceptance decision for this manuscript:");
			System.out.println("1. Accept");
			System.out.println("2. Reject");
			String decision = console.nextLine();
			if (decision.equals("1")) {
				myUsers.get(myName).getMyRoles().myProgramChair.submitDecision(myConf.getMyManuscripts().get(input), ManuscriptAcceptanceStatus.ACCEPTED);
			} else if (decision.equals("2")) {
				myUsers.get(myName).getMyRoles().myProgramChair.submitDecision(myConf.getMyManuscripts().get(input), ManuscriptAcceptanceStatus.REJECTED);
			}
			//System.out.println(myConf.getMyManuscripts().get(input).getMyTitle() + " has been accepted to " + myConf.getMyConfName() + "."); //Alexandria, 5/15/16 - "nameOfPaper has been accepted" or something like that EDIT: done
			System.out.println("Your decision has been submitted.");
			acceptance();
		}
	}
	
	/**
	 * A screen to view manuscripts submitted to this conference.
	 * 
	 * @version 5/19/16
	 */
	public void viewManuscripts(){
		printManuscripts();
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String input = console.nextLine();
		if (input.equals("1")) {
			if (myUsers.get(myName).isRole(myRole)) {
				pcInterface(myName); //GO TO AUTHOR
			}
		} else if (input.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			//serial();
		}
	}
	
	/**
	 * Prints out all of the current conference's submitted manuscripts.
	 * 
	 * @version 5/22/16
	 */
	public void printManuscripts(){
		System.out.println("------Submitted Manuscripts------");
		System.out.println("Title\tAuthor\tAcceptance Status\n");
		for (Manuscript manuscript : myUsers.get(myName).getMyRoles().myProgramChair.viewManuscripts(myConf.getMyManuscripts())){
			System.out.print(manuscript.getMyTitle() + "\t" + manuscript.getMyAuthorName() + "\t");
			switch (manuscript.getMyApproval()){
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
}
