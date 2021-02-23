package edu.srh.bikehire.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.login.ResetPasswordValidator;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.EntityAccount;
import edu.srh.bikehire.service.core.impl.Customer;
import edu.srh.bikehire.service.core.impl.CustomerAccount;
import edu.srh.bikehire.service.core.impl.CustomerRegistrationCredential;
import edu.srh.bikehire.service.core.impl.LoginCredential;
import edu.srh.bikehire.startup.AppInitializer;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBBasedLoginServiceTest {
	
	private int userId = 0;
	
	private AppInitializer appInit;
	
	@Before
	public void initializer()
	{
		appInit = new AppInitializer();
		try {
			appInit.initializeApplication();
		} catch (BikeHireSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testARegisterUserAccountInvalidFirstName() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName(null);
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testBRegisterUserAccountInvalidLastName() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName(null);
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testCRegisterUserAccountInvalidAddress() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress(null);
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testDRegisterUserAccountInvalidDOB() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(null);
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testERegisterUserAccountNullEmail() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID(null);
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testFRegisterUserAccountInvalidEmail() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("something");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testGRegisterUserAccountInvalidGender() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender(null);
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testHRegisterUserAccountInvalidID() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(null);
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testIRegisterUserAccountInvalidPhoto() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(null);
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testJRegisterUserAccountNullPhoneNumber() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber(null);
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testKRegisterUserAccountInvalidPhoneNumber() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("562622342424242");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testLRegisterUserAccountNullUserName() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName(null);
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName(null);
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	
	@Test(expected = BikeHireSystemException.class)
	public void testMRegisterUserAccountInvalidRole() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole(null);
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testNRegisterUserAccountNullAccountInfo() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(null);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testORegisterUserAccountNullNewPassword() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword(null);
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testPRegisterUserAccountNullConfirmPwd() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword(null);
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testQRegisterUserAccountInvalidPassword() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Sr2018");
		registrationValidator.setConfirmPassword("Sr2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testRRegisterUserAccountPasswordMismatch() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2009");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
	}
	
	@Test
	public void testSRegisterUserAccountSuccess() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setFirstName("viraj");
		customer.setLastName("patel");
		customer.setAddress("Germany");
		customer.setDOB(Calendar.getInstance());
		customer.setEmailID("somethingggg123@mailinator.com");
		customer.setGender("Male");
		customer.setIdentityProofBytes(new byte[] {23,35,5,53,34,12,2});
		customer.setPhotoBytes(new byte[] {23,12,45,23,7,3,7,23,78,23,5});
		customer.setPhoneNumber("+49 1517123456");
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setUserName("virajab-patel");
		customerAccount.setUserRole("Customer");
		
		customer.setEntityAccount(customerAccount);
		
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		
		ResetPasswordValidator resetPasswordValidator = dbBasedLoginService.registerUserAccount(customer, registrationValidator);
		assertNotNull(resetPasswordValidator);
		userId = resetPasswordValidator.getUserId();
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testTMarkUserAccountAsActiveInvalidId() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		dbBasedLoginService.markUserAccountAsActive(-1);
	}
	
	@Test
	public void testUMarkUserAccountAsActiveSuccess() throws BikeHireSystemException{
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		dbBasedLoginService.markUserAccountAsActive(1);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testVAuthenticateNullUserName() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		LoginCredential loginCredential = new LoginCredential();
		loginCredential.setUserName(null);
		loginCredential.setPassword("Srh@2018");
		
		Entity loggedInEntity = dbBasedLoginService.authenticate(loginCredential);
		assertNotNull(loggedInEntity);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testWAuthenticateNullPassword() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		LoginCredential loginCredential = new LoginCredential();
		loginCredential.setUserName("virajab-patel");
		loginCredential.setPassword(null);
		
		Entity loggedInEntity = dbBasedLoginService.authenticate(loginCredential);
		assertNotNull(loggedInEntity);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testXAuthenticateInvalidPassword() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		LoginCredential loginCredential = new LoginCredential();
		loginCredential.setUserName("virajab-patel");
		loginCredential.setPassword("Srh@20");
		
		Entity loggedInEntity = dbBasedLoginService.authenticate(loginCredential);
		assertNotNull(loggedInEntity);
	}
	
	@Test
	public void testYAuthenticateSuccess() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		LoginCredential loginCredential = new LoginCredential();
		loginCredential.setUserName("virajab-patel");
		loginCredential.setPassword("Srh@2018");
		
		Entity loggedInEntity = dbBasedLoginService.authenticate(loginCredential);
		assertNotNull(loggedInEntity);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testZSendSecurityTokenForResetPasswordNullEmail() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		ResetPasswordValidator passwordValidator = dbBasedLoginService.sendSecurityTokenForResetPassword(null);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testZASendSecurityTokenForResetPasswordInvalidEmail() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		ResetPasswordValidator passwordValidator = dbBasedLoginService.sendSecurityTokenForResetPassword("somethinginator.com");
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testZBSendSecurityTokenForResetPasswordDoesntExistEmail() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		ResetPasswordValidator passwordValidator = dbBasedLoginService.sendSecurityTokenForResetPassword("something1231@mailinator.com");
	}
	
	@Test
	public void testZCSendSecurityTokenForResetPasswordSuccess() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		ResetPasswordValidator passwordValidator = dbBasedLoginService.sendSecurityTokenForResetPassword("somethingggg123@mailinator.com");
	}

	@Test(expected = BikeHireSystemException.class)
	public void testZDResetPasswordNullUserName() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName(null);
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		dbBasedLoginService.resetPassword(registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testZEResetPasswordUsernameDoesntExist() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel123");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		dbBasedLoginService.resetPassword(registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testZFResetPasswordNullNewPassword() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword(null);
		registrationValidator.setConfirmPassword("Srh@2018");
		dbBasedLoginService.resetPassword(registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testZGResetPasswordNullConfirmPwd() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword(null);
		dbBasedLoginService.resetPassword(registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testZHResetPasswordInvalidPwd() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh018");
		registrationValidator.setConfirmPassword("Srh018");
		dbBasedLoginService.resetPassword(registrationValidator);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testZIResetPasswordPwdMismatch() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2099");
		registrationValidator.setConfirmPassword("Srh@2018");
		dbBasedLoginService.resetPassword(registrationValidator);
	}
	
	@Test
	public void testZJResetPasswordSuccess() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		CustomerRegistrationCredential registrationValidator = new CustomerRegistrationCredential();
		registrationValidator.setUserName("virajab-patel");
		registrationValidator.setNewPassword("Srh@2018");
		registrationValidator.setConfirmPassword("Srh@2018");
		dbBasedLoginService.resetPassword(registrationValidator);
	}

	@Test
	public void testZKGetAccountInfoInvalidId() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		EntityAccount accountInfo = dbBasedLoginService.getAccountInfo(-1);
		assertNull(accountInfo);
	}

	@Test
	public void testZLGetAccountInfoSuccess() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		EntityAccount accountInfo = dbBasedLoginService.getAccountInfo(userId);
		assertNotNull(accountInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testZMDeactivateAccountInvalidUserId() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setUserId(-1);
		dbBasedLoginService.deactivateAccount(customer);
	}
	
	@Test
	public void testZNDeactivateAccount() throws BikeHireSystemException {
		DBBasedLoginService dbBasedLoginService = new DBBasedLoginService();
		Customer customer = new Customer();
		customer.setEmailID("somethingggg123@mailinator.com");
		dbBasedLoginService.deactivateAccount(customer);
	}
	
	public void terminateApp()
	{
		if(appInit != null)
		{
			try {
				appInit.terminateApplication();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
