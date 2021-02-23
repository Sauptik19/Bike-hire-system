package edu.srh.bikehire.service.core.impl;

import java.util.Calendar;

import edu.srh.bikehire.service.core.OrderAppointment;

public class UpcomingAppointment implements OrderAppointment {

	private int orderId;
	
	private String name;
	
	private int bikeId;
	
	private String bikeName;
	
	private Calendar pickupTimestamp;
	
	private Calendar dropoffTimestamp;
	
	public int getOrderId() {
		return orderId;
	}

	public String getName() {
		return name;
	}

	public int getBikeId() {
		return bikeId;
	}

	public String getBikeName() {
		return bikeName;
	}

	public Calendar getPickupTimestamp() {
		return pickupTimestamp;
	}

	public Calendar getDropoffTimestamp() {
		return dropoffTimestamp;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setName(String pName) {
		this.name = pName;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public void setPickupTimestamp(Calendar pickupTimestamp) {
		this.pickupTimestamp = pickupTimestamp;
	}

	public void setDropoffTimestamp(Calendar dropoffTimestamp) {
		this.dropoffTimestamp = dropoffTimestamp;
	}
	
}
