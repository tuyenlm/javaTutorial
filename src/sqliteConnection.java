import java.sql.*;
import javax.swing.*;

public class sqliteConnection {
	Connection conn = null;

	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\tuyen\\workspace\\JavaTutorial\\EmployeeData.sqlite");
			JOptionPane.showConfirmDialog(null, "Connection Successful!");
			return conn;
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e);
			return null;
		}
	}
}
