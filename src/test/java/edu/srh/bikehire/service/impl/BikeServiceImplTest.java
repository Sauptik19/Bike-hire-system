package edu.srh.bikehire.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeDTO;
import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.dto.BikeStatusDTO;
import edu.srh.bikehire.dto.BikeTypeDTO;
import edu.srh.bikehire.dto.WareHouseDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.impl.BikeStatusInfo;

public class BikeServiceImplTest {
	
	BikeServiceImpl bikeServiceImplTest = new BikeServiceImpl();

	@Test
	public void testGetAllBikes() throws BikeHireSystemException
	{
		boolean sortDescending = true;
		List<BikeDTO> getBikes = bikeServiceImplTest.getAllBikes(sortDescending);
		assertNotNull(getBikes);
		assertTrue(getBikes.size() > 0);
	}

	@Test
	public void testGetBikeByID() throws BikeHireSystemException
	{
		BikeDTO BikeReturn = bikeServiceImplTest.getBikeByID(1);
		assertNotNull(BikeReturn);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testGetBikeByIDInvalid() throws BikeHireSystemException
	{
		BikeDTO BikeReturnInvalid = bikeServiceImplTest.getBikeByID(-1);
		
	}
	
	@Test
	public void testUpdateBikeStatus() throws BikeHireSystemException
	{
		BikeStatusType.AVALIABLE_BIKE.getBikeStatus();
		BikeStatusType.RENTED_BIKE.getBikeStatus();
		BikeStatusType.UNDERMAINTAINCE_BIKE.getBikeStatus();
		
	}
	
	@Test
	public void testGetAllBikesBasedOnType() throws BikeHireSystemException
	{	
	    boolean sortDescending = true;
		List<BikeDTO> basedOnType = bikeServiceImplTest.getAllBikesBasedOnType(1,sortDescending);
		
		
	}

	@Test
	public void testGetAllBikesBasedOnWarehouse() throws BikeHireSystemException
	{
		
		boolean sortPriceDescending = true;
		List<BikeDTO> bikesbasedonwarehouse = bikeServiceImplTest.getAllBikesBasedOnWarehouse(1, sortPriceDescending);
		assertNotNull(bikesbasedonwarehouse);
		assertTrue(bikesbasedonwarehouse.size() > 0);
	}

	
	@Test(expected = BikeHireSystemException.class)
	public void testGetAllBikesBasedOnWarehouseInvalid() throws BikeHireSystemException
	{
		
		boolean sortPriceDescending = false;
		List<BikeDTO> bikesbasedonwarehouse = bikeServiceImplTest.getAllBikesBasedOnWarehouse(-1, sortPriceDescending);
		
	}
	
	
	@Test
	public void testGetBikeTypeInfo() throws BikeHireSystemException
	{
		BikeTypeDTO biketypeinfo = bikeServiceImplTest.getBikeTypeInfo(1);
		assertNotNull(biketypeinfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testGetBikeTypeInfoinvalid() throws BikeHireSystemException
	{
		BikeTypeDTO biketypeinfo = bikeServiceImplTest.getBikeTypeInfo(-1);
		
	}
	
	
	@Test
	public void testGetBikeTypes() throws BikeHireSystemException
	{
		List<BikeTypeDTO> biketype = bikeServiceImplTest.getBikeTypes();
		assertNotNull(biketype);
		assertTrue(biketype.size() > 0);
	}

	@Test
	public void testGetAllBikesBasedOnStatus() throws BikeHireSystemException
	{
		
		List<BikeDTO> bikestatus = bikeServiceImplTest.getAllBikesBasedOnStatus(BikeStatusType.AVALIABLE_BIKE, true);
		assertNotNull(bikestatus);
		assertTrue(bikestatus.size() > 0);
		
		List<BikeDTO> bikestatusrented = bikeServiceImplTest.getAllBikesBasedOnStatus(BikeStatusType.RENTED_BIKE, true);
		assertNotNull(bikestatusrented);
		assertTrue(bikestatusrented.size() > 0);
		
		List<BikeDTO> bikestatusunderservice = bikeServiceImplTest.getAllBikesBasedOnStatus(BikeStatusType.UNDERMAINTAINCE_BIKE, true );
		assertNotNull(bikestatusunderservice);
		assertTrue(bikestatusunderservice.size() > 0);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testGetAllBikesBasedOnStatusinvalid() throws BikeHireSystemException
	{
		
		List<BikeDTO> bikestatus = bikeServiceImplTest.getAllBikesBasedOnStatus(BikeStatusType.AVALIABLE_BIKE, false);
		assertNotNull(bikestatus);
		assertTrue(bikestatus.size() > 0);
		
		List<BikeDTO> bikestatusrented = bikeServiceImplTest.getAllBikesBasedOnStatus(BikeStatusType.RENTED_BIKE, false);
		assertNotNull(bikestatusrented);
		assertTrue(bikestatusrented.size() > 0);
		
		List<BikeDTO> bikestatusunderservice = bikeServiceImplTest.getAllBikesBasedOnStatus(BikeStatusType.UNDERMAINTAINCE_BIKE, false );
		assertNotNull(bikestatusunderservice);
		assertTrue(bikestatusunderservice.size() > 0);
	}

	@Test
	public void testGetBikeRent() throws BikeHireSystemException
	{
		BikeRentMappingDTO bikerent = bikeServiceImplTest.getBikeRent(1);
		assertNotNull(bikerent);
	}
	
	@Test(expected = BikeHireSystemException.class)
	public void testGetBikeRentinvalid() throws BikeHireSystemException
	{
		BikeRentMappingDTO bikerent = bikeServiceImplTest.getBikeRent(-1);
	}
	

	@Test
	public void testGetAllWarehouses() throws BikeHireSystemException
	{
		List<WareHouseDTO> allWarehouses = bikeServiceImplTest.getAllWarehouses();
		assertNotNull(allWarehouses);
		assertTrue(allWarehouses.size() > 0);
		
	}

}
