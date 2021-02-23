package edu.srh.bikehire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.OrderHistoryDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.OrderHistoryDTO;
import edu.srh.bikehire.dto.impl.OrderHistoryDTOImpl;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {
	private static final Logger LOG = LogManager.getLogger(OrderHistoryDAOImpl.class);
	
	private EntityManager em;
	
	public OrderHistoryDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public OrderHistoryDTOImpl getOrderHistoryByOrderId(int pOrderId) {
		LOG.debug("getOrderHistoryByOrderId : Start");
		Query lQuery = em.createQuery("from OrderHistoryDTOImpl where OrderID = :typeId ");
		lQuery.setParameter("typeId", pOrderId);
		List<OrderHistoryDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getOrderHistoryByOrderId : End");
			return null;
		}
		LOG.debug("getOrderHistoryByOrderId : End");
		return results.get(0);
	}

	public OrderHistoryDTO getOrderHistoryByInvoiceId(String pInvoiceId) {
		LOG.debug("getOrderHistoryByInvoiceId : Start");
		Query lQuery = em.createQuery("from OrderHistoryDTOImpl where InvoiceID = :typeId ");
		lQuery.setParameter("typeId", pInvoiceId);
		List<OrderHistoryDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getOrderHistoryByInvoiceId : End");
			return null;
		}
		LOG.debug("getOrderHistoryByInvoiceId : End");
		return results.get(0);
	}

	public List<OrderHistoryDTO> getOrderHistoryByBikeId(int pBikeId) {
		LOG.debug("getOrderHistoryByBikeId : Start");
		Query lQuery = em.createQuery("from OrderHistoryDTOImpl where BikeID = :typeId ");
		lQuery.setParameter("typeId", pBikeId);
		List<OrderHistoryDTO> results = lQuery.getResultList();
		LOG.debug("getOrderHistoryByBikeId : End");
		return results;
	}

	public List<OrderHistoryDTO> getOrderHistoryByUserId(int pUserId) {
		LOG.debug("getOrderHistoryByUserId : Start");
		Query lQuery = em.createQuery("from OrderHistoryDTOImpl where UserID = :typeId ");
		lQuery.setParameter("typeId", pUserId);
		List<OrderHistoryDTO> results = lQuery.getResultList();
		LOG.debug("getOrderHistoryByUserId : End");
		return results;
	}

	public boolean addOrderHistory(OrderHistoryDTO pOrderHistoryDTO) {
		LOG.debug("addOrderHistory : Start");
		em.persist(pOrderHistoryDTO);
		LOG.info("addOrderHistory : new order history added successfully.");
		LOG.debug("addOrderHistory : End");
		return true;
	}

}
