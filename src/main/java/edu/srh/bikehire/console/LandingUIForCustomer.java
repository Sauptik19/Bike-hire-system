package edu.srh.bikehire.console;

import java.util.Scanner;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.Entity;

public class LandingUIForCustomer {
	
	private Entity loggedInEntity;
	
	public LandingUIForCustomer(Entity loggedInEntity)
	{
		this.loggedInEntity = loggedInEntity;
	}
	
	public int showMenu(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("1) View bike catalog \n2) Your Account \n3) Your Orders \n4) Logout");
		System.out.println("Select option: ");
		int input = sc.nextInt();
		sc.nextLine();
		try
		{			
			switch(input)
			{
			case 1:
				if(callCatalogUI(sc) < 0)
				{
					return -1;
				}
				return showMenu(sc);
			case 2:
				int returnValue = callAccountUI(sc);
				if(returnValue < 0)
				{
					return -1;
				}
				return showMenu(sc);
			case 3:
				if(callOrdersUI(sc) < 0)
				{
					return -1;
				}
				return showMenu(sc);
			case 4:
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
	
	private int callCatalogUI(Scanner sc) throws BikeHireSystemException
	{
		CatalogUI lCatalogUI = new CatalogUI(loggedInEntity);
		return lCatalogUI.showCatalog(sc);
	}
	
	private int callAccountUI(Scanner sc) throws BikeHireSystemException
	{
		AccountUI lAccountUI = new AccountUI(loggedInEntity);
		return lAccountUI.showAccountInfo(sc);
	}
	
	private int callOrdersUI(Scanner sc) throws BikeHireSystemException
	{
		OrdersUI lOrders = new OrdersUI(loggedInEntity);
		return lOrders.showOrders(sc);
	}
	
}
