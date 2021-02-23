package edu.srh.bikehire.validator;

import java.util.Calendar;

import edu.srh.bikehire.dto.UserDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class UserValidator {

	private UserDTO mUser;

	public UserValidator(UserDTO pUser) {
		mUser = pUser;

	}

	public void validateAddUser() throws BikeHireSystemException {
		
		if (mUser == null) {
			//ERRORMESSAGE: User not found in User Validation.
			throw new BikeHireSystemException(10109);
		}

		if (Util.isEmptyOrNullString(mUser.getFirstName())) {
			//ERRORMESSAGE: Invalid User's First Name in User Validation.
			throw new BikeHireSystemException(10110);

		}

		if (Util.isEmptyOrNullString(mUser.getLastName())) {
			//ERRORMESSAGE: Invalid User's Last Name in User Validation.
			throw new BikeHireSystemException(10111);

		}

		if (Util.isEmptyOrNullString(mUser.getGender())) {
			//ERRORMESSAGE: Invalid User's Gender in User Validation.
			throw new BikeHireSystemException(10112);

		}

		validateUserID();
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int ageLimit = year - mUser.getDob().YEAR;
		if (ageLimit < 95 && ageLimit > 7) {
			//ERRORMESSAGE: User's Age is not meeting age criteria in User Validation.
			throw new BikeHireSystemException(10113);
		}

		if (Util.isEmptyOrNullString(mUser.getAddress())) {
			//ERRORMESSAGE: Invalid User's Address in User Validation.
			throw new BikeHireSystemException(10114);

		}

		if (Util.isValidEmailAddress(mUser.getEmailId())) {
			//ERRORMESSAGE: Invalid User's Email Address in User Validation.
			throw new BikeHireSystemException(10115);

		}

		if (Util.isEmptyOrNullString(mUser.getPhoneNo())) {
			//ERRORMESSAGE: Invalid User's Phone Number in User Validation.
			throw new BikeHireSystemException(10116);
		}

		
	}
	
	
	public void validateUserID() throws BikeHireSystemException{
		if(mUser.getId() <= 0)
		{
			//ERRORMESSAGE: Invalid User's ID in User Validation.
			throw new BikeHireSystemException(10117);
		
		}
	}
}
