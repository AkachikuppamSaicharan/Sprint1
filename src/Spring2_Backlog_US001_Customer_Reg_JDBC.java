import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Spring2_Backlog_US001_Customer_Reg_JDBC {
    static Scanner Sc=new Scanner(System.in);
    public static void main(String[] args) {

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
        String Statement="INSERT INTO Sprint2Customer VALUES(?,?,?,?,?,?,?,?,?)";
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
