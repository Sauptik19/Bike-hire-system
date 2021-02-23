package edu.srh.bikehire.service.core.impl;

import edu.srh.bikehire.service.core.Warehouse;

public class WarehouseInfo implements Warehouse {
	private int warehouseId;
	private String name;
	private String location;
	private int storageCapacity;
	
	public int getWarehouseId() {
		return warehouseId;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public int getStorageCapacity() {
		return storageCapacity;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setStorageCapacity(int storageCapacity) {
		this.storageCapacity = storageCapacity;
	}

}
