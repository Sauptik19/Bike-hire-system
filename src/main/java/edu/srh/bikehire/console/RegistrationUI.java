package edu.srh.bikehire.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.login.LoginConstants;
import edu.srh.bikehire.login.ResetPasswordValidator;
import edu.srh.bikehire.service.core.impl.Customer;
import edu.srh.bikehire.service.core.impl.CustomerAccount;
import edu.srh.bikehire.service.core.impl.CustomerRegistrationCredential;
import edu.srh.bikehire.service.impl.DBBasedLoginService;

public class RegistrationUI {
	
	public int register(Scanner in) throws BikeHireSystemException
	{
		System.out.println("Registration");
		
		System.out.println("Enter your First Name : ");
		String firstname=in.nextLine();
		System.out.println("Enter your Last Name : ");
		String lastname=in.nextLine();
		System.out.println("Enter your EmailID: ");
		String emailId=in.nextLine();
		System.out.println("Choose username for your profile : ");
		String userName = in.nextLine();
		System.out.println("Enter your Gender : ");
		String gender=in.nextLine();
		System.out.println("Enter date of birth (dd/MM/yyyy) : ");
		String dob = in.nextLine();
		Calendar dateOfBirth = getCalendarFromInput(dob);
		System.out.println("Enter your Address : ");
    	String address=in.nextLine();
		System.out.println("Enter your Phone No: ");
		String phoneNo=in.nextLine();
		System.out.println("Please provide profile picture(file path): ");
		String profilePhotoFilePath=in.nextLine();
		System.out.println("Please provide ID proof(file path): ");
		String idProofFilePath=in.nextLine();
		
		Customer customer  = new Customer();
		customer.setFirstName(firstname);
		customer.setLastName(lastname);
		customer.setAddress(address);
		customer.setDOB(dateOfBirth);
		customer.setEmailID(emailId);
		customer.setGender(gender);
		customer.setPhoneNumber(phoneNo);
		try {
			customer.setPhotoBytes(Files.readAllBytes(new File(profilePhotoFilePath).toPath()));
			customer.setIdentityProofBytes(Files.readAllBytes(new File(idProofFilePath).toPath()));
		} catch (IOException e) {
			System.out.println("Please provide valid file path.");
			//ERRORMESSAGE: Invalid file path.
			throw new BikeHireSystemException(10119);
		}
		CustomerAccount account = new CustomerAccount();
		account.setAccountStatus(LoginConstants.LOGIN_ACCOUNT_STATUS_ACTIVE);
		account.setUserRole(LoginConstants.LOGIN_ACCOUNT_ROLE_CUSTOMER);
		account.setUserName(userName);
		customer.setEntityAccount(account);
		
		System.out.println("Choose new password for your profile ((Password must be 8 characters long, should have atleast 1 Uppercase character, 1 Lowercase character, 1 Numeric character and 1 Special Character from '@#$%^&+!=')) : ");
		String newPassword = in.nextLine();
		System.out.println("Confirm password for your profile : ");
		String confirmPasword = in.nextLine();
		
		CustomerRegistrationCredential registrationCredential = new CustomerRegistrationCredential();
		registrationCredential.setUserName(userName);
		registrationCredential.setNewPassword(newPassword);
		registrationCredential.setConfirmPassword(confirmPasword);
		
		System.out.println("Please wait...");
		
		DBBasedLoginService loginService = new DBBasedLoginService();
		ResetPasswordValidator resetPasswordValidator = loginService.registerUserAccount(customer, registrationCredential);
		
		System.out.println("User Successfully Registered! We have sent an email to verify your account.");
		
		System.out.println("Please enter security code to verify your account.");
		boolean isVerified = true;
		int attemptCount = 0;
		do
		{
			if(attemptCount >= 3)
			{
				System.out.println("Maximum unsuccessful verify attempts reached.");
				return -1;
			}
			String securityCode = in.nextLine();
			try
			{	
				attemptCount++;
				resetPasswordValidator.validateToken(securityCode);
				System.out.println("User account verified successfully.");
				loginService.markUserAccountAsActive(resetPasswordValidator.getUserId());
				return 0;
			}
			catch(BikeHireSystemException exception)
			{
				System.out.println("Please enter valid security token : ");
			}
			
		}
		while(isVerified);
		return -1;
	}
	private Calendar getCalendarFromInput(String input)
	{
		StringTokenizer stringTokenizer = new StringTokenizer(input, "/");
		int date = Integer.parseInt(stringTokenizer.nextToken());
		int month = Integer.parseInt(stringTokenizer.nextToken());
		int year = Integer.parseInt(stringTokenizer.nextToken());
		int modifiedMonth = 0;
		if(month == 1)
		{
			modifiedMonth = 0;
		}
		else if(month > 1 && month <=12 )
		{
			modifiedMonth = month -1;
		}
		Calendar returnCalendar = Calendar.getInstance();
		returnCalendar.set(year, modifiedMonth, date);
		return returnCalendar;
	}

}
