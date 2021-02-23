package edu.srh.bikehire.login.core;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.login.util.LoginUtil;
import edu.srh.bikehire.service.core.EntityRegistrationCredential;
import edu.srh.bikehire.util.Util;

public class UserRegistrationCredentialValidator {
	private EntityRegistrationCredential mEntityRegistrationCredential;
	
	public UserRegistrationCredentialValidator(EntityRegistrationCredential pEntityCredential)
	{
		mEntityRegistrationCredential = pEntityCredential;
	}
	
	public void validateEntityCredentials() throws BikeHireSystemException
	{
		if(mEntityRegistrationCredential == null)
		{
			//Error_Message : Entity credentials not provided.
			throw new BikeHireSystemException(10017);
		}
		
		if(Util.isEmptyOrNullString(mEntityRegistrationCredential.getUserName()))
		{
			//Error_Message : Entity username is not provided.
			throw new BikeHireSystemException(10018);
		}
		
		if(Util.isEmptyOrNullString(mEntityRegistrationCredential.getNewPassword()))
		{
			//Error_Message : Entity new password is not provided.
			throw new BikeHireSystemException(10019);
		}
		
		if(Util.isEmptyOrNullString(mEntityRegistrationCredential.getConfirmPassword()))
		{
			//Error_Message : Entity confirm password is not provided.
			throw new BikeHireSystemException(10020);
		}
		
		if(!mEntityRegistrationCredential.getNewPassword().equals(mEntityRegistrationCredential.getConfirmPassword()))
		{
			//Error_Message : Password mismatch.
			throw new BikeHireSystemException(10021);
		}
		
		if(!LoginUtil.isValidPassword(mEntityRegistrationCredential.getNewPassword()))
		{
			//Error_Message : Password does not match password criteria.
			throw new BikeHireSystemException(10022);
		}
	}
	
}
