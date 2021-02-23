package edu.srh.bikehire.dto;

import java.util.Calendar;

public interface UserCredentialDTO {

	public int getUserID();
	public String getUserName();
	public String getPasswordSalt();
	public String getPasswordHash();
	public Calendar getLastModifiedTimeStamp();	
	public void setUserDTO(UserDTO pUserDTO);
}
