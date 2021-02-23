package edu.srh.bikehire.service.core.impl;

import java.util.Calendar;

import edu.srh.bikehire.service.core.BikeStatus;

public class BikeStatusInfo implements BikeStatus {
	private int bikeId;
	private String status;
	private Calendar lastServiceDate;
	private String manufacturer;
	
	public int getBikeId() {
		return bikeId;
	}

	public String getStatus() {
		return status;
	}

	public Calendar getLastServiceDate() {
		return lastServiceDate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setLastServiceDate(Calendar lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}
