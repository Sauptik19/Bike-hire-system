package edu.srh.bikehire.dto.impl;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.srh.bikehire.dto.CurrentOrderDTO;
import edu.srh.bikehire.dto.InvoiceDTO;
import edu.srh.bikehire.dto.WareHouseDTO;

@Entity
@Table(name= "Invoice")
public class InvoiceDTOImpl implements InvoiceDTO, Serializable{

	@Id
	@Column(name = "InvoiceID")
	private String invoiceID;
	
	@JoinColumn(name = "OrderID")
	@Column(name = "OrderID")
	private int orderID;
	
	@Column(name = "CreationTimeStamp")
	private Calendar creationTimeStamp;
	
	@Column(name = "FinalAmount")
	private int finalAmount;
	
	@Column(name = "ReturnDeposit")
	private int returnDeposit;
	
	@Column(name = "DamageCharges")
	private int	damageCharges;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "WarehouseId")
	private WareHouseDTOImpl warehouseDTO;
	
	public String getInvoiceID() {
		return invoiceID;
	}
	
	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}
	
	public int getOrderID() {
		return orderID;
	}
	
	public Calendar getCreationTimeStamp() {
		return creationTimeStamp;
	}
	
	public void setCreationTimeStamp(Calendar creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
	
	public int getFinalAmount() {
		return finalAmount;
	}
	
	public void setFinalAmount(int finalAmount) {
		this.finalAmount = finalAmount;
	}
	
	public int getReturnDeposit() {
		return returnDeposit;
	}
	
	public void setReturnDeposit(int returnDeposit) {
		this.returnDeposit = returnDeposit;
	}
	
	public int getDamageCharges() {
		return damageCharges;
	}
	
	public void setDamageCharges(int damageCharges) {
		this.damageCharges = damageCharges;
	}
	
	public int getWarehouseID() {
		return warehouseDTO.getWarehouseId();
	}

	public WareHouseDTOImpl getWarehouseDTO() {
		return warehouseDTO;
	}

	public void setWarehouseDTO(WareHouseDTOImpl warehouseDTO) {
		this.warehouseDTO = warehouseDTO;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public void setWarehouseDTO(WareHouseDTO pWareHouseDTO)
	{
		this.warehouseDTO = (WareHouseDTOImpl) pWareHouseDTO;
	}
	
}
