package edu.srh.bikehire.service;

import java.util.List;

import edu.srh.bikehire.dao.BikeDAO;
import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.dto.WareHouseDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;

/**
 * This class provides all the services related to bike functionality. 
 *
 */
public interface BikeService{

	/**
	 * This method returns all the bikes in the system. By default bikes are returned in ascending order of deposit amount.
	 * @param sortPriceDescending - true if bikes needs to be sorted in descending order.
	 * @return
	 * @throws BikeHireSystemException
	 */
	public List<BikeDTO> getAllBikes(boolean sortPriceDescending) throws BikeHireSystemException;

	/**
	 * This method returns Bike information for id provided.
	 * @param id
	 * @return
	 * @throws BikeHireSystemException
	 */
	public BikeDTO getBikeByID(int id) throws BikeHireSystemException;

	/**
	 * This method updates status of bike.
	 * @param id
	 * @param bikeStatusType - Bike Status Type enum
	 * @throws BikeHireSystemException
	 */
	public void updateBikeStatus(int id, BikeStatusType bikeStatusType) throws BikeHireSystemException;
	
	/**
	 * This method returns all the bikes based on type provided.
	 * @param bikeTypeId
	 * @param sortPriceDescending - true if bikes needs to be sorted in descending order.
	 * @return
	 * @throws BikeHireSystemException
	 */
	public List<BikeDTO> getAllBikesBasedOnType(int bikeTypeId, boolean sortPriceDescending) throws BikeHireSystemException;
	
	/**
	 * This method returns all the bikes based on warehouse provided.
	 * @param warehouseId
	 * @param sortPriceDescending - true if bikes needs to be sorted in descending order.
	 * @return
	 * @throws BikeHireSystemException
	 */
	public List<BikeDTO> getAllBikesBasedOnWarehouse(int warehouseId, boolean sortPriceDescending) throws BikeHireSystemException;
	
	/**
	 * This method returns bike type information for bike type id.
	 * @param bikeTypeId
	 * @return returns null if invalid bike type id is provided.
	 * @throws BikeHireSystemException
	 */
	public BikeTypeDTO getBikeTypeInfo(int bikeTypeId) throws BikeHireSystemException;
	
	/**
	 * This method returns all the bike types in the system
	 * @return
	 * @throws BikeHireSystemException
	 */
	public List<BikeTypeDTO> getBikeTypes() throws BikeHireSystemException;
	
	/**
	 * This method returns all the bikes based on status of the bikes.
	 * @param bikeStatusType - bike status type enum
	 * @param sortPriceDescending - true if bikes needs to be sorted in descending order. 
	 * @return
	 * @throws BikeHireSystemException
	 */
	public List<BikeDTO> getAllBikesBasedOnStatus(BikeStatusType bikeStatusType, boolean sortPriceDescending) throws BikeHireSystemException;
	
	/**
	 * This method returns bike rent mapping for bike type id
	 * @param bikeId
	 * @return returns null if invalid bike type id provided.
	 * @throws BikeHireSystemException
	 */
	public BikeRentMappingDTO getBikeRent(int bikeId) throws BikeHireSystemException;
	
	/**
	 * This method returns all list of warehouses for this system. 
	 * @return
	 * @throws BikeHireSystemException
	 */
	public List<WareHouseDTO> getAllWarehouses() throws BikeHireSystemException;
}
