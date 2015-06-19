package ch3_1;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ThreadLocalResource {
	
	private static DataSource _dataSource = null;
	
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
		public Connection initialValue() {
			Connection con = null;
			try {
				return _dataSource.getConnection();
			} catch (SQLException e) {
				out.println("Could not get connection");
			}
			return con;
		}

		
	};
	
	public static void setDataSource(DataSource dataSource) {
		_dataSource = dataSource;
	}
	
	public static Connection getConnection() {
		return connectionHolder.get();
	}

}
