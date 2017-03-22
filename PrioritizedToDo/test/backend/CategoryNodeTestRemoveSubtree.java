/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

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
}
