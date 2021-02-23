package edu.srh.bikehire.assetmangement.impl;

import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class BikeTypeValidator {
	private BikeTypeDTO mBikeType;

	public BikeTypeValidator(BikeTypeDTO pBikeType) {
		mBikeType = pBikeType;
	}

	public void validateAddBikeType() throws BikeHireSystemException {
		if (mBikeType == null) {
			//ERROR MESSAGE: Bike Type not provided for Add Bike Type Validation.
			throw new BikeHireSystemException(10043);
		}

		if (Util.isEmptyOrNullString(mBikeType.getBikeType())) {
			//ERROR MESSAGE: Invalid Bike Type for Add Bike Type Validation.
			throw new BikeHireSystemException(10044);
		}

		if (Util.isEmptyOrNullString(mBikeType.getAgeCategory())) {
			//ERROR MESSAGE: Invalid Age Category for Add Bike Type Validation.
			throw new BikeHireSystemException(10045);

		}
		
		// TODO:OTHER BIKETYPE EXCEPTIONS

	}

}
