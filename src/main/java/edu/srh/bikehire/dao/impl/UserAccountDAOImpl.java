package edu.srh.bikehire.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.UserAccountDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.UserAccountDTO;
import edu.srh.bikehire.dto.impl.UserAccountDTOImpl;
import edu.srh.bikehire.dto.impl.UserDTOImpl;

public class UserAccountDAOImpl implements UserAccountDAO{
	private static final Logger LOG = LogManager.getLogger(UserAccountDAOImpl.class);
	
	private EntityManager em;
	
	public UserAccountDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public UserAccountDTOImpl getUserAccount(int pUserId) {
		LOG.debug("getUserAccount : Start");
		Query lQuery = em.createQuery("from UserAccountDTOImpl where ID = :typeId ");
		lQuery.setParameter("typeId", pUserId);
		List<UserAccountDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getUserAccount : End");
			return null;
		}
		LOG.debug("getUserAccount : End");
		return results.get(0);
	}

	public UserAccountDTOImpl getUserAccountUsingUserName(String pUserName)
	{
		LOG.debug("getUserAccountUsingUserName : Start");
		Query lQuery = em.createQuery("from UserAccountDTOImpl where UserName = :typeId ");
		lQuery.setParameter("typeId", pUserName);
		List<UserAccountDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getUserAccountUsingUserName : End");
			return null;
		}
		LOG.debug("getUserAccountUsingUserName : End");
		return results.get(0);
	}
	
	public boolean addUserAccount(UserAccountDTO pUserAccountDTO) {
		LOG.debug("addUserAccount : Start");
		em.persist(pUserAccountDTO);
		LOG.info("addUserAccount : new user account added successfully.");
		LOG.debug("addUserAccount : End");
		return true;
	}

	public boolean updateUserAccount(UserAccountDTO pUserAccountDTO) {
		LOG.debug("updateUserAccount : Start");
		Query lQuery = em.createQuery("UPDATE UserAccountDTOImpl uad SET uad.accountStatus = :as, uad.lastModifiedTimeStamp= :lm where uad.userDTO = :identity ");
		lQuery.setParameter("as", pUserAccountDTO.getAccountStatus());
		lQuery.setParameter("lm", Calendar.getInstance());
		UserDTOImpl lUserDTO = new UserDTOImpl();
		lUserDTO.setId(pUserAccountDTO.getId());
		lQuery.setParameter("identity", lUserDTO);
		int rowsUpdated = lQuery.executeUpdate();
		if(rowsUpdated <= 0)
		{
			LOG.info("updateUserCredential : failed to update user account.");
			LOG.debug("updateUserAccount : End");
			return false;
		}
		LOG.info("updateUserCredential : user account updated successfully.");
		LOG.debug("updateUserAccount : End");
		return true;
	}

}
