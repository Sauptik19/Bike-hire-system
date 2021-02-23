package edu.srh.bikehire.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.Invoice;
import edu.srh.bikehire.service.core.Order;
import edu.srh.bikehire.service.core.OrderHistory;
import edu.srh.bikehire.service.core.impl.OrderInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceImplTest {
	OrderServiceImpl orderService = new OrderServiceImpl();

	String invoiceID =  null;
	
	@Test(expected = BikeHireSystemException.class)
	public void testAPlaceOrderInvalidBikeId() throws BikeHireSystemException  {
		OrderInfo orderInfo = new OrderInfo();
		//create user id and bike id
		orderInfo.setUserId(1);
		orderInfo.setBikeId(-1);
		orderInfo.setOrderMode("online");
		orderInfo.setBookingTimestamp(Calendar.getInstance());
		orderInfo.setPickupTimestamp(Calendar.getInstance());
		orderInfo.setDropOffTimestamp(Calendar.getInstance());
		orderInfo.setActualDropoffTimestamp(Calendar.getInstance());
		int OrderId = orderService.placeOrder(orderInfo);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testBPlaceOrderInvalidBookingTimeStamp() throws BikeHireSystemException  {
		OrderInfo orderInfo = new OrderInfo();
		//create user id and bike id
		orderInfo.setUserId(1);
		orderInfo.setBikeId(1);
		orderInfo.setOrderMode("online");
		orderInfo.setBookingTimestamp(null);
		orderInfo.setPickupTimestamp(Calendar.getInstance());
		orderInfo.setDropOffTimestamp(Calendar.getInstance());
		orderInfo.setActualDropoffTimestamp(Calendar.getInstance());
		int OrderId = orderService.placeOrder(orderInfo);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testCPlaceOrderInvalidUserId() throws BikeHireSystemException  {
		OrderInfo orderInfo = new OrderInfo();
		//create user id and bike id
		orderInfo.setUserId(-1);
		orderInfo.setBikeId(1);
		orderInfo.setOrderMode("online");
		orderInfo.setBookingTimestamp(Calendar.getInstance());
		orderInfo.setPickupTimestamp(Calendar.getInstance());
		orderInfo.setDropOffTimestamp(Calendar.getInstance());
		orderInfo.setActualDropoffTimestamp(Calendar.getInstance());
		int OrderId = orderService.placeOrder(orderInfo);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testDPlaceOrderInvalidpickUpTimeStamp() throws BikeHireSystemException  {
		OrderInfo orderInfo = new OrderInfo();
		//create user id and bike id
		orderInfo.setUserId(1);
		orderInfo.setBikeId(1);
		orderInfo.setOrderMode("online");
		orderInfo.setBookingTimestamp(Calendar.getInstance());
		orderInfo.setPickupTimestamp(null);
		orderInfo.setDropOffTimestamp(Calendar.getInstance());
		orderInfo.setActualDropoffTimestamp(Calendar.getInstance());
		int OrderId = orderService.placeOrder(orderInfo);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testEPlaceOrderInvalidDropOffTimeStamp() throws BikeHireSystemException  {
		OrderInfo orderInfo = new OrderInfo();
		//create user id and bike id
		orderInfo.setUserId(1);
		orderInfo.setBikeId(1);
		orderInfo.setOrderMode("online");
		orderInfo.setBookingTimestamp(Calendar.getInstance());
		orderInfo.setPickupTimestamp(Calendar.getInstance());
		orderInfo.setDropOffTimestamp(null);
		orderInfo.setActualDropoffTimestamp(Calendar.getInstance());
		int OrderId = orderService.placeOrder(orderInfo);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testFPlaceOrderInvalidActualDropOffTimeStamp() throws BikeHireSystemException  {
		OrderInfo orderInfo = new OrderInfo();
		//create user id and bike id
		orderInfo.setUserId(1);
		orderInfo.setBikeId(1);
		orderInfo.setOrderMode("online");
		orderInfo.setBookingTimestamp(Calendar.getInstance());
		orderInfo.setPickupTimestamp(Calendar.getInstance());
		orderInfo.setDropOffTimestamp(Calendar.getInstance());
		orderInfo.setActualDropoffTimestamp(null);
		int OrderId = orderService.placeOrder(orderInfo);	
	}
	
	@Test
	public void testGPlaceOrder() throws BikeHireSystemException  {
		OrderInfo orderInfo = new OrderInfo();
		//create user id and bike id
		orderInfo.setUserId(1);
		orderInfo.setBikeId(1);
		orderInfo.setOrderMode("online");
		orderInfo.setBookingTimestamp(Calendar.getInstance());
		orderInfo.setPickupTimestamp(Calendar.getInstance());
		orderInfo.setDropOffTimestamp(Calendar.getInstance());
		orderInfo.setActualDropoffTimestamp(Calendar.getInstance());
		int OrderId = orderService.placeOrder(orderInfo);
		assertTrue(OrderId > 0);
	}
	
	@Test
	public void testHGetOrderHistory() throws BikeHireSystemException {
		int userID = 1;

		List<OrderHistory> lReturnList  = orderService.getOrderHistory(userID);
		assertNotNull(lReturnList);
		assertTrue(lReturnList.size()>0);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testMGetOrderHistoryInvalidUserId() throws BikeHireSystemException {
		int userID = -1;
		
		List<OrderHistory> lReturnList  = orderService.getOrderHistory(userID);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testNCancelOrderForCompletedOrder() throws BikeHireSystemException  {
		int orderID=1;
		
		orderService.cancelOrder(orderID);
		
	}
	
	@Test
	public void testOGetOrder() throws BikeHireSystemException {
		int orderID = 1;
		
		Order lReturnOrder  = orderService.getOrder(orderID);
		assertNotNull(lReturnOrder);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testPGetOrderInvalidOrderId() throws BikeHireSystemException {
		int orderID = -1;
		
		Order lReturnOrder  = orderService.getOrder(orderID);
		
	}
	
    @Test
	public void testQGetCurrentOrdersForUser() throws BikeHireSystemException {
    	int userID = 1;

		List<Order> allOrders  = orderService.getCurrentOrdersForUser(userID);
		assertNotNull(allOrders);
		assertTrue(allOrders.size()>0);
	}
    
    @Test
	public void testRGetCurrentOrdersForUserInvalidUserId() throws BikeHireSystemException {
    	int userID = -1;

		List<Order> allOrders  = orderService.getCurrentOrdersForUser(userID);
		assertTrue(allOrders.size() == 0);
	}

	@Test
	public void testSGenerateInvoice() throws BikeHireSystemException {
		//first add damage charges, warehouseId, payment reference and then return invoiceId
		int damagecharges= 12;
		int warehouseId	= 2;
		invoiceID = orderService.generateInvoice(damagecharges,warehouseId,1);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testTGenerateInvoiceInvalidDamageCharge() throws BikeHireSystemException {
		int damagecharges= -12;
		int warehouseId	= 2;
		int orderId = 1;
		String invoiceID = orderService.generateInvoice(damagecharges,warehouseId,orderId);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testUGenerateInvoiceInvalidWarehouseId() throws BikeHireSystemException {
		int damagecharges= 12;
		int warehouseId	= -2;
		int orderId = 1;
		String invoiceID = orderService.generateInvoice(damagecharges,warehouseId,orderId);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testVGenerateInvoiceInvalidOrderId() throws BikeHireSystemException {
		int damagecharges= 12;
		int warehouseId	= 2;
		String invoiceID = orderService.generateInvoice(damagecharges,warehouseId,-1);
		
	}

	@Test
	public void testWGetInvoice() throws BikeHireSystemException {
		//first add new invoice and then get invoice
		Invoice lReturnInvoice  = orderService.getInvoice(invoiceID);
		
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testXGetInvoiceInvalidInvoiceID() throws BikeHireSystemException {
		String invoiceID = null;
		Invoice lReturnInvoice  = orderService.getInvoice(invoiceID);
		assertNotNull(orderService.getOrder(1));
	}

	@Test
	public void testYGetCurrentOrderForUser() throws BikeHireSystemException {
		assertNotNull(orderService.getCurrentOrdersForUser(1));
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testZCancelOrderForInvalidOrderID() throws BikeHireSystemException  {
		int orderID=-1;
		
		orderService.cancelOrder(orderID);
	}

	@Test
	public void testZACancelOrder() throws BikeHireSystemException  {
		int orderID=1;
		
		orderService.cancelOrder(orderID);	
	}
}
