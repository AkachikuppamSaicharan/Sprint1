import java.sql.*;
import java.util.Scanner;

public class Sprint_2_US002_Bill_Details_JDBC {
    static Scanner Sc=new Scanner(System.in);
    static int nR=0;
    public static void main(String[] args) throws SQLException {
        CreateCustomerTable();
        CreateBillTable();
        StoreBillDetails();
        QueryBillDetails();
//        DropTable();

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
    public static void StoreBillDetails() throws SQLException{
       for(int i=0;i<4;i++){
           getCustomerDetails();
       }
        for(int i=0;i<10;i++){getBillDetails();}
        if(nR==14){System.out.println("Bill Details Successfully Stored");}
        else{System.out.println("Error In Bill Details Stored");}
    }
    public static void getBillDetails() throws SQLException{
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="INSERT INTO BillDetails VALUES(?,?,?,?)";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        int BillNo=Sc.nextInt();
        Stmt.setInt(1,BillNo);
        double PayableAmount=Sc.nextDouble();
        Stmt.setDouble(2,PayableAmount);
        double DueAmount=Sc.nextDouble();
        Stmt.setDouble(3,DueAmount);
        long CustomerNumber=Sc.nextLong();
        Stmt.setLong(4,CustomerNumber);
        System.out.println(BillNo+" "+PayableAmount+" "+DueAmount+" "+CustomerNumber);
        nR+=Stmt.executeUpdate();

    }
    public static void getCustomerDetails() throws SQLException{
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="INSERT INTO US002_Customer VALUES(?,?,?,?)";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        long CiD=Sc.nextLong();
        Sc.nextLine();
        String CustomerName=Sc.nextLine();
        String Email=Sc.nextLine();
        long MobileNumber=Sc.nextLong();
        System.out.println(CustomerName+" "+Email+" "+MobileNumber);
        Stmt.setLong(1,CiD);
        Stmt.setString(2,CustomerName);
        Stmt.setString(3,Email);
        Stmt.setLong(4,MobileNumber);
        nR+=Stmt.executeUpdate();
        Stmt.close();
        Con.close();
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
    public static void QueryBillDetails() throws SQLException{
        Connection Con=DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="SELECT B.CustomerNumber,C.CustomerName,B.PayableAmount,B.DueAmount\n" +
                "FROM BillDetails AS B JOIN US002_Customer AS C\n" +
                "ON B.CustomerNumber=C.CustomerNumber";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        ResultSet rs=Stmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getLong(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getDouble(4));
        }

    }
}
