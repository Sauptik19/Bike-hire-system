package edu.srh.bikehire.service.core.impl;

import java.util.Calendar;

import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.EntityAccount;

public class Customer implements Entity {
	
	//MEMBERS
	
	private int miUserId;
	
	private String mstrFirstName;
	
	private String mstrLastName;
	
	private String mstrGender;
	
	private String mstrAddress;
	
	private Calendar mDOB;
	
	private String mstrEmailID;
	
	private String mstrPhoneNumber;
	
	private EntityAccount mEntityAccount;
	
	private byte[] mPhotoBytes;
	
	private byte[] mIdentityProofBytes;
	
	//GETTER
	public int getUserId() {
		return miUserId;
	}

	public String getFirstName() {
		return mstrFirstName;
	}

	public String getLastName() {
		return mstrLastName;
	}

	public String getGender() {
		return mstrGender;
	}

	public String getAddress() {
		return mstrAddress;
	}

	public Calendar getDOB() {
		return mDOB;
	}

	public String getEmailId() {
		return mstrEmailID;
	}

	public String getPhoneNumber() {
		return mstrPhoneNumber;
	}

	public EntityAccount getEntityAccount() {
		return mEntityAccount;
	}

	public byte[] getPhoto() {
		return mPhotoBytes;
	}

	public byte[] getIdentityProof() {
		return mIdentityProofBytes;
	}

	public void setUserId(int userId) {
		miUserId = userId;
	}

	public void setFirstName(String firstName) {
		mstrFirstName = firstName;
	}

	public void setLastName(String lastName) {
		mstrLastName = lastName;
	}

	public void setGender(String gender) {
		mstrGender = gender;
	}

	public void setAddress(String address) {
		mstrAddress = address;
	}

	public void setDOB(Calendar mDOB) {
		this.mDOB = mDOB;
	}

	public void setEmailID(String emailID) {
		mstrEmailID = emailID;
	}

	public void setPhoneNumber(String phoneNumber) {
		mstrPhoneNumber = phoneNumber;
	}

	public void setEntityAccount(EntityAccount mEntityAccount) {
		this.mEntityAccount = mEntityAccount;
	}

	public void setPhotoBytes(byte[] mPhotoBytes) {
		this.mPhotoBytes = mPhotoBytes;
	}

	public void setIdentityProofBytes(byte[] mIdentityProofBytes) {
		this.mIdentityProofBytes = mIdentityProofBytes;
	}
}
