package edu.srh.bikehire.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.BikeStatusDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeStatusDTO;
import edu.srh.bikehire.dto.impl.BikeDTOImpl;
import edu.srh.bikehire.dto.impl.BikeStatusDTOImpl;

public class BikeStatusDAOImpl implements BikeStatusDAO {
	private static final Logger LOG = LogManager.getLogger(BikeStatusDAOImpl.class);
	private EntityManager em;
	
	public BikeStatusDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public BikeStatusDTOImpl getBikeStatus(int pBikeId) {
		LOG.debug("getBikeStatus : Start");
		
		Query lQuery = em.createQuery("from BikeStatusDTOImpl where BikeId = :typeId ");
		lQuery.setParameter("typeId", pBikeId);
		
		List<BikeStatusDTOImpl> lBikeStatues = lQuery.getResultList();
		if(lBikeStatues.size() == 0)
		{
			LOG.debug("getBikeStatus : End");
			return null;
		}
		LOG.debug("getBikeStatus : End");
		return lBikeStatues.get(0);
	}

	public boolean addBikeStatus(BikeStatusDTO pBikeStatus) {
		LOG.debug("addBikeStatus : Start");
		em.persist(pBikeStatus);
		LOG.info("addBikeStatus : new bike status added successfully.");
		LOG.debug("addBikeStatus : End");
		return true;
	}

	public boolean updateBikeStatus(BikeStatusDTO pBikeStatus) {
		LOG.debug("addBikeStatus : Start");
		BikeStatusDTOImpl lOrgBikeStatus = getBikeStatus(pBikeStatus.getBikeId());
		if(pBikeStatus.getLastServiceDate() != null)
		{			
			lOrgBikeStatus.setLastServiceDate(pBikeStatus.getLastServiceDate());
		}
		Query lQuery = em.createQuery("UPDATE BikeStatusDTOImpl bsd SET bsd.status = :st, bsd.lastServiceDate = :lsd, bsd.lastModifiedDate = :lmd where bsd.bike = :bikeId");
		lQuery.setParameter("st", pBikeStatus.getStatus());
		lQuery.setParameter("lsd", lOrgBikeStatus.getLastServiceDate());
		lQuery.setParameter("lmd", Calendar.getInstance());
		BikeDTOImpl bikeDTO = new BikeDTOImpl();
		bikeDTO.setBikeId(pBikeStatus.getBikeId());
		lQuery.setParameter("bikeId", bikeDTO);
		int rowsUpdated = lQuery.executeUpdate();
		if(rowsUpdated <= 0)
		{
			LOG.debug("addBikeStatus : End");
			LOG.info("addBikeStatus : failed to update bike status.");
			return false;
		}
		LOG.info("addBikeStatus : bike status updated successfully.");
		LOG.debug("addBikeStatus : End");
		return true;
	}
	
	public List<BikeDTO> getAllBikesBasedOnStatus(String pStatus, boolean sortPriceDescending) {
		LOG.debug("getAllBikesBasedOnStatus : Start");
		Query lQuery = null;
		
		if(sortPriceDescending)
		{
			lQuery = em.createQuery("select b from BikeDTOImpl b, BikeStatusDTOImpl bs where b.bikeId = bs.bike and bs.status = :statusType Order by b.depositAmount DESC");
		}
		else
		{	
			lQuery = em.createQuery("select b from BikeDTOImpl b, BikeStatusDTOImpl bs where b.bikeId = bs.bike and bs.status = :statusType Order by b.depositAmount ASC");
		}
		
		lQuery.setParameter("statusType", pStatus);
		List<BikeDTO> lBikes = lQuery.getResultList();
		LOG.debug("getAllBikesBasedOnStatus : End");
		return lBikes;
	}
	
	public long getBikeCount(String pStatus, int pBikeTypeId)
	{
		LOG.debug("getBikeCount : Start");
		Query lQuery = em.createQuery("select count(bs) from BikeStatusDTOImpl bs, BikeDTOImpl b, BikeTypeDTOImpl bt where b.bikeId = bs.bike and bs.status = :bikestatus and b.bikeType = bt.bikeTypeId and bt.bikeTypeId = :biketyp");
        lQuery.setParameter("bikestatus", pStatus);
        lQuery.setParameter("biketyp", pBikeTypeId);
        LOG.debug("getBikeCount : End");
        return (Long) lQuery.getSingleResult();
	}
}
