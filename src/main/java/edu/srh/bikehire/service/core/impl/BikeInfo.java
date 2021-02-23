package edu.srh.bikehire.service.core.impl;

import edu.srh.bikehire.service.core.Bike;

public class BikeInfo implements Bike {

	private int bikeId;
	private int bikeTypeId;
	private String manufacturer;
	private int yearOfManufacturer;
	private String bikeTitle;
	private int wareHouseID;
	private int depositAmount;
	
	public int getBikeId() {
		return bikeId;
	}

	public int getBikeTypeId() {
		return bikeTypeId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getYearOfManufacture() {
		return yearOfManufacturer;
	}

	public String getBikeTitle() {
		return bikeTitle;
	}

	public int getWareHouseID() {
		return wareHouseID;
	}

	public int getDepositAmount() {
		return depositAmount;
	}

	public int getYearOfManufacturer() {
		return yearOfManufacturer;
	}

	public void setYearOfManufacturer(int yearOfManufacturer) {
		this.yearOfManufacturer = yearOfManufacturer;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public void setBikeTypeId(int bikeTypeId) {
		this.bikeTypeId = bikeTypeId;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setBikeTitle(String bikeTitle) {
		this.bikeTitle = bikeTitle;
	}

	public void setWareHouseID(int wareHouseID) {
		this.wareHouseID = wareHouseID;
	}

	public void setDepositAmount(int depositAmount) {
		this.depositAmount = depositAmount;
	}
}
