package edu.srh.bikehire.dao;

import edu.srh.bikehire.dao.impl.JPADAOFactory;

public abstract class DAOFactory {
	
	public static DAOFactory getDAOFactory(DAOFactoryType daoFactoryType)
	{
		switch(daoFactoryType)
		{
			case JPADAOFACTORY :
				return new JPADAOFactory();
			default :
				return new JPADAOFactory();
		}
	}
	
	public abstract BikeDAO getBikeDAO();
	
	public abstract BikeStatusDAO getBikeStatusDAO();
	
	public abstract BikeStockDAO getBikeStockDAO();
	
	public abstract BikeTypeDAO getBikeTypeDAO();
	
	public abstract BikeRentMappingDAO getBikeRentMappingDAO();
	
	public abstract WarehouseDAO getWarehouseDAO();
	
	public abstract UserDAO getUserDAO();
	
	public abstract UserAccountDAO getUserAccountDAO();
	
	public abstract UserCredentialDAO getUserCredentialDAO();
	
	public abstract CurrentOrderDAO getOrderDAO();
	
	public abstract InvoiceDAO getInvoiceDAO();
	
	public abstract OrderHistoryDAO getOrderHistory();
	
	public abstract OrderPaymentDAO getOrderPayment();
	
	public abstract void beginTransaction();
	
	public abstract void commitTransaction();
	
	public abstract void rollbackTransaction();
}
