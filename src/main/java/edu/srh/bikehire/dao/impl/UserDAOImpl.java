package edu.srh.bikehire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.UserDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.UserDTO;
import edu.srh.bikehire.dto.impl.UserDTOImpl;

public class UserDAOImpl implements UserDAO {
	private static final Logger LOG = LogManager.getLogger(UserDAOImpl.class);
	private EntityManager em;
	
	public UserDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public UserDTOImpl getUser(int pUserId) {
		LOG.debug("getUser : Start");
		Query lQuery = em.createQuery("from UserDTOImpl where ID = :typeId ");
		lQuery.setParameter("typeId", pUserId);
		List<UserDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getUser : End");
			return null;
		}
		LOG.debug("getUser : End");
		return results.get(0);
	}

	public UserDTO getUserByEmailId(String pEmailId) {
		LOG.debug("getUserByEmailId : Start");
		Query lQuery = em.createQuery("from UserDTOImpl where EmailId = :typeId ");
		lQuery.setParameter("typeId", pEmailId);
		List<UserDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getUserByEmailId : End");
			return null;
		}
		LOG.debug("getUserByEmailId : End");
		return results.get(0);
	}
	
	public int addUser(UserDTO pNewUser) {
		LOG.debug("addUser : Start");
		em.persist(pNewUser);
		LOG.info("addUser : new user added successfully.");
		LOG.debug("addUser : End");
		return pNewUser.getId();
	}

	public boolean updateUser(UserDTO pUser) {
		LOG.debug("updateUser : Start");
		UserDTOImpl lUserDTOImpl = getUser(pUser.getId());
		Query lQuery = em.createQuery("UPDATE UserDTOImpl ud SET ud.address = :add, ud.firstName = :fn, ud.lastName = :ln, ud.phoneNo = :pn where ud.id = :identity");
		
		if(pUser.getAddress() != null)
		{
			lUserDTOImpl.setAddress(pUser.getAddress());
		}
		
		if(pUser.getFirstName() != null)
		{
			lUserDTOImpl.setFirstName(pUser.getFirstName());
		}
		
		if(pUser.getLastName() != null)
		{
			lUserDTOImpl.setLastName(pUser.getLastName());
		}
		
		if(pUser.getPhoneNo() != null)
		{
			lUserDTOImpl.setPhoneNo(pUser.getPhoneNo());
		}
		lQuery.setParameter("add", lUserDTOImpl.getAddress());
		lQuery.setParameter("fn", lUserDTOImpl.getFirstName());
		lQuery.setParameter("ln", lUserDTOImpl.getLastName());
		lQuery.setParameter("pn", lUserDTOImpl.getPhoneNo());
		lQuery.setParameter("identity", lUserDTOImpl.getId());
		int rowsUpdated = lQuery.executeUpdate();
		if(rowsUpdated <= 0)
		{
			LOG.info("updateUser : failed to update user.");
			LOG.debug("updateUser : End");
			return false;
		}
		LOG.info("updateUser : user updated successfully.");
		LOG.debug("updateUser : End");
		return true;
	}

}
