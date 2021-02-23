package edu.srh.bikehire.dto;


public interface OrderPaymentDTO {

	public String getPaymentReference();
	public int getOrderID();
	public int getDepositAmount();
	public int getRentPerHour();
	public int getRentPerDay();
	public void setOrderID(int orderID);
}
