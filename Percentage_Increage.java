
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Percentage_Increage {
  
	public static void check_stu_data(Scanner dd,Connection con) throws SQLException {
		System.out.println("Enter stu_id");
		int id = dd.nextInt();
		String querry = "Select * From studata where stu_id=?";
	 PreparedStatement ps = con.prepareStatement(querry);
	ps.setInt(1,id);
	ResultSet rs = ps.executeQuery();
	 System.out.println("ID\tName\tbranch\tpercentage");
	    System.out.println("--------------------------");
	while(rs.next()) {
		int id1 = rs.getInt("stu_id");
        String name = rs.getString("stu_name");
        String branch = rs.getString("stu_branch");
        double percentage = rs.getDouble("stu_percentage");
        System.out.println(id1 + "\t" + name + "\t" + branch+"\t"+ percentage+"%");

	}
	}
	
	 public static void showData(Connection con) throws SQLException {
		    String query = "SELECT stu_id, stu_name, stu_branch, stu_percentage FROM stuData";

		    Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery(query);

		    System.out.println("ID\\tName\\tLocation\tpercentage");
		    System.out.println("--------------------------");

		    while(rs.next()) {
				int id1 = rs.getInt("stu_id");
		        String name = rs.getString("stu_name");
		        String branch = rs.getString("stu_branch");
		        double percentage = rs.getDouble("stu_percentage");
		        System.out.println(id1 + "\t" + name + "\t" + branch+"\t"+ percentage+"%");

			}

		}
	 
 public static void modifydata(Scanner dd,Connection con) throws SQLException {
		 String querry ="";
//		 System.out.println("press 1 for subtract percentage"+"\n"+" press 2 for icrease percentage");
//		 int choice = dd.nextInt();
//		 switch (choice) {
//		 case 1: 
//			 System.out.println("Enter stu_Id");
//			 int id = dd.nextInt();
//			 System.out.println("Enter percentage for subtraction");
//			 double per= dd.nextDouble();
//			 System.out.println("Enter branch for subtraction");
//			 String branch = dd.next();
//			 querry = "UPDATE STUdata set stu_percentage = stu_percentage - ? where stu_id =? and stu_branch =?";
//			 PreparedStatement ps = con.prepareStatement(querry);
//			 ps.setDouble(1,per);
//			 ps.setInt(2,id);
//			 ps.setString(3, branch);
//			 int rows = ps.executeUpdate();
//			 System.out.println(rows>0?"percentage modify successfully":"Invalid data");
//			 break;
//			 
//		 case 2: 
//			 System.out.println("Enter stu_Id");
//			 int id1 = dd.nextInt();
//			 System.out.println("Enter percentage for Increase");
//			 double per1= dd.nextDouble();
//			 System.out.println("Enter branch for Increase");
//			 String branch1 = dd.next();
			 querry = "UPDATE STUdata set stu_percentage = stu_percentage + 5 where stu_branch= 'cse' ";
			 PreparedStatement ps1 = con.prepareStatement(querry);
//			 ps1.setDouble(1,per1);
//			 ps1.setInt(2,id1);
//			 ps1.setString(3, branch1);
			 int rows1 = ps1.executeUpdate();
			 System.out.println(rows1>0?"percentage modify successfully":"Invalid data");
//			 break;
//			 default :
//				 System.out.println("Enter valid data");
		 
		 
	 }

	
	
	public static void main(String[] args) throws SQLException, InterruptedException {
	// Database --> student1,  table name --> stuData
	// table fields--> stuId, stu_name, branch, percentage
		// id start from 121
		Scanner dd = new Scanner(System.in);
		 Connection con =null;
		String  url = "jdbc:mysql://localhost:3306/student1";
		String user = "root";
		String password = "admin123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection(url,user,password);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		 int choice =-1;
		 System.out.println("This method is create to access stud percentage and modify their percentage");
while(choice !=0) {
	System.out.println("press 1 for show studata with stu_id");
	System.out.println("press 2 for check all studens reccord");
	System.out.println("press 3 for modify percentage");
	System.out.println("press 0 for exit");
	System.out.println("Enter your choice");
	choice = dd.nextInt();
	 switch(choice) {
	 case 1 :
		 check_stu_data(dd,con);
		 break;
	
	 case 2:
		 showData(con);
		 break;
		 
	 case 3:
		 modifydata(dd,con);
		 break;
	 case 0: 
		 System.out.println("Code Execute successfully");
		 return;
	 default :
		 System.out.println("Enter valid choice");
	 }
	    
	       Thread.sleep(1000);
}
		 
	}

}

