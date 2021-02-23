package edu.srh.bikehire.dto.impl;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.srh.bikehire.dto.UserDTO;

@Entity
@Table(name="User")
public class UserDTOImpl implements UserDTO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Dob")
	private Calendar dob;
	
	@Column(name = "Address")
	private String address;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "Photo")
	private byte[] photo;
	
	@Column(name = "EmailId")
	private String emailId;
	
	@Column(name = "PhoneNo")
	private String phoneNo;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "IdentityProof")
	private byte[] identityProof;
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGender() {
		return gender;
	}
	
	public String getAddress() {
		return address;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public String getEmailId() {
		return emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public byte[] getIdentityProof() {
		return identityProof;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setIdentityProof(byte[] identityProof) {
		this.identityProof = identityProof;
	}
	
	public Calendar getDob() {
		return dob;
	}
	
	public void setDob(Calendar dOB) {
		this.dob = dOB;
	}
}
