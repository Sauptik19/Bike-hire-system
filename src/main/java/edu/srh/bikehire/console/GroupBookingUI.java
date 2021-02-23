package edu.srh.bikehire.console;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.BikeService;
import edu.srh.bikehire.service.DashboardService;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.impl.OrderInfo;
import edu.srh.bikehire.service.impl.BikeServiceImpl;
import edu.srh.bikehire.service.impl.DashboardServiceImpl;
import edu.srh.bikehire.service.impl.OrderServiceImpl;

public class GroupBookingUI {
	private Entity loggedInEntity;
	
	private boolean isOnlineOrder;
	
	public GroupBookingUI(Entity loggedInEntity, boolean isOnlineOrder)
	{
		this.loggedInEntity = loggedInEntity; 
		this.isOnlineOrder = isOnlineOrder;
	}

	public int processGroupBooking(Scanner sc) throws BikeHireSystemException
	{
		int userId = 0;
		if(isOnlineOrder)
		{
			userId = loggedInEntity.getUserId();
		}
		else
		{			
			System.out.println("Enter User Id : ");
			userId = sc.nextInt();
			sc.nextLine();
		}
		
		BikeService bikeService = new BikeServiceImpl();
		List<BikeTypeDTO> bikeTypes = bikeService.getBikeTypes();
		System.out.println("Following bike types are available : ");
		for(BikeTypeDTO bikeTypeDTO : bikeTypes)
		{
			System.out.println("Bike Type Id : " + bikeTypeDTO.getBikeTypeId());
			System.out.println("Bike Type : " + bikeTypeDTO.getBikeType());
		}
		System.out.println("Select any bike type for group booking option: ");
		int bikeTypeId = sc.nextInt();
		sc.nextLine();		
		
		System.out.println("How many bikes do you want for booking?");
		int noOfBikes = sc.nextInt();
		sc.nextLine();
		
		DashboardService dashboardService = new DashboardServiceImpl();
		long noOfBikesAvailable = dashboardService.getBikeCount(BikeStatusType.AVALIABLE_BIKE, bikeTypeId);
		
		if(noOfBikes > noOfBikesAvailable)
		{
			System.out.println("Sorry, not enough bikes are available for this bike type. Please try again.");
			return 0;
		}
		
		System.out.println("Bikes are available for booking, do you want to book now? (y/n)");
		String input = sc.nextLine();
		
		if(input.equalsIgnoreCase("n"))
		{
			return 1;
		}
		
		System.out.println("Please enter pick up date (dd/MM/yyyy) : ");
		String pickupDateInput = sc.nextLine();
		Calendar pickupDate = getCalendarFromInput(pickupDateInput);
		
		System.out.println("Please enter drop off date (dd/MM/yyyy) : ");
		String dropOffDateInput = sc.nextLine();
		Calendar dropOffDate = getCalendarFromInput(dropOffDateInput);
		
		String orderMode = "Offline order";
		if(isOnlineOrder)
		{			
			orderMode = "Online order";
		}
		
		
		List<Integer> allSelectedBikeIds = new ArrayList<Integer>();
		List<BikeDTO> allAvailableBikes = bikeService.getAllBikesBasedOnStatus(BikeStatusType.AVALIABLE_BIKE, false);
		for(BikeDTO bikeDTO : allAvailableBikes)
		{
			if(bikeDTO.getBikeTypeId() == bikeTypeId)
			{
				allSelectedBikeIds.add(bikeDTO.getBikeId());
			}
		}
		Calendar bookingDate = Calendar.getInstance();
		Calendar actualDropOffDate = dropOffDate;
		OrderServiceImpl orderService = new OrderServiceImpl();
		int bikeBooked = 0;
		for(int bikeId : allSelectedBikeIds)
		{
			if(bikeBooked >= noOfBikes)
			{
				break;
			}
			OrderInfo lOrderInfo = new OrderInfo();
			lOrderInfo.setBikeId(bikeId);
			lOrderInfo.setUserId(userId);
			lOrderInfo.setOrderMode(orderMode);
			lOrderInfo.setActualDropoffTimestamp(actualDropOffDate);
			lOrderInfo.setBookingTimestamp(bookingDate);
			lOrderInfo.setDropOffTimestamp(dropOffDate);
			lOrderInfo.setPickupTimestamp(pickupDate);
			
			int orderId = orderService.placeOrder(lOrderInfo);
			bikeBooked++;
		}
		
		System.out.println("Booking confirmed! Please check email for more details.");
		return 1;
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
