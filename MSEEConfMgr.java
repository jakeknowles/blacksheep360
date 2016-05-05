import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author Carl Huntington
 * @version 4/29/2016
 */


public class MSEEConfMgr implements Serializable {

	static MSEEConfMgr serializeMe = new MSEEConfMgr();
	static HashMap<String, User> myUsers = new HashMap();
	static Conference myConf = new Conference(new ProgramChair("Kevin"), "Innovative Trends in Science");
	static String myWhoAmI;
	static String myRole;
	
	
	public MSEEConfMgr() {
		
	}
	
	public static void main(String[] theArgs) {
		  
		myUsers.put("Josh", new User("Josh")); //Name/Role
		
		myUsers.put("Arthur", new User("Arthur")); //Name/Role
		myUsers.get("Arthur").myRoles.myAuthor = new Author("Arthur");
		
		myUsers.put("Ron", new User("Ron")); //Name/Role
		myUsers.get("Ron").myRoles.myReviewer = new Reviewer("Ron");
		myUsers.get("Ron").myRoles.myReviewer.myManuscripts[0] = 
				new Manuscript(new File("src/AntiSocialNetwork.doc"), "Arthur");
		
		myUsers.put("Steve", new User("Steve")); //Name/Role
		myUsers.get("Steve").myRoles.mySubProgramChair = new SubProgramChair("Steve");
		myUsers.get("Steve").myRoles.mySubProgramChair.myManuscripts[0] = 
				new Manuscript(new File("src/AntiSocialNetwork.doc"), "Arthur");

		myUsers.put("Peter", new User("Peter")); //Name/Role
		myUsers.get("Peter").myRoles.myProgramChair = new ProgramChair("Peter");
		for (int i = 0; i < 5; i++) {
			myConf.myManuscripts.put("name" + i, new Manuscript(new File("src/review.txt"), "something"));
		}
		
		try {
			FileInputStream fileIn = new FileInputStream("src/MSEE.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        serializeMe = (MSEEConfMgr) in.readObject();
	        in.close();
	        fileIn.close();
		} catch(IOException i) {
	        i.printStackTrace();
	        return;
		} catch(ClassNotFoundException c) {
	        System.out.println("MSEE class not found");
	        c.printStackTrace();
	        return;
		}
		
		String whoAmI = login();
		myWhoAmI = whoAmI;
//		System.out.println(myUsers.get(whoAmI).getClass().toString());
		displayInterface(whoAmI, myUsers);
		
	}
	
	public static String login() {
		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to the MSEE Conference Manager.");
		System.out.print("Please enter your User Name > ");
		String userName = console.next();
		System.out.print("Enter Your Role > ");
		myRole = console.next();
		return userName;
	}
	
	public static void displayInterface(String theWhoIam, HashMap<String, User> theUsers) {
		if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals("Author")) {
			authorInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals("ProgramChair")) {
			pcInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals("Reviewer")) {
			reviewerInterface(myWhoAmI);
		} else if (myUsers.get(myWhoAmI).isRole(myRole) && myRole.equals("SubProgramChair")) {
			subpcInterface(myWhoAmI);
		} else {
			myRole = "User";
			userInterface(myWhoAmI);
		}
	}
	
