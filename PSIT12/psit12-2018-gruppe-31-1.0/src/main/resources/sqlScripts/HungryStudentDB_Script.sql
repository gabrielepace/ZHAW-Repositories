-- Hungry Student Database
SET NAMES 'utf8mb4';
DROP DATABASE IF EXISTS HungryStudent_DB;
CREATE DATABASE HungryStudent_DB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE HungryStudent_DB;

CREATE TABLE User (
    UserID INT NOT NULL AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL,
    Email VARCHAR(50),
    Password VARCHAR(150) NOT NULL,
    
    CONSTRAINT PK_User PRIMARY KEY (UserID)
);

CREATE TABLE Marker (
    ID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Address VARCHAR(50) NOT NULL,
    Website VARCHAR(50),
    Description VARCHAR(500),
    OpeningHours VARCHAR(100) NOT NULL,
    Kitchen VARCHAR(50),
    Price VARCHAR(20) NOT NULL,
    Vegetarian BOOLEAN NOT NULL,
    TakeAway BOOLEAN NOT NULL,
    Location VARCHAR(50) NOT NULL,
    Image BLOB,
    
    CONSTRAINT PK_Marker PRIMARY KEY (ID)
);

CREATE TABLE Created (
    UserID INT NOT NULL,
    ID INT NOT NULL,
    
    CONSTRAINT PK_Created PRIMARY KEY (UserID , ID),
    
    FOREIGN KEY (UserID) REFERENCES User (UserID),
    FOREIGN KEY (ID) REFERENCES Marker (ID)
);

CREATE TABLE Favourites (
    UserID INT NOT NULL,
    ID INT NOT NULL,
    
    CONSTRAINT PK_Favourites PRIMARY KEY (UserID , ID),
    
    FOREIGN KEY (UserID) REFERENCES User (UserID),
    FOREIGN KEY (ID) REFERENCES Marker (ID)
);

CREATE TABLE Review (
    UserID INT NOT NULL,
    ID INT NOT NULL,
    ReviewText VARCHAR(100) NOT NULL,
    
    CONSTRAINT PK_Review PRIMARY KEY (UserID , ID, ReviewText),
    
    FOREIGN KEY (UserID) REFERENCES User (UserID),
    FOREIGN KEY (ID) REFERENCES Marker (ID)
);

-- Workbench settings to ensure stable connection from JDBC
SET GLOBAL time_zone = '+2:00';

DROP USER IF EXISTS 'hungrystudent'@'localhost';

CREATE USER 'hungrystudent'@'localhost' IDENTIFIED BY 'hungrystudent';
GRANT ALL PRIVILEGES ON *.* TO 'hungrystudent'@'localhost' WITH GRANT OPTION;