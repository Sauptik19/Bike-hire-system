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

import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;

@Entity
@Table(name="BikeRentMapping")
public class BikeRentMappingDTOImpl implements BikeRentMappingDTO, Serializable {
	
	@Id
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "BikeTypeId")
	private BikeTypeDTOImpl bikeType;
	
	@Column(name = "RentPerHour")
	private int rentPerHour;
	
	@Column(name = "RentPerDay")
	private int rentPerDay;

	@Column(name = "LastModifiedTimeStamp")
	private Calendar lastModifiedTimeStamp;

	public BikeTypeDTOImpl getBikeType() {
		return bikeType;
	}
	public void setBikeType(BikeTypeDTOImpl bikeType) {
		this.bikeType = bikeType;
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
	public Calendar getLastModifiedTimeStamp() {
		return lastModifiedTimeStamp;
	}
	
	public void setLastModifiedTimeStamp(Calendar lastModifiedTimeStamp) {
		this.lastModifiedTimeStamp = lastModifiedTimeStamp;
	}
	
	public int getBikeTypeId() {
		return getBikeType().getBikeTypeId();
	}
	
	public void setBikeType(BikeTypeDTO pBikeTypeDTO) {
		this.bikeType = (BikeTypeDTOImpl) pBikeTypeDTO;
	}
		
}
