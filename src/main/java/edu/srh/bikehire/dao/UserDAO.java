package edu.srh.bikehire.dao;

import edu.srh.bikehire.dto.UserDTO;

public interface UserDAO {
	public UserDTO getUser(int pUserId);
	
	public UserDTO getUserByEmailId(String pEmailId);
	
	public int addUser(UserDTO pNewUser);
	
	public boolean updateUser(UserDTO pUser);
}
