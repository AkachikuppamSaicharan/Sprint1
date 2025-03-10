import java.sql.*;
import java.util.Scanner;

public class Sprint_1 {
    static Scanner Sc=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        boolean Loop=true;
        while(Loop){
            System.out.println("--------------Admin Database--------------");
            System.out.println("(1):Insert Into Table");
            System.out.println("(2):Search Using Customer Id");
            System.out.println("(3):Search Using Email Domain");
            System.out.println("(4):Exit");
            int Option=Sc.nextInt();
            switch(Option){
                case 1:
                    InsertCustomer();
                    break;
                case 2:
                    int CustomerId=Sc.nextInt();
                    SearchCustomerById(CustomerId);
                    break;
                case 3:
                    Sc.nextLine();
                    String CustomerEmail=Sc.nextLine();
                    SearchByEmailDomain(CustomerEmail);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose Correct Option");
                    break;
            }
        }
    }
    public static String InsertCustomer() throws SQLException {
        Sc.nextLine();
        int CustomerId=GenerateRandomNumber();
        String CustomerName=Sc.nextLine();
        String Email=Sc.nextLine();
        String Password=Sc.nextLine();
        String Address=Sc.nextLine();
        String ContactNo=Sc.nextLine();
        String NomineeName=Sc.nextLine();
        String RelWithCust=Sc.nextLine();
        System.out.println(CustomerId+" "+CustomerName+" "+Address+" "+ContactNo+" "+NomineeName+" "+RelWithCust);
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement");
        String Statement="INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement St=Con.prepareStatement(Statement);
        St.setInt(1,CustomerId);
        St.setString(2,CustomerName);
        St.setString(3,Email);
        St.setString(4,Password);
        St.setString(5,Address);
        St.setString(6,ContactNo);
        St.setString(7,NomineeName);
        St.setString(8,RelWithCust);
        int nRowEffected=St.executeUpdate();
        Con.close();
        if(nRowEffected>0) return "Customer Registration is Successful";
        else return "Customer Cant Be Inserted";
    }
    public static ResultSet SearchCustomerById(int CustId) throws SQLException {
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement","","");
        PreparedStatement St=Con.prepareStatement("SELECT * FROM Customer WHERE CustomerId=?");
        St.setInt(1,CustId);
        ResultSet Output=St.executeQuery();
        Con.close();
        return Output;
    }
    public static ResultSet SearchByEmailDomain(String EmailDomain) throws SQLException {
        Connection Con= DriverManager.getConnection("jdbc:derby:AkachikuppamSaiCharan_ElectricityManagement","","");
        PreparedStatement St=Con.prepareStatement("SELECT * FROM Customer WHERE LOWER(Email) LIKE ? ORDER BY CustomerId");
        String LikeStatement="%@"+EmailDomain+".com";
        St.setString(1,EmailDomain);
        ResultSet Output=St.executeQuery();
        Con.close();
        return Output;
    }
    public static int GenerateRandomNumber(){
       return (int) (1000000+ Math.random()*9000000);
    }
}
