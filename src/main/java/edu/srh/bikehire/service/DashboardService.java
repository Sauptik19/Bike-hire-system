package edu.srh.bikehire.service;

import java.util.Calendar;
import java.util.List;

import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.OrderAppointment;

/**
 *	This class provides services related to dashboard functionality. 
 *
 */
public interface DashboardService{

	/**
	 * This method returns number of bikes based on bike status and type of bike.
	 * @param statusType
	 * @param bikeTypeId
	 * @return
	 * @throws BikeHireSystemException
	 */
	public long getBikeCount(BikeStatusType statusType,  int bikeTypeId ) throws BikeHireSystemException;

	/**
	 * This method returns all upcoming appointments. This method also provided pick-up and drop-off upcoming appointments.
	 * Upcoming appointments are calculated for next 5 days of query date.
	 * @param queryDate
	 * @param isPickUpAppointment
	 * @return - Returns empty list if no upcoming appointments.
	 * @throws BikeHireSystemException
	 */
    public List<OrderAppointment> getUpcomingAppointments(Calendar queryDate, boolean isPickUpAppointment) throws BikeHireSystemException;


}
