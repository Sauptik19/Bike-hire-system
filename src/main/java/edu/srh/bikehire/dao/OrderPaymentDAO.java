package edu.srh.bikehire.dao;

import edu.srh.bikehire.dto.OrderPaymentDTO;

public interface OrderPaymentDAO {
	public OrderPaymentDTO getOrderPaymentByPaymentReference(String pPaymentReference);
	
	public OrderPaymentDTO getOrderPaymentByOrderId(int pOrderId);
	
	public String addOrderPayment(OrderPaymentDTO pOrderPaymentDTO);
}
