import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete_Record_basisOnYearOfpassing {
	public static void check_stu_data(Scanner dd,Connection con) throws SQLException {
		System.out.println("Enter stu_id");
		int id = dd.nextInt();
		String querry = "Select * From studata1 where stu_id=?";
	 PreparedStatement ps = con.prepareStatement(querry);
	ps.setInt(1,id);
	ResultSet rs = ps.executeQuery();
	 System.out.println("ID\tName\tbranch\tpercentage\tYOP");
	    System.out.println("--------------------------");
	while(rs.next()) {
		int id1 = rs.getInt("stu_id");
        String name = rs.getString("stu_name");
        String branch = rs.getString("stu_branch");
        double percentage = rs.getDouble("stu_percentage");
        String yop = rs.getString("Stu_YOp");
        System.out.println(id1 + "\t" + name + "\t" + branch+"\t"+ percentage+"%"+"\t"+yop);

	}
	}
	
	 public static void showData(Connection con) throws SQLException {
		    String query = "SELECT stu_id, stu_name, stu_branch, stu_percentage, STU_YOP FROM stuData1";

		    Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery(query);

		    System.out.println("ID\\tName\\tLocation\\tpercentage\\tYOP");
		    System.out.println("--------------------------");

		    while(rs.next()) {
				int id1 = rs.getInt("stu_id");
		        String name = rs.getString("stu_name");
		        String branch = rs.getString("stu_branch");
		        double percentage = rs.getDouble("stu_percentage");
		        String yop = rs.getString("STU_YOP");
		        System.out.println(id1 + "\t" + name + "\t" + branch+"\t"+ percentage+"%"+"\t"+yop);

			}

		}

private static void insertData(Connection con, Scanner dd) throws SQLException {
			System.out.println("Enter student details to insert ");
			System.out.println("Enter student id to  insert ");
	        int id = dd.nextInt();	
	        System.out.println("Enter student name to  insert ");
			String name = dd.next();
			System.out.println("Enter student Branch to  insert ");	
		   String branch = dd.next();
		   System.out.println("Enter student percentage to  insert ");
		  double percentage = dd.nextDouble();
		  System.out.println("Enter student YEAR OF PASSING  to  insert ");
		  int yop = dd.nextInt();
	
String querry = "Insert into studata1(stu_Id,Stu_name,Stu_branch,Stu_Percentage,Stu_YOP)) Values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(querry);
		ps.setInt(1, id);
		ps.setString(2,name);
		ps.setString(3,branch);
		ps.setDouble(4,percentage);
		ps.setInt(5,yop);
		int row = ps.executeUpdate();
		System.out.println(row>0?"Row insert successfully":"Invalid data");
		
		}
	 public static void deleteData(Connection con,Scanner dd ) throws SQLException {
//		 System.out.println("Enter stu year of passing to delte data");
//		 int yop = dd.nextInt();
//		 System.out.println("Enter stu branch to delte data");
//		 String branch = dd.next();
		 String query = "Delete From stu_Data1 where stu_yop = 2024 and stu_branch=civil";
		 PreparedStatement ps = con.prepareStatement(query);
		 
//		 ps.setInt(1, yop);
//		 ps.setString(2, branch);
		System.out.println("With delete data");
		showData(con);
		 int row  = ps.executeUpdate();
		 
		 System.out.println(row>0?"Delete record successfully":"Invalid data");
		 showData(con);
	 }
	
	public static void main(String[] args) throws SQLException, InterruptedException {
		// Database --> student1,  table name --> stuData1
		// table fields--> stuId, stu_name, branch, percentage,YOP
			// id start from 131
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
			 System.out.println("This method is create to insert new student and delte passout student");
		while(choice!=0) {
			 System.out.println("press 1 for show studata with stu_id");
		System.out.println("press 2 for check all studens reccord");
		System.out.println("press 3 for Delete data based on YOP");
		System.out.println("Press 4 for Insert stu_data");
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
			 deleteData(con,dd);
			 break;
		 case 4:
			 insertData(con,dd);
			 break;
		 case 0: 
			 System.out.println("Code Execute successfully");
			 return;
		 default :
			 System.out.println("Enter valid choice");
		 }
		    
		       Thread.sleep(3000);
	}
	}


}

