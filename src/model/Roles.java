package model;

import java.io.Serializable;

public class Roles implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -8401286720437180771L;

		/**
		 * The user's author role.
		 */
		private Author myAuthor;
		
		/**
		 * The user's reviewer role.
		 */
		private Reviewer myReviewer;
		/**
		 * The user's subprogram chair role.
		 */
		private SubProgramChair mySubProgramChair;
		/**
		 * The user's Program chair role.
		 */
		private ProgramChair myProgramChair;
		
		/**
		 * Constructor.
		 * 
		 * @version 5/8/2016
		 */
		public Roles() {
			myAuthor = null;
			myReviewer = null;
			mySubProgramChair = null;
			myProgramChair = null;
		}
		
		public Roles(Author theAuthor, Reviewer theReviewer, SubProgramChair theSubProgramChair, ProgramChair theProgramChair) {
			myAuthor = theAuthor;
			myReviewer = theReviewer;
			mySubProgramChair = theSubProgramChair;
			myProgramChair = theProgramChair;
		}
		
		//Setters and Getters
		
		/**
		 * @version 5/31/2016
		 */
		public void setAuthor(Author theAuthor) {
			myAuthor = theAuthor;
		}
		
		/**
		 * @version 5/31/2016
		 */
		public Author getAuthor() {
			return myAuthor;
		}
		
		/**
		 * @version 5/31/2016
		 */
		public void setReviewer(Reviewer theReviewer) {
			myReviewer = theReviewer;
		}
		
		/**
		 * @version 5/31/2016
		 */
		public Reviewer getReviewer() {
			return myReviewer;
		}
		
		/**
		 * @version 5/31/2016
		 */
		public SubProgramChair getSubProgramChair() {
			return mySubProgramChair;
		}
		
		/**
		 * @version 5/31/2016
		 */
		public void setSubProgramChair(SubProgramChair theSubProgramChair) {
			mySubProgramChair = theSubProgramChair;
		}
		
		/**
		 * @version 5/31/2016
		 */
		public ProgramChair getProgramChair() {
			return myProgramChair;
		}
		
		/**
		 * @version 5/31/2016
		 */
		public void setProgramChair(ProgramChair theProgramChair) {
			myProgramChair = theProgramChair;
		}
}
