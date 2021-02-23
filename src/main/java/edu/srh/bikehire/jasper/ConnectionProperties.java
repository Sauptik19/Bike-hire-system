package edu.srh.bikehire.jasper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.util.Util;

public class ConnectionProperties {

	private static final String RELATIVE_DB_CONNECTION_CONFIG_FILE_PATH = System.getProperty("user.dir") + File.separator +"src/main/resources/META-INF/connection.properties";
	
	private static ConnectionProperties connectionProperties;
	
	private String connectionString;
	
	private String username;
	
	private String password;
	
	private String className;
	
	private ConnectionProperties()
	{
		
	}
	
	public static ConnectionProperties getInstance() throws BikeHireSystemException
	{
		ConnectionProperties connectionPropertiesTemp = new ConnectionProperties();
		connectionPropertiesTemp.readFromFile(RELATIVE_DB_CONNECTION_CONFIG_FILE_PATH);
		connectionProperties = connectionPropertiesTemp;
		return connectionProperties;
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
	}

	private void readAndValidateFile(Properties pProperties) throws BikeHireSystemException
	{
		this.setConnectionString(readAndValidateConnectionString(pProperties));
		this.setUsername(readAndValidateUsername(pProperties));
		this.setPassword(readAndValidatePassword(pProperties));
		this.setClassName(readAndValidateClassname(pProperties));
	}
	
	private String readAndValidateConnectionString(Properties pProperties) throws BikeHireSystemException
	{
		String connectionString = pProperties.getProperty("connection.url");
		if(Util.isEmptyOrNullString(connectionString))
		{
			//ERRORMESSAGE: Invalid connection string. 
			throw new BikeHireSystemException(10120);
		}
		return connectionString;
	}
	
	private String readAndValidateUsername(Properties pProperties) throws BikeHireSystemException
	{
		String username = pProperties.getProperty("connection.username");
		if(Util.isEmptyOrNullString(username))
		{
			//ERRORMESSAGE: Invalid connection user name.
			throw new BikeHireSystemException(10121);
		}
		return username;
	}
	
	private String readAndValidatePassword(Properties pProperties) throws BikeHireSystemException
	{
		String password = pProperties.getProperty("connection.password");
		if(Util.isEmptyOrNullString(password))
		{
			//ERRORMESSAGE: Invalid connection user password.
			throw new BikeHireSystemException(10122);
		}
		return password;
	}
	
	private String readAndValidateClassname(Properties pProperties) throws BikeHireSystemException
	{
		String classname = pProperties.getProperty("connection.class.name");
		if(Util.isEmptyOrNullString(classname))
		{
			//ERRORMESSAGE: Invalid connection class name.
			throw new BikeHireSystemException(10123);
		}
		return classname;
	}
	
	public String getConnectionString() {
		return connectionString;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getClassName() {
		return className;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
