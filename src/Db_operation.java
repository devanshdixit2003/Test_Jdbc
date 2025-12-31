import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Db_operation {
	
public static void add(Scanner dd,Connection con)throws SQLException {
		String insertQuery = "INSERT INTO classData(stu_name,stu_Id,stu_location) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(insertQuery);
        System.out.println("Enter value  to insert first student name, second roll no,third location ");
    	String name = dd.nextLine();
    	dd.next();
    	int rollno = dd.nextInt();
    	String location = dd.next();
    	 ps.setString(1,name);             
         ps.setInt(2, rollno);     
         ps.setString(3, location);              

         int rows = ps.executeUpdate();

         System.out.println(rows + " record inserted successfully");
	} 
	public static void delete(Scanner dd,Connection con) throws SQLException {
	
	PreparedStatement ps = con.prepareStatement("Delete from classData where stu_id = ?");
	System.out.println("Enter stu_id whose want to delete data");
	int id = dd.nextInt();
	ps.setInt(1,id);
	int row = ps.executeUpdate();
	if(row>0) {
		System.out.println("Row delete Successfully");
	}
	else {
		System.out.println("Entered id not registered");
	}
	
	}
  
	 public static void update(Scanner dd, Connection con) throws SQLException {
		 String querry ="";
		 System.out.println("Enter stud_ID whose want to update");
		 int id  = dd.nextInt();
		 System.out.println("Enter choice to update things");
		 System.out.println("Enter 1 for update name");
		 System.out.println("Enter 2 for update stu_location");
		 int choice = dd.nextInt();
		 switch(choice) {
		 case 1: 
			 System.out.println("Enter new_Name");
			 String newname = dd.next();
			 querry = "UPDATE classData SET Stu_name ="+ newname +"WHERE stu_id ="+id;
		     break;
		 case 2: 
			 System.out.println("Enter new_Location");
			 String newLocation = dd.next();
			 querry = "UPDATE classData SET stu_location ="+ newLocation +"WHERE student_id ="+id;
		     break;
		     
		  default :
			  System.out.println("Enter valid choice");
		 }
		 PreparedStatement ps = con.prepareStatement(querry);
		 int row = ps.executeUpdate();
		 
		 System.out.println(row>0?"Update Successfully":"Entered id not registered");
	 }
	 public static void showData(Connection con) throws SQLException {
		    String query = "SELECT stu_id, stu_name, stu_location FROM classData";

		    Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery(query);

		    System.out.println("ID\tName\tLocation");
		    System.out.println("--------------------------");

		    while (rs.next()) {
		        int id = rs.getInt("stu_id");
		        String name = rs.getString("stu_name");
		        String location = rs.getString("stu_location");

		        System.out.println(id + "\t" + name + "\t" + location);
		    }
		}
	
	public static void main(String[] args) throws SQLException {
		Connection con =null;
	
		Scanner dd = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/CLG";
		String user = "root";
		String password= "admin123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			  con = DriverManager.getConnection(url, user, password);
			  
		}
		
        catch (Exception e) {

      System.out.println("Database connection error: " + e.getMessage());       
	}
		int choice=-1;
	while(choice!=0) {
		System.out.println("Enter 1  to perform add operation");
		System.out.println("Enter 2  to perform delete operation");
		System.out.println("Enter 3 to perform update operation");
		System.out.println("Enter 4 to perform showData operation");
		System.out.println("Enter choice to perform operation");
		choice = dd.nextInt();
		
	switch(choice) {
		case 1:
			add(dd,con);
			break;
		case 2 :
			delete(dd,con);
			break;
		case 3:
			update(dd,con);
			break;
		case 4: 
			showData(con);
			break;
		default : 
			System.out.println("Enter valid name");
		
	}
	try {
	    Thread.sleep(10000); // 10 seconds
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
		}
		
		
	}
}


