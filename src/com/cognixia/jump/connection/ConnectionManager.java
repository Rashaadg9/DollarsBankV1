package com.cognixia.jump.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
	// Windows: jdbc:mysql://localhost:3306/university
	// MAC/Linux: jdbc:mysql://localhost:3306/university?serverTimezone=EST5EDT
	private static final String URL = "jdbc:mysql://localhost:3306/DBankDB";
	private static final String USERNAME= "root";
	private static final String PASSWORD = "root";
	
	// method for creating a connection
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			// Register the driver -> load in the correct classes from the provided Driver Library
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			System.out.println("Made Connection");
		}
		catch (SQLException e)
		{
			System.out.println("Couldn't make connection");
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void main(String[] args)
	{
		Connection conn = ConnectionManager.getConnection();
		
		try
		{
			conn.close();
			System.out.println("Connection was closed");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}
