package edu.srh.bikehire.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dashboard.EmailNotificationType;
import edu.srh.bikehire.dashboard.util.MailSender;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.exception.util.ExceptionUtil;
import edu.srh.bikehire.service.NotificationService;

public class EmailNotificationService implements NotificationService {

	private static final Logger LOG = LogManager.getLogger(EmailNotificationService.class);
	
	public void emailVerification(String emailId, String pSecurityCode, boolean isOnboardingEmail) throws BikeHireSystemException {
		LOG.info("emailVerification : Start");
		try
		{			
			MailSender lNewEmail = MailSender.getInstance();
			
			List<String> pToEmailAddresses = new ArrayList<String>();
			pToEmailAddresses.add(emailId);
			
			String bodyTextLine = null;
			String subjectLine = null;
			if(isOnboardingEmail)
			{
				bodyTextLine = EmailNotificationType.REGISTRATION_CONFIRMATION.getBodyText();			
				bodyTextLine = bodyTextLine.replace("$SECURITY_CODE$", pSecurityCode);
				subjectLine = EmailNotificationType.REGISTRATION_CONFIRMATION.getSubjectLine();
			}
			else
			{
				bodyTextLine = EmailNotificationType.RESET_PASSWORD.getBodyText();			
				bodyTextLine = bodyTextLine.replace("$SECURITY_CODE$", pSecurityCode);
				subjectLine = EmailNotificationType.RESET_PASSWORD.getSubjectLine();
			}
			
			lNewEmail.sendEmail(pToEmailAddresses,subjectLine, bodyTextLine);
			LOG.info("emailVerification : End");
		}
		catch(Throwable throwable)
		{
			LOG.error("emailVerification : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public void passwordResetSuccess(String emailId) throws BikeHireSystemException {
		LOG.info("passwordResetSuccess : Start");
		try
		{			
			MailSender lpasswordreset = MailSender.getInstance();
			
			List<String> pToEmailAddresses = new ArrayList<String>();
			pToEmailAddresses.add(emailId);
			lpasswordreset.sendEmail(pToEmailAddresses, EmailNotificationType.RESET_PASSWORD_SUCCESS.getSubjectLine(),
					EmailNotificationType.RESET_PASSWORD_SUCCESS.getBodyText());
			LOG.info("passwordResetSuccess : End");
		}
		catch(Throwable throwable)
		{
			LOG.error("passwordResetSuccess : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}

	}

	public void bookingConfirmation(int orderId, String emailId) throws BikeHireSystemException {
		LOG.info("bookingConfirmation : Start");
		try
		{			
			MailSender lbookingconfirmed = MailSender.getInstance();
			
			List<String> pToEmailAddresses = new ArrayList<String>();
			pToEmailAddresses.add(emailId);
			String bodyTextLine = EmailNotificationType.BOOKING_CONFIRMATION.getBodyText().replace("$ORDER_ID$", String.valueOf(orderId));
			String subjectLine = EmailNotificationType.BOOKING_CONFIRMATION.getSubjectLine();
			
			lbookingconfirmed.sendEmail(pToEmailAddresses, subjectLine, bodyTextLine);
			LOG.info("bookingConfirmation : End");
		}
		catch(Throwable throwable)
		{
			LOG.error("bookingConfirmation : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public void cancelBooking(int orderId, String emailId) throws BikeHireSystemException {
		LOG.info("cancelBooking : Start");
		try
		{			
			MailSender lcancellation = MailSender.getInstance();
			List<String> pToEmailAddresses = new ArrayList<String>();
			pToEmailAddresses.add(emailId);
			String bodyTextLine = EmailNotificationType.BOOKING_CANCEL.getBodyText().replace("$ORDER_ID$", String.valueOf(orderId));
			String subjectLine = EmailNotificationType.BOOKING_CANCEL.getSubjectLine();
			lcancellation.sendEmail(pToEmailAddresses, subjectLine, bodyTextLine);
			LOG.info("cancelBooking : End");
		}
		catch(Throwable throwable)
		{
			LOG.error("cancelBooking : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public void orderInvoice(int orderId, String invoiceId, int finalAmount, String emailId) throws BikeHireSystemException {
		LOG.info("orderInvoice : Start");
		try
		{			
			MailSender lInvoice = MailSender.getInstance();
			List<String> pToEmailAddresses = new ArrayList<String>();
			pToEmailAddresses.add(emailId);
			String bodyTextLine = EmailNotificationType.BOOKING_INVOICE.getBodyText().replace("$ORDER_ID$", String.valueOf(orderId));
			bodyTextLine = bodyTextLine.replace("$INVOICE_ID$", invoiceId);
			bodyTextLine = bodyTextLine.replace("$AMOUNT$", String.valueOf(finalAmount));
			String subjectLine = EmailNotificationType.BOOKING_INVOICE.getSubjectLine();
			lInvoice.sendEmail(pToEmailAddresses, subjectLine,bodyTextLine);
			LOG.info("orderInvoice : End");
		}
		catch(Throwable throwable)
		{
			LOG.error("orderInvoice : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
}


