package edu.srh.bikehire.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.BikeDAO;
import edu.srh.bikehire.dao.BikeRentMappingDAO;
import edu.srh.bikehire.dao.BikeStatusDAO;
import edu.srh.bikehire.dao.CurrentOrderDAO;
import edu.srh.bikehire.dao.DAOFactory;
import edu.srh.bikehire.dao.DAOFactoryType;
import edu.srh.bikehire.dao.InvoiceDAO;
import edu.srh.bikehire.dao.OrderHistoryDAO;
import edu.srh.bikehire.dao.OrderPaymentDAO;
import edu.srh.bikehire.dao.UserAccountDAO;
import edu.srh.bikehire.dao.UserDAO;
import edu.srh.bikehire.dao.WarehouseDAO;
import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.dto.BikeStatusDTO;
import edu.srh.bikehire.dto.CurrentOrderDTO;
import edu.srh.bikehire.dto.InvoiceDTO;
import edu.srh.bikehire.dto.OrderHistoryDTO;
import edu.srh.bikehire.dto.OrderPaymentDTO;
import edu.srh.bikehire.dto.UserAccountDTO;
import edu.srh.bikehire.dto.UserDTO;
import edu.srh.bikehire.dto.WareHouseDTO;
import edu.srh.bikehire.dto.impl.BikeDTOImpl;
import edu.srh.bikehire.dto.impl.BikeStatusDTOImpl;
import edu.srh.bikehire.dto.impl.CurrentOrderDTOImpl;
import edu.srh.bikehire.dto.impl.InvoiceDTOImpl;
import edu.srh.bikehire.dto.impl.OrderHistoryDTOImpl;
import edu.srh.bikehire.dto.impl.OrderPaymentDTOImpl;
import edu.srh.bikehire.dto.impl.UserDTOImpl;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.exception.util.ExceptionUtil;
import edu.srh.bikehire.login.LoginConstants;
import edu.srh.bikehire.service.OrderService;
import edu.srh.bikehire.service.core.Invoice;
import edu.srh.bikehire.service.core.Order;
import edu.srh.bikehire.service.core.OrderHistory;
import edu.srh.bikehire.service.core.impl.InvoiceInfo;
import edu.srh.bikehire.service.core.impl.OrderHistoryInfo;
import edu.srh.bikehire.service.core.impl.OrderInfo;
import edu.srh.bikehire.util.Constants;
import edu.srh.bikehire.validator.OrderValidator;

public class OrderServiceImpl implements OrderService {
	private static final Logger LOG = LogManager.getLogger(OrderServiceImpl.class);
	
	private BikeDAO bikeDAO;
	
	private UserAccountDAO userAccountDAO;
	
	private CurrentOrderDAO currentOrderDAO;
	
	private InvoiceDAO invoiceDAO;
	
	private OrderHistoryDAO orderHistoryDAO;
	
	private BikeStatusDAO bikeStatusDAO;
	
	private WarehouseDAO warehouseDAO;
	
	private OrderPaymentDAO orderPaymentDAO;
	
	private BikeRentMappingDAO bikeRentMappingDAO;
	
	private UserDAO userDAO;
	
	private DAOFactory daoFactory;
	
	public OrderServiceImpl()
	{
		daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.JPADAOFACTORY);
		
