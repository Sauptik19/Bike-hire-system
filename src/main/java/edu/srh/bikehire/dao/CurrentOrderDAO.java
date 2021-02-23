package edu.srh.bikehire.dao;

import java.util.Calendar;
import java.util.List;

import edu.srh.bikehire.dto.CurrentOrderDTO;

public interface CurrentOrderDAO {
	public CurrentOrderDTO getCurrentOrderByOrderId(int pOrderId);
	
	public CurrentOrderDTO getCurrentOrderByBikeId(int pBikeId);
	
	public List<CurrentOrderDTO> getCurrentOrderByUserId(int pUserId);
	
	public int addCurrentOrder(CurrentOrderDTO pCurrentOrderDTO);
	
	public boolean updateCurrentOrder(CurrentOrderDTO pCurrentOrderDTO);
	
	public boolean deleteCurrentOrder(CurrentOrderDTO pCurrentOrderDTO);
	
	public List<CurrentOrderDTO> getOrdersBasedOnPickUpDate(Calendar pFromCalendar, Calendar pToCalendar);
	
	public List<CurrentOrderDTO> getOrdersBasedOnDropOffDate(Calendar pFromCalendar, Calendar pToCalendar);
}
