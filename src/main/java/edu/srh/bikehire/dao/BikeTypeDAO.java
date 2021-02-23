package edu.srh.bikehire.dao;

import java.util.List;

import edu.srh.bikehire.dto.BikeTypeDTO;

public interface BikeTypeDAO {
	public BikeTypeDTO getBikeType(int pBikeType);
	
	public List<BikeTypeDTO> getBikeTypes();
	
	public int saveBikeType(BikeTypeDTO bikeType);
}
