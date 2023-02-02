package work.service;

import java.time.LocalDate;

public class DatePg {
		LocalDate now = LocalDate.now();
		
		int year = now.getYear();
		int month = now.getMonthValue();
		
		
		
		
		
		public int getYear() {
			return year;
		}
		public int getMonth() {
			return month;
		}
		
		

}
