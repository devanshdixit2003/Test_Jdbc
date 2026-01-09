import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ShowDataOf_7th_Sem {
	  
	 public static void showData(Connection con) throws SQLException {
		    String query = "SELECT stu_id, stu_name, stu_branch, stu_percentage, STU_YOP,stu_Semester FROM stuData2 where stu_Semester = 7 ";

		    Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery(query);

		    System.out.println("ID\\tName\\tLocation\\tpercentage\\tYOP\\semester");
		    System.out.println("--------------------------");

		    while(rs.next()) {
				int id1 = rs.getInt("stu_id");
		        String name = rs.getString("stu_name");
		        String branch = rs.getString("stu_branch");
		        double percentage = rs.getDouble("stu_percentage");
		        String yop = rs.getString("STU_YOP");
		        int sem = rs.getInt("stu_Semester");
		        System.out.println(id1 + "\t" + name + "\t" + branch+"\t"+ percentage+"%"+"\t"+yop+"\t"+sem);

			}

		}


	public static void main(String[] args) throws SQLException {
		// Database --> student1,  table name --> stuData2
				// table fields--> stuId, stu_name, branch, percentage,YOP, stu_semester
		Connection con = null;
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
		showData(con);

}
}
