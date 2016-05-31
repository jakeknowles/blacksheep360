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
		
		public void setAuthor(Author theAuthor) {
			myAuthor = theAuthor;
		}
		
		public Author getAuthor() {
			return myAuthor;
		}
		
		public void setReviewer(Reviewer theReviewer) {
			myReviewer = theReviewer;
		}
		
		public Reviewer getReviewer() {
			return myReviewer;
		}
		public SubProgramChair getSubProgramChair() {
			return mySubProgramChair;
		}
		
		public void setSubProgramChair(SubProgramChair theSubProgramChair) {
			mySubProgramChair = theSubProgramChair;
		}
		
		public ProgramChair getProgramChair() {
			return myProgramChair;
		}
		
		public void setProgramChair(ProgramChair theProgramChair) {
			myProgramChair = theProgramChair;
		}
}
