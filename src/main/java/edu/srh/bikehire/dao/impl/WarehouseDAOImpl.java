package edu.srh.bikehire.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.WarehouseDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.WareHouseDTO;
import edu.srh.bikehire.dto.impl.WareHouseDTOImpl;
import edu.srh.bikehire.util.Util;

public class WarehouseDAOImpl implements WarehouseDAO {
	private static final Logger LOG = LogManager.getLogger(WareHouseDTOImpl.class);
	
	private EntityManager em;
	
	public WarehouseDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public WareHouseDTOImpl getWarehouse(int pWarehouseId) {
		LOG.debug("getWarehouse : Start");
		Query lQuery = em.createQuery("from WareHouseDTOImpl where WareHouseId = :typeId ");
		lQuery.setParameter("typeId", pWarehouseId);
		
		List<WareHouseDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getWarehouse : End");
			return null;
		}
		LOG.debug("getWarehouse : End");
		return results.get(0);
	}

	public int addWarehouse(WareHouseDTO pWarehouse) {
		LOG.debug("addWarehouse : Start");
		em.persist(pWarehouse);
		LOG.info("addWarehouse : new warehouse added successfully.");
		LOG.debug("addWarehouse : End");
		return pWarehouse.getWarehouseId();
	}

	public boolean updateWarehouse(WareHouseDTO pWarehouse) {
		LOG.debug("updateWarehouse : Start");
		Query lQuery = em.createQuery("UPDATE WareHouseDTOImpl wh SET wh.storageCapacity = :sc, wh.name = :name, wh.lastmodifiedTimeStamp = :lm where wh.warehouseId = :id ");
		lQuery.setParameter("sc", pWarehouse.getStorageCapacity());
		lQuery.setParameter("name", pWarehouse.getName());
		lQuery.setParameter("lm", Calendar.getInstance());
		lQuery.setParameter("id", pWarehouse.getWarehouseId());
		int rowsUpdated = lQuery.executeUpdate();
		if(rowsUpdated <= 0)
		{
			LOG.info("updateWarehouse : failed to update warehouse.");
			LOG.debug("updateWarehouse : End");
			return false;
		}
		LOG.info("updateWarehouse : warehouse updated successfully.");
		LOG.debug("updateWarehouse : End");
		return true;
	}
	
	public List<WareHouseDTO> getAllWarehouses()
	{
		LOG.debug("getAllWarehouses : Start");
		Query lQuery = em.createQuery("from WareHouseDTOImpl");
		
		List<WareHouseDTO> results = lQuery.getResultList();
		LOG.debug("getAllWarehouses : End");
		return results;
		
	}
}
