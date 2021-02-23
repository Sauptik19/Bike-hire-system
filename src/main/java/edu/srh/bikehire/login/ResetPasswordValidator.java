package edu.srh.bikehire.login;

import edu.srh.bikehire.exception.BikeHireSystemException;

public interface ResetPasswordValidator {
	public void validateToken(String pToken) throws BikeHireSystemException;
	
	public void sendNotfificationForSecurityCode() throws BikeHireSystemException;
	
	public boolean isEmailVerificationOnboarding();
	
	public int getUserId();
}
