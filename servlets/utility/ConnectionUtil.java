package com.proj.servlets.utility;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection connection;

	public static Connection getConnection() throws IOException, SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = System.getenv("jdbc:oracle:thin:@datahole.coclrgmgmyml.us-east-2.rds.amazonaws.com:1521:ORCL");
		String username = System.getenv("reimb1");
		String password = System.getenv("p4ssw0rd");
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@datahole.coclrgmgmyml.us-east-2.rds.amazonaws.com:1521:ORCL", "reimb1", "p4ssw0rd");
				
		}
		return connection;
	}
}