package edu.srh.bikehire.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.srh.bikehire.dao.BikeDAO;
import edu.srh.bikehire.dao.BikeRentMappingDAO;
import edu.srh.bikehire.dao.BikeStatusDAO;
import edu.srh.bikehire.dao.BikeTypeDAO;
import edu.srh.bikehire.dao.DAOFactory;
import edu.srh.bikehire.dao.DAOFactoryType;
import edu.srh.bikehire.dao.WarehouseDAO;
import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.dto.WareHouseDTO;
import edu.srh.bikehire.dto.impl.BikeStatusDTOImpl;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.exception.util.ExceptionUtil;
import edu.srh.bikehire.service.BikeService;

public class BikeServiceImpl implements BikeService {

	private static final Logger LOG = LogManager.getLogger(BikeServiceImpl.class);
	
	private BikeDAO bikeDAO;
	
	private BikeStatusDAO bikeStatusDAO;
	
	private BikeTypeDAO bikeTypeDAO;
	
	private WarehouseDAO warehouseDAO;
	
	private BikeRentMappingDAO bikeRentMappingDAO;
	
	private DAOFactory daoFactory;
	
	public BikeServiceImpl()
	{
		daoFactory = DAOFactory.getDAOFactory(DAOFactoryType.JPADAOFACTORY);
		bikeDAO = daoFactory.getBikeDAO();
		bikeTypeDAO = daoFactory.getBikeTypeDAO();
		bikeStatusDAO = daoFactory.getBikeStatusDAO();
		warehouseDAO = daoFactory.getWarehouseDAO();
		bikeRentMappingDAO = daoFactory.getBikeRentMappingDAO();
	}

