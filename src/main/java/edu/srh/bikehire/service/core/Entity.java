package edu.srh.bikehire.service.core;

import java.util.Calendar;

public interface Entity {
	
	public int getUserId();
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getGender();
	
	public String getAddress();
	
	public Calendar getDOB();
	
	public String getEmailId();
	
	public String getPhoneNumber();
	
	public EntityAccount getEntityAccount();
	
	public byte[] getPhoto();
	
	public byte[] getIdentityProof();
	
}
