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

import edu.srh.bikehire.dto.BikeStockDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;

@Entity
@Table(name="BikeStock")
public class BikeStockDTOImpl implements Serializable, BikeStockDTO {
	
	@Id
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BikeTypeId")
	private BikeTypeDTOImpl bikeType;
	
	@Column(name="TotalQuantity")
	private long totalQuantity;
	
	@Column(name="CreationTimeStamp")
	private Calendar creationTimeStamp;
	
	@Column(name="LastModifiedTimeStamp")
	private Calendar lastModifiedTimeStamp;

	public BikeTypeDTOImpl getBikeType() {
		return bikeType;
	}

	public void setBikeType(BikeTypeDTOImpl bikeType) {
		this.bikeType = bikeType;
	}

	public long getTotalQuantity() {
		return totalQuantity;
	}

	public Calendar getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public Calendar getLastModifiedTimeStamp() {
		return lastModifiedTimeStamp;
	}

	public void setTotalQuantity(long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public void setCreationTimeStamp(Calendar creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	public void setLastModifiedTimeStamp(Calendar lastModifiedTimeStamp) {
		this.lastModifiedTimeStamp = lastModifiedTimeStamp;
	}

	public int getBikeTypeId() {
		return getBikeType().getBikeTypeId();
	}
	
	public void setBikeTypeDTO(BikeTypeDTO pBikeTypeDTO)
	{
		this.bikeType = (BikeTypeDTOImpl) pBikeTypeDTO;
	}
}