	public static void authorInterface(String theWhoIam) {
		Scanner console = new Scanner(System.in);
		int temp = 0;
		System.out.println();
		System.out.println("MSEE Conference Management");
		System.out.println("Author - " + theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a manuscript");
		System.out.println("\t2. Edit a manuscript");
		System.out.println("\t3. Remove a manuscript");
		System.out.println("\t4. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
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
			serial();
			break;

		}
	}
	
	public static void pcInterface(String theWhoIam) {
		Scanner console = new Scanner(System.in);
		int temp = 0;
		System.out.println();
		System.out.println("MSEE Conference Management");
		System.out.println("Program Chair - " + theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. View list of submitted manuscripts.");
		System.out.println("\t2. Make acceptance decision on manuscripts.");
		System.out.println("\t3. View manuscript assignments to Subprogram Chairs.");
		System.out.println("\t4. Assign a Subprogram Chair to a manuscript.");
		System.out.println("\t5. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
		switch (temp) {
		case 1:
			for (String s : myConf.myManuscripts.keySet()) {
				System.out.println(myConf.myManuscripts.get(s));
			}
			pcInterface(myWhoAmI);
			break;
		case 2:
			acceptance();
			break;
		case 3:
			for (String s : myUsers.keySet()) {
				if (myUsers.get(s).isRole("SubProgramChair")) {
					for (int i = 0; i < myUsers.get(s).myRoles.mySubProgramChair.myManuscripts.length; i++) {
						System.out.println(s + myUsers.get(s).myRoles.mySubProgramChair.myManuscripts[i]);
					}
				}
			}
			pcInterface(myWhoAmI);
			break;
		case 4:
			assignSPC();
			break;
		case 5:
			System.out.println("Exiting - Goodbye!");
			serial();
			break;
		}
	}

	public static void assignSPC() {
		Scanner s = new Scanner(System.in);
		System.out.println("MSEE Conference Management");
		System.out.println("Program Chair - " + myWhoAmI);
		System.out.println("Enter the name of the subprogram chair you want to assign to a manuscript.");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String name = s.next();
		if (name.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				pcInterface(myWhoAmI); //GO TO AUTHOR
			}
		} else if (name.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			serial();
		} else {
			System.out.println("Please enter the name of the manuscript you wish to assign:");
			for (String str : myConf.myManuscripts.keySet()) {
				if (!myConf.myManuscripts.get(str).myAuthorName.equals(name)) {
					System.out.println(myConf.myManuscripts.get(str));
				}
			}
			System.out.println("> ");
			String selection = s.next();
			for (int i = 0; i < 4; i++) {
				if (myUsers.get(name).myRoles.mySubProgramChair.myManuscripts[i] == null) {
					myUsers.get(name).myRoles.mySubProgramChair.myManuscripts[i] = myConf.myManuscripts.get(selection);
				}
			}
			System.out.println("SUCCESS!");
			assignSPC();
		}
	}
	
	public static void acceptance() {
		Scanner s = new Scanner(System.in);
		System.out.println("MSEE Conference Management");
		System.out.println("Program Chair - " + myWhoAmI);
		int i = 1;
		for (String str : myConf.myManuscripts.keySet()) {
			System.out.print(str + " ");
			System.out.println(myConf.myManuscripts.get(str));
		}
		System.out.println("Enter the name of the Author for the "
				+ "manuscript you want to accept/reject.");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.print("> ");
		String input = s.next();
		if (input.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				pcInterface(myWhoAmI); //GO TO AUTHOR
			}
		} else if (input.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			serial();
		} else {
			myConf.myManuscripts.get(input).myApproval = true;
			System.out.println("SUCCESS!");
			acceptance();
		}
	}
	
	public static void subpcInterface(String theWhoIam) {
		Scanner console = new Scanner(System.in);
		int temp = 0;
		System.out.println();
		System.out.println("MSEE Conference Management");
		System.out.println("Subprogram Chair - " + theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. Assign a reviewer to a paper");
		System.out.println("\t2. Submit recommendation");
		System.out.println("\t3. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
		switch (temp) {
		case 1:
			assignReviewer();
			break;
		case 2:
			submitRecommendation();
			break;
		case 3:
			System.out.println("Exiting - Goodbye!");
			serial();
			break;
		}
	}
	

	
	public static void assignReviewer() {
		Scanner s = new Scanner(System.in);
		System.out.println("MSEE Conference Management");
		System.out.println("Subprogram Chair - " + myWhoAmI);
		System.out.println("Enter the name of the reviewer you want to assign.");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String name = s.next();
		if (name.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				subpcInterface(myWhoAmI); //GO TO AUTHOR
			}
		} else if (name.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			serial();
		} else {
			//Show a list (with numbers) of papers with exception of those authored by that reviewer
			//Select a paper number
			//Attach selected paper to selected reviewer			
			System.out.println("Please select the manuscript number you wish to assign:");
			for (int i = 0; i < 4; i++) {
				if (myUsers.get(myWhoAmI).myRoles.mySubProgramChair.myManuscripts[i] != null
						&& !myUsers.get(myWhoAmI).myRoles.mySubProgramChair.myManuscripts[i]
								.myAuthorName.equals(name)) {
					System.out.println((i + 1) + ". " + myUsers.get(myWhoAmI)
							.myRoles.mySubProgramChair.myManuscripts[i].toString());	
				}
			}
			System.out.println("> ");
			int selection = s.nextInt();
			for (int i = 0; i < 4; i++) {
				if (myUsers.get(name).myRoles.myReviewer.myManuscripts[i] == null) {
					myUsers.get(name).myRoles.myReviewer.myManuscripts[i] = myUsers.get(myWhoAmI)
							.myRoles.mySubProgramChair.myManuscripts[selection - 1];
				}
			}
//			System.out.println(myUsers.get(name).myRoles.myReviewer.myManuscripts[0]);
			System.out.println("SUCCESS!");
			assignReviewer();
		}
	}
	
	public static void reviewerInterface(String theWhoIam) {
		Scanner console = new Scanner(System.in);
		int temp = 0;
		System.out.println();
		System.out.println("MSEE Conference Management");
		System.out.println("Reviewer - " + theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a review");
		System.out.println("\t2. View Submitted Reviews");
		System.out.println("\t3. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
		switch (temp) {
		case 1:
			submitReview();
			break;
		case 2:
			break;
		case 3:
			System.out.println("Exiting - Goodbye!");
			serial();
			break;

		}
	}
	
	public static void userInterface(String theWhoIam) {
		Scanner console = new Scanner(System.in);
		int temp = 0;
		System.out.println();
		System.out.println("MSEE Conference Management");
		System.out.println("User - " + theWhoIam);
		System.out.println("Select an action:");
		System.out.println("\t1. Submit a manuscript");
		System.out.println("\t2. Exit");
		System.out.println("Enter a selection");
		temp = console.nextInt();
		switch (temp) {
			case 1:
				submitManuscript();
				break;
			case 2:
				System.out.println("Exiting - Goodbye!");
				serial();
				break;

		}
	}
	
	public static void submitManuscript() {
		int temp = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("MSEE Conference Management");
//		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
//				substring(6, myUsers.get(myWhoAmI).getClass().
//						toString().length()) +" " + myWhoAmI);
		System.out.println(myRole + " - " + myWhoAmI);
		System.out.println("Enter the file path and name for the manuscript");
		System.out.println("you wish to upload");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = s.nextLine();
		if (file.equals("1")) {
			if (myUsers.get(myWhoAmI).isRole(myRole)) {
				authorInterface(myWhoAmI); //GO TO AUTHOR
			} else {
				userInterface(myWhoAmI);
			}
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			serial();
		} else {
			File toBeSaved = new File(file);
//			System.out.println(toBeSaved.length());
			myConf.addManuscript(myWhoAmI, toBeSaved);
			System.out.println("SUCCESS!");
			myUsers.get(myWhoAmI).myRoles.myAuthor = new Author(myWhoAmI);
			myRole = "Author";
			submitManuscript();
		}
	}
	
	public static void editManuscript() {
		int temp = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("MSEE Conference Management");
//		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
//				substring(6, myUsers.get(myWhoAmI).getClass().
//						toString().length()) +" " + myWhoAmI);
		System.out.println(myRole + " - " + myWhoAmI);
		System.out.println("Enter the file path and name for the manuscript");
		System.out.println("you wish to edit");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = s.nextLine();
		if (file.equals("1")) {
			authorInterface(myWhoAmI); //GO TO AUTHOR
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			serial();
		} else {
			File toBeSaved = new File(file);
//			System.out.println(toBeSaved.length());
			myConf.addManuscript(myWhoAmI, toBeSaved);
			System.out.println("SUCCESS!");
			editManuscript();
		}
	}
	
	public static void removeManuscript() {
		int temp = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("MSEE Conference Management");
//		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
//				substring(6, myUsers.get(myWhoAmI).getClass().
//						toString().length()) +" " + myWhoAmI);
		System.out.println(myRole + " - " + myWhoAmI);
		System.out.println("Enter the file path and name for the manuscript");
		System.out.println("you wish to remove");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = s.nextLine();
		if (file.equals("1")) {
			authorInterface(myWhoAmI); //GO TO AUTHOR
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			serial();
		} else {
//			System.out.println(toBeSaved.length());
			myConf.removeManuscript(myWhoAmI);
			System.out.println("SUCCESS!");
			editManuscript();
		}
	}
	
	public static void submitReview() {
		int temp = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("MSEE Conference Management");
//		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
//				substring(6, myUsers.get(myWhoAmI).getClass().
//						toString().length()) +" " + myWhoAmI);
		System.out.println(myRole + " - " + myWhoAmI);
		System.out.println("Enter the file path and name for the review");
		System.out.println("you wish to submit");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = s.nextLine();
		if (file.equals("1")) {
			reviewerInterface(myWhoAmI); //GO TO AUTHOR
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			serial();
		} else {
//			System.out.println(toBeSaved.length());
			System.out.println("Please select the manuscript number you are reviewing:");
			for (int i = 0; i < 4; i++) {
				if (myUsers.get(myWhoAmI).myRoles.myReviewer.myManuscripts[i] != null) {
					System.out.println((i + 1) + ". " + myUsers.get(myWhoAmI)
							.myRoles.myReviewer.myManuscripts[i].toString());	
				}
			}
			System.out.println("> ");
			int selection = s.nextInt();
			myUsers.get(myWhoAmI).myRoles.myReviewer.myManuscripts[selection - 1]
					.myReviews.add(new Review(new File(file)));
			System.out.println("SUCCESS!");
			submitReview();
		}
	}
	
	public static void submitRecommendation() {
		int temp = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("MSEE Conference Management");
//		System.out.println(myUsers.get(myWhoAmI).getClass().toString().
//				substring(6, myUsers.get(myWhoAmI).getClass().
//						toString().length()) +" " + myWhoAmI);
		System.out.println(myRole + " - " + myWhoAmI);
		System.out.println("Enter the file path and name for the recommendation");
		System.out.println("you wish to submit");
		System.out.println("ex. C:\\Documents\\example.doc");
		System.out.println("\n\t- OR -");
		System.out.println("\t1. Back");
		System.out.println("\t2. Exit");
		System.out.println("> ");
		String file = s.nextLine();
		if (file.equals("1")) {
			subpcInterface(myWhoAmI); //GO TO AUTHOR
		} else if (file.equals("2")) {
			System.out.println("Exiting - Goodbye!");
			serial();
		} else {
//			System.out.println(toBeSaved.length());
			System.out.println("Please select the manuscript number ");
			System.out.println("you are making a recommendation for:");
			for (int i = 0; i < 4; i++) {
				if (myUsers.get(myWhoAmI).myRoles.mySubProgramChair.myManuscripts[i] != null) {
					System.out.println((i + 1) + ". " + myUsers.get(myWhoAmI)
							.myRoles.mySubProgramChair.myManuscripts[i].toString());	
				}
			}
			System.out.println("> ");
			int selection = s.nextInt();
			myUsers.get(myWhoAmI).myRoles.mySubProgramChair.myManuscripts[selection - 1]
					.myRecommendation = new Recommendation(new File(file));
			System.out.println("SUCCESS!");
			submitRecommendation();
		}
	}
	
	public static void serial() {
	    try {
	    	FileOutputStream fileOut = new FileOutputStream("src/MSEE.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(serializeMe);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized file saved in /src/MSEE.ser");
	      } catch(IOException i) {
	    	  i.printStackTrace();
	      }
		
	}
	
}
