package edu.srh.bikehire.exception.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.exception.ErrorMessage;

public class ExceptionUtil {
	
	//Error Code Starts @ 10000
	public static Map<Long, ErrorMessage> smapErrorCodesToMessages = null;
	static
	{
		smapErrorCodesToMessages = new HashMap<Long, ErrorMessage>();
		smapErrorCodesToMessages.put(10000L, new ErrorMessage("Unexpected error occurred - {0} " , "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10001L, new ErrorMessage("Invalid token provided.", "Invalid security code provided."));
		smapErrorCodesToMessages.put(10002L, new ErrorMessage("Maximum allowed attempts reached.", "You have exceeded maximum allowed attempts. Please try after sometime."));
		smapErrorCodesToMessages.put(10003L, new ErrorMessage("Token has expired.", "Your security code has expired. Please request new security code."));
		smapErrorCodesToMessages.put(10004L, new ErrorMessage("Unable to generate password hash.", "Unexpected Error Occured!"));
		smapErrorCodesToMessages.put(10005L, new ErrorMessage("Login credentials not provided.", "Please enter valid login credentials."));
		smapErrorCodesToMessages.put(10006L, new ErrorMessage("Invalid login credentials provided.", "Please enter valid login credentials."));
		smapErrorCodesToMessages.put(10007L, new ErrorMessage("Entity information not provided.", "Please provide valid information for registration."));
		smapErrorCodesToMessages.put(10008L, new ErrorMessage("Entity first name is not provided.", "Please provide firstname for registration."));
		smapErrorCodesToMessages.put(10009L, new ErrorMessage("Entity last name is not provided.", "Please provide lastname for registration."));
		smapErrorCodesToMessages.put(10010L, new ErrorMessage("Entity email id is not provided.", "Please provide emailid for registration."));
		smapErrorCodesToMessages.put(10011L, new ErrorMessage("Entity email id {0} is not valid.", "Please provide valid emailid for registration."));
		smapErrorCodesToMessages.put(10012L, new ErrorMessage("Entity address is not provided.", "Please provide address for registration."));
		smapErrorCodesToMessages.put(10013L, new ErrorMessage("Entity gender is not provided.", "Please provide gender for registration."));
		smapErrorCodesToMessages.put(10014L, new ErrorMessage("Entity phone number is not provided.", "Please provide phone number for registration."));
		smapErrorCodesToMessages.put(10015L, new ErrorMessage("Entity Date of birth is not provided.", "Please provide date of birth for registration."));
		smapErrorCodesToMessages.put(10016L, new ErrorMessage("Entity phone number is invalid.", "Please provide valid phone number for registration."));
		smapErrorCodesToMessages.put(10017L, new ErrorMessage("Entity credentials not provided.", "Please provide valid username/password for registration."));
		smapErrorCodesToMessages.put(10018L, new ErrorMessage("Entity username is not provided.", "Please provide username for registration."));
		smapErrorCodesToMessages.put(10019L, new ErrorMessage("Entity new password is not provided.", "Please provide new password for registration."));
		smapErrorCodesToMessages.put(10020L, new ErrorMessage("Entity confirm password is not provided.", "Please provide confirm password for registration."));
		smapErrorCodesToMessages.put(10021L, new ErrorMessage("Password mismatch.", "New password and confirm password do not match."));
		smapErrorCodesToMessages.put(10022L, new ErrorMessage("Password does not match password criteria.", "Entered password does not match password criteria."));
		smapErrorCodesToMessages.put(10023L, new ErrorMessage("Invalid login credentials provided. Password hash matching failed.", "Invalid login credentials provided."));
		smapErrorCodesToMessages.put(10024L, new ErrorMessage("Invalid login credentials provided. Username doesn't exists. ", "Invalid login credentials provided."));
		smapErrorCodesToMessages.put(10025L, new ErrorMessage("Invalid email address provided for reset password. ", "Invalid email address provided for reset password."));
		smapErrorCodesToMessages.put(10026L, new ErrorMessage("Invalid email address {0} provided for reset password. ", "Account doesn't exist for email address {0}"));
		smapErrorCodesToMessages.put(10027L, new ErrorMessage("User with email address {0} already exists. ", "User account already exists with emaill address {0}") );
		smapErrorCodesToMessages.put(10028L, new ErrorMessage("User with username {0} already exists. ", "User account already exists with username {0}") );
		smapErrorCodesToMessages.put(10029L, new ErrorMessage("Bike rent details not provided.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10030L, new ErrorMessage("Invalid Bike Type Id", "Please provide valid Bike Type Id.") );
		smapErrorCodesToMessages.put(10031L, new ErrorMessage("Bike rent details not provided.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10032L, new ErrorMessage("Invalid Rent Per Hour", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10033L, new ErrorMessage("Invalid Rent Per Day", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10034L, new ErrorMessage("Invalid Bike Status for Add Bike Status Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10035L, new ErrorMessage("Invalid Bike Id for Add Bike Status Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10036L, new ErrorMessage("Invalid Bike Manufacturer for Add Bike Status Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10037L, new ErrorMessage("Invalid Bike Status for Update Bike Status Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10038L, new ErrorMessage("Invalid Bike Status for Update Bike Status Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10039L, new ErrorMessage("Invalid Bike Stock for Add Bike Stock Validation,", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10040L, new ErrorMessage("Invalid Bike Type Id for Add Bike Stock Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10041L, new ErrorMessage("Invalid Bike Stock for Update Bike Stock Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10042L, new ErrorMessage("Invalid Total Quantity for Bike Quantity Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10043L, new ErrorMessage("Bike Type not provided for Add Bike Type Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10044L, new ErrorMessage("Invalid Bike Type for Add Bike Type Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10045L, new ErrorMessage("Invalid Age Category for Add Bike Type Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10046L, new ErrorMessage("Bike not provided for Add Bike Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10047L, new ErrorMessage("Invalid Year Of Manufacture for Add Bike Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10048L, new ErrorMessage("Invalid Manufacture for Add Bike Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10049L, new ErrorMessage("Invalid Bike Title for Add Bike Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10050L, new ErrorMessage("Invalid WareHouse ID for Add Bike Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10051L, new ErrorMessage("Bike not provided for Delete Bike Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10052L, new ErrorMessage("Invalid Bike not provided for Update Bike Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10053L, new ErrorMessage("Invalid Bike ID for Bike Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10054L, new ErrorMessage("Invalid Deposit Amount for Deposit Amount Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10055L, new ErrorMessage("Warehouse not provided for Add Warehouse Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10056L, new ErrorMessage("Invalid Warehouse name for Add Warehouse Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10057L, new ErrorMessage("Invalid Warehouse location for Add Warehouse Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10058L, new ErrorMessage("Warehouse not provided for Update Warehouse Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10059L, new ErrorMessage("Invalid Storage Capacity for Warehouse Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10060L, new ErrorMessage("Invalid option is selected.", "Please select a valid option.") );
		smapErrorCodesToMessages.put(10061L, new ErrorMessage("Invalid option is selected.", "Please select a valid option.") );
		smapErrorCodesToMessages.put(10062L, new ErrorMessage("Invalid option is selected.", "Please select a valid option.") );
		smapErrorCodesToMessages.put(10063L, new ErrorMessage("Invalid option is selected.", "Please select a valid option.") );
		smapErrorCodesToMessages.put(10064L, new ErrorMessage("Inactive Account User is trying to login.", "Your Account is inactive. Please contact customer support!") );
		smapErrorCodesToMessages.put(10065L, new ErrorMessage("Invalid credentials are used for login.", "Invalid credentials! Please enter valid credentials.") );
		smapErrorCodesToMessages.put(10066L, new ErrorMessage("Unable to validate the Properties File.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10067L, new ErrorMessage("Unable to close the input stream while reading the properties file.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10068L, new ErrorMessage("Unable to validate the AuthRequired from Properties File.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10069L, new ErrorMessage("Unable to validate the TLSEnabled from Properties File.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10070L, new ErrorMessage("Unable to validate the HostName from Properties File.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10071L, new ErrorMessage("Unable to validate the Port from Properties File.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10072L, new ErrorMessage("Unable to validate the User Email Id from Properties File.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10073L, new ErrorMessage("Unable to validate the User Password from Properties File.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10074L, new ErrorMessage("Mail Sender didn't able to send an email.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10075L, new ErrorMessage("Age is not meeting the criteria.", "Age is not eligible for booking a bike. Please contact customer support!") );
		smapErrorCodesToMessages.put(10076L, new ErrorMessage("Photo is not provided.", "Please provide valid photo.") );
		smapErrorCodesToMessages.put(10077L, new ErrorMessage("Identity Proof is not provided.", "Please provide valid Identity Proof..") );
		smapErrorCodesToMessages.put(10078L, new ErrorMessage("Entity Account is not available.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10079L, new ErrorMessage("Invalid User name.", "Please provide valid username.") );
		smapErrorCodesToMessages.put(10080L, new ErrorMessage("Invalid User role.", "Please provide valid user role.") );
		smapErrorCodesToMessages.put(10081L, new ErrorMessage("Invalid Account status.", "Account status is not active. Please contact customer support!") );
		smapErrorCodesToMessages.put(10082L, new ErrorMessage("Bike Data Transfer Object Not Found.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10083L, new ErrorMessage("Bike Type Data Transfer Object Not Found.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10084L, new ErrorMessage("WareHouse Data Transfer Object Not Found.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10085L, new ErrorMessage("User Account Data Transfer Object Not Found.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10086L, new ErrorMessage("Current Order Data Transfer Object Not Found.", "Invalid order id provided.") );
		smapErrorCodesToMessages.put(10087L, new ErrorMessage("List of Current Order Data Transfer Objects Not Found.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10088L, new ErrorMessage("Order Payment Data Transfer Object Not Found.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10089L, new ErrorMessage("User Account status is not active.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10090L, new ErrorMessage("Bike Status Data Transfer Object Not Found.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10091L, new ErrorMessage("No bike is available.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10092L, new ErrorMessage("User Credential not found.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10093L, new ErrorMessage("Invalid User ID.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10094L, new ErrorMessage("Invalid Password Hash.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10095L, new ErrorMessage("Invalid Password Salt.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10096L, new ErrorMessage("Invalid Bike ID of Current Order Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10097L, new ErrorMessage("Invalid Bike ID of Current Order Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10098L, new ErrorMessage("Invalid Order mode of Current Order Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10099L, new ErrorMessage("Invalid Actual dropoff Timestamp of Current Order Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10100L, new ErrorMessage("Invalid Booking Timestamp of Current Order Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10101L, new ErrorMessage("Invalid Dropoff Timestamp of Current Order Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10102L, new ErrorMessage("Invalid Pickup Timestamp of Current Order Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10103L, new ErrorMessage("Invalid User ID of User Account Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10104L, new ErrorMessage("Invalid User Name of User Account Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10105L, new ErrorMessage("Invalid User Role of User Account Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10106L, new ErrorMessage("Invalid User Account Status of User Account Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10107L, new ErrorMessage("Invalid Creation TimeStamp of User Account Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10108L, new ErrorMessage("Invalid Last Modified TimeStamp of User Account Data Transfer Object.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10109L, new ErrorMessage("User not found in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10110L, new ErrorMessage("Invalid User's First Name in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10111L, new ErrorMessage("Invalid User's Last Name in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10112L, new ErrorMessage("Invalid User's Gender in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10113L, new ErrorMessage("User's Age is not meeting age criteria in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10114L, new ErrorMessage("Invalid User's Address in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10115L, new ErrorMessage("Invalid User's Email Address in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10116L, new ErrorMessage("Invalid User's Phone Number in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10117L, new ErrorMessage("Invalid User's ID in User Validation.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10118L, new ErrorMessage("Invalid option selected.", "Please select a valid option.") );
		smapErrorCodesToMessages.put(10119L, new ErrorMessage("Invalid file path.", "Please provide a valid file path.") );
		smapErrorCodesToMessages.put(10120L, new ErrorMessage("Invalid connection string.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10121L, new ErrorMessage("Invalid connection user name.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10122L, new ErrorMessage("Invalid connection user password.", "Unexpected error occurred. Please try after some time.") );
		smapErrorCodesToMessages.put(10123L, new ErrorMessage("Invalid connection class name.", "Unexpected error occurred. Please try after some time.") );
	}
	
	public static String getErrorMessage(long pErrorCode, Object[] pPlaceHolderValues)
	{
		ErrorMessage lErrorMessage = smapErrorCodesToMessages.get(pErrorCode); 
		if(lErrorMessage != null)
		{
			if(pPlaceHolderValues == null)
			{
				return lErrorMessage.getErrorMessage();
			}
			MessageFormat lMessageFormat = new MessageFormat(lErrorMessage.getErrorMessage());
			
			return lMessageFormat.format(pPlaceHolderValues);
		}
		return "Unexpected Error Occured!";
	}
	
	public static String getDisplayMessage(long pErrorCode, Object[] pPlaceHolderValues)
	{
		ErrorMessage lErrorMessage = smapErrorCodesToMessages.get(pErrorCode); 
		if(lErrorMessage != null)
		{
			if(pPlaceHolderValues == null)
			{
				return lErrorMessage.getDisplayMessage();
			}
			MessageFormat lMessageFormat = new MessageFormat(lErrorMessage.getDisplayMessage());
			return lMessageFormat.format(pPlaceHolderValues);
		}
		return "Unexpected Error Occured!";
	}
	
	public static BikeHireSystemException wrapThrowableToBHSException(Throwable throwable)
	{
		if(throwable instanceof BikeHireSystemException)
		{
			return (BikeHireSystemException) throwable;
		}
		
		return new BikeHireSystemException(10000L, new Object[] {throwable.getMessage()}, throwable);
	}
}
