package init;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Author;
import model.Conference;
import model.ProgramChair;
import model.Reviewer;
import model.SubProgramChair;
import model.User;

public class InitializeData {

	public static void main(String[] args) {
		// TODO Initialize conferences
		// TODO Create test files
		
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
		//Steve
		User steve = new User("Steve");
		steve.assignSubProgramChair(new SubProgramChair());
		//Sarah
		User sarah = new User("Sarah");
		sarah.assignSubProgramChair(new SubProgramChair());
		//Ron
		User ron = new User("Ron");
		ron.assignReviewer(new Reviewer());
		//Rachel
		User rachel = new User("Rachel");
		rachel.assignReviewer(new Reviewer());
		//Rick
		User rick = new User("Rick");
		rick.assignReviewer(new Reviewer());
		//Ashley
		User ashley = new User("Ashley");
		ashley.assignAuthor(new Author(ashley.getMyName()));
		//Arthur
		User arthur = new User("Arthur");
		arthur.assignAuthor(new Author(arthur.getMyName()));
		//Abigail
		User abigail = new User("Abigail");
		abigail.assignAuthor(new Author(abigail.getMyName()));
		//Aaron
		User aaron = new User("Aaron");
		aaron.assignAuthor(new Author(aaron.getMyName()));

		//conference
		Conference conf1 = new Conference(peter, "Innovations in Science 2016", new Date(2016, 9, 27), 0, 100);
		Conference conf2 = new Conference(patricia, "Nanotechnology in the Medical Sciences", new Date(2017, 4, 12), 1, 5);
		Conference conf3 = new Conference(paul, "Traffic and Transportation Psychology", new Date(2015, 9, 27), 0, 100);
		
		//
		conf1.getUserMap().put(steve.getMyName(), steve);
		conf1.getUserMap().put(sarah.getMyName(), sarah);
		conf1.getUserMap().put(ron.getMyName(), ron);
		conf1.getUserMap().put(rachel.getMyName(), rachel);
		conf1.getUserMap().put(rick.getMyName(), rick);
		conf1.getUserMap().put(ashley.getMyName(), ashley);
		conf1.getUserMap().put(arthur.getMyName(), arthur);
		conf1.getUserMap().put(abigail.getMyName(), abigail);
		
		conf2.getUserMap().put(steve.getMyName(), steve);
		conf2.getUserMap().put(sarah.getMyName(), sarah);
		conf2.getUserMap().put(ron.getMyName(), ron);
		conf2.getUserMap().put(rachel.getMyName(), rachel);
		conf2.getUserMap().put(rick.getMyName(), rick);
		conf2.getUserMap().put(ashley.getMyName(), ashley);
		conf2.getUserMap().put(arthur.getMyName(), arthur);
		conf2.getUserMap().put(abigail.getMyName(), abigail);
		
		myConferences.add(conf1);
		myConferences.add(conf2);
		myConferences.add(conf3);
		
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
