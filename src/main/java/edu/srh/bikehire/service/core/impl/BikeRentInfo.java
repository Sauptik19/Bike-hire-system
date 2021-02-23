package edu.srh.bikehire.service.core.impl;

import edu.srh.bikehire.service.core.BikeRent;

public class BikeRentInfo implements BikeRent {
	private int bikeTypeId;
	private int rentPerHour;
	private int rentPerDay;
	
	public int getBikeTypeId() {
		return bikeTypeId;
	}

	public int getRentPerHour() {
		return rentPerHour;
	}

	public int getRentPerDay() {
		return rentPerDay;
	}

	public void setBikeTypeId(int bikeTypeId) {
		this.bikeTypeId = bikeTypeId;
	}

	public void setRentPerHour(int rentPerHour) {
		this.rentPerHour = rentPerHour;
	}

	public void setRentPerDay(int rentPerDay) {
		this.rentPerDay = rentPerDay;
	}

}
