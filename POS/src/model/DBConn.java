package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static String dbuid = "POS";
	private static String dbpwd = "1234";
	
	private static Connection conn = null;

	private DBConn() {}
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, dbuid, dbpwd);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
}
