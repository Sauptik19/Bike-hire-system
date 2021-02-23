package edu.srh.bikehire.service.core.impl;

import java.util.Calendar;

import edu.srh.bikehire.service.core.OrderHistory;

public class OrderHistoryInfo implements OrderHistory {

	private int orderId;
	
	private int bikeId;
	
	private String invoiceId;
	
	private int userId;
	
	private Calendar bookingTimeStamp;
	
	private Calendar pickupTimeStamp;
	
	private Calendar returnedTimeStamp;
	
	private String orderStatus;
	
	public int getOrderId() {
		return orderId;
	}

	public int getBikeId() {
		return bikeId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public int getUserID() {
		return userId;
	}

	public Calendar getBookingTimeStamp() {
		return bookingTimeStamp;
	}

	public Calendar getPickupTimeStamp() {
		return pickupTimeStamp;
	}

	public Calendar getReturnedTimeStamp() {
		return returnedTimeStamp;
	}

	public String getOrderStatus(){
		return orderStatus;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setBookingTimeStamp(Calendar bookingTimeStamp) {
		this.bookingTimeStamp = bookingTimeStamp;
	}

	public void setPickupTimeStamp(Calendar pickupTimeStamp) {
		this.pickupTimeStamp = pickupTimeStamp;
	}

	public void setReturnedTimeStamp(Calendar returnedTimeStamp) {
		this.returnedTimeStamp = returnedTimeStamp;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
