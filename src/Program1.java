
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class Program1 {
 
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/CLG";
		String user = "root";
		String password= "admin123";
		try {
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  Connection con = DriverManager.getConnection(url, user, password);
			  System.out.println("Database Connected Successfully!");
			  
			  String insertQuery = "INSERT INTO classData(stu_name,stu_Id,stu_location) VALUES (?,?,?)";

	            PreparedStatement ps = con.prepareStatement(insertQuery);
	            ps.setString(1,"Ram");             
	            ps.setInt(2, 121);     
	            ps.setString(3, "Bhopal");              

	            int rows = ps.executeUpdate();

	            System.out.println(rows + " record inserted.");
	            
	            String selectQuery = "SELECT * FROM classData";

	            PreparedStatement ps2 = con.prepareStatement(selectQuery);
	            PreparedStatement ps3 = con.prepareStatement("Delete From clgData where stu_id =? ");
	            
	            ps.setInt(1, 121);
	            int rows1 = ps3.executeUpdate();

	            if(rows1 > 0)
	                System.out.println("Record deleted successfully!");
	            else
	                System.out.println("No record found!");
	            

	            ResultSet rs = ps2.executeQuery();

	            System.out.println("\nStudent Records:");

	            while (rs.next()) {
	                String name = rs.getString("stu_name");
	                int id = rs.getInt("Stu_Id");
	                String location = rs.getString("stu_location");

	                System.out.println(id + "  |  " + name + "  |  " + location);
	            }

   
			   con.close();
		}
		catch (ClassNotFoundException e) {

            System.out.println("JDBC Driver not found: " + e.getMessage());

        } 
        catch (SQLException e) {

      System.out.println("Database connection error: " + e.getMessage());

           
	}
		
		
	}

}
