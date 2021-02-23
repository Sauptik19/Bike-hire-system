package edu.srh.bikehire.dashboard.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class EmailServerConfiguration {
	
	private static EmailServerConfiguration sEmailServerConfiguration;
	
	private static final String LOCK_FOR_GET_INSTANCE = "emailserverconfiguration_lock";
	private static final String RELATIVE_EMAIL_SERVER_CONFIG_FILE_PATH = System.getProperty("user.dir") + File.separator +"src/main/resources/mail_config.properties";
	
	private String mAuthRequired;
	
	private String mTLSEnabled;
	
	private String mHostname;
	
	private String mPortNumber;
	
	private String mUserEmailId;
	
	private String mPassword;
	
	private EmailServerConfiguration()
	{
		
	}
	
	public static EmailServerConfiguration getInstance() throws BikeHireSystemException
	{
		if(sEmailServerConfiguration != null)
		{
			return sEmailServerConfiguration;
		}
		
		synchronized (LOCK_FOR_GET_INSTANCE) {
			
			if(sEmailServerConfiguration != null)
			{
				return sEmailServerConfiguration;
			}
			EmailServerConfiguration lEmailServerConfig = new EmailServerConfiguration();
			lEmailServerConfig.readFromFile(RELATIVE_EMAIL_SERVER_CONFIG_FILE_PATH);
		}
		
		return sEmailServerConfiguration;
	}
	
	public String getAuthRequired() {
		return mAuthRequired;
	}
	
	public String getTLSEnabled() {
		return mTLSEnabled;
	}
	
	public String getHostname() {
		return mHostname;
	}
	
	public String getPortNumber() {
		return mPortNumber;
	}
	
	public String getUserEmailId() {
		return mUserEmailId;
	}
	
	public String getPassword() {
		return mPassword;
	}
	
	private void readFromFile(String pFilePath) throws BikeHireSystemException
	{
		
		String lPropertiesFile = pFilePath;
		Properties lProperties = new Properties();
		InputStream lInputStream = null;
		try
		{
			lInputStream = new FileInputStream(lPropertiesFile);
			
			lProperties.load(lInputStream);
			this.readAndValidateFile(lProperties);
			
		}
		catch(IOException lException)
		{
			//ERRORMESSAGE: Unable to validate the Properties File.
			throw new BikeHireSystemException(10066);
		}
		finally
		{
			if(lInputStream != null)
			{
				try
				{
					lInputStream.close();
				}
				catch(IOException lException)
				{
					//ERRORMESSAGE: Unable to close the input stream while reading the properties file.
					throw new BikeHireSystemException(10067);
				}
			}
		}
		sEmailServerConfiguration = this;
	}
	
	private void readAndValidateFile(Properties pProperties) throws BikeHireSystemException
	{
		this.setAuthRequired(readAndValidateAuthRequired(pProperties));
		this.setTLSEnabled(readAndValidateTLSEnabled(pProperties));
		this.setHostname(readAndValidateHostName(pProperties));
		this.setPortNumber(readAndValidatePort(pProperties));
		this.setUserEmailId(readAndValidateUserEmailId(pProperties));
		this.setPassword(readAndValidateUserPassword(pProperties));
	}

	private String readAndValidateAuthRequired(Properties pProperties) throws BikeHireSystemException
	{
		String lAuthRequired = pProperties.getProperty("mail.smtp.auth");
		if(Util.isEmptyOrNullString(lAuthRequired))
		{
			//ERRORMESSAGE: Unable to validate the AuthRequired from Properties File.
			throw new BikeHireSystemException(10068);
		}
		
		return lAuthRequired;
	}
	
	private String readAndValidateTLSEnabled(Properties pProperties) throws BikeHireSystemException
	{
		String lTLSEnabled = pProperties.getProperty("mail.smtp.starttls.enable");
		if(Util.isEmptyOrNullString(lTLSEnabled))
		{
			//ERRORMESSAGE: Unable to validate the TLSEnabled from Properties File.
			throw new BikeHireSystemException(10069);
		}
		
		return lTLSEnabled;
	}
	
	private String readAndValidateHostName(Properties pProperties) throws BikeHireSystemException
	{
		String lHostname = pProperties.getProperty("mail.smtp.host");
		if(Util.isEmptyOrNullString(lHostname))
		{
			//ERRORMESSAGE: Unable to validate the HostName from Properties File.
			throw new BikeHireSystemException(10070);
		}
		
		return lHostname;
	}
	
	private String readAndValidatePort(Properties pProperties) throws BikeHireSystemException
	{
		String lPort = pProperties.getProperty("mail.smtp.port");
		if(Util.isEmptyOrNullString(lPort))
		{
			//ERRORMESSAGE: Unable to validate the Port from Properties File.
			throw new BikeHireSystemException(10071);
		}
		
		return lPort;
	}
	
	private String readAndValidateUserEmailId(Properties pProperties) throws BikeHireSystemException
	{
		String lUserEmailId = pProperties.getProperty("user.emailid");
		if(Util.isEmptyOrNullString(lUserEmailId))
		{
			//ERRORMESSAGE: Unable to validate the User Email Id from Properties File.
			throw new BikeHireSystemException(10072);
		}
		
		return lUserEmailId;
	}
	
	private String readAndValidateUserPassword(Properties pProperties) throws BikeHireSystemException
	{
		String lUserPassword = pProperties.getProperty("user.password");
		if(Util.isEmptyOrNullString(lUserPassword))
		{
			//ERRORMESSAGE: Unable to validate the User Password from Properties File.
			throw new BikeHireSystemException(10073);
		}
		
		return lUserPassword;
	}
	
	
	protected void setAuthRequired(String mAuthRequired) {
		this.mAuthRequired = mAuthRequired;
	}

	protected void setTLSEnabled(String mTLSEnabled) {
		this.mTLSEnabled = mTLSEnabled;
	}

	protected void setHostname(String mHostname) {
		this.mHostname = mHostname;
	}

	protected void setPortNumber(String mPortNumber) {
		this.mPortNumber = mPortNumber;
	}

	protected void setUserEmailId(String mUserEmailId) {
		this.mUserEmailId = mUserEmailId;
	}

	protected void setPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	
}
