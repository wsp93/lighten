package backend;

import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryNodeTestDeadline {
    
    ////////////////////////////////////////////////////////////////////
    //				   TO DO			      //
    ////////////////////////////////////////////////////////////////////
    //								      //
    // -Test Deadline-Complete cases				      //
    // -Refactor......						      //
    ////////////////////////////////////////////////////////////////////
    
    public CategoryNodeTestDeadline() {
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
    /*				 DEADLINE TESTS			      */
    /*								      */
    /******************************************************************/
    
    /******************************************************************/
    /*			     NO OR INVALID DEADLINE		      */
    /******************************************************************/
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: No deadline.
     */
    @Test(expected = Exception.class)
    public void testGetTimeRemainingNoDeadline() {
	CategoryNode noDeadline = new CategoryNode("noDeadline");
	noDeadline.setDeadline(null);
	
	noDeadline.getTimeRemaining();
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is beyond MAX_DEADLINE time away from now.
     */
    @Test(expected = Exception.class)
    public void testGetTimeRemainingDeadlineBeyondMax() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().plusYears(CategoryNode.MAX_DEADLINE)
			.plusSeconds(1));
    }
    
    /******************************************************************/
    /*			     MARKED COMPLETE TESTS		      */
    /******************************************************************/
    
    /*************************************/
    /* Past			         */
    /*************************************/
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline passed over 24 hours ago, and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlinePassedComplete() {
	// How can I set the completion date to some time
	// before the deadline, without making setCompletionDate() public?
	fail("The test case is a prototype.");
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline passed exactly 24 hours ago, and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineOneDayPassedComplete() {
	fail("The test case is a prototype.");
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline passed within 24 hours ago, and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineWithinOneDayPassedComplete() {
	fail("The test case is a prototype.");
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline just passed, and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineJustPassedComplete() {
	fail("The test case is a prototype.");
    }
    
    /*************************************/
    /* Present			         */
    /*************************************/
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is now, and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineNowComplete() {
	CategoryNode node = new CategoryNode("node");
	node.complete();
	node.setDeadline(LocalDateTime.now());
	assertEquals(node.getTimeRemaining().getDays(), 0);
    }
    
    /*************************************/
    /* Future			         */
    /*************************************/
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is just ahead, and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineJustAheadComplete() {
	CategoryNode node = new CategoryNode("node");
	node.complete(); // need to set date complete again, to distinguish complete vs incomplete cases
	node.setDeadline(LocalDateTime.now().plusSeconds(1));
	assertEquals(node.getTimeRemaining().getSeconds(), 1);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is within 24 hours, and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineWithinOneDayAwayComplete() {
	CategoryNode node = new CategoryNode("node");
	node.complete(); // need to set date complete again, to distinguish complete vs incomplete cases
	node.setDeadline(LocalDateTime.now().plusHours(12));
	assertEquals(node.getTimeRemaining().getDays(), 1);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is exactly 24 hours from now, and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineOneDayAwayComplete() {
	CategoryNode node = new CategoryNode("node");
	node.complete();  // need to set date complete again, to distinguish complete vs incomplete cases
	node.setDeadline(LocalDateTime.now().plusHours(24));
	assertEquals(node.getTimeRemaining().getDays(), 1);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is over 24 hours (and less than MAX_DEADLINE) away, 
     * and Node is marked as completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineFutureComplete() {
	CategoryNode node = new CategoryNode("node");
	node.complete();  // need to set date complete again, to distinguish complete vs incomplete cases
	node.setDeadline(LocalDateTime.now().plusDays(10));
	assertEquals(node.getTimeRemaining().getDays(), 10);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is exactly at MAX_DEADLINE away, and Node is marked as 
     * completed.
     */
    @Test
    public void testGetTimeRemainingDeadlineMaxComplete() {
	CategoryNode node = new CategoryNode("node");
	node.complete();  // need to set date complete again, to distinguish complete vs incomplete cases
	node.setDeadline(LocalDateTime.now().plusYears(CategoryNode.MAX_DEADLINE));
	assertEquals(node.getTimeRemaining().getYears(), CategoryNode.MAX_DEADLINE);
    }
    
    /******************************************************************/
    /*			    MARKED INCOMPLETE TESTS		      */
    /******************************************************************/
    
    /*************************************/
    /* Past			         */
    /*************************************/
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline passed over 24 hours ago, and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlinePassedIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().minusDays(10));
	assertEquals(node.getTimeRemaining().getDays(), -10);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline passed exactly 24 hours ago, and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineOneDayPassedIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().minusDays(1));
	assertEquals(node.getTimeRemaining().getDays(), -1);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline passed within 24 hours ago, and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineWithinOneDayPassedIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().minusHours(12));
	assertEquals(node.getTimeRemaining().getDays(), -1);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline just passed, and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineJustPassedIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().minusSeconds(1));
	assertEquals(node.getTimeRemaining().getDays(), -1);
    }
    
    /*************************************/
    /* Present			         */
    /*************************************/
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is now, and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineNowIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now());
	assertEquals(node.getTimeRemaining().getDays(), -1);
    }
    
    /*************************************/
    /* Future			         */
    /*************************************/
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is just ahead, and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineJustAheadIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().plusSeconds(1));
	assertEquals(node.getTimeRemaining().getDays(), 0);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is within 24 hours, and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineWithinOneDayAwayIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().plusHours(12));
	assertEquals(node.getTimeRemaining().getDays(), 0);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is exactly 24 hours from now, and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineOneDayAwayIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().plusDays(1));
	assertEquals(node.getTimeRemaining().getDays(), 1);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is over 24 hours (and less than MAX_DEADLINE) away, 
     * and Node is marked as incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineFutureIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().plusDays(10));
	assertEquals(node.getTimeRemaining().getDays(), 10);
    }
    
    /**
     * Test of getTimeRemaining method, of class CategoryNode.
     * Case: Deadline is exactly at MAX_DEADLINE away, and Node is marked as 
     * incomplete.
     */
    @Test
    public void testGetTimeRemainingDeadlineMaxIncomplete() {
	CategoryNode node = new CategoryNode("node");
	node.setDeadline(LocalDateTime.now().plusYears(CategoryNode.MAX_DEADLINE));
	assertEquals(node.getTimeRemaining().getYears(), CategoryNode.MAX_DEADLINE);
    }
}
