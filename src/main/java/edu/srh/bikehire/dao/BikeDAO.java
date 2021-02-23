package edu.srh.bikehire.dao;


import java.util.List;

import edu.srh.bikehire.dto.BikeDTO;

public interface BikeDAO {
	public BikeDTO getBike(int pBikeId);
	
	public int addBike(BikeDTO pBike);
	
	public boolean updateBike(BikeDTO pBike);
	
	public List<BikeDTO> getBikeForWarehouseId(int pWarehouseId, boolean pSortPriceDescending);
	
	public List<BikeDTO> getBikeForBikeType(int pBikeTypeId, boolean pSortPriceDescending);
	
	public List<BikeDTO> getAllBikes(boolean pSortPriceDescending);
}
