package edu.srh.bikehire.jasper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportingConstants {
	
	public static final String FILE_DIRECTORY_BIKERENTEDREPORTS_DAILY = System.getProperty("user.dir") + "/Reports/BikeRentedReports_" + getDateInString() + "_DailyReport.xlsx";
	public static final String FILE_DIRECTORY_BIKERENTEDREPORTS_WEEKLY = System.getProperty("user.dir") + "/Reports/BikeRentedReports_" + getDateInString() + "_WeeklyReport.xlsx";
	public static final String FILE_DIRECTORY_BIKERENTEDREPORTS_MONTHLY = System.getProperty("user.dir") + "/Reports/BikeRentedReports_" + getDateInString() + "_MonthlyReport.xlsx";
	public static final String FILE_DIRECTORY_INVOICEREPORTS_DAILY = System.getProperty("user.dir") + "/Reports/InvoiceReports_" + getDateInString() + "_DailyReport.xlsx";
	public static final String FILE_DIRECTORY_INVOICEREPORTS_WEEKLY = System.getProperty("user.dir") + "/Reports/InvoiceReports_" + getDateInString() + "_WeeklyReport.xlsx";
	public static final String FILE_DIRECTORY_INVOICEREPORTS_MONTHLY = System.getProperty("user.dir") + "/Reports/InvoiceReports_" + getDateInString() + "_MonthlyReport.xlsx";
	
	public static String getDateInString()
	{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String dateStr = dateFormat.format(date);
		return dateStr;
	}
}
