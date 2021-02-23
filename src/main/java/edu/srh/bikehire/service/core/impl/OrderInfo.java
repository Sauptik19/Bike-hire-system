package edu.srh.bikehire.service.core.impl;

import java.util.Calendar;

import edu.srh.bikehire.service.core.Order;

public class OrderInfo implements Order {
	
	private int orderId;
	
	private int userId;
	
	private int bikeId;
	
	private Calendar bookingTimestamp;
	
	private Calendar pickupTimestamp;
	
	private Calendar dropOffTimestamp;
	
	private Calendar actualDropoffTimestamp;
	
	private String orderMode;

	public int getOrderId() {
		
		return orderId;
	}

	public int getUserId() {
		
		return userId;
	}

	public int getBikeId() {
		
		return bikeId;
	}

	public Calendar getBookingTimestamp() {
		
		return bookingTimestamp;
	}

	public Calendar getPickupTimestamp() {
		
		return pickupTimestamp;
	}

	public Calendar getDropoffTimestamp() {
		
		return dropOffTimestamp;
	}

	public Calendar getActualdropoffTimestamp() {
		
		return actualDropoffTimestamp;
	}

	public String getOrderMode() {
		
		return orderMode;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public void setBookingTimestamp(Calendar bookingTimestamp) {
		this.bookingTimestamp = bookingTimestamp;
	}

	public void setPickupTimestamp(Calendar pickupTimestamp) {
		this.pickupTimestamp = pickupTimestamp;
	}

	public void setDropOffTimestamp(Calendar dropOffTimestamp) {
		this.dropOffTimestamp = dropOffTimestamp;
	}

	public void setActualDropoffTimestamp(Calendar actualDropoffTimestamp) {
		this.actualDropoffTimestamp = actualDropoffTimestamp;
	}

	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}
	
}
