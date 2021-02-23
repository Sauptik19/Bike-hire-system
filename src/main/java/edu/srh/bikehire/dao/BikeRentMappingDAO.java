package edu.srh.bikehire.dao;

import edu.srh.bikehire.dto.BikeRentMappingDTO;

public interface BikeRentMappingDAO {
	public BikeRentMappingDTO getBikeRentMapping(int pBikeTypeId);
	
	public boolean addBikeRentMapping(BikeRentMappingDTO pBikeRentMappingDTO);
	
	public boolean updateBikeRentMapping(BikeRentMappingDTO pBikeRentMappingDTO);
}
