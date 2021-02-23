package edu.srh.bikehire.service.core.impl;

import java.util.Calendar;

import edu.srh.bikehire.service.core.EntityAccount;

public class CustomerAccount implements EntityAccount {
	//MEMBERS
	
	private int miUserId;
	
	private String mstrUserName;
	
	private String mstrUserRole;
	
	private String mstrAccountStatus;
	
	//GETTER
	
	public int getUserId() {
		return miUserId;
	}

	public String getUserName() {
		return mstrUserName;
	}

	public String getUserRole() {
		return mstrUserRole;
	}

	public String getAccountStatus() {
		return mstrAccountStatus;
	}

	public void setUserId(int userId) {
		miUserId = userId;
	}

	public void setUserName(String userName) {
		mstrUserName = userName;
	}

	public void setUserRole(String userRole) {
		mstrUserRole = userRole;
	}

	public void setAccountStatus(String accountStatus) {
		mstrAccountStatus = accountStatus;
	}
}
