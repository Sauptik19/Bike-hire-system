package edu.srh.bikehire.console;

import java.util.Calendar;
import java.util.Scanner;

import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.AssetManagementService;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.impl.BikeInfo;
import edu.srh.bikehire.service.core.impl.BikeRentInfo;
import edu.srh.bikehire.service.core.impl.BikeStatusInfo;
import edu.srh.bikehire.service.core.impl.BikeStockInfo;
import edu.srh.bikehire.service.core.impl.BikeTypeInfo;
import edu.srh.bikehire.service.core.impl.WarehouseInfo;
import edu.srh.bikehire.service.impl.AssetManagementServiceImpl;

public class AssetManagementUI {
	private Entity loggedInStaff;
	
	public AssetManagementUI(Entity loggedInEntity)
	{
		loggedInStaff = loggedInEntity;
	}
	
	public int manageAssets(Scanner sc) throws BikeHireSystemException
	{
		System.out.println("1) Add Bike Type \n2) Add Warehouse \n3) Add Bike \n4) Update Bike Rent \n5) Back");
		System.out.println("Select option: ");
		AssetManagementService assetManagementService = new AssetManagementServiceImpl();
		int input = sc.nextInt();
		sc.nextLine();
		switch(input)
		{
		case 1:
			addNewBikeType(sc, assetManagementService);
			return manageAssets(sc);
			
		case 2:
			addNewWarehouse(sc, assetManagementService);
			return manageAssets(sc);
			
		case 3:
			addNewBike(sc, assetManagementService);
			return manageAssets(sc);
			
		case 4:
			updateBikeRent(sc, assetManagementService);
			return manageAssets(sc);
			
		case 5:
			return 0;
			
		default :
			//ERROR MESSAGE: Invalid option is selected
			throw new BikeHireSystemException(10061);
		}
	}
	
	private void addNewBikeType(Scanner sc, AssetManagementService assetManagementService) throws BikeHireSystemException
	{
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		BikeStockInfo bikeStock = new BikeStockInfo();
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		System.out.println("Enter bike type name : ");		
		bikeTypeInfo.setBikeType(sc.nextLine());
		System.out.println("Enter age category : ");
		bikeTypeInfo.setAgeCategory(sc.nextLine());
		System.out.println("Total quantity available : ");
		bikeStock.setTotalQuantity(sc.nextLong());
		System.out.println("Rent per hour : ");
		bikeRentInfo.setRentPerHour(sc.nextInt());
		sc.nextLine();
		System.out.println("Rent per day : ");
		bikeRentInfo.setRentPerDay(sc.nextInt());
		sc.nextLine();
		int bikeTypeId = assetManagementService.addNewBikeType(bikeTypeInfo, bikeStock, bikeRentInfo);
		System.out.println("New Bike Type added successfully! Bike Type Id : " + bikeTypeId);
	}
	
	private void addNewWarehouse(Scanner sc, AssetManagementService assetManagementService) throws BikeHireSystemException
	{
		WarehouseInfo warehouseInfo = new WarehouseInfo();
		System.out.println("Enter warehouse name : ");
		warehouseInfo.setName(sc.nextLine());
		System.out.println("Enter location : ");
		warehouseInfo.setLocation(sc.nextLine());
		System.out.println("Enter storage capacity : ");
		warehouseInfo.setStorageCapacity(sc.nextInt());
		sc.nextLine();
		int warehouseId = assetManagementService.addNewWarehouse(warehouseInfo);
		System.out.println("New warehouse added successfully! warehouse id : " + warehouseId);
	}
	
	private void addNewBike(Scanner sc, AssetManagementService assetManagementService) throws BikeHireSystemException
	{
		BikeInfo bikeInfo = new BikeInfo();
		System.out.println("Enter bike type id : ");
		bikeInfo.setBikeTypeId(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter bike title : ");
		bikeInfo.setBikeTitle(sc.nextLine());
		System.out.println("Enter warehouse id : ");
		bikeInfo.setWareHouseID(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter deposit amount : ");
		bikeInfo.setDepositAmount(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter bike manufacturer : ");
		String manufacturer = sc.nextLine();
		bikeInfo.setManufacturer(manufacturer);
		System.out.println("Enter year of manufacture : ");
		bikeInfo.setYearOfManufacturer(sc.nextInt());
		sc.nextLine();
		
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setManufacturer(manufacturer);
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		bikeStatusInfo.setStatus(BikeStatusType.AVALIABLE_BIKE.getBikeStatus());
		
		int bikeId = assetManagementService.addNewBikeDetails(bikeInfo, bikeStatusInfo);
		System.out.println("New bike added successfully! bike id : " + bikeId);
	}
	
	private void updateBikeRent(Scanner sc, AssetManagementService assetManagementService)  throws BikeHireSystemException
	{
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		System.out.println("Enter bike type id : ");
		bikeRentInfo.setBikeTypeId(sc.nextInt());
		sc.nextLine();
		System.out.println("Rent per hour : ");
		bikeRentInfo.setRentPerHour(sc.nextInt());
		sc.nextLine();
		System.out.println("Rent per day : ");
		bikeRentInfo.setRentPerDay(sc.nextInt());
		sc.nextLine();
		boolean status = assetManagementService.updateBikeRent(bikeRentInfo);
		if(status)
		{
			System.out.println("Successfully updated record!");
		}
		else
		{
			System.out.println("Failed to update record!");
		}
	}
	
	
}
