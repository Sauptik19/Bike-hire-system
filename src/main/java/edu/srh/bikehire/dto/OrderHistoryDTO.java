package edu.srh.bikehire.dto;

import java.util.Calendar;

public interface OrderHistoryDTO {
	
	public int getOrderID();
	public String getInvoiceId();
	public int getUserID();
	public int getBikeID();
	public String getOrderStatus();
	public Calendar getBookingTimeStamp();
	public Calendar getPickupTimeStamp();
	public Calendar getReturnedTimeStamp();
	public void setInvoiceId(String pInvoiceId);
	public void setUserDTO(UserDTO pUserDTO);
	public void setBikeDTO(BikeDTO pBikeDTO);
}
