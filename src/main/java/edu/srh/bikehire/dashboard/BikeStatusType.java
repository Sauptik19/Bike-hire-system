package edu.srh.bikehire.dashboard;

public enum BikeStatusType {

	AVALIABLE_BIKE(1,"available"),
	RENTED_BIKE(2,"hired"),
	UNDERMAINTAINCE_BIKE(3,"under maintenance");
	
	
	private int bikeType;
	
	private String bikeStatus;
	
	
	private BikeStatusType(int ptype,String pstatus) {
		
		bikeType = ptype;
		
		bikeStatus = pstatus;
		
	}


	public int getBikeType() {
		return bikeType;
	}


	public String getBikeStatus() {
		return bikeStatus;
	}


	public void setBikeType(int bikeType) {
		this.bikeType = bikeType;
	}


	public void setBikeStatus(String bikeStatus) {
		this.bikeStatus = bikeStatus;
	}
	
}
