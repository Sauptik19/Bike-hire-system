package edu.srh.bikehire.service.core;

import java.math.BigInteger;
import java.sql.Timestamp;

public interface BookingConfirmation {
	
	public Timestamp PickupTimeStamp();
	
	public Timestamp ReturnTimeStamp();
	
	public BigInteger BikeID();
	
	public BigInteger PaymentReference();
	
	public BigInteger UserID();
	
	public Integer FinalAmount();
	
	public Integer DepositAmount();
	
}
