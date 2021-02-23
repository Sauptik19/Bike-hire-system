package edu.srh.bikehire.dto.impl;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeStatusDTO;

@Entity
@Table(name="BikeStatus")
public class BikeStatusDTOImpl implements Serializable,BikeStatusDTO {
	
	@Id
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BikeId")
	private BikeDTOImpl bike;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="LastServiceDate")
	private Calendar lastServiceDate;
	
	@Column(name="LastModifiedDate")
	private Calendar lastModifiedDate;
	
	@Column(name="Manufacturer")
	private String Manufacturer;

	public BikeDTOImpl getBike() {
		return bike;
	}

	public void setBike(BikeDTOImpl bike) {
		this.bike = bike;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getLastServiceDate() {
		return lastServiceDate;
	}

	public void setLastServiceDate(Calendar lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	public Calendar getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Calendar lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public int getBikeId() {
		return getBike().getBikeId();
	}
	
	public void setBikeDTO(BikeDTO pBikeDTO)
	{
		this.bike = (BikeDTOImpl) pBikeDTO;
	}
}
