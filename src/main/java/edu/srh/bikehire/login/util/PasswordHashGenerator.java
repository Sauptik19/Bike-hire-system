package edu.srh.bikehire.login.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import edu.srh.bikehire.exception.BikeHireSystemException;

public class PasswordHashGenerator {
	public static boolean verifyPasswordHash(String pOriginalHash, String pAttemptedPassword, String pSalt) throws BikeHireSystemException
	{
		String lstrHashedPassword = getSHA512Hash(pAttemptedPassword, pSalt);
		return pOriginalHash.equals(lstrHashedPassword);
	}
	
	public static String getSHA512Hash(String pPassword, String pSalt) throws BikeHireSystemException
	{
		String lstrHashedPassword = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			byte[] lSaltBytes = pSalt.getBytes(StandardCharsets.UTF_8);
			md.update(lSaltBytes);
			byte[] bytes = md.digest(pPassword.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			lstrHashedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			//ERROR_MESSAGE : Unable to generate password hash.
			throw new BikeHireSystemException(10004, new Object[] {}, e);
		}
		return lstrHashedPassword;
	}
	
	public static String generateSalt()
	{
		SecureRandom lSecureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		lSecureRandom.nextBytes(salt);
		return new String(salt, StandardCharsets.UTF_8);
	}
}
