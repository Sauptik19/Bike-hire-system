package edu.srh.bikehire.console;

import java.util.Scanner;

import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.login.LoginConstants;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.EntityAccount;
import edu.srh.bikehire.service.impl.DBBasedLoginService;
import edu.srh.bikehire.startup.AppInitializer;

public class HomePage {
	private static Scanner sc = null;
	private static AppInitializer initializer = null; 
	public void display_menu() {
		try {
			String leftAlignFormat = "| %-4d | %-19s |%n";

			System.out.format("+------+---------------------+%n");
			System.out.format("| ID   | Task Name           |%n");
			System.out.format("+------+---------------------+%n");
			System.out.format(leftAlignFormat, 1, "Register");
			System.out.format(leftAlignFormat, 2, "Login");
			System.out.format(leftAlignFormat, 3, "Forgot Password?");
			System.out.format(leftAlignFormat, 4, "Exit");
			System.out.format("+------+---------------------+%n");
			//System.out.println("1) Register \n2) Login \n 3) Forgot Password? \n");
			System.out.println("Select option: ");
			
			int option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
				int returnValueRU = this.callRegistrationUI();
				if(returnValueRU <= 0)
				{
					this.display_menu();
				}
				break;
			case 2:
				Entity loggedInUser = this.callLoginUI();
				if(loggedInUser == null)
				{
					System.out.println("Invalid username/password. Please try again with valid credentials.");
					this.display_menu();
					return;
				}
				else {
					int returnValue = this.processLoggedInUser(loggedInUser);
					if(returnValue < 0)
					{
						System.out.println("You have successfully logged out of the system.");
					}
					this.display_menu();
					return;
				}
			case 3:
				
				this.callForgotPasswordUI();
				break;
			case 4:
				this.terminateApplication();
				break;
			default:
				//ERROR MESSAGE: Invalid option is selected
				throw new BikeHireSystemException(10062);
			}
		}catch (BikeHireSystemException exception) {
			System.out.println(exception.getDisplayMessage());
			this.display_menu();
			return;
		} 
		catch (Exception exception) {
			System.out.println(exception.getMessage());
			this.display_menu();
			return;
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		
	}

	public static void main(String[] args){
		HomePage homepage = null;
		try {
			AppInitializer initializerTemp = new AppInitializer();
			initializerTemp.initializeApplication();
			initializer = initializerTemp;
			homepage = new HomePage();
			homepage.initializeScanner();
			homepage.display_menu();
		} catch (BikeHireSystemException e) {
			System.out.println(e.getDisplayMessage());
			//e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			homepage.closeScanner();
		}
	}

	private int callRegistrationUI() throws BikeHireSystemException {
		RegistrationUI registrationUI = new RegistrationUI();
		return registrationUI.register(sc);
	}

	private Entity callLoginUI() {
		LoginUI loginUI = new LoginUI();
		return loginUI.login(sc);
	}

	private void callForgotPasswordUI() throws BikeHireSystemException {
		ForgotPasswordUI forgotPasswordUI = new ForgotPasswordUI();
		forgotPasswordUI.startFPProcess(sc);
	}

	private int processLoggedInUser(Entity entity) throws BikeHireSystemException
	{
		DBBasedLoginService lDbBasedLoginService = new DBBasedLoginService();
		EntityAccount userAccount = lDbBasedLoginService.getAccountInfo(entity.getUserId());
		if(LoginConstants.LOGIN_ACCOUNT_STATUS_INACTIVE.equals(userAccount.getAccountStatus()))
		{
			//ERROR MESSAGE: Inactive Account User is trying to login.
			throw new BikeHireSystemException(10064);
		}
		
		if(LoginConstants.LOGIN_ACCOUNT_STATUS_UNVERIFIED.equals(userAccount.getAccountStatus()))
		{
			System.out.println("Please verify your account!");
			return -1;
		}
		
		if(LoginConstants.LOGIN_ACCOUNT_ROLE_CUSTOMER.equals(userAccount.getUserRole()))
		{
			LandingUIForCustomer landingUIForCustomer = new LandingUIForCustomer(entity);
			return landingUIForCustomer.showMenu(sc);
		}
		else
		{
			LandingUIForStaff landingUIForStaff = new LandingUIForStaff(entity);
			return landingUIForStaff.showMenu(sc);
		}
	}
	
	private void terminateApplication()
	{
		try {
			initializer.terminateApplication();
		} catch (InterruptedException e) {
			System.out.println("Some error occured while terminating application.");
		}
		PersistenceManager.INSTANCE.close();
		System.out.println("Successfully terminated application.");
	}
	
	private void initializeScanner()
	{
		sc = new Scanner(System.in);
	}
	
	private void closeScanner()
	{
		if (sc != null) {
			sc.close();
		}
	}
}
