package edu.srh.bikehire.service.impl;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.DAOFactory;
import edu.srh.bikehire.dao.DAOFactoryType;
import edu.srh.bikehire.dao.UserAccountDAO;
import edu.srh.bikehire.dao.UserCredentialDAO;
import edu.srh.bikehire.dao.UserDAO;
import edu.srh.bikehire.dto.UserAccountDTO;
import edu.srh.bikehire.dto.UserCredentialDTO;
import edu.srh.bikehire.dto.UserDTO;
import edu.srh.bikehire.dto.impl.UserAccountDTOImpl;
import edu.srh.bikehire.dto.impl.UserCredentialDTOImpl;
import edu.srh.bikehire.dto.impl.UserDTOImpl;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.exception.util.ExceptionUtil;
import edu.srh.bikehire.login.LoginConstants;
import edu.srh.bikehire.login.ResetPasswordValidator;
import edu.srh.bikehire.login.core.CustomerCredentialValidator;
import edu.srh.bikehire.login.core.UserDetailsValidator;
import edu.srh.bikehire.login.core.UserRegistrationCredentialValidator;
import edu.srh.bikehire.login.impl.DBBasedResetPasswordValidator;
import edu.srh.bikehire.login.util.PasswordHashGenerator;
import edu.srh.bikehire.service.Login;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.EntityAccount;
import edu.srh.bikehire.service.core.EntityLoginCredential;
import edu.srh.bikehire.service.core.EntityRegistrationCredential;
import edu.srh.bikehire.service.core.impl.Customer;
import edu.srh.bikehire.service.core.impl.CustomerAccount;
import edu.srh.bikehire.util.Util;

public class DBBasedLoginService implements Login {
	
	private static final Logger LOG = LogManager.getLogger(DBBasedLoginService.class);
	
	private UserDAO mUserDAO;
	
	private UserAccountDAO mUserAccountDAO;
	
	private UserCredentialDAO mUserCredentialDAO;
	
	private DAOFactory daoFactory;
	
