package edu.srh.bikehire.dao;

import edu.srh.bikehire.dto.UserAccountDTO;

public interface UserAccountDAO {
	public UserAccountDTO getUserAccount(int pUserId);
	
	public UserAccountDTO getUserAccountUsingUserName(String pUserName);
	
	public boolean addUserAccount(UserAccountDTO pUserAccountDTO);
	
	public boolean updateUserAccount(UserAccountDTO pUserAccountDTO);
}
