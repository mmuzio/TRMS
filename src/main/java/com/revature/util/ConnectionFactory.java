package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static String url;
	
	private static String username;
	
	private static String password;
	
	//This will be the only one existing in memory, because it is a singleton
	private static ConnectionFactory cf;
	
	private static final String SCHEMA = "trms-test";
	
	public static Connection getConnection() {
		
		if (cf==null) {
			
			cf = new ConnectionFactory();
			
		}
		
		return cf.createConnection();
		
	}
	
	private ConnectionFactory() {
		//private constructor helps make this a singleton
		
		url = "jdbc:postgresql://localhost:5432/postgres?";
		
		username = "postgres";
		
		password = "postgres";
		
		
	}
	
	private Connection createConnection() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			
		} catch (ClassNotFoundException e1) {
			
			System.out.println("Could not load PostgreSQL Driver");
			
			e1.printStackTrace();
			
		}
		
		try {
			
			conn = DriverManager.getConnection(url, username, password);
			
			conn.setSchema(SCHEMA);
			
		} catch (SQLException e) {
			
			System.out.println("Problem create DB connection");
			
			e.printStackTrace();
			
		}
		
		return conn;
		
	}
}
