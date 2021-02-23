package edu.srh.bikehire.dto;

import java.util.Calendar;

public interface BikeStockDTO {

	public int getBikeTypeId();
	public void setBikeTypeDTO(BikeTypeDTO pBikeTypeDTO);
	public long getTotalQuantity();
	public Calendar getCreationTimeStamp();
	public Calendar getLastModifiedTimeStamp();
}
