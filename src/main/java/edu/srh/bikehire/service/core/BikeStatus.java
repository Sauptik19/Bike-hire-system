package edu.srh.bikehire.service.core;

import java.util.Calendar;

public interface BikeStatus {
	public int getBikeId();
	public String getStatus();
	public Calendar getLastServiceDate();
	public String getManufacturer();
}
