package edu.srh.bikehire.login.core;

import java.util.Calendar;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.login.util.LoginUtil;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.util.Util;

public class UserDetailsValidator {
	private Entity mEntity;
	
	public UserDetailsValidator(Entity pEntity)
	{
		mEntity = pEntity;
	}
	
	public void validateUserInformationForRegistartion() throws BikeHireSystemException
	{
		if(mEntity == null)
		{
			//Error_Message : Entity information not provided.
			throw new BikeHireSystemException(10007);
		}
		
		if(Util.isEmptyOrNullString(mEntity.getFirstName()))
		{
			//Error_Message : Entity first name is not provided.
			throw new BikeHireSystemException(10008);
		}
		
		if(Util.isEmptyOrNullString(mEntity.getLastName()))
		{
			//Error_Message : Entity last name is not provided.
			throw new BikeHireSystemException(10009);
		}
		
		if(Util.isEmptyOrNullString(mEntity.getEmailId()))
		{
			//Error_Message : Entity email id is not provided.
			throw new BikeHireSystemException(10010);
		}
		
		if(!Util.isValidEmailAddress(mEntity.getEmailId()))
		{
			//Error_Message : Entity email id {0} is not valid.
			throw new BikeHireSystemException(10011, new Object[] {mEntity.getEmailId()});
		}
		
		if(Util.isEmptyOrNullString(mEntity.getAddress()))
		{
			//Error_Message : Entity address is not provided.
			throw new BikeHireSystemException(10012);
		}
		
		if(Util.isEmptyOrNullString(mEntity.getGender()))
		{
			//Error_Message : Entity gender is not provided.
			throw new BikeHireSystemException(10013);
		}
		
		if(Util.isEmptyOrNullString(mEntity.getPhoneNumber()))
		{
			//Error_Message : Entity phone number is not provided.
			throw new BikeHireSystemException(10014);
		}
		
		if(mEntity.getPhoneNumber().length() < 10)
		{
			//Error_Message: Entity phone number is invalid.
			throw new BikeHireSystemException(10016);
		}
		
		if(!LoginUtil.isValidMobileNumber(mEntity.getPhoneNumber()))
		{
			//Error_Message: Entity phone number is invalid.
			throw new BikeHireSystemException(10016);
		}
		
		if(mEntity.getDOB() == null)
		{
			//Error_Message : Entity Date of birth is not provided.
			throw new BikeHireSystemException(10015);
		}
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int age = year - mEntity.getDOB().get(Calendar.YEAR);
		if (age > 95 && age < 7) {
			//ERRORMESSAGE: Age is not meeting the criteria.
			throw new BikeHireSystemException(10075);
		}
		
		if (mEntity.getPhoto() == null) {
			//ERRORMESSAGE: Photo is not provided.
			throw new BikeHireSystemException(10076);
		}
		
		if (mEntity.getIdentityProof() == null) {
			//ERRORMESSAGE: Identity Proof is not provided.
			throw new BikeHireSystemException(10077);
		}
		
		if(mEntity.getEntityAccount() == null) {
			//ERRORMESSAGE: Entity Account is not available.
			throw new BikeHireSystemException(10078);
		}
		
		validateUserAccountInformation();
	}
	
	public void validateUserAccountInformation() throws BikeHireSystemException
	{
		if(Util.isEmptyOrNullString(mEntity.getEntityAccount().getUserName()))
		{
			//ERRORMESSAGE: Invalid User name.
			throw new BikeHireSystemException(10079);
		}
		
		if(Util.isEmptyOrNullString(mEntity.getEntityAccount().getUserRole()))
		{
			//ERRORMESSAGE: Invalid User role.
			throw new BikeHireSystemException(10080);
		}
		
	}
	
	public void validateUserInformationForDeactiviation() throws BikeHireSystemException
	{
		if(mEntity == null)
		{
			//Error_Message : Entity information not provided.
			throw new BikeHireSystemException(10007);
		}
		
		if(Util.isEmptyOrNullString(mEntity.getEmailId()))
		{
			//Error_Message : Entity email id is not provided.
			throw new BikeHireSystemException(10010);
		}
		
		if(!Util.isValidEmailAddress(mEntity.getEmailId()))
		{
			//Error_Message : Entity email id {0} is not valid.
			throw new BikeHireSystemException(10011, new Object[] {mEntity.getEmailId()});
		}
	}
	
}
