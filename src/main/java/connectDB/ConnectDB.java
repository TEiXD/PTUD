package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection conn = null;
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public Connection connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost\\\\Tei-Laptop:1433;databaseName=BanVeTau;integratedSecurity=false;encrypt=false;trustServerCertificate=true;";
		String user = "tei";
		String password = "29032004";
		return conn = DriverManager.getConnection(url, user, password);
	}

	public void disconnect() {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static Connection getConnection() {
		return conn;
	}
}
