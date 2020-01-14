DROP SCHEMA IF EXISTS `FoodBusterApplication`;
CREATE SCHEMA IF NOT EXISTS `FoodBusterApplication` ;
USE FoodBusterApplication;
SET SQL_SAFE_UPDATES=0;


DROP TABLE IF EXISTS ViolationHistory;
DROP TABLE IF EXISTS InspectionHistory;
DROP TABLE IF EXISTS LiquorEstablishments;
DROP TABLE IF EXISTS FoodEstablishments;
DROP TABLE IF EXISTS FoodLicenses;
DROP TABLE IF EXISTS LiquorLicenses;
DROP TABLE IF EXISTS Addresses;
DROP TABLE IF EXISTS Establishments;
DROP TABLE IF EXISTS Licenses;
DROP TABLE IF EXISTS LicenseCategories;
DROP TABLE IF EXISTS Weather;
DROP TABLE IF EXISTS Outbreaks;


CREATE TABLE IF NOT EXISTS LicenseCategories (
	LicenseCategory VARCHAR(45),
 	LicenseCategoryDescription MEDIUMTEXT,
 	CONSTRAINT pk_LicenseCategories
		PRIMARY KEY (LicenseCategory)
);

CREATE TABLE IF NOT EXISTS Licenses (
	LicenseKey INT NOT NULL AUTO_INCREMENT,
	LicenseNumber VARCHAR(45),
	LicenseIssue DATETIME,
 	LicenseExpiration DATETIME,
	LicenseStatus ENUM('Active', 'Inactive'),
 	LicenseCategoryFK VARCHAR(45),
 	CONSTRAINT pk_LicenseKey 
		PRIMARY KEY (LicenseKey),
	CONSTRAINT fk_Licenses_LicenseCategoryFK
		FOREIGN KEY (LicenseCategoryFK)
        	REFERENCES LicenseCategories(LicenseCategory)
        	ON DELETE NO ACTION 
        	ON UPDATE CASCADE
#We want updates to follow through in case there is a change, but there shouldn't be one.
#We don't want to delete records if this FK is deleted, or even set null in order to keep records.
);

CREATE TABLE IF NOT EXISTS FoodLicenses (
	FoodLicenseKey INT NOT NULL AUTO_INCREMENT,
	LicenseFK INT,
    LicenseNumber VARCHAR(45),
 	CONSTRAINT pk_FoodLicenseKey 
		PRIMARY KEY (FoodLicenseKey),
	CONSTRAINT fk_FoodLicenses_LicenseFK
		FOREIGN KEY (LicenseFK)
        	REFERENCES Licenses(LicenseKey)
        	ON DELETE CASCADE 
			ON UPDATE CASCADE
#We want updates to follow through in case there is a change, but there shouldn't be one.
#In case a license is deleted, it makes sense for the subcategory to be deleted as well.
);

CREATE TABLE IF NOT EXISTS LiquorLicenses (
	LiquorLicenseKey INT NOT NULL AUTO_INCREMENT,
 	LiquorLicenseComments MEDIUMTEXT,
	LiquorLocationComments MEDIUMTEXT,
	LicenseFK INT,
	LicenseNumber VARCHAR(45),
 	CONSTRAINT pk_LiquorLicenseKey
		PRIMARY KEY (LiquorLicenseKey),
	CONSTRAINT fk_LiquorLicenses_LicenseFK
		FOREIGN KEY (LicenseFK)
        	REFERENCES Licenses(LicenseKey)
        	ON DELETE CASCADE 
			ON UPDATE CASCADE
#We want updates to follow through in case there is a change, but there shouldn't be one.
#In case a license is deleted, it makes sense for the subcategory to be deleted as well.
);

CREATE TABLE IF NOT EXISTS Establishments (
	EstablishmentKey INT NOT NULL AUTO_INCREMENT,
	EstablishmentName VarChar(255) NOT NULL,
 	EstablishmentOwner VARCHAR(255),
 	LicenseFK INT,
 	CONSTRAINT pk_EstablishmentKey
		PRIMARY KEY (EstablishmentKey),
	CONSTRAINT fk_Establishments_LicenseFK
		FOREIGN KEY (LicenseFK)
        	REFERENCES Licenses(LicenseKey)
			ON DELETE CASCADE 
			ON UPDATE CASCADE
#We want updates to follow through in case there is a change, but there shouldn't be one.
#We don't want to delete records if this FK is deleted, or even set null in order to keep records.
);

