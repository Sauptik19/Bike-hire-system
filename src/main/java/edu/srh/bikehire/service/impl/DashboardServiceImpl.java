package edu.srh.bikehire.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.BikeDAO;
import edu.srh.bikehire.dao.BikeStatusDAO;
import edu.srh.bikehire.dao.CurrentOrderDAO;
import edu.srh.bikehire.dao.DAOFactory;
import edu.srh.bikehire.dao.DAOFactoryType;
import edu.srh.bikehire.dao.UserDAO;
import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.CurrentOrderDTO;
import edu.srh.bikehire.dto.UserDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.exception.util.ExceptionUtil;
import edu.srh.bikehire.service.DashboardService;
import edu.srh.bikehire.service.core.OrderAppointment;
import edu.srh.bikehire.service.core.impl.UpcomingAppointment;

public class DashboardServiceImpl implements DashboardService {

	private static final Logger LOG = LogManager.getLogger(DashboardServiceImpl.class);
	
	private BikeDAO bikeDAO;

	private BikeStatusDAO bikeStatusDAO;

	private CurrentOrderDAO currentorderDAO;

	private UserDAO userDAO;

	private DAOFactory daoFactory;
	
	public DashboardServiceImpl() {
		daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.JPADAOFACTORY);
		bikeDAO = daoFactory.getBikeDAO();
		bikeStatusDAO = daoFactory.getBikeStatusDAO();
		currentorderDAO = daoFactory.getOrderDAO();
		userDAO = daoFactory.getUserDAO();
	}

	public long getBikeCount(BikeStatusType statusType, int bikeTypeId) throws BikeHireSystemException {
		LOG.info("getBikeCount : Start");
		try
		{			
			long count = bikeStatusDAO.getBikeCount(statusType.getBikeStatus(), bikeTypeId);
			LOG.info("getBikeCount : End");
			return count;
		}
		catch(Throwable throwable)
		{
			LOG.error("getBikeCount : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	
	public List<OrderAppointment> getUpcomingAppointments(Calendar queryDate, boolean isPickUpAppointment) throws BikeHireSystemException {
		LOG.info("getUpcomingAppointments : Start");
		try
		{
			Calendar l5DayAfter = Calendar.getInstance();
			l5DayAfter.add(Calendar.DATE, 5);
			List<CurrentOrderDTO> lUpcomingOrders = null;
			if(isPickUpAppointment)
			{			
				lUpcomingOrders = currentorderDAO.getOrdersBasedOnPickUpDate(queryDate, l5DayAfter);
			}
			else
			{
				lUpcomingOrders = currentorderDAO.getOrdersBasedOnDropOffDate(queryDate, l5DayAfter);
			}
			
			if(lUpcomingOrders == null)
			{
				return new ArrayList<OrderAppointment>();
			}
			
			LOG.info("getUpcomingAppointments : Upcoming appointments found.");
			List<UpcomingAppointment> lReturnList = new ArrayList<UpcomingAppointment>();
			for(CurrentOrderDTO lCurrentOrderDTO : lUpcomingOrders)
			{
				UpcomingAppointment lUpcomingAppointment = new UpcomingAppointment();
				lUpcomingAppointment.setOrderId(lCurrentOrderDTO.getOrderID());
				lUpcomingAppointment.setPickupTimestamp(lCurrentOrderDTO.getPickupTimeStamp());
				lUpcomingAppointment.setDropoffTimestamp(lCurrentOrderDTO.getDropOffTimeStamp());
				
				UserDTO lUserDTO = userDAO.getUser(lCurrentOrderDTO.getUserID());
				lUpcomingAppointment.setName(lUserDTO.getFirstName() + " " + lUserDTO.getLastName());
				
				lUpcomingAppointment.setBikeId(lCurrentOrderDTO.getBikeID());
				
				BikeDTO lBikeDTO = bikeDAO.getBike(lCurrentOrderDTO.getBikeID());
				lUpcomingAppointment.setBikeName(lBikeDTO.getBikeTitle());
				
				lReturnList.add(lUpcomingAppointment);
			}
			LOG.info("getUpcomingAppointments : End");
			return (List)lReturnList;
		}
		catch(Throwable throwable)
		{
			LOG.error("getUpcomingAppointments : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
}
