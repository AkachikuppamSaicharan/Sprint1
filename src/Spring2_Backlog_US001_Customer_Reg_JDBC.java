import java.sql.*;
import java.util.Scanner;

public class Spring2_Backlog_US001_Customer_Reg_JDBC {
    static Scanner Sc=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        CreateTable();
        InsertCustomer();
        SelectCustomer();
    }
    public static void CreateTable() throws SQLException {
        Connection Con = DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement\"");
        String CreateTable = "CREATE TABLE Sprint2_US001_Customer(\n" +
                "    ConsumerId NUMERIC(13) PRIMARY KEY,\n" +
                "    BillNo NUMERIC(5) NOT NULL UNIQUE,\n" +
                "    Title VARCHAR(3) NOT NULL,\n" +
                "    CustomerName VARCHAR(50) NOT NULL,\n" +
                "    Email VARCHAR(250) NOT NULL UNIQUE,\n" +
                "    MobileNo NUMERIC(10) NOT NULL UNIQUE,\n" +
                "    UserId VARCHAR(20) NOT NULL UNIQUE,\n" +
                "    Password VARCHAR(30) NOT NULL,\n" +
                "    ConfirmPassword VARCHAR(30) NOT NULL\n" +
                ")";
        PreparedStatement Stmt = Con.prepareStatement(CreateTable);
        int nR = Stmt.executeUpdate();
        if (nR == 0) System.out.println("Table Successfully Created");
        else {
            System.out.println("Table Cannot Be Created");
        }
        return;
    }
    public static void InsertCustomer() throws SQLException {
        long ConsumerId=Sc.nextLong();
        int BillNo=Sc.nextInt();
        Sc.nextLine();
        String CustomerName=Sc.nextLine();
        String Email=Sc.nextLine();
        long MobileNo=Sc.nextLong();
        Sc.nextLine();
        String UserId=Sc.nextLine();
        String Password=Sc.nextLine();
        String ConfirmPassword=Sc.nextLine();
        System.out.println("Consumer ID: "+ConsumerId);
        System.out.println("Bill No: "+BillNo);
        System.out.println("Customer Name: "+CustomerName);
        System.out.println("Email: "+Email);
        System.out.println("Mobile No: "+MobileNo);
        System.out.println("User ID: "+UserId);
        System.out.println("Password: "+Password);
        System.out.println("Confirm Password: "+ConfirmPassword);
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="INSERT INTO Sprint2_US001_Customer  VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement St=Con.prepareStatement(Statement);
        St.setLong(1, ConsumerId);
        St.setInt(2, BillNo);
        St.setString(3, CustomerName);
        St.setString(4, Email);
        St.setLong(5, MobileNo);
        St.setString(6, UserId);
        St.setString(7, Password);
        St.setString(8, ConfirmPassword);
        int nRowEffected=St.executeUpdate();
        if(nRowEffected>0){System.out.println("Customer Registration is Successful");}
        else {System.out.println("Customer Registration is Unsuccessful");}
    }
    public static void SelectCustomer() throws SQLException{
        Connection Con=DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="SELECT * FROM Sprint2_US001_Customer";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        ResultSet Output=Stmt.executeQuery();
        while(Output.next()){
            System.out.println(Output.getLong(1));
        }
        Output.close();
        Stmt.close();
        Con.close();
    }

}
