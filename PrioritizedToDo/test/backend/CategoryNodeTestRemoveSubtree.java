/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import static backend.Helper.assertExpectedPercent;
import static backend.Helper.assertNoParentChildRelationship;
import static backend.Helper.assertParentChildRelationship;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wing-sea
 */
public class CategoryNodeTestRemoveSubtree {
    
    public CategoryNodeTestRemoveSubtree() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /******************************************************************/
    /*								      */
    /*			    REMOVE SUBTREE TESTS		      */
    /*								      */
    /******************************************************************/
    
    /******************************************************************/
    /*	       Remove Subtree: PARENT-CHILD RELATIONSHIP TESTS	      */
    /******************************************************************/
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Test removeSubtree() with input a null Node.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSubtreeInputNull() throws Exception {
	CategoryNode root = new CategoryNode("root");
	root.removeSubtree(null);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Test removeSubtree() with input the Node itself.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSubtreeInputSelf() throws Exception {
	CategoryNode root = new CategoryNode("root");
	root.removeSubtree(root);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Test removeSubtree() with input a Node that is not contained in the
     * list of children of the Node being removed from.
     */
    @Test
    public void testRemoveSubtreeInputNotFound() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child = new CategoryNode("child");
	parent.add(child);
	
	CategoryNode cousin = new CategoryNode("cousin");
	parent.removeSubtree(cousin);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Test removeSubtree() from a Node with empty child list.
     */
    @Test(expected = NoSuchElementException.class)
    public void testRemoveSubtreeEmpty() throws Exception {
	CategoryNode childless = new CategoryNode("childless");
	CategoryNode unrelatedNode = new CategoryNode("unrelatedNode");
	
	childless.removeSubtree(unrelatedNode);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Test removeSubtree() with output resulting in a Node with empty child list.
     */
    @Test
    public void testRemoveSubtreeRelationshipOutputRoot() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode child = new CategoryNode("child");
	root.add(child);
	
	root.removeSubtree(child);
	assertNoParentChildRelationship(root, "root", child, "child");
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Remove an internal subtree, where the Node to be removed is the
     * first child in the list of children.
     */
    @Test
    public void testRemoveSubtreeRelationshipInputInternalFirst() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	CategoryNode child3 = new CategoryNode("child3");
	CategoryNode grandchild1 = new CategoryNode("grandchild1");
	CategoryNode grandchild2 = new CategoryNode("grandchild2");
	
	parent.add(child1);
	parent.add(child2);
	parent.add(child3);
	
	child1.add(grandchild1);
	child1.add(grandchild2);
	
	parent.removeSubtree(child1);
	assertNoParentChildRelationship(parent, "parent", child1, "child1");
	assertNoParentChildRelationship(parent, "parent", grandchild1, "grandchild1");
	assertNoParentChildRelationship(parent, "parent", grandchild2, "grandchild2");
	assertParentChildRelationship(parent, "parent", child2, "child2");
	assertParentChildRelationship(parent, "parent", child3, "child3");
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Remove an internal subtree, where the Node to be removed is a
     * middle child in the list of children.
     */
    @Test
    public void testRemoveSubtreeRelationshipInputInternalMiddle() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	CategoryNode child3 = new CategoryNode("child3");
	CategoryNode grandchild1 = new CategoryNode("grandchild1");
	CategoryNode grandchild2 = new CategoryNode("grandchild2");
	
	parent.add(child1);
	parent.add(child2);
	parent.add(child3);
	
	child2.add(grandchild1);
	child2.add(grandchild2);
	
	parent.removeSubtree(child2);
	assertNoParentChildRelationship(parent, "parent", child2, "child2");
	assertNoParentChildRelationship(parent, "parent", grandchild1, "grandchild1");
	assertNoParentChildRelationship(parent, "parent", grandchild2, "grandchild2");
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child3, "child3");
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Remove an internal subtree, where the Node to be removed is the 
     * last child in the list of children.
     */
    @Test
    public void testRemoveSubtreeRelationshipInputInternalLast() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	CategoryNode child3 = new CategoryNode("child3");
	CategoryNode grandchild1 = new CategoryNode("grandchild1");
	CategoryNode grandchild2 = new CategoryNode("grandchild2");
	
	parent.add(child1);
	parent.add(child2);
	parent.add(child3);
	
	child3.add(grandchild1);
	child3.add(grandchild2);
	
	parent.removeSubtree(child3);
	assertNoParentChildRelationship(parent, "parent", child3, "child3");
	assertNoParentChildRelationship(parent, "parent", grandchild1, "grandchild1");
	assertNoParentChildRelationship(parent, "parent", grandchild2, "grandchild2");
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child2, "child2");
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Remove a leaf Node.
     */
    @Test
    public void testRemoveSubtreeRelationshipInputLeaf() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	CategoryNode grandchild1 = new CategoryNode("grandchild1");
	CategoryNode grandchild2 = new CategoryNode("grandchild2");
	
	parent.add(child1);
	parent.add(child2);
	
	child1.add(grandchild1);
	child1.add(grandchild2);
	
	child1.removeSubtree(grandchild1);
	assertNoParentChildRelationship(child1, "child1", grandchild1, "grandchild1");
	assertParentChildRelationship(child1, "child1", grandchild2, "grandchild2");
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child2, "child2");
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case: Remove with input a Node that is different from, but has the same 
     * name as, another Node that is in the list of children.
     */
    @Test
    public void testRemoveSubtreeRelationshipDuplicateName() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child");
	CategoryNode child2 = new CategoryNode("child");
	
