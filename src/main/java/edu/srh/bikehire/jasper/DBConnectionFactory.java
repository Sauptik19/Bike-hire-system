package edu.srh.bikehire.jasper;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.exception.BikeHireSystemException;

public class DBConnectionFactory {
	private static final Logger LOG = LogManager.getLogger(ReportGenerator.class);
	
	private static BasicDataSource basicDataSource  = null;
	
	private static final String DB_LOCK = "db_lock"; 
	
	private DBConnectionFactory()
	{
		
	}
	
	public static void initializeFactory() throws BikeHireSystemException
	{
		synchronized (DB_LOCK) {
			if(basicDataSource != null)
			{
				return;
			}
			BasicDataSource basicDataSourceTemp = new BasicDataSource();
			ConnectionProperties connectionProperties = ConnectionProperties.getInstance();
			basicDataSourceTemp.setDriverClassName(connectionProperties.getClassName());
			basicDataSourceTemp.setUrl(connectionProperties.getConnectionString());
			basicDataSourceTemp.setUsername(connectionProperties.getUsername());
			basicDataSourceTemp.setPassword(connectionProperties.getPassword());
			
			basicDataSource = basicDataSourceTemp;
		}
	}
	
	public static Connection getNewConnection() throws SQLException
	{
		return basicDataSource.getConnection();
	}
	
	public static void close()
	{
		if(basicDataSource != null)
		{
			try {
				basicDataSource.close();
			} catch (SQLException e) {
				LOG.error("createRentedBikeReportToday : " + e.getMessage(), e);
			}
		}
	}
}
