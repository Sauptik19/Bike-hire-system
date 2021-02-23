package edu.srh.bikehire.service.core.impl;

import java.util.Calendar;

import edu.srh.bikehire.service.core.Invoice;

public class InvoiceInfo implements Invoice {
	
	private String invoiceId;
	
	private int orderId;
	
	private Calendar creationTimestamp;
	
	private int finalAmount;
	
	private int returnAmount;
	
	private int damagedCharges;
	
	private int warehouseId;

	public String getInvoiceId() {
		return invoiceId;
	}

	public int getOrderId() {
		return orderId;
	}

	public Calendar getCreationTimestamp() {
		return creationTimestamp;
	}

	public int getFinalAmount() {
		return finalAmount;
	}

	public int getReturnAmount() {
		return returnAmount;
	}

	public int getDamagedCharges() {
		return damagedCharges;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setCreationTimestamp(Calendar creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public void setFinalAmount(int finalAmount) {
		this.finalAmount = finalAmount;
	}

	public void setReturnAmount(int returnAmount) {
		this.returnAmount = returnAmount;
	}

	public void setDamagedCharges(int damagedCharges) {
		this.damagedCharges = damagedCharges;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

}
