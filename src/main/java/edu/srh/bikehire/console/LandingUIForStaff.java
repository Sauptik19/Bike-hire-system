package edu.srh.bikehire.console;

import java.util.Scanner;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.impl.OrderServiceImpl;

public class LandingUIForStaff {
	private Entity loggedInEntity;
	
	public LandingUIForStaff(Entity loggedInEntity)
	{
		this.loggedInEntity = loggedInEntity;
	}
	public int showMenu(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("1) Dashboard \n2) Asset management \n3) Place order \n4) Generate invoice \n5) Generate report \n6) Logout");
		System.out.println("Select option: ");
		int input = sc.nextInt();
		sc.nextLine();
		try
		{
			
			switch(input)
			{
			case 1:
				int returnValueUi = callDashboardUI(sc);
				if(returnValueUi < 0)
				{
					return -1;
				}
				return showMenu(sc);
			case 2:
				int returnValueAM = callAssetManagementUI(sc);
				if(returnValueAM < 0)
				{
					return -1;
				}
				return showMenu(sc);
			case 3:
				int returnValuePO = callPlaceOrderUI(sc);
				if(returnValuePO < 0)
				{
					return -1;
				}
				return showMenu(sc);
			case 4:
				int returnValueGI = callGenerateInvoice(sc);
				if(returnValueGI < 0)
				{
					return -1;
				}
				return showMenu(sc);
			case 5:
				int returnValueGR = callGenerateReport(sc);
				if(returnValueGR < 0)
				{
					return -1;
				}
				return showMenu(sc);
			case 6:
				return -1;
			default:
				//ERRORMESSAGE: Invalid option selected.
				throw new BikeHireSystemException(10118);
			}
		}
		catch(BikeHireSystemException exp)
		{
			System.out.println(exp.getDisplayMessage());
		}
		return this.showMenu(sc);
	}
	
	private int callDashboardUI(Scanner sc) throws BikeHireSystemException
	{
		DashboardUI dashboardUI = new DashboardUI(loggedInEntity);
		return dashboardUI.showDashboard(sc);
	}
	
	private int callAssetManagementUI(Scanner sc) throws BikeHireSystemException
	{
		AssetManagementUI assetManagementUI = new AssetManagementUI(loggedInEntity);
		return assetManagementUI.manageAssets(sc);
	}
	
	private int callPlaceOrderUI(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("Do you want to do group booking? (y/n) ");
		String input = sc.nextLine();
		if(input.equalsIgnoreCase("y"))
		{
			GroupBookingUI groupBookingUI = new GroupBookingUI(loggedInEntity, false);
			return groupBookingUI.processGroupBooking(sc);
		}
		
		System.out.println("Enter Bike Id : ");
		int bikeId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter User Id : ");
		int userId = sc.nextInt();
		sc.nextLine();
		PlaceOrderUI placeOrderUI = new PlaceOrderUI(loggedInEntity, bikeId);
		try
		{			
			boolean status = placeOrderUI.processOrder(sc, false, userId);
		}
		catch(BikeHireSystemException exp)
		{
			System.out.println(exp.getDisplayMessage());
		}
		return 0;
	}
	
	private int callGenerateInvoice(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("Enter order id : ");
		int orderId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter warehouse id : ");
		int warehouseId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter damage charges if any : ");
		int damageCharges = sc.nextInt();
		sc.nextLine();
		OrderServiceImpl orderService = new OrderServiceImpl();
		String invoiceId = orderService.generateInvoice( damageCharges, warehouseId, orderId);
		System.out.println("Invoice generated successfully! Invoice id : " + invoiceId);
		return 0;
	}
	
	private int callGenerateReport(Scanner sc) throws BikeHireSystemException
	{
		ReportGeneratorUI reportUI = new ReportGeneratorUI(loggedInEntity);
		return reportUI.processReport(sc);
	}
}
