<<<<<<< HEAD
CREATE TABLE Menu (
    dishName VARCHAR(150) PRIMARY KEY,
    imgpath VARCHAR(400),
    description VARCHAR(400),
    dishPrice DECIMAL(4, 2),
	  dishSpecialPrice DECIMAL(4, 2)
);

CREATE TABLE dish_orders (
    orderID INT NOT NULL AUTO_INCREMENT,
    dish VARCHAR(150),
    customer_name VARCHAR(150),
    customer_phone VARCHAR(30),
    orderPlacedTime TIMESTAMP,
    FOREIGN KEY (dish) REFERENCES dishes(dishName),
    PRIMARY KEY (orderID)
);

CREATE TABLE bowl_bases (
    title VARCHAR(150) PRIMARY KEY,
    imgpath VARCHAR(400),
    description VARCHAR(400),
    price DECIMAL(4, 2)
);

CREATE TABLE bowl_toppings (
    title VARCHAR(150) PRIMARY KEY,
    imgpath VARCHAR(400),
    description VARCHAR(400),
    price DECIMAL(4, 2)
);

CREATE TABLE bowl_orders (
    base VARCHAR(150),
    topping VARCHAR(150),
    customer_name VARCHAR(150),
    customer_phone VARCHAR(20),
    FOREIGN KEY (base) REFERENCES bowl_bases(title),
    FOREIGN KEY (topping) REFERENCES bowl_toppings(title)
);

SELECT * FROM dishes;
SELECT * FROM dish_orders;
SELECT * FROM bowl_bases;
SELECT * FROM bowl_toppings;
SELECT * FROM bowl_orders;

DELETE FROM dishes;
DELETE FROM dish_orders;
DELETE FROM bowl_bases;
DELETE FROM bowl_toppings;
DELETE FROM bowl_orders;

DROP TABLE bowl_orders;
DROP TABLE bowl_toppings;
DROP TABLE bowl_bases;
DROP TABLE dish_orders;
DROP TABLE dishes;
=======
CREATE TABLE Dishes (
    DishName VARCHAR(150) PRIMARY KEY,
    DishPrice DECIMAL(4, 2),
    DishSpecialPrice DECIMAL(4, 2),
    DishDescription VARCHAR(400),
    DishPicture VARCHAR(400),
	SubMenu VARCHAR(40)
);

CREATE TABLE Sides (
    SideName VARCHAR(150) PRIMARY KEY,
    SidePrice DECIMAL(4, 2),
    SideDescription VARCHAR(400),
    SidePicture VARCHAR(400)
);

CREATE TABLE Toppings (
    ToppingName VARCHAR(150) PRIMARY KEY,
    ToppingPrice DECIMAL(4, 2),
    ToppingDescription VARCHAR(400),
    ToppingPicture VARCHAR(400)
);

CREATE TABLE Users (
    UserName VARCHAR(30) PRIMARY KEY,
    Email VARCHAR(200)
);

CREATE TABLE Orders (
    OrderID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(30),
    OrderPlacedTime TIMESTAMP,
    OrderTotalPrice DECIMAL(4, 2),
    PRIMARY KEY (OrderID),
    FOREIGN KEY (UserName) REFERENCES Users(UserName)
);

CREATE TABLE LunchBowls (
    OrderID INT,
    Dish VARCHAR(150),
    Side VARCHAR(150),
    Topping VARCHAR(150),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (Dish) REFERENCES Dishes(DishName),
    FOREIGN KEY (Side) REFERENCES Sides(SideName),
    FOREIGN KEY (Topping) REFERENCES Toppings(ToppingName)
);

SELECT * FROM Dishes;
SELECT * FROM Sides;
SELECT * FROM Toppings;
SELECT * FROM Users;
SELECT * FROM Orders;
SELECT * FROM LunchBowls;

DELETE FROM Dishes;
DELETE FROM Sides;
DELETE FROM Toppings;
DELETE FROM Users;
DELETE FROM Orders;
DELETE FROM LunchBowls;

DROP TABLE LunchBowls;
DROP TABLE Orders;
DROP TABLE Users;
DROP TABLE Toppings;
DROP TABLE Sides;
DROP TABLE Dishes;
>>>>>>> origin/master
