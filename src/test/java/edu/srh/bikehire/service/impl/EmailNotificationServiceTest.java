package edu.srh.bikehire.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.srh.bikehire.exception.BikeHireSystemException;

public class EmailNotificationServiceTest {

	
	@Test (expected = BikeHireSystemException.class)
	public void testEmailIdNotValid() throws BikeHireSystemException {
		EmailNotificationService emailIdIncorrect = new EmailNotificationService();
		emailIdIncorrect.emailVerification("hgvg", "ctcc", true);
		
		
		EmailNotificationService ScIncorrect = new EmailNotificationService();
			ScIncorrect.emailVerification("hgvg", "", true);
			
		EmailNotificationService emailSentVerify = new EmailNotificationService();
		emailSentVerify.emailVerification("hgvg", "ctcc", false);
		
		
	}
	
	
	@Test
	
	public void testEmailIdSentIsValid() throws BikeHireSystemException {
		EmailNotificationService emailIdCheck = new EmailNotificationService();
		emailIdCheck.emailVerification("akanshanhce@gmail.com", "", true);
		
		EmailNotificationService securityCodeCheck = new EmailNotificationService();
		 securityCodeCheck.emailVerification("akanshanhce@gmail.com", "", false);
	}
	
	
	@Test (expected = BikeHireSystemException.class)
	public void passwordUnsuccessEmail() throws BikeHireSystemException {
		EmailNotificationService resetEmailUnSuccess = new EmailNotificationService();
		resetEmailUnSuccess.passwordResetSuccess("sdfdgf");
		
		
	}
	
	@Test
	public void passwordsuccessEmail() throws BikeHireSystemException {
		EmailNotificationService resetEmailSuccess = new EmailNotificationService();
		resetEmailSuccess.passwordResetSuccess("emailid@gmail.com");
		
	}
	
	
	@Test (expected = BikeHireSystemException.class)
	public void bookingConfirmIncorrect()throws BikeHireSystemException {
		EmailNotificationService incorrectOrderId = new EmailNotificationService();
		incorrectOrderId.bookingConfirmation(-1, "emailId@gmail.com");
		
		EmailNotificationService incorrectEmailId = new EmailNotificationService();
		incorrectEmailId.bookingConfirmation(1, "efdgv");
		
		
	}

	
	@Test
	public void bookingConfirm() throws BikeHireSystemException {
		EmailNotificationService vaildCredentials = new EmailNotificationService();
		vaildCredentials.bookingConfirmation(1, "emailid@gmail.com");
	}
	
	
     
	@Test (expected = BikeHireSystemException.class)
	public void cancelBookingIncorrect() throws BikeHireSystemException {
		EmailNotificationService incorrectCancelOrderid = new EmailNotificationService();
		incorrectCancelOrderid.cancelBooking(-1, "emailId@gmail.com");
		
		EmailNotificationService incorrectCancelEmailid = new EmailNotificationService();
		incorrectCancelEmailid.cancelBooking(1, "dfbfbhg");
	
	}
	
	@Test
	public void cancelBookingValid() throws BikeHireSystemException {
		EmailNotificationService cancellationCredential = new EmailNotificationService();
		cancellationCredential.cancelBooking(1, "emailId@gmail.com");
	}


	@Test (expected = BikeHireSystemException.class)
	public void invalidOrderInvoice() throws BikeHireSystemException {
		EmailNotificationService invalidOrderId = new EmailNotificationService();
		invalidOrderId.orderInvoice(-1, "abdc", 200, "emailId@gmail.com");
		
		
		EmailNotificationService invalidInvoiceId = new EmailNotificationService();
		invalidInvoiceId.orderInvoice(1," ", 200, "emailId@gmail.com");
		
		EmailNotificationService invalidFinalAmt = new EmailNotificationService();
		invalidFinalAmt.orderInvoice(1,"abfs", -200, "emailId@gmail.com");
		
		EmailNotificationService invalidEmail = new EmailNotificationService();
		invalidEmail.orderInvoice(1,"abfs", 200, "sdgfh");
		
		}
	
	@Test
	public void validOrderInvoice() throws BikeHireSystemException {
		EmailNotificationService validInvoice = new EmailNotificationService();
		validInvoice.orderInvoice(1, "sdafc", 200, "emailId@gmail.com");
       }
}
