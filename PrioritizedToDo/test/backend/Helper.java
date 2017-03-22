package backend;

import static org.junit.Assert.*;

public class Helper {
    
    private static boolean isChild(CategoryNode child, CategoryNode parent) {
	
	for(int i = 0; i < parent.numOfChildren(); i++) {
	    
	    boolean isSame = parent.getChild(i) == child; 
	    if(isSame) {
		return true;
	    }
	}
	
	return false;
    }
    
    public static void assertParentChildRelationship(CategoryNode parent, String parentName,
					    CategoryNode child, String childName) {
	assertTrue(childName + " is not " + parentName + "'s child", 
		   isChild(child, parent));
	assertSame(parentName + " is not " + childName + "'s parent", 
		   parent, child.getParent());
    }
    
    public static void assertNoParentChildRelationship(CategoryNode parent, String parentName,
					    CategoryNode child, String childName) {
	assertFalse(childName + " is " + parentName + "'s child", 
		   isChild(child, parent));
	assertNotSame(parentName + " is " + childName + "'s parent", 
		   parent, child.getParent());
    }
    
}
