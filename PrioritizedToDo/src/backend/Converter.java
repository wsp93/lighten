/***************************************************************************/
/*									   */
/*				Converter.JAVA				   */
/*			     Makes type conversions.			   */
/*									   */
/*									   */
/***************************************************************************/

package backend;


public class Converter {

    /**
     * Divide 2 ints and get an integer percentage.
     * @param num The numerator
     * @param den The denominator
     * @return The resulting percentage. 
     * Ex.: If num = 2 and den = 3, returns 66 because 2/3 = 0.66 = 66%.
     * @throws Exception If the denominator is 0.
     */
    public static int getPercentage(int num, int den) throws Exception {
	if(den == 0) {
	    throw new Exception("ERROR: Divide by 0");
	}
	
	double percentage = (double) num / den * 100;
	return (int) percentage;
    }

}
