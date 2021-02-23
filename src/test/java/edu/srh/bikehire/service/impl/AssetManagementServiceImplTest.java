package edu.srh.bikehire.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import edu.srh.bikehire.dao.BikeRentMappingDAO;
import edu.srh.bikehire.dao.BikeStatusDAO;
import edu.srh.bikehire.dao.DAOFactory;
import edu.srh.bikehire.dao.DAOFactoryType;
import edu.srh.bikehire.dao.WarehouseDAO;
import edu.srh.bikehire.dashboard.BikeStatusType;
import edu.srh.bikehire.dto.BikeRentMappingDTO;
import edu.srh.bikehire.dto.BikeStatusDTO;
import edu.srh.bikehire.dto.WareHouseDTO;
import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.impl.BikeInfo;
import edu.srh.bikehire.service.core.impl.BikeRentInfo;
import edu.srh.bikehire.service.core.impl.BikeStatusInfo;
import edu.srh.bikehire.service.core.impl.BikeStockInfo;
import edu.srh.bikehire.service.core.impl.BikeTypeInfo;
import edu.srh.bikehire.service.core.impl.WarehouseInfo;

public class AssetManagementServiceImplTest {

	AssetManagementServiceImpl assetService = new AssetManagementServiceImpl();

	@Test
	public void testAddNewWarehouse() throws BikeHireSystemException {
		WarehouseInfo warehouseInfo = new WarehouseInfo();
		warehouseInfo.setName("Dossenheim Main Warehouse");
		warehouseInfo.setLocation("Dossenheim");
		warehouseInfo.setStorageCapacity(1000);
		int warehouseId = assetService.addNewWarehouse(warehouseInfo);
		assertTrue(warehouseId > 0);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewWarehouseInvalidStorageCapacity() throws BikeHireSystemException {
		WarehouseInfo warehouseInfo = new WarehouseInfo();
		warehouseInfo.setName("Dossenheim Main Warehouse");
		warehouseInfo.setLocation("Dossenheim");
		warehouseInfo.setStorageCapacity(-1);
		int warehouseId = assetService.addNewWarehouse(warehouseInfo);
	}

	@Test
	public void testUpdateWarehouse() throws BikeHireSystemException {
		WarehouseInfo warehouseInfo = new WarehouseInfo();
		warehouseInfo.setWarehouseId(1);
		warehouseInfo.setName("Heidelberg Main Warehouse");
		warehouseInfo.setLocation("Heidelberg");
		warehouseInfo.setStorageCapacity(100);
		boolean warehouseUpdateStatus = assetService.updateWarehouse(warehouseInfo);
		assertTrue(warehouseUpdateStatus);
	}

	@Test
	public void testAddNewBikeType() throws BikeHireSystemException {
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeType("sports bike");
		bikeTypeInfo.setAgeCategory("8");
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setTotalQuantity(100);
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setRentPerDay(23);
		bikeRentInfo.setRentPerHour(3);
		int bikeTypeId = assetService.addNewBikeType(bikeTypeInfo, bikeStockInfo, bikeRentInfo);
		assertTrue(bikeTypeId > 0);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeTypeInvalidBikeType() throws BikeHireSystemException {
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeType(null);
		bikeTypeInfo.setAgeCategory("8");
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setTotalQuantity(2);
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setRentPerDay(23);
		bikeRentInfo.setRentPerHour(3);
		int bikeTypeId = assetService.addNewBikeType(bikeTypeInfo, bikeStockInfo, bikeRentInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeTypeInvalidAgeCategory() throws BikeHireSystemException {
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeType("sports bike");
		bikeTypeInfo.setAgeCategory(null);
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setTotalQuantity(2);
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setRentPerDay(23);
		bikeRentInfo.setRentPerHour(3);
		int bikeTypeId = assetService.addNewBikeType(bikeTypeInfo, bikeStockInfo, bikeRentInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeTypeInvalidTotalQuantity() throws BikeHireSystemException {
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeType("sports bike");
		bikeTypeInfo.setAgeCategory("8");
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setTotalQuantity(-2);
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setRentPerDay(23);
		bikeRentInfo.setRentPerHour(3);
		int bikeTypeId = assetService.addNewBikeType(bikeTypeInfo, bikeStockInfo, bikeRentInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeTypeInvalidRentPerDay() throws BikeHireSystemException {
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeType("sports bike");
		bikeTypeInfo.setAgeCategory("8");
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setTotalQuantity(5);
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setRentPerDay(-23);
		bikeRentInfo.setRentPerHour(3);
		int bikeTypeId = assetService.addNewBikeType(bikeTypeInfo, bikeStockInfo, bikeRentInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeTypeInvalidRentPerHour() throws BikeHireSystemException {
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeType("sports bike");
		bikeTypeInfo.setAgeCategory("8");
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setTotalQuantity(7);
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setRentPerDay(23);
		bikeRentInfo.setRentPerHour(-3);
		int bikeTypeId = assetService.addNewBikeType(bikeTypeInfo, bikeStockInfo, bikeRentInfo);
	}

	@Test
	public void testAddNewBikeDetails() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Mountain BMW Bike");
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer("BMW");
		bikeInfo.setYearOfManufacturer(2017);
		bikeInfo.setWareHouseID(1);
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setManufacturer("BMW");
		bikeStatusInfo.setStatus("hired");
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		int bikeId = assetService.addNewBikeDetails(bikeInfo, bikeStatusInfo);
		assertTrue(bikeId > 0);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeDetailsInvalidBikeTitle() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle(null);
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer("BMW");
		bikeInfo.setYearOfManufacturer(2017);
		bikeInfo.setWareHouseID(1);
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setManufacturer("BMW");
		bikeStatusInfo.setStatus("hired");
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		assetService.addNewBikeDetails(bikeInfo, bikeStatusInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeDetailsInvalidDepositAmount() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(-300);
		bikeInfo.setManufacturer("Benz");
		bikeInfo.setYearOfManufacturer(2016);
		bikeInfo.setWareHouseID(1);
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setManufacturer("BMW");
		bikeStatusInfo.setStatus("hired");
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		assetService.addNewBikeDetails(bikeInfo, bikeStatusInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeDetailsInvalidManufacturer() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer(null);
		bikeInfo.setYearOfManufacturer(2016);
		bikeInfo.setWareHouseID(1);
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setManufacturer("BMW");
		bikeStatusInfo.setStatus("hired");
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		assetService.addNewBikeDetails(bikeInfo, bikeStatusInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeDetailsInvalidYearOfManufacturer() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer("Benz");
		bikeInfo.setYearOfManufacturer(18);
		bikeInfo.setWareHouseID(1);
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setManufacturer("BMW");
		bikeStatusInfo.setStatus("hired");
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		assetService.addNewBikeDetails(bikeInfo, bikeStatusInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeDetailsInvalidStatusManufacturer() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer("Benz");
		bikeInfo.setYearOfManufacturer(2018);
		bikeInfo.setWareHouseID(1);
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setManufacturer(null);
		bikeStatusInfo.setStatus("hired");
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		assetService.addNewBikeDetails(bikeInfo, bikeStatusInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testAddNewBikeDetailsInvalidBikeStatus() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer("Benz");
		bikeInfo.setYearOfManufacturer(2018);
		bikeInfo.setWareHouseID(1);
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setManufacturer("Benz");
		bikeStatusInfo.setStatus(null);
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		assetService.addNewBikeDetails(bikeInfo, bikeStatusInfo);
	}

	@Test
	public void testUpdateBikeDetails() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeId(1);
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer("Benz");
		bikeInfo.setYearOfManufacturer(2018);
		bikeInfo.setWareHouseID(1);
		assertTrue(assetService.updateBikeDetails(bikeInfo));
	}

	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeDetailsInvalid() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeId(-9);
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer("Benz");
		bikeInfo.setYearOfManufacturer(2018);
		bikeInfo.setWareHouseID(1);
		assetService.updateBikeDetails(bikeInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeDetailsInvalidDepositAmount() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeId(9);
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(-300);
		bikeInfo.setManufacturer("Benz");
		bikeInfo.setYearOfManufacturer(2018);
		bikeInfo.setWareHouseID(1);
		assetService.updateBikeDetails(bikeInfo);
	}


	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeDetailsInvalidWareHouseID() throws BikeHireSystemException {
		BikeInfo bikeInfo = new BikeInfo();
		bikeInfo.setBikeId(9);
		bikeInfo.setBikeTypeId(1);
		bikeInfo.setBikeTitle("Benz Bikes");
		bikeInfo.setDepositAmount(300);
		bikeInfo.setManufacturer("Benz");
		bikeInfo.setYearOfManufacturer(2018);
		bikeInfo.setWareHouseID(-1);
		assetService.updateBikeDetails(bikeInfo);
	}

	@Test
	public void testUpdateBikeRent() throws BikeHireSystemException {
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setBikeTypeId(1);
		bikeRentInfo.setRentPerDay(30);
		bikeRentInfo.setRentPerHour(5);
		boolean updateBikeRent = assetService.updateBikeRent(bikeRentInfo);
		assertTrue(updateBikeRent);
	}

	@Test
	public void testUpdateBikeRentandValidate() throws BikeHireSystemException {
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setBikeTypeId(1);
		bikeRentInfo.setRentPerDay(30);
		bikeRentInfo.setRentPerHour(5);
		boolean updateBikeRent = assetService.updateBikeRent(bikeRentInfo);
		BikeRentMappingDAO bikeRentDAO = DAOFactory.getDAOFactory(DAOFactoryType.JPADAOFACTORY).getBikeRentMappingDAO();
		BikeRentMappingDTO returnedBikeRent = bikeRentDAO.getBikeRentMapping(1);
		assertEquals(30, returnedBikeRent.getRentPerDay());
	}

	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeRentInvalidRentPerDay() throws BikeHireSystemException {
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setBikeTypeId(1);
		bikeRentInfo.setRentPerDay(-30);
		bikeRentInfo.setRentPerHour(5);
		assetService.updateBikeRent(bikeRentInfo);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeRentInvalidRentPerHour() throws BikeHireSystemException {
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setBikeTypeId(1);
		bikeRentInfo.setRentPerDay(30);
		bikeRentInfo.setRentPerHour(-5);
		assetService.updateBikeRent(bikeRentInfo);
	}

	@Test
	public void testUpdateBikeStock() throws BikeHireSystemException {
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setBikeTypeId(1);
		bikeStockInfo.setTotalQuantity(999);
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeTypeId(1);
		bikeTypeInfo.setBikeType("sports bike");
		bikeTypeInfo.setAgeCategory("8");
		boolean updateBikeStock = assetService.updateBikeStock(bikeStockInfo, bikeTypeInfo);
		assertTrue(updateBikeStock);
	}

	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeStockInvalidBikeTypeId() throws BikeHireSystemException {
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setBikeTypeId(-1);
		bikeStockInfo.setTotalQuantity(999);
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeTypeId(-1);
		bikeTypeInfo.setBikeType("sports bike");
		bikeTypeInfo.setAgeCategory("8");
		assetService.updateBikeStock(bikeStockInfo, bikeTypeInfo);

	}

	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeStockInvalidTotalQuantity() throws BikeHireSystemException {
		BikeStockInfo bikeStockInfo = new BikeStockInfo();
		bikeStockInfo.setBikeTypeId(1);
		bikeStockInfo.setTotalQuantity(-999);
		BikeTypeInfo bikeTypeInfo = new BikeTypeInfo();
		bikeTypeInfo.setBikeTypeId(1);
		bikeTypeInfo.setBikeType("sports bike");
		bikeTypeInfo.setAgeCategory("8");
		assetService.updateBikeStock(bikeStockInfo, bikeTypeInfo);

	}

	@Test
	public void testUpdateBikeStatus() throws BikeHireSystemException {
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setBikeId(2);
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		bikeStatusInfo.setManufacturer("Audi");
		bikeStatusInfo.setStatus("available");
		boolean updateBikeStatus = assetService.updateBikeStatus(bikeStatusInfo);
		assertTrue(updateBikeStatus);
	}

	@Test
	public void testUpdateBikeStatusandValidate() throws BikeHireSystemException {
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setBikeId(2);
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		bikeStatusInfo.setManufacturer("Benz");
		bikeStatusInfo.setStatus(BikeStatusType.RENTED_BIKE.getBikeStatus());
		boolean updateBikeStatus = assetService.updateBikeStatus(bikeStatusInfo);
		BikeStatusDAO bikeStatusDAO = DAOFactory.getDAOFactory(DAOFactoryType.JPADAOFACTORY).getBikeStatusDAO();
		BikeStatusDTO returnedBikeStatus = bikeStatusDAO.getBikeStatus(2);
		assertEquals(BikeStatusType.RENTED_BIKE.getBikeStatus(), returnedBikeStatus.getStatus());
	}

	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeStatusInvalidBikeId() throws BikeHireSystemException {
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setBikeId(-2);
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		bikeStatusInfo.setManufacturer("Audi");
		bikeStatusInfo.setStatus(BikeStatusType.AVALIABLE_BIKE.getBikeStatus());
		assetService.updateBikeStatus(bikeStatusInfo);
	} 

	@Test(expected = BikeHireSystemException.class)
	public void testUpdateBikeStatusInvalidStatus() throws BikeHireSystemException {
		BikeStatusInfo bikeStatusInfo = new BikeStatusInfo();
		bikeStatusInfo.setBikeId(2);
		bikeStatusInfo.setLastServiceDate(Calendar.getInstance());
		bikeStatusInfo.setManufacturer("BMW");
		bikeStatusInfo.setStatus(null);
		assetService.updateBikeStatus(bikeStatusInfo);
	}
}
