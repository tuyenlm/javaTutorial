package timeSystem;

import java.sql.*;

public class accessDbConnection {
	public static void main(String[] args) throws ClassNotFoundException {
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/postgres",
	            "postgres", "postgres");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         DatabaseMetaData md = c.getMetaData();
	         ResultSet usersTable = md.getTables(null, null, "users", null);
	         stmt = c.createStatement();
	         switch (key) {
			case value:
				
				break;

			default:
				break;
			}
	         if (usersTable.next()) {
	        	 ResultSet rs = stmt.executeQuery( "SELECT * FROM users;" );
		         while ( rs.next() ) {
		            int id = rs.getInt("id");
		            String  fullname = rs.getString("fullname");
		            String username  = rs.getString("username");
		            System.out.println( "ID = " + id );
		            System.out.println( "fullname = " + fullname );
		            System.out.println( "username = " + username );
	
		            System.out.println();
		         }
		         rs.close();
	         }
	         
	         ResultSet companyTable = md.getTables(null, null, "COMPANY", null);
	         if (companyTable.next()) {
		         ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
		         while ( rs.next() ) {
		            int id = rs.getInt("id");
		            String  name = rs.getString("name");
		            int age  = rs.getInt("age");
		            String  address = rs.getString("address");
		            float salary = rs.getFloat("salary");
		            System.out.println( "ID = " + id );
		            System.out.println( "NAME = " + name );
		            System.out.println( "AGE = " + age );
		            System.out.println( "ADDRESS = " + address );
		            System.out.println( "SALARY = " + salary );
		            System.out.println();
		         }
		         rs.close();
	         }
	         
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Operation done successfully");
	}
}
