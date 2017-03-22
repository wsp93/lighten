/***************************************************************************/
/*									   */
/*				CategoryNode.JAVA			   */
/* Represents a single category for the Progress Tracker section of	   */
/* Lighten.								   */
/*									   */
/***************************************************************************/

package backend;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Category whose progress we wish to track. Categories may have
 * deadlines and an infinite number of sub-categories (within memory limitations).
 * 
 * Invariants:
 *	-A parent-child relationship must be mutual. I.e. If this node has a
 *	parent, that parent node must have this node as a child.
 * 
 *	-Each node should have percentComplete consistent with child nodes.
 *	i.e. If a child node's percentComplete changes, this change should
 *	propagate to its ancestor nodes.
 * 
 * @author Wing-Sea
 */
public class CategoryNode {
    
    ////////////////////////////////////////////////////////////////////
    //				   TO DO			      //
    ////////////////////////////////////////////////////////////////////
    //								      //
    // -Should percentComplete be a double?			      //
    // -Data should be persistent. Use a DB.			      //
    // -Nodes sortable by deadline/% complete/both (group by)	      //
    // -Add IDs for each node? (Be careful of overflow)		      //
    //								      //
    //	Optional:						      //
    // -Sync Deadlines with Calendar				      //
    // -Implement deadlineChangesCtr				      //
    // -Reminders						      //
    // -Repeatable						      //
    // -Display “landmarks” (labels of sub-cat completion) on bars    //
    // -Bookmarks (to quickly reach common cats)		      //
    // -Implement Iterable to iterate over children for cleaner code  //
    ////////////////////////////////////////////////////////////////////
    
    /******************************************************************/
    /*				  CONSTANTS			      */
    /******************************************************************/
    
    public static final int MAX_DEADLINE = 100; // years from now
    
    /******************************************************************/
    /*				   FIELDS			      */
    /******************************************************************/
    
    private String name;
    private List<CategoryNode> children; // represents sub-categories
    private CategoryNode parent; // super-category
    private int percentComplete; // use whole-number percentages // TODO: double?
    private LocalDateTime deadline;
    private LocalDateTime completionDate;
    //private int deadlineChangesCtr; // count # of times deadline has changed
    
    /******************************************************************/
    /*				 CONSTRUCTORS			      */
    /******************************************************************/
    
    public CategoryNode(String name) {
	this(name, null);
    }
    
    public CategoryNode(String name, LocalDateTime deadline) {
	this.name = name;
	children = new ArrayList();
	parent = null;
	percentComplete = 0;
	this.deadline = deadline;
	completionDate = null;
    }
    
    /******************************************************************/
    /*				ADD, REMOVE, EDIT		      */
    /******************************************************************/
    
    public void add(CategoryNode child) {
	
    }
    
    /**
     * Removes the specified child Node from the list of children. Any children
     * of the removed Node will become children of this Node.
     * @param child The Node to be removed.
     */
    public void remove(CategoryNode child) {
	
    }
    
    /**
     * Removes the specified child Node, along with its children, from the 
     * list of children.
     * @param child The Node (and its descendents) to be removed.
     */
    public void removeSubtree(CategoryNode child) {
	
    }
    
    public void edit(String newName) {
	
    }
    
    public void complete() {
	
    }
    
    public void undoComplete() {
	
    }
    
    /******************************************************************/
    /*				DEADLINE-RELATED		      */
    /******************************************************************/
    
    /**
     * @return If the CategoryNode is complete: returns the time remaining
     * between deadline and completionDate.
     * If the CategoryNode is incomplete: returns either the amount of time
     * overdue, or the amount of time remaining.
     */
    public PeriodDuration getTimeRemaining() {
	return null;
    }
    
    public boolean hasDeadline() {
	return deadline != null;
    }
    
    /******************************************************************/
    /*				   GETTERS			      */
    /******************************************************************/
    
    public String getName() {
	return this.name;
    }
    
    public CategoryNode getChild(int index) {
	return children.get(index);
    }
    
    public int numOfChildren() {
	return children.size();
    }
    
    public CategoryNode getParent() {
	return this.parent;
    }
    
    public int getPercentComplete() {
	// BEWARE DIVIDE BY 0
	assert(0 <= percentComplete && percentComplete <= 100);
	
	return -1;
    }
    
    public boolean isComplete() {
	return percentComplete == 100;
    }
    
    public LocalDateTime getDeadline() {
	return this.deadline;
    }
    
    public LocalDateTime getCompletionDate() {
	return this.completionDate;
    }
    
    /******************************************************************/
    /*				   SETTERS			      */
    /******************************************************************/
    
    public void setName(String name) {
	
    }
    
    public void setDeadline(LocalDateTime deadline) {
	
    }
    
    /******************************************************************/
    /*				PRIVATE HELPERS			      */
    /******************************************************************/
    
    private void setParent(CategoryNode parent) {
	
    }
    
    private void setPercentComplete(int percent) {
	
	assert(0 <= percentComplete && percentComplete <= 100);
    }
    
    private void setCompletionDate(LocalDateTime completionDate) {
	
    }
}
