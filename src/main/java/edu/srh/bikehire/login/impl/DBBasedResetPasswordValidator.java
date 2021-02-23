package edu.srh.bikehire.login.impl;

import java.util.Calendar;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.login.ResetPasswordValidator;
import edu.srh.bikehire.login.util.LoginUtil;
import edu.srh.bikehire.service.impl.EmailNotificationService;
import edu.srh.bikehire.startup.AppInitializer;

public class DBBasedResetPasswordValidator implements ResetPasswordValidator {
	
	private static final int MAXIMUM_ALLOWED_ATTEMPTS = 5;
	
	private static final int MAXIMUM_ALLOWED_DURATION_IN_SEC = 15*60;
	
	private int miAttemptCount = 0;
	
	private String mstrToken;
	
	private int miUserId;
	
	private Calendar mGenerationTime;
	
	private Calendar mLastAttemptTime;
	
	private String mstrUserEmailId;
	
	private boolean mbIsEmailVerificationOnboarding;
	
	public DBBasedResetPasswordValidator(int pUserId, String pEmailAddress, boolean pIsEmailVerification)
	{
		miUserId = pUserId;
		mstrUserEmailId = pEmailAddress;
		mbIsEmailVerificationOnboarding = pIsEmailVerification;
	}
	
	public void validateToken(String pToken) throws BikeHireSystemException {
		
		mLastAttemptTime = Calendar.getInstance();
		miAttemptCount++;
		if(pToken == null || pToken.isEmpty() || pToken.length() !=6)
		{
			//ERROR_MESSAGE : Invalid token provided.
			throw new BikeHireSystemException(10001);
		}
		
		if(miAttemptCount >= MAXIMUM_ALLOWED_ATTEMPTS)
		{
			//ERROR_MESSAGE : Maximum allowed attempts reached.
			throw new BikeHireSystemException(10002);
		}
		
		if(mstrToken.equals(pToken.trim()) == false)
		{
			//ERROR_MESSAGE : Invalid token provided.
			throw new BikeHireSystemException(10001); 
		}
		//Token has matched, check token expiry
		
		if((Calendar.getInstance().get(Calendar.SECOND) - mGenerationTime.get(Calendar.SECOND)) > MAXIMUM_ALLOWED_DURATION_IN_SEC)
		{
			//ERROR_MESSAGE : Token has expired.
			throw new BikeHireSystemException(10003);
		}
		
		//Token matched successfully, remove PasswordReset Validator from cache. 
		AppInitializer.getResetPasswordCache().removeFromCache(mstrUserEmailId);
	}
	
	public void sendNotfificationForSecurityCode() throws BikeHireSystemException
	{
		EmailNotificationService emailNotificationService = new EmailNotificationService();
		emailNotificationService.emailVerification(mstrUserEmailId, mstrToken, isEmailVerificationOnboarding());
	}
	
	public void generateToken()
	{
		mGenerationTime = Calendar.getInstance();
		miAttemptCount = 0;
		mstrToken = LoginUtil.getResetPasswordToken();
		AppInitializer.getResetPasswordCache().putInCache(mstrUserEmailId, this);
	}

	public String getUserEmailId() {
		return mstrUserEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		mstrUserEmailId = userEmailId;
	}

	public Calendar getLastAttemptTime() {
		return mLastAttemptTime;
	}

	public boolean isEmailVerificationOnboarding() {
		return mbIsEmailVerificationOnboarding;
	}

	public int getUserId() {
		return miUserId;
	}
	
	
}
