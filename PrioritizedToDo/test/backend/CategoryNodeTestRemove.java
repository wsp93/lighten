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

public class CategoryNodeTestRemove {
    
    /******************************************************************/
    /*								      */
    /*				SETUP, TEAR DOWN		      */
    /*								      */
    /******************************************************************/
    
    public CategoryNodeTestRemove() {
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
    /*				 REMOVE TESTS			      */
    /*								      */
    /******************************************************************/
    
    /******************************************************************/
    /*		    Remove: PARENT-CHILD RELATIONSHIP TESTS	      */
    /******************************************************************/
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() with input a null Node.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInputNull() throws Exception {
	CategoryNode root = new CategoryNode("root");
	root.remove(null);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() with input the Node itself.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInputSelf() throws Exception {
	CategoryNode root = new CategoryNode("root");
	root.remove(root);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() with input a Node that is not contained in the
     * list of children of the Node being removed from.
     */
    @Test
    public void testRemoveInputNotFound() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child = new CategoryNode("child");
	parent.add(child);
	
	CategoryNode cousin = new CategoryNode("cousin");
	parent.remove(cousin);
	
	assertNotNull(cousin);
	assertEquals(parent.numOfChildren(), 1);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() from a Node with empty child list.
     */
    @Test(expected = NoSuchElementException.class)
    public void testRemoveEmpty() throws Exception {
	CategoryNode childless = new CategoryNode("childless");
	CategoryNode unrelatedNode = new CategoryNode("unrelatedNode");
	
	childless.remove(unrelatedNode);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() with output resulting in a Node with empty child list.
     */
    @Test
    public void testRemoveRelationshipOutputRoot() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode child = new CategoryNode("child");
	root.add(child);
	
	root.remove(child);
	assertNoParentChildRelationship(root, "root", child, "child");
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove an internal Node that is the first child in the list of children.
     */
    @Test
    public void testRemoveRelationshipInputInternalFirst() throws Exception {
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
	
	parent.remove(child1);
	assertNoParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", grandchild1, "grandchild1");
	assertParentChildRelationship(parent, "parent", grandchild2, "grandchild2");
	assertParentChildRelationship(parent, "parent", child2, "child2");
	assertParentChildRelationship(parent, "parent", child3, "child3");
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove an internal Node that is a middle child in the list of children.
     */
    @Test
    public void testRemoveRelationshipInputInternalMiddle() throws Exception {
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
	
	parent.remove(child2);
	assertNoParentChildRelationship(parent, "parent", child2, "child2");
	assertParentChildRelationship(parent, "parent", grandchild1, "grandchild1");
	assertParentChildRelationship(parent, "parent", grandchild2, "grandchild2");
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child3, "child3");
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove an internal Node that is the last child in the list of children.
     */
    @Test
    public void testRemoveRelationshipInputInternalLast() throws Exception {
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
	
	parent.remove(child3);
	assertNoParentChildRelationship(parent, "parent", child3, "child3");
	assertParentChildRelationship(parent, "parent", grandchild1, "grandchild1");
	assertParentChildRelationship(parent, "parent", grandchild2, "grandchild2");
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child2, "child2");
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove a leaf Node.
     */
    @Test
    public void testRemoveRelationshipInputLeaf() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	CategoryNode grandchild1 = new CategoryNode("grandchild1");
	CategoryNode grandchild2 = new CategoryNode("grandchild2");
	
	parent.add(child1);
	parent.add(child2);
	
	child1.add(grandchild1);
	child1.add(grandchild2);
	
	child1.remove(grandchild1);
	assertNoParentChildRelationship(child1, "child1", grandchild1, "grandchild1");
	assertParentChildRelationship(child1, "child1", grandchild2, "grandchild2");
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child2, "child2");
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove with input a Node that is different from, but has the same 
     * name as, another Node that is in the list of children.
     */
    @Test
    public void testRemoveRelationshipDuplicateName() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child");
	CategoryNode child2 = new CategoryNode("child");
	
	parent.add(child1);
	parent.add(child2);
	parent.remove(child2);
	
	assertParentChildRelationship(parent, "parent", child1, "child");
	assertNoParentChildRelationship(parent, "parent", child2, "dup child");
    }
    
    /******************************************************************/
    /*		   Remove: PERCENT COMPLETE CONSISTENCY TESTS	      */
    /******************************************************************/

    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove from a Node that has only one (incomplete) child.
     */
    @Test
    public void testRemovePercentOne() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child");
	parent.add(child1);
	
	parent.remove(child1);
	
	assertExpectedPercent(parent, 0, 1); // 0 complete leaves, 1 total leaf
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove from a Node that has multiple children. The Node to be
     * removed is marked complete, and is a direct descendent. The result is
     * a partially complete parent.
     */
    @Test
    public void testRemovePercentInComplDirDescOutPartiallyComplete() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	complChild1.complete();
	CategoryNode complChild2 = new CategoryNode("complChild2");
	complChild2.complete();
	CategoryNode incomplChild = new CategoryNode("incomplChild");
	
	parent.add(complChild1);
	parent.add(complChild2);
	parent.add(incomplChild);
	
	parent.remove(complChild1);
	
	assertExpectedPercent(parent, 1, 2);
    }
    
    
    /*********************************************************************/
    /* Note:								 */
    /* Started using test naming convention here, as these test names are*/
    /* starting to get ridiculous... will need to refactor earlier tests.*/
    /*									 */
    /* [UnitOfWork__StateUnderTest__ExpectedBehavior]			 */
    /*********************************************************************/
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove from a Node that has multiple children. The Node to be
     * removed is incomplete, and is a direct descendent. The result is
     * a partially complete parent.
     */
    @Test
    public void removePercent_IncompleteDirectDescendent_PartiallyCompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	complChild1.complete();
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	CategoryNode incomplChild2 = new CategoryNode("incomplChild2");
	
	parent.add(complChild1);
	parent.add(incomplChild1);
	parent.add(incomplChild2);
	
	parent.remove(incomplChild1);
	
	assertExpectedPercent(parent, 1, 2);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove from a Node that has multiple children. The Node to be
     * removed is incomplete, and is a direct descendent. The result is
     * a fully complete parent.
     */
    @Test
    public void removePercent_IncompleteDirectDescendent_CompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	complChild1.complete();
	CategoryNode complChild2 = new CategoryNode("complChild2");
	complChild2.complete();
	CategoryNode incomplChild = new CategoryNode("incomplChild");
	
	parent.add(complChild1);
	parent.add(complChild2);
	parent.add(incomplChild);
	
	parent.remove(incomplChild);
	
	assertExpectedPercent(parent, 2, 2);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove from a Node that has multiple children. The Node to be
     * removed is complete, and is a direct descendent. The result is
     * a 0% complete parent.
     */
    @Test
    public void removePercent_CompleteDirectDescendent_IncompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	complChild1.complete();
	CategoryNode incomplChild = new CategoryNode("incomplChild");
	
	parent.add(complChild1);
	parent.add(incomplChild);
	
	parent.remove(complChild1);
	
	assertExpectedPercent(parent, 0, 1);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove an internal Node. The result is a 0% complete parent.
     */
    @Test
    public void removePercent_Internal_IncompleteParent() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode child = new CategoryNode("child");
	
	parent.add(internal);
	parent.add(child);
	
	CategoryNode internalChild1 = new CategoryNode("internalChild1");
	CategoryNode internalChild2 = new CategoryNode("internalChild2");
	
	internal.add(internalChild1);
	internal.add(internalChild2);
	
	parent.remove(internal);
	assertExpectedPercent(parent, 0, 3);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Remove an internal Node. The result is a partially complete parent.
     */
    @Test
    public void removePercent_Internal_PartiallyCompleteParent() throws Exception {
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
	
	parent.remove(internal);
	assertExpectedPercent(parent, 1, 3);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case:
     * BEFORE: Root at 50% completion, Internal Node at 66%.
     * DURING: Remove one of Internal's complete children.
     * AFTER: Root at 33%, Internal at 50%.
     */
    @Test
    public void removePercent_RemoveComplete_PropagateUp() throws Exception {
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
	
	internal.remove(internalChildComplete1);
	assertExpectedPercent(internal, 1, 2);
	assertExpectedPercent(root, 1, 3);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case:
     * BEFORE: Root at 25% completion, Internal Node at 33%.
     * DURING: Remove one of Internal's incomplete children.
     * AFTER: Root at 33%, Internal at 50%.
     */
    @Test
    public void removePercent_RemoveIncomplete_PropagateUp() throws Exception {
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
	
	internal.remove(incomplete);
	assertExpectedPercent(internal, 1, 2);
	assertExpectedPercent(root, 1, 3);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case:
     * BEFORE: Root at 33% completion, Internal Node at 50%.
     * DURING: Remove Internal's only incomplete child.
     * AFTER: Root at 50%, Internal at 100%.
     */
    @Test
    public void removePercent_RemoveIncomplete_PropagateUpInternalComplete() throws Exception {
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
	
	internal.remove(incomplete);
	assertExpectedPercent(internal, 1, 1);
	assertExpectedPercent(root, 1, 2);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case:
     * BEFORE: Root at 33% completion, Internal Node at 50%.
     * DURING: Remove Internal's only complete child.
     * AFTER: Root at 0%, Internal at 0%.
     */
    @Test
    public void removePercent_RemoveComplete_PropagateUpIncomplete() throws Exception {
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
	
	internal.remove(complete);
	assertExpectedPercent(internal, 0, 1);
	assertExpectedPercent(root, 0, 2);
    }
}
