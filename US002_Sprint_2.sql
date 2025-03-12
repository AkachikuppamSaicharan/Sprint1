CREATE TABLE US002_Customer(
    CustomerNumber NUMERIC(13) PRIMARY KEY,
    CustomerName VARCHAR(50) NOT NULL,
    Email VARCHAR(250) NOT NULL UNIQUE,
    MobileNumber NUMERIC(10) NOT NULL UNIQUE
);
CREATE TABLE BillDetails(
    BillNo NUMERIC(5) PRIMARY KEY,
    PayableAmount NUMERIC(7,2) NOT NULL,
    DueAmount NUMERIC(7,2) NOT NULL,
    CustomerNumber NUMERIC(13),
    Constraint Fkey FOREIGN KEY(CustomerNumber) REFERENCES US002_Customer(CustomerNumber)
);

SELECT B.CustomerNumber,C.CustomerName,B.PayableAmount,B.DueAmount
FROM BillDetails AS B,US002_Customer AS C
WHERE B.CustomerNumber=C.CustomerNumber;