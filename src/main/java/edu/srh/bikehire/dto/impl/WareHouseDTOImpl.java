package edu.srh.bikehire.dto.impl;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.srh.bikehire.dto.WareHouseDTO;

@Entity
@Table(name = "WareHouse")
public class WareHouseDTOImpl implements WareHouseDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WareHouseId")
	private int warehouseId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Location")
	private String location;
	
	@Column(name = "StorageCapacity")
	private int storageCapacity;
	
	@Column(name = "CreationTimeStamp")
	private Calendar creationTimeStamp;
	
	@Column(name = "LastModifiedTimeStamp")
	private Calendar lastmodifiedTimeStamp;

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(int storageCapacity) {
		this.storageCapacity = storageCapacity;
	}

	public Calendar getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(Calendar creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	public Calendar getLastmodifiedTimeStamp() {
		return lastmodifiedTimeStamp;
	}

	public void setLastmodifiedTimeStamp(Calendar lastmodifiedTimeStamp) {
		this.lastmodifiedTimeStamp = lastmodifiedTimeStamp;
	}
}
