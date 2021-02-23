package edu.srh.bikehire.validator;

import edu.srh.bikehire.dto.CurrentOrderDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.exception.ErrorMessage;
import edu.srh.bikehire.service.core.Order;
import edu.srh.bikehire.util.Util;

public class OrderValidator {
	private Order currentOrderDTO;
	
	public OrderValidator(Order currentOrderDTO)
	{
		this.currentOrderDTO = currentOrderDTO;
	}
	
	public void validateForNewOrder() throws BikeHireSystemException
	{
		if(currentOrderDTO == null)
		{
			//ERRORMESSAGE: Current Order Data Transfer Object Not Found.
			throw new BikeHireSystemException(10086);
		}
		
		if(currentOrderDTO.getBikeId() <= 0)
		{
			//ERRORMESSAGE: Invalid Bike ID of Current Order Data Transfer Object.
			throw new BikeHireSystemException(10096);
		}
		
		if(currentOrderDTO.getUserId() <= 0)
		{
			//ERRORMESSAGE: Invalid User ID of Current Order Data Transfer Object.
			throw new BikeHireSystemException(10097);
		}
		
		if(Util.isEmptyOrNullString(currentOrderDTO.getOrderMode()))
		{
			//ERRORMESSAGE: Invalid Order mode of Current Order Data Transfer Object.
			throw new BikeHireSystemException(10098);
		}
		
		if(currentOrderDTO.getActualdropoffTimestamp() == null)
		{
			//ERRORMESSAGE: Invalid Actual dropoff Timestamp of Current Order Data Transfer Object.
			throw new BikeHireSystemException(10099);
		}
		
		if(currentOrderDTO.getBookingTimestamp() == null)
		{
			//ERRORMESSAGE: Invalid Booking Timestamp of Current Order Data Transfer Object.
			throw new BikeHireSystemException(10100);
		}
		
		if(currentOrderDTO.getDropoffTimestamp() == null)
		{
			//ERRORMESSAGE: Invalid Dropoff Timestamp of Current Order Data Transfer Object.
			throw new BikeHireSystemException(10101);
		}
		
		if(currentOrderDTO.getPickupTimestamp() == null)
		{
			//ERRORMESSAGE: Invalid Pickup Timestamp of Current Order Data Transfer Object.
			throw new BikeHireSystemException(10102);
		}
	}
}
