package edu.srh.bikehire.assetmangement.impl;

import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class BikeRentMappingValidator {
	private BikeRentMappingDTO mBikeRentDetails;

	public BikeRentMappingValidator(BikeRentMappingDTO pBikeRentDetails) {
		mBikeRentDetails = pBikeRentDetails;
	}
	
	public void validateAddBikeRentDetails() throws BikeHireSystemException
	{
		if(mBikeRentDetails == null)
		{
			//ERROR_MESSAGE : 
			throw new BikeHireSystemException(10029);
		}
		
		
		if(mBikeRentDetails.getBikeTypeId() <= 0)
		{
			//ERROR_MESSAGE :
			throw new BikeHireSystemException(10030);
		}
		
		validateRentPerHour();
		validateRentPerDay();
		
		
        }
	public void validateUpdateBikeRentDetails() throws BikeHireSystemException
	{
		if(mBikeRentDetails == null)
		{
			//ERROR_MESSAGE :
			throw new BikeHireSystemException(10031);
		}
	
		validateRentPerHour();
		validateRentPerDay();
		
	}
	
	
	
	public void validateRentPerHour() throws BikeHireSystemException
	{
		if(mBikeRentDetails.getRentPerHour()<=0)
		{
			//ERROR_MESSAGE :
			throw new BikeHireSystemException(10032);
		
		}
	}
	
	public void validateRentPerDay() throws BikeHireSystemException
	{
		if(mBikeRentDetails.getRentPerDay()<=0)
		{
			//ERROR_MESSAGE :
			throw new BikeHireSystemException(10033);
		
		}
	}
	
	
	
	
}
	


