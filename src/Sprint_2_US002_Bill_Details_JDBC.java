import java.sql.*;
import java.util.Scanner;

public class Sprint_2_US002_Bill_Details_JDBC {
    static Scanner Sc=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        CreateBillTable();
        CreateCustomerTable();

    }
    public static void CreateCustomerTable() throws SQLException {
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String CreateTable="CREATE TABLE Sprint2_US002_Customer(\n" +
                "     ConsumerId NUMERIC(13) PRIMARY KEY,\n" +
                "     BillNo NUMERIC(5) NOT NULL UNIQUE,\n" +
                "     Title VARCHAR(3) NOT NULL,\n" +
                "     CustomerName VARCHAR(50) NOT NULL,\n" +
                "     Email VARCHAR(250) NOT NULL UNIQUE,\n" +
                "     MobileNo NUMERIC(10) NOT NULL UNIQUE,\n" +
                "     UserId VARCHAR(20) NOT NULL UNIQUE,\n" +
                "     Password VARCHAR(30) NOT NULL,\n" +
                "     ConfirmPassword VARCHAR(30) NOT NULL,\n" +
                "    CONSTRAINT Fkey FOREIGN KEY (BillNo) REFERENCES BillDetails(BillNo)\n" +
                ")";

        PreparedStatement Stmt=Con.prepareStatement(CreateTable);
        int nR=Stmt.executeUpdate();
        if(nR==0)System.out.println("Table Customer Successfully Created");
        else{System.out.println("Table Cannot Be Created");}
        Stmt.close();
        Con.close();
        return;

    }
    public static void CreateBillTable() throws SQLException {
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String CreateBillTable="CREATE TABLE BillDetails(\n" +
                "    BillNo NUMERIC(5) PRIMARY KEY,\n" +
                "    DueAmount DECIMAL(7,2) NOT NULL,\n" +
                "    PayableAmount DECIMAL(7,2) NOT NULL\n" +
                ")";
        PreparedStatement Stmt=Con.prepareStatement(CreateBillTable);
        int nR=Stmt.executeUpdate();
        if(nR==0)System.out.println("Table Bill Successfully Created");
        else{System.out.println("Table Cannot Be Created");}
        Stmt.close();
        Con.close();
        return;
    }
    public static void StoreBillDetails(){

    }
    public static void DropTable() throws SQLException {
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String DROPTable="DROP TABLE Sprint2_US002_Customer";
        String DROPBillTable="DROP TABLE BillDetails";
        PreparedStatement Stmt=Con.prepareStatement(DROPTable);
        PreparedStatement Stmt1=Con.prepareStatement(DROPBillTable);
        Stmt.execute();
        Stmt1.execute();
        Stmt.close();
        Stmt1.close();
        Con.close();
    }
}
