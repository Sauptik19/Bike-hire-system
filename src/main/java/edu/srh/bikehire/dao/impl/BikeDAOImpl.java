package edu.srh.bikehire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.BikeDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.impl.BikeDTOImpl;
import edu.srh.bikehire.dto.impl.WareHouseDTOImpl;

public class BikeDAOImpl implements BikeDAO {
	private static final Logger LOG = LogManager.getLogger(BikeDAOImpl.class);
	
	private EntityManager em;
	
	public BikeDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public BikeDTOImpl getBike(int pBikeId) {
		LOG.debug("getBike : Start");
		
		Query lQuery = em.createQuery("from BikeDTOImpl where BikeId = :typeId ");
		lQuery.setParameter("typeId", pBikeId);
		
		List<BikeDTOImpl> lBikes = lQuery.getResultList();
		if(lBikes.size() == 0)
		{
			LOG.debug("getBike : End");
			return null;
		}
		LOG.debug("getBike : End");
		return lBikes.get(0);
	}

	public int addBike(BikeDTO pBike) {
		LOG.debug("addBike : Start");
		BikeDTOImpl lBikeDTOImpl = (BikeDTOImpl) pBike;
		em.persist(lBikeDTOImpl);
		LOG.info("addBike : new bike added successfully.");
		LOG.debug("addBike : End");
		return lBikeDTOImpl.getBikeId();
	}

	public boolean updateBike(BikeDTO pBike) {
		LOG.debug("updateBike : Start");
		BikeDTOImpl lBike = getBike(pBike.getBikeId());
		if(pBike.getBikeTitle() != null)
		{			
			lBike.setBikeTitle(pBike.getBikeTitle());
		}
		if(pBike.getDepositAmount() > 0)
		{
			lBike.setDepositAmount(pBike.getDepositAmount());
		}
		WareHouseDTOImpl lWareHouse = new WareHouseDTOImpl();
		if(pBike.getWareHouseID() > 0) 
		{
			lWareHouse.setWarehouseId(pBike.getWareHouseID());
			lBike.setWarehouse(lWareHouse);
		}
		Query lQuery = em.createQuery("UPDATE BikeDTOImpl bd SET bd.bikeTitle = :bt, bd.depositAmount = :da, bd.warehouse = :wa where bd.bikeId = :bi");
		lQuery.setParameter("bt", lBike.getBikeTitle());
		lQuery.setParameter("da", lBike.getDepositAmount());
		lQuery.setParameter("wa", lWareHouse);
		lQuery.setParameter("bi", lBike.getBikeId());
		
		int rowsUpdated = lQuery.executeUpdate();
		if(rowsUpdated <= 0)
		{
			LOG.info("updateBike : failed to update bike.");
			LOG.debug("updateBike : End");
			return false;
		}
		LOG.info("updateBike : successfully updated bike.");
		LOG.debug("updateBike : End");
		return true;
	}

	public List<BikeDTO> getBikeForWarehouseId(int pWarehouseId, boolean pSortPriceDescending) {
		LOG.debug("getBikeForWarehouseId : Start");
		Query lQuery = null;
		if(pSortPriceDescending)
		{
			lQuery = em.createQuery("from BikeDTOImpl where WareHouseId = :typeId Order by DepositAmount DESC");
		}
		else
		{			
			lQuery = em.createQuery("from BikeDTOImpl where WareHouseId = :typeId Order by DepositAmount ASC");
		}
		lQuery.setParameter("typeId", pWarehouseId);
		
		List<BikeDTO> lBikes = lQuery.getResultList();
		LOG.debug("getBikeForWarehouseId : End");
		return (List<BikeDTO>)lBikes;
	}

	public List<BikeDTO> getBikeForBikeType(int pBikeTypeId, boolean pSortPriceDescending) {
		LOG.debug("getBikeForBikeType : Start");
		Query lQuery = null;
		if(pSortPriceDescending)
		{			
			lQuery = em.createQuery("from BikeDTOImpl where BikeTypeId = :typeId Order by DepositAmount DESC");
		}
		else
		{
			lQuery = em.createQuery("from BikeDTOImpl where BikeTypeId = :typeId Order by DepositAmount ASC");
		}
		
		lQuery.setParameter("typeId", pBikeTypeId);
		
		List<BikeDTO> lBikes = lQuery.getResultList();
		LOG.debug("getBikeForBikeType : End");
		return lBikes;
	}

	public List<BikeDTO> getAllBikes(boolean pSortPriceDescending)
	{
		LOG.debug("getAllBikes : Start");
		Query lQuery = null;
		if(pSortPriceDescending)
		{
			lQuery = em.createQuery("from BikeDTOImpl Order by DepositAmount DESC");
		}
		else
		{
			lQuery = em.createQuery("from BikeDTOImpl Order by DepositAmount ASC");
		}
		
		List<BikeDTO> lBikes = lQuery.getResultList();
		LOG.debug("getAllBikes : End");
		return lBikes;
		
	}
}