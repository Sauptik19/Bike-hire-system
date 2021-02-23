package edu.srh.bikehire.console;

import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.jasper.ReportGenerator;
import edu.srh.bikehire.service.core.Entity;

public class ReportGeneratorUI {
	private Entity loggedInEntity;
	
	public ReportGeneratorUI(Entity entity)
	{
		loggedInEntity = entity;
	}
	
	public int processReport(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("1) Rented Bike Report \n2) Invoice Report \n3) Back");
		System.out.println("Select option: ");
		int input = sc.nextInt();
		sc.nextLine();
		switch(input)
		{
		case 1:
			showRentedBikeReport(sc);
			return 0;
		case 2:
			showInvoiceReport(sc);
			return 0;
		case 3:
			return 0;
		default:
			//ERRORMESSAGE: Invalid option selected.
			throw new BikeHireSystemException(10118);
		}
	}
	
	private void showRentedBikeReport(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("Do you want report for? \n1) Daily \n2) Weekly \n3) Monthly");
		System.out.println("Select option: ");
		int input = sc.nextInt();
		sc.nextLine();
		System.out.println("Please enter start date (dd/MM/yyyy) : ");
		String startDateInput = sc.nextLine();
		Calendar startDate = getCalendarFromInput(startDateInput);
		ReportGenerator generator = new ReportGenerator();
		String filePath = "";
		switch(input)
		{
			case 1: 
				filePath = generator.createRentedBikeReportToday(startDate);
				if(filePath == null || filePath.isEmpty())
				{
					System.out.println("Report not generated. Please try again.");
					break;
				}
				System.out.println("Report successfully generated at : " + filePath);
				break;
			case 2:
				filePath = generator.createRentedBikeReportWeekly(startDate);
				if(filePath == null || filePath.isEmpty())
				{
					System.out.println("Report not generated. Please try again.");
					break;
				}
				System.out.println("Report successfully generated at : " + filePath);
				break;
			case 3:
				filePath = generator.createRentedBikeReportMonthly(startDate);
				if(filePath == null || filePath.isEmpty())
				{
					System.out.println("Report not generated. Please try again.");
					break;
				}
				System.out.println("Report successfully generated at : " + filePath);
				break;
			default:
				//ERRORMESSAGE: Invalid option selected.
				throw new BikeHireSystemException(10118);
		}
	}
	
	private void showInvoiceReport(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("Do you want report for? \n1) Daily \n2) Weekly \n3) Monthly");
		System.out.println("Select option: ");
		int input = sc.nextInt();
		sc.nextLine();
		System.out.println("Please enter start date (dd/MM/yyyy) : ");
		String startDateInput = sc.nextLine();
		Calendar startDate = getCalendarFromInput(startDateInput);
		ReportGenerator generator = new ReportGenerator();
		String filePath = "";
		switch(input)
		{
			case 1:
				filePath = generator.createInvoiceReportToday(startDate);
				if(filePath == null || filePath.isEmpty())
				{
					System.out.println("Report not generated. Please try again.");
					break;
				}
				System.out.println("Report successfully generated at : " + filePath);
				break;
			case 2:
				filePath = generator.createInvoiceReportWeekly(startDate);
				if(filePath == null || filePath.isEmpty())
				{
					System.out.println("Report not generated. Please try again.");
					break;
				}
				System.out.println("Report successfully generated at : " + filePath);
				break;
			case 3:
				filePath = generator.createInvoiceReportMonthly(startDate);
				if(filePath == null || filePath.isEmpty())
				{
					System.out.println("Report not generated. Please select ");
					break;
				}
				System.out.println("Report successfully generated at : " + filePath);
				break;
			default:
				//ERRORMESSAGE: Invalid option selected.
				throw new BikeHireSystemException(10118);
		}
	}
	
	private Calendar getCalendarFromInput(String input)
	{
		StringTokenizer stringTokenizer = new StringTokenizer(input, "/");
		int date = Integer.parseInt(stringTokenizer.nextToken());
		int month = Integer.parseInt(stringTokenizer.nextToken());
		int year = Integer.parseInt(stringTokenizer.nextToken());
		int modifiedMonth = 0;
		if(month == 1)
		{
			modifiedMonth = 0;
		}
		else if(month > 1 && month <=12 )
		{
			modifiedMonth = month -1;
		}
		Calendar returnCalendar = Calendar.getInstance();
		returnCalendar.set(year, modifiedMonth, date);
		return returnCalendar;
	}
}
