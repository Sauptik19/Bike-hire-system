package edu.srh.bikehire.service;


import edu.srh.bikehire.exception.BikeHireSystemException;

/**
 * This class provides different notification services required for this system 
 *
 */
public interface NotificationService {

	/**
	 * This method sends notification verification token for onboarding and forgot password password.
	 * @param emailId
	 * @param pSecurityCode
	 * @param isOnBoardingEmail
	 * @throws BikeHireSystemException
	 */
	public void emailVerification(String emailId, String pSecurityCode, boolean isOnBoardingEmail) throws BikeHireSystemException;
	
	/**
	 * This method sends notification password reset successful.
	 * @param emailId
	 * @throws BikeHireSystemException
	 */
	public void passwordResetSuccess(String emailId) throws BikeHireSystemException;
	
	/**
	 * This method sends notification for booking confirmation.
	 * @param orderId
	 * @param emailId
	 * @throws BikeHireSystemException
	 */
	public void bookingConfirmation(int orderId, String emailId) throws BikeHireSystemException;
	
	/**
	 * This method sends notification for booking cancellation
	 * @param orderId
	 * @param emailId
	 * @throws BikeHireSystemException
	 */
	public void cancelBooking(int orderId, String emailId) throws BikeHireSystemException;
	
	/**
	 * This method sends notification for order invoice
	 * @param orderId
	 * @param invoiceId
	 * @param finalAmount
	 * @param emailId
	 * @throws BikeHireSystemException
	 */
	public void orderInvoice(int orderId, String invoiceId, int finalAmount ,String emailId) throws BikeHireSystemException;
	
	
	
}
