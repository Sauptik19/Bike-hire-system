package edu.srh.bikehire.dao;

import java.util.List;

import edu.srh.bikehire.dto.OrderHistoryDTO;

public interface OrderHistoryDAO {
	public OrderHistoryDTO getOrderHistoryByOrderId(int pOrderId);
	
	public OrderHistoryDTO getOrderHistoryByInvoiceId(String pInvoiceId);
	
	public List<OrderHistoryDTO> getOrderHistoryByBikeId(int pBikeId);
	
	public List<OrderHistoryDTO> getOrderHistoryByUserId(int pUserId);
	
	public boolean addOrderHistory(OrderHistoryDTO pOrderHistoryDTO);
}
