package edu.srh.bikehire.validator;

import edu.srh.bikehire.dto.UserAccountDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class UserAccountValidator {

	private UserAccountDTO mUserAccount;

	public UserAccountValidator(UserAccountDTO pUserAccount) {
		mUserAccount = pUserAccount;
	}

	public void validateUserAccount() throws BikeHireSystemException {
		
		if (mUserAccount == null) {
			//ERRORMESSAGE: User Account Data Transfer Object Not Found.
			throw new BikeHireSystemException(10085);
		}
		
		if(mUserAccount.getId() <= 0) {
			//ERRORMESSAGE: Invalid User ID of User Account Data Transfer Object.
			throw new BikeHireSystemException(10103);
		}
		
		if(Util.isEmptyOrNullString(mUserAccount.getUserName())) {
			//ERRORMESSAGE: Invalid User Name of User Account Data Transfer Object.
			throw new BikeHireSystemException(10104);
		}
		
		if(Util.isEmptyOrNullString(mUserAccount.getRole())) {
			//ERRORMESSAGE: Invalid User Role of User Account Data Transfer Object.
			throw new BikeHireSystemException(10105);
		}

		if(Util.isEmptyOrNullString(mUserAccount.getAccountStatus())) {
			//ERRORMESSAGE: Invalid User Account Status of User Account Data Transfer Object.
			throw new BikeHireSystemException(10106);
		}
		
		if(mUserAccount.getCreationTimeStamp() == null) {
			//ERRORMESSAGE: Invalid Creation TimeStamp of User Account Data Transfer Object.
			throw new BikeHireSystemException(10107);
		}
		
		if(mUserAccount.getLastModifiedTimeStamp() == null) {
			//ERRORMESSAGE: Invalid Last Modified TimeStamp of User Account Data Transfer Object.
			throw new BikeHireSystemException(10108);
	
		}
	}

}
