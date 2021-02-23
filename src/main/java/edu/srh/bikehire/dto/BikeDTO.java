package edu.srh.bikehire.dto;

import java.util.Calendar;

public interface BikeDTO {
	
	public int getBikeId();
	public int getBikeTypeId();
	public String getManufacturer();
	public int getYearOfManufacture();
	public String getBikeTitle();
	public int getWareHouseID();
	public int getDepositAmount();
	public Calendar getCreationTimeStamp();
}
