package edu.srh.bikehire.assetmangement.impl;

import edu.srh.bikehire.dto.BikeStockDTO;
import edu.srh.bikehire.dto.impl.BikeStockDTOImpl;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class BikeStockValidator {
	private BikeStockDTO mBikeStock;

	public BikeStockValidator(BikeStockDTO pBikeStock) {
		mBikeStock = pBikeStock;
	}
	
	public void validateAddBikeStock() throws BikeHireSystemException
	{
		if(mBikeStock == null)
		{
			//ERROR MESSAGE: Invalid Bike Stock for Add Bike Stock Validation.
			throw new BikeHireSystemException(10039);
		}
		
		if(mBikeStock.getBikeTypeId() <= 0)
		{
			//ERROR MESSAGE: Invalid Bike Type Id for Add Bike Stock Validation.
			throw new BikeHireSystemException(10040);
		
		}
		
		validateBikeQuantity();
		
		}
	
	public void validateUpdateBikeStock() throws BikeHireSystemException
	{
		if(mBikeStock == null)
		{
			//ERROR MESSAGE: Invalid Bike Stock for Update Bike Stock Validation
			throw new BikeHireSystemException(10041);
		}
		validateBikeQuantity();
	}
	
	public void validateBikeQuantity() throws BikeHireSystemException
	{
		if(mBikeStock.getTotalQuantity()<=0)
		{
			//ERROR MESSAGE: Invalid Total Quantity for Bike Quantity Validation.
			throw new BikeHireSystemException(10042);
		
		}
	}
	
	

}
	


