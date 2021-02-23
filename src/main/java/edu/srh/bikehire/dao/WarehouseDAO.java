package edu.srh.bikehire.dao;

import java.util.List;

import edu.srh.bikehire.dto.WareHouseDTO;

public interface WarehouseDAO {
	public WareHouseDTO getWarehouse(int pWarehouseId);
	
	public int addWarehouse(WareHouseDTO pWarehouse);
	
	public boolean updateWarehouse(WareHouseDTO pWarehouse);
	
	public List<WareHouseDTO> getAllWarehouses();
}
