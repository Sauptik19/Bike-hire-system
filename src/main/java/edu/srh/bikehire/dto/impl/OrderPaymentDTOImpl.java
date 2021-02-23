package edu.srh.bikehire.dto.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.srh.bikehire.dto.CurrentOrderDTO;
import edu.srh.bikehire.dto.OrderPaymentDTO;

@Entity
@Table(name = "OrderPayment")
public class OrderPaymentDTOImpl implements OrderPaymentDTO {

	@Id
	@Column(name = "PaymentReference")
	private String paymentReference;
	
	@Column(name = "OrderID")
	private int orderID;
	
	@Column(name = "DepositAmount")
	private int depositAmount;
	
	@Column(name = "RentPerHour")
	private int rentPerHour;
	
	@Column(name = "RentPerDay")
	private int rentPerDay;
	
	public String getPaymentReference() {
		return paymentReference;
	}
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}
	public int getOrderID() {
		return orderID;
	}
	public int getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(int depositAmount) {
		this.depositAmount = depositAmount;
	}
	public int getRentPerHour() {
		return rentPerHour;
	}
	public void setRentPerHour(int rentPerHour) {
		this.rentPerHour = rentPerHour;
	}
	public int getRentPerDay() {
		return rentPerDay;
	}
	public void setRentPerDay(int rentPerDay) {
		this.rentPerDay = rentPerDay;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
}
