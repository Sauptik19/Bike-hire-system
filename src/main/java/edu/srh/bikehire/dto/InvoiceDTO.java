package edu.srh.bikehire.dto;

import java.util.Calendar;

public interface InvoiceDTO {

	public String getInvoiceID();
	public int getOrderID();
	public void setOrderID(int pOrderId);
	public Calendar getCreationTimeStamp();
	public int getFinalAmount();
	public int getReturnDeposit();
	public int getDamageCharges();
	public int getWarehouseID();
	public void setWarehouseDTO(WareHouseDTO pWareHouseDTO);
}
