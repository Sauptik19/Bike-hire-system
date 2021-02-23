package edu.srh.bikehire.service.core;

import java.util.Calendar;

public interface OrderHistory {
	public int getOrderId();
	
	public int getBikeId();
	
	public String getInvoiceId();
	
	public int getUserID();
	
	public String getOrderStatus();
	
	public Calendar getBookingTimeStamp();
	
	public Calendar getPickupTimeStamp();
	
	public Calendar getReturnedTimeStamp();
}