		bikeDAO = daoFactory.getBikeDAO();
		userAccountDAO = daoFactory.getUserAccountDAO();
		currentOrderDAO = daoFactory.getOrderDAO();
		invoiceDAO = daoFactory.getInvoiceDAO();
		orderHistoryDAO = daoFactory.getOrderHistory();
		bikeStatusDAO = daoFactory.getBikeStatusDAO();
		warehouseDAO = daoFactory.getWarehouseDAO();
		orderPaymentDAO = daoFactory.getOrderPayment();
		bikeRentMappingDAO = daoFactory.getBikeRentMappingDAO();
		userDAO = daoFactory.getUserDAO();
	}

	public List<OrderHistory> getOrderHistory(int userID) throws BikeHireSystemException {
		LOG.info("getOrderHistory : Start");
		try
		{			
			UserAccountDTO userAccountDTO = getUserAccount(userID);
			validateUserStatus(userAccountDTO);
			
			List<OrderHistoryDTO> lOrderHistoryDTO = orderHistoryDAO.getOrderHistoryByUserId(userID);
			List<OrderHistory> lReturnList = new ArrayList<OrderHistory>();
			for(OrderHistoryDTO lOrderDTO : lOrderHistoryDTO)
			{
				OrderHistory lOrderInfo = getOrderHistoryFromDTO(lOrderDTO);
				lReturnList.add(lOrderInfo);
			}
			LOG.info("getOrderHistory : End");
			return lReturnList;
		}
		catch(Throwable throwable)
		{
			LOG.error("getOrderHistory : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public int placeOrder(Order order) throws BikeHireSystemException {
		LOG.info("placeOrder : Start");
		try
		{			
			daoFactory.beginTransaction();
			OrderValidator lValidator = new OrderValidator(order);
			lValidator.validateForNewOrder();
			
			UserAccountDTO userAccountDTO = getUserAccount(order.getUserId());
			validateUserStatus(userAccountDTO);
			
			BikeStatusDTO bikeStatusDTO = bikeStatusDAO.getBikeStatus(order.getBikeId());
			validateBikeStatus(bikeStatusDTO);
			
			CurrentOrderDTO currentOrderDTO = getDTOFromInputs(order);
			int orderId = currentOrderDAO.addCurrentOrder(currentOrderDTO);
			LOG.info("placeOrder : order added in database");
			
			//update bike status
			bikeStatusDAO.updateBikeStatus(getHiredBikeStatusDTO(bikeStatusDTO));
			LOG.info("placeOrder : bike status changed to hired.");
			
			updateOrderPaymentForDepositPayment(currentOrderDTO);
			
			daoFactory.commitTransaction();
			
			LOG.info("placeOrder : bike deposit payment done.");
			UserDTO userDTO = userDAO.getUser(order.getUserId());
			
			//Send order notification
			EmailNotificationService emailNotificationService = new EmailNotificationService();
			emailNotificationService.bookingConfirmation(currentOrderDTO.getOrderID(), userDTO.getEmailId());
			LOG.info("placeOrder : successfully sent email notification.");
			LOG.info("placeOrder : End");
			return orderId;
		}
		catch(Throwable throwable)
		{
			daoFactory.rollbackTransaction();
			LOG.error("placeOrder : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public void cancelOrder(int orderID) throws BikeHireSystemException {
		LOG.info("cancelOrder : Start");
		
		try
		{
			daoFactory.beginTransaction();
			CurrentOrderDTO currentOrderDTO = currentOrderDAO.getCurrentOrderByOrderId(orderID);
			if(currentOrderDTO == null)
			{
				//ERRORMESSAGE: Current Order Data Transfer Object Not Found.
				throw new BikeHireSystemException(10086);
			}
			int orderId = currentOrderDTO.getOrderID();
			currentOrderDAO.deleteCurrentOrder(currentOrderDTO);
			LOG.info("cancelOrder : order deleted from orders.");
			
			//make bike status available
			BikeStatusDTOImpl bikeStatusDTOImpl = new BikeStatusDTOImpl();
			BikeDTOImpl bikeDTOImpl = new BikeDTOImpl();
			bikeDTOImpl.setBikeId(currentOrderDTO.getBikeID());
			bikeStatusDTOImpl.setBikeDTO(bikeDTOImpl);
			bikeStatusDTOImpl.setStatus(BikeStatusType.AVALIABLE_BIKE.getBikeStatus());
			bikeStatusDAO.updateBikeStatus(bikeStatusDTOImpl);
			LOG.info("cancelOrder : bike status changed to avaiable.");
			
			//move order to order history
			OrderHistoryDTOImpl orderHistoryDTOImpl = new OrderHistoryDTOImpl();
			orderHistoryDTOImpl.setBikeDTO(bikeDTOImpl);
			UserDTOImpl userDTOImpl = new UserDTOImpl();
			userDTOImpl.setId(currentOrderDTO.getUserID());
			orderHistoryDTOImpl.setUserDTO(userDTOImpl);
			orderHistoryDTOImpl.setOrderID(orderId);
			orderHistoryDTOImpl.setBookingTimeStamp(currentOrderDTO.getBookingTimeStamp());
			orderHistoryDTOImpl.setPickupTimeStamp(currentOrderDTO.getPickupTimeStamp());
			orderHistoryDTOImpl.setReturnedTimeStamp(currentOrderDTO.getPickupTimeStamp());
			orderHistoryDTOImpl.setOrderStatus(Constants.ORDER_STATUS_CANCELLED);
			orderHistoryDAO.addOrderHistory(orderHistoryDTOImpl);
			LOG.info("cancelOrder : order moved to order history");
			
			daoFactory.commitTransaction();
			
			UserDTO userDTO = userDAO.getUser(currentOrderDTO.getUserID());
			
			//Send cancel order notification
			EmailNotificationService emailNotificationService = new EmailNotificationService();
			emailNotificationService.cancelBooking(orderID, userDTO.getEmailId());
			LOG.info("cancelOrder : cancel booking email sent.");
			LOG.info("cancelOrder : End");
		}
		catch(Throwable throwable)
		{
			daoFactory.rollbackTransaction();
			LOG.error("cancelOrder : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public Order getOrder(int orderID) throws BikeHireSystemException {
		LOG.info("getOrder : Start");
		try
		{			
			CurrentOrderDTO currentOrderDTO = currentOrderDAO.getCurrentOrderByOrderId(orderID);
			if(currentOrderDTO == null)
			{
				//ERRORMESSAGE: Current Order Data Transfer Object Not Found.
				throw new BikeHireSystemException(10086);
			}
			
			Order lReturnOrder = getOrderFromDTO(currentOrderDTO);
			LOG.info("getOrder : End");
			return lReturnOrder;
		}
		catch(Throwable throwable)
		{
			LOG.error("getOrder : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public List<Order> getCurrentOrdersForUser(int userId) throws BikeHireSystemException
	{
		LOG.info("getCurrentOrdersForUser : Start");
		try
		{			
			List<CurrentOrderDTO> currentOrderDTOs = currentOrderDAO.getCurrentOrderByUserId(userId);
			if(currentOrderDTOs == null)
			{
				//ERRORMESSAGE: List of Current Order Data Transfer Objects Not Found.
				throw new BikeHireSystemException(10087);
			}
			
			System.out.println("You have " + currentOrderDTOs.size() + " order(s)");
			List<Order> allOrders = new ArrayList<Order>();
			for(CurrentOrderDTO currentOrder : currentOrderDTOs)
			{			
				Order lReturnOrder = getOrderFromDTO(currentOrder);
				allOrders.add(lReturnOrder);
			}
			
			LOG.info("getCurrentOrdersForUser : End");
			return allOrders;
		}
		catch(Throwable throwable)
		{
			LOG.error("getCurrentOrdersForUser : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	public String generateInvoice( int damageCharges, int warehouseId, int orderId) throws BikeHireSystemException {
		LOG.info("getCurrentOrdersForUser : Start");
		try
		{
			daoFactory.beginTransaction();
			OrderPaymentDTO orderPaymentDTO = orderPaymentDAO.getOrderPaymentByOrderId(orderId);
			if(orderPaymentDTO == null)
			{
				//ERRORMESSAGE: Order Payment Data Transfer Object Not Found.
				throw new BikeHireSystemException(10088);
			}
			
			CurrentOrderDTO currentOrderDTO = currentOrderDAO.getCurrentOrderByOrderId(orderPaymentDTO.getOrderID());
			if(currentOrderDTO == null)
			{
				//ERRORMESSAGE: Current Order Data Transfer Object Not Found.
				throw new BikeHireSystemException(10086);
			}
			
			WareHouseDTO warehouseDTO = warehouseDAO.getWarehouse(warehouseId);
			
			InvoiceDTOImpl invoiceDTO = new InvoiceDTOImpl();
			invoiceDTO.setDamageCharges(damageCharges);
			invoiceDTO.setCreationTimeStamp(Calendar.getInstance());
			invoiceDTO.setOrderID(currentOrderDTO.getOrderID());
			invoiceDTO.setWarehouseDTO(warehouseDTO);
			invoiceDTO.setReturnDeposit(orderPaymentDTO.getDepositAmount() - damageCharges);
			int finalAmount = getFinalAmountPaid(currentOrderDTO, orderPaymentDTO);
			invoiceDTO.setFinalAmount(finalAmount);
			
			String invoiceId = invoiceDAO.addInvoice(invoiceDTO);
			LOG.info("getCurrentOrdersForUser : invoice added successfully.");
			
			BikeStatusDTOImpl bikeStatusDTOImpl = new BikeStatusDTOImpl();
			BikeDTOImpl bikeDTOImpl = new BikeDTOImpl();
			bikeDTOImpl.setBikeId(currentOrderDTO.getBikeID());
			bikeStatusDTOImpl.setBikeDTO(bikeDTOImpl);
			bikeStatusDTOImpl.setStatus(BikeStatusType.AVALIABLE_BIKE.getBikeStatus());
			bikeStatusDAO.updateBikeStatus(bikeStatusDTOImpl);
			LOG.info("getCurrentOrdersForUser : bike status changed to avaiable.");
			
			currentOrderDAO.deleteCurrentOrder(currentOrderDTO);
			LOG.info("getCurrentOrdersForUser : current order deleted successfully.");
			
			OrderHistoryDTOImpl orderHistoryDTOImpl = new OrderHistoryDTOImpl();
			orderHistoryDTOImpl.setInvoiceId(invoiceId);
			orderHistoryDTOImpl.setBikeDTO(bikeDTOImpl);
			orderHistoryDTOImpl.setOrderID(currentOrderDTO.getOrderID());
			UserDTOImpl userDTOImpl = new UserDTOImpl();
			userDTOImpl.setId(currentOrderDTO.getUserID());
			orderHistoryDTOImpl.setUserDTO(userDTOImpl);
			orderHistoryDTOImpl.setBookingTimeStamp(currentOrderDTO.getBookingTimeStamp());
			orderHistoryDTOImpl.setPickupTimeStamp(currentOrderDTO.getPickupTimeStamp());
			orderHistoryDTOImpl.setReturnedTimeStamp(currentOrderDTO.getPickupTimeStamp());
			orderHistoryDTOImpl.setOrderStatus(Constants.ORDER_STATUS_COMPLETED);
			orderHistoryDAO.addOrderHistory(orderHistoryDTOImpl);
			daoFactory.commitTransaction();
			LOG.info("getCurrentOrdersForUser : order moved to order history.");
			
			UserDTO userDTO = userDAO.getUser(currentOrderDTO.getUserID());
			
			//Send invoice notification
			EmailNotificationService emailNotificationService = new EmailNotificationService();
			emailNotificationService.orderInvoice(currentOrderDTO.getOrderID(), invoiceId, finalAmount, userDTO.getEmailId());
			LOG.info("getCurrentOrdersForUser : order invoice email sent successfully.");
			
			LOG.info("getCurrentOrdersForUser : End");
			return invoiceId;
		}
		catch(Throwable throwable)
		{
			daoFactory.rollbackTransaction();
			LOG.error("getCurrentOrdersForUser : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public Invoice getInvoice(String invoiceID) throws BikeHireSystemException  {
		LOG.info("getInvoice : Start");
		try
		{			
			InvoiceDTO invoiceDTO = invoiceDAO.getInvoiceByInvoiceId(invoiceID);
			
			Invoice lReturnInvoice = getInvoiceFromDTO(invoiceDTO);
			
			LOG.info("getInvoice : End");
			return lReturnInvoice;
		}
		catch(Throwable throwable)
		{
			LOG.error("getInvoice : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
		
	}
	
	private UserAccountDTO getUserAccount(int userID)
	{
		UserAccountDTO lUserAccountDTO = userAccountDAO.getUserAccount(userID);
		return lUserAccountDTO;
	}
	
	private void validateUserStatus(UserAccountDTO userAccountDTO) throws BikeHireSystemException
	{
		if (userAccountDTO == null) {
			//ERRORMESSAGE: User Account Data Transfer Object Not Found.
			throw new BikeHireSystemException(10085);
		}
		
		if(!userAccountDTO.getAccountStatus().equals(LoginConstants.LOGIN_ACCOUNT_STATUS_ACTIVE))
		{
			//ERRORMESSAGE: User Account status is not active.
			throw new BikeHireSystemException(10089);
		}
	}
	
	private void validateBikeStatus(BikeStatusDTO bikeStatusDTO) throws BikeHireSystemException
	{
		if(bikeStatusDTO == null)
		{
			//ERRORMESSAGE: Bike Status Data Transfer Object Not Found.
			throw new BikeHireSystemException(10090);
		}
		
		if(!bikeStatusDTO.getStatus().equals(BikeStatusType.AVALIABLE_BIKE.getBikeStatus()))
		{
			//ERRORMESSAGE: No bike is available. 
			throw new BikeHireSystemException(10091);
		}
	}
	
	private OrderHistory getOrderHistoryFromDTO(OrderHistoryDTO previousOrders) {
		OrderHistoryInfo lOrderInfo = new OrderHistoryInfo();
		lOrderInfo.setBikeId(previousOrders.getBikeID());
		lOrderInfo.setBookingTimeStamp(previousOrders.getBookingTimeStamp());
		lOrderInfo.setInvoiceId(previousOrders.getInvoiceId());
		lOrderInfo.setOrderId(previousOrders.getOrderID());
		lOrderInfo.setPickupTimeStamp(previousOrders.getPickupTimeStamp());
		lOrderInfo.setReturnedTimeStamp(previousOrders.getReturnedTimeStamp());
		lOrderInfo.setUserId(previousOrders.getUserID());
		lOrderInfo.setOrderStatus(previousOrders.getOrderStatus());
		return lOrderInfo;
	}

	private CurrentOrderDTO getDTOFromInputs(Order order)
	{
		CurrentOrderDTOImpl lCurrentOrderDTOImpl = new CurrentOrderDTOImpl();
		lCurrentOrderDTOImpl.setActualDropOffTimeStamp(order.getActualdropoffTimestamp());
		lCurrentOrderDTOImpl.setBookingTimeStamp(order.getBookingTimestamp());
		lCurrentOrderDTOImpl.setDropOffTimeStamp(order.getDropoffTimestamp());
		lCurrentOrderDTOImpl.setPickupTimeStamp(order.getPickupTimestamp());
		lCurrentOrderDTOImpl.setOrderMode(order.getOrderMode());
		
		BikeDTOImpl bikeDTOImpl = new BikeDTOImpl();
		bikeDTOImpl.setBikeId(order.getBikeId());
		lCurrentOrderDTOImpl.setBikeDTO(bikeDTOImpl);
		
		UserDTOImpl userDTOImpl = new UserDTOImpl();
		userDTOImpl.setId(order.getUserId());
		lCurrentOrderDTOImpl.setUserDTO(userDTOImpl);
		
		return lCurrentOrderDTOImpl;
	}
	
	private Order getOrderFromDTO(CurrentOrderDTO pCurrentOrder)
	{
		OrderInfo lOrderInfo = new OrderInfo();
		lOrderInfo.setActualDropoffTimestamp(pCurrentOrder.getActualDropOffTimeStamp());
		lOrderInfo.setBikeId(pCurrentOrder.getBikeID());
		lOrderInfo.setBookingTimestamp(pCurrentOrder.getBookingTimeStamp());
		lOrderInfo.setDropOffTimestamp(pCurrentOrder.getDropOffTimeStamp());
		lOrderInfo.setOrderId(pCurrentOrder.getOrderID());
		lOrderInfo.setOrderMode(pCurrentOrder.getOrderMode());
		lOrderInfo.setPickupTimestamp(pCurrentOrder.getPickupTimeStamp());
		lOrderInfo.setUserId(pCurrentOrder.getUserID());
		return lOrderInfo;
	}
	
	private int getFinalAmountPaid(CurrentOrderDTO currentOrderDTO, OrderPaymentDTO orderPaymentDTO)
	{
		Calendar pickupCalendar = currentOrderDTO.getPickupTimeStamp();
		Calendar dropoffCalendar = currentOrderDTO.getActualDropOffTimeStamp();
		long diffMilliSecond = dropoffCalendar.getTimeInMillis() - pickupCalendar.getTimeInMillis();
		int noOfHours = (int)diffMilliSecond/ (1000*60*60);
		int noOfDays = noOfHours / 24;
		if(noOfDays > 0)
		{
			noOfHours = noOfHours - (noOfDays * 24);
		}
		
		int finalAmount = noOfDays * orderPaymentDTO.getRentPerDay() + noOfHours * orderPaymentDTO.getRentPerHour();
		
		return finalAmount;
	}
	
	
	private Invoice getInvoiceFromDTO(InvoiceDTO invoiceDTO) {
		InvoiceInfo invoiceInfo = new InvoiceInfo();
		invoiceInfo.setCreationTimestamp(invoiceDTO.getCreationTimeStamp());
		invoiceInfo.setDamagedCharges(invoiceDTO.getDamageCharges());
		invoiceInfo.setFinalAmount(invoiceDTO.getFinalAmount());
		invoiceInfo.setInvoiceId(invoiceDTO.getInvoiceID());
		invoiceInfo.setOrderId(invoiceDTO.getOrderID());
		invoiceInfo.setReturnAmount(invoiceDTO.getReturnDeposit());
		invoiceInfo.setWarehouseId(invoiceDTO.getWarehouseID());
		
		return invoiceInfo;
		
	}
	
	private void updateOrderPaymentForDepositPayment(CurrentOrderDTO currentOrderDTO)
	{
		//get bike for deposit amount
		BikeDTO bikeDTO = bikeDAO.getBike(currentOrderDTO.getBikeID());
		
		//get bike rent mapping for setting bike rent at that time
		BikeRentMappingDTO bikeRentMappingDTO = bikeRentMappingDAO.getBikeRentMapping(bikeDTO.getBikeTypeId());
		
		//add in order payment table
		OrderPaymentDTOImpl lOrderPaymentDTO = new OrderPaymentDTOImpl();
		lOrderPaymentDTO.setOrderID(currentOrderDTO.getOrderID());
		lOrderPaymentDTO.setDepositAmount(bikeDTO.getDepositAmount());
		lOrderPaymentDTO.setRentPerDay(bikeRentMappingDTO.getRentPerDay());
		lOrderPaymentDTO.setRentPerHour(bikeRentMappingDTO.getRentPerHour());
		String paymentReference = orderPaymentDAO.addOrderPayment(lOrderPaymentDTO);
	}
	
	private BikeStatusDTO getHiredBikeStatusDTO(BikeStatusDTO bikeStatusDTO)
	{
		BikeStatusDTOImpl lBikeStatusDTOImpl = new BikeStatusDTOImpl();
		BikeDTOImpl lBikeDTOImpl = new BikeDTOImpl();
		lBikeDTOImpl.setBikeId(bikeStatusDTO.getBikeId());
		lBikeStatusDTOImpl.setBikeDTO(lBikeDTOImpl);
		lBikeStatusDTOImpl.setLastServiceDate(bikeStatusDTO.getLastServiceDate());
		lBikeStatusDTOImpl.setStatus(BikeStatusType.RENTED_BIKE.getBikeStatus());
		
		return lBikeStatusDTOImpl;
	}
}
