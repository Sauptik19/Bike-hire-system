package edu.srh.bikehire.console;

import java.io.Console;
import java.util.Scanner;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.impl.LoginCredential;
import edu.srh.bikehire.service.impl.DBBasedLoginService;

public class LoginUI {

	private String username ;
	
	private String password;
	
	public Entity login(Scanner in)
	{
		System.out.println("Login");
		
		try
		{			
			readInputs(in);
			
			//Call Login API using username and password
			LoginCredential credentials = new LoginCredential();
			credentials.setUserName(username);
			credentials.setPassword(password);
			DBBasedLoginService loginService = new DBBasedLoginService();
			System.out.println("Please wait...");
			Entity loggedInEntity = loginService.authenticate(credentials);
			
			if(loggedInEntity == null)
			{
				//ERROR MESSAGE: Invalid credentials are used for login.
				throw new BikeHireSystemException(10065);
			}
			return loggedInEntity;
		}
		catch(BikeHireSystemException exception)
		{
			System.out.println(exception.getDisplayMessage());
			return this.login(in);
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage());
			return this.login(in);
		}
	}
	
	private void readInputs(Scanner in)
	{
		System.out.println("Enter UserName : ");
		username=in.nextLine();
		String enterPwdMsg = "Enter password: ";
		Console cons = System.console();
		if(cons == null)
		{
			System.out.println(enterPwdMsg);
			password=in.nextLine();
		}
		else
		{			
			password=getPasswordMasked(cons, enterPwdMsg);
		}
	}
	
   public static String getPasswordMasked(Console cons, String msg)
   {
       char[] passwd;
       while (true) {
           passwd = cons.readPassword("%s", msg);
           if (passwd != null) {
               if (passwd.length > 0) {
                   String lpassword  = new String(passwd);
                   System.out.println(lpassword);
                   return lpassword;
               } else {
                   System.out.println("Invalid input\n");
               }
           }
       }
   }

}
