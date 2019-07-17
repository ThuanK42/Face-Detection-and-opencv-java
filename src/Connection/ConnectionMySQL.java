package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionMySQL {
	// Kết nối vào MySQL.
	public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
		String hostName = "localhost";
		String dbName = "nhandienkhuonmat";
		String userName = "root";
		String password = "";
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
		Connection conn = (Connection) DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}

	public static void main(String[] args) {
		try {
			Connection connection = ConnectionMySQL.getMySQLConnection();
			System.out.println("Ket noi thanh cong");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ket noi that bai");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
