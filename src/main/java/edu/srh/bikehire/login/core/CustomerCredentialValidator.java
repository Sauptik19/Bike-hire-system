package edu.srh.bikehire.login.core;

import edu.srh.bikehire.dao.UserCredentialDAO;
import edu.srh.bikehire.dto.UserCredentialDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.login.util.PasswordHashGenerator;
import edu.srh.bikehire.service.core.EntityLoginCredential;
import edu.srh.bikehire.util.Util;

public class CustomerCredentialValidator {
	
	//MEMBERS
	EntityLoginCredential mEntityLoginCredential = null;
	
	public CustomerCredentialValidator(EntityLoginCredential pEntityLoginCredential)
	{
		mEntityLoginCredential = pEntityLoginCredential;
	}
	
	public int validateLoginCredentials(UserCredentialDAO pUserCredentialDAO) throws BikeHireSystemException
	{
		//validate inputs
		validateLoginInputs();
		
		UserCredentialDTO lReturnedUserCredentials = pUserCredentialDAO.getUserCredentialByUserName(mEntityLoginCredential.getUserName());

		if(lReturnedUserCredentials == null)
		{
			//ERROR_MESSAGE : Invalid login credentials provided. Username doesn't exists.
			throw new BikeHireSystemException(10024);
		}
		//If user exists, create hash using salt and validate with input password
		String lstrOriginPasswordHash = lReturnedUserCredentials.getPasswordHash();
		String lstrPasswordSalt = lReturnedUserCredentials.getPasswordSalt();
		
		boolean lbIsValidPassword = PasswordHashGenerator.verifyPasswordHash(lstrOriginPasswordHash, mEntityLoginCredential.getPassword(), lstrPasswordSalt);
		
		if(!lbIsValidPassword)
		{
			//ERROR_MESSAGE : Invalid login credentials provided. Password hash matching failed.
			throw new BikeHireSystemException(10023);
		}
		
		return lReturnedUserCredentials.getUserID();
	}
	
	private void validateLoginInputs() throws BikeHireSystemException
	{
		if(mEntityLoginCredential == null)
		{
			//Error_Message : Login credentials not provided.
			throw new BikeHireSystemException(10005);
		}
		
		if(Util.isEmptyOrNullString(mEntityLoginCredential.getUserName()) || Util.isEmptyOrNullString(mEntityLoginCredential.getPassword()))
		{
			//Error_Message : Invalid login credentials provided.
			throw new BikeHireSystemException(10006);
		}
	}
}