CREATE TABLE IF NOT EXISTS Addresses (
	EstablishmentKey INT NOT NULL AUTO_INCREMENT,
	Street VARCHAR(255) NOT NULL,
 	City VARCHAR(255) NOT NULL,
 	State CHAR(2) NOT NULL,
 	ZipCode INT NOT NULL,
	PropertyId INT,
	CONSTRAINT pk_Addresses
		PRIMARY KEY (EstablishmentKey),
	CONSTRAINT fk_Addresses
		FOREIGN KEY (EstablishmentKey)
		REFERENCES Establishments(EstablishmentKey)
);


CREATE TABLE IF NOT EXISTS LiquorEstablishments (
	LiquorEstablishmentKey INT NOT NULL AUTO_INCREMENT,
	EstablishmentFK INT,
	LiquorEstablishmentComments LONGTEXT,
 	TimeClose VARCHAR(45),
 	TimePatronsOffPremises VARCHAR(45),
 	Capacity INT,
 	CONSTRAINT pk_LiquorEstablishmentKey
		PRIMARY KEY (LiquorEstablishmentKey),
	CONSTRAINT fk_LiquorEstablishments_EstablishmentFK
		FOREIGN KEY (EstablishmentFK)
        	REFERENCES Establishments(EstablishmentKey)
        	ON DELETE CASCADE 
		ON UPDATE CASCADE
#We want updates to follow through in case there is a change, but there shouldn't be one.
#In case an establishment is deleted, it makes sense for the subcategory to be deleted as well.
);

CREATE TABLE IF NOT EXISTS FoodEstablishments (
	FoodEstablishmentKey INT NOT NULL AUTO_INCREMENT,
	EstablishmentFK INT NOT NULL,
	EstablishmentName VARCHAR(255),
	CONSTRAINT pk_FoodEstablishmentKey
		PRIMARY KEY (FoodEstablishmentKey),
	CONSTRAINT fk_FoodEstablishments_EstablishmentFK
		FOREIGN KEY (EstablishmentFK)
       		REFERENCES Establishments(EstablishmentKey)
		ON DELETE CASCADE
		ON UPDATE CASCADE
#We want updates to follow through in case there is a change, but there shouldn't be one.
#In case an establishment is deleted, it makes sense for the subcategory to be deleted as well.
);


CREATE TABLE IF NOT EXISTS Weather (
	WeatherKey INT NOT NULL AUTO_INCREMENT,
	WeatherDate Date,
	TempHighInF INT,
	TempAverageInF INT,
	TempLowInF INT,
	HumidityHighPercentage INT,
	HumidityAveragePercentage INT,
	HumidityLowPercentage INT,
	WindHighInMpf INT,
	WindAverageInMpf INT,
	WindGustHighInMpf INT,
	SnowFallInInches DOUBLE,
	PrecipitationInInches DOUBLE,
	Events ENUM('Rain','Snow', 'Both', 'None'),
	CONSTRAINT pk_Weather
		PRIMARY KEY (WeatherKey)
);

CREATE TABLE IF NOT EXISTS InspectionHistory (
	InspectionHistoryKey INT NOT NULL AUTO_INCREMENT,
	EstablishmentName VARCHAR(255),
	InspectionDate DATE,
	InspectionResult VARCHAR(255),
	FoodEstablishmentFK INT,
	CONSTRAINT pk_InspectionHistoryKey
		PRIMARY KEY (InspectionHistoryKey),
	CONSTRAINT fk_InspectionHistory_FoodEstablishmentFK
		FOREIGN KEY (FoodEstablishmentFK)
       		REFERENCES FoodEstablishments(FoodEstablishmentKey)
        	ON DELETE CASCADE
		ON UPDATE CASCADE
#We want updates to follow through in case there is a change, but there shouldn't be one
#We don't want to delete records if this FK is deleted, or even set null in order to keep records.
);

CREATE TABLE IF NOT EXISTS ViolationHistory (
	ViolationHistoryKey INT NOT NULL AUTO_INCREMENT,
	EstablishmentName VARCHAR(255),	
 	ViolationCode VARCHAR(45),
 	ViolationLevel VARCHAR(10),
 	ViolationDescription MEDIUMTEXT,
 	ViolationDate DATE,
	ViolationStatus VARCHAR(10),
 	ViolationComments MEDIUMTEXT,
	FoodEstablishmentFK INT,
 	InspectionHistoryFK INT,
 	CONSTRAINT pk_ViolatoinHistoryKey
		PRIMARY KEY (ViolationHistoryKey),
	CONSTRAINT fk_ViolationHistory_FoodEstablishmentFK
		FOREIGN KEY (FoodEstablishmentFK)
        	REFERENCES FoodEstablishments(FoodEstablishmentKey)
		ON DELETE NO ACTION
		ON UPDATE CASCADE,
#We want updates to follow through in case there is a change, but there shouldn't be one
#We don't want to delete records if this FK is deleted, or even set null in order to keep records.
	CONSTRAINT fk_ViolationHistory_InspectionHistoryFK
		FOREIGN KEY (InspectionHistoryFK)
        	REFERENCES InspectionHistory(InspectionHistoryKey)
        	ON DELETE NO ACTION 
		ON UPDATE CASCADE
#We want updates to follow through in case there is a change, but there shouldn't be one
#We don't want to delete records if this FK is deleted, or even set null in order to keep records.
);




