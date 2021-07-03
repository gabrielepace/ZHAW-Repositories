-- SQL Queries
USE HungryStudent_DB;
-- ----------------
-- User SQL Queries
-- ----------------
-- SELECT
SELECT * FROM User;
SELECT * FROM User WHERE UserID = '1';
SELECT * FROM User WHERE UserID = '2';
SELECT * FROM User WHERE UserID = '3';
SELECT * FROM User WHERE UserID = '4';

-- UPDATE
UPDATE User SET Email = 'mustermann@test.com' WHERE UserID = '1';
UPDATE User SET Email = 'musterfrau@test.com' WHERE UserID = '2';
UPDATE User SET Email = 'mariorossi@test.com' WHERE UserID = '3';
UPDATE User SET Email = 'bernasconi@test.com' WHERE UserID = '4';

-- DELETE
DELETE FROM USER;
DELETE FROM User WHERE UserID = '1';
DELETE FROM User WHERE UserID = '2';
DELETE FROM User WHERE UserID = '3';
DELETE FROM User WHERE UserID = '4';

-- ------------------
-- Marker SQL Queries
-- ------------------
SELECT * FROM Marker;
SELECT * FROM Marker WHERE Vegetarian=true AND TakeAway=false AND PRICE LIKE 'MEDIUM' AND Kitchen LIKE '%SWISS%BARS%';
SELECT * FROM Marker WHERE ID = 1;
SELECT ID FROM Created;
SELECT ID FROM Favorites;

-- UPDATE
UPDATE Marker SET Name = 'Pizzeria Bella Napoli' WHERE ID = '1';
UPDATE Marker SET Name = 'Restaurant Loewen' WHERE ID = '2';

-- DELETE
DELETE FROM Marker;
DELETE FROM Marker WHERE ID = '1';
DELETE FROM Marker WHERE ID = '2';
DELETE FROM Marker WHERE ID = '3';
DELETE FROM Marker WHERE ID = '4';
DELETE FROM Marker WHERE ID = '5';
DELETE FROM Marker WHERE Name LIKE 'Selenium%';

-- ---------------------
-- Favourite SQL Queries
-- ---------------------
SELECT * FROM Favourites;
-- DELETE
DELETE FROM Favourites;
DELETE FROM Favourites WHERE UserID = '1' AND ID = '1';
DELETE FROM Favourites WHERE UserID = '1' AND ID = '2';
DELETE FROM Favourites WHERE UserID = '1' AND ID = '3';

-- -------------------
-- Created SQL Queries
-- -------------------
SELECT * FROM Created;
-- DELETE
DELETE FROM Created;
DELETE FROM Created WHERE UserID = '1' AND ID = '1';
DELETE FROM Created WHERE UserID = '1' AND ID = '2';
DELETE FROM Created WHERE UserID = '1' AND ID = '3';

-- ------------------
-- Review SQL Queries
-- ------------------
SELECT * FROM Review;
SELECT Review.UserID, Username, ID, ReviewText FROM Review 
	JOIN User ON Review.UserID = User.UserID WHERE ID = '1';
-- DELETE
DELETE FROM Review;
DELETE FROM Review WHERE UserID = '1';
DELETE FROM Review WHERE ID = '2';