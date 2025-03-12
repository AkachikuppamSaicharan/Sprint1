import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sprint2_US003_RegisterComplaint {
    static Scanner Sc=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        CreateComplaints();
        InsertComplaints();
        ViewComplaint();
        DropComplaints();

    }
    public  static void ViewComplaint() throws SQLException {
        Connection Con= DriverManager.getConnection("");
        String Statement="SELECT * FROM Complaints";
        PreparedStatement St=Con.prepareStatement(Statement);
        ResultSet Output=St.executeQuery();
        while(Output.next()){
            System.out.println(Output.getString(1)+" "+Output.getString(2)+" "+Output.getString(3)+" "+Output.getString(4)+" "+Output.getString(5)+" "+Output.getLong(6)+" "+Output.getString(7)+" "+Output.getLong(8));
        }
        Output.close();
        Con.close();

    }
    public static void CreateComplaints() throws SQLException{
        Connection Con=DriverManager.getConnection("");
        String Statement="CREATE TABLE Complaints(\n"
                + "	ComplaintType VARCHAR(75) NOT NULL,\n"
                + "	Category VARCHAR(75) NOT NULL,\n"
                + "	Landmark VARCHAR(100) NOT NULL,\n"
                + "	CustomerName VARCHAR(50) NOT NULL,\n"
                + "	Problem VARCHAR(500) NOT NULL,\n"
                + "	ConsumerNumber NUMERIC(13) PRIMARY KEY,\n"
                + "	Address VARCHAR(150) NOT NULL,\n"
                + "	MobileNumber NUMERIC(10) NOT NULL UNIQUE\n"
                + ")";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        int nRowEffected=Stmt.executeUpdate();
        if(nRowEffected>0) {System.out.println("Table Successfully Created");}
        else {System.out.println("Table Cant Be Created");}
        Stmt.close();
        Con.close();
    }
    public static void InsertComplaints() throws SQLException{
        String CmpType=Sc.nextLine();
        String Category=Sc.nextLine();
        String Landmark=Sc.nextLine();
        String CustName=Sc.nextLine();
        String Problem=Sc.nextLine();
        long ConsumerNo=Sc.nextLong();
        Sc.nextLine();
        String Address=Sc.nextLine();
        long MobileNo=Sc.nextLong();
        Sc.nextLine();
        Connection Con=DriverManager.getConnection("");
        String Statement="INSERT INTO Complaints VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        Stmt.setString(1, CmpType);
        Stmt.setString(2,Category);
        Stmt.setString(3, Landmark);
        Stmt.setString(4, CustName);
        Stmt.setString(5, Problem);
        Stmt.setLong(6, ConsumerNo);
        Stmt.setString(7, Address);
        Stmt.setLong(8, MobileNo);
        int nRows=Stmt.executeUpdate();
        if(nRows>0) {System.out.println("Successfully registered Complaint");}
        else {System.out.println("Cant Be Inserted");}
    }
    public static void DropComplaints() throws SQLException{
        Connection Con=DriverManager.getConnection("");
        String Statement="DROP TABLE Complaints";
        PreparedStatement Stmt=Con.prepareStatement(Statement);
        Stmt.execute();
        Stmt.close();
        Con.close();
    }
}
