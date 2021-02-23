package edu.srh.bikehire.assetmangement.impl;

import java.util.Calendar;

import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.impl.BikeDTOImpl;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class BikeValidator {
	private BikeDTO mBike;

	public BikeValidator(BikeDTO pBike) {
		mBike = pBike;
		
	}
	
	public void validateAddBike() throws BikeHireSystemException
	{
		if(mBike == null)
		{
			//ERROR MESSAGE: Bike not provided for Add Bike Validation.
			throw new BikeHireSystemException(10046);
		}
		
		validateBikeID();
		
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if(mBike.getYearOfManufacture() <1960 || mBike.getYearOfManufacture() >year)
		{	
			//ERROR MESSAGE: Invalid Year Of Manufacture for Add Bike Validation.
			throw new BikeHireSystemException(10047);
		}
		
		if(Util.isEmptyOrNullString(mBike.getManufacturer()))
		{
			//ERROR MESSAGE: Invalid Manufacture for Add Bike Validation.
			throw new BikeHireSystemException(10048);
		}
		
		if(Util.isEmptyOrNullString(mBike.getBikeTitle()))
		{
			//ERROR MESSAGE: Invalid Bike Title for Add Bike Validation.
			throw new BikeHireSystemException(10049);
		
		}
		//TODO:OTHER WAREHOUSE EXCEPTIONS
		validateDepositAmount();
		
		if(mBike.getWareHouseID() <= 0)
		{
			//ERROR MESSAGE: Invalid WareHouse ID for Add Bike Validation.
			throw new BikeHireSystemException(10050);
		}
	
        }
	public void validateDeleteBike() throws BikeHireSystemException
	{
		if(mBike == null)
		{
			//ERROR MESSAGE: 	
			throw new BikeHireSystemException(10051);
		}
	
		validateBikeID();
		
	}
	
	public void validateUpdateBike() throws BikeHireSystemException
	{
		if(mBike == null)
		{
			//ERROR MESSAGE: Invalid Bike not provided for Update Bike Validation.
			throw new BikeHireSystemException(10052);
		}
	
		validateDepositAmount();
		
	}
	
	public void validateBikeID() throws BikeHireSystemException
	{
		if(mBike.getBikeId() < 0)
		{
			//ERROR MESSAGE: Invalid Bike ID for Bike Validation.
			throw new BikeHireSystemException(10053);
		
		}
	}
	
	public void validateDepositAmount() throws BikeHireSystemException
	{
		if(mBike.getDepositAmount() <= 0)
		{
			//ERROR MESSAGE: Invalid Deposit Amount for Deposit Amount Validation.
			throw new BikeHireSystemException(10054);
		
		}
	}
}
