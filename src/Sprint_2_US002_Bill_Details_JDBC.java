import java.sql.*;
import java.util.Scanner;

public class Sprint_2_US002_Bill_Details_JDBC {
    static Scanner Sc=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        CreateCustomerTable();
        CreateBillTable();
        DropTable();

    }
    public static void CreateCustomerTable() throws SQLException {
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String CreateTable="CREATE TABLE US002_Customer(\n" +
                "    CustomerNumber NUMERIC(13) PRIMARY KEY,\n" +
                "    CustomerName VARCHAR(50) NOT NULL,\n" +
                "    Email VARCHAR(250) NOT NULL UNIQUE,\n" +
                "    MobileNumber NUMERIC(10) NOT NULL UNIQUE\n" +
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
                "    PayableAmount NUMERIC(7,2) NOT NULL,\n" +
                "    DueAmount NUMERIC(7,2) NOT NULL,\n" +
                "    CustomerNumber NUMERIC(13),\n" +
                "    Constraint Fkey FOREIGN KEY(CustomerNumber) REFERENCES US002_Customer(CustomerNumber)\n" +
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
        String DROPTable="DROP TABLE US002_Customer";
        String DROPBillTable="DROP TABLE BillDetails";
        PreparedStatement Stmt=Con.prepareStatement(DROPTable);
        PreparedStatement Stmt1=Con.prepareStatement(DROPBillTable);
        Stmt1.execute();
        Stmt.execute();
        Stmt.close();
        Stmt1.close();
        Con.close();
    }
}
