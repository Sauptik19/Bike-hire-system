package edu.srh.bikehire.dto;

import java.util.Calendar;

public interface BikeStatusDTO {

	public int getBikeId();
	public void setBikeDTO(BikeDTO pBikeDTO);
	public String getStatus();
	public Calendar getLastServiceDate();
	public Calendar getLastModifiedDate();
	public String getManufacturer();
	
}
