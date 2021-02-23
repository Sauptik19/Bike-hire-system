package edu.srh.bikehire.dao;

import java.util.List;

import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeStatusDTO;

public interface BikeStatusDAO {
	public BikeStatusDTO getBikeStatus(int pBikeId);
	
	public boolean addBikeStatus(BikeStatusDTO pBikeStatus);
	
	public boolean updateBikeStatus(BikeStatusDTO pBikeStatus);
	
	public List<BikeDTO> getAllBikesBasedOnStatus(String pStatus, boolean sortPriceDescending);
	
	public long getBikeCount(String pStatus, int pBikeTypeId);
}
