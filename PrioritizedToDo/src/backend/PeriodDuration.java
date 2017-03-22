/***************************************************************************/
/*									   */
/*				  PeriodDuration.JAVA			   */
/* Contains both a Java Period (year, month, day) and Duration (seconds).  */
/*									   */
/*									   */
/***************************************************************************/

package backend;

import java.time.Duration;
import java.time.Period;


public class PeriodDuration {
    
    /******************************************************************/
    /*				  CONSTANTS			      */
    /******************************************************************/

    public static final int SECONDS_PER_MIN = 60;
    public static final int SECONDS_PER_HOUR = 3600;
    public static final int DAYS_PER_WEEK = 7;
    
    /******************************************************************/
    /*				   FIELDS			      */
    /******************************************************************/
    
    private Period period;
    private Duration duration;

    /******************************************************************/
    /*				 CONSTRUCTORS			      */
    /******************************************************************/
    
    public PeriodDuration(Period period) {
	this(period, Duration.ZERO);
    }
    
    public PeriodDuration(Duration duration) {
	this(Period.ZERO, duration);
    }
    
    public PeriodDuration(Period period, Duration duration) {
	this.period = period;
	this.duration = duration;
    }
    
    /******************************************************************/
    /*				   GETTERS			      */
    /******************************************************************/
    
    public long getSeconds() {
	return duration.getSeconds();
    }
    
    public int getMinutes() {
	return (int) (Math.ceil(duration.getSeconds() / SECONDS_PER_MIN));
    }
    
    public int getHours() {
	return (int) (Math.ceil(duration.getSeconds() / SECONDS_PER_HOUR));
    }
    
    public int getDays() {
	return period.getDays();
    }
    
    public int getWeeks() {
	return (int) (Math.ceil(period.getDays() / DAYS_PER_WEEK));
    }
    
    public int getMonths() {
	return period.getMonths();
    }
    
    public int getYears() {
	return period.getYears();
    }
}
