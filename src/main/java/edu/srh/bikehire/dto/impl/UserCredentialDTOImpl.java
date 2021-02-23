package edu.srh.bikehire.dto.impl;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.srh.bikehire.dto.UserCredentialDTO;
import edu.srh.bikehire.dto.UserDTO;

@Entity
@Table(name="Credentials")
public class UserCredentialDTOImpl implements UserCredentialDTO, Serializable {

	@Id
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "UserID")
	private UserDTOImpl userDTO;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "UserName")
	private UserAccountDTOImpl userAccount;
	
	@Column(name = "PasswordSalt")
	private String passwordSalt;
	
	@Column(name = "PasswordHash")
	private String passwordHash;
	
	@Column(name = "LastModifiedTimeStamp")
	private Calendar lastModifiedTimeStamp;
	
	public int getUserID() {
		return userDTO.getId();
	}
	
	public String getUserName() {
		return userAccount.getUserName();
	}
	
	public String getPasswordSalt() {
		return passwordSalt;
	}
	
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public Calendar getLastModifiedTimeStamp() {
		return lastModifiedTimeStamp;
	}
	
	public void setLastModifiedTimeStamp(Calendar lastModifiedTimeStamp) {
		this.lastModifiedTimeStamp = lastModifiedTimeStamp;
	}

	public UserDTOImpl getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTOImpl userDTO) {
		this.userDTO = userDTO;
	}

	public UserAccountDTOImpl getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccountDTOImpl userAccount) {
		this.userAccount = userAccount;
	}
	
	public void setUserDTO(UserDTO pUserDTO) {
		this.userDTO = (UserDTOImpl) pUserDTO;
	}
}
