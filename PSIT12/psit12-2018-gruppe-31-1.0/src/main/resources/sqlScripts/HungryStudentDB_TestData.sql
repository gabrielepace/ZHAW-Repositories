-- -------------------------------------------------------------------
-- Hungry Student DB Test Data
-- -------------------------------------------------------------------
-- ------------------------------
-- SQL Queries for User testing
-- ------------------------------
INSERT INTO User (UserID, Username, Email, Password) VALUES ('1','mustermann','mustermann@testing.ch','4b865323fe7744fc136db11f5e7b329ba6ebb88c996928ee21050e32d2976ad65f46a9d747d91a3fb7307ddd61a7a71172d2808212d5e68b2c913d27f8d97aed');
INSERT INTO User (UserID, Username, Email, Password) VALUES ('2','musterfrau','musterfrau@testing.ch','7b6dfb9cabf147630baaeaa1ac8a7cf88a24c2afd67a2c69e509bcd49738033b865b5be8bb6c552660bf5c97a0aeea7d76c7d08f52b61278123315059430e425');
INSERT INTO User (UserID, Username, Email, Password) VALUES ('3','mariorossi','mariorossi@testing.ch','cd29a94a7ef372d418ce4e26a92bb4528011e99395a68e0d20ed235d8af3988084d1f8ac79e2c78d3159288a1913134fabcbb98c36866c8390b5519067ea7d06');
INSERT INTO User (UserID, Username, Email, Password) VALUES ('4','bernasconi','bernasconi@testing.ch','252e09e627c4f2adf246b5900291da14e2f7277a3fca18035f5260f869a20174a3840e4cb7768267d21c1886603558dee6b6bd8d1e2ad5397b80aa2f2e1f5c49');
INSERT INTO User (UserID, Username, Email, Password) VALUES ('5','seleniumuser','selenium@testing.ch','ac5c1fa5e6850b37c05efe8ddae8a1d7b5c766ba6d22cf6f730981d33d421fe7da2f28d0b767f92f595cf8be47d822e5ccc711308245d0f5b02b95f8767ad2e2');
-- 1.mustermann pw: asdf
-- 2.musterfrau pw: abc123
-- 3.mariorossi pw: test123
-- 4.bernasconi pw: password
-- 5.seleniumuser pw: selenium123

-- ------------------------------
-- SQL Queries for Marker testing
-- ------------------------------
INSERT INTO Marker (ID, Name, Address, Website, Description, OpeningHours, Kitchen, Price, Vegetarian, TakeAway, Location)
VALUES ('1','Pizzeria Roma','Lagerstrasse 1','www.pizzerianapoli.ch','Eine Gute Pizza wie in Italien','09:00-22:00','EUROPEAN','MEDIUM',true,true,'47.28668, 8.65723');
INSERT INTO Marker (ID, Name, Address, Website, Description, OpeningHours, Kitchen, Price, Vegetarian, TakeAway, Location) 
VALUES ('2','Burger King','Lagerstrasse 22','www.burgerking.ch','Has burgers','09:00-22:00','FASTFOOD','CHEAP',true,true,'47.37299, 8.53733');
INSERT INTO Marker (ID, Name, Address, Website, Description, OpeningHours, Kitchen, Price, Vegetarian, TakeAway, Location) 
VALUES ('3','Starbucks','Lagerstrasse 42','www.starbucks.ch','Has Coffee','09:00-21:00','CAFE','MEDIUM',false,true,'47.37637, 8.54038');
INSERT INTO Marker (ID, Name, Address, Website, Description, OpeningHours, Kitchen, Price, Vegetarian, TakeAway, Location) 
VALUES ('4','Bling bling Resti','Lagerstrasse 32','www.blingbling.ch','Is Expensive','09:00-21:00','EUROPEAN,SWISS,BARS','EXPENSIVE',true,false,'47.37447, 8.53033');
INSERT INTO Marker (ID, Name, Address, Website, Description, OpeningHours, Kitchen, Price, Vegetarian, TakeAway, Location) 
VALUES ('5','Restaurant ABC','Lagerstrasse 12','www.abc.ch','has everything','09:00-21:00','EUROPEAN,ASIAN,SWISS,BARS','MEDIUM',true,false,'47.37445, 8.53033');
INSERT INTO Marker (ID, Name, Address, Website, Description, OpeningHours, Kitchen, Price, Vegetarian, TakeAway, Location) 
VALUES ('6','Restaurant XYZ','Lagerstrasse 11','www.abc.ch','has not everything','09:00-21:00','EUROPEAN,BARS','MEDIUM',true,false,'47.37445, 8.53043');

-- ---------------------------------
-- SQL Queries for Favourites testing
-- ---------------------------------
INSERT INTO Favourites (UserID, ID) VALUES ('1', '1');
INSERT INTO Favourites (UserID, ID) VALUES ('1', '3');
INSERT INTO Favourites (UserID, ID) VALUES ('1', '5');

-- ---------------------------------
-- SQL Queries for Created testing
-- ---------------------------------
INSERT INTO Created (UserID, ID) VALUES ('1', '1');
INSERT INTO Created (UserID, ID) VALUES ('1', '2');
INSERT INTO Created (UserID, ID) VALUES ('1', '3');

-- ---------------------------------
-- SQL Queries for Review testing
-- ---------------------------------
INSERT INTO Review (UserID, ID, ReviewText) VALUES ('3', '1', 'War sehr gut');
INSERT INTO Review (UserID, ID, ReviewText) VALUES ('2', '2', 'Schlechte Beleuchtung :(');
INSERT INTO Review (UserID, ID, ReviewText) VALUES ('1', '3', 'Die Vorspeise war fantastisch');