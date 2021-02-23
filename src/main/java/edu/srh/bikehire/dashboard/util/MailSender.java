package edu.srh.bikehire.dashboard.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.srh.bikehire.exception.BikeHireSystemException;

public class MailSender {
	
	private static MailSender sMailSender;
	
	private static final String LOCK_FOR_MAIL_SENDER = "lock_for_mail_sender";
	
	private Session mSession;
	
	private MailSender()
	{
		
	}
	
	public static MailSender getInstance() throws BikeHireSystemException
	{
		if(sMailSender != null)
		{
			return sMailSender;
		}
		
		synchronized (LOCK_FOR_MAIL_SENDER) {
			if(sMailSender != null)
			{
				return sMailSender;
			}
			
			MailSender lMailSender = new MailSender();
			
			EmailServerConfiguration lEmailServerConfiguration = EmailServerConfiguration.getInstance();
			
			sMailSender = lMailSender;
		}
		
		return sMailSender;
	}
	
	public void sendEmail(List<String> pListOfTo,String pEmailSubject, String pEmailBodyText) throws BikeHireSystemException{ 
		
		try {
			
			EmailServerConfiguration lEmailServerConfiguration = EmailServerConfiguration.getInstance();
			final String lUsername = lEmailServerConfiguration.getUserEmailId();
			final String lPassword = lEmailServerConfiguration.getPassword();
			
			Properties lProperties = new Properties();
			lProperties.setProperty("mail.smtp.auth", lEmailServerConfiguration.getAuthRequired());
			lProperties.setProperty("mail.smtp.starttls.enable", lEmailServerConfiguration.getTLSEnabled());
			lProperties.setProperty("mail.smtp.host",lEmailServerConfiguration.getHostname());
			lProperties.setProperty("mail.smtp.port",lEmailServerConfiguration.getPortNumber());
			
			// Get the default Session object.
			Session session = Session.getInstance(lProperties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication( lUsername, lPassword);
				}
			});
			
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(EmailServerConfiguration.getInstance().getUserEmailId()));

			List<Address> llistOfTo = new ArrayList<Address>();

			for (String lToEmailAddress : pListOfTo) {
				llistOfTo.add(new InternetAddress(lToEmailAddress));
			}

			Address[] lAddresses = new Address[llistOfTo.size()];
			llistOfTo.toArray(lAddresses);
			// Set To: header field of the header.
			message.addRecipients(Message.RecipientType.TO, lAddresses);

			// Set Subject: header field
			message.setSubject(pEmailSubject);

			// Now set the actual message
			message.setText(pEmailBodyText);

			// Send message
			Transport.send(message);
			
		} catch (MessagingException mex) {
			//ERRORMESSAGE: Mail Sender didn't able to send an email.
			throw new BikeHireSystemException(10074, new Object[] {}, mex);
		}
	}
	
	private static Properties getConfigProperties(EmailServerConfiguration pConfigurations)
	{
		Properties lProperties = new Properties();
		lProperties.setProperty("mail.smtp.auth", pConfigurations.getAuthRequired());
		lProperties.setProperty("mail.smtp.starttls.enable", pConfigurations.getTLSEnabled());
		lProperties.setProperty("mail.smtp.host",pConfigurations.getHostname() );
		lProperties.setProperty("mail.smtp.port",pConfigurations.getPassword() );
		return lProperties;
	}

	protected static MailSender getsMailSender() {
		return sMailSender;
	}

	protected void setSession(Session pSession) {
		this.mSession = pSession;
	}
	
	protected Session getSession()
	{
		return mSession;
	}
	
	
}