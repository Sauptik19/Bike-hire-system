package edu.srh.bikehire.console;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.OrderService;
import edu.srh.bikehire.service.core.Entity;
import edu.srh.bikehire.service.core.Order;
import edu.srh.bikehire.service.core.OrderHistory;
import edu.srh.bikehire.service.impl.OrderServiceImpl;

public class OrdersUI {
	private Entity loggedInUser;
	
	public OrdersUI(Entity loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}
	
	public int showOrders(Scanner sc) throws BikeHireSystemException
	{
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		List<Order> orders = orderServiceImpl.getCurrentOrdersForUser(loggedInUser.getUserId());
		if(orders == null || orders.isEmpty())
		{			
			System.out.println("No current order for your account.");
		}
		else
		{	
			for(Order order : orders)
			{				
				System.out.println("Current Order : ");
				System.out.println("Order Id : " + order.getOrderId());
				System.out.println("Bike Id : " + order.getBikeId());
				System.out.println("Order Mode : " + order.getOrderMode());
				System.out.println("Booking time : " + ConsoleUtil.getStringForDate(order.getBookingTimestamp()));
				System.out.println("------------------------------------------------------");
			}
			
			//Cancel order
			System.out.println("Do you want to cancel any order? (y/n)");
			String input = sc.nextLine(); 
			if(input.equalsIgnoreCase("y"))
			{
				System.out.println("Please enter order id : ");
				int orderId = sc.nextInt();
				sc.nextLine();
				Order lCurrentOrder = null;
				for(int i =0 ; i< orders.size() ; i++)
				{
					Order lTempOrder = orders.get(i);
					if(lTempOrder.getOrderId() == orderId)
					{
						lCurrentOrder = lTempOrder;
						break; 
					}
				}
				
				if(lCurrentOrder == null)
				{
					System.out.println("Please enter valid order id.");
					return this.showOrders(sc);
				}
				
				if(Calendar.getInstance().before(lCurrentOrder.getPickupTimestamp()))
				{
					System.out.println("Do you want to cancel order id "+lCurrentOrder.getOrderId()+ " ? (y/n)");
					String cancelInput = sc.nextLine(); 
					if(cancelInput.equalsIgnoreCase("y"))
					{						
						orderServiceImpl.cancelOrder(lCurrentOrder.getOrderId());
						System.out.println("Order cancelled successfully!");
					}
				}
				else
				{
					System.out.println("You cannot cancel this order as bike has already been rented. Contact customer support.");
					LandingUIForCustomer uiCustomer = new LandingUIForCustomer(loggedInUser);
					return uiCustomer.showMenu(sc);
				}
			}
			
		}
		
		System.out.println("Do you want to view previous order history? (y/n)");
		String input = sc.nextLine(); 
		if(input.equalsIgnoreCase("y"))
		{
			return showOrderHistory(sc, orderServiceImpl);
		}
		else
		{
			LandingUIForCustomer landingUI = new LandingUIForCustomer(loggedInUser);
			return landingUI.showMenu(sc);
		}
		
	}
	
	private int showOrderHistory(Scanner sc, OrderService orderServiceImpl) throws BikeHireSystemException
	{
		List<OrderHistory> orderHistories = orderServiceImpl.getOrderHistory(loggedInUser.getUserId());
		if(orderHistories == null || orderHistories.isEmpty())
		{
			System.out.println("No previous orders!");
			LandingUIForCustomer landingUI = new LandingUIForCustomer(loggedInUser);
			return landingUI.showMenu(sc);
		}
		
		System.out.println("Number of orders : " + orderHistories.size());
		System.out.println("-------------------------------------------------");
		for(OrderHistory orderHistory : orderHistories)
		{
			System.out.println("Order Id : " + orderHistory.getOrderId());
			System.out.println("Bike Id : " + orderHistory.getBikeId());
			System.out.println("Order Status : " + orderHistory.getOrderStatus());
			System.out.println("Booking time : " + ConsoleUtil.getStringForDate(orderHistory.getBookingTimeStamp()));
			System.out.println("-------------------------------------------------");
		}
		return 1;
	}
}
