package edu.srh.bikehire.service.core.impl;

import edu.srh.bikehire.service.core.BikeType;

public class BikeTypeInfo implements BikeType {
	private int bikeTypeId;
	private String bikeType;
	private String ageCategory;
	
	public int getBikeTypeId() {
		return bikeTypeId;
	}

	public String getBikeType() {
		return bikeType;
	}

	public String getAgeCategory() {
		return ageCategory;
	}

	public void setBikeTypeId(int bikeTypeId) {
		this.bikeTypeId = bikeTypeId;
	}

	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}

	public void setAgeCategory(String ageCategory) {
		this.ageCategory = ageCategory;
	}

}
