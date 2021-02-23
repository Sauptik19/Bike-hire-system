package edu.srh.bikehire.login.util;

import java.util.Random;
import java.util.UUID;

import edu.srh.bikehire.util.Util;

public class LoginUtil {
	
	/**
	 * 	^                 		# start-of-string
		<br/> (?=.*[0-9])       # a digit must occur at least once
		<br/> (?=.*[a-z])       # a lower case letter must occur at least once
		<br/> (?=.*[A-Z])       # an upper case letter must occur at least once
		<br/> (?=.*[@#$%^&+!=])  # a special character must occur at least once
		<br/> (?=\S+$)          # no whitespace allowed in the entire string
		<br/> .{8,}             # anything, at least eight places though
		<br/> $                 # end-of-string
	 */
	private static final String PASSWORD_REGEX_STRING = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{8,}$";
	
	private static final String PHONE_NO_REGEX_STRING = "^(\\+\\d{1,3}[- ]?)?\\d{10,12}$";
	
	public static String getResetPasswordToken()
	{
		int leftLimitCharacter = 65; // letter 'A'
	    int rightLimitCharacter = 90; // letter 'Z'
	    int leftLimitNumber =  49; //Number 49
	    int rightLimitNumber = 57; //Number 57
	    int targetStringLength = 6;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	    	float lRandomFloat = random.nextFloat();
	    	int randomLimitedInt = 65;
	    	if(lRandomFloat < 0.5f)
	    	{	    		
	    		randomLimitedInt = leftLimitCharacter + (int) 
	    				( lRandomFloat * (rightLimitCharacter - leftLimitCharacter + 1));
	    	}
	    	else
	    	{
	    		randomLimitedInt = leftLimitNumber + (int) 
	    				( lRandomFloat * (rightLimitNumber - leftLimitNumber + 1));
	    	}
	    	
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return generatedString;
	}
	
	public static boolean isValidPassword(String pInputPassword)
	{
		if(Util.isEmptyOrNullString(pInputPassword))
		{
			return false;
		}
		
		if(pInputPassword.matches(PASSWORD_REGEX_STRING))
		{
			return true;
		}
		return false;
	}
	
	public static boolean isValidMobileNumber(String pMobileNumber)
	{
		if(Util.isEmptyOrNullString(pMobileNumber))
		{
			return false;
		}
		
		if(pMobileNumber.matches(PHONE_NO_REGEX_STRING))
		{
			return true;
		}
		return false;
	}
	
}
