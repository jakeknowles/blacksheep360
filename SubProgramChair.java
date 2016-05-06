import java.util.List;
import java.io.Serializable;

/**
 * @author Alexandria Reynolds
 * @author Carl Huntington
 * @author Geoffrey Tanay
 * @author Jake Knowles
 *  
 * @version 4/29/2016
 */
public class SubProgramChair implements java.io.Serializable {

	public Manuscript[] myManuscripts;
	
	public SubProgramChair(String theName) {
		myManuscripts = new Manuscript[4];
	}

	/**
	 * Assigns the Reviewer to the Manuscript. If the Manuscript is not
	 * one that the Subprogram Chair has been assigned, or the Reviewer 
	 * has already been assigned four Manuscript Reviews, or the Reviewer
	 * is the author of the Manuscript then this method does nothing.
	 * 
	 * @param theManuscript the manuscript that is assigned to the reviewer
	 * @param theReviewer the Reviewer that is assigned a manuscript
	 * 
	 * @author Geoffrey Tanay
	 * @version 05/01/2016
	 */
	public void assignReviewer(final Manuscript theManuscript, final Reviewer theReviewer) {
//		int index = myManuscripts.indexOf(theManuscript);
//		if (index >= 0 && theReviewer.myReviews < 4 && !theManuscript.getAuthor().equals(theReviwer.getName())) {
//			myManuscripts.get(index).addReviewer(theReviewer);
//		}
	}
	
	/**
	 * Submits a manuscript recommendation.
	 * 
	 * @param theManuscript
	 * @param theRecommendation
	 * 
	 * @author Geoffrey Tanay
	 * @version 05/01/2016
	 */
	public void submitRecommendation(final Manuscript theManuscript, final Recommendation theRecommendation) {
//		int index = myManuscripts.indexOf(theManuscript);
//		if (index >=? 0) {
//			myManuscripts.get(index).addRecommendation(theRecommendation);
//		}	?
	}
	
	/**
	 * Adds a manuscript to the list of assigned manuscripts.
	 * 
	 * @param theManuscript
	 * 
	 * @author Geoffrey Tanay
	 * @version 05/01/2016
	 */
	public void addManuscript(final Manuscript theManuscript) {
//		if (!this.myName.equals(theManuscript.myAuthor) && myManuscript.size() < 4) {
//			myManuscripts.add(theManuscript);
//		}
	}
	
	
	
	private Recommendation viewRecommendation() {
		return new Recommendation(null);
	}
	
	public void editRecommendation() {
		
	}
	
	public String toString() {
		return "Im a SubPC";
	}

}
