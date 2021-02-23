package edu.srh.bikehire.assetmangement.impl;

import edu.srh.bikehire.dto.BikeStatusDTO;
import edu.srh.bikehire.dto.impl.BikeStatusDTOImpl;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class BikeStatusValidator {
	private BikeStatusDTO mBikeStatus;

	public BikeStatusValidator(BikeStatusDTO pBikeStatus) {
		mBikeStatus = pBikeStatus;
		}
	
	public void validateAddBikeStatus() throws BikeHireSystemException
	{
		if(mBikeStatus == null)
		{
			//ERROR MESSAGE: Invalid Bike Status for Add Bike Status Validation.
			throw new BikeHireSystemException(10034);
		}
		
		if(mBikeStatus.getBikeId() <= 0)
		{
			//ERROR MESSAGE: Invalid Bike Id for Add Bike Status Validation.
			throw new BikeHireSystemException(10035);
		
		}
		
		validateBikeRentStatus();
		
		if(Util.isEmptyOrNullString(mBikeStatus.getManufacturer()))
		{
			//ERROR MESSAGE: Invalid Bike Manufacturer for Add Bike Status Validation.
			throw new BikeHireSystemException(10036);
		
		}
		//TODO:OTHER BIKESTATUS EXCEPTIONS
		
	
        }
	
	public void validateUpdateBikeStatus() throws BikeHireSystemException
	{
		if(mBikeStatus == null)
		{
			//ERROR MESSAGE: Invalid Bike Status for Update Bike Status Validation.
			throw new BikeHireSystemException(10037);
		}
		validateBikeRentStatus();
	}
	
	public void validateBikeRentStatus() throws BikeHireSystemException
	{
		if(Util.isEmptyOrNullString(mBikeStatus.getStatus()))
		{
			//ERROR MESSAGE: Invalid Bike Status for Update Bike Status Validation
			throw new BikeHireSystemException(10038);
		
		}
	}
	
	

}
