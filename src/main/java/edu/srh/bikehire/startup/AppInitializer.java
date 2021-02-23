package edu.srh.bikehire.startup;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.jasper.DBConnectionFactory;
import edu.srh.bikehire.login.impl.ResetPasswordCache;
import edu.srh.bikehire.login.impl.ResetPasswordCacheCleanup;

public class AppInitializer {
	
	private static ResetPasswordCache sResetPasswordCache = null;
	private ResetPasswordCacheCleanup mCacheCleanup = null;
	private Thread mResetPasswordCacheCleanupThread = null;
	
	public void initializeApplication() throws BikeHireSystemException
	{
		
		sResetPasswordCache = new ResetPasswordCache();
		sResetPasswordCache.initialize();
		
		mCacheCleanup = new ResetPasswordCacheCleanup(sResetPasswordCache);
		mResetPasswordCacheCleanupThread = new Thread(mCacheCleanup);
		mResetPasswordCacheCleanupThread.start();
		DBConnectionFactory.initializeFactory();
	}
	
	
	public void terminateApplication() throws InterruptedException
	{
		if(mResetPasswordCacheCleanupThread != null && mCacheCleanup != null)
		{
			mCacheCleanup.stopThread();
			mResetPasswordCacheCleanupThread.join();
		}
		
		DBConnectionFactory.close();
	}


	public static ResetPasswordCache getResetPasswordCache() {
		return sResetPasswordCache;
	}
	
}
