package edu.srh.bikehire.service.core;

import java.util.Calendar;

public interface OrderAppointment {
	public int getOrderId();
	
	public String getName();
	
	public int getBikeId();
	
	public String getBikeName();
	
	public Calendar getPickupTimestamp();
	
	public Calendar getDropoffTimestamp();
}
