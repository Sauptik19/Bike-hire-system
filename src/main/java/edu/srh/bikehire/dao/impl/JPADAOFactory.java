package edu.srh.bikehire.dao.impl;

import javax.persistence.EntityManager;

import edu.srh.bikehire.dao.BikeDAO;
import edu.srh.bikehire.dao.BikeRentMappingDAO;
import edu.srh.bikehire.dao.BikeStatusDAO;
import edu.srh.bikehire.dao.BikeStockDAO;
import edu.srh.bikehire.dao.BikeTypeDAO;
import edu.srh.bikehire.dao.CurrentOrderDAO;
import edu.srh.bikehire.dao.DAOFactory;
import edu.srh.bikehire.dao.InvoiceDAO;
import edu.srh.bikehire.dao.OrderHistoryDAO;
import edu.srh.bikehire.dao.OrderPaymentDAO;
import edu.srh.bikehire.dao.UserAccountDAO;
import edu.srh.bikehire.dao.UserCredentialDAO;
import edu.srh.bikehire.dao.UserDAO;
import edu.srh.bikehire.dao.WarehouseDAO;
import edu.srh.bikehire.dao.impl.util.PersistenceManager;

public class JPADAOFactory extends DAOFactory {
	
	EntityManager em;
	
	public JPADAOFactory()
	{
		em = PersistenceManager.INSTANCE.getEntityManager();
	}
	
	@Override
	public BikeDAO getBikeDAO() {
		return new BikeDAOImpl(em);
	}

	@Override
	public BikeStatusDAO getBikeStatusDAO() {
		return new BikeStatusDAOImpl(em);
	}

	@Override
	public BikeStockDAO getBikeStockDAO() {
		return new BikeStockDAOImpl(em);
	}

	@Override
	public BikeTypeDAO getBikeTypeDAO() {
		return new BikeTypeDAOImpl(em);
	}

	@Override
	public BikeRentMappingDAO getBikeRentMappingDAO() {
		return new BikeRentMappingDAOImpl(em);
	}

	@Override
	public WarehouseDAO getWarehouseDAO() {
		return new WarehouseDAOImpl(em);
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl(em);
	}

	@Override
	public UserAccountDAO getUserAccountDAO() {
		return new UserAccountDAOImpl(em);
	}

	@Override
	public UserCredentialDAO getUserCredentialDAO() {
		return new UserCredentialDAOImpl(em);
	}

	@Override
	public CurrentOrderDAO getOrderDAO() {
		return new CurrentOrderDAOImpl(em);
	}

	@Override
	public InvoiceDAO getInvoiceDAO() {
		return new InvoiceDAOImpl(em);
	}

	@Override
	public OrderHistoryDAO getOrderHistory() {
		return new OrderHistoryDAOImpl(em);
	}

	@Override
	public OrderPaymentDAO getOrderPayment() {
		return new OrderPaymentDAOImpl(em);
	}

	@Override
	public void beginTransaction() {
		em.getTransaction().begin();
	}

	@Override
	public void commitTransaction() {
		if(em.getTransaction().isActive())
		{			
			em.getTransaction().commit();
		}
	}
	
	public void rollbackTransaction() {
		if(em.getTransaction().isActive())
		{			
			em.getTransaction().rollback();
		}
	}
}
