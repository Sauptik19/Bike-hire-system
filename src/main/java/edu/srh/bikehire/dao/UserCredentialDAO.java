package edu.srh.bikehire.dao;

import edu.srh.bikehire.dto.UserCredentialDTO;

public interface UserCredentialDAO {
	
	public UserCredentialDTO getUserCredentialByUserId(int pUserId);
	
	public UserCredentialDTO getUserCredentialByUserName(String pUserName);
	
	public boolean addUserCredential(UserCredentialDTO pUserCredentialDTO);
	
	public boolean updateUserCredential(UserCredentialDTO pUserCredentialDTO);
	
}
