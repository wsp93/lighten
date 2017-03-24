package backend;

import static backend.Helper.assertExpectedPercent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryNodeTestComplete {
    
    private CategoryNode oneChildTree_parent;
    private CategoryNode oneChildTree_child;
    
    private CategoryNode twoChildTree_parent;
    private CategoryNode twoChildTree_child1;
    private CategoryNode twoChildTree_child2;
    
    private CategoryNode threeLayerTree_layer1_root;
    private CategoryNode threeLayerTree_layer2_internal;
    private CategoryNode threeLayerTree_layer2_leaf;
    private CategoryNode threeLayerTree_layer3_leaf1;
    private CategoryNode threeLayerTree_layer3_leaf2;
    
    public CategoryNodeTestComplete() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
	initOneChildTree();
	initTwoChildTree();
	initThreeLayerTree();
    }
    
    @After
    public void tearDown() {
    }
    
    private void initOneChildTree() {
	oneChildTree_parent = new CategoryNode("parent");
	oneChildTree_child = new CategoryNode("child");
	oneChildTree_parent.add(oneChildTree_child);
    }
    
    private void initTwoChildTree() {
	twoChildTree_parent = new CategoryNode("parent");
	twoChildTree_child1 = new CategoryNode("child1");
	twoChildTree_child2 = new CategoryNode("child2");
	
	twoChildTree_parent.add(twoChildTree_child1);
	twoChildTree_parent.add(twoChildTree_child2);
    }
    
    private void initThreeLayerTree() {
	threeLayerTree_layer1_root = new CategoryNode("layer1 root");
	threeLayerTree_layer2_internal = new CategoryNode("layer2 internal");
	threeLayerTree_layer2_leaf = new CategoryNode("layer2 leaf");
	
	threeLayerTree_layer1_root.add(threeLayerTree_layer2_internal);
	threeLayerTree_layer1_root.add(threeLayerTree_layer2_leaf);
	
	threeLayerTree_layer3_leaf1 = new CategoryNode("layer3 leaf1");
	threeLayerTree_layer3_leaf2 = new CategoryNode("layer3 leaf2");
	
	threeLayerTree_layer2_internal.add(threeLayerTree_layer3_leaf1);
	threeLayerTree_layer2_internal.add(threeLayerTree_layer3_leaf2);
    }
    
    /******************************************************************/
    /*								      */
    /*			     SET PERCENT COMPLETE TESTS		      */
    /*								      */
    /******************************************************************/
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: One incomplete root Node.
     * DURING: Set that Node to complete.
     * AFTER: Root is complete.
     */
    @Test
    public void completeOneNode_SetComplete_CompleteNode() {
	CategoryNode root = new CategoryNode("root");
	root.complete();
	
	assertExpectedPercent(100, root.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: One incomplete root Node with a single incomplete child.
     * DURING: Set the child to complete.
     * AFTER: Root and child are both complete.
     */
    @Test
    public void completeOneChild_SetChildComplete_BothComplete() {
	oneChildTree_child.complete();
	assertExpectedPercent(100, oneChildTree_parent.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: One incomplete root Node with a single incomplete child.
     * DURING: Set Root to complete.
     * AFTER: Root and child are both complete.
     */
    @Test
    public void completeOneChild_SetRootComplete_BothComplete() {
	oneChildTree_parent.complete();
	assertExpectedPercent(100, oneChildTree_child.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A root Node with multiple children, all of which are incomplete.
     * DURING: Set one of the children to complete.
     * AFTER: Root is partially complete.
     */
    @Test
    public void completeMultChild_SetOneDirectDescendentComplete_RootPartiallyComplete() {
	twoChildTree_child1.complete();
	assertExpectedPercent(50, twoChildTree_parent.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A root Node with multiple children, all of which are incomplete.
     * DURING: Set all children to complete.
     * AFTER: Root is fully complete.
     */
    @Test
    public void completeMultChild_SetAllDirectDescendentsComplete_RootComplete() {
	twoChildTree_child1.complete();
	twoChildTree_child2.complete();
	
	assertExpectedPercent(100, twoChildTree_parent.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A root Node with multiple children: 1 complete, 1 incomplete.
     * DURING: Set Root to complete.
     * AFTER: All descendents are complete.
     */
    @Test
    public void completeMultChild_SetRootComplete_AllDescendentsComplete() {
	twoChildTree_child2.complete();
	twoChildTree_parent.complete();
	
	assertExpectedPercent(100, twoChildTree_child1.getPercentComplete());
	assertExpectedPercent(100, twoChildTree_child2.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A tree with multiple layers.
     * DURING: Set Root to complete.
     * AFTER: All descendents are complete.
     */
    @Test
    public void completeMultLayer_SetRootComplete_AllDescendentsComplete() {
	threeLayerTree_layer1_root.complete();
	
	assertExpectedPercent(100, threeLayerTree_layer2_internal.getPercentComplete());
	assertExpectedPercent(100, threeLayerTree_layer2_leaf.getPercentComplete());
	assertExpectedPercent(100, threeLayerTree_layer3_leaf1.getPercentComplete());
	assertExpectedPercent(100, threeLayerTree_layer3_leaf2.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A tree with multiple layers.
     * DURING: Set Internal to complete.
     * AFTER: Internal's descendents are complete; Root is partially complete;
     * Root's other child is the same as before (incomplete).
     */
    @Test
    public void completeMultLayer_SetInternalComplete_InternalDescendentsComplete() {
	threeLayerTree_layer2_internal.complete();
	
	assertExpectedPercent(100, threeLayerTree_layer3_leaf1.getPercentComplete());
	assertExpectedPercent(100, threeLayerTree_layer3_leaf2.getPercentComplete());
	
	assertExpectedPercent(0, threeLayerTree_layer2_leaf.getPercentComplete());
	
	assertExpectedPercent(66, threeLayerTree_layer1_root.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A tree with multiple layers.
     * DURING: Set one of the leaves at the last layer to complete.
     * AFTER: Percent complete propagates up to Internal and Root.
     */
    @Test
    public void completeMultLayer_SetLastLayerLeafComplete_PropagateUp() {
	threeLayerTree_layer3_leaf1.complete();
	
	assertExpectedPercent(0, threeLayerTree_layer3_leaf2.getPercentComplete());
	
	assertExpectedPercent(50, threeLayerTree_layer2_internal.getPercentComplete());
	assertExpectedPercent(0, threeLayerTree_layer2_leaf.getPercentComplete());
	
	assertExpectedPercent(33, threeLayerTree_layer1_root.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A tree with multiple layers.
     * DURING: Set the leaf at the middle layer to complete.
     * AFTER: Percent complete propagates up to Root.
     */
    @Test
    public void completeMultLayer_SetMidLayerLeafComplete_PropagateUp() {
	threeLayerTree_layer2_leaf.complete();
	
	assertExpectedPercent(0, threeLayerTree_layer3_leaf1.getPercentComplete());
	assertExpectedPercent(0, threeLayerTree_layer3_leaf2.getPercentComplete());
	assertExpectedPercent(0, threeLayerTree_layer2_internal.getPercentComplete());
	
	assertExpectedPercent(33, threeLayerTree_layer1_root.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A tree with multiple layers. All leaves on the last layer, except
     * one, are already complete.
     * DURING: Set the last incomplete leaf at the last layer to complete.
     * AFTER: The Internal parent node is set to complete.
     */
    @Test
    public void completeMultLayer_SetLastLayerLastIncompleteLeafComplete_InternalComplete() {
	threeLayerTree_layer3_leaf1.complete(); // before
	threeLayerTree_layer3_leaf2.complete(); // during
	
	// after
	assertExpectedPercent(100, threeLayerTree_layer2_internal.getPercentComplete());
	assertExpectedPercent(66, threeLayerTree_layer1_root.getPercentComplete());
    }
    
    /**
     * Test of complete method, of class CategoryNode.
     * Case:
     * BEFORE: A tree with multiple layers. All leaves, except one, are complete.
     * DURING: Set the last incomplete leaf to complete.
     * AFTER: All Nodes are set to complete.
     */
    @Test
    public void completeMultLayer_SetLastIncompleteLeafComplete_AllComplete() {
	// before
	threeLayerTree_layer3_leaf1.complete();
	threeLayerTree_layer2_leaf.complete();
	
	// during
	threeLayerTree_layer3_leaf2.complete();
	
	// after
	assertExpectedPercent(100, threeLayerTree_layer2_internal.getPercentComplete());
	assertExpectedPercent(100, threeLayerTree_layer1_root.getPercentComplete());
    }
}
