import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Spring2_Backlog_US001_Customer_Reg_JDBC {
    static Scanner Sc=new Scanner(System.in);
    public static void main(String[] args) {

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
                ");";
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
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="INSERT INTO Sprint2_US001_Customer  VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement St=Con.prepareStatement(Statement);
        St.setLong(1,ConsumerId);
        St.setString(2,CustomerName);
        St.setString(3,Email);
        St.setLong(4,MobileNo);
        St.setString(5,UserId);
        St.setString(6,Password);
        St.setString(7,ConfirmPassword);
        St.setLong(8,BillNo);
        int nRowEffected=St.executeUpdate();
        if(nRowEffected>0){System.out.println("Customer Registration is Successful");}
        else {System.out.println("Customer Registration is Unsuccessful");}
    }

}
