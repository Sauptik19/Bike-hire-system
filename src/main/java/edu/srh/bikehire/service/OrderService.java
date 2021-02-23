package edu.srh.bikehire.service;

import java.util.List;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.Invoice;
import edu.srh.bikehire.service.core.Order;
import edu.srh.bikehire.service.core.OrderHistory;

/**
 * This service is responsible for all order related activities.
 * All Services are available for logged in users only.
 *
 */
public interface OrderService {

	/**
	 * This method returns list of {@link OrderHistory} objects for given user. 
	 * @param userID
	 * @return
	 * @throws BikeHireSystemException
	 */
	public List<OrderHistory> getOrderHistory(int userID) throws BikeHireSystemException;
	
	/**
	 * This method will add new order into system. All the inputs are validated and appropriate exception is thrown for invalid inputs.
	 * @param order - Order info
	 * @return - Returns empty list in case of no order history.
	 * @throws BikeHireSystemException
	 */
	public int placeOrder(Order order) throws BikeHireSystemException ;
	
	/**
	 * This method cancel the order based on order id. This method also adds this order in order history for auditing purposes.
	 * @param orderID
	 * @throws BikeHireSystemException
	 */
	public void cancelOrder(int orderID) throws BikeHireSystemException;
	
	/**
	 * This method returns Order for particular oder id provided in input.
	 * @param orderID - Order id
	 * @return - Returns null if order id is not found.
	 * @throws BikeHireSystemException
	 */
	public Order getOrder(int orderID) throws BikeHireSystemException ;
	
	/**
	 * This method generates invoice after bike is returned to warehouse.
	 * @param damageCharges
	 * @param warehouseId - warehouse id where invoice is generated.
	 * @param orderId
	 * @return - Returns invoice id.
	 * @throws BikeHireSystemException
	 */
	public String generateInvoice(int damageCharges, int warehouseId, int orderId) throws BikeHireSystemException ;
	
	/**
	 * This method returns invoice information.
	 * @param invoiceID - invoice id
	 * @return returns null if invoiceID is not available.
	 * @throws BikeHireSystemException
	 */
	public Invoice getInvoice(String invoiceID) throws BikeHireSystemException ;
	
	/**
	 * This method returns current active orders of particular id.
	 * @param userId 
	 * @return - returns empty list of no orders are available for user. 
	 * @throws BikeHireSystemException
	 */
	public List<Order> getCurrentOrdersForUser(int userId) throws BikeHireSystemException;
}
