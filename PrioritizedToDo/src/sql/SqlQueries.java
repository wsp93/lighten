/***************************************************************************/
/*									   */
/*				  SqlQueries.JAVA			   */
/*                List of public constants representing SQL queries.	   */
/*									   */
/*									   */
/***************************************************************************/

package sql;


public class SqlQueries {
    
    /******************************************************************/
    /*				   SETUP			      */
    /******************************************************************/

    public static final String CREATE_CATEGORY_TABLE =
	    "CREATE TABLE IF NOT EXISTS Category (\n"
	    + "id integer NOT NULL, \n"
	    + "name text NOT NULL, \n"
	    + "isComplete integer NOT NULL, \n" // SQLite doesn't use booleans
	    + "deadline text, \n"
	    + "completionDate text, \n"
	    
	    + "PRIMARY KEY(id), \n"
	    + ");";
    
    public static final String CREATE_SUBCATEGORY_TABLE =
	    "CREATE TABLE IF NOT EXISTS Subcategory (\n"
	    + "parentID integer NOT NULL, \n"
	    + "childID integer NOT NULL, \n"
	    
	    + "PRIMARY KEY(parentID), \n"
	    + "PRIMARY KEY(childID), \n"
	    + "FOREIGN KEY (parentID) REFERENCES Category(id), \n"
	    + "FOREIGN KEY (childID) REFERENCES Category(id), \n"
	    + ");";
    
    /******************************************************************/
    /*				 ADD, REMOVE			      */
    /******************************************************************/
    
    /******************************************************************/
    /*				   GETTERS			      */
    /******************************************************************/
    
    public static final String GET_MAX_ID = "SELECT MAX(id) FROM Category";
    public static final String GET_SORTED_IDS = 
	    "SELECT id "
	    + "FROM Category"
	    + "ORDER BY id";
    
    /******************************************************************/
    /*				   SETTERS			      */
    /******************************************************************/

}
