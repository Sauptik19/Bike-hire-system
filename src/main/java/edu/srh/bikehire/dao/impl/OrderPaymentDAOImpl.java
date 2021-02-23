package edu.srh.bikehire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.OrderPaymentDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.OrderPaymentDTO;
import edu.srh.bikehire.dto.impl.OrderPaymentDTOImpl;
import edu.srh.bikehire.util.Util;

public class OrderPaymentDAOImpl implements OrderPaymentDAO {
	private static final Logger LOG = LogManager.getLogger(OrderPaymentDAOImpl.class);

	private EntityManager em;
	
	public OrderPaymentDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public OrderPaymentDTOImpl getOrderPaymentByPaymentReference(String pPaymentReference) {
		LOG.debug("getOrderPaymentByPaymentReference : Start");
		Query lQuery = em.createQuery("from OrderPaymentDTOImpl where PaymentReference = :typeId ");
		lQuery.setParameter("typeId", pPaymentReference);
		List<OrderPaymentDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getOrderPaymentByPaymentReference : End");
			return null;
		}
		LOG.debug("getOrderPaymentByPaymentReference : End");
		return results.get(0);
	}

	public OrderPaymentDTO getOrderPaymentByOrderId(int pOrderId) {
		LOG.debug("getOrderPaymentByOrderId : Start");
		Query lQuery = em.createQuery("from OrderPaymentDTOImpl where OrderID = :typeId ");
		lQuery.setParameter("typeId", pOrderId);
		List<OrderPaymentDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getOrderPaymentByOrderId : End");
			return null;
		}
		LOG.debug("getOrderPaymentByOrderId : End");
		return results.get(0);
	}

	public String addOrderPayment(OrderPaymentDTO pOrderPaymentDTO) {
		LOG.debug("addOrderPayment : Start");
		String lstrPaymentReference = Util.getRandomAlphaNumericId();
		OrderPaymentDTOImpl lOrderPaymentDTOImpl = (OrderPaymentDTOImpl) pOrderPaymentDTO;
		lOrderPaymentDTOImpl.setPaymentReference(lstrPaymentReference);
		em.persist(lOrderPaymentDTOImpl);
		LOG.info("addOrderPayment : a new order payment added successfully.");
		LOG.debug("addOrderPayment : End");
		return lstrPaymentReference;
	}

}
