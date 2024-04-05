package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String DB_URL = "jdbc:sqlserver://localhost\\\\Tei-Laptop:1433;databaseName=BanVeTau;integratedSecurity=false;encrypt=false;trustServerCertificate=true;";
    private static final String DB_USER = "tei";
    private static final String DB_PASSWORD = "29032004";

    private static Connection conn = null;
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public Connection connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle the exception (log, throw, or return null)
        }
        return conn;
    }

    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
