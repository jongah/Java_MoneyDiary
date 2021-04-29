package Other;

import java.util.Calendar;
import java.util.GregorianCalendar;
public class Date_ymd {
	private static final GregorianCalendar today = new GregorianCalendar();
	
	public static int getYear() {
		return Date_ymd.today.get(Date_ymd.today.YEAR);
	}
	public static int getDate() {
		return Date_ymd.today.get(Date_ymd.today.DATE);
	}
	public static int getMonth() {
		return (Date_ymd.today.get(Date_ymd.today.MONTH) + 1);
	}
	public static int getlast() {
		return Date_ymd.today.getActualMaximum(Date_ymd.today.DAY_OF_MONTH);
	}
}
