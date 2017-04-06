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
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {
    
    /******************************************************************/
    /*				  CONSTANTS			      */
    /******************************************************************/
    
    public static final String DB_URL = "jdbc:sqlite:main.db";
    public static final String CREATE_CATEGORY_TABLE_SQL =
	    "CREATE TABLE IF NOT EXISTS Category (\n"
	    + "id integer NOT NULL, \n"
	    + "name text NOT NULL, \n"
	    + "isComplete integer NOT NULL, \n" // SQLite doesn't use booleans
	    + "deadline text, \n"
	    + "completionDate text, \n"
	    
	    + "PRIMARY KEY(id), \n"
	    + ");";
    public static final String CREATE_SUBCATEGORY_TABLE_SQL =
	    "CREATE TABLE IF NOT EXISTS Subcategory (\n"
	    + "parentID integer NOT NULL, \n"
	    + "childID integer NOT NULL, \n"
	    
	    + "PRIMARY KEY(parentID), \n"
	    + "PRIMARY KEY(childID), \n"
	    + "FOREIGN KEY (parentID) REFERENCES Category(id), \n"
	    + "FOREIGN KEY (childID) REFERENCES Category(id), \n"
	    + ");";
    
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
	createTableStmt.execute(CREATE_CATEGORY_TABLE_SQL);
    }
    
    public static void createSubcategoryTable() throws SQLException {
	connect();
	createTableStmt.execute(CREATE_SUBCATEGORY_TABLE_SQL);
    }

}
