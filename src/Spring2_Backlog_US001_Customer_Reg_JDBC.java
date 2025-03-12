import java.sql.*;
import java.util.Scanner;

public class Spring2_Backlog_US001_Customer_Reg_JDBC {
    static Scanner Sc=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        CreateTable();
        for(int i=1;i<=5;i++){InsertCustomer();}
        SelectCustomer();
        DropTable();
    }
    public static void CreateTable() throws SQLException {
        Connection Con = DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
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
        String[] Titles={"Mr","Ms","Mrs","Dr","Prof"};
        System.out.println("Select The Title From the Below:\n(1):Mr\n(2):Ms\n(3):Mrs\n(4):Dr\n(5):Prof");
        int TitlesIndex=Sc.nextInt();
        Sc.nextLine();
        String CustomerName=Sc.nextLine();
        String Email=Sc.nextLine();
        long MobileNo=Sc.nextLong();
        Sc.nextLine();
        String UserId=Sc.nextLine();
        String Password=Sc.nextLine();
        String ConfirmPassword=Sc.nextLine();
//        System.out.println("Consumer ID: "+ConsumerId);
//        System.out.println("Bill No: "+BillNo);
//        System.out.println("Customer Name: "+CustomerName);
//        System.out.println("Email: "+Email);
//        System.out.println("Mobile No: "+MobileNo);
//        System.out.println("User ID: "+UserId);
//        System.out.println("Password: "+Password);
//        System.out.println("Confirm Password: "+ConfirmPassword);
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="INSERT INTO Sprint2_US001_Customer  VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement St=Con.prepareStatement(Statement);
        St.setLong(1, ConsumerId);
        St.setInt(2, BillNo);
        St.setString(3, Titles[TitlesIndex-1]);
        St.setString(4, CustomerName);
        St.setString(5, Email);
        St.setLong(6, MobileNo);
        St.setString(7, UserId);
        St.setString(8, Password);
        St.setString(9, ConfirmPassword);
        int nRowEffected=St.executeUpdate();
        if(nRowEffected>0){System.out.println("Customer Registration is Successful");}
        else {System.out.println("Customer Registration is Unsuccessful");}
    }
    public static void SelectCustomer() throws SQLException{
        Connection Con=DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="SELECT * FROM Sprint2_US001_Customer";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        ResultSet Output=Stmt.executeQuery();
        System.out.println("CustomerId  | Bill Number | Title | Customer Name | Email | Mobile No | UserId | Password | ConfirmPassword");
        while(Output.next()){
            System.out.println(Output.getLong(1)+"|"+Output.getInt(2)+"|"+Output.getString(3)+"|"+Output.getString(4)+"|"+Output.getString(5)+"|"+Output.getLong(6)+"|"+Output.getString(7)+"|"+Output.getString(8)+"|"+Output.getString(9));
        }
        Output.close();
        Stmt.close();
        Con.close();
    }
    public static void DropTable() throws SQLException {
        Connection Con=DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="DROP TABLE Sprint2_US001_Customer";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        Stmt.execute();
        Stmt.close();
        Con.close();
    }

}

/*
1578984512154
98664
1
A.SAI CHARAN
akachikuppamsaicharan@gmail.com
7989591890
Akach
Akach
Akach
 */