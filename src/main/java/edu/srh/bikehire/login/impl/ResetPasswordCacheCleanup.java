package edu.srh.bikehire.login.impl;

public class ResetPasswordCacheCleanup implements Runnable {
	private ResetPasswordCache mResetPasswordCache = null;
	
	private volatile boolean mbIsCallForTerminate = false;
	
	public ResetPasswordCacheCleanup(ResetPasswordCache pResetPasswordCache)
	{
		mResetPasswordCache = pResetPasswordCache;
	}

	public void run() {
		while(mbIsCallForTerminate)
		{
			mResetPasswordCache.removeExpiredCache();
			try {
				Thread.sleep(5*60*60);
			} catch (InterruptedException e) {
				//TODO: Log exception
				break;
			}
		}
	}
	
	public void stopThread()
	{
		mbIsCallForTerminate = true;
	}
}