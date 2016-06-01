package init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.ProgramChair;
import model.Reviewer;
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
public class InitializeData {

	/**
	 * Initializes a list of three conferences with users and manuscripts
	 * Then serializes the conference to be used with the main driver
	 * program.
	 * 
	 * @version 5/31/2016
	 */
	public static void main(String[] args) {
		
		List<Conference> myConferences = new ArrayList<Conference>();
		
		//users
		//Peter
		User peter = new User("Peter");
		peter.assignProgramChair(new ProgramChair());
		//Patricia
		User patricia = new User("Patricia");
		patricia.assignProgramChair(new ProgramChair());
		//Paul
		User paul = new User("Paul");
		paul.assignProgramChair(new ProgramChair());
		
		
		//Ashley
		User ashley = new User("Ashley");
		ashley.assignAuthor(new Author(ashley.getMyName()));
		//Arthur
		User arthur = new User("Arthur");
		arthur.assignAuthor(new Author(arthur.getMyName()));
		//Abigail
		User abigail = new User("Abigail");
		abigail.assignAuthor(new Author(abigail.getMyName()));
		
		//Ron
		User ron = new User("Ron");
		ron.assignReviewer(new Reviewer());
		//Rachel
		User rachel = new User("Rachel");
		rachel.assignReviewer(new Reviewer());
		rachel.assignAuthor(new Author(rachel.getMyName()));
		
		//Steve
		User steve = new User("Steve");
		steve.assignSubProgramChair(new SubProgramChair());
		steve.assignAuthor(new Author(steve.getMyName()));
		//Sarah
		User sarah = new User("Sarah");
		sarah.assignSubProgramChair(new SubProgramChair());
		sarah.assignReviewer(new Reviewer());

		//conference

		Conference conf1 = new Conference(peter, "Innovations in Science 2016", new Date(2016, 9, 17), 0, 100);
		Conference conf2 = new Conference(patricia, "Nanotechnology in the Medical Sciences", new Date(2017, 4, 12), 1, 5);
		Conference conf3 = new Conference(paul, "Traffic and Transportation Psychology", new Date(2015, 9, 27), 0, 100);
		
		//
		conf1.getUserMap().put(peter.getMyName(), peter);
		conf1.getUserMap().put(steve.getMyName(), steve);
		conf1.getUserMap().put(sarah.getMyName(), sarah);
		conf1.getUserMap().put(ron.getMyName(), ron);
		conf1.getUserMap().put(rachel.getMyName(), rachel);
		conf1.getUserMap().put(ashley.getMyName(), ashley);
		conf1.getUserMap().put(arthur.getMyName(), arthur);
		conf1.getUserMap().put(abigail.getMyName(), abigail);
		
		
		conf2.getUserMap().put(patricia.getMyName(), patricia);
		conf2.getUserMap().put(steve.getMyName(), steve);
		conf2.getUserMap().put(sarah.getMyName(), sarah);
		conf2.getUserMap().put(ron.getMyName(), ron);
		conf2.getUserMap().put(rachel.getMyName(), rachel);
		conf2.getUserMap().put(ashley.getMyName(), ashley);
		conf2.getUserMap().put(arthur.getMyName(), arthur);
		conf2.getUserMap().put(abigail.getMyName(), abigail);
		
		conf3.getUserMap().put(paul.getMyName(), paul);
		conf3.getUserMap().put(arthur.getMyName(), arthur);
		
		myConferences.add(conf1);
		myConferences.add(conf2);
		myConferences.add(conf3);
		
		//serialize
		try {
	    	FileOutputStream fileOut = new FileOutputStream("./MSEEdata.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(myConferences);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized file saved in ./MSEEdata.ser");
	      } catch(IOException i) {
	    	  i.printStackTrace();
	      }
	}

}
