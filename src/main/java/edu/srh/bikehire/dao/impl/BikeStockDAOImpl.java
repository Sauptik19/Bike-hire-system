package edu.srh.bikehire.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.BikeStockDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.BikeStockDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.dto.impl.BikeStockDTOImpl;
import edu.srh.bikehire.dto.impl.BikeTypeDTOImpl;

public class BikeStockDAOImpl implements BikeStockDAO {
	private static final Logger LOG = LogManager.getLogger(BikeStockDAOImpl.class);
	private EntityManager em;
	
	public BikeStockDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public BikeStockDTOImpl getBikeStock(BikeTypeDTO bikeType) {
		LOG.debug("getBikeStock : Start");
		
		Query lQuery = em.createQuery("from BikeStockDTOImpl where BikeTypeId = :typeId ");
		lQuery.setParameter("typeId", bikeType.getBikeTypeId());
		
		List<BikeStockDTOImpl> lBikeStocks = lQuery.getResultList();
		if(lBikeStocks.size() == 0)
		{
			LOG.debug("getBikeStock : End");
			return null;
		}
		LOG.debug("getBikeStock : End");
		return lBikeStocks.get(0);
	}

	public boolean addBikeStock(BikeStockDTO bikeStock) {
		LOG.debug("addBikeStock : Start");
		em.persist(bikeStock);
		LOG.info("addBikeStock : new bike stock added successfully.");
		LOG.debug("addBikeStock : End");
		return false;
	}

	public boolean updateBikeStock(BikeStockDTO bikeStock, BikeTypeDTO bikeType) {
		LOG.debug("updateBikeStock : Start");
		
		BikeStockDTOImpl lBikeStock = getBikeStock(bikeType);
		Query lQuery = em.createQuery("UPDATE BikeStockDTOImpl bs SET bs.totalQuantity = :tq, bs.lastModifiedTimeStamp = :lm where bs.bikeType = :bti");
		lQuery.setParameter("tq", bikeStock.getTotalQuantity());
		lQuery.setParameter("lm", Calendar.getInstance());
		BikeTypeDTOImpl bikeTypeDTO = new BikeTypeDTOImpl();
		bikeTypeDTO.setBikeTypeId(lBikeStock.getBikeTypeId());
		lQuery.setParameter("bti", bikeTypeDTO);
		int rowsUpdated = lQuery.executeUpdate();
		if(rowsUpdated <= 0)
		{
			LOG.info("updateBikeStock : failed to update bike stock.");
			LOG.debug("updateBikeStock : End");
			return false;
		}
		LOG.info("updateBikeStock : new bike stock updated successfully.");
		LOG.debug("updateBikeStock : End");
		return true;
	}

}
