package dk.kea.pokeshopdemo.utility;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
	{
		private static Connection connection;

		private ConnectionManager(){}
		public static Connection getConnection(String DB_URL,String UID, String PWD){
				if (connection == null) {

					try {
						connection= DriverManager.getConnection(DB_URL,UID,PWD);
					}
					catch (SQLException e) {
						System.out.println("could not connect to database");
						e.printStackTrace();
					}
				}
				return connection;
			}
	}
