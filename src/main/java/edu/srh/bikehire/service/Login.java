package edu.srh.bikehire.service;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.login.ResetPasswordValidator;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.EntityAccount;
import edu.srh.bikehire.service.core.EntityLoginCredential;
import edu.srh.bikehire.service.core.EntityRegistrationCredential;

/**
 * This class provides all the services related to user management.  
 */
public interface Login {
	
	/**
	 * This method will validate user login credentials for authentication.
	 * @param pInputEntityCredentials
	 * @return - return Entity object for 
	 * @throws BikeHireSystemException
	 */
	public Entity authenticate(EntityLoginCredential pInputEntityCredentials) throws BikeHireSystemException;
	
	/**
	 * This method sends security token for reset password/Forgot password process.
	 * @param pEmailAddress
	 * @return - {@link ResetPasswordValidator} has methods to validated entered security token.
	 * @throws BikeHireSystemException
	 */
	public ResetPasswordValidator sendSecurityTokenForResetPassword(String pEmailAddress) throws BikeHireSystemException;
	
	/**
	 * This method will register new users and send email verification token.
	 * @param pEntity
	 * @param pEntityCredential
	 * @return - {@link ResetPasswordValidator} has methods to validated entered security token.
	 * @throws BikeHireSystemException
	 */
	public ResetPasswordValidator registerUserAccount(Entity pEntity, EntityRegistrationCredential pEntityCredential) throws BikeHireSystemException;
	
	/**
	 * This method will validate new password for resetting password.
	 * @param pEntityCredential
	 * @throws BikeHireSystemException - if password policy is not matched.
	 */
	public void resetPassword(EntityRegistrationCredential pEntityCredential) throws BikeHireSystemException;
	
	/**
	 * This method deactivates user account.
	 * @param pEntity - User information.
	 * @throws BikeHireSystemException
	 */
	public void deactivateAccount(Entity pEntity) throws BikeHireSystemException;
	
	/**
	 * This method will fetch and return user account information.
	 * @param pUserId
	 * @return
	 * @throws BikeHireSystemException
	 */
	public EntityAccount getAccountInfo(int pUserId) throws BikeHireSystemException;
	
	/**
	 * This method will mark user account as active
	 * @param pUserId
	 * @return
	 * @throws BikeHireSystemException
	 */
	public boolean markUserAccountAsActive(int pUserId) throws BikeHireSystemException;
}
