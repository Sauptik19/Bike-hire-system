package edu.srh.bikehire.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.BikeRentMappingDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.dto.impl.BikeDTOImpl;
import edu.srh.bikehire.dto.impl.BikeRentMappingDTOImpl;
import edu.srh.bikehire.dto.impl.BikeTypeDTOImpl;

public class BikeRentMappingDAOImpl implements BikeRentMappingDAO {
	private static final Logger LOG = LogManager.getLogger(BikeRentMappingDAOImpl.class);
	private EntityManager em;
	
	public BikeRentMappingDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public BikeRentMappingDTOImpl getBikeRentMapping(int pBikeTypeId) {
		LOG.debug("getBikeRentMapping : Start");
		
		Query lQuery = em.createQuery("from BikeRentMappingDTOImpl where BikeTypeId = :typeId ");
		lQuery.setParameter("typeId", pBikeTypeId);
		
		List<BikeRentMappingDTOImpl> lBikeRentMappings = lQuery.getResultList();
		if(lBikeRentMappings.size() == 0)
		{
			LOG.debug("getBikeRentMapping : End");
			return null;
		}
		LOG.debug("getBikeRentMapping : End");
		return lBikeRentMappings.get(0);
	}

	public boolean addBikeRentMapping(BikeRentMappingDTO pBikeRentMappingDTO) {
		LOG.debug("addBikeRentMapping : Start");
		em.persist(pBikeRentMappingDTO);
		LOG.info("addBikeRentMapping : new bike rent mapping added successfully.");
		LOG.debug("addBikeRentMapping : End");
		return true;
	}

	public boolean updateBikeRentMapping(BikeRentMappingDTO pBikeRentMappingDTO) {
		LOG.debug("updateBikeRentMapping : Start");
		
		BikeRentMappingDTOImpl lBikeRentMapping = getBikeRentMapping(pBikeRentMappingDTO.getBikeTypeId());
		if(pBikeRentMappingDTO.getRentPerDay() != 0)
		{
			lBikeRentMapping.setRentPerDay(pBikeRentMappingDTO.getRentPerDay());
		}
		
		if(pBikeRentMappingDTO.getRentPerHour() != 0)
		{
			lBikeRentMapping.setRentPerHour(pBikeRentMappingDTO.getRentPerHour());
		}
		
		Query lQuery = em.createQuery("UPDATE BikeRentMappingDTOImpl brm SET brm.rentPerHour = :rph, brm.rentPerDay = :rpd, brm.lastModifiedTimeStamp = :lm where brm.bikeType = :bikeTypeId");
		lQuery.setParameter("rph", lBikeRentMapping.getRentPerHour());
		lQuery.setParameter("rpd", lBikeRentMapping.getRentPerDay());
		lQuery.setParameter("lm", Calendar.getInstance());
		BikeTypeDTOImpl bikeTypeDTO = new BikeTypeDTOImpl();
		bikeTypeDTO.setBikeTypeId(pBikeRentMappingDTO.getBikeTypeId());
		lQuery.setParameter("bikeTypeId", bikeTypeDTO);
		int rowsUpdated = lQuery.executeUpdate();
		if(rowsUpdated <= 0)
		{
			LOG.info("updateBikeRentMapping : failed to update bike rent mapping.");
			LOG.debug("updateBikeRentMapping : End");
			return false;
		}
		LOG.info("updateBikeRentMapping : bike rent mapping updated successfully.");
		LOG.debug("updateBikeRentMapping : End");
		return true;
	}

}
