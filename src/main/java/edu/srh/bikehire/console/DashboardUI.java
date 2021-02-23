package edu.srh.bikehire.console;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.BikeService;
import edu.srh.bikehire.service.DashboardService;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.OrderAppointment;
import edu.srh.bikehire.service.impl.BikeServiceImpl;
import edu.srh.bikehire.service.impl.DashboardServiceImpl;

public class DashboardUI {
	private Entity loggedInStaff;
	
	public DashboardUI(Entity loggedInEntity)
	{
		loggedInStaff = loggedInEntity;
	}
	
	public int showDashboard(Scanner sc) throws BikeHireSystemException
	{
		DashboardServiceImpl dashboardService = new DashboardServiceImpl();
		BikeServiceImpl bikeService = new BikeServiceImpl();
		System.out.println("1) View Bike Status\n2) Upcoming Appointments \n3) Back");
		System.out.println("Select option: ");
		int option = sc.nextInt();
		sc.nextLine();
		switch(option)
		{
		case 1:
			showBikeStatus(dashboardService, bikeService);
			return showDashboard(sc);
			
		case 2:
			upcomingAppointments( dashboardService);
			return showDashboard(sc);
			
		case 3:
			return 0;
		default:
			//ERROR MESSAGE: Invalid option is selected
			throw new BikeHireSystemException(10062);
		}
		
	}
	
	private void showBikeStatus(DashboardService dashboardService, BikeService bikeService) throws BikeHireSystemException
	{
		List<BikeTypeDTO> allBikeTypes = bikeService.getBikeTypes();
		System.out.println("No Bikes are available");
		for(BikeTypeDTO bikeTypeDTO : allBikeTypes)
		{
			System.out.println("Bike Type Id : " + bikeTypeDTO.getBikeTypeId());
			System.out.println("Bike Type : " + bikeTypeDTO.getBikeType());
			long availableBikes = dashboardService.getBikeCount(BikeStatusType.AVALIABLE_BIKE, bikeTypeDTO.getBikeTypeId());
			long hiredBikes = dashboardService.getBikeCount(BikeStatusType.RENTED_BIKE, bikeTypeDTO.getBikeTypeId());
			long underMaintenanceBikes = dashboardService.getBikeCount(BikeStatusType.UNDERMAINTAINCE_BIKE, bikeTypeDTO.getBikeTypeId());
			System.out.println("Available Bikes : " + availableBikes);
			System.out.println("Hired Bikes : " + hiredBikes);
			System.out.println("Under Maintenance Bikes : " + underMaintenanceBikes);
			System.out.println("--------------------------------------------------------");
		}
	}
	
	private void upcomingAppointments(DashboardService dashboardService) throws BikeHireSystemException
	{
		Calendar queryDate = Calendar.getInstance();
		System.out.println("Pick-up Appointments : ");
		List<OrderAppointment> upcomingPickUpAppointments = dashboardService.getUpcomingAppointments(queryDate, true);
		if(upcomingPickUpAppointments == null || upcomingPickUpAppointments.isEmpty())
		{
			System.out.println("No upcoming pick-up appointments.");
		}
		
		for(OrderAppointment pickupAppointment : upcomingPickUpAppointments)
		{
			System.out.println("Order Id : " + pickupAppointment.getOrderId());
			System.out.println("Customer Name : " + pickupAppointment.getName());
			System.out.println("Bike Id : " + pickupAppointment.getBikeId());
			System.out.println("Bike Name : " + pickupAppointment.getBikeName());
			System.out.println("Pickup Time : " + ConsoleUtil.getStringForDate(pickupAppointment.getPickupTimestamp()));
			System.out.println("---------------------");
		}
		
		System.out.println("Drop-off Appointments : ");
		List<OrderAppointment> upcomingDropOffAppointments = dashboardService.getUpcomingAppointments(queryDate, false);
		if(upcomingDropOffAppointments == null || upcomingDropOffAppointments.isEmpty())
		{
			System.out.println("No upcoming drop-off appointments.");
		}
		
		for(OrderAppointment dropOffAppointment : upcomingDropOffAppointments)
		{
			System.out.println("Order Id : " + dropOffAppointment.getOrderId());
			System.out.println("Customer Name : " + dropOffAppointment.getName());
			System.out.println("Bike Id : " + dropOffAppointment.getBikeId());
			System.out.println("Bike Name : " + dropOffAppointment.getBikeName());
			System.out.println("Drop Off Time : " + ConsoleUtil.getStringForDate(dropOffAppointment.getDropoffTimestamp()));
			System.out.println("---------------------");
		}
		
	}
}
