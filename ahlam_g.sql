create database profx10;
use profx10;
show databases;
create table category(
c_name varchar(255) primary key
);
CREATE TABLE Product (
    product_code INT PRIMARY KEY,
    pname VARCHAR(255),
    price DECIMAL,
    wholesale_price DECIMAL,
    product_company_name VARCHAR(255),
    product_image BLOB,
    c_name VARCHAR(255), -- Adding the c_name column here
    FOREIGN KEY (c_name) REFERENCES category(c_name)
);

CREATE TABLE DefectProduct (
    product_code INT PRIMARY KEY,
    defect_cause VARCHAR(255),
    FOREIGN KEY (product_code) REFERENCES Product(product_code)
);
create table shop (
shop_name varchar(255) primary key,
address varchar(255)
);
CREATE TABLE DisplayProduct (
    product_code INT PRIMARY KEY,
    display_date DATE,
    shop_name VARCHAR(255), -- Adding the shop_name column here
    FOREIGN KEY (product_code) REFERENCES Product(product_code),
    FOREIGN KEY (shop_name) REFERENCES shop(shop_name)
);

create table store (
store_name varchar (255) primary key ,
address varchar (255)
);
CREATE TABLE StockedProduct (
    product_code INT PRIMARY KEY,
    stocked_date DATE,
    store_name VARCHAR(255), -- Adding the store_name column here
    FOREIGN KEY (product_code) REFERENCES Product(product_code),
    FOREIGN KEY (store_name) REFERENCES store(store_name)
);

create table sales(
sale_number int primary key,
sale_date date
);
create table localSales(
sale_number int primary key,
local_quantity int,
FOREIGN KEY (sale_number) REFERENCES sales(sale_number)
);
create table deliverySale (
sale_number int ,
shipping_price int,
total_price int ,
primary key (sale_number),
FOREIGN KEY (sale_number) REFERENCES sales(sale_number)
);
create table display2sales(
sale_number int,
  product_code INT,
  primary key(sale_number,product_code),
  FOREIGN KEY (sale_number) REFERENCES sales(sale_number),
  FOREIGN KEY (product_code) REFERENCES product(product_code)
);
CREATE TABLE deliveryCustomers(
    customerID INT PRIMARY KEY,
    fname VARCHAR(255),
    lname VARCHAR(255),
    phone BIGINT, -- Assuming phone numbers are stored as integers
    address VARCHAR(255)
);

CREATE TABLE delivery2customers (
    sale_number INT,
    customerID INT, -- Changed to INT datatype
    quantity INT,
    PRIMARY KEY (sale_number, customerID),
    FOREIGN KEY (sale_number) REFERENCES sales(sale_number),
    FOREIGN KEY (customerID) REFERENCES deliveryCustomers(customerID) -- Corrected table reference
);

CREATE TABLE employee (
    emp_ID INT PRIMARY KEY,
    age INT,
    salary INT,
    Bdate DATE,
    fname VARCHAR(255),
    lname VARCHAR(255), -- Changed to lname for consistency
    phone BIGINT, -- Changed to BIGINT assuming phone numbers are stored as integers
    address VARCHAR(255),
    shop_name VARCHAR(255), -- Added shop_name column
    FOREIGN KEY (shop_name) REFERENCES shop(shop_name)
);

create table hourlyEmployee(
emp_ID int primary key,
hourRate int,
hourNumber int,
FOREIGN KEY (emp_ID) REFERENCES employee(emp_ID)
);
create table dailyEmployee(
emp_ID int primary key,
dayRate int,
daysNumber int,
FOREIGN KEY (emp_ID) REFERENCES employee(emp_ID)
);