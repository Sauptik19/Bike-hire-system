package edu.srh.bikehire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.BikeTypeDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.dto.impl.BikeTypeDTOImpl;
import edu.srh.bikehire.util.Util;

public class BikeTypeDAOImpl implements BikeTypeDAO {
	private static final Logger LOG = LogManager.getLogger(BikeTypeDAOImpl.class);
	private EntityManager em;
	
	public BikeTypeDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public BikeTypeDTOImpl getBikeType(int pBikeType)
	{
		LOG.debug("getBikeType : Start");
		Query lQuery = em.createQuery("from BikeTypeDTOImpl where BikeTypeId = :typeId ");
		lQuery.setParameter("typeId", pBikeType);
		
		List<BikeTypeDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getBikeType : End");
			return null;
		}
		LOG.debug("getBikeType : End");
		return results.get(0);
	}
	
	public List<BikeTypeDTO> getBikeTypes() {
		LOG.debug("getBikeTypes : Start");
		Query lQuery = em.createQuery("SELECT a FROM BikeTypeDTOImpl a");
		
		List<BikeTypeDTO> results = lQuery.getResultList();
		LOG.debug("getBikeTypes : End");
		return results;
	}

	public int saveBikeType(BikeTypeDTO bikeType) {
		LOG.debug("saveBikeType : Start");
		em.persist(bikeType);
		LOG.info("saveBikeType : new bike type added successfully");
		LOG.debug("saveBikeType : End");
		return bikeType.getBikeTypeId();
	}

}
