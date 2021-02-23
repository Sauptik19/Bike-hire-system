package edu.srh.bikehire.dto;

import java.util.Calendar;

public interface CurrentOrderDTO {
	
	public int getOrderID();
	public int getUserID();
	public void setUserDTO(UserDTO pUserDTO);
	public int getBikeID();
	public void setBikeDTO(BikeDTO pBikeDTO);
	public Calendar getBookingTimeStamp();
	public Calendar getPickupTimeStamp();
	public Calendar getDropOffTimeStamp();
	public Calendar getActualDropOffTimeStamp();
	public String getOrderMode();
	
}