	public List<BikeDTO> getAllBikes(boolean sortPriceDescending) throws BikeHireSystemException {
		LOG.info("getAllBikes : Start");
		try
		{			
			List<BikeDTO> allBikes = bikeDAO.getAllBikes(sortPriceDescending);
			LOG.info("getAllBikes : End");
			return allBikes;
		}
		catch(Throwable throwable)
		{
			LOG.error("getAllBikes : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public BikeDTO getBikeByID(int id) throws BikeHireSystemException {
		LOG.info("getBikeByID : Start");
		try
		{
			BikeDTO bikeDTO = bikeDAO.getBike(id);
			LOG.info("getBikeByID : End");
			return bikeDTO;
		}
		catch(Throwable throwable)
		{
			LOG.error("getBikeByID : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public void updateBikeStatus(int id, BikeStatusType bikeStatusType) throws BikeHireSystemException {
		LOG.info("updateBikeStatus : Start");
		try
		{
			daoFactory.beginTransaction();
			BikeDTO bikeDTO = bikeDAO.getBike(id);
			if(bikeDTO == null)
			{
				//ERRORMESSAGE: Bike Data Transfer Object Not Found.
				throw new BikeHireSystemException(10082);
			}
			
			BikeStatusDTOImpl bikeStatusDTOImpl = new BikeStatusDTOImpl();
			bikeStatusDTOImpl.setBikeDTO(bikeDTO);
			if(BikeStatusType.UNDERMAINTAINCE_BIKE == bikeStatusType)
			{
				bikeStatusDTOImpl.setLastServiceDate(Calendar.getInstance());
			}
			bikeStatusDTOImpl.setStatus(bikeStatusType.getBikeStatus());
			
			bikeStatusDAO.updateBikeStatus(bikeStatusDTOImpl);
			daoFactory.commitTransaction();
			
			LOG.info("updateBikeStatus : bike status updated successfully.");
			LOG.info("updateBikeStatus : End");
		}
		catch(Throwable throwable)
		{
			daoFactory.rollbackTransaction();
			LOG.error("updateBikeStatus : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}

	public List<BikeDTO> getAllBikesBasedOnType(int bikeTypeId, boolean sortPriceDescending) throws BikeHireSystemException
	{
		LOG.info("getAllBikesBasedOnType : Start");
		try
		{			
			BikeTypeDTO bikeTypeDTO = bikeTypeDAO.getBikeType(bikeTypeId);
			if(bikeTypeDTO == null)
			{
				//ERRORMESSAGE: Bike Type Data Transfer Object Not Found.
				throw new BikeHireSystemException(10083);
			}
			
			List<BikeDTO> allBikes = bikeDAO.getBikeForBikeType(bikeTypeId, sortPriceDescending);
			LOG.info("getAllBikesBasedOnType : End");
			return allBikes;
		}
		catch(Throwable throwable)
		{
			LOG.error("getAllBikesBasedOnType : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	public List<BikeDTO> getAllBikesBasedOnWarehouse(int warehouseId, boolean sortPriceDescending) throws BikeHireSystemException
	{
		LOG.info("getAllBikesBasedOnWarehouse : Start");
		try
		{			
			WareHouseDTO warehouseDTO = warehouseDAO.getWarehouse(warehouseId);
			if(warehouseDTO == null)
			{
				//ERRORMESSAGE: WareHouse Data Transfer Object Not Found.
				throw new BikeHireSystemException(10084);
			}
			
			List<BikeDTO> allBikes = bikeDAO.getBikeForWarehouseId(warehouseId, sortPriceDescending);
			LOG.info("getAllBikesBasedOnWarehouse : End");
			return allBikes;
		}
		catch(Throwable throwable)
		{
			LOG.error("getAllBikesBasedOnWarehouse : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	public BikeTypeDTO getBikeTypeInfo(int bikeTypeId) throws BikeHireSystemException {
		LOG.info("getBikeTypeInfo : Start");
		try
		{			
			BikeTypeDTO bikeTypeDTO = bikeTypeDAO.getBikeType(bikeTypeId);
			LOG.info("getBikeTypeInfo : End");
			return bikeTypeDTO;
		}
		catch(Throwable throwable)
		{
			LOG.error("getBikeTypeInfo : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	public List<BikeTypeDTO> getBikeTypes() throws BikeHireSystemException
	{
		LOG.info("getBikeTypes : Start");
		try
		{			
			List<BikeTypeDTO> allBikeTypes = bikeTypeDAO.getBikeTypes();
			LOG.info("getBikeTypes : End");
			return allBikeTypes;
		}
		catch(Throwable throwable)
		{
			LOG.error("getBikeTypes : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	public List<BikeDTO> getAllBikesBasedOnStatus(BikeStatusType bikeStatusType, boolean sortPriceDescending) throws BikeHireSystemException
	{
		LOG.info("getAllBikesBasedOnStatus : Start");
		try
		{			
			List<BikeDTO> allBikes = bikeStatusDAO.getAllBikesBasedOnStatus(bikeStatusType.getBikeStatus(), sortPriceDescending); 
			LOG.info("getAllBikesBasedOnStatus : End");
			return allBikes;
		}
		catch(Throwable throwable)
		{
			LOG.error("getAllBikesBasedOnStatus : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	public BikeRentMappingDTO getBikeRent(int bikeId) throws BikeHireSystemException{
		LOG.info("getBikeRent : Start");
		try
		{			
			BikeRentMappingDTO bikeRentMappingDTO = bikeRentMappingDAO.getBikeRentMapping(bikeId);
			LOG.info("getBikeRent : End");
			return bikeRentMappingDTO;
		}
		catch(Throwable throwable)
		{
			LOG.error("getBikeRent : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
	public List<WareHouseDTO> getAllWarehouses() throws BikeHireSystemException{
		LOG.info("getAllWarehouses : Start");
		try
		{			
			List<WareHouseDTO> warehouses = warehouseDAO.getAllWarehouses();
			LOG.info("getAllWarehouses : End");
			return warehouses;
		}
		catch(Throwable throwable)
		{
			LOG.error("getAllWarehouses : " + throwable.getMessage(), throwable);
			throw ExceptionUtil.wrapThrowableToBHSException(throwable);
		}
	}
	
}
