package edu.srh.bikehire.console;

import java.util.List;
import java.util.Scanner;

import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.dto.WareHouseDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.BikeService;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.impl.BikeServiceImpl;

public class CatalogUI {

	private Entity loggedInEntity;
	
	public CatalogUI(Entity loggedInEntity)
	{
		this.loggedInEntity = loggedInEntity;
	}
	
	public int showCatalog(Scanner sc) throws BikeHireSystemException
	{
		BikeServiceImpl bikeService = new BikeServiceImpl();
		List<BikeDTO> listOfBikes = bikeService.getAllBikesBasedOnStatus(BikeStatusType.AVALIABLE_BIKE, false);
		
		if(listOfBikes == null || listOfBikes.isEmpty())
		{
			System.out.println("Sorry no bikes available for now. Please try after some time.");
			LandingUIForCustomer landingUI = new LandingUIForCustomer(loggedInEntity);
			return landingUI.showMenu(sc);
		}
		
		return nextInput(sc, listOfBikes, bikeService);
	}
	
	private void displayBikes(List<BikeDTO> listOfBikes, BikeService bikeService) throws BikeHireSystemException
	{
		System.out.println("Total bikes : " + listOfBikes.size());
		System.out.println("-----------------------------------------------");
		for(BikeDTO bikeDTO : listOfBikes)
		{
			System.out.println("Bike Id : " + bikeDTO.getBikeId());
			BikeTypeDTO bikeTypeDTO = bikeService.getBikeTypeInfo(bikeDTO.getBikeTypeId());
			System.out.println("Bike Type : " + bikeTypeDTO.getBikeType());
			System.out.println("Bike Title : " + bikeDTO.getBikeTitle());
			System.out.println("Bike Deposit Amount : " + bikeDTO.getDepositAmount());
			BikeRentMappingDTO bikeRent = bikeService.getBikeRent(bikeDTO.getBikeTypeId());
			System.out.println("Bike rent per hour : " + bikeRent.getRentPerHour());
			System.out.println("Bike rent per day : " + bikeRent.getRentPerDay());
			System.out.println("-----------------------------------------------");
		}
	}
	
	private int nextInput(Scanner sc, List<BikeDTO> listOfBikes, BikeService bikeService) throws BikeHireSystemException
	{
		if(listOfBikes != null)
		{			
			displayBikes(listOfBikes, bikeService);
		}
		
		System.out.println("1) Select bike \n2) Sort bikes in descending order of deposit amount \n3) Sort bikes in ascending order of deposit amount \n4) View bikes by type \n5) View bike by warehouse \n6) Group Booking \n7) Back");
		System.out.println("Select option: ");
		int input = sc.nextInt();
		sc.nextLine();
		switch(input)
		{
		case 1:
			boolean orderStatus = placeBikeOrderUI(sc);
			if(!orderStatus)
			{
				return this.showCatalog(sc);
			}
			
			LandingUIForCustomer landingUI = new LandingUIForCustomer(loggedInEntity);
			return landingUI.showMenu(sc);
		case 2:
			return sortByDeposit(sc, bikeService, true);
		case 3:
			return sortByDeposit(sc, bikeService, false);
		case 4:
			return viewBikeByTypes(sc, bikeService);
		case 5:
			return viewBikeByWarehouse(sc, bikeService);
		case 6:
			return groupBookingOption(sc, bikeService);
		case 7:
			LandingUIForCustomer customerLandingUi = new LandingUIForCustomer(loggedInEntity);
			return customerLandingUi.showMenu(sc);
		default:
			//ERRORMESSAGE: Invalid option selected.
			throw new BikeHireSystemException(10118);
		}

	}
	
	private boolean placeBikeOrderUI(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("Please enter bike id to select bike for booking : ");
		int bikeId = sc.nextInt();
		sc.nextLine();
		PlaceOrderUI placeOrder = new PlaceOrderUI(loggedInEntity, bikeId);
		try
		{			
			return placeOrder.processOrder(sc, true, loggedInEntity.getUserId());
		}
		catch(BikeHireSystemException exp)
		{
			System.out.println(exp.getDisplayMessage());
			return false;
		}
	}
	