############# LOADING DATA ################


LOAD DATA  INFILE '/tmp/Weather.csv' INTO TABLE Weather
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
LOAD DATA  INFILE '/tmp/license-cats.csv' INTO TABLE LicenseCategories
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
LOAD DATA  INFILE '/tmp/license-licenses.csv' INTO TABLE Licenses
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
LOAD DATA  INFILE '/tmp/license-foodlicenses.csv' INTO TABLE FoodLicenses
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
LOAD DATA  INFILE '/tmp/license-liquorlicenses.csv' INTO TABLE LiquorLicenses
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
LOAD DATA  INFILE '/tmp/Joined-establishments.csv' INTO TABLE Establishments
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;

LOAD DATA  INFILE '/tmp/Joined-addresses.csv' INTO TABLE Addresses
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
LOAD DATA  INFILE '/tmp/Liquor-establishments.csv' INTO TABLE LiquorEstablishments
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
LOAD DATA  INFILE '/tmp/inspection-establishments.csv' INTO TABLE FoodEstablishments
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
###### NOW SOME DATA NEEDS ADDITIONAL FIELDS CONTAINING FOREIGN KEYS THAT HAVE TO BE DERIVED USING TABLE JOINS AND CONDITIONS ##    
###### CREATING TEMPORARY TABLES TO TRANSFER DATA TO FINAL Inspection History Table ######

DROP TABLE IF EXISTS InspectionHistorySub;
DROP TABLE IF EXISTS InspectionHistorySub2;

##Used to store initial data##
CREATE TABLE IF NOT EXISTS InspectionHistorySub (
	InspectionHistoryKey INT,
	EstablishmentName VARCHAR(255),
	InspectionDate VARCHAR(45),
	InspectionResult VARCHAR(45)
);

##used to store data to be added to inital data##
CREATE TABLE IF NOT EXISTS InspectionHistorySub2 (
	ihs2Key INT NOT NULL AUTO_INCREMENT,
    FoodRestFK INT,
    CONSTRAINT pk_InspectionHistorySub2
    PRIMARY KEY (ihs2Key)
    );
    
##Loading initial data into SubTable##    Weather
LOAD DATA  INFILE '/tmp/inspection-inspectionsSub.csv' INTO TABLE InspectionHistorySub
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;

##Update InspectionHistorySup to replace 1900-01-00 with NULL
SET SQL_SAFE_UPDATES=0;
UPDATE InspectionHistorySub
SET InspectionDate = null #want null the keyword, not the string
WHERE InspectionDate = '1900-01-00';
SET SQL_SAFE_UPDATES=1;

##Deriving FK from FoodEstablishment Table and InspectionHistorySub##    
INSERT INTO InspectionHistorySub2(ihs2Key, FoodRestFK)
SELECT '0', FoodEstablishments.FoodEstablishmentKey
FROM FoodEstablishments INNER JOIN InspectionHistorySub on FoodEstablishments.EstablishmentName=InspectionHistorySub.EstablishmentName;

##Loading Both subTables to a Final Table containing all data: InspectionHistory##
INSERT INTO InspectionHistory(InspectionHistoryKey, EstablishmentName, InspectionDate, InspectionResult, FoodEstablishmentFK)
SELECT '0', ihs.EstablishmentName, ihs.InspectionDate, ihs.InspectionResult, ihs2.FoodRestFK
FROM InspectionHistorySub ihs JOIN InspectionHistorySub2 ihs2 ON ihs2.ihs2Key=ihs.InspectionHistoryKey;

###### CREATING TEMPORARY TABLES TO TRANSFER DATA TO FINAL Inspection History Table ######

DROP TABLE IF EXISTS ViolationHistorySub;
DROP TABLE IF EXISTS ViolationHistorySub2;
DROP TABLE IF EXISTS ViolationHistorySub3;
DROP TABLE IF EXISTS ViolationHistorySub4;

##Used to store initial data##
CREATE TABLE IF NOT EXISTS ViolationHistorySub (
	ViolationHistoryKey INT,
	EstablishmentName VARCHAR(255),	
 	ViolationCode VARCHAR(45),
 	ViolationLevel ENUM ('*', '**', '***','NULL'),
 	ViolationDescription MEDIUMTEXT,
 	ViolationDate VARCHAR(45),
	ViolationStatus ENUM('Pass', 'Fail', 'NULL'),
 	ViolationComments MEDIUMTEXT
);

