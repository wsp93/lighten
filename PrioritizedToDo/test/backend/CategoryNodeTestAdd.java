package backend;

import static backend.Helper.assertParentChildRelationship;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryNodeTestAdd {
    
    ////////////////////////////////////////////////////////////////////
    //				   TO DO			      //
    ////////////////////////////////////////////////////////////////////
    //								      //
    // -Refactor......						      //
    ////////////////////////////////////////////////////////////////////
    
    /******************************************************************/
    /*								      */
    /*				SETUP, TEAR DOWN		      */
    /*								      */
    /******************************************************************/
    
    public CategoryNodeTestAdd() {
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
    /*				   ADD TESTS			      */
    /*								      */
    /******************************************************************/
    
    /******************************************************************/
    /*		      Add: PARENT-CHILD RELATIONSHIP TESTS	      */
    /******************************************************************/
    
    /*************************************/
    /* Type: Null, Root, Internal, Leaf  */
    /*************************************/
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test add() with input a null Node.
     */
    @Test(expected = Exception.class)
    public void testAddInputNull() {
	CategoryNode parent = new CategoryNode("parent");
	parent.add(null);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test add() with input a Node with null name.
     */
    @Test(expected = Exception.class)
    public void testAddNullName() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child = new CategoryNode(null);
	parent.add(child);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for parent-child relationship, with output resulting in a
     * single childless Node.
     */
    @Test
    public void testAddRelationshipOutputRoot() {
	CategoryNode root = new CategoryNode("root");
	assertTrue("Root should have no children", root.numOfChildren() == 0);
	assertEquals("Root should have no parent", null, root.getParent());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test add() with input the Internal Node of another Tree.
     */
    @Test(expected = Exception.class)
    public void testAddInputInternal() {
	CategoryNode root1 = new CategoryNode("root1");
	
	CategoryNode root2 = new CategoryNode("root2");
	CategoryNode internal = new CategoryNode("internal");
	CategoryNode leaf = new CategoryNode("leaf");
	root2.add(internal);
	internal.add(leaf);
	
	root1.add(internal);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test add() with input the Leaf Node of another Tree.
     */
    @Test(expected = Exception.class)
    public void testAddInputLeaf() {
	CategoryNode root1 = new CategoryNode("root1");
	
	CategoryNode root2 = new CategoryNode("root2");
	CategoryNode leaf = new CategoryNode("leaf");
	root2.add(leaf);
	
	root1.add(leaf);
    }
    
    /*************************************/
    /* # of Children: 0, 1, Many         */
    /*************************************/
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for parent-child relationship, where the Node being added
     * has zero children.
     */
    @Test
    public void testAddRelationshipZeroChildren() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child = new CategoryNode("child");
	
	parent.add(child);
	
	assertParentChildRelationship(parent, "parent", child, "child");
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for parent-child relationship, where the Node being added
     * has 1 child.
     */
    @Test
    public void testAddRelationshipOneChild() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child = new CategoryNode("child");
	parent.add(child);
	
	CategoryNode grandparent = new CategoryNode("grandparent");
	grandparent.add(parent);
	
	assertParentChildRelationship(parent, "parent", child, "child");
	assertParentChildRelationship(grandparent, "grandparent", parent, "parent");
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for parent-child relationship, where the Node being added
     * has multiple children.
     */
    @Test
    public void testAddRelationshipMultipleChildren() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	
	parent.add(child1);
	parent.add(child2);
	
	CategoryNode grandparent = new CategoryNode("grandparent");
	grandparent.add(parent);
	
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child2, "child2");
	assertParentChildRelationship(grandparent, "grandparent", parent, "parent");
    }
    
    /*************************************/
    /* Location: First, Middle, Last     */
    /*************************************/
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for parent-child relationship, adding to the first child
     * of a Node's list of children.
     */
    @Test
    public void testAddRelationshipToFirstChild() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	CategoryNode child3 = new CategoryNode("child3");
	
	parent.add(child1);
	parent.add(child2);
	parent.add(child3);
	
	CategoryNode grandchild = new CategoryNode("grandchild");
	child1.add(grandchild);
	
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child2, "child2");
	assertParentChildRelationship(parent, "parent", child3, "child3");
	assertParentChildRelationship(child1, "child1", grandchild, "grandchild");
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for parent-child relationship, adding to one of the middle 
     * children in a Node's list of children.
     */
    @Test
    public void testAddRelationshipToMiddleChild() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	CategoryNode child3 = new CategoryNode("child3");
	
	parent.add(child1);
	parent.add(child2);
	parent.add(child3);
	
	CategoryNode grandchild = new CategoryNode("grandchild");
	child2.add(grandchild);
	
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child2, "child2");
	assertParentChildRelationship(parent, "parent", child3, "child3");
	assertParentChildRelationship(child2, "child2", grandchild, "grandchild");
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for parent-child relationship, adding to the last child
     * of a Node's list of children.
     */
    @Test
    public void testAddRelationshipToLastChild() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child1");
	CategoryNode child2 = new CategoryNode("child2");
	CategoryNode child3 = new CategoryNode("child3");
	
	parent.add(child1);
	parent.add(child2);
	parent.add(child3);
	
	CategoryNode grandchild = new CategoryNode("grandchild");
	child3.add(grandchild);
	
	assertParentChildRelationship(parent, "parent", child1, "child1");
	assertParentChildRelationship(parent, "parent", child2, "child2");
	assertParentChildRelationship(parent, "parent", child3, "child3");
	assertParentChildRelationship(child3, "child3", grandchild, "grandchild");
    }
    
    /*************************************/
    /* Duplicates		         */
    /*************************************/
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for add(), with input a Node that is already in the list
     * of children.
     */
    @Test(expected = Exception.class)
    public void testAddDuplicateNode() {
	CategoryNode root = new CategoryNode("root");
	CategoryNode child = new CategoryNode("child");
	
	root.add(child);
	root.add(child);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test for add(), with input a Node that is different from,
     * but has the same name as, another Node that is already in the list
     * of children.
     */
    @Test
    public void testAddRelationshipDuplicateName() {
	CategoryNode parent = new CategoryNode("parent");
	CategoryNode child1 = new CategoryNode("child");
	CategoryNode child2 = new CategoryNode("child");
	
	parent.add(child1);
	parent.add(child2);
	
	assertParentChildRelationship(parent, "parent", child1, "child");
	assertParentChildRelationship(parent, "parent", child2, "dup child");
    }
    
    /******************************************************************/
    /*		      Add: PERCENT COMPLETE CONSISTENCY TESTS	      */
    /******************************************************************/
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test add() for consistent percentComplete, with resulting in a 
     * single childless Node.
     */
    @Test
    public void testAddPercentOutputRoot() {
	CategoryNode root = new CategoryNode("root");
	assertTrue("Root just added; should be 0% complete", root.getPercentComplete() == 0);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: 
     */
    @Test
    public void testAddPercent() {
	fail("The test case is a prototype.");
    }
}
