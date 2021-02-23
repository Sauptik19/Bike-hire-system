package edu.srh.bikehire.dao;

import edu.srh.bikehire.dto.BikeStockDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;

public interface BikeStockDAO {
	public BikeStockDTO getBikeStock(BikeTypeDTO bikeType);
	
	public boolean addBikeStock(BikeStockDTO bikeStock);
	
	public boolean updateBikeStock(BikeStockDTO bikeStock, BikeTypeDTO bikeType);
}
