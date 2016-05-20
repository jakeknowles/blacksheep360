
	
import java.util.HashMap;
import java.util.Scanner;

public class ProgramChairUI {

	static HashMap<String, User> myUsers;
	static Conference myConf;
	static String myWhoAmI;
	static String myRole;
	static String currDateString;
	static Scanner console;


	public ProgramChairUI(HashMap<String, User> theUsers, Conference theConf, 
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
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				pcInterface(myWhoAmI); //GO TO AUTHOR
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
			boolean result = myUsers.get(myWhoAmI).getMyRoles().myProgramChair.assignManuscripts(myUsers.get(name), myConf.getMyManuscripts().get(selection));
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
	public static void acceptance() {
//		System.out.println("MSEE Conference Management");
//		System.out.println("Program Chair - " + myWhoAmI);
		header(myWhoAmI);
		System.out.println("Unaccepted Manuscripts: ");
		for (String str : myConf.getMyManuscripts().keySet()) { //Alexandria, 5/15/16 - if we put view manuscripts into a method we could call that here, unless we wanted to not show already accepted manuscripts.
			//System.out.print(str + " "); ////Alexandria, 5/17/16 - These two lines would print out the some thing, wouldn't they? Did I do that?
			if (!myConf.getMyManuscripts().get(str).getMyApproval()){
				System.out.println(myConf.getMyManuscripts().get(str).getMyTitle());  //Alexandria, 5/19/16 - this should only display unaccepted manuscript titles now
			}
		}
		System.out.println("Enter the title of the "
				+ "manuscript you want to accept/reject."); //TODO Alexandria, 5/15/16 - problem here: we're telling them they can accept and reject, but when they input a name the system "accepts" it. Need group input on this one.
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
			//TODO Alexandria, 5/19/16 - I think we somehow need to add the three states for manuscripts that Tennenberg talked about (undecided, accepted, rejected)
			myUsers.get(myWhoAmI).getMyRoles().myProgramChair.submitDecision(myConf.getMyManuscripts().get(input), true);
			System.out.println(myConf.getMyManuscripts().get(input).getMyTitle() + " has been accepted to " + myConf.getMyConfName() + "."); //Alexandria, 5/15/16 - "nameOfPaper has been accepted" or something like that EDIT: done
			acceptance();
		}
	}
	
	/**
	 * Displays a list of all manuscripts submitted to this conference.
	 * 
	 * @version 5/19/16
	 */
	public static void viewManuscripts(){
		System.out.println(myUsers.get(myWhoAmI).getMyRoles().myProgramChair.viewManuscripts(myConf.getMyManuscripts()));
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
		}
	}
	
}
