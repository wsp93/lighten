package backend;

import static backend.Helper.assertNoParentChildRelationship;
import static backend.Helper.assertParentChildRelationship;
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
    @Test(expected = Exception.class)
    public void testRemoveInputNull() {
	CategoryNode root = new CategoryNode("root");
	root.remove(null);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() with input the Node itself.
     */
    @Test(expected = Exception.class)
    public void testRemoveInputSelf() {
	CategoryNode root = new CategoryNode("root");
	root.remove(root);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() with input a Node that is not contained in the
     * list of children of the Node being removed from.
     */
    @Test(expected = Exception.class)
    public void testRemoveInputNotFound() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child = new CategoryNode("child");
	parent.add(child);
	
	CategoryNode cousin = new CategoryNode("cousin");
	parent.remove(cousin);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() from a Node with empty child list.
     */
    @Test(expected = Exception.class)
    public void testRemoveEmpty() {
	CategoryNode childless = new CategoryNode("childless");
	CategoryNode unrelatedNode = new CategoryNode("unrelatedNode");
	
	childless.remove(unrelatedNode);
    }
    
    /**
     * Test of remove method, of class CategoryNode.
     * Case: Test remove() with output resulting in a Node with empty child list.
     */
    @Test
    public void testRemoveRelationshipOutputRoot() {
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
    public void testRemoveRelationshipInputInternalFirst() {
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
    public void testRemoveRelationshipInputInternalMiddle() {
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
    public void testRemoveRelationshipInputInternalLast() {
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
    public void testRemoveRelationshipInputLeaf() {
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
    public void testRemoveRelationshipDuplicateName() {
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

}
