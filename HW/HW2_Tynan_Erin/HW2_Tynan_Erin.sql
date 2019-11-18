CREATE SCHEMA IF NOT EXISTS `Restaurant_Reviews` ;
USE  Restaurant_Reviews;

DROP TABLE IF EXISTS CreditCards;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Reservations;
DROP TABLE IF EXISTS FoodCartRestaurants;
DROP TABLE IF EXISTS TakeOutRestaurants;
DROP TABLE IF EXISTS SitDownRestaurants;
DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS Companies;
DROP TABLE IF EXISTS Users;

CREATE TABLE IF NOT EXISTS Users (
	UserName VARCHAR(255) NOT NULL,
	Password VARCHAR(255) NOT NULL,
	FirstName VARCHAR(255) NOT NULL,
	LastName VARCHAR(255) NOT NULL,
	Email VARCHAR(255) NOT NULL,
	PhoneNumber VARCHAR(20) NOT NULL,
    CONSTRAINT pk_Users_UserName 
		PRIMARY KEY (UserName)
);

CREATE TABLE IF NOT EXISTS CreditCards (
	CardNumber BIGINT NOT NULL,
	ExpirationDate DATE NOT NULL,
    UserName VARCHAR(255) NOT NULL,
    CONSTRAINT fk_Users_UserName 
		FOREIGN KEY (UserName)
        REFERENCES USERS(UserName)
        ON DELETE NO ACTION 
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS Companies (
	CompanyName VARCHAR(255),
    Description TEXT(300) NOT NULL,
	CONSTRAINT pk_Companies_CompanyName 
		PRIMARY KEY (CompanyName)
);

CREATE TABLE IF NOT EXISTS Restaurants (
	RestaurantID INT NOT NULL AUTO_INCREMENT,
	CompanyName VARCHAR(255), 
	Name VARCHAR(255) NOT NULL,
	Description TEXT(500) NOT NULL,
	Menu LONGTEXT NOT NULL,
	Hours VARCHAR(20) NOT NULL,
	Active BOOLEAN NOT NULL,
	Street1 VARCHAR(25) NOT NULL,
	Street2 VARCHAR(15),
	City VARCHAR(35) NOT NULL,
	State VARCHAR(30) NOT NULL,
	Zip VARCHAR(15) NOT NULL,
	CuisineType ENUM('AFRICAN', 'AMERICAN', 'ASIAN', 'EUROPEAN', 'HISPANIC'),
	CONSTRAINT pk_Restaurants_RestaurantID 
		PRIMARY KEY (RestaurantID),
	CONSTRAINT fk_Restaruants_CompanyName 
		FOREIGN KEY (CompanyName)
        REFERENCES COMPANIES(CompanyName)
        ON DELETE NO ACTION 
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS Reviews (
	ReviewID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(255),
    RestaurantID INT,
	Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	Content TEXT(500) NOT NULL,
    Rating FLOAT NULL,
	CONSTRAINT pk_Reviews_ReviewID 
		PRIMARY KEY (ReviewID),
	CONSTRAINT fk_Reviews_UserName 
		FOREIGN KEY (UserName)
        REFERENCES USERS(UserName)
        ON DELETE NO ACTION 
        ON UPDATE CASCADE,
	CONSTRAINT fk_Reviews_RestaurantID 
		FOREIGN KEY (RestaurantID)
        REFERENCES RESTAURANTS(RestaurantID)
        ON DELETE NO ACTION 
        ON UPDATE CASCADE,
	CONSTRAINT uq_Reviews_Review
		UNIQUE(UserName, RestaurantID)
);

CREATE TABLE IF NOT EXISTS Recommendations (
	RecommendationID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(255),
    RestaurantID INT,
    Recommendation BOOLEAN,
	CONSTRAINT pk_Recommendations_RecommendationID 
		PRIMARY KEY (RecommendationID),
	CONSTRAINT fk_Recommendations_UserName 
		FOREIGN KEY (UserName)
        REFERENCES USERS(UserName)
        ON DELETE NO ACTION 
        ON UPDATE CASCADE,
	CONSTRAINT fk_Recommendations_RestaurantID 
		FOREIGN KEY (RestaurantID)
        REFERENCES RESTAURANTS(RestaurantID)
        ON DELETE NO ACTION 
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS FoodCartRestaurants (
	RestaurantID INT,
	Licensed BOOLEAN, 
	CONSTRAINT pk_FoodCartRestaurants_RestaurantID
		PRIMARY KEY (RestaurantID), 
	CONSTRAINT fk_FoodCartRestaurants_RestaurantID
		FOREIGN KEY (RestaurantID)
		REFERENCES RESTAURANTS(RestaurantID)
		ON DELETE NO ACTION 
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS TakeOutRestaurants (
	RestaurantID INT,
	MaxWaitTime INT, 
	CONSTRAINT pk_TakeOutRestaurants_RestaurantID
		PRIMARY KEY (RestaurantID), 
	CONSTRAINT fk_TakeOutRestaurants_RestaurantID
		FOREIGN KEY (RestaurantID)
		REFERENCES RESTAURANTS(RestaurantID)
		ON DELETE NO ACTION 
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS SitDownRestaurants (
	RestaurantID INT,
	Capacity INT, 
	CONSTRAINT pk_SitDownRestaurants_RestaurantID
		PRIMARY KEY (RestaurantID), 
	CONSTRAINT fk_SitDownRestaurants_RestaurantID
		FOREIGN KEY (RestaurantID)
		REFERENCES RESTAURANTS(RestaurantID)
		ON UPDATE CASCADE 
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Reservations (
	ReservationID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(255),
    RestaurantID INT,
	Start TIMESTAMP,
	End TIMESTAMP,
    PartySize INT,
	CONSTRAINT pk_Reservations_ReservationID 
		PRIMARY KEY (ReservationID),
	CONSTRAINT fk_Reservations_UserName 
		FOREIGN KEY (UserName)
        REFERENCES USERS(UserName)
        ON DELETE NO ACTION 
        ON UPDATE CASCADE,
	CONSTRAINT fk_Reservations_RestaurantID 
		FOREIGN KEY (RestaurantID)
        REFERENCES SITDOWNRESTAURANTS(RestaurantID)
        ON DELETE NO ACTION 
        ON UPDATE CASCADE
);

INSERT INTO Users(UserName, Password, FirstName,LastName, Email, PhoneNumber) 
	VALUES('Mary22', '12345', 'Mary', 'Cary', 'mary@the.com', '123456789');
INSERT INTO Users(UserName, Password, FirstName,LastName, Email, PhoneNumber) 
	VALUES('jbob','765432', 'Jim', 'Bob', 'jim@bob.com', '987654321');
    
INSERT INTO Companies(CompanyName, Description) 
	VALUES('Yummy', 'Good tasting health food');
INSERT INTO Companies(CompanyName, Description) 
	VALUES('Tummy','Food for the sweet tooth');

INSERT INTO Restaurants(RestaurantID, CompanyName, Name, Description, Menu, Hours, 
	Active, Street1, Street2, City, State, Zip, CuisineType) 
	VALUES(54, 'Yummy', 'Yogurt Stop', 'Get the health benefits of yogurt in a yummy setting',
    'Chocolate, Vanilla, Strawberry, Other Berries', '6am-8pm', TRUE, '123 Main St', 
    'suite 2', 'Anyville', 'Ohio', '12345', 'AMERICAN');
INSERT INTO Restaurants(RestaurantID, CompanyName, Name, Description, Menu, Hours, 
	Active, Street1, Street2, City, State, Zip, CuisineType) 
	VALUES(6, 'Tummy', 'Thai Food', 'Thai on the go', 'Pho', '11am-11pm', TRUE, 
    '456 Avenue St', null , 'Seattle', 'Washington', '54321', 'ASIAN');
INSERT INTO Restaurants(RestaurantID, CompanyName, Name, Description, Menu, Hours, 
	Active, Street1, Street2, City, State, Zip, CuisineType) 
	VALUES(99, null, 'Ethiopian Food', 'Taste of home', 'Ethiopian baklava', '11am-1am', TRUE, 
    '778 Parkway St', null , 'Phoenix', 'Arizona', '57648', 'AFRICAN');
INSERT INTO Restaurants(RestaurantID, CompanyName, Name, Description, Menu, Hours, 
	Active, Street1, Street2, City, State, Zip, CuisineType) 
	VALUES(2304, null, 'Frenchies', 'Gourmet French food', 'red wine, white wine, steak', '5pm-10pm', TRUE, 
    '4523 98th St', null , 'New York', 'New York', '98765', 'EUROPEAN');
    
INSERT INTO FoodCartRestaurants(RestaurantID, Licensed) 
	VALUES(54, TRUE);
INSERT INTO FoodCartRestaurants(RestaurantID, Licensed) 
	VALUES(6, FALSE);
    
INSERT INTO TakeOutRestaurants(RestaurantID, MaxWaitTime) 
	VALUES(99, 45);
INSERT INTO TakeOutRestaurants(RestaurantID, MaxWaitTime) 
	VALUES(54, 20);
    
INSERT INTO SitDownRestaurants(RestaurantID, Capacity) 
	VALUES(99, 60);
INSERT INTO SitDownRestaurants(RestaurantID, Capacity) 
	VALUES(54, 60);
INSERT INTO SitDownRestaurants(RestaurantID, Capacity) 
	VALUES(2304, 120);

INSERT INTO Reviews(ReviewID, UserName, RestaurantID, Created, Content, Rating) 
	VALUES(33, 'Mary22', 99, '2015-02-03 00:45:43', 'Loved this place', 5.0);
INSERT INTO Reviews(ReviewID, UserName, RestaurantID, Created, Content, Rating) 
	VALUES(12, 'jbob', 54, '2014-12-08 18:34:29', 'Bad Service', 2.5);

INSERT INTO Recommendations(RecommendationID, UserName, RestaurantID, Recommendation) 
	VALUES(107,'Mary22', 99, TRUE);
INSERT INTO Recommendations(RecommendationID, UserName, RestaurantID, Recommendation) 
	VALUES(77,'jbob', 54, FALSE);
    
INSERT INTO Reservations(ReservationID, Start, End, PartySize, UserName, RestaurantID) 
	VALUES(107, '2018-01-23 17:00', '2018-01-23 19:00', 4,  'Mary22', 99);
INSERT INTO Reservations(ReservationID, Start, End, PartySize, UserName, RestaurantID) 
	VALUES(304, '2010-04-13 19:00', '2010-04-14 21:00', 8, 'jbob', 2304);

LOAD DATA INFILE '/tmp/creditcards.csv' INTO TABLE CreditCards 
	FIELDS TERMINATED BY ',' ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES;