	private int sortByDeposit(Scanner sc, BikeService bikeService, boolean sortDescending) throws BikeHireSystemException
	{
		List<BikeDTO> listOfBikes = bikeService.getAllBikesBasedOnStatus(BikeStatusType.AVALIABLE_BIKE, sortDescending);
		
		if(listOfBikes == null || listOfBikes.isEmpty())
		{
			System.out.println("Sorry no bikes available for now. Please try after some time.");
			LandingUIForCustomer landingUI = new LandingUIForCustomer(loggedInEntity);
			return landingUI.showMenu(sc);
		}
		
		return nextInput(sc, listOfBikes, bikeService);
	}
	
	private int viewBikeByTypes(Scanner sc, BikeService bikeService) throws BikeHireSystemException
	{
		List<BikeTypeDTO> bikeTypes = bikeService.getBikeTypes();
		System.out.println("Following bike types are available : ");
		for(BikeTypeDTO bikeTypeDTO : bikeTypes)
		{
			System.out.println("Bike Type Id : " + bikeTypeDTO.getBikeTypeId());
			System.out.println("Bike Type : " + bikeTypeDTO.getBikeType());
		}
		
		System.out.println("Select option: ");
		int bikeTypeId = sc.nextInt();
		sc.nextLine();
		boolean isCorrectInput = false;
		int order = 1;
		do
		{			
			System.out.println("Sort by\n 1) Ascending order of deposit amount\n 2) Descending order of deposit amount");
			System.out.println("Select option: ");
			order = sc.nextInt();
			sc.nextLine();
			if(order != 1 && order!= 2)
			{
				isCorrectInput = false;
			}
			isCorrectInput = true;
		}
		while(!isCorrectInput);
		
		boolean isSortDescending = false;
		if(order == 2)
		{
			isSortDescending = true;
		}
		
		List<BikeDTO> lAllBikesForType = bikeService.getAllBikesBasedOnType(bikeTypeId, isSortDescending);
		return nextInput(sc, lAllBikesForType, bikeService);
	}
	
	private int viewBikeByWarehouse(Scanner sc, BikeService bikeService) throws BikeHireSystemException
	{
		List<WareHouseDTO> warehouses = bikeService.getAllWarehouses();
		System.out.println("Following warehouses are available : ");
		for(WareHouseDTO warehouseDTO : warehouses)
		{
			System.out.println("Warehouse Id : " + warehouseDTO.getWarehouseId());
			System.out.println("Warehouse Name : " + warehouseDTO.getName());
			System.out.println("Warehouse Location : " + warehouseDTO.getLocation());
		}
		
		System.out.println("Select option: ");
		int warehouseId = sc.nextInt();
		sc.nextLine();
		boolean isCorrectInput = false;
		int order = 1;
		do
		{			
			System.out.println("Sort by\n 1) Ascending order of deposit amount\n 2) Descending order of deposit amount");
			System.out.println("Select option: ");
			order = sc.nextInt();
			sc.nextLine();
			if(order != 1 && order!= 2)
			{
				isCorrectInput = false;
			}
			isCorrectInput = true;
		}
		while(!isCorrectInput);

		boolean isSortDescending = false;
		if(order == 2)
		{
			isSortDescending = true;
		}
		
		List<BikeDTO> lAllBikesForType = bikeService.getAllBikesBasedOnWarehouse(warehouseId, isSortDescending);
		return nextInput(sc, lAllBikesForType, bikeService);
	}
	
	private int groupBookingOption(Scanner sc, BikeService bikeService) throws BikeHireSystemException
	{
		GroupBookingUI groupBookingUi = new GroupBookingUI(loggedInEntity, true);
		return groupBookingUi.processGroupBooking(sc);
	}
}