##used to store data to be added to inital data##
CREATE TABLE IF NOT EXISTS ViolationHistorySub2 (
	vhs2Key INT NOT NULL AUTO_INCREMENT,
	FoodRestFK INT,
	CONSTRAINT pk_ViolationHistorySub2
    PRIMARY KEY (vhs2Key)
    );

##used to store data to be added to inital data##
CREATE TABLE IF NOT EXISTS ViolationHistorySub3 (
	vhs3Key INT NOT NULL AUTO_INCREMENT,
	InspectionFK INT,
	CONSTRAINT pk_ViolationHistorySub3
    PRIMARY KEY (vhs3Key)
    );

##used as intermediary (sub and sub2 combined) which adds one of the FKs (FoodEstablishmentFK)##

CREATE TABLE IF NOT EXISTS ViolationHistorySub4 (
	vhs4Key INT NOT NULL AUTO_INCREMENT,
	EstablishmentName VARCHAR(255),	
 	ViolationCode VARCHAR(45),
 	ViolationLevel ENUM ('*', '**', '***','NULL'),
 	ViolationDescription MEDIUMTEXT,
 	ViolationDate VARCHAR(45),
	ViolationStatus ENUM('Pass', 'Fail', 'NULL'),
 	ViolationComments MEDIUMTEXT,
    FoodEstablishmentFK INT,
    CONSTRAINT pk_ViolationHistorySub4
    PRIMARY KEY (vhs4Key)
);


##Loading initial data into SubTable##   
 LOAD DATA  INFILE '/tmp/inspection-violationsSub.csv' INTO TABLE ViolationHistorySub
	FIELDS TERMINATED BY  ','  ENCLOSED BY '"'
    LINES TERMINATED BY '\r\n'
    IGNORE 1 LINES;
    
    ##Update ViolationHistorySup to replace 1900-01-00 with NULL
SET SQL_SAFE_UPDATES=0;
UPDATE ViolationHistorySub
SET ViolationDate = null # We want null the keyword, not the string, so no quotes
WHERE ViolationDate = '1900-01-00';
SET SQL_SAFE_UPDATES=1;

##Deriving FK from FoodEstablishment Table and ViolationHistorySub## 
INSERT INTO ViolationHistorySub2(vhs2Key, FoodRestFK)
SELECT '0', FoodEstablishments.FoodEstablishmentKey
FROM FoodEstablishments JOIN ViolationHistorySub on FoodEstablishments.EstablishmentName=ViolationHistorySub.EstablishmentName;
 
INSERT INTO ViolationHistorySub4(vhs4Key, EstablishmentName, ViolationCode, ViolationLevel, ViolationDescription, ViolationDate, ViolationStatus, ViolationComments, FoodEstablishmentFK)
SELECT '0', vhs.EstablishmentName, vhs.ViolationCode, vhs.ViolationLevel, vhs.ViolationDescription, vhs.ViolationDate, vhs.ViolationStatus, vhs.ViolationComments, vhs2.FoodRestFK
FROM ViolationHistorySub vhs JOIN ViolationHistorySub2 vhs2 ON vhs.ViolationHistoryKey=vhs2.vhs2Key;

##Deriving FK from InspectionHistory Table and ViolationHistorySub## 
INSERT INTO ViolationHistorySub3(vhs3Key, InspectionFK)
SELECT '0', InspectionHistory.InspectionHistoryKey
FROM InspectionHistory JOIN ViolationHistorySub4 on InspectionHistory.FoodEstablishmentFK=ViolationHistorySub4.FoodEstablishmentFK
WHERE InspectionHistory.InspectionDate=ViolationHistorySub4.ViolationDate;

##Loading three subTables to a Final Table containing all data: ViolationHistory##
INSERT INTO ViolationHistory(ViolationHistoryKey, EstablishmentName, ViolationCode, ViolationLevel, ViolationDescription, ViolationDate, ViolationStatus, ViolationComments, FoodEstablishmentFK, InspectionHistoryFK)
SELECT '0', vhs4.EstablishmentName, vhs4.ViolationCode, vhs4.ViolationLevel, vhs4.ViolationDescription, vhs4.ViolationDate, vhs4.ViolationStatus, vhs4.ViolationComments, vhs4.FoodEstablishmentFK, vhs3.InspectionFK
FROM ViolationHistorySub4 vhs4 JOIN ViolationHistorySub3 vhs3 on vhs3.vhs3Key=vhs4.vhs4Key;







