/***************************************************************************/
/*									   */
/*				  DBSetup.JAVA				   */
/*                      Connects to and initializes the database.	   */
/*									   */
/*									   */
/***************************************************************************/

package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static sql.SqlQueries.CREATE_SUBCATEGORY_TABLE;
import static sql.SqlQueries.CREATE_CATEGORY_TABLE;
import static sql.SqlQueries.GET_MAX_ID;
import static sql.SqlQueries.GET_SORTED_IDS;

public class DBSetup {
    
    /******************************************************************/
    /*				  CONSTANTS			      */
    /******************************************************************/
    
    public static final String DB_URL = "jdbc:sqlite:main.db";
    
    /******************************************************************/
    /*				  FIELDS			      */
    /******************************************************************/
    
    private static Connection conn;
    private static Statement createTableStmt;
    
    /******************************************************************/
    /*				   SETUP			      */
    /******************************************************************/
    
    public static void connect() throws SQLException {
	if(conn == null) {
	    conn = DriverManager.getConnection(DB_URL);
	    createTableStmt = conn.createStatement();
	}
    }
    
    public static void createCategoryTable() throws SQLException {
	connect();
	createTableStmt.execute(CREATE_CATEGORY_TABLE);
    }
    
    public static void createSubcategoryTable() throws SQLException {
	connect();
	createTableStmt.execute(CREATE_SUBCATEGORY_TABLE);
    }
    
    /******************************************************************/
    /*				 ADD, REMOVE			      */
    /******************************************************************/
    
    public static void add() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void remove() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void removeSubtree() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /******************************************************************/
    /*				   GETTERS			      */
    /******************************************************************/
    
    public static void getID() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static long getNextAvailableID() throws SQLException {
	connect();
	long nextAvailableID = 0;
	Statement stmt = conn.createStatement();
	
	ResultSet result = stmt.executeQuery(GET_MAX_ID);
	if(result.next()) {
	    long maxIDInTable = result.getLong("id");
	    nextAvailableID = maxIDInTable + 1;
	    
	    boolean overflow = nextAvailableID < 0;
	    if(overflow) {
		nextAvailableID = findUnusedID();
	    }
	}
	
	return nextAvailableID;
    }
    
    private static long findUnusedID() throws SQLException {
	Statement stmt = conn.createStatement();
	ResultSet resultSet = stmt.executeQuery(GET_SORTED_IDS);
	
	long nextConsecutive = 0;
	while(resultSet.next()) {
	    if(nextConsecutive == resultSet.getLong("id")) {
		nextConsecutive++;
	    }
	    else return nextConsecutive;
	}
	
	throw new RuntimeException("All IDs used");
    }
    
    public static void getName() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void getIsComplete() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void getDeadline() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void getCompletionDate() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void getChildren() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void getParent() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /******************************************************************/
    /*				   SETTERS			      */
    /******************************************************************/

    public static void setName() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void setIsComplete() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void setDeadline() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void setCompletionDate() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void setChild() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void setParent() {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
