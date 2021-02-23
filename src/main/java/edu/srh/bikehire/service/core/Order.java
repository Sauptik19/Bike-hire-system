package edu.srh.bikehire.service.core;
import java.util.Calendar;

public interface Order {
	
	public int getOrderId();
	
	public int getUserId();
	
	public int getBikeId();
	
	public Calendar getBookingTimestamp();
	
	public Calendar getPickupTimestamp();
	
	public Calendar getDropoffTimestamp();
	
	public Calendar getActualdropoffTimestamp();
	
	 public String getOrderMode();
}
