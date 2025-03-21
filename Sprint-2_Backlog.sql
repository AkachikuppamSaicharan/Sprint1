CREATE TABLE Sprint2_US001_Customer(
    ConsumerId NUMERIC(13) PRIMARY KEY,
    BillNo NUMERIC(5) NOT NULL UNIQUE,
    Title VARCHAR(3) NOT NULL,
    CustomerName VARCHAR(50) NOT NULL,
    Email VARCHAR(250) NOT NULL UNIQUE,
    MobileNo NUMERIC(10) NOT NULL UNIQUE,
    UserId VARCHAR(20) NOT NULL UNIQUE,
    Password VARCHAR(30) NOT NULL,
    ConfirmPassword VARCHAR(30) NOT NULL
);
DROP TABLE Sprint2_US001_Customer;


CREATE TABLE Sprint2_US001_Customer(
     ConsumerId NUMERIC(13) PRIMARY KEY,
     BillNo NUMERIC(5) NOT NULL UNIQUE,
     Title VARCHAR(3) NOT NULL,
     CustomerName VARCHAR(50) NOT NULL,
     Email VARCHAR(250) NOT NULL UNIQUE,
     MobileNo NUMERIC(10) NOT NULL UNIQUE,
     UserId VARCHAR(20) NOT NULL UNIQUE,
     Password VARCHAR(30) NOT NULL,
     ConfirmPassword VARCHAR(30) NOT NULL,
    CONSTRAINT Fkey FOREIGN KEY (BillNo) REFERENCES BillDetails(BillNo)
);
CREATE TABLE BillDetails(
    BillNo NUMERIC(5) PRIMARY KEY,
    DueAmount DECIMAL(7,2) NOT NULL,
    PayableAmount DECIMAL(7,2) NOT NULL
)