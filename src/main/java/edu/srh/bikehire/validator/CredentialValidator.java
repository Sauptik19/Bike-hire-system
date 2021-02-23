package edu.srh.bikehire.validator;

import edu.srh.bikehire.dto.UserCredentialDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class CredentialValidator {

	private UserCredentialDTO mUserCredentail;

	public CredentialValidator(UserCredentialDTO pUserCredentail) {
		mUserCredentail = pUserCredentail;
	}

	public void validateCredential() throws BikeHireSystemException {

		if (mUserCredentail == null) {
			//ERRORMESSAGE: User Credential not found.
			throw new BikeHireSystemException(10092);
		}

		if (mUserCredentail.getUserID() <= 0) {
			//ERRORMESSAGE: Invalid User ID.
			throw new BikeHireSystemException(10093);
		}
		
		validateUserName();

		if (Util.isEmptyOrNullString(mUserCredentail.getPasswordHash())) {
			//ERRORMESSAGE: Invalid Password Hash.
			throw new BikeHireSystemException(10094);
		}

		if (Util.isEmptyOrNullString(mUserCredentail.getPasswordSalt())) {
			//ERRORMESSAGE: Invalid Password Salt.
			throw new BikeHireSystemException(10095);
		}

	}

	public void validateUserName() throws BikeHireSystemException {
		if (Util.isEmptyOrNullString(mUserCredentail.getUserName())) {
			//ERRORMESSAGE: Invalid User name.
			throw new BikeHireSystemException(10079);
		}
	}

}