	parent.add(child1);
	parent.add(child2);
	parent.removeSubtree(child2);
	
	assertParentChildRelationship(parent, "parent", child1, "child");
	assertNoParentChildRelationship(parent, "parent", child2, "dup child");
    }
    
    /******************************************************************/
    /*	      Remove Subtree: PERCENT COMPLETE CONSISTENCY TESTS      */
    /******************************************************************/
    
    /*********************************************************************/
    /* Note:								 */
    /* Tests should be exactly the same as for remove(), except for the  */
    /* internal Node cases. Code should reflect this after refacotring.  */
    /* Note also to use removeSubtree() instead of just remove().	 */
    /*********************************************************************/
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Root with one child; both 0%.
     * DURING: Remove the only child.
     * AFTER: Root at 0%.
     */
    @Test
    public void removeSubtreePercent_OneChildOnly_IncompleteRoot() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child");
	parent.add(child1);
	
	parent.removeSubtree(child1);
	
	assertExpectedPercent(parent, 0, 1); // 0 complete leaves, 1 total leaf
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Parent (66%) has 2 complete children and 1 incomplete child.
     * DURING: Remove one of the complete children.
     * AFTER: Parent at 50%.
     */
    @Test
    public void removeSubtreePercent_CompleteDirectDescendent_PartiallyCompleteParent() 
    throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	complChild1.complete();
	CategoryNode complChild2 = new CategoryNode("complChild2");
	complChild2.complete();
	CategoryNode incomplChild = new CategoryNode("incomplChild");
	
	parent.add(complChild1);
	parent.add(complChild2);
	parent.add(incomplChild);
	
	parent.removeSubtree(complChild1);
	
	assertExpectedPercent(parent, 1, 2);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Parent (33%) has 2 incomplete children and 1 complete child.
     * DURING: Remove one of the incomplete children.
     * AFTER: Parent at 50%.
     */
    @Test
    public void removeSubtreePercent_IncompleteDirectDescendent_PartiallyCompleteParent() 
    throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	complChild1.complete();
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	CategoryNode incomplChild2 = new CategoryNode("incomplChild2");
	
	parent.add(complChild1);
	parent.add(incomplChild1);
	parent.add(incomplChild2);
	
	parent.removeSubtree(incomplChild1);
	
	assertExpectedPercent(parent, 1, 2);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Parent (66%) has 2 complete, 1 incomplete child.
     * DURING: Remove the only incomplete child.
     * AFTER: Parent is now 100% complete.
     */
    @Test
    public void removeSubtreePercent_IncompleteDirectDescendent_CompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	complChild1.complete();
	CategoryNode complChild2 = new CategoryNode("complChild2");
	complChild2.complete();
	CategoryNode incomplChild = new CategoryNode("incomplChild");
	
	parent.add(complChild1);
	parent.add(complChild2);
	parent.add(incomplChild);
	
	parent.removeSubtree(incomplChild);
	
	assertExpectedPercent(parent, 2, 2);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Parent has one complete, one incomplete child.
     * DURING: Remove the only complete child.
     * AFTER: Parent at 0%.
     */
    @Test
    public void removeSubtreePercent_CompleteDirectDescendent_IncompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	complChild1.complete();
	CategoryNode incomplChild = new CategoryNode("incomplChild");
	
	parent.add(complChild1);
	parent.add(incomplChild);
	
	parent.removeSubtree(complChild1);
	
	assertExpectedPercent(parent, 0, 1);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Root has 1 partially complete child, 1 zero% complete child.
     * DURING: Remove the partially complete (internal node) child.
     * AFTER: Root at 0%.
     */
    @Test
    public void removeSubtreePercent_Internal_IncompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode child = new CategoryNode("child");
	
	parent.add(internal);
	parent.add(child);
	
	CategoryNode internalChild1 = new CategoryNode("internalChild1");
	internalChild1.complete();
	CategoryNode internalChild2 = new CategoryNode("internalChild2");
	
	internal.add(internalChild1);
	internal.add(internalChild2);
	
	parent.removeSubtree(internal);
	assertExpectedPercent(parent, 0, 1);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Root has 4 children. One is complete. Another is an internal,
     * partially (50%) complete node. The rest are incomplete.
     * DURING: Remove the internal node.
     * AFTER: Root is 33% complete.
     */
    @Test
    public void removeSubtreePercent_Internal_PartiallyCompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode complChild = new CategoryNode("complChild");
	complChild.complete();
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	
	parent.add(internal);
	parent.add(complChild);
	parent.add(child1);
	parent.add(child2);
	
	CategoryNode internalChild1 = new CategoryNode("internalChild1");
	internalChild1.complete();
	CategoryNode internalChild2 = new CategoryNode("internalChild2");
	
	internal.add(internalChild1);
	internal.add(internalChild2);
	
	parent.removeSubtree(internal);
	assertExpectedPercent(parent, 1, 3);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Root has 1 complete, 1 incomplete child. The incomplete child
     * is an internal node.
     * DURING: Remove the internal node.
     * AFTER: Root is 100% complete.
     */
    @Test
    public void removeSubtreePercent_Internal_CompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode child = new CategoryNode("child");
	child.complete();
	
	parent.add(internal);
	parent.add(child);
	
	CategoryNode internalChild1 = new CategoryNode("internalChild1");
	CategoryNode internalChild2 = new CategoryNode("internalChild2");
	
	internal.add(internalChild1);
	internal.add(internalChild2);
	
	parent.removeSubtree(internal);
	assertExpectedPercent(parent, 1, 1);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Root at 50% completion, Internal Node at 66%.
     * DURING: Remove one of Internal's complete children.
     * AFTER: Root at 33%, Internal at 50%.
     */
    @Test
    public void removeSubtreePercent_RemoveComplete_PropagateUp() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode rootChild = new CategoryNode("rootChild");
	
	root.add(internal);
	root.add(rootChild);
	
	CategoryNode internalChildComplete1 = new CategoryNode("i1");
	internalChildComplete1.complete();
	CategoryNode internalChildComplete2 = new CategoryNode("i2");
	internalChildComplete2.complete();
	CategoryNode internalChildIncomplete = new CategoryNode("i3");
	
	internal.add(internalChildComplete1);
	internal.add(internalChildComplete2);
	internal.add(internalChildIncomplete);
	
	internal.removeSubtree(internalChildComplete1);
	assertExpectedPercent(internal, 1, 2);
	assertExpectedPercent(root, 1, 3);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Root at 25% completion, Internal Node at 33%.
     * DURING: Remove one of Internal's incomplete children.
     * AFTER: Root at 33%, Internal at 50%.
     */
    @Test
    public void removeSubtreePercent_RemoveIncomplete_PropagateUp() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode rootChild = new CategoryNode("rootChild");
	
	root.add(internal);
	root.add(rootChild);
	
	CategoryNode internalChildComplete1 = new CategoryNode("i1");
	internalChildComplete1.complete();
	CategoryNode incomplete = new CategoryNode("incomplete");
	CategoryNode i3 = new CategoryNode("i3");
	
	internal.add(internalChildComplete1);
	internal.add(incomplete);
	internal.add(i3);
	
	internal.removeSubtree(incomplete);
	assertExpectedPercent(internal, 1, 2);
	assertExpectedPercent(root, 1, 3);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Root at 33% completion, Internal Node at 50%.
     * DURING: Remove Internal's only incomplete child.
     * AFTER: Root at 50%, Internal at 100%.
     */
    @Test
    public void removeSubtreePercent_RemoveIncomplete_PropagateUpInternalComplete() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode rootChild = new CategoryNode("rootChild");
	
	root.add(internal);
	root.add(rootChild);
	
	CategoryNode complete = new CategoryNode("complete");
	complete.complete();
	CategoryNode incomplete = new CategoryNode("incomplete");
	
	internal.add(complete);
	internal.add(incomplete);
	
	internal.removeSubtree(incomplete);
	assertExpectedPercent(internal, 1, 1);
	assertExpectedPercent(root, 1, 2);
    }
    
    /**
     * Test of removeSubtree method, of class CategoryNode.
     * Case:
     * BEFORE: Root at 33% completion, Internal Node at 50%.
     * DURING: Remove Internal's only complete child.
     * AFTER: Root at 0%, Internal at 0%.
     */
    @Test
    public void removeSubtreePercent_RemoveComplete_PropagateUpIncomplete() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode rootChild = new CategoryNode("rootChild");
	
	root.add(internal);
	root.add(rootChild);
	
	CategoryNode complete = new CategoryNode("complete");
	complete.complete();
	CategoryNode incomplete = new CategoryNode("incomplete");
	
	internal.add(complete);
	internal.add(incomplete);
	
	internal.removeSubtree(complete);
	assertExpectedPercent(internal, 0, 1);
	assertExpectedPercent(root, 0, 2);
    }
}
