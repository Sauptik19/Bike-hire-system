create schema jpa_db;

use jpa_db;

CREATE TABLE `User` (
	`ID` int NOT NULL AUTO_INCREMENT,
	`FirstName` varchar(100) NOT NULL,
	`LastName` varchar(100) NOT NULL,
	`Gender` varchar(50) NOT NULL,
	`DOB` TIMESTAMP NOT NULL,
	`Address` varchar(200),
	`Photo` mediumblob,
	`EmailId` varchar(200) NOT NULL UNIQUE,
	`PhoneNo` varchar(20) NOT NULL,
	`IdentityProof` mediumblob NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Credentials` (
	`UserID` int NOT NULL,
	`UserName` varchar(200) NOT NULL,
	`PasswordSalt` varchar(200) NOT NULL,
	`PasswordHash` varchar(256) NOT NULL,
	`LastModifiedTimeStamp` TIMESTAMP NOT NULL,
	UNIQUE KEY Credentials_uk (UserID, UserName)
);

CREATE TABLE `UserAccount` (
	`ID` int NOT NULL,
	`UserName` varchar(200) NOT NULL,
	`Role` varchar(50) NOT NULL,
	`AccountStatus` varchar(20) NOT NULL,
	`CreationTimeStamp` TIMESTAMP NOT NULL,
	`LastModifiedTimeStamp` TIMESTAMP NOT NULL,
	PRIMARY KEY (`UserName`)
);

CREATE TABLE `BikeType` (
	`BikeTypeId` int NOT NULL AUTO_INCREMENT,
	`Type` varchar(100) NOT NULL,
	`AgeCategory` varchar(100) NOT NULL,
	`CreationTimeStamp` TIMESTAMP NOT NULL,
	PRIMARY KEY (`BikeTypeId`)
);

CREATE TABLE `BikeStock` (
	`BikeTypeId` int NOT NULL,
	`TotalQuantity` bigint NOT NULL,
	`CreationTimeStamp` TIMESTAMP NOT NULL,
	`LastModifiedTimeStamp` TIMESTAMP NOT NULL,
	UNIQUE KEY BikeStock_uk (BikeTypeId)
);

CREATE TABLE `Bike` (
	`BikeId` int NOT NULL AUTO_INCREMENT,
	`BikeTypeId` int NOT NULL,
	`Manufacturer` varchar(200) NOT NULL,
	`YearOfManufacture` int(4) NOT NULL,
	`BikeTitle` varchar(200) NOT NULL,
	`WareHouseID` int NOT NULL,
	`DepositAmount` int(10) NOT NULL,
	`CreationTimeStamp` TIMESTAMP NOT NULL,
	PRIMARY KEY (`BikeId`)
);

CREATE TABLE `WareHouse` (
	`WareHouseID` int NOT NULL AUTO_INCREMENT,
	`Name` varchar(200) NOT NULL,
	`Location` varchar(200) NOT NULL,
	`StorageCapacity` int(8) NOT NULL,
	`CreationTimeStamp` TIMESTAMP NOT NULL,
	`LastModifiedTimeStamp` TIMESTAMP NOT NULL,
	PRIMARY KEY (`WareHouseID`)
);

CREATE TABLE `BikeRentMapping` (
	`BikeTypeId` int NOT NULL,
	`RentPerHour` int(10) NOT NULL,
	`RentPerDay` int(10) NOT NULL,
	`LastModifiedTimeStamp` TIMESTAMP NOT NULL,
	UNIQUE KEY BikeRentMapping_uk (BikeTypeId)
);

CREATE TABLE `BikeStatus` (
	`BikeId` int NOT NULL,
	`Status` varchar(50) NOT NULL,
	`LastServiceDate` TIMESTAMP NOT NULL,
	`LastModifiedDate` TIMESTAMP NOT NULL,
	`Manufacturer` varchar(200) NOT NULL,
	UNIQUE KEY BikeStatus_uk (BikeId)
);

CREATE TABLE `CurrentOrder` (
	`OrderID` int NOT NULL AUTO_INCREMENT,
	`UserID` int NOT NULL,
	`BikeID` int NOT NULL,
	`BookingTimeStamp` TIMESTAMP NOT NULL,
	`PickupTimeStamp` TIMESTAMP NOT NULL,
	`DropOffTimeStamp` TIMESTAMP NOT NULL,
	`ActualDropOffTimeStamp` TIMESTAMP NOT NULL,
	`OrderMode` varchar(20) NOT NULL,
	PRIMARY KEY (`OrderID`),
	UNIQUE KEY CurrentOrder_uk (BikeID)
);

CREATE TABLE `OrderPayment` (
	`PaymentReference` varchar(100) NOT NULL,
	`OrderID` int NOT NULL,
	`DepositAmount` int(10) NOT NULL,
	`RentPerHour` int(10) NOT NULL,
	`RentPerDay` int(10) NOT NULL,
	PRIMARY KEY (`PaymentReference`)
);

CREATE TABLE `Invoice` (
	`InvoiceID` varchar(100) NOT NULL,
	`OrderID` int NOT NULL,
	`CreationTimeStamp` TIMESTAMP NOT NULL,
	`FinalAmount` int(10) NOT NULL,
	`ReturnDeposit` int(10) NOT NULL,
	`DamageCharges` int(10) NOT NULL,
	`WarehouseId` int NOT NULL,
	PRIMARY KEY (`InvoiceID`)
);

CREATE TABLE `OrderHistory` (
	`OrderID` int NOT NULL,
	`InvoiceID` varchar(100) NULL,
	`UserID` int NOT NULL,
	`BikeID` int NOT NULL,
	`OrderStatus` varchar(50) NOT NULL,
	`BookingTimeStamp` TIMESTAMP NOT NULL,
	`PickupTimeStamp` TIMESTAMP NOT NULL,
	`ReturnedTimeStamp` TIMESTAMP NOT NULL
);

ALTER TABLE `Credentials` ADD CONSTRAINT `Credentials_fk0` FOREIGN KEY (`UserID`) REFERENCES `User`(`ID`);

ALTER TABLE `Credentials` ADD CONSTRAINT `Credentials_fk1` FOREIGN KEY (`UserName`) REFERENCES `UserAccount`(`UserName`);

ALTER TABLE `UserAccount` ADD CONSTRAINT `UserAccount_fk0` FOREIGN KEY (`ID`) REFERENCES `User`(`ID`);

ALTER TABLE `BikeStock` ADD CONSTRAINT `BikeStock_fk0` FOREIGN KEY (`BikeTypeId`) REFERENCES `BikeType`(`BikeTypeId`);

ALTER TABLE `Bike` ADD CONSTRAINT `Bike_fk0` FOREIGN KEY (`BikeTypeId`) REFERENCES `BikeType`(`BikeTypeId`);

ALTER TABLE `Bike` ADD CONSTRAINT `Bike_fk1` FOREIGN KEY (`WareHouseID`) REFERENCES `WareHouse`(`WareHouseID`);

ALTER TABLE `BikeRentMapping` ADD CONSTRAINT `BikeRentMapping_fk0` FOREIGN KEY (`BikeTypeId`) REFERENCES `BikeType`(`BikeTypeId`);

ALTER TABLE `BikeStatus` ADD CONSTRAINT `BikeStatus_fk0` FOREIGN KEY (`BikeId`) REFERENCES `Bike`(`BikeId`);

ALTER TABLE `CurrentOrder` ADD CONSTRAINT `CurrentOrder_fk0` FOREIGN KEY (`UserID`) REFERENCES `User`(`ID`);

ALTER TABLE `CurrentOrder` ADD CONSTRAINT `CurrentOrder_fk1` FOREIGN KEY (`BikeID`) REFERENCES `Bike`(`BikeId`);

ALTER TABLE `Invoice` ADD CONSTRAINT `Invoice_fk0` FOREIGN KEY (`WarehouseId`) REFERENCES `WareHouse`(`WareHouseID`);

ALTER TABLE `OrderHistory` ADD CONSTRAINT `OrderHistory_fk0` FOREIGN KEY (`UserID`) REFERENCES `User`(`ID`);

ALTER TABLE `OrderHistory` ADD CONSTRAINT `OrderHistory_fk1` FOREIGN KEY (`BikeID`) REFERENCES `Bike`(`BikeId`);

-- Daily - Weekly -Monthly rented bikes
CREATE VIEW RENTEDBIKEINFO 
AS SELECT CO.ORDERID ORDERID, B.BIKEID BIKEID, BT.TYPE BIKETYPE, B.BIKETITLE BIKENAME, U.ID USERID, U.EMAILID USEREMAIL, B.DEPOSITAMOUNT DEPOSIT, BS.LASTMODIFIEDDATE MODIFIEDTIME 
FROM BIKE B
INNER JOIN BIKESTATUS BS ON BS.BIKEID = B.BIKEID
INNER JOIN CURRENTORDER CO ON CO.BIKEID = B.BIKEID
INNER JOIN USER U ON U.ID = CO.USERID
INNER JOIN BIKETYPE BT ON BT.BIKETYPEID = B.BIKETYPEID
WHERE BS.STATUS = 'hired';

-- Daily - Weekly - Monthly invoice generated - Invoice id, user id, bike type name, final amount, damage charges, invoice creation date
CREATE VIEW INVOICEINFO
AS SELECT IV.INVOICEID INVOICEID, IV.CREATIONTIMESTAMP MODIFIEDTIME, OH.USERID USERID, BT.TYPE BIKETYPE, IV.FINALAMOUNT AMOUNTPAID, IV.DAMAGECHARGES DAMAGECHARGES 
FROM INVOICE IV
INNER JOIN ORDERHISTORY OH ON OH.ORDERID = IV.ORDERID
INNER JOIN BIKE B ON B.BIKEID = OH.BIKEID
INNER JOIN BIKETYPE BT ON B.BIKETYPEID = BT.BIKETYPEID;