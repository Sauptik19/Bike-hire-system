package edu.srh.bikehire.service;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.Bike;
import edu.srh.bikehire.service.core.BikeRent;
import edu.srh.bikehire.service.core.BikeStatus;
import edu.srh.bikehire.service.core.BikeStock;
import edu.srh.bikehire.service.core.BikeType;
import edu.srh.bikehire.service.core.Warehouse;

/**
 * This class provides all the services for managing assets 
 *
 */
public interface AssetManagementService {
	/**
	 * This method adds new bike type in the system
	 * @param pNewBikeType
	 * @param pBikeStock
	 * @param pRentMapping
	 * @return returns new bike type id
	 * @throws BikeHireSystemException
	 */
	public int addNewBikeType(BikeType pNewBikeType, BikeStock pBikeStock, BikeRent pRentMapping) throws BikeHireSystemException;
	
	/**
	 * This method adds new bike details in the system
	 * @param pNewBike
	 * @param pBikeStatus
	 * @return returns new bike id.
	 * @throws BikeHireSystemException
	 */
	public int addNewBikeDetails(Bike pNewBike, BikeStatus pBikeStatus) throws BikeHireSystemException;
	
	/**
	 * This method adds new warehouse in the system.
	 * @param pNewWarehouse
	 * @return returns new warehouse id.
	 * @throws BikeHireSystemException
	 */
	public int addNewWarehouse(Warehouse pNewWarehouse) throws BikeHireSystemException;
	
	/**
	 * This method updates details for the warehouse.
	 * @param pUpdatedWarehouse
	 * @return returns status whether operation is successful or not.
	 * @throws BikeHireSystemException
	 */
	public boolean updateWarehouse(Warehouse pUpdatedWarehouse) throws BikeHireSystemException;
	
	/**
	 * This method updates details for the bike.
	 * @param pUpdatedBike
	 * @return returns status whether operation is successful or not.
	 * @throws BikeHireSystemException
	 */
	public boolean updateBikeDetails(Bike pUpdatedBike) throws BikeHireSystemException;
	
	/**
	 * This method updates rent of the bike.
	 * @param pUpdatedBikeRent
	 * @return returns status whether operation is successful or not.
	 * @throws BikeHireSystemException
	 */
	public boolean updateBikeRent(BikeRent pUpdatedBikeRent) throws BikeHireSystemException;
	
	/**
	 * This method updates stock of the bike.
	 * @param pUpdatedBikeStock
	 * @param pUpdatedBikeType
	 * @return returns status whether operation is successful or not.
	 * @throws BikeHireSystemException
	 */
	public boolean updateBikeStock(BikeStock pUpdatedBikeStock, BikeType pUpdatedBikeType) throws BikeHireSystemException;
	
	/**
	 * This method updates status of the bike.
	 * @param pUpdatedBikeStatus
	 * @return returns status whether operation is successful or not.
	 * @throws BikeHireSystemException
	 */
	public boolean updateBikeStatus(BikeStatus pUpdatedBikeStatus) throws BikeHireSystemException;

}
