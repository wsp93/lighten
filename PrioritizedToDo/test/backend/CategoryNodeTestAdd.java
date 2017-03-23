package backend;

import static backend.Helper.assertExpectedPercent;
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
    @Test(expected = NullPointerException.class)
    public void testAddInputNull() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	parent.add(null);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test add() with input a Node with null name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullName() throws Exception {
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
    public void testAddRelationshipOutputRoot() throws Exception {
	CategoryNode root = new CategoryNode("root");
	assertTrue("Root should have no children", root.numOfChildren() == 0);
	assertEquals("Root should have no parent", null, root.getParent());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Test add() with input the Internal Node of another Tree.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddInputInternal() throws Exception {
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
    @Test(expected = IllegalArgumentException.class)
    public void testAddInputLeaf() throws Exception {
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
    public void testAddRelationshipZeroChildren() throws Exception {
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
    public void testAddRelationshipOneChild() throws Exception {
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
    public void testAddRelationshipMultipleChildren() throws Exception {
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
    public void testAddRelationshipToFirstChild() throws Exception {
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
    public void testAddRelationshipToMiddleChild() throws Exception {
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
    public void testAddRelationshipToLastChild() throws Exception {
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
    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateNode() throws Exception {
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
    public void testAddRelationshipDuplicateName() throws Exception {
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
    public void testAddPercentOutputRoot() throws Exception {
	CategoryNode root = new CategoryNode("root");
	assertEquals("Root just added; should be 0% complete", root.getPercentComplete(), 0);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add a single incomplete Node as a direct descendent to another 
     * incomplete Node.
     */
    @Test
    public void testAddPercentOneIncomplete() {
	CategoryNode root = new CategoryNode("root");
	CategoryNode child = new CategoryNode("child");
	root.add(child);
	
	assertEquals("Incomplete Node added to another Incomplete Node should"
		+ " result in percentComplete 0", root.getPercentComplete(), 0);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add a childless Node, and get the percentComplete of the childless
     * Node. Should return the percentComplete of that Node, NOT div by 0.
     */
    @Test
    public void testAddPercentAttemptDivByZero() {
	CategoryNode root = new CategoryNode("root");
	CategoryNode child = new CategoryNode("child");
	root.add(child);
	
	assertEquals(child.getPercentComplete(), 0);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add a single Node to an complete Node.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPercentOneComplete() {
	CategoryNode root = new CategoryNode("root");
	root.complete();
	
	CategoryNode child = new CategoryNode("child");
	root.add(child);
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add a direct descendent to a partially complete Node;
     * only one descendent is marked complete.
     */
    @Test
    public void testAddPercentPartialCompleteAddDirDesc() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode complChild = new CategoryNode("complChild");
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	CategoryNode incomplChild2 = new CategoryNode("incomplChild2");
	complChild.complete();
	
	root.add(complChild);
	root.add(incomplChild1);
	root.add(incomplChild2);
	
	root.add(new CategoryNode("incomplChild3"));
	assertExpectedPercent(25, root.getPercentComplete());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add a single grandchild to a partially complete Node;
     * only one direct descendent of the root is marked complete.
     */
    @Test
    public void testAddPercentPartialCompleteAddOneIndirDesc() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode complChild = new CategoryNode("complChild");
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	CategoryNode incomplChild2 = new CategoryNode("incomplChild2");
	complChild.complete();
	
	root.add(complChild);
	root.add(incomplChild1);
	root.add(incomplChild2);
	
	incomplChild1.add(new CategoryNode("incomplGrandchild"));
	assertExpectedPercent(33, root.getPercentComplete());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add more than one grandchild to a partially complete Node;
     * only one direct descendent of the root is marked complete.
     */
    @Test
    public void testAddPercentPartialCompleteAddMultIndirDesc() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode complChild = new CategoryNode("complChild");
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	CategoryNode incomplChild2 = new CategoryNode("incomplChild2");
	complChild.complete();
	
	root.add(complChild);
	root.add(incomplChild1);
	root.add(incomplChild2);
	
	incomplChild1.add(new CategoryNode("incomplGrandchild1"));
	incomplChild1.add(new CategoryNode("incomplGrandchild2"));
	assertExpectedPercent(25, root.getPercentComplete());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add a direct descendent to a partially complete Node;
     * more than one descendent is marked complete.
     */
    @Test
    public void testAddPercentMultPartialCompleteAddDirDesc() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	CategoryNode complChild2 = new CategoryNode("complChild2");
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	
	complChild1.complete();
	complChild2.complete();
	
	root.add(complChild1);
	root.add(complChild2);
	root.add(incomplChild1);
	
	root.add(new CategoryNode("incomplChild2"));
	assertExpectedPercent(50, root.getPercentComplete());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add a single grandchild to a partially complete Node;
     * more than one direct descendent of the root is marked complete.
     */
    @Test
    public void testAddPercentMultPartialCompleteAddOneIndirDesc() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	CategoryNode complChild2 = new CategoryNode("complChild2");
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	
	complChild1.complete();
	complChild2.complete();
	
	root.add(complChild1);
	root.add(complChild2);
	root.add(incomplChild1);
	
	incomplChild1.add(new CategoryNode("incomplGrandchild"));
	assertExpectedPercent(66, root.getPercentComplete());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Add more than one grandchild to a partially complete Node;
     * more than one direct descendent of the root is marked complete.
     */
    @Test
    public void testAddPercentMultPartialCompleteAddMultIndirDesc() throws Exception {
	CategoryNode root = new CategoryNode("root");
	CategoryNode complChild1 = new CategoryNode("complChild1");
	CategoryNode complChild2 = new CategoryNode("complChild2");
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	
	complChild1.complete();
	complChild2.complete();
	
	root.add(complChild1);
	root.add(complChild2);
	root.add(incomplChild1);
	
	incomplChild1.add(new CategoryNode("incomplGrandchild1"));
	incomplChild1.add(new CategoryNode("incomplGrandchild2"));
	assertExpectedPercent(50, root.getPercentComplete());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Check that adding a grandchild propagates the changes up to
     * both the child and the parent.
     */
    @Test
    public void testAddPercentAddGrandchildPropagateUp() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	
	CategoryNode complChild = new CategoryNode("complChild");
	complChild.complete();
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	
	parent.add(complChild);
	parent.add(incomplChild1);
	
	CategoryNode complGrandChild1 = new CategoryNode("complGrandChild1");
	complGrandChild1.complete();
	CategoryNode incomplGrandChild1 = new CategoryNode("incomplGrandChild1");
	
	incomplChild1.add(complGrandChild1);
	incomplChild1.add(incomplGrandChild1);
	
	incomplChild1.add(new CategoryNode("incomplGrandChild2"));
	assertExpectedPercent(50, parent.getPercentComplete());
	assertExpectedPercent(33, incomplChild1.getPercentComplete());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Check that adding a single great-grandchild to a (previously) 
     * childless grandchild does not affect ancestors (since no new leaves are
     * being added).
     */
    @Test
    public void testAddPercentAddGreatGrandchildPropagateUp() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	
	CategoryNode complChild = new CategoryNode("complChild");
	complChild.complete();
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	
	parent.add(complChild);
	parent.add(incomplChild1);
	
	CategoryNode complGrandChild1 = new CategoryNode("complGrandChild1");
	complGrandChild1.complete();
	CategoryNode incomplGrandChild1 = new CategoryNode("incomplGrandChild1");
	
	incomplChild1.add(complGrandChild1);
	incomplChild1.add(incomplGrandChild1);
	
	incomplGrandChild1.add(new CategoryNode("incomplGreatGrandChild"));
	assertExpectedPercent(66, parent.getPercentComplete());
	assertExpectedPercent(50, incomplChild1.getPercentComplete());
    }
    
    /**
     * Test of add method, of class CategoryNode.
     * Case: Check that adding multiple great-grandchildren to a grandchild does 
     * affect ancestors (since new leaves are being added).
     */
    @Test
    public void testAddPercentAddMultGreatGrandchildPropagateUp() throws Exception {
	CategoryNode parent = new CategoryNode("parent");
	
	CategoryNode complChild = new CategoryNode("complChild");
	complChild.complete();
	CategoryNode incomplChild1 = new CategoryNode("incomplChild1");
	
	parent.add(complChild);
	parent.add(incomplChild1);
	
	CategoryNode complGrandChild1 = new CategoryNode("complGrandChild1");
	complGrandChild1.complete();
	CategoryNode incomplGrandChild1 = new CategoryNode("incomplGrandChild1");
	
	incomplChild1.add(complGrandChild1);
	incomplChild1.add(incomplGrandChild1);
	
	incomplGrandChild1.add(new CategoryNode("incomplGreatGrandChild1"));
	incomplGrandChild1.add(new CategoryNode("incomplGreatGrandChild2"));
	assertExpectedPercent(50, parent.getPercentComplete());
	assertExpectedPercent(33, incomplChild1.getPercentComplete());
	assertExpectedPercent(0, incomplGrandChild1.getPercentComplete());
    }
}
