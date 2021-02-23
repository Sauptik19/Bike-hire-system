package edu.srh.bikehire.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.CurrentOrderDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.CurrentOrderDTO;
import edu.srh.bikehire.dto.impl.CurrentOrderDTOImpl;
import edu.srh.bikehire.util.Util;

public class CurrentOrderDAOImpl implements CurrentOrderDAO {
	private static final Logger LOG = LogManager.getLogger(CurrentOrderDAOImpl.class);
	
	private EntityManager em;
	
	public CurrentOrderDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public CurrentOrderDTOImpl getCurrentOrderByOrderId(int pOrderId) {
		LOG.debug("getCurrentOrderByOrderId : Start");
		Query lQuery = em.createQuery("from CurrentOrderDTOImpl where OrderID = :typeId ");
		lQuery.setParameter("typeId", pOrderId);
		List<CurrentOrderDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getCurrentOrderByOrderId : End");
			return null;
		}
		LOG.debug("getCurrentOrderByOrderId : End");
		return results.get(0);
	}

	public CurrentOrderDTOImpl getCurrentOrderByBikeId(int pBikeId) {
		LOG.debug("getCurrentOrderByBikeId : Start");
		Query lQuery = em.createQuery("from CurrentOrderDTOImpl where BikeID = :typeId ");
		lQuery.setParameter("typeId", pBikeId);
		List<CurrentOrderDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getCurrentOrderByBikeId : End");
			return null;
		}
		LOG.debug("getCurrentOrderByBikeId : End");
		return results.get(0);
	}

	public List<CurrentOrderDTO> getCurrentOrderByUserId(int pUserId)
	{
		LOG.debug("getCurrentOrderByUserId : Start");
		Query lQuery = em.createQuery("from CurrentOrderDTOImpl where UserID = :typeId ");
		lQuery.setParameter("typeId", pUserId);
		List<CurrentOrderDTO> results = lQuery.getResultList();
		LOG.debug("getCurrentOrderByUserId : End");
		return results;
	}
	
	public int addCurrentOrder(CurrentOrderDTO pCurrentOrderDTO) {
		LOG.debug("addCurrentOrder : Start");
		CurrentOrderDTOImpl lCurrentOrderDTOImpl = (CurrentOrderDTOImpl) pCurrentOrderDTO;
		em.persist(lCurrentOrderDTOImpl);
		LOG.info("addCurrentOrder : new order added successfully.");
		LOG.debug("addCurrentOrder : End");
		return lCurrentOrderDTOImpl.getOrderID();
	}

	public boolean updateCurrentOrder(CurrentOrderDTO pCurrentOrderDTO) {
		LOG.debug("updateCurrentOrder : Start");
		CurrentOrderDTOImpl lCurrentOrderDTOImpl = getCurrentOrderByOrderId(pCurrentOrderDTO.getOrderID());
		
		lCurrentOrderDTOImpl.setPickupTimeStamp(pCurrentOrderDTO.getPickupTimeStamp());
		
		LOG.info("updateCurrentOrder : order updated successfully.");
		LOG.debug("updateCurrentOrder : End");
		return true;
	}

	public boolean deleteCurrentOrder(CurrentOrderDTO pCurrentOrderDTO) {
		LOG.debug("deleteCurrentOrder : Start");
		Query lQuery = em.createQuery("delete from CurrentOrderDTOImpl where OrderID = :typeId ");
		lQuery.setParameter("typeId", pCurrentOrderDTO.getOrderID());
		int lRowsAffected = lQuery.executeUpdate();
		if(lRowsAffected > 0)
		{
			LOG.info("deleteCurrentOrder : order deleted successfully.");
			LOG.debug("deleteCurrentOrder : End");
			return true;
		}
		LOG.info("deleteCurrentOrder : failed to delete order.");
		LOG.debug("deleteCurrentOrder : End");
		return false;
	}
	
	public List<CurrentOrderDTO> getOrdersBasedOnPickUpDate(Calendar pFromCalendar, Calendar pToCalendar)
	{
		LOG.debug("getOrdersBasedOnPickUpDate : Start");
		Query lQuery = em.createQuery("from CurrentOrderDTOImpl where PickupTimeStamp >= :fromTime and PickupTimeStamp <= :toTime ");
		lQuery.setParameter("fromTime", pFromCalendar, TemporalType.TIMESTAMP);
		lQuery.setParameter("toTime", pToCalendar, TemporalType.TIMESTAMP);
		
		List<CurrentOrderDTO> results = lQuery.getResultList();
		LOG.debug("getOrdersBasedOnPickUpDate : End");
		return results;
	}
	
	public List<CurrentOrderDTO> getOrdersBasedOnDropOffDate(Calendar pFromCalendar, Calendar pToCalendar)
	{
		LOG.debug("getOrdersBasedOnDropOffDate : Start");
		Query lQuery = em.createQuery("from CurrentOrderDTOImpl where DropOffTimeStamp >= :fromTime and DropOffTimeStamp <= :toTime ");
		lQuery.setParameter("fromTime", pFromCalendar, TemporalType.TIMESTAMP);
		lQuery.setParameter("toTime", pToCalendar, TemporalType.TIMESTAMP);
		
		List<CurrentOrderDTO> results = lQuery.getResultList();
		LOG.debug("getOrdersBasedOnDropOffDate : End");
		return results;
	}
}
