package edu.srh.bikehire.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.InvoiceDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;
import edu.srh.bikehire.dto.InvoiceDTO;
import edu.srh.bikehire.dto.impl.InvoiceDTOImpl;
import edu.srh.bikehire.util.Util;

public class InvoiceDAOImpl implements InvoiceDAO {
	private static final Logger LOG = LogManager.getLogger(InvoiceDAOImpl.class);
	
	private EntityManager em;
	
	public InvoiceDAOImpl(EntityManager em)
	{
		this.em = em;
	}
	
	public InvoiceDTOImpl getInvoiceByInvoiceId(String pInvoiceId) {
		LOG.debug("getInvoiceByInvoiceId : Start");
		Query lQuery = em.createQuery("from InvoiceDAOImpl where invoiceID = :typeId ");
		lQuery.setParameter("typeId", pInvoiceId);
		List<InvoiceDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getInvoiceByInvoiceId : End");
			return null;
		}
		LOG.debug("getInvoiceByInvoiceId : End");
		return results.get(0);
	}

	public InvoiceDTO getInvoiceByOrderId(int pOrderId) {
		LOG.debug("getInvoiceByOrderId : Start");
		Query lQuery = em.createQuery("from InvoiceDAOImpl where invoiceID = :typeId ");
		lQuery.setParameter("typeId", pOrderId);
		List<InvoiceDTOImpl> results = lQuery.getResultList();
		if(results == null || results.size() == 0)
		{
			LOG.debug("getInvoiceByOrderId : End");
			return null;
		}
		LOG.debug("getInvoiceByOrderId : End");
		return results.get(0);
	}

	public String addInvoice(InvoiceDTO pInvoiceDTO) {
		LOG.debug("addInvoice : Start");
		String lstrInvoiceId = Util.getRandomAlphaNumericId();
		InvoiceDTOImpl lInvoiceDTOImpl = (InvoiceDTOImpl) pInvoiceDTO;
		lInvoiceDTOImpl.setInvoiceID(lstrInvoiceId);
		em.persist(lInvoiceDTOImpl);
		LOG.info("addInvoice : new invoice added successfully.");
		LOG.debug("addInvoice : End");
		return lstrInvoiceId;
	}

}
