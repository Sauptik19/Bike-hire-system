package edu.srh.bikehire.assetmangement.impl;

import edu.srh.bikehire.dto.WareHouseDTO;
import edu.srh.bikehire.dto.impl.WareHouseDTOImpl;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class WarehouseValidator {
	private WareHouseDTO mWarehouse;

	public WarehouseValidator(WareHouseDTO pWarehouse) {
		mWarehouse = pWarehouse;
	}
	
	public void validateAddWarehouse() throws BikeHireSystemException
	{
		if(mWarehouse == null)
		{
			//ERROR MESSAGE: Warehouse not provided for Add Warehouse Validation.
			throw new BikeHireSystemException(10055);
		}
		
		validateWareHouse();
		
		if(Util.isEmptyOrNullString(mWarehouse.getName()))
		{
			//ERROR MESSAGE: Invalid Warehouse name for Add Warehouse Validation.
			throw new BikeHireSystemException(10056);
		}
		
		if(Util.isEmptyOrNullString(mWarehouse.getLocation()))
		{
			//ERROR MESSAGE: Invalid Warehouse location for Add Warehouse Validation.
			throw new BikeHireSystemException(10057);
		
		}
		//TODO:OTHER WAREHOUSE EXCEPTIONS
		
	
        }
	public void validateUpdateWareHouse() throws BikeHireSystemException
	{
		if(mWarehouse == null)
		{
			//ERROR MESSAGE: Warehouse not provided for Update Warehouse Validation.
			throw new BikeHireSystemException(10058);
		}
	
		validateWareHouse();
		
	}
	
	
	
	public void validateWareHouse() throws BikeHireSystemException
	{
		if(mWarehouse.getStorageCapacity()<=0)
		{
			//ERROR MESSAGE: Invalid Storage Capacity for Warehouse Validation.
			throw new BikeHireSystemException(10059);
		
		}
	}
	
	
}