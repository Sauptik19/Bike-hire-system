package edu.srh.bikehire.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.UserCredentialDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.UserCredentialDTO;
import edu.srh.bikehire.dto.impl.UserCredentialDTOImpl;
import edu.srh.bikehire.dto.impl.UserDTOImpl;

public class UserCredentialDAOImpl implements UserCredentialDAO{
	private static final Logger LOG = LogManager.getLogger(UserCredentialDAOImpl.class);
	
	private EntityManager em;
	
	public UserCredentialDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public UserCredentialDTOImpl getUserCredentialByUserId(int pUserId) {
		LOG.debug("getUserCredentialByUserId : Start");
		Query lQuery = em.createQuery("from UserCredentialDTOImpl where UserID = :typeId ");
		lQuery.setParameter("typeId", pUserId);
		List<UserCredentialDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getUserCredentialByUserId : End");
			return null;
		}
		LOG.debug("getUserCredentialByUserId : End");
		return results.get(0);
	}

	public UserCredentialDTOImpl getUserCredentialByUserName(String pUserName) {
		LOG.debug("getUserCredentialByUserName : Start");
		Query lQuery = em.createQuery("from UserCredentialDTOImpl where UserName = :typeId ");
		lQuery.setParameter("typeId", pUserName);
		List<UserCredentialDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getUserCredentialByUserName : End");
			return null;
		}
		LOG.debug("getUserCredentialByUserName : End");
		return results.get(0);
	}

	public boolean addUserCredential(UserCredentialDTO pUserCredentialDTO) {
		LOG.debug("addUserCredential : Start");
		em.persist(pUserCredentialDTO);
		LOG.info("addUserCredential : new user credential added successfully.");
		LOG.debug("addUserCredential : End");
		return true;
	}

	public boolean updateUserCredential(UserCredentialDTO pUserCredentialDTO) {
		LOG.debug("updateUserCredential : Start");
		UserCredentialDTOImpl lUserCredentialDTOImpl = null;
		if(pUserCredentialDTO.getUserID() > 0)
		{			
			lUserCredentialDTOImpl = getUserCredentialByUserId(pUserCredentialDTO.getUserID());
		}
		else
		{
			lUserCredentialDTOImpl = getUserCredentialByUserName(pUserCredentialDTO.getUserName());
		}
		Query lQuery = em.createQuery("UPDATE UserCredentialDTOImpl ucd SET ucd.passwordHash = :ph, ucd.passwordSalt = :ps, ucd.lastModifiedTimeStamp = :lm where ucd.userDTO =:identity");
		lQuery.setParameter("ph", pUserCredentialDTO.getPasswordHash());
		lQuery.setParameter("ps", pUserCredentialDTO.getPasswordSalt());
		lQuery.setParameter("lm", Calendar.getInstance());
		UserDTOImpl userDTO = new UserDTOImpl();
		userDTO.setId(lUserCredentialDTOImpl.getUserID());
		lQuery.setParameter("identity",userDTO);
		int rowsUpdated = lQuery.executeUpdate();
		if(rowsUpdated <= 0)
		{
			LOG.info("updateUserCredential : failed to update user credentials.");
			LOG.debug("updateUserCredential : End");
			return false;
		}
		LOG.info("updateUserCredential : user credential updated successfully.");
		LOG.debug("updateUserCredential : End");
		return true;
	}

}