	public DBBasedLoginService()
	{
		daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.JPADAOFACTORY);
		mUserDAO = daoFactory.getUserDAO();
		mUserAccountDAO = daoFactory.getUserAccountDAO();
		mUserCredentialDAO = daoFactory.getUserCredentialDAO();
	}
	
	
	public Entity authenticate(EntityLoginCredential pInputEntityCredentials) throws BikeHireSystemException {
		LOG.info("authenticate : Start");
		try
		{			
			CustomerCredentialValidator lLoginCredentialValidator = new CustomerCredentialValidator(pInputEntityCredentials);
			int userId = lLoginCredentialValidator.validateLoginCredentials(mUserCredentialDAO);
			LOG.info("authenticate : password validated successfully.");
			
			UserDTO lLoggedInUser = validateAndGetUserDTO(userId);
			UserAccountDTO lLoggedInUserAccountDTO = validateAndGetUserAccountDTO(userId);
			
			Entity lLoggedInEntity = getEntityFromUserDTO(lLoggedInUser, lLoggedInUserAccountDTO);
			LOG.info("authenticate : End");
			return lLoggedInEntity;
		}
		catch(Throwable throwable)
		{
			LOG.error("authenticate : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public ResetPasswordValidator sendSecurityTokenForResetPassword(String pEmailAddress) throws BikeHireSystemException {
		LOG.info("sendSecurityTokenForResetPassword : Start");
		try
		{			
			UserDTO lUserDTO = validateAndGetUserDTOForEmailId(pEmailAddress);
			
			DBBasedResetPasswordValidator lResetPasswordValidator = new DBBasedResetPasswordValidator(lUserDTO.getId(),pEmailAddress, false);
			lResetPasswordValidator.generateToken();
			
			lResetPasswordValidator.sendNotfificationForSecurityCode();
			LOG.info("sendSecurityTokenForResetPassword : Security code email sent successfully.");
			
			LOG.info("sendSecurityTokenForResetPassword : End");
			return lResetPasswordValidator;
		}
		catch(Throwable throwable)
		{
			LOG.error("sendSecurityTokenForResetPassword : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public ResetPasswordValidator registerUserAccount(Entity pEntity, EntityRegistrationCredential pEntityCredential) throws BikeHireSystemException {
		LOG.info("registerUserAccount : Start");
		
		try
		{
			daoFactory.beginTransaction();
			validateUserInfoForRegistration(pEntity);
			LOG.info("registerUserAccount : No user exists with email address. Proceed to user creation.");
			
			validateUserCredentialsForRegistration(pEntityCredential);
			
			int userId = insertRegistrationInfo(pEntity, pEntityCredential);
			
			DBBasedResetPasswordValidator lResetPasswordValidator = new DBBasedResetPasswordValidator(userId, pEntity.getEmailId(), true);
			lResetPasswordValidator.generateToken();
			
			daoFactory.commitTransaction();
			
			//send email notification for security code
			lResetPasswordValidator.sendNotfificationForSecurityCode();
			
			LOG.info("registerUserAccount : Security code for email verification sent successfully.");
			LOG.info("registerUserAccount : End");
			return lResetPasswordValidator;
		}
		catch(Throwable throwable)
		{
			daoFactory.rollbackTransaction();
			LOG.error("registerUserAccount : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
		
	}
	
	public void resetPassword(EntityRegistrationCredential pEntityCredential) throws BikeHireSystemException
	{
		LOG.info("resetPassword : Start");
		try
		{	
			daoFactory.beginTransaction();
			UserAccountDTO lUserAccountDTO = validateAndGetAccountInfoForReset(pEntityCredential);
			UserDTO lUserDTO = mUserDAO.getUser(lUserAccountDTO.getId());
			
			UserCredentialDTO lUserCredentialDTO = getUserCredentialDTOFromInputs(pEntityCredential, lUserAccountDTO, lUserDTO);
			boolean lbCredentials = mUserCredentialDAO.updateUserCredential(lUserCredentialDTO);
			LOG.info("resetPassword : Password reset successfully.");
			
			daoFactory.commitTransaction();
			
			EmailNotificationService emailNotificationService = new EmailNotificationService();
			emailNotificationService.passwordResetSuccess(lUserDTO.getEmailId());
			LOG.info("resetPassword : password reset email sent.");
			LOG.info("resetPassword : End");
		}
		catch(Throwable throwable)
		{
			daoFactory.rollbackTransaction();
			LOG.error("resetPassword : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	

	public void deactivateAccount(Entity pEntity) throws BikeHireSystemException {
		LOG.info("deactivateAccount : Start");
		try
		{			
			daoFactory.beginTransaction();
			UserDetailsValidator lUserDetailsValidator = new UserDetailsValidator(pEntity);
			lUserDetailsValidator.validateUserInformationForDeactiviation();
			
			UserDTO lUserDTO = mUserDAO.getUserByEmailId(pEntity.getEmailId());
			UserAccountDTO lUserAccountDTO = getUserAccountForDeactivation(lUserDTO);
			boolean lbResult = mUserAccountDAO.updateUserAccount(lUserAccountDTO);
			daoFactory.commitTransaction();
			
			LOG.info("deactivateAccount : User account deactivated successfully.");
			LOG.info("deactivateAccount : End");
		}
		catch(Throwable throwable)
		{
			daoFactory.rollbackTransaction();
			LOG.error("deactivateAccount : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	public EntityAccount getAccountInfo(int pUserId) throws BikeHireSystemException{
		LOG.info("getAccountInfo : Start");
		try
		{			
			UserAccountDTO lUserAccountDTO = mUserAccountDAO.getUserAccount(pUserId);
			LOG.info("getAccountInfo : End");
			return getEntityAccountFromDTO(lUserAccountDTO);
		}
		catch(Throwable throwable)
		{
			LOG.error("getAccountInfo : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public boolean markUserAccountAsActive(int pUserId) throws BikeHireSystemException
	{
		LOG.info("markUserAccountAsActive : Start");
		try
		{		
			daoFactory.beginTransaction();
			UserAccountDTOImpl userAccountDTOImpl = (UserAccountDTOImpl) mUserAccountDAO.getUserAccount(pUserId);
			userAccountDTOImpl.setAccountStatus(LoginConstants.LOGIN_ACCOUNT_STATUS_ACTIVE);
			boolean isSuccess = mUserAccountDAO.updateUserAccount(userAccountDTOImpl);
			daoFactory.commitTransaction();
			LOG.info("markUserAccountAsActive : End");
			return isSuccess;
		}
		catch(Throwable throwable)
		{
			daoFactory.rollbackTransaction();
			LOG.error("markUserAccountAsActive : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	private Entity getEntityFromUserDTO(UserDTO pUserDTO, UserAccountDTO pUserAccountDTO)
	{
		Customer lCustomer = new Customer();
		lCustomer.setAddress(pUserDTO.getAddress());
		lCustomer.setEmailID(pUserDTO.getEmailId());
		lCustomer.setFirstName(pUserDTO.getFirstName());
		lCustomer.setGender(pUserDTO.getGender());
		lCustomer.setLastName(pUserDTO.getLastName());
		lCustomer.setUserId(pUserDTO.getId());
		lCustomer.setDOB(pUserDTO.getDob());
		lCustomer.setIdentityProofBytes(pUserDTO.getIdentityProof());
		lCustomer.setPhoneNumber(pUserDTO.getPhoneNo());
		lCustomer.setPhotoBytes(pUserDTO.getPhoto());
		
		CustomerAccount lCustomerAccount = new CustomerAccount();
		lCustomerAccount.setUserId(pUserAccountDTO.getId());
		lCustomerAccount.setUserName(pUserAccountDTO.getUserName());
		lCustomerAccount.setUserRole(pUserAccountDTO.getRole());
		lCustomerAccount.setAccountStatus(pUserAccountDTO.getAccountStatus());
		
		lCustomer.setEntityAccount(lCustomerAccount);
		
		return lCustomer;
	}
	
	private UserDTO getUserDTOFromInputs(Entity pEntity)
	{
		UserDTOImpl lUserDTO = new UserDTOImpl();
		lUserDTO.setAddress(pEntity.getAddress());
		lUserDTO.setDob(pEntity.getDOB());
		lUserDTO.setEmailId(pEntity.getEmailId());
		lUserDTO.setFirstName(pEntity.getFirstName());
		lUserDTO.setGender(pEntity.getGender());
		lUserDTO.setIdentityProof(pEntity.getIdentityProof());
		lUserDTO.setLastName(pEntity.getLastName());
		lUserDTO.setPhoneNo(pEntity.getPhoneNumber());
		lUserDTO.setPhoto(pEntity.getPhoto());
		
		return lUserDTO;
	}
	
	private UserAccountDTO getUserAccountDTOFromInputs(EntityAccount pEntityAccount, UserDTO pUserDTO)
	{
		UserAccountDTOImpl lUserAccountDTOImpl = new UserAccountDTOImpl();
		lUserAccountDTOImpl.setAccountStatus(LoginConstants.LOGIN_ACCOUNT_STATUS_UNVERIFIED);
		lUserAccountDTOImpl.setCreationTimeStamp(Calendar.getInstance());
		lUserAccountDTOImpl.setLastModifiedTimeStamp(Calendar.getInstance());
		lUserAccountDTOImpl.setRole(pEntityAccount.getUserRole());
		lUserAccountDTOImpl.setUserName(pEntityAccount.getUserName());
		lUserAccountDTOImpl.setUserDTO(pUserDTO);
		
		return lUserAccountDTOImpl;
	}
	
	private EntityAccount getEntityAccountFromDTO(UserAccountDTO userAccountDTO)
	{
		CustomerAccount lCustomerAccount = new CustomerAccount();
		lCustomerAccount.setAccountStatus(userAccountDTO.getAccountStatus());
		lCustomerAccount.setUserId(userAccountDTO.getId());
		lCustomerAccount.setUserName(userAccountDTO.getUserName());
		lCustomerAccount.setUserRole(userAccountDTO.getRole());
		return lCustomerAccount;
	}
	
	private UserCredentialDTO getUserCredentialDTOFromInputs(EntityRegistrationCredential pCredentials, UserAccountDTO pUserAccountDTO, UserDTO pUserDTO) throws BikeHireSystemException
	{
		UserCredentialDTOImpl lUserCredentialDTO = new UserCredentialDTOImpl();
		lUserCredentialDTO.setLastModifiedTimeStamp(Calendar.getInstance());
		lUserCredentialDTO.setUserAccount((UserAccountDTOImpl)pUserAccountDTO);
		
		String lstrSalt = PasswordHashGenerator.generateSalt();
		lUserCredentialDTO.setPasswordSalt(lstrSalt);
		lUserCredentialDTO.setPasswordHash(PasswordHashGenerator.getSHA512Hash(pCredentials.getNewPassword(), lstrSalt));
		
		lUserCredentialDTO.setUserDTO(pUserDTO);
		
		return lUserCredentialDTO;
	}
	
	private UserAccountDTO getUserAccountForDeactivation(UserDTO pUserDTO)
	{
		UserAccountDTOImpl lUserAccountDTOImpl = new UserAccountDTOImpl();
		lUserAccountDTOImpl.setAccountStatus(LoginConstants.LOGIN_ACCOUNT_STATUS_INACTIVE);
		lUserAccountDTOImpl.setUserDTO(pUserDTO);
		lUserAccountDTOImpl.setLastModifiedTimeStamp(Calendar.getInstance());
		return lUserAccountDTOImpl;
	}
	
	private UserDTO validateAndGetUserDTO(int pUserId) throws BikeHireSystemException
	{
		UserDTO lLoggedInUser = mUserDAO.getUser(pUserId);
		
		if(lLoggedInUser == null)
		{
			//ERROR_MESSAGE : Invalid login credentials provided. Username doesn't exists.
			throw new BikeHireSystemException(10024);
		}
		return lLoggedInUser;
	}
	
	private UserDTO validateAndGetUserDTOForEmailId(String pEmailAddress) throws BikeHireSystemException
	{
		if(Util.isEmptyOrNullString(pEmailAddress))
		{
			//ERROR_MESSAGE : Invalid email address provided for reset password.
			throw new BikeHireSystemException(10025);
		}
		
		UserDTO lUserDTO = mUserDAO.getUserByEmailId(pEmailAddress);
		
		if(lUserDTO == null)
		{
			//ERROR_MESSAGE : Invalid email address {0} provided for reset password. 
			throw new BikeHireSystemException(10026, new Object[] {pEmailAddress});
		}
		
		return lUserDTO;
	}
	
	private UserAccountDTO validateAndGetUserAccountDTO(int pUserId) throws BikeHireSystemException
	{
		UserAccountDTO lLoggedInUserAccountDTO = mUserAccountDAO.getUserAccount(pUserId);
		
		if(lLoggedInUserAccountDTO == null)
		{
			//ERROR_MESSAGE : Invalid login credentials provided. Username doesn't exists.
			throw new BikeHireSystemException(10024);
		}
		
		return lLoggedInUserAccountDTO;
	}
	
	private void validateUserInfoForRegistration(Entity pEntity) throws BikeHireSystemException
	{
		UserDetailsValidator lUserDetailsValidator = new UserDetailsValidator(pEntity);
		lUserDetailsValidator.validateUserInformationForRegistartion();
		
		UserDTO lUserDTO = mUserDAO.getUserByEmailId(pEntity.getEmailId());
		
		if(lUserDTO != null)
		{
			//ERROR_MESSAGE : User with email address {0} already exists. 
			throw new BikeHireSystemException(10027, new Object[] {pEntity.getEmailId()});
		}
	}
	
	private void validateUserCredentialsForRegistration(EntityRegistrationCredential pEntityCredential) throws BikeHireSystemException
	{
		UserRegistrationCredentialValidator lCredentialValidator = new UserRegistrationCredentialValidator(pEntityCredential);
		lCredentialValidator.validateEntityCredentials();
		
		UserAccountDTO lUserAccountDTO = mUserAccountDAO.getUserAccountUsingUserName(pEntityCredential.getUserName());
		
		if(lUserAccountDTO != null)
		{
			//ERROR_MESSAGE : User with username {0} already exists. 
			throw new BikeHireSystemException(10028, new Object[] {pEntityCredential.getUserName()});
		}
	}
	
	private int insertRegistrationInfo(Entity pEntity, EntityRegistrationCredential pEntityCredential) throws BikeHireSystemException
	{
		//Insert information using mUserDAO
		UserDTO lNewUserDTO = getUserDTOFromInputs(pEntity);
		int liUserId = mUserDAO.addUser(lNewUserDTO);
		LOG.info("insertRegistrationInfo : User added successfully.");
		//Insert information using mUserAccounrDAO
		UserAccountDTO lNewUserAccountDTO = getUserAccountDTOFromInputs(pEntity.getEntityAccount(), lNewUserDTO);
		boolean lbIsAdded = mUserAccountDAO.addUserAccount(lNewUserAccountDTO);
		LOG.info("insertRegistrationInfo : User account details created successfully.");
		
		//Insert information using mUserCredentialDAO
		UserCredentialDTO lNewUserCredentials = getUserCredentialDTOFromInputs(pEntityCredential, lNewUserAccountDTO, lNewUserDTO);
		boolean lbIsCredentialAdded = mUserCredentialDAO.addUserCredential(lNewUserCredentials);
		LOG.info("insertRegistrationInfo : User credentials added successfully.");
		
		return liUserId;
	}
	
	private UserAccountDTO validateAndGetAccountInfoForReset(EntityRegistrationCredential pEntityCredential) throws BikeHireSystemException
	{
		UserRegistrationCredentialValidator lCredentialValidator = new UserRegistrationCredentialValidator(pEntityCredential);
		lCredentialValidator.validateEntityCredentials();
		
		UserAccountDTO lUserAccountDTO = mUserAccountDAO.getUserAccountUsingUserName(pEntityCredential.getUserName());
		if(lUserAccountDTO == null)
		{
			//ERRORMESSAGE: User Account Data Transfer Object Not Found.
			throw new BikeHireSystemException(10085);
		}
		return lUserAccountDTO;
	}
}
