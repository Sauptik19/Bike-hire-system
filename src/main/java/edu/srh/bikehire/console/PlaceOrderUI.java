package edu.srh.bikehire.console;

import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.impl.OrderInfo;
import edu.srh.bikehire.service.impl.BikeServiceImpl;
import edu.srh.bikehire.service.impl.OrderServiceImpl;

public class PlaceOrderUI {
	private Entity loggedInEntity;
	
	private int bikeId;
	
	public PlaceOrderUI(Entity loggedInEntity, int bikeId)
	{
		this.loggedInEntity = loggedInEntity;
		this.bikeId = bikeId;
	}
	
	public boolean processOrder(Scanner sc, boolean isOnlineOrder, int userId) throws BikeHireSystemException
	{
		System.out.println("You have selected bike id : " + bikeId);
		String orderMode = null;
		if(isOnlineOrder)
		{			
			orderMode = "Online order";
		}
		else
		{
			orderMode = "Offline order";
		}
		
		System.out.println("Please enter pick up date (dd/MM/yyyy) : ");
		String pickupDateInput = sc.nextLine();
		Calendar pickupDate = getCalendarFromInput(pickupDateInput);
		
		System.out.println("Please enter drop off date (dd/MM/yyyy) : ");
		String dropOffDateInput = sc.nextLine();
		Calendar dropOffDate = getCalendarFromInput(dropOffDateInput);
		
		BikeServiceImpl bikeService = new BikeServiceImpl();
		BikeDTO bikeDTO = bikeService.getBikeByID(bikeId);
		
		System.out.println("€ "+bikeDTO.getDepositAmount() +" amount will be charged for deposit. Do you want to proceed (y/n)?");
		String userInput = sc.nextLine();
		if(userInput.equalsIgnoreCase("n"))
		{
			return false;
		}
		
		Calendar bookingDate = Calendar.getInstance();
		Calendar actualDropOffDate = dropOffDate;
		
		OrderInfo lOrderInfo = new OrderInfo();
		lOrderInfo.setBikeId(bikeId);
		lOrderInfo.setUserId(userId);
		lOrderInfo.setOrderMode(orderMode);
		lOrderInfo.setActualDropoffTimestamp(actualDropOffDate);
		lOrderInfo.setBookingTimestamp(bookingDate);
		lOrderInfo.setDropOffTimestamp(dropOffDate);
		lOrderInfo.setPickupTimestamp(pickupDate);
		
		OrderServiceImpl orderService = new OrderServiceImpl();
		int orderId = orderService.placeOrder(lOrderInfo);
		System.out.println("Booking Confirmed! Order Id is " + orderId);
		return true;
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
