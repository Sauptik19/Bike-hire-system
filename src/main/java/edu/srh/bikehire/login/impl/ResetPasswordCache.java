package edu.srh.bikehire.login.impl;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResetPasswordCache {
	private static Map<String, DBBasedResetPasswordValidator> smapResetPasswordCache= null;
	
	private static final String CACHE_LOCK = "Cache_lock";
	
	private static final long CACHE_CLEANUP_TIME_LIMIT_IN_SEC = 60*60l;
	public void initialize()
	{
		synchronized (CACHE_LOCK) {			
			if(smapResetPasswordCache == null)
			{
				smapResetPasswordCache = new ConcurrentHashMap<String, DBBasedResetPasswordValidator>();
			}
		}
	}
	
	public void putInCache(String pEmailAddress, DBBasedResetPasswordValidator pResetPasswordValidator)
	{
		synchronized (CACHE_LOCK) {
			smapResetPasswordCache.put(pEmailAddress, pResetPasswordValidator);
		}
	}
	
	public void removeFromCache(String pEmailAddress)
	{
		synchronized (CACHE_LOCK) {
			smapResetPasswordCache.remove(pEmailAddress);
		}
	}
	
	public void removeExpiredCache()
	{
		Calendar lCurrentTime = Calendar.getInstance();
		synchronized (CACHE_LOCK) {
			for (Map.Entry<String, DBBasedResetPasswordValidator> entry : smapResetPasswordCache.entrySet())
			{
			    if((lCurrentTime.get(Calendar.SECOND) -  entry.getValue().getLastAttemptTime().get(Calendar.SECOND))> CACHE_CLEANUP_TIME_LIMIT_IN_SEC)
			    {
			    	smapResetPasswordCache.remove(entry.getKey());
			    }
			}
		}
	}
	
	
}
