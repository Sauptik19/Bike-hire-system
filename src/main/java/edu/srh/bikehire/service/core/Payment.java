package edu.srh.bikehire.service.core;

public interface Payment {
	
	public String PaymentReference();
	
	public Integer DepositAmount();
	
	public Integer RentPerDay();
	
	public Integer RentPerHour();
	
	public Integer FinalAmount();
	
}
