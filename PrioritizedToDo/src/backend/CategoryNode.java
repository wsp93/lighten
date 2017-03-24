/***************************************************************************/
/*									   */
/*				CategoryNode.JAVA			   */
/* Represents a single category for the Progress Tracker section of	   */
/* Lighten.								   */
/*									   */
/***************************************************************************/

package backend;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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
    private boolean isComplete;
    private LocalDateTime deadline;
    private LocalDateTime completionDate;
    //private int deadlineChangesCtr; // count # of times deadline has changed
    
    /******************************************************************/
    /*				 CONSTRUCTORS			      */
    /******************************************************************/
    
    public CategoryNode(String name) throws IllegalArgumentException {
	this(name, null);
    }
    
    public CategoryNode(String name, LocalDateTime deadline) throws IllegalArgumentException {
	if(name == null) {
	    throw new IllegalArgumentException("CategoryNode needs a Name");
	}
	
	this.name = name;
	children = new LinkedList(); // change if get() is called more often than add/remove
	parent = null;
	isComplete = false;
	this.deadline = deadline;
	completionDate = null;
    }
    
    /******************************************************************/
    /*				ADD, REMOVE, EDIT		      */
    /******************************************************************/
    
    public void add(CategoryNode child) {
	checkConstraintsAdd(child);
	children.add(child);
	child.parent = this;
    }
    
    /**
     * Removes the specified child Node from the list of children. Any children
     * of the removed Node will become children of this Node.
     * @param child The Node to be removed.
     */
    public void remove(CategoryNode child) {
	checkConstraintsRemove(child);
	
	for(CategoryNode grandchild : child.children) {
	    grandchild.parent = this;
	    this.children.add(grandchild);
	}
	
	removeChildOnly(child);
    }
    
    /**
     * Removes the specified child Node, along with its children, from the 
     * list of children.
     * @param child The Node (and its descendents) to be removed.
     */
    public void removeSubtree(CategoryNode child) {
	checkConstraintsRemove(child);
	removeChildOnly(child);
    }
    
    /*********************************************************************/
    /* TO DO:								 */
    /*									 */
    /* Consider the case when you have completed all tasks.		 */
    /*********************************************************************/
    
    public void complete() {
	setDescendentsComplete(this);
    }
    
    public void undoComplete() {
	throw new UnsupportedOperationException("Not supported yet.");
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
	throw new UnsupportedOperationException("Not supported yet.");
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
    
    public int getPercentComplete() throws Exception {
	int completeLeaves = getCompleteLeaves(this);
	int totalLeaves = getTotalLeaves(this);
	
	return Converter.getPercentage(completeLeaves, totalLeaves);
    }
    
    public boolean isComplete() {
	return isComplete;
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
	this.name = name;
    }
    
    public void setDeadline(LocalDateTime deadline) {
	this.deadline = deadline;
    }
    
    /******************************************************************/
    /*				PRIVATE HELPERS			      */
    /******************************************************************/
    
    // find the total number of leaf nodes that descend from this node.
    private int getTotalLeaves(CategoryNode root) {
	if(root != null) {
	    if(root.children.isEmpty()) {
		return 1;
	    } else { // total leaves = sum of each child's # of leaves
		int sum = 0;
		
		for(CategoryNode child : root.children) {
		    sum += getTotalLeaves(child);
		}
		
		return sum;
	    }
	}
	
	return 0;
    }
    
    // find the number of Complete leaf nodes that descend from this node.
    private int getCompleteLeaves(CategoryNode root) {
	if(root != null) {
	    if(root.children.isEmpty()) {
		if(root.isComplete) return 1;
		else return 0;
	    } else {
		int sum = 0;
		
		for(CategoryNode child : root.children) {
		    sum += getCompleteLeaves(child);
		}
		
		return sum;
	    }
	}
	
	return 0;
    }
    
    private void removeChildOnly(CategoryNode child) {
	children.remove(child);
	child.parent = null;
    }
    
    private void checkConstraintsRemove(CategoryNode child) {
	if(child == null) {
	    throw new IllegalArgumentException("Child is null");
	}
	if(child == this) {
	    throw new IllegalArgumentException("Cannot remove self");
	}
	if(children.isEmpty()) {
	    throw new NoSuchElementException("Removing from a childless Node");
	}
    }
    
    private void setDescendentsComplete(CategoryNode root) {
	if(root != null) {
	    root.isComplete = true;
	    
	    for(CategoryNode child : root.children) {
		setDescendentsComplete(child);
	    }
	}
    }

    private void checkConstraintsAdd(CategoryNode child) {
	if(child.parent != null) {
	    throw new IllegalArgumentException("This child already has a parent");
	}
	if(isComplete) {
	    throw new IllegalArgumentException("This Node is already complete");
	}
    }
}
