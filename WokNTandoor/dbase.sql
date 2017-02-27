CREATE TABLE dishes (
    title VARCHAR(150) PRIMARY KEY,
    imgpath VARCHAR(400),
    description VARCHAR(400),
    price DECIMAL(4, 2)
);

CREATE TABLE dish_orders (
    dish VARCHAR(150),
    customer_name VARCHAR(150),
    customer_phone VARCHAR(30),
    FOREIGN KEY (dish) REFERENCES dishes(title)
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