package days_from_birth;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class DaysSinceBirth {

	public static void main(String[] argc) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input your year of birth ");
		int year = sc.nextInt();
		System.out.println("Please input your month of birth ");
		int month = sc.nextInt();
		System.out.println("Please input your day of birth ");
		int day = sc.nextInt();
		int days = daysFromBirth(year, month, day);
		System.out.println(days);
		/*int year = 1999;
		int month = 5;
		int day = 9;
		ZonedDateTime zdt = ZonedDateTime.parse(year + "-0" + month + "-0" + day + "T12:30:40+01:00[Europe/London]");
		System.out.println(zdt.getYear() + " " + zdt.getMonth() + " " + zdt.getDayOfMonth() + " " + zdt.getHour() + zdt.getMinute() + zdt.getSecond());
		*/
		
	}
	
	public static int daysFromBirth(int year, int month, int day) {
		ZonedDateTime zdtNOW = ZonedDateTime.now();
		int cYear = zdtNOW.getYear();
		int days = 0;
		String sday;
		String smonth;
		
		if(day < 10) {
			sday = "0" + day;
		}else {
			sday = "" + day;
		}
		if(month < 10) {
			smonth = "0" + month;
		}else
			smonth = "" + month;
		
		int i = year;
		
		ZonedDateTime zdtB = ZonedDateTime.parse(year + "-12-31T12:00:00-07:00[America/Los_Angeles]");
		while(i != cYear) {
			days += zdtB.getDayOfYear();
			i++;
			zdtB = ZonedDateTime.parse(i + "-12-31T12:00:00-07:00[America/Los_Angeles]");
		}
		zdtB = ZonedDateTime.parse(year + "-" + smonth + "-" + sday + "T12:00:00-07:00[America/Los_Angeles]");
		i = month;
		
		while(i != zdtNOW.getMonthValue()) {
			if(i <  zdtNOW.getMonthValue()) {
				days += zdtB.getDayOfMonth();
				i++;
				if(i < 10)
					zdtB = ZonedDateTime.parse(year + "-0" + i + "-" + sday + "T12:00:00-07:00[America/Los_Angeles]");
				else
					zdtB = ZonedDateTime.parse(year + "-" + month + "-" + sday + "T12:00:00-07:00[America/Los_Angeles]");
			}else {
				days -= zdtB.getDayOfMonth();
				i--;
				if(i < 10)
					zdtB = ZonedDateTime.parse(year + "-0" + i + "-"+ sday + "T12:00:00-07:00[America/Los_Angeles]");
				else
					zdtB = ZonedDateTime.parse(year + "-" + month + "-" + sday + "T12:00:00-07:00[America/Los_Angeles]");
			}
		}
		
		
		days += zdtNOW.getDayOfMonth() - day;
		
		
		return days;
	}
}
